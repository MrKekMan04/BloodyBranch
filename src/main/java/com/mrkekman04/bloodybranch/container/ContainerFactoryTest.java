package com.mrkekman04.bloodybranch.container;

import com.mrkekman04.bloodybranch.container.slot.SlotInputTest;
import com.mrkekman04.bloodybranch.container.slot.SlotOutoutTest;
import com.mrkekman04.bloodybranch.tile.TileFactory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ContainerFactoryTest extends BaseContainer<TileFactory>{

    private final TileFactory te;
    private final EntityPlayer player;

    private int totalTime;
    private int processTime;
    private int energyGUI;
    private int fluidGUI;

    public ContainerFactoryTest(EntityPlayer player, TileFactory te) {
        super(te);

        this.te = te;
        this.player = player;

        this.addSlotToContainer(new SlotInputTest(te, 0, 71, 36));
        this.addSlotToContainer(new SlotOutoutTest(te, 1, 125, 36));

        addInventoryPlayer(player, 8, 18, 84, 18, 8, 18, 142);
    }

    @Override
    public void addListener(IContainerListener listener) {
        super.addListener(listener);
        listener.sendAllWindowProperties(this, te);
    }

    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();
        for (IContainerListener iContainerListener : this.listeners) {
            if (totalTime != te.getField(0)) {
                iContainerListener.sendWindowProperty(this, 0, te.getField(0));
            }
            if (processTime != te.getField(1)) {
                iContainerListener.sendWindowProperty(this, 1, te.getField(1));
            }
            if (energyGUI != te.getField(2)) {
                iContainerListener.sendWindowProperty(this, 2, te.getField(2));
            }
            if (fluidGUI != te.getField(3)) {
                iContainerListener.sendWindowProperty(this, 3, te.getField(3));
            }


            totalTime = te.getField(0);
            processTime = te.getField(1);
            energyGUI = te.getField(2);
            fluidGUI = te.getField(3);
        }
    }


    @Override
    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int id, int data) {
        te.setField(id, data);
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
