package com.mrkekman04.bloodybranch.blocks;

import com.mrkekman04.bloodybranch.tile.TileAdvancedBloodyAltar;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class BlockAdvancedBloodyAltar extends BlockBloodyAltar {

    public BlockAdvancedBloodyAltar(String name) {
        super(name);
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileAdvancedBloodyAltar();
    }
}
