package com.mrkekman04.bloodybranch.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public abstract class BaseItemSubtypes extends BaseItem {
    public BaseItemSubtypes(String name) {
        super(name);
        setMaxDamage(0);
        setHasSubtypes(true);
    }

    @Override
    public abstract String getUnlocalizedName(ItemStack stack);

    @Override
    public abstract void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items);
}

