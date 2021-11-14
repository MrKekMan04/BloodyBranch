package com.mrkekman04.bloodybranch.utils.handlers;

import com.mrkekman04.bloodybranch.core.helper.CustomItemStackHelper;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemStackHandler;

import java.util.ArrayList;

public class CustomItemStackHandler extends ItemStackHandler {

    public CustomItemStackHandler() {
        this(1);
    }

    public CustomItemStackHandler(int size) {
        super(size);
    }

    public CustomItemStackHandler(NonNullList<ItemStack> stacks) {
        super(stacks);
    }

    public ItemStack getFirstFindItem() {
        for (ItemStack stack : stacks) {
            if (!stack.isEmpty()) {
                return stack;
            }
        }
        return ItemStack.EMPTY;
    }

    public int getFirstFindItemIndex() {
        for (int i = 0; i < stacks.size(); i++) {
            if (!stacks.get(i).isEmpty()) {
                return i;
            }
        }
        return -1;
    }

    public int getFirstFindItemIndex(int index) {
        for (int i = index; i < stacks.size(); i++) {
            if (!stacks.get(i).isEmpty()) {
                return i;
            }
        }
        return -1;
    }


    public boolean isEmptySlot() {
        for (ItemStack stack : stacks) {
            if (stack.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    public boolean isEmptySlots() {
        for (ItemStack stack : stacks) {
            if (!stack.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public ArrayList<ItemStack> getItems() {
        ArrayList<ItemStack> items = new ArrayList<>();
        for (ItemStack stack : stacks) {
            if (!stack.isEmpty()) {
                items.add(stack);
            }
        }
        return items;
    }

    public ItemStack getItem(ItemStack itemStack) {
        for (ItemStack stack : stacks) {
            if (ItemStack.areItemsEqual(stack, itemStack)) {
                return stack;
            }
        }
        return ItemStack.EMPTY;
    }

    public ItemStack getAndRemove(int index) {
        validateSlotIndex(index);
        return this.stacks.set(index, ItemStack.EMPTY);
    }

    public boolean insertItem(ItemStack itemStack, Boolean simulation) {
        for (int i = 0; i < stacks.size(); i++) {
            ItemStack stack = stacks.get(i);
            ItemStack copy = itemStack.copy();
            copy.setCount(1);
            if (stack.isEmpty() || ItemStack.areItemsEqual(stack, itemStack) && !CustomItemStackHelper.isItemMaxStack(stack)) {
                if (!simulation) {
                    insertItem(i, copy, false);
                }
                return true;
            }
        }
        return false;
    }

    public void dropItem(World world, BlockPos pos) {
        for (int i = 0; i < stacks.size(); i++) {
            ItemStack itemStack = extractItem(i, 64, false);
            if (!itemStack.isEmpty()) {
                InventoryHelper.spawnItemStack(world, pos.getX(), pos.getY(), pos.getZ(), itemStack);
            }
        }
    }
}
