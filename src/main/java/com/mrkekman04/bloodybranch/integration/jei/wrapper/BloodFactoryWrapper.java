package com.mrkekman04.bloodybranch.integration.jei.wrapper;

import com.mrkekman04.bloodybranch.items.contract.ItemContract;
import com.mrkekman04.bloodybranch.recipes.factory.RecipesFactory;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BloodFactoryWrapper implements IRecipeWrapper {

    private final RecipesFactory recipe;

    public BloodFactoryWrapper(RecipesFactory recipe) {
        this.recipe = recipe;
    }

    @Override
    public void getIngredients(IIngredients ingredients) {
        ingredients.setInputs(VanillaTypes.ITEM, recipe.getInputs());
        ingredients.setOutputs(VanillaTypes.ITEM, recipe.getOutputs());
    }

    @Override
    public void drawInfo(Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {
        if (recipe != null) {
            minecraft.fontRenderer.drawString(I18n.format("tile.factory.inventoryName"), 148 - (minecraft.fontRenderer.getStringWidth(I18n.format("tile.factory.inventoryName"))) - 8, 6, 16777215, true);
            if (recipe.getInput().getItem() instanceof ItemContract) {
                minecraft.fontRenderer.drawString(I18n.format("tile.factory.itemNotConsumed"), 152 - (minecraft.fontRenderer.getStringWidth(I18n.format("tile.factory.itemNotConsumed"))) - 8, 56, 12557851, true);
            }
        }
    }

    @Override
    public List<String> getTooltipStrings(int mouseX, int mouseY) {
        if (mouseX > 5 && mouseY > 5 && mouseX < 22 && mouseY < 64) {
            ArrayList<String> list = new ArrayList<>();
            list.add(I18n.format("category.blood_factory.tip2", recipe.getTime() * recipe.getEnergyPerTick()));
            return list;
        } else if (mouseX > 80 && mouseY > 28 && mouseX < 103 && mouseY < 43) {
            ArrayList<String> list = new ArrayList<>();
            list.add(I18n.format("category.blood_factory.tip1", recipe.getTime()));
            return list;
        }
        return Collections.emptyList();
    }

    @Override
    public boolean handleClick(Minecraft minecraft, int mouseX, int mouseY, int mouseButton) {
        return IRecipeWrapper.super.handleClick(minecraft, mouseX, mouseY, mouseButton);
    }

    public RecipesFactory getRecipe() {
        return recipe;
    }
}
