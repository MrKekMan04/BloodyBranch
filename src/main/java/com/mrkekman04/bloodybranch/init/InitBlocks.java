package com.mrkekman04.bloodybranch.init;

import com.mrkekman04.bloodybranch.blocks.BlockBloodFactory;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

import java.util.ArrayList;
import java.util.List;

public class InitBlocks {

    public static final List<Block> BLOCKS = new ArrayList<>();

    // ========== BLOCKS_OF_MOD ==========
    //public static final Block NAME = new classname(name, material)
    public static final Block BLOOD_FACTORY = new BlockBloodFactory("blood_factory", Material.ROCK);



}
