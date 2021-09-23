package com.mrkekman04.bloodybranch.items;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class ItemLavaBlock extends BaseItem {
    public ItemLavaBlock(String name) {
        super(name);
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (! world.isRemote) {
            if (hand == EnumHand.MAIN_HAND) {
                if (world.isAirBlock(pos.offset(facing))) {
                    ItemStack Item = player.getHeldItem(hand);
                    Item.shrink(1);
                    world.setBlockState(pos.offset(facing), Blocks.FLOWING_LAVA.getDefaultState());
                    return EnumActionResult.SUCCESS;
                }

            }
        }
        return EnumActionResult.FAIL;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(I18n.format("desc.lava_block"));
    }
}
