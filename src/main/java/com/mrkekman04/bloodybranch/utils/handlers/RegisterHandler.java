package com.mrkekman04.bloodybranch.utils.handlers;

import com.mrkekman04.bloodybranch.init.InitItems;
import com.mrkekman04.bloodybranch.lib.*;
import com.mrkekman04.bloodybranch.main.Main;
import com.mrkekman04.bloodybranch.utils.interfaces.IHasModel;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class RegisterHandler {
    @SubscribeEvent
    public static void onItemRegister(RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(InitItems.ITEMS.toArray(new Item[0]));


    }

    @SubscribeEvent
    public static void onModelRegister(ModelRegistryEvent event) {
        for (Item item : InitItems.ITEMS) {
            if (! (item instanceof IHasModel)) {
                Main.proxy.registerItemRenderer(item, 0, "inventory");
            }
        }

        for (EnumSpawnStone type : EnumSpawnStone.values()) {
            ModelLoader.setCustomModelResourceLocation(InitItems.SPAWN_STONE, type.ordinal(), new ModelResourceLocation(InitItems.SPAWN_STONE.getRegistryName() + "_" + type.toString().toLowerCase(), "inventory"));
        } // register spawn stones


        for (EnumMatrix type : EnumMatrix.values()) {
            ModelLoader.setCustomModelResourceLocation(InitItems.MATRIX, type.ordinal(), new ModelResourceLocation(InitItems.MATRIX.getRegistryName() + "_" + type.toString().toLowerCase(), "inventory"));
        } // register matrix

        for (EnumElement type : EnumElement.values()) {
            ModelLoader.setCustomModelResourceLocation(InitItems.ELEMENT, type.ordinal(), new ModelResourceLocation(InitItems.ELEMENT.getRegistryName() + "_of_" + type.toString().toLowerCase(), "inventory"));
        } // register elements

        for (EnumPaperSheets type : EnumPaperSheets.values()) {
            ModelLoader.setCustomModelResourceLocation(InitItems.PAPER_SHEETS, type.ordinal(), new ModelResourceLocation(InitItems.PAPER_SHEETS.getRegistryName() + "_" + type.toString().toLowerCase(), "inventory"));
        } // register paper sheets

        for (EnumRune type : EnumRune.values()) {
            ModelLoader.setCustomModelResourceLocation(InitItems.RUNE, type.ordinal(), new ModelResourceLocation(InitItems.RUNE.getRegistryName() + "_of_" + type.toString().toLowerCase(), "inventory"));
        } // register runes
    }
}
