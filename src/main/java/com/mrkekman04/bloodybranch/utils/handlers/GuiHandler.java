package com.mrkekman04.bloodybranch.utils.handlers;

import com.mrkekman04.bloodybranch.container.ContainerBloodFactory;
import com.mrkekman04.bloodybranch.gui.GuiBloodFactory;
import com.mrkekman04.bloodybranch.tile.TileBloodFactory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import org.jetbrains.annotations.Nullable;

public class GuiHandler implements IGuiHandler {

    @Nullable
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity te = world.getTileEntity(new BlockPos(x, y, z));

        if (te != null) {
            switch (ID) {
                case 0:
                    return new ContainerBloodFactory(player, (TileBloodFactory) te);
            }
        }
        return null;
    }

    @Nullable
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity te = world.getTileEntity(new BlockPos(x, y, z));

        if (te != null) {
            switch (ID) {
                case 0:
                    return new GuiBloodFactory((TileBloodFactory) te, player);
            }
        }
        return null;
    }
}
