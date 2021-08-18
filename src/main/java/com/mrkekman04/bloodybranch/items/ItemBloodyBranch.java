package com.mrkekman04.bloodybranch.items;

import com.mrkekman04.bloodybranch.init.InitItems;
import com.mrkekman04.bloodybranch.main.Main;
import com.mrkekman04.bloodybranch.utils.interfaces.IHasModel;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemBloodyBranch extends Item implements IHasModel {
    public ItemBloodyBranch(String name) {
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(Main.BLOODY_BRANCH);
        setMaxStackSize(1);

        InitItems.ITEMS.add(this);
    }

    @Override
    public void registerModels() {
        Main.proxy.registerItemRenderer(this, 0, "inventory");
    }


}
