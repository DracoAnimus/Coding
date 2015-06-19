package net.wildbill22.draco.blocks;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockDragonEgg;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.wildbill22.draco.Creative_Tab;
import net.wildbill22.draco.lib.REFERENCE;

public class BlockDracoEgg extends BlockDragonEgg {
	Item itemDropped;

	public BlockDracoEgg(String name, Item itemDropped) {
		setBlockName(name);
		setBlockTextureName(REFERENCE.MODID + ":" + name);
		setCreativeTab(Creative_Tab.TabDraco_Animus);
		setHardness(1.0F);
		setResistance(3.0F);
		setHarvestLevel("pickaxe", 1);
		this.itemDropped = itemDropped;
	}
	
	@Override
    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)  {
        return itemDropped;
    }
	
	// Stuff to not make it do what a BlockDragonEgg does!
    /**
     * Ticks the block if it's been scheduled
     */
	@Override
    public void updateTick(World p_149674_1_, int p_149674_2_, int p_149674_3_, int p_149674_4_, Random p_149674_5_) {}

    /**
     * Called upon block activation (right click on the block.)
     */
	@Override
    public boolean onBlockActivated(World p_149727_1_, int p_149727_2_, int p_149727_3_, int p_149727_4_, EntityPlayer p_149727_5_, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_)  {
        return false;
    }
	
    /**
     * Called when a player hits the block. Args: world, x, y, z, player
     */
	@Override
    public void onBlockClicked(World p_149699_1_, int p_149699_2_, int p_149699_3_, int p_149699_4_, EntityPlayer p_149699_5_) {}

    /**
     * Gets an item for the block being called on. Args: world, x, y, z
     */
    @SideOnly(Side.CLIENT)
	@Override
    public Item getItem(World p_149694_1_, int p_149694_2_, int p_149694_3_, int p_149694_4_)
    {
        return Item.getItemFromBlock(this);
    }
}
