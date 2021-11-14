package com.mrkekman04.bloodybranch.container.slot;

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;

public class SlotOutput extends SlotItemHandler {
    public SlotOutput(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
        super(itemHandler, index, xPosition, yPosition);
    }



    @Override
    public int getItemStackLimit(@NotNull ItemStack stack) {
        return stack.getMaxStackSize();
    }

    @Override
    public boolean isItemValid(@NotNull ItemStack stack) {
        return false;
    }
}
