package com.mrkekman04.bloodybranch.items.contract;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class ItemContractPlatinum extends ItemContract {

    public ItemContractPlatinum(String name) {
        super(name, 15, true);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(I18n.format("desc.platinum_contract1"));
        tooltip.add(I18n.format("desc.platinum_contract2"));
    }

    @Override
    public Item setMaxDamage(int maxDamageIn) {
        return super.setMaxDamage(15);
    }
}
