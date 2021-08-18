package com.mrkekman04.bloodybranch.items;

import com.mrkekman04.bloodybranch.init.InitItems;
import com.mrkekman04.bloodybranch.lib.EnumMatrix;
import com.mrkekman04.bloodybranch.lib.EnumPaperSheets;
import com.mrkekman04.bloodybranch.main.Main;
import com.mrkekman04.bloodybranch.reference.Reference;
import com.mrkekman04.bloodybranch.utils.interfaces.IHasModel;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class ItemPaperSheets extends Item {

    public ItemPaperSheets(String name){

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
        return this.getUnlocalizedName()  + "_" +  EnumPaperSheets.values()[stack.getItemDamage()].name().toLowerCase();
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        if (tab == Main.BLOODY_BRANCH) {
            for (EnumPaperSheets type : EnumPaperSheets.values()) {
                items.add(new ItemStack(this, 1, type.ordinal()));
            }
        }
    }
}
