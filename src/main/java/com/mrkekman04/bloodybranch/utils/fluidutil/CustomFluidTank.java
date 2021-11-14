package com.mrkekman04.bloodybranch.utils.fluidutil;

import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;

import javax.annotation.Nullable;

public class CustomFluidTank extends FluidTank {

    protected FluidStack requiredLiquid;

    public CustomFluidTank(int capacity) {
        super(capacity);
    }

    public CustomFluidTank(int capacity, String requiredLiquid) {
        super(capacity);
        this.requiredLiquid = new FluidStack(FluidRegistry.getFluid(requiredLiquid), 0);
    }

    public CustomFluidTank(@Nullable FluidStack fluidStack, int capacity) {
        super(fluidStack, capacity);
        requiredLiquid = fluidStack;
    }

    public CustomFluidTank(Fluid fluid, int amount, int capacity) {
        super(fluid, amount, capacity);
        requiredLiquid = new FluidStack(fluid, 0);
    }

    @Override
    public int fill(FluidStack resource, boolean doFill) {
        if (resource.isFluidEqual(requiredLiquid))
            return super.fill(resource, doFill);
        return 0;
    }

    @Nullable
    @Override
    public FluidStack getFluid() {
        return super.getFluid();
    }

    @Override
    public void setFluid(@Nullable FluidStack fluid) {
        super.setFluid(fluid);
    }

    @Override
    public int getCapacity() {
        return super.getCapacity();
    }

    @Override
    public FluidStack drain(int maxDrain, boolean doDrain) {
        return super.drain(maxDrain, doDrain);
    }



    public FluidStack getRequiredLiquid() {
        return requiredLiquid;
    }

    public void setFluidAmount(int amount) {
        if (fluid != null)
            super.fluid.amount = Math.max(0, Math.min(amount, this.getCapacity()));
    }
}
