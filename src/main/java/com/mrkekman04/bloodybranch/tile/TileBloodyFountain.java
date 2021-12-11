package com.mrkekman04.bloodybranch.tile;

import WayofTime.bloodmagic.tile.TileAltar;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;

public class TileBloodyFountain extends TileEntity implements ITickable {

    public static int BloodyFountainAmountFill;
    public static int BloodyFountainTimeFill;

    protected BlockPos altarPos = BlockPos.ORIGIN;
    protected TileAltar tileAltar;
    protected boolean hasAltar;
    protected long lastCheck;
    protected long lastFill;
    protected int amountFill;
    protected int timeFill;

    public TileBloodyFountain() {
        amountFill = BloodyFountainAmountFill;
        timeFill = BloodyFountainTimeFill;
    }

    @Override
    public void update() {
        if (world.isRemote) {
            return;
        }
        long totalWorldTime = world.getTotalWorldTime();
        if (!hasAltar) {
            if (totalWorldTime - lastCheck >= 100) {
                altarSearch();
                lastCheck = totalWorldTime;
            }
        }
        if (hasAltar) {
            if (totalWorldTime - lastFill >= timeFill) {
                tileAltar.fillMainTank(amountFill);
                lastFill = totalWorldTime;
            }
        }
    }

    private void altarSearch() {
        BlockPos altarPos = pos.down();
        TileEntity tileEntity = world.getTileEntity(altarPos);
        if (tileEntity instanceof TileAltar) {
            tileAltar = (TileAltar) tileEntity;
            this.altarPos = tileAltar.getPos();
            hasAltar = true;
        }
    }


    public BlockPos getAltarPos() {
        return altarPos;
    }

    public void setHasAltar(boolean hasAltar) {
        this.hasAltar = hasAltar;
    }
}
