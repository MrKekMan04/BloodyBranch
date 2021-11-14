package com.mrkekman04.bloodybranch.utils.itemStackUtil;

import gnu.trove.strategy.HashingStrategy;
import net.minecraft.item.ItemStack;

import java.util.Objects;

public class ItemStackHashingStrategy implements HashingStrategy<ItemStack> {

    @Override
    public int computeHashCode(ItemStack itemStack) {
        return Objects.hash(itemStack.getItem(), itemStack.getMetadata());
    }


    @Override
    public boolean equals(ItemStack o1, ItemStack o2) {
        return ItemStack.areItemsEqual(o1, o2);
    }
}
