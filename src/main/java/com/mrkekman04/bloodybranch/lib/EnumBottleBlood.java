package com.mrkekman04.bloodybranch.lib;

public enum EnumBottleBlood {
    LESSER,
    MEDIUM,
    LARGE;

    public static EnumBottleBlood getByMeta(int index) {
        return values()[index % values().length];
    }
}
