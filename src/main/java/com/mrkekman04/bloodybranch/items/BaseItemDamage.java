package com.mrkekman04.bloodybranch.items;

public abstract class BaseItemDamage extends BaseItem {
    public BaseItemDamage(String name, int maxDamage, boolean isRepair, boolean isContainerItem) {
        super(name);
        if (isContainerItem) setContainerItem(this);
        setMaxDamage(maxDamage);
        setMaxStackSize(1);
        if (! isRepair) setNoRepair();
    }
}
