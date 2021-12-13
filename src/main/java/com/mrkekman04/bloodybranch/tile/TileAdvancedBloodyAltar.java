package com.mrkekman04.bloodybranch.tile;

import net.minecraft.util.ITickable;

public class TileAdvancedBloodyAltar extends TileBloodyAltar implements ITickable {

    public static int ADVANCED_BLOODY_ALTAR_MAX_CAPACITY;
    public static int ADVANCED_BLOODY_ALTAR_AMOUNT_FILL;
    public static int ADVANCED_BLOODY_ALTAR_TIME_FILL;

    public TileAdvancedBloodyAltar() {
        maxCapacity = ADVANCED_BLOODY_ALTAR_MAX_CAPACITY;
        amountFill = ADVANCED_BLOODY_ALTAR_AMOUNT_FILL;
        timeFill = ADVANCED_BLOODY_ALTAR_TIME_FILL;
    }
}
