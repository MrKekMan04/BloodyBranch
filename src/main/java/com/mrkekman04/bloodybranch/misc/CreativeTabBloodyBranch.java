package com.mrkekman04.bloodybranch.misc;

import com.mrkekman04.bloodybranch.init.InitItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class CreativeTabBloodyBranch extends CreativeTabs {
    public CreativeTabBloodyBranch(String label) {
        super(label);
    }

    @Override
    public ItemStack getTabIconItem() {

        return new ItemStack(InitItems.BLOODY_BRANCH);
    }
}
