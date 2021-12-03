package com.mrkekman04.bloodybranch.config;

import com.mrkekman04.bloodybranch.recipes.factory.RecipesFactoryHandler;
import com.mrkekman04.bloodybranch.reference.Reference;
import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class ConfigHandler {

    public static final String BLOOD_FACTORY_CATEGORY = "blood_factory";


    public static void load(File file) {
        Configuration configuration = new Configuration(file, Reference.VERSION);
        configuration.load();

        // BLOOD_FACTORY RECIPES
        RecipesFactoryHandler.CONTRACT_IRON_PARAMETER = configuration.get(BLOOD_FACTORY_CATEGORY, "CONTRACT_IRON_PARAMETER", new int[]{40, 20, 20}, "Parameter for Iron Contract: time, energyPerTick, fluidPerTick").getIntList();
        RecipesFactoryHandler.CONTRACT_GOLD_PARAMETER = configuration.get(BLOOD_FACTORY_CATEGORY, "CONTRACT_GOLD_PARAMETER", new int[]{40, 20, 20}, "Parameter for Gold Contract: time, energyPerTick, fluidPerTick").getIntList();
        RecipesFactoryHandler.CONTRACT_COPPER_PARAMETER = configuration.get(BLOOD_FACTORY_CATEGORY, "CONTRACT_COPPER_PARAMETER", new int[]{40, 20, 20}, "Parameter for Copper Contract: time, energyPerTick, fluidPerTick").getIntList();
        RecipesFactoryHandler.CONTRACT_TIN_PARAMETER = configuration.get(BLOOD_FACTORY_CATEGORY, "CONTRACT_TIN_PARAMETER", new int[]{40, 20, 20}, "Parameter for Tin Contract: time, energyPerTick, fluidPerTick").getIntList();
        RecipesFactoryHandler.CONTRACT_LEAD_PARAMETER = configuration.get(BLOOD_FACTORY_CATEGORY, "CONTRACT_LEAD_PARAMETER", new int[]{40, 20, 20}, "Parameter for Lead Contract: time, energyPerTick, fluidPerTick").getIntList();
        RecipesFactoryHandler.CONTRACT_NICKEL_PARAMETER = configuration.get(BLOOD_FACTORY_CATEGORY, "CONTRACT_NICKEL_PARAMETER", new int[]{40, 20, 20}, "Parameter for Nickel Contract: time, energyPerTick, fluidPerTick").getIntList();
        RecipesFactoryHandler.CONTRACT_SILVER_PARAMETER = configuration.get(BLOOD_FACTORY_CATEGORY, "CONTRACT_SILVER_PARAMETER", new int[]{40, 20, 20}, "Parameter for Silver Contract: time, energyPerTick, fluidPerTick").getIntList();
        RecipesFactoryHandler.CONTRACT_ALUMINIUM_PARAMETER = configuration.get(BLOOD_FACTORY_CATEGORY, "CONTRACT_ALUMINIUM_PARAMETER", new int[]{40, 20, 20}, "Parameter for Aluminium Contract: time, energyPerTick, fluidPerTick").getIntList();
        RecipesFactoryHandler.CONTRACT_PLATINUM_PARAMETER = configuration.get( BLOOD_FACTORY_CATEGORY, "CONTRACT_PLATINUM_PARAMETER", new int[]{40, 20, 20}, "Parameter for Platinum Contract: time, energyPerTick, fluidPerTick").getIntList();

        if (configuration.hasChanged()) {
            configuration.save();
        }
    }

}
