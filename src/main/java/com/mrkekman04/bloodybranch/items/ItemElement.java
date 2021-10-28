package com.mrkekman04.bloodybranch.items;

import com.mrkekman04.bloodybranch.init.InitItems;
import com.mrkekman04.bloodybranch.lib.EnumElement;
import com.mrkekman04.bloodybranch.main.Main;
import com.mrkekman04.bloodybranch.reference.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;

public class ItemElement extends Item {

    public ItemElement (String name) {
        ResourceLocation RS = new ResourceLocation(Reference.MOD_ID, name);
        setMaxDamage(0);
        setHasSubtypes(true);
        setCreativeTab(Main.BLOODY_BRANCH);
        setRegistryName(RS);
        setUnlocalizedName(name);
        InitItems.ITEMS.add(this);
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return this.getUnlocalizedName()  + "_of_" +  EnumElement.values()[stack.getItemDamage()].name().toLowerCase();
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        if (isInCreativeTab(tab)) {
            if (tab == Main.BLOODY_BRANCH) {
                for (EnumElement type : EnumElement.values()) {
                    items.add(new ItemStack(this, 1, type.ordinal()));
                }
            }
        }
    }
}


