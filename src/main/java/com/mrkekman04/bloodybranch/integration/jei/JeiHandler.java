package com.mrkekman04.bloodybranch.integration.jei;

import com.mrkekman04.bloodybranch.gui.GuiBloodFactory;
import com.mrkekman04.bloodybranch.init.InitBlocks;
import com.mrkekman04.bloodybranch.integration.jei.category.BloodFactoryCategory;
import com.mrkekman04.bloodybranch.integration.jei.wrapper.BloodFactoryWrapper;
import com.mrkekman04.bloodybranch.recipes.factory.RecipesFactory;
import com.mrkekman04.bloodybranch.recipes.factory.RecipesFactoryHandler;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import net.minecraft.item.ItemStack;

@JEIPlugin
public class JeiHandler implements IModPlugin {

    @Override
    public void registerCategories(IRecipeCategoryRegistration registry) {
        IGuiHelper guiHelper = registry.getJeiHelpers().getGuiHelper();
        registry.addRecipeCategories(
                new BloodFactoryCategory(guiHelper)
        );
    }

    @Override
    public void register(IModRegistry registry) {
        registry.addRecipes(RecipesFactoryHandler.ALL_RECIPES, BloodFactoryCategory.UID); // Регистрация рецептов для Вашего UID.

        registry.handleRecipes(RecipesFactory.class, BloodFactoryWrapper::new, BloodFactoryCategory.UID); // Регистрация рецептов из листа рецептов.

        registry.addRecipeCatalyst(new ItemStack(InitBlocks.BLOOD_FACTORY), BloodFactoryCategory.UID); // Регистрация показывающихся блоков для Вашего UID.

        registry.addRecipeClickArea(GuiBloodFactory.class, 95, 36, 22, 16, BloodFactoryCategory.UID);
    }


}
