package com.mrkekman04.bloodybranch.tile;

import com.mrkekman04.bloodybranch.blocks.BlockFactory;
import com.mrkekman04.bloodybranch.items.ItemMatrix;
import com.mrkekman04.bloodybranch.core.helper.RecipeHelper;
import com.mrkekman04.bloodybranch.recipes.factory.RecipesFactory;
import com.mrkekman04.bloodybranch.recipes.factory.RecipesFactoryHandler;
import com.mrkekman04.bloodybranch.utils.handlers.CustomItemStackHandler;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.EnergyStorage;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidTank;

import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.CombinedInvWrapper;


import javax.annotation.Nullable;
import java.util.ArrayList;

public class TileEntityFactory extends TileEntity implements ITickable {

    @CapabilityInject(IItemHandler.class)
    protected static Capability<IItemHandler> ITEM_HANDLER_CAPABILITY = null;

    private CombinedInvWrapper inventory;
    private final ArrayList<CustomItemStackHandler> inventories = new ArrayList<>();

    private final CustomItemStackHandler inventoryInput = new CustomItemStackHandler(1);
    private final CustomItemStackHandler inventoryOutput = new CustomItemStackHandler(1);


    FluidTank tank = new FluidTank(10 * Fluid.BUCKET_VOLUME);


    EnergyStorage energyStorage = new EnergyStorage(10000, 100, 100);


    protected int workingTime = 0; // total
    protected int processTime = 0; // now time \ burnTime

    protected ItemStack workItem = ItemStack.EMPTY; // outputItem

    protected RecipesFactory activeRecipe;


    protected int energyPerTick = 0;
    protected int fluidPerTick = 0;


    public TileEntityFactory() {
        this.initInventory(inventoryInput, inventoryOutput);

    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        for (int i = 0; i < inventories.size(); i++) {
            compound.setTag("inventory" + i, inventories.get(i).serializeNBT());
        }

        compound.setInteger("processTime", this.processTime);
        compound.setInteger("workingTime", this.workingTime);


        compound.setInteger("workingFluid", this.fluidPerTick);
        compound.setInteger("workingEnergy", this.energyPerTick);

        compound.setTag("tank", tank.writeToNBT(new NBTTagCompound()));

        if (energyStorage != null) {
            compound.setTag("energyStorage", CapabilityEnergy.ENERGY.writeNBT(energyStorage, null));
        }

        return compound;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        for (int i = 0; i < inventories.size(); i++) {
            if (compound.hasKey("inventory" + i)) {
                inventories.get(i).deserializeNBT(compound.getCompoundTag("inventory" + i));
            }
        }

        this.processTime = compound.getInteger("processTime");
        this.workingTime = compound.getInteger("workingTime");


        this.fluidPerTick = compound.getInteger("workingFluid");
        this.energyPerTick = compound.getInteger("workingEnergy");

        this.tank.readFromNBT(compound.getCompoundTag("tank"));

        if (compound.hasKey("energyStorage") && energyStorage != null) {
            CapabilityEnergy.ENERGY.readNBT(energyStorage, null, compound.getTag("energyStorage"));
        }
    }

    public void initInventory(CustomItemStackHandler... itemStackHandlers) {
        inventory = new CombinedInvWrapper(itemStackHandlers);
        for (CustomItemStackHandler itemStackHandler : itemStackHandlers) {
            if (!inventories.add(itemStackHandler)) {
                throw new IllegalArgumentException("duplicate inventory in " + TileEntityFactory.class);
            }
        }
    }

