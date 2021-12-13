package com.mrkekman04.bloodybranch.blocks;

import com.mrkekman04.bloodybranch.tile.TileAdvancedBloodyFountain;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class BlockAdvancedBloodyFountain extends BaseBlockHasTileEntity {
    public BlockAdvancedBloodyFountain(String name) {
        super(name, Material.IRON);
        setHardness(2.0F);
        setHarvestLevel("pickaxe", 1);
        setSoundType(SoundType.METAL);
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileAdvancedBloodyFountain();
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullBlock(IBlockState state) {
        return false;
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return new AxisAlignedBB(0.0645, 0.25, 0.0645, 0.9355, 0.41, 0.9355);
    }
}
