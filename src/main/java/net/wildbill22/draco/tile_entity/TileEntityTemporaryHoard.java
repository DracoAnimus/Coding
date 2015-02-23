package net.wildbill22.draco.tile_entity;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.ChatComponentText;
import net.wildbill22.draco.blocks.ModBlocks;
import net.wildbill22.draco.blocks.TemporaryHoard;
import net.wildbill22.draco.items.ModItems;
import net.wildbill22.draco.lib.LogHelper;

/**
 * 
 * @author WILLIAM
*/ 
@SuppressWarnings("unused")
public class TileEntityTemporaryHoard extends TileEntityChest {

	private String customName;
	private ItemStack[] chestContents = new ItemStack[36];
	private int cachedChestType;
	
	public TileEntityTemporaryHoard adjacentChestZNeg;
    /** Contains the chest tile located adjacent to this one (if any) */
    public TileEntityTemporaryHoard adjacentChestXPos;
    /** Contains the chest tile located adjacent to this one (if any) */
    public TileEntityTemporaryHoard adjacentChestXNeg;
    /** Contains the chest tile located adjacent to this one (if any) */
    public TileEntityTemporaryHoard adjacentChestZPos;

    // Stuff Changed to allow only gold coins:
    /**
     * Sets the given item stack to the specified slot in the inventory (can be crafting or armor sections).
     */
    public void setInventorySlotContents(int par1, ItemStack itemstack){
    	if (itemstack.getItem() == ModItems.goldCoin) {
	    	this.chestContents[par1] = itemstack;
	    	if(itemstack != null && itemstack.stackSize > this.getInventoryStackLimit()){
	    		itemstack.stackSize = this.getInventoryStackLimit();
	    	}
	    	this.markDirty();
    	}
    	else {
    		// TODO: Add chat message as to why it won't add
//    		this.getWorldObj().playerEntities;
    		// Find closest?
//			player.addChatMessage(new ChatComponentText("You can only store gold coins in the hoard"));
    	}
    }
    

    // Original stuff from Treasure Chest:
    
	@Override
    public void closeInventory(){
		super.closeInventory();
	}

	/**
     * Returns the name of the inventory
     */
    public String getInventoryName() {
        return this.hasCustomInventoryName() ? this.customName : ModBlocks.TEMPORARY_HOARD_TEXTURENAME;
    }

    /**
     * Returns if the inventory is named
     */
    public boolean hasCustomInventoryName()
    {
        return this.customName != null && this.customName.length() > 0;
    }

//    @Override
//    public int getSizeInventory() {
//    	return 40;
//    }

    public void func_145976_a(String p_145976_1_)
    {
        this.customName = p_145976_1_;
    }

