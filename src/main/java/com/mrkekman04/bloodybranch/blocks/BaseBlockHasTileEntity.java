package com.mrkekman04.bloodybranch.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;

public class BaseBlockHasTileEntity extends BaseBlock{
    public BaseBlockHasTileEntity(String name, Material material) {
        super(name, material);
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }
}
