package com.mrkekman04.bloodybranch.utils.itemStackUtil;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public abstract class NBTTagCompoundHelper {


    public static NBTTagCompound ensureTagCompound(ItemStack itemStack) {
        NBTTagCompound compound = itemStack.getTagCompound();
        if (compound == null) {
            compound = new NBTTagCompound();
            itemStack.setTagCompound(compound);
        }
        return compound;
    }

    public static NBTTagCompound getOrCreateCompoundTag(NBTTagCompound compound, String tagCompound) {
        if (!compound.hasKey(tagCompound)) {
            NBTTagCompound nbtTagCompound = new NBTTagCompound();
            compound.setTag(tagCompound, nbtTagCompound);
            return nbtTagCompound;
        } else {
            return compound.getCompoundTag(tagCompound);
        }
    }
}

