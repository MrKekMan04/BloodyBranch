package com.mrkekman04.bloodybranch.integration.jei.category;

import WayofTime.bloodmagic.block.BlockLifeEssence;
import com.mrkekman04.bloodybranch.integration.jei.wrapper.BloodFactoryWrapper;
import com.mrkekman04.bloodybranch.recipes.factory.RecipesFactory;
import com.mrkekman04.bloodybranch.reference.Reference;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.*;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;

public class BloodFactoryCategory implements IRecipeCategory<BloodFactoryWrapper> {

    public static final String UID = Reference.MOD_ID + ":BloodFactory";
    private final IDrawableStatic bg;

    private IDrawableAnimated progress;
    private IDrawableAnimated energyBar;

    RecipesFactory recipesFactory;

    public BloodFactoryCategory(IGuiHelper h) {
        animation(h);
        bg = h.createDrawable(new ResourceLocation(Reference.MOD_ID, "textures/gui/jei/jei_blood_factory.png"), 0, 0, 148, 70);
    }

    @Override
    public String getUid() {
        return UID;
    }

    @Override
    public String getTitle() {
        return I18n.format("category.blood_factory.name");
    }

    @Override
    public String getModName() {
        return Reference.MOD_NAME;
    }

    @Override
    public IDrawable getBackground() {
        return bg;
    }

    @Override
    public void drawExtras(Minecraft minecraft) {
        progress.draw(minecraft, 80, 28);
        energyBar.draw(minecraft, 6, 6);
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, BloodFactoryWrapper recipe, IIngredients ingredients) {
        recipesFactory = recipe.getRecipe();
        IGuiItemStackGroup isg = recipeLayout.getItemStacks();
        isg.init(0, true, 55, 27);
        isg.set(0, recipe.getRecipe().getInput());
        isg.init(1, false, 109, 27);
        isg.set(1, recipe.getRecipe().getOutputResource());
        IGuiFluidStackGroup fluidStacks = recipeLayout.getFluidStacks();
        fluidStacks.init(2, false, 24, 6, 16, 58, 10000, false, null);
        fluidStacks.set(2, new FluidStack(BlockLifeEssence.getLifeEssence(), recipe.getRecipe().getFluidPerTick() * recipe.getRecipe().getTime()));
    }

    protected void animation(IGuiHelper handler) {
        progress = handler.drawableBuilder(new ResourceLocation(Reference.MOD_ID, "textures/gui/jei/jei_blood_factory.png"), 180, 0, 22, 16).buildAnimated(40, IDrawableAnimated.StartDirection.LEFT, false);
        energyBar = handler.drawableBuilder(new ResourceLocation(Reference.MOD_ID, "textures/gui/jei/jei_blood_factory.png"), 164, 0, 16, 58).buildAnimated(200, IDrawableAnimated.StartDirection.TOP, true);
    }
}
