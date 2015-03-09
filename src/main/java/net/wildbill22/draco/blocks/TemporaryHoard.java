package net.wildbill22.draco.blocks;

import static net.minecraftforge.common.util.ForgeDirection.DOWN;

import java.util.Iterator;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryLargeChest;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.wildbill22.draco.lib.LogHelper;
import net.wildbill22.draco.lib.REFERENCE;
import net.wildbill22.draco.tile_entity.TileEntityTemporaryHoard;

/**
 * 
 * @author WILLIAM
*/ 
public class TemporaryHoard extends BlockChest{
	public static final String textureName = "temporaryHoard";
	public static final String displayName = "Temporary Hoard";
    private final Random rand = new Random();

	protected TemporaryHoard(){
		super(0); // Regular chest (not trapped or Christmas chest)
	}
	
	/**
     * Called when the block is placed in the world.
     */
	@Override
    public void onBlockPlacedBy(World world, int xPos, int yPos, int zPos, EntityLivingBase player, ItemStack chest)
    {
        Block block = world.getBlock(xPos, yPos, zPos - 1);
        Block block1 = world.getBlock(xPos, yPos, zPos + 1);
        Block block2 = world.getBlock(xPos - 1, yPos, zPos);
        Block block3 = world.getBlock(xPos + 1, yPos, zPos);
        byte b0 = 0;
        int l = MathHelper.floor_double((double)(player.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

        if (l == 0)
        {
            b0 = 2;
        }

        if (l == 1)
        {
            b0 = 5;
        }

        if (l == 2)
        {
            b0 = 3;
        }

        if (l == 3)
        {
            b0 = 4;
        }

        // Checks for regular chest, makes it play nice with a regular chest, but a regular chest doesn't have this, so it has problems
        if (!(block instanceof BlockChest || block1 instanceof BlockChest || block2 instanceof BlockChest || block3 instanceof BlockChest))
        {	                	
	        if (block != this && block1 != this && block2 != this && block3 != this)
	        {
	            world.setBlockMetadataWithNotify(xPos, yPos, zPos, b0, 3);
	        }
	        else
	        {
	            if ((block == this || block1 == this) && (b0 == 4 || b0 == 5))
	            {
	                if (block == this)
	                {
	                    world.setBlockMetadataWithNotify(xPos, yPos, zPos - 1, b0, 3);
	                }
	                else
	                {
	                    world.setBlockMetadataWithNotify(xPos, yPos, zPos + 1, b0, 3);
	                }
	
	                world.setBlockMetadataWithNotify(xPos, yPos, zPos, b0, 3);
	            }
	
	            if ((block2 == this || block3 == this) && (b0 == 2 || b0 == 3))
	            {
	                if (block2 == this)
	                {
	                    world.setBlockMetadataWithNotify(xPos - 1, yPos, zPos, b0, 3);
	                }
	                else
	                {
	                    world.setBlockMetadataWithNotify(xPos + 1, yPos, zPos, b0, 3);
	                }
	
	                world.setBlockMetadataWithNotify(xPos, yPos, zPos, b0, 3);
	            }
	        }
	
	        if (chest.hasDisplayName())
	        {
	            ((TileEntityTemporaryHoard)world.getTileEntity(xPos, yPos, zPos)).func_145976_a(chest.getDisplayName());
	        }
        }
    }
	
    /**
     * Checks to see if its valid to put this block at the specified coordinates. Args: world, x, y, z
     * For the Temporary Hoard, we don't want to allow placing a chest if there is one adjacent
     */
	@Override
    public boolean canPlaceBlockAt(World p_149742_1_, int p_149742_2_, int p_149742_3_, int p_149742_4_)
    {
        int l = 0;

        if (p_149742_1_.getBlock(p_149742_2_ - 1, p_149742_3_, p_149742_4_) == this)
        {
            ++l;
        }

        if (p_149742_1_.getBlock(p_149742_2_ + 1, p_149742_3_, p_149742_4_) == this)
        {
            ++l;
        }

        if (p_149742_1_.getBlock(p_149742_2_, p_149742_3_, p_149742_4_ - 1) == this)
        {
            ++l;
        }

        if (p_149742_1_.getBlock(p_149742_2_, p_149742_3_, p_149742_4_ + 1) == this)
        {
            ++l;
        }

        return l > 0 ? false : (this.func_149952_n(p_149742_1_, p_149742_2_ - 1, p_149742_3_, p_149742_4_) ? false : (this.func_149952_n(p_149742_1_, p_149742_2_ + 1, p_149742_3_, p_149742_4_) ? false : (this.func_149952_n(p_149742_1_, p_149742_2_, p_149742_3_, p_149742_4_ - 1) ? false : !this.func_149952_n(p_149742_1_, p_149742_2_, p_149742_3_, p_149742_4_ + 1))));
    }

	// Can't override private, but it replaces the function for the above function from the super class
//	@Override
    private boolean func_149952_n(World p_149952_1_, int p_149952_2_, int p_149952_3_, int p_149952_4_)
    {
        return p_149952_1_.getBlock(p_149952_2_, p_149952_3_, p_149952_4_) != this ? false : (p_149952_1_.getBlock(p_149952_2_ - 1, p_149952_3_, p_149952_4_) == this ? true : (p_149952_1_.getBlock(p_149952_2_ + 1, p_149952_3_, p_149952_4_) == this ? true : (p_149952_1_.getBlock(p_149952_2_, p_149952_3_, p_149952_4_ - 1) == this ? true : p_149952_1_.getBlock(p_149952_2_, p_149952_3_, p_149952_4_ + 1) == this)));
    }

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor Block
     */
	@Override
    public void onNeighborBlockChange(World p_149695_1_, int p_149695_2_, int p_149695_3_, int p_149695_4_, Block p_149695_5_)
    {
        super.onNeighborBlockChange(p_149695_1_, p_149695_2_, p_149695_3_, p_149695_4_, p_149695_5_);
        TileEntityTemporaryHoard tileentitychest = (TileEntityTemporaryHoard)p_149695_1_.getTileEntity(p_149695_2_, p_149695_3_, p_149695_4_);

        if (tileentitychest != null)
        {
        	tileentitychest.updateContainingBlockInfo();
        }
    }

	@Override
    public void breakBlock(World p_149749_1_, int p_149749_2_, int p_149749_3_, int p_149749_4_, Block p_149749_5_, int p_149749_6_)
    {
        TileEntityTemporaryHoard tileentitychest = (TileEntityTemporaryHoard)p_149749_1_.getTileEntity(p_149749_2_, p_149749_3_, p_149749_4_);

        if (tileentitychest != null)
        {
            for (int i1 = 0; i1 < tileentitychest.getSizeInventory(); ++i1)
            {
                ItemStack itemstack = tileentitychest.getStackInSlot(i1);

                if (itemstack != null)
                {
                    float f = this.rand.nextFloat() * 0.8F + 0.1F;
                    float f1 = this.rand.nextFloat() * 0.8F + 0.1F;
                    EntityItem entityitem;

                    for (float f2 = this.rand.nextFloat() * 0.8F + 0.1F; itemstack.stackSize > 0; p_149749_1_.spawnEntityInWorld(entityitem))
                    {
                        int j1 = this.rand.nextInt(21) + 10;

                        if (j1 > itemstack.stackSize)
                        {
                            j1 = itemstack.stackSize;
                        }

                        itemstack.stackSize -= j1;
                        entityitem = new EntityItem(p_149749_1_, (double)((float)p_149749_2_ + f), (double)((float)p_149749_3_ + f1), (double)((float)p_149749_4_ + f2), new ItemStack(itemstack.getItem(), j1, itemstack.getItemDamage()));
                        float f3 = 0.05F;
                        entityitem.motionX = (double)((float)this.rand.nextGaussian() * f3);
                        entityitem.motionY = (double)((float)this.rand.nextGaussian() * f3 + 0.2F);
                        entityitem.motionZ = (double)((float)this.rand.nextGaussian() * f3);

                        if (itemstack.hasTagCompound())
                        {
                            entityitem.getEntityItem().setTagCompound((NBTTagCompound)itemstack.getTagCompound().copy());
                        }
                    }
                }
            }

            p_149749_1_.func_147453_f(p_149749_2_, p_149749_3_, p_149749_4_, p_149749_5_);
        }

        super.breakBlock(p_149749_1_, p_149749_2_, p_149749_3_, p_149749_4_, p_149749_5_, p_149749_6_);
    }

	@Override
    public IInventory func_149951_m(World world, int x, int y, int z)
    {
        Object object = (TileEntityTemporaryHoard)world.getTileEntity(x, y, z);

        if (object == null)
        {
            return null;
        }
        else if (world.isSideSolid(x, y + 1, z, DOWN))
        {
            return null;
        }
        else if (func_149953_o(world, x, y, z))
        {
            return null;
        }
        else if (world.getBlock(x - 1, y, z) == this && (world.isSideSolid(x - 1, y + 1, z, DOWN) || func_149953_o(world, x - 1, y, z)))
        {
            return null;
        }
        else if (world.getBlock(x + 1, y, z) == this && (world.isSideSolid(x + 1, y + 1, z, DOWN) || func_149953_o(world, x + 1, y, z)))
        {
            return null;
        }
        else if (world.getBlock(x, y, z - 1) == this && (world.isSideSolid(x, y + 1, z - 1, DOWN) || func_149953_o(world, x, y, z - 1)))
        {
            return null;
        }
        else if (world.getBlock(x, y, z + 1) == this && (world.isSideSolid(x, y + 1, z + 1, DOWN) || func_149953_o(world, x, y, z + 1)))
        {
            return null;
        }
        else
        {
            if (world.getBlock(x - 1, y, z) == this)
            {
                object = new InventoryLargeChest("Large Temporary Hoard", (TileEntityTemporaryHoard)world.getTileEntity(x - 1, y, z), (IInventory)object);
            }

            if (world.getBlock(x + 1, y, z) == this)
            {
                object = new InventoryLargeChest("Large Temporary Hoard", (IInventory)object, (TileEntityTemporaryHoard)world.getTileEntity(x + 1, y, z));
            }

            if (world.getBlock(x, y, z - 1) == this)
            {
                object = new InventoryLargeChest("Large Temporary Hoard", (TileEntityTemporaryHoard)world.getTileEntity(x, y, z - 1), (IInventory)object);
            }

            if (world.getBlock(x, y, z + 1) == this)
            {
                object = new InventoryLargeChest("Large Temporary Hoard", (IInventory)object, (TileEntityTemporaryHoard)world.getTileEntity(x, y, z + 1));
            }

            return (IInventory)object;
        }
    }

	//@Override
    private static boolean func_149953_o(World p_149953_0_, int p_149953_1_, int p_149953_2_, int p_149953_3_)
    {
        Iterator<?> iterator = p_149953_0_.getEntitiesWithinAABB(EntityOcelot.class, AxisAlignedBB.getBoundingBox((double)p_149953_1_, (double)(p_149953_2_ + 1), (double)p_149953_3_, (double)(p_149953_1_ + 1), (double)(p_149953_2_ + 2), (double)(p_149953_3_ + 1))).iterator();
        EntityOcelot entityocelot;

        do
        {
            if (!iterator.hasNext())
            {
                return false;
            }

            Entity entity = (Entity)iterator.next();
            entityocelot = (EntityOcelot)entity;
        }
        while (!entityocelot.isSitting());

        return true;
    }

    /**
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     */
	@Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_)
    {
        TileEntityTemporaryHoard tileentitychest = new TileEntityTemporaryHoard();
        return tileentitychest;
    }

    @SideOnly(Side.CLIENT)
	@Override
    public void registerBlockIcons(IIconRegister p_149651_1_)
    {
        this.blockIcon = p_149651_1_.registerIcon(REFERENCE.Texture_Path + TemporaryHoard.textureName);
        LogHelper.info("Set chest icon texture to temporaryHoard!");
    }    
}
