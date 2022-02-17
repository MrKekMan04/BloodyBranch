package com.mrkekman04.bloodybranch.recipes.factory;

import com.mrkekman04.bloodybranch.init.InitItems;
import com.mrkekman04.bloodybranch.utils.itemStackUtil.ItemStackHashingStrategy;
import gnu.trove.map.hash.TCustomHashMap;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.HashSet;

public class RecipesFactoryHandler {

    public static final TCustomHashMap<ItemStack, RecipesFactory> RECIPES = new TCustomHashMap<>(new ItemStackHashingStrategy());
    public static final ArrayList<RecipesFactory> ALL_RECIPES = new ArrayList<>();
    public static final HashSet<ItemStack> ITEMS_VALID = new HashSet<>();

    public static int[] CONTRACT_IRON_PARAMETER = new int[3];
    public static int[] CONTRACT_GOLD_PARAMETER = new int[3];
    public static int[] CONTRACT_COPPER_PARAMETER = new int[3];
    public static int[] CONTRACT_TIN_PARAMETER = new int[3];
    public static int[] CONTRACT_LEAD_PARAMETER = new int[3];
    public static int[] CONTRACT_NICKEL_PARAMETER = new int[3];
    public static int[] CONTRACT_SILVER_PARAMETER = new int[3];
    public static int[] CONTRACT_ALUMINIUM_PARAMETER = new int[3];
    public static int[] CONTRACT_PLATINUM_PARAMETER = new int[3];

    public static void initRecipes() {
        RECIPES.clear();
        addRecipe(new ItemStack(InitItems.CONTRACT_IRON), new ItemStack(InitItems.MATRIX, 1, 1), CONTRACT_IRON_PARAMETER[0], CONTRACT_IRON_PARAMETER[1], CONTRACT_IRON_PARAMETER[2], false);
        addRecipe(new ItemStack(InitItems.CONTRACT_GOLD), new ItemStack(InitItems.MATRIX, 1, 2), CONTRACT_GOLD_PARAMETER[0], CONTRACT_GOLD_PARAMETER[1], CONTRACT_GOLD_PARAMETER[2], false);
        addRecipe(new ItemStack(InitItems.CONTRACT_COPPER), new ItemStack(InitItems.MATRIX, 1, 3), CONTRACT_COPPER_PARAMETER[0], CONTRACT_COPPER_PARAMETER[1], CONTRACT_COPPER_PARAMETER[2], false);
        addRecipe(new ItemStack(InitItems.CONTRACT_TIN), new ItemStack(InitItems.MATRIX, 1, 4), CONTRACT_TIN_PARAMETER[0], CONTRACT_TIN_PARAMETER[1], CONTRACT_TIN_PARAMETER[2], false);
        addRecipe(new ItemStack(InitItems.CONTRACT_LEAD), new ItemStack(InitItems.MATRIX, 1, 5), CONTRACT_LEAD_PARAMETER[0], CONTRACT_LEAD_PARAMETER[1], CONTRACT_LEAD_PARAMETER[2], false);
        addRecipe(new ItemStack(InitItems.CONTRACT_NICKEL), new ItemStack(InitItems.MATRIX, 1, 6), CONTRACT_NICKEL_PARAMETER[0], CONTRACT_NICKEL_PARAMETER[1], CONTRACT_NICKEL_PARAMETER[2], false);
        addRecipe(new ItemStack(InitItems.CONTRACT_SILVER), new ItemStack(InitItems.MATRIX, 1, 7), CONTRACT_SILVER_PARAMETER[0], CONTRACT_SILVER_PARAMETER[1], CONTRACT_SILVER_PARAMETER[2], false);
        addRecipe(new ItemStack(InitItems.CONTRACT_ALUMINIUM), new ItemStack(InitItems.MATRIX, 1, 8), CONTRACT_ALUMINIUM_PARAMETER[0], CONTRACT_ALUMINIUM_PARAMETER[1], CONTRACT_ALUMINIUM_PARAMETER[2], false);
        addRecipe(new ItemStack(InitItems.CONTRACT_PLATINUM), new ItemStack(InitItems.MATRIX, 1, 9), CONTRACT_PLATINUM_PARAMETER[0], CONTRACT_PLATINUM_PARAMETER[1], CONTRACT_PLATINUM_PARAMETER[2], false);
    }

    public static RecipesFactory getRecipe(ItemStack input) {
        return RECIPES.get(input);
    }

    public static void addRecipe(ItemStack input, ItemStack outputResource, int time, int energyPerTick, int fluidPerTick, boolean isConsume) {
        if (RECIPES.containsKey(input)) {
            throw new IllegalArgumentException("Duplicate recipe tried to register");
        }
        if (input.isEmpty() || outputResource.isEmpty()) {
            throw new IllegalArgumentException("Incoming item is empty");
        }
        RecipesFactory recipesFactory = new RecipesFactory(input, outputResource, time, energyPerTick, fluidPerTick, isConsume);
        RECIPES.put(input, recipesFactory);
        ALL_RECIPES.add(recipesFactory);
    }

    public static void removeRecipe(ItemStack input) {
        if (input.isEmpty()) {
            throw new IllegalArgumentException("Incoming item is empty");
        }
        RecipesFactory remove = RECIPES.remove(input);
        ALL_RECIPES.remove(remove);
    }

    public static ItemStack[] getValidItem() {
        HashSet<ItemStack> ITEMS_VALID = new HashSet<>();
        for (RecipesFactory recipe : RECIPES.values()) {
            ItemStack input = recipe.getInput().copy();
            input.setCount(1);
            ITEMS_VALID.add(input);
        }
        return ITEMS_VALID.toArray(new ItemStack[0]);
    }
}
