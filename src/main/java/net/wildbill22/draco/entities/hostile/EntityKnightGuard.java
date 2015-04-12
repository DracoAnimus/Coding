package net.wildbill22.draco.entities.hostile;

import net.minecraft.world.World;

public class EntityKnightGuard extends EntityGuard {
	public static final String name = "knightGuard";

	public EntityKnightGuard(World world){
		super(world);
		this.type = GuardType.KNIGHT;
		setGuardWeaponType();
	}
	
	@Override
	protected void dropFewItems(boolean par1, int par2)	{
		super.dropFewItems(par1, par2);
		// TODO: Anything else?
//		if (this.rand.nextInt(5) == 0) {
//			this.dropItem(ModWeapons.battleAxe, 1);
//		}
	}	
}
