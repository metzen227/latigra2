package com.metzen227.krystalcraft;


import com.metzen227.krystalcraft.handler.ConfigurationHandler;
import com.metzen227.krystalcraft.init.ModBlocks;
import com.metzen227.krystalcraft.init.ModItems;
import com.metzen227.krystalcraft.init.Recipes;
import com.metzen227.krystalcraft.proxy.IProxy;
import com.metzen227.krystalcraft.reference.References;
import com.metzen227.krystalcraft.util.LogHelper;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = References.MOD_ID, name = References.MOD_NAME, version = References.MOD_VERSION, guiFactory = References.GUI_FACTORY_CLASS)

public class KrystalCraft
{
    @Mod.Instance("KrystalCraft")
    public static KrystalCraft instance;

    @SidedProxy(clientSide = References.CLIENT_PROXY_CLASS, serverSide = References.SERVER_PROXY_CLASS)
    public static IProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        ConfigurationHandler.init(event.getSuggestedConfigurationFile());
        FMLCommonHandler.instance().bus().register(new ConfigurationHandler());

        //initialize items
        ModItems.init();
        //initialize blocks
        ModBlocks.init();

        LogHelper.info("Pre Initialization Complete");
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        //initialize the recipes
        Recipes.init();
        LogHelper.info("Initialization Complete");
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {

        LogHelper.info("Post Initialization Complete");
    }

}