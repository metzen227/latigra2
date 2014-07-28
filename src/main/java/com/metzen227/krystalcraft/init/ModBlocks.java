package com.metzen227.krystalcraft.init;

import com.metzen227.krystalcraft.block.BlockAttunementTable;
import com.metzen227.krystalcraft.block.BlockBaseKC;
import com.metzen227.krystalcraft.reference.Names;
import com.metzen227.krystalcraft.reference.References;
import cpw.mods.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(References.MOD_ID)
public class ModBlocks
{
    //declare the block types here
    //almost all blocks should be of the BlockBaseKC type
    public static final BlockBaseKC attunementTable = new BlockAttunementTable();

    public static void init()
    {
        //register the blocks with minecraft
        GameRegistry.registerBlock(attunementTable, Names.Blocks.ATTUNEMENT_TABLE);

    }


}
