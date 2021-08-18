package com.mrkekman04.bloodybranch.proxy;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class CommonProxy
{
    public void registerItemRenderer(Item item, int meta, String id) {}
}
