package com.mrkekman04.bloodybranch.lib;

public enum EnumSpawnStone {

    UNFILLED, BLANK, ZOMBIE, SKELETON, WITHER_SKELETON, SPIDER, SLIME, MAGMACUBE, CREEPER, WITCH, SHULKER,
    PIGMAN, ENDERMAN, GHAST, BLITZ, BLUE_SLIME, BASALZ, BLAZE, WOLF, SHEEP, PIG, COW, RABBIT, BLIZZ,
    CHICKEN, OCELOT, MOOSHROOM, PARROT;

    public static EnumSpawnStone getByMeta(int index) {
        return values()[index % values().length];
    }
}