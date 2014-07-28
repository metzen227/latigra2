package com.metzen227.krystalcraft.block;

import com.metzen227.krystalcraft.KrystalCraft;
import com.metzen227.krystalcraft.reference.GuiId;
import com.metzen227.krystalcraft.reference.Names;
import com.metzen227.krystalcraft.reference.RenderIds;
import com.metzen227.krystalcraft.tileentity.TileEntityContainmentChamber;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockContainmentChamber extends BlockBaseKC implements ITileEntityProvider
{
    public BlockContainmentChamber()
    {
        super(Material.glass);
        this.setHardness(1.0f);
        this.setBlockName(Names.Blocks.CONTAINMENT_CHAMBER);
    }


    @Override
    public TileEntity createNewTileEntity(World world, int metaData)
    {
        return new TileEntityContainmentChamber();
    }

    @Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }


    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }


    @Override
    public int getRenderType()
    {
        return RenderIds.containmentChamber;
    }

    @Override
    public int getLightValue(IBlockAccess world, int x, int y, int z)
    {
        if (world.getTileEntity(x, y, z) instanceof TileEntityContainmentChamber)
        {
            TileEntityContainmentChamber tileEntityContainmentChamber = (TileEntityContainmentChamber) world.getTileEntity(x, y, z);


            return tileEntityContainmentChamber.getState();
        }
        return 0;
    }


    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
    {
        if (player.isSneaking())
        {
            //if the player is sneaking.. do nothing
            return false;
        }
        else
        {
            if (!world.isRemote)
            {
                if (world.getTileEntity(x, y, z) instanceof TileEntityContainmentChamber)
                {
                    player.openGui(KrystalCraft.instance, GuiId.CONTAINMENT_CHAMBER.ordinal(), world, x, y, z);
                }
            }
            return true;
        }
    }




}
