package com.mrkekman04.bloodybranch.blocks;

import WayofTime.bloodmagic.block.BlockLifeEssence;
import com.mrkekman04.bloodybranch.core.helper.FluidHelper;
import com.mrkekman04.bloodybranch.main.Main;
import com.mrkekman04.bloodybranch.tile.TileEntityFactory;
import com.mrkekman04.bloodybranch.tile.TileFactory;
import com.mrkekman04.bloodybranch.utils.handlers.CustomItemStackHandler;
import com.mrkekman04.bloodybranch.utils.handlers.FluidHandler;
import com.mrkekman04.bloodybranch.utils.itemStackUtil.NBTTagCompoundHelper;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.fluids.FluidStack;
import org.jetbrains.annotations.Nullable;

public class BlockFactoryTest extends BaseBlockHasTileEntity {

    public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
    public static final PropertyBool WORKING = PropertyBool.create("working");

    public BlockFactoryTest(String name, Material material) {
        super(name, material);
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(WORKING, false));

        setHardness(2.0F);
        setHarvestLevel("pickaxe", 1);
        setSoundType(SoundType.METAL);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (worldIn.isRemote) return true;

        ItemStack item = playerIn.getHeldItem(hand);
        if (item.hasCapability(FluidHelper.FLUID_HANDLER_ITEM, null)) {
            FluidStack fluid = FluidHelper.getFluidFromStack(item);
            if (fluid != null) {
                if (fluid.getFluid() == BlockLifeEssence.getLifeEssence()) {
                    if (FluidHandler.bucketInteractWithBlock(playerIn, hand, worldIn, pos, false)) return true;
                }
            }
        }

        TileEntity tile = worldIn.getTileEntity(pos);
        if (tile instanceof TileFactory) {
            playerIn.openGui(Main.instance, 1, worldIn, pos.getX(), pos.getY(), pos.getZ());
            return true;
        }
        return false;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(FACING).ordinal() | (state.getValue(WORKING) ? 8 : 0);
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(FACING, EnumFacing.getHorizontal(meta & 7)).withProperty(WORKING, (meta & 8) > 0);
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, FACING, WORKING);
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        worldIn.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);

        if (worldIn.isRemote) return;
        NBTTagCompound nbtTagCompound = NBTTagCompoundHelper.ensureTagCompound(stack);
        TileEntity tileEntity = worldIn.getTileEntity(pos);
        if (tileEntity instanceof TileFactory) {
            TileFactory tile = (TileFactory) tileEntity;

            if (tile.getTank() != null && !nbtTagCompound.hasNoTags()) {
                tile.getTank().readFromNBT(nbtTagCompound.getCompoundTag("tank"));
            }

            if (tile.getEnergyStorage() != null && !nbtTagCompound.hasNoTags()) {
                CapabilityEnergy.ENERGY.readNBT(tile.getEnergyStorage(), null, nbtTagCompound.getTag("energyStorage"));
            }
        }


    }

    @Override
    public void onBlockHarvested(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player) {
        if (worldIn.isRemote) return;
        TileEntity tileEntity = worldIn.getTileEntity(pos);
        if (tileEntity instanceof TileFactory) {
            TileFactory tile = (TileFactory) tileEntity;
            ItemStack item = new ItemStack(this);
            NBTTagCompound compound = new NBTTagCompound();
            if (tile.getTank() != null) {
                compound.setTag("tank", tile.getTank().writeToNBT(new NBTTagCompound()));
            }
            if (tile.getEnergyStorage() != null) {
                compound.setTag("energyStorage", CapabilityEnergy.ENERGY.writeNBT(tile.getEnergyStorage(), null));
            }

            if (!compound.hasNoTags()) {
                item.setTagCompound(compound);
                spawnAsEntity(worldIn, pos, item);
            } else if (!player.isCreative()) {
                spawnAsEntity(worldIn, pos, item);
            }
        }
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
        if (worldIn.isRemote) return;
        TileEntity tileEntity = worldIn.getTileEntity(pos);
        if (tileEntity instanceof TileFactory) {
            TileFactory tile = (TileFactory) tileEntity;
            for (ItemStack item : tile.getInventory()) {
                if (!item.isEmpty()) {
                    InventoryHelper.spawnItemStack(worldIn, pos.getX(), pos.getY(), pos.getZ(), item);
                }
            }
        }
    }

    @Override
    public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {

    }

    @Nullable
    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileFactory();
    }
}
