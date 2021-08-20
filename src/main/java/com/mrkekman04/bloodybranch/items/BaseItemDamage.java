package com.mrkekman04.bloodybranch.items;

public abstract class BaseItemDamage extends BaseItem {
    public BaseItemDamage(String name, int maxDamage, boolean isRepair) {
        super(name);
        setContainerItem(this);
        setMaxDamage(maxDamage);
        setMaxStackSize(1);
        if (! isRepair) setNoRepair();
    }
}
