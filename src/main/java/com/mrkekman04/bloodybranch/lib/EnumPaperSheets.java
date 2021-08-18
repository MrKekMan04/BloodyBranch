package com.mrkekman04.bloodybranch.lib;

public enum EnumPaperSheets {

    BLANK, BLOODY;

    public static EnumPaperSheets getByMeta(int index) {
        return values()[index % values().length];
    }

}
