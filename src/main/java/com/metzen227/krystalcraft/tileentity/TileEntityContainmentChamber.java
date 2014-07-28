package com.metzen227.krystalcraft.tileentity;

import com.metzen227.krystalcraft.reference.Names;
import cpw.mods.fml.common.network.NetworkRegistry;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.Packet;

public class TileEntityContainmentChamber extends TileEntityKC implements IInventory
{
    public static final int INVENTORY_SIZE = 1;
    public static final int DISPLAY_SLOT_INVENTORY_INDEX = 0;   //pahicode
    public ItemStack outputItemStack;   //pahicode
    public static final int MAX_STACK_SIZE = 64;

    //itemstack that contains the item currently held
    private ItemStack[] inventory;

    public TileEntityContainmentChamber()
    {
        //constructor initializes the inventory
        inventory = new ItemStack[INVENTORY_SIZE];
    }


    @Override
    public int getSizeInventory()
    {
        return inventory.length;
    }

    @Override
    public ItemStack getStackInSlot(int slotIndex)
    {
        return inventory[slotIndex];
    }

    @Override
    public ItemStack decrStackSize(int slotIndex, int decrementAmount)
    {
        //get the current item in the slot
        ItemStack itemStack = getStackInSlot(slotIndex);
        //if no item exists, exit else continue
        if (itemStack != null)
        {
            //if the count of items is less than the decrement amount, null the stack
            if (itemStack.stackSize <= decrementAmount)
            {
                setInventorySlotContents(slotIndex,null);
            }
            else
            {
                //else decrease the stack by the number of items
                itemStack = itemStack.splitStack(decrementAmount);
                //if the stack count reaches 0 due to the decrement, null the stack
                if (itemStack.stackSize == 0)
                {
                    setInventorySlotContents(slotIndex, null);
                }
            }
        }
        //return the stack
        return itemStack;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slotIndex)
    {
        //get the current item in the slot
        ItemStack itemStack = getStackInSlot(slotIndex);
        //if no item exists, exit else continue
        if (itemStack != null)
        {
            //if there is no item left in the slot, then set the slot to null
            setInventorySlotContents(slotIndex, null);
        }
        //return the stack
        return itemStack;
    }

    @Override
    public void setInventorySlotContents(int slotIndex, ItemStack itemStack)
    {
        inventory[slotIndex] = itemStack;

        if (itemStack != null && itemStack.stackSize > getInventoryStackLimit())
        {
            itemStack.stackSize = getInventoryStackLimit();
        }

        if (!this.worldObj.isRemote)
        {
            ItemStack displayStack = this.inventory[DISPLAY_SLOT_INVENTORY_INDEX];

            if (displayStack != null)
            {
                this.state = (byte) Block.getBlockFromItem(displayStack.getItem()).getLightValue();
            }
            else
            {
                this.state = 0;
            }

            //pahicode to update light render
            //PacketHandler.INSTANCE.sendToAllAround(new MessageTileEntityContainmentChamber(this, displayStack), new NetworkRegistry.TargetPoint(this.worldObj.provider.dimensionId, this.xCoord, this.yCoord, this.zCoord, 128d));
        }

        this.markDirty();

    }

    @Override
    public String getInventoryName()
    {
        return this.hasCustomName() ? this.getCustomName() : Names.Containers.KRYSTAL_CONTAINMENT_CHAMBER;
    }

    @Override
    public boolean hasCustomInventoryName()
    {
        return this.hasCustomName();
    }

    @Override
    public int getInventoryStackLimit()
    {
        return this.MAX_STACK_SIZE;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer entityPlayer)
    {
        return true;
    }

    @Override
    public void openInventory()
    {
        //not used
    }

    @Override
    public void closeInventory()
    {
        //not used
    }

    @Override
    public boolean isItemValidForSlot(int slotIndex, ItemStack itemStack)
    {
        return true;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound)
    {
        super.readFromNBT(nbtTagCompound);

        //pahicode
        // Read in the ItemStacks in the inventory from NBT
        NBTTagList tagList = nbtTagCompound.getTagList(Names.NBT.ITEMS, 10);
        inventory = new ItemStack[this.getSizeInventory()];
        for (int i = 0; i < tagList.tagCount(); ++i)
        {
            NBTTagCompound tagCompound = tagList.getCompoundTagAt(i);
            byte slotIndex = tagCompound.getByte("Slot");
            if (slotIndex >= 0 && slotIndex < inventory.length)
            {
                inventory[slotIndex] = ItemStack.loadItemStackFromNBT(tagCompound);
            }
        }
    }


    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound)
    {
        super.writeToNBT(nbtTagCompound);

        //pahicode
        // Write the ItemStacks in the inventory to NBT
        NBTTagList tagList = new NBTTagList();
        for (int currentIndex = 0; currentIndex < inventory.length; ++currentIndex)
        {
            if (inventory[currentIndex] != null)
            {
                NBTTagCompound tagCompound = new NBTTagCompound();
                tagCompound.setByte("Slot", (byte) currentIndex);
                inventory[currentIndex].writeToNBT(tagCompound);
                tagList.appendTag(tagCompound);
            }
        }
        nbtTagCompound.setTag(Names.NBT.ITEMS, tagList);
    }

    @Override
    public Packet getDescriptionPacket()
    {
        //pahicode
        //return PacketHandler.INSTANCE.getPacketFrom(new MessageTileEntityGlassBell(this, getStackInSlot(DISPLAY_SLOT_INVENTORY_INDEX)));
        return null;
    }




}
