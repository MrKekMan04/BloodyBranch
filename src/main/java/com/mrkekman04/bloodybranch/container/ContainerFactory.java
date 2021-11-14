package com.mrkekman04.bloodybranch.container;

import com.mrkekman04.bloodybranch.container.slot.SlotInput;
import com.mrkekman04.bloodybranch.container.slot.SlotOutput;
import com.mrkekman04.bloodybranch.tile.TileEntityFactory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.wrapper.CombinedInvWrapper;


public class ContainerFactory extends BaseContainer<TileEntityFactory> {

    private final TileEntityFactory te;
    private final EntityPlayer player;


    public ContainerFactory(EntityPlayer player, TileEntityFactory te) {

        super(te);
        this.te = te;
        this.player = player;

        int index = 0;

        CombinedInvWrapper inventory = te.getInventory();

        addSlotToContainer(new SlotInput(inventory, index++, 71, 36));
        addSlotToContainer(new SlotOutput(inventory, index, 125, 36));

        addInventoryPlayer(player, 8, 18, 84, 18, 8, 18, 142);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
        Slot slot = getSlot(index);
        if (!slot.getHasStack()) {
            return ItemStack.EMPTY;
        }
        ItemStack item = slot.getStack();

        if (index >= 0 && index < 2) {
            if (!this.mergeItemStack(item,2, 38, true)) {
                return ItemStack.EMPTY;
            }
        }
        this.mergeItemStack(item, 0, 1, false);
        return ItemStack.EMPTY;
    }
}
