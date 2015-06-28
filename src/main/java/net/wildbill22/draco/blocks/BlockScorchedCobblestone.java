package net.wildbill22.draco.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.wildbill22.draco.entities.player.DragonPlayer;

public class BlockScorchedCobblestone extends ModBlocks {
	public static final String name = "scorchedCobblestone";

	public BlockScorchedCobblestone() {
		super(name, name, Material.rock);
//		setStepSound(soundTypeStone);
		setHardness(50.0F);             // Same as obsidian
		setResistance(2000.0F);         // Same as obsidian
		setHarvestLevel("pickaxe", 3);  // Needs diamond axe
	}
	
    /**
     * Called whenever an entity is walking on top of this block. Args: world, x, y, z, entity
     */
    public void onEntityWalking(World world, int x, int y, int z, Entity entity) {
		if (entity instanceof EntityPlayer && !DragonPlayer.get((EntityPlayer) entity).isDragon())
			entity.attackEntityFrom(DamageSource.generic, 2.0F);
    }

    /**
     * Called when a player hits the block. Args: world, x, y, z, player
     */
	@Override
    public void onBlockClicked(World world, int x, int y, int z, EntityPlayer player) {
		if (!DragonPlayer.get(player).isDragon())
			player.attackEntityFrom(DamageSource.generic, 2.0F);	
	}
}
