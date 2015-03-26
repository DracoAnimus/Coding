package net.wildbill22.draco.common.entities.dragons;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.wildbill22.draco.common.MCACommonLibrary.IMCAnimatedEntity;

public abstract class EntityMCDragon extends EntityLiving implements IMCAnimatedEntity  {
    protected EntityPlayer dragonPlayer;
	private static String dragonName;

	public EntityMCDragon(World world, String name) {
		super(world);
		dragonPlayer = null;
		setDragonName(name);
	}

	public void setPlayer(EntityPlayer player) {
		dragonPlayer = player;
	}

	public static EntityMCDragon EntityMCDragonFactory(String name, World world) {
//		if (name.contentEquals(EntityMCSilverDragon.name))
			return new EntityMCSilverDragon(world);
//		switch (name) {
//		case EntityMCSilverDragon.name:
//			return new EntityMCSilverDragon(world);
//		default:
//			return new EntityMCSilverDragon(world);
//		}
	}
	
	protected boolean isMoving() {
		if (dragonPlayer.posX != dragonPlayer.prevPosX || dragonPlayer.posY != dragonPlayer.prevPosY 
				|| dragonPlayer.posZ != dragonPlayer.prevPosZ)
			return true;
		else
			return false;
	}

	public static String getDragonName() {
		return dragonName;
	}

	public static void setDragonName(String dragonName) {
		EntityMCDragon.dragonName = dragonName;
	}
}
