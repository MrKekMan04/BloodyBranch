package com.mrkekman04.bloodybranch.items;

import com.mrkekman04.bloodybranch.init.InitItems;
import com.mrkekman04.bloodybranch.main.Main;
import net.minecraft.item.Item;

public class BaseItem extends Item {

    public BaseItem(String name) {
        this.setRegistryName(name);
        this.setUnlocalizedName(name);
        this.setCreativeTab(Main.BLOODY_BRANCH);
        InitItems.ITEMS.add(this);
    }
}
