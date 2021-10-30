package com.mrkekman04.bloodybranch.init;

import com.mrkekman04.bloodybranch.reference.Reference;
import com.mrkekman04.bloodybranch.tile.TileEntityFactory;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class InitTile {

    public static void init() {

        // GameRegistry.registerTileEntity(TILE_ENTITY_CLASS.class, new ResourceLocation(Reference.MOD_ID, "TILE_NAME"));
        GameRegistry.registerTileEntity(TileEntityFactory.class, new ResourceLocation(Reference.MOD_ID, "tile_factory"));

    }
}
