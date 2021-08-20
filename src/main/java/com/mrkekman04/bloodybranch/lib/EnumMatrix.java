package com.mrkekman04.bloodybranch.lib;

public enum EnumMatrix {

    EMPTY, IRON, GOLD, COPPER, TIN, LEAD, NICKEL, SILVER, ALUMINIUM, PLATINUM;

    public static EnumMatrix getByMeta(int index) {
        return values()[index % values().length];
    }

}
