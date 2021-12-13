package com.mrkekman04.bloodybranch.tile;

import WayofTime.bloodmagic.tile.TileAltar;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;

public class TileBloodyFountain extends TileEntity implements ITickable {

    public static int BLOODY_FOUNTAIN_AMOUNT_FILL;
    public static int BLOODY_FOUNTAIN_TIME_FILL;

    protected BlockPos altarPos = BlockPos.ORIGIN;
    protected long lastFill;
    protected int amountFill;
    protected int timeFill;

    public TileBloodyFountain() {
        amountFill = BLOODY_FOUNTAIN_AMOUNT_FILL;
        timeFill = BLOODY_FOUNTAIN_TIME_FILL;
    }

    @Override
    public void update() {
        if (world.isRemote) {
            return;
        }
        long totalWorldTime = world.getTotalWorldTime();
        if (totalWorldTime - lastFill >= timeFill) {
            TileAltar tileAltar = getTileAltar();
            if (tileAltar == null) {
                return;
            }
            tileAltar.fillMainTank(amountFill);
            lastFill = totalWorldTime;
        }
    }

    public TileAltar getTileAltar() {
        TileEntity tileEntity = world.getTileEntity(pos.down());
        if (tileEntity instanceof TileAltar) return ((TileAltar) tileEntity);
        return null;
    }

    public BlockPos getAltarPos() {
        return altarPos;
    }
}
