package com.metzen227.krystalcraft.reference;

import com.metzen227.krystalcraft.util.ResourceLocationHelper;
import net.minecraft.util.ResourceLocation;

public final class Textures
{
    public static final String RESOURCE_PREFIX = References.MOD_ID.toLowerCase() + ":";

    //declare the base file paths
    public static final String MODEL_TEXTURE_LOCATION = "textures/models/";
    public static final String GUI_SHEET_LOCATION = "textures/gui/";
    public static final String ARMOR_SHEET_LOCATION = "textures/armor/";
    public static final String EFFECTS_LOCATIONS = "textures/effects/";

    //model textures
    public static final ResourceLocation MODEL_ATTUNEMENT_TABLE = ResourceLocationHelper.getResourceLocation(MODEL_TEXTURE_LOCATION + "attunementTable.png");

    //gui textures
    public static final ResourceLocation GUI_ATTUNEMENT_TABLE = ResourceLocationHelper.getResourceLocation(GUI_SHEET_LOCATION + "attunementTable.png");



}
