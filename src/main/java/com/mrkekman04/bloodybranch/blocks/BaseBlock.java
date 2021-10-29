package com.mrkekman04.bloodybranch.blocks;

import com.mrkekman04.bloodybranch.init.InitBlocks;
import com.mrkekman04.bloodybranch.init.InitItems;
import com.mrkekman04.bloodybranch.main.Main;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;

public class BaseBlock extends Block {

    public BaseBlock(String name, Material material) {

        super(material);
        setRegistryName(name);
        setUnlocalizedName(name);
        setCreativeTab(Main.BLOODY_BRANCH);

        InitBlocks.BLOCKS.add(this);
        InitItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
    }
}
