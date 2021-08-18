package com.mrkekman04.bloodybranch.lib;

public enum EnumElement {

    WATER, AIR, FIRE, EARTH, DARKNESS, LIGHT;

    public static EnumElement getByMeta(int index) {
        return values()[index % values().length];
    }

}