    public void readFromNBT(NBTTagCompound p_145839_1_) {
        super.readFromNBT(p_145839_1_);
        
        NBTTagList nbttaglist = p_145839_1_.getTagList("Items", 10);
        this.chestContents = new ItemStack[this.getSizeInventory()];

        if (p_145839_1_.hasKey(ModBlocks.TEMPORARY_HOARD_TEXTURENAME, 8)) {
            this.customName = p_145839_1_.getString(ModBlocks.TEMPORARY_HOARD_TEXTURENAME);
        }

        for (int i = 0; i < nbttaglist.tagCount(); ++i) {
            NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
            int j = nbttagcompound1.getByte("Slot") & 255;

            if (j >= 0 && j < this.chestContents.length) {
                this.chestContents[j] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }        
    }

    public void writeToNBT(NBTTagCompound p_145841_1_) {
        super.writeToNBT(p_145841_1_);
        
        NBTTagList nbttaglist = new NBTTagList();
        for (int i = 0; i < this.chestContents.length; ++i) {
            if (this.chestContents[i] != null) {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte)i);
                this.chestContents[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }
        p_145841_1_.setTag("Items", nbttaglist);

        if (this.hasCustomInventoryName()) {
            p_145841_1_.setString(ModBlocks.TEMPORARY_HOARD_TEXTURENAME, this.customName);
        }
    }

    private void func_145978_a(TileEntityTemporaryHoard p_145978_1_, int p_145978_2_)
    {
        if (p_145978_1_.isInvalid())
        {
            this.adjacentChestChecked = false;
        }
        else if (this.adjacentChestChecked)
        {
            switch (p_145978_2_)
            {
                case 0:
                    if (this.adjacentChestZPos != p_145978_1_)
                    {
                        this.adjacentChestChecked = false;
                    }

                    break;
                case 1:
                    if (this.adjacentChestXNeg != p_145978_1_)
                    {
                        this.adjacentChestChecked = false;
                    }

                    break;
                case 2:
                    if (this.adjacentChestZNeg != p_145978_1_)
                    {
                        this.adjacentChestChecked = false;
                    }

                    break;
                case 3:
                    if (this.adjacentChestXPos != p_145978_1_)
                    {
                        this.adjacentChestChecked = false;
                    }
            }
        }
    }

    /**
     * Performs the check for adjacent chests to determine if this chest is double or not.
     */
    public void checkForAdjacentChests()
    {
        if (!this.adjacentChestChecked)
        {
            this.adjacentChestChecked = true;
            this.adjacentChestZNeg = null;
            this.adjacentChestXPos = null;
            this.adjacentChestXNeg = null;
            this.adjacentChestZPos = null;

            if (this.func_145977_a(this.xCoord - 1, this.yCoord, this.zCoord))
            {
                this.adjacentChestXNeg = (TileEntityTemporaryHoard)this.worldObj.getTileEntity(this.xCoord - 1, this.yCoord, this.zCoord);
            }

            if (this.func_145977_a(this.xCoord + 1, this.yCoord, this.zCoord))
            {
                this.adjacentChestXPos = (TileEntityTemporaryHoard)this.worldObj.getTileEntity(this.xCoord + 1, this.yCoord, this.zCoord);
            }

            if (this.func_145977_a(this.xCoord, this.yCoord, this.zCoord - 1))
            {
                this.adjacentChestZNeg = (TileEntityTemporaryHoard)this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord - 1);
            }

            if (this.func_145977_a(this.xCoord, this.yCoord, this.zCoord + 1))
            {
                this.adjacentChestZPos = (TileEntityTemporaryHoard)this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord + 1);
            }

            if (this.adjacentChestZNeg != null)
            {
                this.adjacentChestZNeg.func_145978_a(this, 0);
            }

            if (this.adjacentChestZPos != null) {
                this.adjacentChestZPos.func_145978_a(this, 2);
            }

            if (this.adjacentChestXPos != null) {
                this.adjacentChestXPos.func_145978_a(this, 1);
            }

            if (this.adjacentChestXNeg != null) {
                this.adjacentChestXNeg.func_145978_a(this, 3);
            }
        }
    }

    // Get an instance of the chest at x, y, z
    private boolean func_145977_a(int x, int y, int z)
    {
        if (this.worldObj == null)
        {
            return false;
        }
        else
        {
            Block block = this.worldObj.getBlock(x, y, z);
            return block instanceof TemporaryHoard && ((TemporaryHoard)block).field_149956_a == this.func_145980_j();
        }
    }
    
    public int func_145980_j()
    {
        if (this.cachedChestType == -1)
        {
            if (this.worldObj == null || !(this.getBlockType() instanceof TemporaryHoard)) {
                return 0;
            }

            this.cachedChestType = ((TemporaryHoard)this.getBlockType()).field_149956_a;
        }

        return this.cachedChestType;
    }
    
    public ItemStack getStackInSlot(int par1) {
    	return this.chestContents[par1];
    }
    
    public ItemStack decrStackSize(int par1, int par2) {
        if (this.chestContents[par1] != null) {
            ItemStack itemstack;

            if (this.chestContents[par1].stackSize <= par2) {
                itemstack = this.chestContents[par1];
                this.chestContents[par1] = null;
                this.markDirty();
                return itemstack;
            }
            else {
                itemstack = this.chestContents[par1].splitStack(par2);

                if (this.chestContents[par1].stackSize == 0) {
                    this.chestContents[par1] = null;
                }

                this.markDirty();
                return itemstack;
            }
        } else {
            return null;
        }
    }
    
    public ItemStack getStackInSlotOnClosing(int par1){
    	if(this.chestContents[par1] != null){
    		ItemStack itemstack = this.chestContents[par1];
    		this.chestContents[par1] = null;
    		return itemstack;
    	}
    	else{
    		return null;
    	}
    }
    
    public int getInventoryStackLimit() {
        return 64;
    }
}
