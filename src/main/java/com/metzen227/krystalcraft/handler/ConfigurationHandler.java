package com.metzen227.krystalcraft.handler;

import com.metzen227.krystalcraft.reference.References;
import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.common.config.Configuration;


import java.io.File;

public class ConfigurationHandler
{

    public static Configuration configuration;
    public static boolean testValue = false;


    public static void init(File configFile)
    {
        if (configuration == null)
        {
            //create the configuration object from the given configuration file
            configuration = new Configuration(configFile);
            loadConfiguration();
        }

    }

    @SubscribeEvent
    public void onConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event)
    {
        if (event.modID.equalsIgnoreCase(References.MOD_ID))
        {
            //reload the configs
            loadConfiguration();
        }
    }


    private static void loadConfiguration()
    {
        //load the configurations
        testValue = configuration.getBoolean("configValue",Configuration.CATEGORY_GENERAL, false, "this is an example configuration value");

        if (configuration.hasChanged())
        {
            configuration.save();
        }

    }


}
