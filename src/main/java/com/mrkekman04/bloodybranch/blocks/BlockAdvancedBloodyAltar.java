package com.mrkekman04.bloodybranch.blocks;

import com.mrkekman04.bloodybranch.tile.TileAdvancedBloodyAltar;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BlockAdvancedBloodyAltar extends BlockBloodyAltar {

    public BlockAdvancedBloodyAltar(String name) {
        super(name);
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileAdvancedBloodyAltar();
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, ITooltipFlag advanced) {
        tooltip.add(I18n.format("desc.bloody_altar.1"));
        tooltip.add(I18n.format("desc.bloody_altar.2", TileAdvancedBloodyAltar.ADVANCED_BLOODY_ALTAR_MAX_CAPACITY));
        tooltip.add(I18n.format("desc.bloody_altar.3", TileAdvancedBloodyAltar.ADVANCED_BLOODY_ALTAR_AMOUNT_FILL, TileAdvancedBloodyAltar.ADVANCED_BLOODY_ALTAR_TIME_FILL / 20));
        tooltip.add(I18n.format("desc.donate"));
    }
}
