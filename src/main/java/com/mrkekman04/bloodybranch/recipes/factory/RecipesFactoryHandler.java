package com.mrkekman04.bloodybranch.recipes.factory;

import com.mrkekman04.bloodybranch.init.InitItems;
import com.mrkekman04.bloodybranch.utils.itemStackUtil.ItemStackHashingStrategy;
import gnu.trove.map.hash.TCustomHashMap;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.HashSet;

public class RecipesFactoryHandler {

    public static final TCustomHashMap<ItemStack, RecipesFactory> RECIPES = new TCustomHashMap<>(new ItemStackHashingStrategy());
    public static final ArrayList<RecipesFactory> ALL_RECIPES = new ArrayList<>();
    public static final HashSet<ItemStack> ITEMS_VALID = new HashSet<>();

    public static void initRecipes() {
        RECIPES.clear();
        addRecipe(new ItemStack(Items.APPLE), new ItemStack(Items.ARROW), 20, 5, 0);
        addRecipe(new ItemStack(Items.BONE), new ItemStack(Items.BLAZE_ROD), 40, 0, 5);
        addRecipe(new ItemStack(InitItems.CONTRACT_IRON), new ItemStack(InitItems.MATRIX, 1, 1), 10, 0, 0);
        addRecipe(new ItemStack(InitItems.CONTRACT_GOLD), new ItemStack(InitItems.MATRIX, 1, 2), 10, 0, 0);
        addRecipe(new ItemStack(InitItems.CONTRACT_COPPER), new ItemStack(InitItems.MATRIX, 1, 3), 10, 0, 0);
        addRecipe(new ItemStack(InitItems.CONTRACT_TIN), new ItemStack(InitItems.MATRIX, 1, 4), 10, 0, 0);
        addRecipe(new ItemStack(InitItems.CONTRACT_LEAD), new ItemStack(InitItems.MATRIX, 1, 5), 10, 0, 0);
        addRecipe(new ItemStack(InitItems.CONTRACT_NICKEL), new ItemStack(InitItems.MATRIX, 1, 6), 10, 0, 0);
        addRecipe(new ItemStack(InitItems.CONTRACT_SILVER), new ItemStack(InitItems.MATRIX, 1, 7), 10, 0, 0);
        addRecipe(new ItemStack(InitItems.CONTRACT_ALUMINIUM), new ItemStack(InitItems.MATRIX, 1, 8), 10, 0, 0);
        addRecipe(new ItemStack(InitItems.CONTRACT_PLATINUM), new ItemStack(InitItems.MATRIX, 1, 9), 10, 0, 0);


    }

    public static RecipesFactory getRecipe(ItemStack input) {
        return RECIPES.get(input);
    }

    public static void addRecipe(ItemStack input, ItemStack outputResource, int time, int energyPerTick, int fluidPerTick) {
        if (RECIPES.containsKey(input)) {
            throw new IllegalArgumentException("Duplicate recipe tried to register");
        }
        if (input.isEmpty() || outputResource.isEmpty()) {
            throw new IllegalArgumentException("Incoming item is empty");
        }
        RecipesFactory recipesFactory = new RecipesFactory(input, outputResource, time, energyPerTick, fluidPerTick);
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
