package com.mrkekman04.bloodybranch.init;

import com.mrkekman04.bloodybranch.reference.Reference;
import com.mrkekman04.bloodybranch.tile.*;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class InitTile {

    public static void init() {
        GameRegistry.registerTileEntity(TileBloodFactory.class, new ResourceLocation(Reference.MOD_ID, "tile_blood_factory"));
        GameRegistry.registerTileEntity(TileBloodyFountain.class, new ResourceLocation(Reference.MOD_ID, "tile_bloody_fountain"));
        GameRegistry.registerTileEntity(TileAdvancedBloodyFountain.class, new ResourceLocation(Reference.MOD_ID, "tile_advanced_bloody_fountain"));
        GameRegistry.registerTileEntity(TileBloodyAltar.class, new ResourceLocation(Reference.MOD_ID, "tile_bloody_altar"));
        GameRegistry.registerTileEntity(TileAdvancedBloodyAltar.class, new ResourceLocation(Reference.MOD_ID, "tile_advanced_bloody_altar"));
    }
}
