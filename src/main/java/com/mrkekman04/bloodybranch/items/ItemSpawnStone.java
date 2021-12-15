package com.mrkekman04.bloodybranch.items;

import com.mrkekman04.bloodybranch.lib.EnumSpawnStone;
import com.mrkekman04.bloodybranch.main.Main;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;


public class ItemSpawnStone extends BaseItemSubtypes {

    public ItemSpawnStone(String name) {
        super(name);
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return this.getUnlocalizedName() + "_" + EnumSpawnStone.values()[stack.getItemDamage()].name().toLowerCase();
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        if (isInCreativeTab(tab)) {
            for (EnumSpawnStone type : EnumSpawnStone.values()) {
                items.add(new ItemStack(this, 1, type.ordinal()));
            }
        }
    }


    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(I18n.format("desc." + "spawnstone_" + EnumSpawnStone.values()[stack.getItemDamage()].name().toLowerCase()));
    }
}
