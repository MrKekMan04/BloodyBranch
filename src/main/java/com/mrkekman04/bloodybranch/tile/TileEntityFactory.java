package com.mrkekman04.bloodybranch.tile;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.items.ItemStackHandler;

public class TileEntityFactory extends TileEntity {

    ItemStackHandler inventory = new ItemStackHandler(2);

    public TileEntityFactory() {

    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        NBTTagCompound nbtTagCompound = inventory.serializeNBT();
        compound.setTag("inventory", nbtTagCompound);
        return super.writeToNBT(compound);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        NBTTagCompound inventory = compound.getCompoundTag("inventory");
        this.inventory.deserializeNBT(inventory);
        super.readFromNBT(compound);
    }

    public ItemStackHandler getInventory() {
        return inventory;
    }
}
