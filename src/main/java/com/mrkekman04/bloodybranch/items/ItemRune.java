package com.mrkekman04.bloodybranch.items;

import com.mrkekman04.bloodybranch.lib.EnumRune;
import com.mrkekman04.bloodybranch.main.Main;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class ItemRune extends BaseItemSubtypes {

    public ItemRune(String name) {
        super(name);
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return this.getUnlocalizedName() + "_of_" + EnumRune.values()[stack.getItemDamage()].name().toLowerCase();
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        if (isInCreativeTab(tab)) {
            for (EnumRune type : EnumRune.values()) {
                items.add(new ItemStack(this, 1, type.ordinal()));
            }
        }
    }
}
