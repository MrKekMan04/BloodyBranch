package com.mrkekman04.bloodybranch.utils.interfaces;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public interface ICustomInventory extends IInventory {
    /**
     * Get the inventory as a List.
     * @return the inventory
     */
    NonNullList<ItemStack> getInventory();
    /**
     * Mostly a call to markDirty().
     */
    void updateGraphics(int slot);

    /**
     * How many items can fit in the given slot.
     * @param slot slot to check
     * @return max stack size
     */
    default int getSlotLimit(int slot)
    {
        return getInventoryStackLimit();
    }
}