    @Override
    public void update() {
        if (!getWorld().isRemote) {

            if (inventory.getStackInSlot(0).isEmpty()) {
                reset();
                world.setBlockState(pos, world.getBlockState(pos).withProperty(BlockFactory.WORKING, false));
            } else {
                if (activeRecipe == null) {
                    RecipesFactory recipe = RecipesFactoryHandler.getRecipe(inventory.getStackInSlot(0));
                    if (recipe != null) {
                        if (energyStorage.getEnergyStored() >= recipe.getEnergyPerTick() * workingTime && tank.getFluidAmount() >= recipe.getFluidPerTick() * workingTime) {
                            activeRecipe = recipe;
                            workItem = recipe.getOutputResource();
                            workingTime = recipe.getTime();
                            energyPerTick = recipe.getEnergyPerTick();
                            fluidPerTick = recipe.getFluidPerTick();
                        }
                    } else {
                        reset();
                    }
                }

                if (activeRecipe == null
                        || workingTime < 0
                        || !RecipeHelper.canMergeItemStack(activeRecipe.getOutputResource(), inventory.getStackInSlot(1))) {
                    world.setBlockState(pos, world.getBlockState(pos).withProperty(BlockFactory.WORKING, false));
                    reset();
                    return;
                }

                if (activeRecipe == RecipesFactoryHandler.getRecipe(inventory.getStackInSlot(0))) {
                    if (RecipeHelper.canMergeItemStack(workItem, inventory.getStackInSlot(1))) {
                        if (RecipesFactoryHandler.getRecipe(inventory.getStackInSlot(0)) != null) {

                            if (world.getBlockState(pos) == world.getBlockState(pos).withProperty(BlockFactory.WORKING, false)) {
                                world.setBlockState(pos, world.getBlockState(pos).withProperty(BlockFactory.WORKING, true));
                            }

                            processTime++;

                            energyStorage.extractEnergy(energyPerTick, false);
                            tank.drain(fluidPerTick, true);

                            if (processTime >= workingTime) {

                                if (!(activeRecipe.getOutputResource().getItem() instanceof ItemMatrix)) {
                                    inventory.getStackInSlot(0).shrink(activeRecipe.getInput().getCount());
                                }

                                ItemStack outputItem = inventory.getStackInSlot(1);
                                ItemStack outputRecipeResource = activeRecipe.getOutputResource();

                                if (outputItem.isEmpty()) {
                                    outputItem = outputRecipeResource.copy();
                                } else {
                                    outputItem.grow(outputRecipeResource.getCount());
                                }

                                inventory.setStackInSlot(1, outputItem);
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
        workingTime = 0;
        processTime = 0;
        energyPerTick = 0;
        fluidPerTick = 0;
        workItem = ItemStack.EMPTY;
        activeRecipe = null;
    }

    public void saveAndSync() {
        IBlockState state = this.world.getBlockState(this.pos);
        this.world.markBlockRangeForRenderUpdate(this.pos, this.pos);
        this.world.notifyBlockUpdate(pos, state, state, 3);
        this.markDirty();
    }

    @Nullable
    @Override
    public SPacketUpdateTileEntity getUpdatePacket() {
        return new SPacketUpdateTileEntity(this.getPos(), 0, this.getUpdateTag());
    }

    @Override
    public NBTTagCompound getUpdateTag() {
        return this.writeToNBT(new NBTTagCompound());
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity packet) {
        this.readFromNBT(packet.getNbtCompound());
    }

    @Override
    public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newSate) {
        return oldState.getBlock() != newSate.getBlock();
    }


    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
        if (capability == CapabilityEnergy.ENERGY) {
            return true;
        }

        if (capability == ITEM_HANDLER_CAPABILITY) {
            return true;
        }

        return super.hasCapability(capability, facing);
    }


//    private CustomInventoryHandler invHandler = new CustomInventoryHandler (inventory, 2, 0,
//            new boolean[]{true, false},
//            new boolean[]{false,true});

    @Nullable
    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {

        if (CapabilityEnergy.ENERGY.equals(capability) && energyStorage != null) {
            return CapabilityEnergy.ENERGY.cast(energyStorage);
        }

        if (capability == ITEM_HANDLER_CAPABILITY) {
            return (T) inventory;
        }

        return super.getCapability(capability, facing);

    }

    public void updateGraphics(int slot) {
        markDirty();
    }

    public String getInventoryName() {
        return "tile.factory.inventoryName";
    }

    public int getProcessTime() {
        return this.processTime;
    }

    public int getWorkingTime() {
        return this.workingTime;
    }

    public int getCapacityFluid() {
        return this.tank.getCapacity();
    }

    public int getAmountFluid() {
        return this.tank.getFluidAmount();
    }

    public EnergyStorage getEnergyStorage() {
        return this.energyStorage;
    }

    public int getEnergyCapacity() {
        return this.energyStorage.getMaxEnergyStored();
    }

    public int getAmountEnergy() {
        return this.energyStorage.getEnergyStored();
    }

    public FluidTank getTank() {
        return this.tank;
    }

    public CombinedInvWrapper getInventory() {
        return inventory;
    }

    public ArrayList<CustomItemStackHandler> getInventories() {
        return inventories;
    }
}