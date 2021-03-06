package com.mrkekman04.bloodybranch.main;

import com.mrkekman04.bloodybranch.config.ConfigHandler;
import com.mrkekman04.bloodybranch.init.InitTile;
import com.mrkekman04.bloodybranch.integration.crafttweaker.CTHandler;
import com.mrkekman04.bloodybranch.misc.CreativeTabBloodyBranch;
import com.mrkekman04.bloodybranch.proxy.Proxy;
import com.mrkekman04.bloodybranch.utils.handlers.RecipeHandler;
import com.mrkekman04.bloodybranch.reference.Reference;
import com.mrkekman04.bloodybranch.utils.consoleMessage.ConsoleMsg;
import com.mrkekman04.bloodybranch.utils.handlers.GuiHandler;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLLoadCompleteEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

@Mod(
        modid = Reference.MOD_ID,
        name = Reference.MOD_NAME,
        version = Reference.VERSION
)
public class Main {

    @Mod.Instance
    public static Main instance;

    @SidedProxy(clientSide = Reference.CLIENT, serverSide = Reference.SERVER)
    public static Proxy proxy;


    // CreativeTab
    public static final CreativeTabs BLOODY_BRANCH = new CreativeTabBloodyBranch("bloody_branch");


    @Mod.EventHandler
    public static void preInit(FMLPreInitializationEvent event) {
        InitTile.init();
        ConfigHandler.load(event.getSuggestedConfigurationFile());
        proxy.preInit(event);
    };

    @Mod.EventHandler
    public static void Init(FMLInitializationEvent event) {
        NetworkRegistry.INSTANCE.registerGuiHandler(Main.instance, new GuiHandler());
        proxy.init(event);

        RecipeHandler.initRecipes();
    };

    @Mod.EventHandler
    public static void postInit(FMLPostInitializationEvent event) {
        if (Loader.isModLoaded("mtlib") && Loader.isModLoaded("crafttweaker")) {
            CTHandler.postInit();
        }

        proxy.postInit(event);
    }
}
