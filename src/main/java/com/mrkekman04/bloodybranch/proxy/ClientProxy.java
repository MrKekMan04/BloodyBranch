package com.mrkekman04.bloodybranch.proxy;

import com.mrkekman04.bloodybranch.client.render.RenderAdvancedBloodyAltar;
import com.mrkekman04.bloodybranch.client.render.RenderBloodyAltar;
import com.mrkekman04.bloodybranch.tile.TileAdvancedBloodyAltar;
import com.mrkekman04.bloodybranch.tile.TileBloodyAltar;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends Proxy{

    public void preInit(FMLPreInitializationEvent event) {

    }

    public void init(FMLInitializationEvent event) {
        ClientRegistry.bindTileEntitySpecialRenderer(TileBloodyAltar.class, new RenderBloodyAltar());
        ClientRegistry.bindTileEntitySpecialRenderer(TileAdvancedBloodyAltar.class, new RenderAdvancedBloodyAltar());
    }

    public void postInit(FMLPostInitializationEvent event){

    }

    public void registerItemRenderer(Item item, int meta, String id)
    {
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), id));
    }
}
