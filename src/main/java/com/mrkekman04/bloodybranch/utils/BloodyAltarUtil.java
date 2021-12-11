package com.mrkekman04.bloodybranch.utils;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.BlockPos;

import java.util.HashMap;
import java.util.UUID;

public class BloodyAltarUtil {

    private static HashMap<UUID, BlockPos> blocks = new HashMap<>();

    public static boolean addBlock(EntityPlayerMP player, BlockPos pos) {
        if (!hasPlayer(player)) {
            blocks.put(player.getUniqueID(), pos);
            return true;
        }
        return false;
    }

    public static void removeBlock(UUID uuid, BlockPos pos) {
        blocks.remove(uuid, pos);

    }

    public static boolean hasPlayer(EntityPlayerMP player) {
        return blocks.containsKey(player.getUniqueID());
    }
}
