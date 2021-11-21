package com.mrkekman04.bloodybranch.tile;

import WayofTime.bloodmagic.block.BlockLifeEssence;
import com.mrkekman04.bloodybranch.blocks.BlockBloodFactory;
import com.mrkekman04.bloodybranch.core.helper.RecipeHelper;
import com.mrkekman04.bloodybranch.energy.CustomEnergyStorage;
import com.mrkekman04.bloodybranch.items.ItemMatrix;
import com.mrkekman04.bloodybranch.recipes.factory.RecipesFactory;
import com.mrkekman04.bloodybranch.recipes.factory.RecipesFactoryHandler;
import com.mrkekman04.bloodybranch.utils.fluidutil.CustomFluidTank;
import com.mrkekman04.bloodybranch.utils.handlers.CustomInventoryHandler;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import org.jetbrains.annotations.Nullable;

public class TileBloodFactory extends TileMachineInventory implements ITickable {

    private int totalTime;
    private int processTime;
    RecipesFactory activeRecipe = null;

    CustomFluidTank tank = new CustomFluidTank( BlockLifeEssence.getLifeEssence(), 0, 10 * Fluid.BUCKET_VOLUME);
    CustomEnergyStorage energyStorage = new CustomEnergyStorage(10000, 100, 100);

    private int energyPerTick = 0;
    private int fluidPerTick = 0;

    //Slots :
    //0 Input
    //1 Output
    public TileBloodFactory() {
        super(2);
    }

    @Override
    public String getName() {
        return super.getName() + "factory";
    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        return index == 0;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        this.processTime = compound.getInteger("processTime");
        this.totalTime = compound.getInteger("totalTime");

        this.fluidPerTick = compound.getInteger("workingFluid");
        this.energyPerTick = compound.getInteger("workingEnergy");

        if (compound.hasKey("tank") && tank != null) {
            tank.readFromNBT(compound.getCompoundTag("tank"));
        }
        if (compound.hasKey("energyStorage") && energyStorage != null) {
            CapabilityEnergy.ENERGY.readNBT(energyStorage, null, compound.getTag("energyStorage"));
        }
        super.readFromNBT(compound);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.setInteger("processTime", this.processTime);
        compound.setInteger("totalTime", this.totalTime);

        compound.setInteger("workingFluid", this.fluidPerTick);
        compound.setInteger("workingEnergy", this.energyPerTick);

        if (tank != null) {
            compound.setTag("tank", tank.writeToNBT(new NBTTagCompound()));
        }

        if (energyStorage != null) {
            compound.setTag("energyStorage", CapabilityEnergy.ENERGY.writeNBT(energyStorage, null));
        }
        return super.writeToNBT(compound);
    }

    @SideOnly(Side.CLIENT)
    public static boolean isProcessing(IInventory inventory) {
        return inventory.getField(1) > 0;
    }

    @Override
    public int getField(int id) {
        switch (id) {
            case 0:
                return totalTime;
            case 1:
                return processTime;
            case 2:
                return energyStorage.getEnergyStored();
            case 3:
                return tank.getFluidAmount();
            default:
                return 0;
        }
    }

    @Override
    public void setField(int id, int value) {
        switch (id) {
            case 0:
                totalTime = value;
                break;
            case 1:
                processTime = value;
                break;
            case 2:
                energyStorage.setEnergyStored(value);
                break;
            case 3:
                tank.setFluidAmount(value);
                break;
        }
    }

    @Override
    public int getFieldCount() {
        return 4;
    }

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
        if (capability == CapabilityEnergy.ENERGY) {
            return true;
        }
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return true;
        }
        if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
            return true;
        }
        return super.hasCapability(capability, facing);
    }

    private IItemHandlerModifiable invHandler = new CustomInventoryHandler(this, 2, 0,
            new boolean[]{true, false},
            new boolean[]{false, true});

    @Nullable
    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        if (CapabilityEnergy.ENERGY.equals(capability) && energyStorage != null) {
            return CapabilityEnergy.ENERGY.cast(energyStorage);
        }
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return (T) invHandler;
        }
        if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
            return (T) tank;
        }
        return super.getCapability(capability, facing);
    }

    @Override
    public void update() {
        if (!getWorld().isRemote) {

            if (inventory.get(0).isEmpty()) {
                reset();
                world.setBlockState(pos, world.getBlockState(pos).withProperty(BlockBloodFactory.WORKING, false));
            } else {
                if (activeRecipe == null) {
                    RecipesFactory recipe = RecipesFactoryHandler.getRecipe(inventory.get(0));
                    if (recipe != null) {
                        if (energyStorage.getEnergyStored() >= recipe.getEnergyPerTick() * totalTime && tank.getFluidAmount() >= recipe.getFluidPerTick() * totalTime) {
                            activeRecipe = recipe;
                            totalTime = recipe.getTime();
                            energyPerTick = recipe.getEnergyPerTick();
                            fluidPerTick = recipe.getFluidPerTick();
                        }
                    } else {
                        reset();
                    }
                }

                if (activeRecipe == null
                        || totalTime < 0
                        || !RecipeHelper.canMergeItemStack(activeRecipe.getOutputResource(), inventory.get(1))) {
                    world.setBlockState(pos, world.getBlockState(pos).withProperty(BlockBloodFactory.WORKING, false));
                    reset();
                    return;
                }

                if (activeRecipe == RecipesFactoryHandler.getRecipe(inventory.get(0))) {
                    if (RecipeHelper.canMergeItemStack(activeRecipe.getOutputResource(), inventory.get(1))) {
                        if (RecipesFactoryHandler.getRecipe(inventory.get(0)) != null) {

                            if (world.getBlockState(pos) == world.getBlockState(pos).withProperty(BlockBloodFactory.WORKING, false)) {
                                world.setBlockState(pos, world.getBlockState(pos).withProperty(BlockBloodFactory.WORKING, true));
                            }

                            processTime++;

                            energyStorage.extractEnergy(energyPerTick, false);
                            tank.drain(fluidPerTick, true);

                            if (processTime >= totalTime) {

                                if (!(activeRecipe.getOutputResource().getItem() instanceof ItemMatrix)) {
                                    inventory.get(0).shrink(activeRecipe.getInput().getCount());
                                }

                                ItemStack outputItem = inventory.get(1);
                                ItemStack outputRecipeResource = activeRecipe.getOutputResource();

                                if (outputItem.isEmpty()) {
                                    outputItem = outputRecipeResource.copy();
                                } else {
                                    outputItem.grow(outputRecipeResource.getCount());
                                }

                                inventory.set(1, outputItem);
                                reset();
                            }
                        } else {
                            reset();
                        }
                    }
                } else {
                    reset();
                }
            }
        }
    }

    private void reset() {
        activeRecipe = null;
        totalTime = 0;
        processTime = 0;
        energyPerTick = 0;
        fluidPerTick = 0;
    }

    public int getTotalTime() {
        return this.totalTime;
    }

    public int getProcessTime() {
        return this.processTime;
    }

    public int getFluidAmount() {
        return this.tank.getFluidAmount();
    }

    public int getFluidCapacity() {
        return this.tank.getCapacity();
    }

    public int getEnergyAmount() {
        return this.energyStorage.getEnergyStored();
    }

    public int getEnergyCapacity() {
        return this.energyStorage.getMaxEnergyStored();
    }

    public CustomFluidTank getTank() {
        return this.tank;
    }

    public CustomEnergyStorage getEnergyStorage() {
        return this.energyStorage;
    }

    public String getInventoryName() {
        return "tile.factory.inventoryName";
    }

}
