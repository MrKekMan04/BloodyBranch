package com.mrkekman04.bloodybranch.blocks;

import com.mrkekman04.bloodybranch.tile.TileBloodyAltar;
import com.mrkekman04.bloodybranch.utils.BloodyAltarUtil;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BlockBloodyAltar extends BaseBlockHasTileEntity {
    public static PropertyBool ACTIVE = PropertyBool.create("active");

    public BlockBloodyAltar(String name) {
        super(name, Material.ROCK);
        setHardness(2.0F);
        setHarvestLevel("pickaxe", 1);
        setSoundType(SoundType.METAL);
        setDefaultState(getDefaultState().withProperty(ACTIVE, false));
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(ACTIVE) ? 1 : 0;
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(ACTIVE, meta > 0);
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, ACTIVE);
    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        world.setBlockState(pos, state, 2);
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (world.isRemote) {
            return true;
        }
        TileEntity tileEntity = world.getTileEntity(pos);
        if (tileEntity instanceof TileBloodyAltar) {
            TileBloodyAltar tileBloodyAltar = (TileBloodyAltar) tileEntity;
            ItemStack stack = player.getHeldItem(hand);

            if (stack.isEmpty()) {
                if (player.isSneaking()) {
                    player.sendStatusMessage(new TextComponentString(tileBloodyAltar.isValid() + ""), true);
                } else if (!tileBloodyAltar.getItemOrb().isEmpty()) {
                    if (!player.inventory.addItemStackToInventory(tileBloodyAltar.removeItemOrb())) {
                        world.spawnEntity(new EntityItem(world, player.posX, player.posY, player.posZ, stack));
                    }
                }
            } else if (tileBloodyAltar.getItemOrb().isEmpty()) {
                if (TileBloodyAltar.getOrb(stack) == null) {
                    return true;
                }
                tileBloodyAltar.setItemOrb(stack.copy());
                stack.setCount(0);
            }
        }
        return true;
    }

    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState state) {
        if (!world.isRemote) {
            TileEntity tileEntity = world.getTileEntity(pos);
            if (tileEntity instanceof TileBloodyAltar) {
                TileBloodyAltar tileBloodyAltar = (TileBloodyAltar) tileEntity;
                BloodyAltarUtil.removeBlock(tileBloodyAltar.getUuid(), tileBloodyAltar.getPos());
                ItemStack itemStack = tileBloodyAltar.removeItemOrb();
                world.spawnEntity(new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), itemStack));
            }
        }
        super.breakBlock(world, pos, state);
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

    @Nullable
    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileBloodyAltar();
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return new AxisAlignedBB(0, 0, 0, 1, 0.9, 1);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, ITooltipFlag advanced) {
        tooltip.add(I18n.format("desc.bloody_altar.1"));
        tooltip.add(I18n.format("desc.bloody_altar.2", TileBloodyAltar.BLOODY_ALTAR_MAX_CAPACITY));
        tooltip.add(I18n.format("desc.bloody_altar.3", TileBloodyAltar.BLOODY_ALTAR_AMOUNT_FILL, TileBloodyAltar.BLOODY_ALTAR_TIME_FILL / 20));
    }
}
