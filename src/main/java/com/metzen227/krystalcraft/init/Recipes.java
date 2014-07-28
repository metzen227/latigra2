package com.metzen227.krystalcraft.init;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class Recipes
{
    public static void init()
    {
        initModRecipes();


    }

    private static void initModRecipes()
    {
        //adds standard recipes for Blocks
        GameRegistry.addRecipe(new ItemStack(ModBlocks.attunementTable), "ggg", "sds", "sos", 'g', Blocks.glass, 's', Blocks.stone, 'd', Items.diamond, 'o', Blocks.obsidian );



        //adds standard recipes for Items
        GameRegistry.addRecipe(new ItemStack(ModItems.attunementRod), "  d", " s ", "s  ", 'd', Items.diamond, 's', Items.stick);


    }

}
