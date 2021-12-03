package com.mrkekman04.bloodybranch.integration.crafttweaker;

import com.blamejared.mtlib.utils.BaseAction;
import com.mrkekman04.bloodybranch.recipes.factory.RecipesFactoryHandler;
import crafttweaker.annotations.ModOnly;
import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.item.IItemStack;
import net.minecraft.item.ItemStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import static com.blamejared.mtlib.helpers.InputHelper.toStack;

@ZenClass("mods.bloodybranch.blood_factory")
@ModOnly("bloodybranch")
@ZenRegister
public class BloodFactory {
    public static final String NAME = "blood_factory";

    @ZenMethod
    public static void addRecipe(IItemStack input, IItemStack outputResource, int time, int energyPerTick, int fluidPerTick) {
        CTHandler.LATE_ADDITIONS.add(new Add(toStack(input), toStack(outputResource), time, energyPerTick, fluidPerTick));
    }

    @ZenMethod
    public static void removeRecipe(IItemStack input) {
        CTHandler.LATE_REMOVALS.add(new Remove(toStack(input)));
    }

    private static class Add extends BaseAction {
        ItemStack input, output;
        int time, energyPerTick, fluidPerTick;

        protected Add(ItemStack input, ItemStack output, int time, int energyPerTick, int fluidPerTick) {
            super(NAME);
            this.input = input;
            this.output = output;
            this.time = time;
            this.energyPerTick = energyPerTick;
            this.fluidPerTick = fluidPerTick;
        }

        @Override
        public void apply() {
            RecipesFactoryHandler.addRecipe(input, output, time, energyPerTick, fluidPerTick);
        }
    }

    private static class Remove extends BaseAction {
        ItemStack input;

        protected Remove(ItemStack input) {
            super(NAME);
            this.input = input;
        }

        @Override
        public void apply() {
            RecipesFactoryHandler.removeRecipe(input);
        }
    }
}
