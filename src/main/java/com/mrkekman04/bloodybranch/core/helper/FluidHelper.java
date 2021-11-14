package com.mrkekman04.bloodybranch.core.helper;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;
import net.minecraftforge.fluids.capability.IFluidTankProperties;

public class FluidHelper {

    @CapabilityInject(IFluidHandlerItem.class)
    public static final Capability<IFluidHandler> FLUID_HANDLER_ITEM = null;

    public static boolean isFluidInStack(ItemStack stack) {
        return !stack.isEmpty() && stack.hasCapability(FLUID_HANDLER_ITEM, null);
    }

    public static FluidStack getFluidFromStack(ItemStack container) {
        if (isFluidInStack(container)) {
            IFluidTankProperties[] tank = container.getCapability(FLUID_HANDLER_ITEM, null).getTankProperties();
            return tank.length <= 0 ? null : tank[0].getContents();
        }
        return null;
    }

    public static FluidStack getFluidForFilledItem(ItemStack container) {
        if (container != null && isFluidInStack(container)) {
            return getFluidFromStack(container);
        }
        return null;
    }

    public static boolean isBucket(ItemStack stack) {
        return stack.getItem() == Items.BUCKET;
    }

    public static boolean isFluidEqual(FluidStack fluid, String name) {
        if (fluid != null) {
            return fluid.getFluid().getName().equals(name);
        }
        return false;
    }
}

