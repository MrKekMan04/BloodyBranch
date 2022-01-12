package com.mrkekman04.bloodybranch.items.contract;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class ItemContractGold extends ItemContract {

    public ItemContractGold(String name) {
        super(name, 63, true);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(I18n.format("desc.gold_contract1"));
        tooltip.add(I18n.format("desc.gold_contract2", getMaxDamage(stack) + 1 - getDamage(stack)));
    }
}
