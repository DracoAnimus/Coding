package net.wildbill22.draco.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockWall;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.DamageSource;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.wildbill22.draco.Creative_Tab;

// TODO: How to make this catch fire spontaneously and not get burned up
public class BlockScorchedWall extends BlockWall {
	public static final String name = "scorchedWall";

	public BlockScorchedWall() {
		super(Blocks.cobblestone_wall);
		setHardness(50.0F);                     // Same as obsidian
		setResistance(2000.0F);                 // Same as obsidian
		setHarvestLevel("pickaxe", 3);          // Needs diamond axe
		setCreativeTab(Creative_Tab.TabDraco_Animus);
		setBlockName(name);

//		Blocks.fire.setFireInfo(this, 60, 100); // Same as grass
	}
	
	/**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor Block
     */
//    public void onNeighborBlockChange(World p_149695_1_, int p_149695_2_, int p_149695_3_, int p_149695_4_, Block p_149695_5_) {
//    	
//    }

    /**
     * Called whenever an entity is walking on top of this block. Args: world, x, y, z, entity
     */
//    public void onEntityWalking(World world, int x, int y, int z, Entity entity) {}

    /**
     * Called when a player hits the block. Args: world, x, y, z, player
     */
	@Override
    public void onBlockClicked(World world, int x, int y, int z, EntityPlayer player) {
		player.attackEntityFrom(DamageSource.generic, 2.0F);	
	}

    /**
     * Determines if this block should set fire and deal fire damage
     * to entities coming into contact with it.
     *
     * @param world The current world
     * @param x X Position
     * @param y Y position
     * @param z Z position
     * @return True if the block should deal damage
     */
    public boolean isBurning(IBlockAccess world, int x, int y, int z) {
        return true;  // Added to burn players and entities
    }

    /**
     * Chance that fire will spread and consume this block.
     * 300 being a 100% chance, 0, being a 0% chance.
     *
     * @param world The current world
     * @param x The blocks X position
     * @param y The blocks Y position
     * @param z The blocks Z position
     * @param face The face that the fire is coming from
     * @return A number ranging from 0 to 300 relating used to determine if the block will be consumed by fire
     */
    // Hmm, if set to 0, will not catch fire. If set to 100, it gets consumed
    public int getFlammability(IBlockAccess world, int x, int y, int z, ForgeDirection face) {
    	if (face == ForgeDirection.UP)
    		return 0; // Tall grass is 100
    	else
    		return 0; // Tall grass is 100    		
    }

    /**
     * Called when fire is updating, checks if a block face can catch fire.
     *
     *
     * @param world The current world
     * @param x The blocks X position
     * @param y The blocks Y position
     * @param z The blocks Z position
     * @param face The face that the fire is coming from
     * @return True if the face can be on fire, false otherwise.
     */
//    public boolean isFlammable(IBlockAccess world, int x, int y, int z, ForgeDirection face)
//    {
//        return getFlammability(world, x, y, z, face) > 0;
//    }

    /**
     * Called when fire is updating on a neighbor block.
     * The higher the number returned, the faster fire will spread around this block.
     *
     * @param world The current world
     * @param x The blocks X position
     * @param y The blocks Y position
     * @param z The blocks Z position
     * @param face The face that the fire is coming from
     * @return A number that is used to determine the speed of fire growth around the block
     */
    public int getFireSpreadSpeed(IBlockAccess world, int x, int y, int z, ForgeDirection face) {
        return 30;   // Tall grass is 60
    }

    /**
     * Currently only called by fire when it is on top of this block.
     * Returning true will prevent the fire from naturally dying during updating.
     * Also prevents firing from dying from rain.
     *
     * @param world The current world
     * @param x The blocks X position
     * @param y The blocks Y position
     * @param z The blocks Z position
     * @param metadata The blocks current metadata
     * @param side The face that the fire is coming from
     * @return True if this block sustains fire, meaning it will never go out.
     */
    public boolean isFireSource(World world, int x, int y, int z, ForgeDirection side) {
    	if (side == ForgeDirection.UP)
    		return true;
    	else
    		return false;
    }

    /**
     * Spawn particles for when the block is destroyed. Due to the nature
     * of how this is invoked, the x/y/z locations are not always guaranteed
     * to host your block. So be sure to do proper sanity checks before assuming
     * that the location is this block.
     *
     * @param world The current world
     * @param x X position to spawn the particle
     * @param y Y position to spawn the particle
     * @param z Z position to spawn the particle
     * @param meta The metadata for the block before it was destroyed.
     * @param effectRenderer A reference to the current effect renderer.
     * @return True to prevent vanilla break particles from spawning.
     */
    @SideOnly(Side.CLIENT)
    public boolean addDestroyEffects(World world, int x, int y, int z, int meta, EffectRenderer effectRenderer)
    {
        return false;
    }

    /**
     * Determines if a torch can be placed on the top surface of this block.
     * Useful for creating your own block that torches can be on, such as fences.
     *
     * @param world The current world
     * @param x X Position
     * @param y Y Position
     * @param z Z Position
     * @return True to allow the torch to be placed
     */
    public boolean canPlaceTorchOnTop(World world, int x, int y, int z) {
    	return true;
    }

}
