package com.mrkekman04.bloodybranch.main;
import com.mrkekman04.bloodybranch.misc.BloodyBranch;
import com.mrkekman04.bloodybranch.proxy.CommonProxy;
import com.mrkekman04.bloodybranch.reference.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(
        modid = Reference.MOD_ID,
        name = Reference.MOD_NAME,
        version = Reference.VERSION
)
public class Main {

    @Mod.Instance
    public static Main instance;

    @SidedProxy(clientSide = Reference.CLIENT, serverSide = Reference.SERVER)
    public static CommonProxy proxy;

    // CreativeTab

    public static final CreativeTabs BLOODY_BRANCH = new BloodyBranch("bloody_branch");


    @Mod.EventHandler
    public static void preInit(FMLPreInitializationEvent event) {};

    @Mod.EventHandler
    public static void Init(FMLInitializationEvent event) {};

    @Mod.EventHandler
    public static void postInit(FMLPostInitializationEvent event) {};

}
