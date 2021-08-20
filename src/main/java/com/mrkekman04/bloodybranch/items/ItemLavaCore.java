//package com.mrkekman04.bloodybranch.items;
//
//import com.mrkekman04.bloodybranch.init.InitItems;
//import com.mrkekman04.bloodybranch.main.Main;
//import com.mrkekman04.bloodybranch.utils.interfaces.IHasModel;
//import net.minecraft.item.Item;
//
//public class ItemLavaCore extends Item implements IHasModel {
//
//    public ItemLavaCore(String name){
//        setUnlocalizedName(name);
//        setRegistryName(name);
//        setCreativeTab(Main.BLOODY_BRANCH);
//
//        InitItems.ITEMS.add(this);
//    }
//
//    @Override
//    public void registerModels() {
//        Main.proxy.registerItemRenderer(this, 0, "inventory");
//    }
//}
