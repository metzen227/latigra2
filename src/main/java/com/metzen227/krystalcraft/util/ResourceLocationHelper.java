package com.metzen227.krystalcraft.util;

import com.metzen227.krystalcraft.reference.References;
import net.minecraft.util.ResourceLocation;

public class ResourceLocationHelper
{
    public static ResourceLocation getResourceLocation(String modId, String path)
    {
        return new ResourceLocation(modId, path);
    }

    public static ResourceLocation getResourceLocation(String path)
    {
        return getResourceLocation(References.MOD_ID.toLowerCase(), path);
    }

}
