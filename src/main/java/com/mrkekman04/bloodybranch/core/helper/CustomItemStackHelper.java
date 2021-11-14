package com.mrkekman04.bloodybranch.core.helper;

import net.minecraft.item.ItemStack;

public class CustomItemStackHelper {

    public static boolean itemStacksIsEmpty(ItemStack... itemStacks) {
        for (ItemStack itemStack : itemStacks) {
            if (itemStack.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    public static boolean itemIsEqual(ItemStack itemStack, ItemStack... itemStacks) {
        for (ItemStack stack : itemStacks) {
            if (ItemStack.areItemsEqual(stack, itemStack)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isItemEqualByItem(ItemStack itemStack, ItemStack... itemStacks) {
        for (ItemStack stack : itemStacks) {
            if (ItemStack.areItemsEqual(stack, itemStack)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isItemEqualByItemIgnoreDurability(ItemStack itemStack, ItemStack... itemStacks) {
        for (ItemStack stack : itemStacks) {
            if (ItemStack.areItemsEqualIgnoreDurability(stack, itemStack)) {
                return true;
            }
        }
        return false;
    }

    public static ItemStack getItemEqualByItem(ItemStack itemStack, ItemStack... itemStacks) {
        for (ItemStack stack : itemStacks) {
            if (ItemStack.areItemsEqual(stack, itemStack)) {
                return stack;
            }
        }
        return ItemStack.EMPTY;
    }

    public static int getIndexItemWithArray(ItemStack item, ItemStack[] items) {
        for (int i = 0; i < items.length; i++) {
            ItemStack item1 = items[i];
            if (ItemStack.areItemsEqual(item, item1)) {
                return i;
            }
        }
        return -1;
    }

    public static boolean isEnoughCount(ItemStack stack1, ItemStack stack2) {
        return ItemStack.areItemsEqual(stack1, stack2) && stack1.getCount() >= stack2.getCount();
    }

    public static boolean isEnoughCounts(ItemStack stack1, ItemStack stack2, ItemStack stack3, ItemStack stack4) {
        boolean b1 = ItemStack.areItemsEqual(stack1, stack2) && stack1.getCount() >= stack2.getCount();
        boolean b2 = ItemStack.areItemsEqual(stack3, stack2) && stack3.getCount() >= stack2.getCount();
        boolean b3 = ItemStack.areItemsEqual(stack3, stack4) && stack3.getCount() >= stack4.getCount();
        boolean b4 = ItemStack.areItemsEqual(stack1, stack4) && stack1.getCount() >= stack4.getCount();

        return (b1 || b2) && b3 && b4;
    }

    public static boolean isItemMaxStack(ItemStack itemStack) {
        return itemStack.getMaxStackSize() <= itemStack.getCount();
    }
}
