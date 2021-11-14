package com.mrkekman04.bloodybranch.utils.handlers;

import java.util.Arrays;

import com.mrkekman04.bloodybranch.utils.interfaces.ICustomInventory;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemHandlerHelper;

public class CustomInventoryHandler implements IItemHandlerModifiable {
    private ICustomInventory inventory;
    private int slots, offset;
    private boolean[] insert, extract;

    public CustomInventoryHandler(ICustomInventory inventory, int slots)
    {
        this(inventory, slots, 0, true, true);
    }

    public CustomInventoryHandler(ICustomInventory inventory, int slots, int offset, boolean insert, boolean extract)
    {
        this(inventory, slots, offset, new boolean[slots], new boolean[slots]);
        Arrays.fill(this.insert, insert);
        Arrays.fill(this.extract, extract);
    }

    public CustomInventoryHandler(ICustomInventory inventory, int slots, int offset, boolean[] insert, boolean[] extract)
    {
        this.inventory = inventory;
        this.slots = slots;
        this.offset = offset;
        this.insert = insert;
        this.extract = extract;
    }

    @Override
    public int getSlots() {
        return slots;
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        return inventory.getInventory().get(slot+offset);
    }

    public boolean canInsert(int slot) {
        return insert[slot];
    }

    @Override
    public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
        if (stack.isEmpty() || !canInsert(slot)) return stack;
        stack = stack.copy();

        if(!inventory.isItemValidForSlot(slot+offset, stack)) return stack;

        int realSlot = slot+offset;
        ItemStack current = inventory.getInventory().get(realSlot);

        if (current.isEmpty())
        {
            int accepted = Math.min(stack.getMaxStackSize(), getSlotLimit(slot));
            if(accepted < stack.getCount())
            {
                if(!simulate)
                {
                    inventory.getInventory().set(realSlot, stack.splitStack(accepted));
                    inventory.updateGraphics(realSlot);
                    return stack;
                }
                else
                {
                    stack.shrink(accepted);
                    return stack;
                }
            }
            else
            {
                if(!simulate)
                {
                    inventory.getInventory().set(realSlot, stack);
                    inventory.updateGraphics(realSlot);
                }
                return ItemStack.EMPTY;
            }
        }
        else
        {
            if(!ItemHandlerHelper.canItemStacksStack(stack, current))
                return stack;

            int accepted = Math.min(stack.getMaxStackSize(), getSlotLimit(slot)) - current.getCount();
            if(accepted < stack.getCount())
            {
                if(!simulate)
                {
                    ItemStack newStack = stack.splitStack(accepted);
                    newStack.grow(current.getCount());
                    inventory.getInventory().set(realSlot, newStack);
                    inventory.updateGraphics(realSlot);
                    return stack;
                }
                else
                {
                    stack.shrink(accepted);
                    return stack;
                }
            }
            else
            {
                if(!simulate)
                {
                    ItemStack newStack = stack.copy();
                    newStack.grow(current.getCount());
                    inventory.getInventory().set(realSlot, newStack);
                    inventory.updateGraphics(realSlot);
                }
                return ItemStack.EMPTY;
            }
        }
    }

    public boolean canExtract(int slot) {
        return extract[slot];
    }

    @Override
    public ItemStack extractItem(int slot, int amount, boolean simulate) {
        if (amount == 0 || !canExtract(slot)) return ItemStack.EMPTY;

        int realSlot = slot+offset;
        ItemStack stack = inventory.getInventory().get(realSlot);

        if (stack.isEmpty()) return ItemStack.EMPTY;

        int extracted = Math.min(stack.getCount(), amount);

        ItemStack copy = stack.copy();
        copy.setCount(extracted);

        if(!simulate)
        {
            if(extracted < stack.getCount()) stack.shrink(extracted);
            else stack = ItemStack.EMPTY;
            inventory.getInventory().set(realSlot, stack);
            inventory.updateGraphics(realSlot);
        }
        return copy;
    }

    @Override
    public int getSlotLimit(int slot) {
        return inventory.getSlotLimit(slot+offset);
    }

    @Override
    public void setStackInSlot(int slot, ItemStack stack) {
        inventory.getInventory().set(slot+offset, stack);
        inventory.updateGraphics(slot+offset);
    }

}