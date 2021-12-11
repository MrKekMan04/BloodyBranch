package com.mrkekman04.bloodybranch.tile;

import WayofTime.bloodmagic.core.RegistrarBloodMagic;
import WayofTime.bloodmagic.core.data.Binding;
import WayofTime.bloodmagic.core.data.SoulNetwork;
import WayofTime.bloodmagic.core.data.SoulTicket;
import WayofTime.bloodmagic.orb.BloodOrb;
import WayofTime.bloodmagic.util.helper.NetworkHelper;
import com.mrkekman04.bloodybranch.utils.BloodyAltarUtil;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

import static com.mrkekman04.bloodybranch.blocks.BlockBloodyAltar.ACTIVE;

public class TileBloodyAltar extends TileEntity implements ITickable {

    public static int bloodyAltarMaxCapacity;
    public static int bloodyAltarAmountFill;
    public static int bloodyAltarTimeFill;

    protected final ItemStackHandler inventory = new ItemStackHandler(1);
    protected EntityPlayer player;
    protected UUID uuid;
    protected SoulNetwork soulNetwork;
    protected BloodOrb orb;
    protected long lastCheck;
    protected long lastFill;
    protected boolean valid;
    protected boolean dirty;

    protected int maxCapacity;
    protected int amountFill;
    protected int timeFill;

    public TileBloodyAltar() {
        maxCapacity = bloodyAltarMaxCapacity;
        amountFill = bloodyAltarAmountFill;
        timeFill = bloodyAltarTimeFill;
    }

    @Override
    public void update() {
        if (world.isRemote) {
            return;
        }
        if (dirty) {
            saveAndSync();
            dirty = false;
        }
        ItemStack stack = inventory.getStackInSlot(0);
        if (stack.isEmpty()) {
            return;
        }
        if (orb == null) {
            orb = getOrb(stack);
            if (orb == null) {
                return;
            }
        }
        if (soulNetwork == null) {
            Binding binding = Binding.fromStack(stack);
            if (binding == null) {
                return;
            }
            soulNetwork = NetworkHelper.getSoulNetwork(binding);
            uuid = binding.getOwnerId();
        }
        if (uuid == null) {
            return;
        }
        long worldTime = world.getTotalWorldTime();
        if (!valid) {
            if (worldTime - lastCheck >= 100) {
                validation();
                lastCheck = worldTime;
            }
            if (!valid) {
                return;
            }
            IBlockState blockState = world.getBlockState(pos);
            world.setBlockState(pos, blockState.withProperty(ACTIVE, true));
        }
        if (worldTime - lastFill >= timeFill) {
            soulNetwork.add(new SoulTicket(amountFill), Math.min(orb.getCapacity(), maxCapacity));
            lastFill = worldTime;
        }
    }

    public static BloodOrb getOrb(ItemStack stack) {
        if (!stack.hasTagCompound()) {
            return null;
        } else {
            ResourceLocation id = new ResourceLocation(stack.getTagCompound().getString("orb"));
            return RegistrarBloodMagic.BLOOD_ORBS.getValue(id);
        }
    }

    public void setItemOrb(ItemStack stack) {
        inventory.setStackInSlot(0, stack);
        reset();
    }

    public ItemStack removeItemOrb() {
        BloodyAltarUtil.removeBlock(uuid, pos);
        reset();
        return inventory.extractItem(0, 64, false);
    }

    public ItemStack getItemOrb() {
        return inventory.getStackInSlot(0);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.setTag("inventory", inventory.serializeNBT());
        return super.writeToNBT(compound);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        inventory.deserializeNBT(compound.getCompoundTag("inventory"));
        super.readFromNBT(compound);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        world.setBlockState(pos, world.getBlockState(pos).withProperty(ACTIVE, false));
        validation();
    }

    private void validation() {
        if (!world.isRemote) {
            if (uuid == null) {
                return;
            }
            dirty = true;
            EntityPlayer entityPlayer = world.getPlayerEntityByUUID(uuid);
            if (entityPlayer instanceof EntityPlayerMP) {
                EntityPlayerMP entityPlayerMP = (EntityPlayerMP) entityPlayer;
                if (BloodyAltarUtil.addBlock(entityPlayerMP, pos)) {
                    player = entityPlayerMP;
                    valid = true;
                    return;
                }
            }
            player = null;
            valid = false;
        }
    }

    private void reset() {
        orb = null;
        soulNetwork = null;
        player = null;
        uuid = null;
        valid = false;
        dirty = true;
        IBlockState blockState = world.getBlockState(pos);
        if (blockState.getBlock().isAir(blockState, world, pos)) {
            return;
        }
        if (blockState.getValue(ACTIVE)) {
            world.setBlockState(pos, blockState.withProperty(ACTIVE, false));
        }
    }

    @Override
    public void invalidate() {
        BloodyAltarUtil.removeBlock(uuid, pos);
        super.invalidate();
    }

    @Override
    public void onChunkUnload() {
        BloodyAltarUtil.removeBlock(uuid, pos);
    }

    public void saveAndSync() {
        IBlockState state = this.world.getBlockState(this.pos);
        this.world.markBlockRangeForRenderUpdate(this.pos, this.pos);
        this.world.notifyBlockUpdate(pos, state, state, 3);
        this.markDirty();
    }

    @Nullable
    @Override
    public SPacketUpdateTileEntity getUpdatePacket() {
        return new SPacketUpdateTileEntity(this.getPos(), 0, this.getUpdateTag());
    }

    @Override
    public NBTTagCompound getUpdateTag() {
        return this.writeToNBT(new NBTTagCompound());
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity packet) {
        this.readFromNBT(packet.getNbtCompound());
    }

    @Override
    public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newSate) {
        return oldState.getBlock() != newSate.getBlock();
    }

    public boolean isValid() {
        return valid;
    }

    public UUID getUuid() {
        return uuid;
    }
}
