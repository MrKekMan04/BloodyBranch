package com.mrkekman04.bloodybranch.items;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class ItemMatrixChard extends BaseItem {
    public ItemMatrixChard(String name) {
        super(name);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(I18n.format("desc.matrix_chard1"));
        tooltip.add(I18n.format("desc.matrix_chard2"));
        tooltip.add(I18n.format("desc.matrix_chard3"));
        tooltip.add(I18n.format("desc.matrix_chard4"));
    }
}
