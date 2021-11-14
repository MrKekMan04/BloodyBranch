package com.mrkekman04.bloodybranch.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;

public class BaseContainer<T extends TileEntity> extends Container {

    private final T te;


    public BaseContainer(T te) {
        this.te = te;
    }


    @Override
    public boolean canInteractWith(EntityPlayer player) {
        BlockPos pos = this.te.getPos();
        if (this.te.getWorld().getTileEntity(pos) != this.te) {
            return false;
        } else {
            return player.getDistanceSq((double) pos.getX() + 0.5D, (double) pos.getY() + 0.5D, (double) pos.getZ() + 0.5D) <= 64.0D;
        }
    }


    public void addInventoryPlayer(EntityPlayer player, int xL1, int xW1, int yT1, int yH, int xL2, int xW2, int yT2) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                addSlotToContainer(new Slot(player.inventory, j + i * 9 + 9, xL1 + j * xW1, yT1 + i * yH));
            }
        }
        for (int i = 0; i < 9; i++) {
            addSlotToContainer(new Slot(player.inventory, i, xL2 + i * xW2, yT2));
        }
    }
}
