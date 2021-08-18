package com.mrkekman04.bloodybranch.lib;

public enum EnumRune {

    PRIMORDIAL_ELEMENTS, SEASON, MORTAL_SIN;

    public static EnumRune getByMeta(int index) {
        return values()[index % values().length];
    }

}
