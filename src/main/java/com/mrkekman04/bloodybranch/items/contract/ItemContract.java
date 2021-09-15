package com.mrkekman04.bloodybranch.items.contract;

import com.mrkekman04.bloodybranch.items.BaseItemDamage;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;

public abstract class ItemContract extends BaseItemDamage {
    public ItemContract(String name, int maxDamage, boolean isContainerItem) {
        super(name, maxDamage, false, isContainerItem);
    }


    @Override
    public boolean hasContainerItem(ItemStack stack) {
        return true;
    }

    @Override
    public ItemStack getContainerItem(ItemStack itemStack) {
        return new ItemStack(this, 1, itemStack.getMetadata() + 1);
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        return false;
    }

    @Override
    public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
        return false;
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return false;
    }
}
