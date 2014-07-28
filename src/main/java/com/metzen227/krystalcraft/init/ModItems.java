package com.metzen227.krystalcraft.init;


import com.metzen227.krystalcraft.item.ItemAttunementRod;
import com.metzen227.krystalcraft.item.ItemBaseKC;
import com.metzen227.krystalcraft.reference.Names;
import com.metzen227.krystalcraft.reference.References;
import cpw.mods.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(References.MOD_ID)
public class ModItems
{
    //declare the item types here
    //almost all items should be of the ItemBaseKC type
    public static final ItemBaseKC attunementRod = new ItemAttunementRod();


    public static void init()
    {
        //register the items with minecraft
        GameRegistry.registerItem(attunementRod, Names.Items.ATTUNEMENT_ROD);
    }

}
