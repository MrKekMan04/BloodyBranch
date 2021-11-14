package com.mrkekman04.bloodybranch.recipes.factory;


import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class RecipesFactory {

    private final ItemStack input;
    private final ItemStack outputResource;
    private final int time;
    private final int energyPerTick;
    private final int fluidPerTick;

    public RecipesFactory(ItemStack input1, ItemStack outputResource, int time, int energyPerTick, int fluidPerTick) {
        this.input = input1;
        this.outputResource = outputResource;
        this.time = time;
        this.energyPerTick = energyPerTick;
        this.fluidPerTick = fluidPerTick;
    }


    public ItemStack getInput() {
        return input;
    }

    public ItemStack getOutputResource() {
        return outputResource;
    }

    public int getTime() {
        return time;
    }

    public int getEnergyPerTick() {
        return energyPerTick;
    }

    public int getFluidPerTick() {
        return fluidPerTick;
    }

    public List<ItemStack> getOutputs() {
        ArrayList<ItemStack> list = new ArrayList<>();
        list.add(outputResource);
        return list;
    }

    public List<ItemStack> getInputs() {
        ArrayList<ItemStack> list = new ArrayList<>();
        list.add(input);
        return list;
    }
}
