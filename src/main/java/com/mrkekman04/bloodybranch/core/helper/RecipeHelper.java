package com.mrkekman04.bloodybranch.core.helper;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class RecipeHelper {

    public static boolean canMergeItemStack(ItemStack newItemStack, ItemStack oldItemStack) {
        if (oldItemStack.isEmpty() || oldItemStack.getItem().equals(Items.AIR)) {
            return true;
        }
        if (ItemStack.areItemsEqual(newItemStack, oldItemStack)) {
            return newItemStack.getMaxStackSize() >= oldItemStack.getCount() + newItemStack.getCount();
        }
        return false;
    }

    public static ItemStack mergeItemStack(ItemStack newItemStack, ItemStack oldItemStack) {
        if (oldItemStack.isEmpty()) {
            return newItemStack.copy();
        } else {
            oldItemStack.grow(newItemStack.getCount());
            return oldItemStack;
        }
    }

    public static boolean hasCount(ItemStack item, int count) {
        return item.getCount() >= count;
    }
}