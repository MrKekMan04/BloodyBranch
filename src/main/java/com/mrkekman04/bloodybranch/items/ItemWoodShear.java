package com.mrkekman04.bloodybranch.items;

import com.mrkekman04.bloodybranch.init.InitItems;
import com.mrkekman04.bloodybranch.main.Main;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemShears;
import net.minecraft.item.ItemStack;

public class ItemWoodShear extends ItemShears {

    public ItemWoodShear(String name) {
        super();
        setCreativeTab(Main.BLOODY_BRANCH);
        setMaxDamage(32);
        this.setRegistryName(name);
        this.setUnlocalizedName(name);
        this.setCreativeTab(Main.BLOODY_BRANCH);
        InitItems.ITEMS.add(this);
    }

    @Override
    public float getDestroySpeed(ItemStack stack, IBlockState state) {
        Block block = state.getBlock();

        if (block != Blocks.WEB && state.getMaterial() != Material.LEAVES) {
            return block == Blocks.WOOL ? 5.0F : super.getDestroySpeed(stack, state);
        } else {
            return 2.0F;
        }
    }
}
