package com.mrkekman04.bloodybranch.utils.handlers;

import com.mrkekman04.bloodybranch.core.helper.FluidHelper;
import com.mrkekman04.bloodybranch.tile.TileEntityFactory;
import com.mrkekman04.bloodybranch.tile.TileFactory;
import com.mrkekman04.bloodybranch.utils.fluidutil.CustomFluidTank;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidActionResult;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.items.ItemStackHandler;

public class FluidHandler {

    public static boolean isFullTank(FluidTank tank) {
        if (tank.getFluid() == null) return false;
        return tank.getCapacity() <= tank.getFluidAmount();
    }

    public static boolean canFillTank(FluidTank tank, FluidStack fluidStack) {
        return tank.getCapacity() >= tank.fill(fluidStack, false);
    }

    public static boolean canDrainTank(FluidTank tank, int amount) {
        FluidStack drain = tank.drain(amount, false);
        return drain != null && drain.amount >= amount;
    }

    public static boolean fillTank(FluidTank tank, FluidStack fluidStack) {
        if (canFillTank(tank, fluidStack)) {
            tank.fill(fluidStack, true);
            return true;
        }
        return false;
    }

    public static boolean drainTank(FluidTank tank, int amount) {
        if (canDrainTank(tank, amount)) {
            tank.drain(amount, true);
            return true;
        }
        return false;
    }

    public static ItemStack fillFromSlot(CustomFluidTank tank, ItemStack itemStack) {
        if (isFullTank(tank)) return itemStack;
        FluidStack fluidFromStack = FluidHelper.getFluidFromStack(itemStack);
        if (fluidFromStack == null) return itemStack;
        if (canFillTank(tank, fluidFromStack)) {
            FluidActionResult fluidActionResult = FluidUtil.tryEmptyContainer(itemStack, tank, Integer.MAX_VALUE, null, true);
            if (fluidActionResult.isSuccess()) return fluidActionResult.result;
        }
        return itemStack;
    }

    public static boolean fillFromSlot(CustomFluidTank tank, ItemStackHandler inventory, int index) {
        ItemStack itemFluid = FluidHandler.fillFromSlot(tank, inventory.getStackInSlot(index));
        if (itemFluid != inventory.getStackInSlot(index)) {
            inventory.setStackInSlot(index, itemFluid);
            return true;
        }
        return false;
    }

    public static boolean drainFluid(FluidTank tank, int amount) {
        if (isAmountFluid(tank, amount)) {
            if (tank.getFluid() != null) {
                tank.getFluid().amount = tank.getFluidAmount() - amount;
                if (tank.getFluid().amount == 0) {
                    tank.setFluid(null);
                }
                return true;
            }
        }
        return false;
    }

    public static boolean isAmountFluid(FluidTank tank, int amount) {
        return tank.getFluidAmount() >= amount;
    }

    public static void changeTankCapacity(FluidTank tank, int capacity) {
        tank.setCapacity(capacity);
        if (tank.getFluidAmount() > tank.getCapacity()) {
            if (tank.getFluid() != null) {
                tank.getFluid().amount = tank.getCapacity();
            }
        }
    }

    public static boolean bucketInteractWithBlock(EntityPlayer player, EnumHand hand, World world, BlockPos pos, boolean simulate) {
        ItemStack item = player.getHeldItem(hand);
        if (item.hasCapability(FluidHelper.FLUID_HANDLER_ITEM, null)) {
            FluidStack fluidFromStack = FluidHelper.getFluidFromStack(item);
            if (fluidFromStack != null) {
                return fillTankByBucket(player, world, pos, item, fluidFromStack, simulate);
            } else {
                return drainTankByBucket(player, world, pos, item, simulate);
            }
        }
        return false;
    }

    public static boolean fillTankByBucket(EntityPlayer player, World world, BlockPos pos, ItemStack item, FluidStack fluid, boolean simulate) {
        TileEntity tile = world.getTileEntity(pos);
        if (tile instanceof TileFactory) {
            TileFactory tileFactory = (TileFactory) tile;
            CustomFluidTank tank = tileFactory.getTank();

            FluidActionResult fluidActionResult = FluidUtil.tryEmptyContainer(item, tank, Integer.MAX_VALUE, null, !simulate);
            if (!simulate && fluidActionResult.isSuccess()) {
                if (!player.isCreative()) {
                    ItemStack containerItem = fluidActionResult.result;
                    item.shrink(1);
                    if (!player.addItemStackToInventory(containerItem)) {
                        BlockPos position = player.getPosition();
                        world.spawnEntity(new EntityItem(world, position.getX() + 0.5f, position.getY(), position.getZ() + 0.5f, containerItem));
                    }
                }
                if (fluidActionResult.isSuccess())
                    return true;
            }
        }
        return false;
    }

    public static boolean drainTankByBucket(EntityPlayer player, World world, BlockPos pos, ItemStack item, boolean simulate) {
        TileEntity tile = world.getTileEntity(pos);
        if (tile instanceof TileFactory) {
            TileFactory tileFactory = (TileFactory) tile;
            CustomFluidTank tank = tileFactory.getTank();
            FluidActionResult fluidActionResult = FluidUtil.tryFillContainer(item, tank, Integer.MAX_VALUE, player, !simulate);
            if (fluidActionResult == FluidActionResult.FAILURE) return false;
            if (!simulate) {
                if (!player.isCreative()) {
                    item.shrink(1);
                    if (!player.addItemStackToInventory(fluidActionResult.result)) {
                        BlockPos position = player.getPosition();
                        world.spawnEntity(new EntityItem(world, position.getX() + 0.5f, position.getY(), position.getZ() + 0.5f, fluidActionResult.result));
                    }
                }
                return true;
            }
        }
        return false;
    }
}

