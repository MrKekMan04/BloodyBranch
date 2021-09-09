package com.mrkekman04.bloodybranch.items.contract;

import com.mrkekman04.bloodybranch.items.BaseItemDamage;

public abstract class ItemContract extends BaseItemDamage {
    public ItemContract(String name, int maxDamage, boolean isContainerItem) {
        super(name, maxDamage, false, isContainerItem);
    }
}
