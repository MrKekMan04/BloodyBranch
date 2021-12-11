package com.mrkekman04.bloodybranch.tile;

import net.minecraft.util.ITickable;

public class TileAdvancedBloodyAltar extends TileBloodyAltar implements ITickable {

    public static int advancedBloodyAltarMaxCapacity;
    public static int advancedBloodyAltarAmountFill;
    public static int advancedBloodyAltarTimeFill;

    public TileAdvancedBloodyAltar() {
        maxCapacity = advancedBloodyAltarMaxCapacity;
        amountFill = advancedBloodyAltarAmountFill;
        timeFill = advancedBloodyAltarTimeFill;
    }
}
