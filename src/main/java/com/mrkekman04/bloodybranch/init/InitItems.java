package com.mrkekman04.bloodybranch.init;

import com.mrkekman04.bloodybranch.items.*;
import com.mrkekman04.bloodybranch.items.contract.*;
import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.List;

public class InitItems
{
    public static final List<Item> ITEMS = new ArrayList<Item>();
    //items

    // ============MATRIX==================
    public static final Item MATRIX_CHARD = new ItemMatrixChard("matrix_chard");
    public static final Item MATRIX = new ItemMatrix("matrix");
    // ============CONTRACTS===============
    public static final Item CONTRACT_BLANK = new ItemContractBlank("contract_blank");
    public static final Item CONTRACT_IRON = new ItemContractIron("contract_iron");
    public static final Item CONTRACT_GOLD = new ItemContractGold("contract_gold");
    public static final Item CONTRACT_COPPER = new ItemContractCopper("contract_copper");
    public static final Item CONTRACT_TIN = new ItemContractTin("contract_tin");
    public static final Item CONTRACT_LEAD = new ItemContractLead("contract_lead");
    public static final Item CONTRACT_NICKEL = new ItemContractNickel("contract_nickel");
    public static final Item CONTRACT_SILVER = new ItemContractSilver("contract_silver");
    public static final Item CONTRACT_ALUMINIUM = new ItemContractAluminium("contract_aluminium");
    public static final Item CONTRACT_PLATINUM = new ItemContractPlatinum("contract_platinum");
    // ============ELEMENTS================
    public static final Item ELEMENT = new ItemElement("element");
    public static final Item ELEMENTAL_CORE = new BaseItem("elemental_core");
    // ============MATERIALS================
    public static final Item PAPER_SHEETS = new ItemPaperSheets("paper_sheets");
    public static final Item LAVA_CORE = new BaseItem("lava_core");
    public static final Item BLOODY_BRANCH = new ItemBloodyBranch("bloody_branch");
    public static final Item LAVA_BLOCK = new ItemLavaBlock("lava_block");
    public static final Item RUNE = new ItemRune("rune");
    public static final Item SPAWN_STONE = new ItemSpawnStone("spawnstone");

    // ============BOTTLE_BLOOD=================
    public static final Item BOTTLE_BLOOD = new ItemBottleBlood("bottleblood");


    public static final Item WOODEN_SHEAR = new ItemWoodShear("wooden_shear");


}
