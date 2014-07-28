package com.metzen227.krystalcraft.creativeTab;

import com.metzen227.krystalcraft.init.ModItems;
import com.metzen227.krystalcraft.reference.References;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class CreativeTabKC
{
    public static final CreativeTabs KC_TAB = new CreativeTabs(References.MOD_ID.toLowerCase())
    {
        @Override
        public Item getTabIconItem()
        {
            return ModItems.attunementRod;
        }


    };

}
