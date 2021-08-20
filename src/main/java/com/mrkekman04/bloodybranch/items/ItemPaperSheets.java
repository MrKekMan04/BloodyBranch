package com.mrkekman04.bloodybranch.items;

import com.mrkekman04.bloodybranch.lib.EnumPaperSheets;
import com.mrkekman04.bloodybranch.main.Main;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class ItemPaperSheets extends BaseItemSubtypes {

    public ItemPaperSheets(String name) {
        super(name);
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return this.getUnlocalizedName() + "_" + EnumPaperSheets.values()[stack.getItemDamage()].name().toLowerCase();
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        if (isInCreativeTab(tab)) {
            if (tab == Main.BLOODY_BRANCH) {
                for (EnumPaperSheets type : EnumPaperSheets.values()) {
                    items.add(new ItemStack(this, 1, type.ordinal()));
                }
            }
        }
    }
}
