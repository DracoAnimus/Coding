package net.wildbill22.draco.tile_entity;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntityChest;
import net.wildbill22.draco.blocks.ModBlocks;
import net.wildbill22.draco.blocks.TemporaryHoard;
import net.wildbill22.draco.entities.player.DragonPlayer;
import net.wildbill22.draco.items.ModItems;

/**
 * 
 * @author WILLIAM
*/ 
public class TileEntityTemporaryHoard extends TileEntityChest {	
	/**
	 * Container (Used for inventory interactions on server and client) for this temporaryHoard
	 * Calculates dragon level on close
	 * @author Maxanier
	 */
	public static class HoardContainer extends ContainerChest{
		public HoardContainer(IInventory player, IInventory chest) {
			super(player, chest);
		}
		
		@Override
		public void onContainerClosed(EntityPlayer player){
			if(!player.worldObj.isRemote){
				DragonPlayer.get(player).calculateHoardSize(player.worldObj);
			}
			super.onContainerClosed(player);
		}
		
		/**
		 * Replaces the chest inventory slots by filter slots
		 */
		@Override
		protected Slot addSlotToContainer(Slot slot){
			if(slot.inventory.equals(this.getLowerChestInventory())){
				return super.addSlotToContainer(new CoinSlot(slot));
			}
			return super.addSlotToContainer(slot);
		}
		
		/**
		 * Filter Slot which clones an existing slot, but only allows gold coins
		 */
		public static class CoinSlot extends Slot{
			public CoinSlot(Slot slot) {
				super(slot.inventory, slot.getSlotIndex(),slot.xDisplayPosition,slot.yDisplayPosition);
			}
			
			public boolean isItemValid(ItemStack stack){
				if(stack!=null&&stack.getItem().equals(ModItems.goldCoin)){
					return true;
				}
				else{
					return false;
				}
			}
		}
	}

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

    // owner of chest (player displayname)
    private String owner;
    
    // Stuff Changed to allow only gold coins:
    /**
     * Sets the given item stack to the specified slot in the inventory (can be crafting or armor sections).
     * Allows other items, need to figure out how to make only gold coins accepted but not lose other items
     */
	@Override
    public void setInventorySlotContents(int par1, ItemStack itemstack){
    	if (itemstack!=null&&itemstack.getItem() == ModItems.goldCoin) {
	    	this.chestContents[par1] = itemstack;
	    	if(itemstack != null && itemstack.stackSize > this.getInventoryStackLimit()){
	    		itemstack.stackSize = this.getInventoryStackLimit();
	    	}
	    	this.markDirty();
    	}
    	else {
	    	this.chestContents[par1] = itemstack;
	    	if(itemstack != null && itemstack.stackSize > this.getInventoryStackLimit()){
	    		itemstack.stackSize = this.getInventoryStackLimit();
	    	}
	    	this.markDirty();    		
    	}
    }
	
    /**
     * Returns true if automation is allowed to insert the given stack (ignoring stack size) into the given slot.
     */
	// Never gets called! Trying to ensure the player only puts coins in hoard.
	@Override
    public boolean isItemValidForSlot(int slot, ItemStack itemstack) {
    	if (itemstack.getItem() != ModItems.goldCoin)
    		return false;
    	else
    		return true;
    }

    // *** Original stuff from Treasure Chest ***:
    
	@Override
    public void closeInventory(){
		super.closeInventory();
	}

	/**
     * Returns the name of the inventory
     */
	@Override
    public String getInventoryName() {
        return this.hasCustomInventoryName() ? this.customName : TemporaryHoard.textureName;
    }

    /**
     * Returns if the inventory is named
     */
	@Override
    public boolean hasCustomInventoryName()
    {
        return this.customName != null && this.customName.length() > 0;
    }

	@Override
    public void func_145976_a(String p_145976_1_) {
        this.customName = p_145976_1_;
    }

	@Override
    public void readFromNBT(NBTTagCompound p_145839_1_) {
        super.readFromNBT(p_145839_1_);
        
        NBTTagList nbttaglist = p_145839_1_.getTagList("Items", 10);
        this.chestContents = new ItemStack[this.getSizeInventory()];

        if (p_145839_1_.hasKey(TemporaryHoard.textureName, 8)) {
            this.customName = p_145839_1_.getString(TemporaryHoard.textureName);
        }

        for (int i = 0; i < nbttaglist.tagCount(); ++i) {
            NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
            int j = nbttagcompound1.getByte("Slot") & 255;

            if (j >= 0 && j < this.chestContents.length) {
                this.chestContents[j] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }        
        this.owner = p_145839_1_.getString("owner");
    }

	@Override
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
            p_145841_1_.setString(TemporaryHoard.textureName, this.customName);
        }
        
