package com.mrkekman04.bloodybranch.items;

import WayofTime.bloodmagic.tile.TileAltar;
import com.mrkekman04.bloodybranch.lib.EnumBottleBlood;
import com.mrkekman04.bloodybranch.main.Main;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class ItemBottleBlood extends BaseItemSubtypes {

    public ItemBottleBlood(String name) {
        super(name);
    }

    @Override
    public EnumActionResult onItemUseFirst(EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, EnumHand hand) {
        if (world.isRemote) {
            TileEntity tileEntity = world.getTileEntity(pos);
            if (tileEntity instanceof TileAltar) {
                return EnumActionResult.SUCCESS;
            }
            return EnumActionResult.FAIL;
        }
        TileEntity tileEntity = world.getTileEntity(pos);
        if (tileEntity instanceof TileAltar) {
            ItemStack item = player.getHeldItem(hand);
            TileAltar tileAltar = (TileAltar) tileEntity;
            if (!item.isEmpty()) {
                switch (item.getItemDamage()) {
                    case 0:
                        tileAltar.fillMainTank(1000);
                        break;
                    case 1:
                        tileAltar.fillMainTank(3000);
                        break;
                    case 2:
                        tileAltar.fillMainTank(5000);
                        break;
                }
                if (!player.isCreative()) {
                    item.shrink(1);
                }
                return EnumActionResult.SUCCESS;
            }
        }
        return EnumActionResult.FAIL;
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return this.getUnlocalizedName() + "_" + EnumBottleBlood.values()[stack.getItemDamage()].name().toLowerCase();
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        if (isInCreativeTab(tab)) {
            for (EnumBottleBlood type : EnumBottleBlood.values()) {
                items.add(new ItemStack(this, 1, type.ordinal()));
            }
        }
    }


    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(I18n.format("desc." + "bottleblood_" + EnumBottleBlood.values()[stack.getItemDamage()].name().toLowerCase()));
    }
}
