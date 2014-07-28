package com.metzen227.krystalcraft.block;


import com.metzen227.krystalcraft.creativeTab.CreativeTabKC;
import com.metzen227.krystalcraft.reference.References;
import com.metzen227.krystalcraft.tileentity.TileEntityKC;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.Random;

public class BlockBaseKC extends Block
{
    //constructor
    public BlockBaseKC(Material material)
    {
        super(material);
        this.setCreativeTab(CreativeTabKC.KC_TAB);
    }

    //generic constructor
    public BlockBaseKC()
    {
        this(Material.iron);
    }

    @Override
    public String getUnlocalizedName()
    {
        return String.format("tile.%s%s", References.MOD_ID.toLowerCase() + ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }


    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        blockIcon = iconRegister.registerIcon(this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1));
    }


    protected String getUnwrappedUnlocalizedName(String unlocalizedName)
    {
        return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int meta)
    {
        //if the block is broken perform this code
        dropInventory(world, x, y, z);
        super.breakBlock(world, x, y, z, block, meta);
    }

    protected void dropInventory(World world, int x, int y, int z)
    {
        //based on Pahimar's EE3 code.
        //called when an block is broken
        TileEntity tileEntity = world.getTileEntity(x, y, z);

        if (!(tileEntity instanceof IInventory))
        {
            //if the tile entity isn't a container, do nothing extra
            return;
        }

        //get the inventory contained by the tileEntity that is contained within the block
        IInventory inventory = (IInventory) tileEntity;

        for (int i = 0; i < inventory.getSizeInventory(); i++)
        {
            //loop through all the items in the inventory
            ItemStack itemStack = inventory.getStackInSlot(i);

            if (itemStack != null && itemStack.stackSize > 0)
            {
                //check to see if an item exists in the inventory slot and that the stack size is at least 1
                //if so, create some random vectors for the item to be ejected on that path
                Random rand = new Random();

                float dX = rand.nextFloat() * 0.8F + 0.1F;
                float dY = rand.nextFloat() * 0.8F + 0.1F;
                float dZ = rand.nextFloat() * 0.8F + 0.1F;

                //create a new copy of the item stack
                EntityItem entityItem = new EntityItem(world, x + dX, y + dY, z + dZ, itemStack.copy());

                if (itemStack.hasTagCompound())
                {
                    //copy over any NBT data that may be associated with the original item
                    entityItem.getEntityItem().setTagCompound((NBTTagCompound) itemStack.getTagCompound().copy());
                }

                //create motion random effect
                float factor = 0.05F;
                entityItem.motionX = rand.nextGaussian() * factor;
                entityItem.motionY = rand.nextGaussian() * factor + 0.2F;
                entityItem.motionZ = rand.nextGaussian() * factor;

                //spawn the new item into the world
                world.spawnEntityInWorld(entityItem);
                //delete the original itemStack from the inventory
                itemStack.stackSize = 0;

            }
        }
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityLiving, ItemStack itemStack)
    {
        //code based on Pahimar's EE3 block placed code
        if (world.getTileEntity(x,y,z) instanceof TileEntityKC)
        {
            //if the block is part of the Mod's tileEntity group then perform this code to orient the block to the direction of the placer
            int direction = 0;
            int facing = MathHelper.floor_double(entityLiving.rotationYaw *4.0F /360.0F + 05D) & 3;

            if (facing == 0)
            {
                direction = ForgeDirection.NORTH.ordinal();
            }
            else if (facing == 1)
            {
                direction = ForgeDirection.EAST.ordinal();
            }
            else if (facing == 2)
            {
                direction = ForgeDirection.SOUTH.ordinal();
            }
            else if (facing == 3)
            {
                direction = ForgeDirection.WEST.ordinal();
            }


            if (itemStack.hasDisplayName())
            {
                ((TileEntityKC) world.getTileEntity(x,y,z)).setCustomName(itemStack.getDisplayName());
            }

            ((TileEntityKC) world.getTileEntity(x,y,z)).setOrientation(direction);

        }

    }

}