        p_145841_1_.setString("owner", owner);
    }

    private void func_145978_a(TileEntityTemporaryHoard entity, int side) {
        if (entity.isInvalid()) {
            this.adjacentChestChecked = false;
        }
        else if (this.adjacentChestChecked) {
            switch (side) {
            case 0:
                if (this.adjacentChestZPos != entity) {
                    this.adjacentChestChecked = false;
                }
                break;
            case 1:
                if (this.adjacentChestXNeg != entity) {
                    this.adjacentChestChecked = false;
                }
                break;
            case 2:
                if (this.adjacentChestZNeg != entity) {
                    this.adjacentChestChecked = false;
                }
                break;
            case 3:
                if (this.adjacentChestXPos != entity) {
                    this.adjacentChestChecked = false;
                }
            }
        }
    }

    /**
     * Performs the check for adjacent chests to determine if this chest is double or not.
     */
	@Override
    public void checkForAdjacentChests() {
        if (!this.adjacentChestChecked) {
            this.adjacentChestChecked = true;
            this.adjacentChestZNeg = null;
            this.adjacentChestXPos = null;
            this.adjacentChestXNeg = null;
            this.adjacentChestZPos = null;

            if (this.func_145977_a(this.xCoord - 1, this.yCoord, this.zCoord)) {
                this.adjacentChestXNeg = (TileEntityTemporaryHoard)this.worldObj.getTileEntity(this.xCoord - 1, this.yCoord, this.zCoord);
            }
            if (this.func_145977_a(this.xCoord + 1, this.yCoord, this.zCoord)) {
                this.adjacentChestXPos = (TileEntityTemporaryHoard)this.worldObj.getTileEntity(this.xCoord + 1, this.yCoord, this.zCoord);
            }
            if (this.func_145977_a(this.xCoord, this.yCoord, this.zCoord - 1)) {
                this.adjacentChestZNeg = (TileEntityTemporaryHoard)this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord - 1);
            }
            if (this.func_145977_a(this.xCoord, this.yCoord, this.zCoord + 1)) {
                this.adjacentChestZPos = (TileEntityTemporaryHoard)this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord + 1);
            }
            if (this.adjacentChestZNeg != null) {
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
    private boolean func_145977_a(int x, int y, int z) {
        if (this.worldObj == null) {
            return false;
        }
        else {
            Block block = this.worldObj.getBlock(x, y, z);
            return block instanceof TemporaryHoard && ((TemporaryHoard)block).field_149956_a == this.func_145980_j();
        }
    }
    
	@Override
    public int func_145980_j() {
        if (this.cachedChestType == -1) {
            if (this.worldObj == null || !(this.getBlockType() instanceof TemporaryHoard)) {
                return 0;
            }
            this.cachedChestType = ((TemporaryHoard)this.getBlockType()).field_149956_a;
        }
        return this.cachedChestType;
    }
    
	@Override
    public ItemStack getStackInSlot(int slot) {
    	return this.chestContents[slot];
    }
    
	@Override
    public ItemStack decrStackSize(int slot, int maxItems) {
        if (this.chestContents[slot] != null) {
            ItemStack itemstack;

            if (this.chestContents[slot].stackSize <= maxItems) {
                itemstack = this.chestContents[slot];
                this.chestContents[slot] = null;
                this.markDirty();
                return itemstack;
            }
            else {
                itemstack = this.chestContents[slot].splitStack(maxItems);
                if (this.chestContents[slot].stackSize == 0) {
                    this.chestContents[slot] = null;
                }
                this.markDirty();
                return itemstack;
            }
        } else {
            return null;
        }
    }
    
	@Override
    public ItemStack getStackInSlotOnClosing(int slot){
    	if(this.chestContents[slot] != null){
    		ItemStack itemstack = this.chestContents[slot];
    		this.chestContents[slot] = null;
    		return itemstack;
    	}
    	else{
    		return null;
    	}
    }
    
	@Override
    public int getInventoryStackLimit() {
        return 64;
    }

	/**
	 * Creates a new Container for chest interaction
	 * @param inventory
	 * @return
	 */
	public Container getNewInventoryContainer(InventoryPlayer inventory) {
		return new HoardContainer(inventory, getInventory());
	}
	
	/**
	 * @return The inventory to this block
	 */
	public IInventory getInventory(){
		return ((TemporaryHoard)ModBlocks.temporaryHoard).func_149951_m(worldObj, this.xCoord,yCoord,zCoord);
	}

	public void setOwner(String displayName) {
		owner = displayName;
    	this.markDirty();
	}

	public String getOwner() {
		return owner;
	}
}
