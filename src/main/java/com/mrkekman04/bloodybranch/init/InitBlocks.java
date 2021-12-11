package com.mrkekman04.bloodybranch.init;

import com.mrkekman04.bloodybranch.blocks.*;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

import java.util.ArrayList;
import java.util.List;

public class InitBlocks {

    public static final List<Block> BLOCKS = new ArrayList<>();

    // ========== BLOCKS_OF_MOD ==========
    //public static final Block NAME = new classname(name, material)
    public static final Block BLOOD_FACTORY = new BlockBloodFactory("blood_factory", Material.ROCK);

    public static final Block BLOODY_FOUNTAIN = new BlockBloodyFountain("bloody_fountain");
    public static final Block ADVANCED_BLOODY_FOUNTAIN = new BlockAdvancedBloodyFountain("advanced_bloody_fountain");
    public static final Block BLOODY_ALTAR = new BlockBloodyAltar("bloody_altar");
    public static final Block ADVANCED_BLOODY_ALTAR = new BlockAdvancedBloodyAltar("advanced_bloody_altar");
}
