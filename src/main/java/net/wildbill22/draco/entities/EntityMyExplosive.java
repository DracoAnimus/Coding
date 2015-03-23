package net.wildbill22.draco.entities;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.wildbill22.draco.entities.player.DragonPlayer;
import net.wildbill22.draco.items.ItemMyExplosive;
import net.wildbill22.draco.items.ItemMyFireball;
import net.wildbill22.draco.lib.LogHelper;

public class EntityMyExplosive extends EntityThrowable {
	EntityPlayer player;

	public EntityMyExplosive(World world, double p_i1778_2_, double p_i1778_4_, double p_i1778_6_) {
		super(world, p_i1778_2_, p_i1778_4_, p_i1778_6_);
	}
	
	public EntityMyExplosive(World par1World, EntityLivingBase par2EntityLivingBase) {
		super(par1World, par2EntityLivingBase);
		if (par2EntityLivingBase instanceof EntityPlayer)
			player = (EntityPlayer) par2EntityLivingBase;
		else
			player = null;
	}
	
	public EntityMyExplosive(World par1World) {
		super(par1World);
	}	

	@Override
	protected void onImpact(MovingObjectPosition movObjPos) {
		if (!this.worldObj.isRemote)
			LogHelper.info("EntityMyExplosive landed!");

		doExplosionDamage(movObjPos);
		
		// Get rid of the used fireball
		if (!this.worldObj.isRemote)
			this.setDead();
	}
	
	private void doExplosionDamage(MovingObjectPosition movObjPos) {
		//If this hit's a block, continue
		if (movObjPos.typeOfHit == MovingObjectType.BLOCK)
		{
			// You might be wondering what all these case and break are These are use to switch the number mop.sideHit
			// Example: If mop.sideHit == 3 whatever is in case 3 Happens!
			switch(movObjPos.sideHit)
			{
				case 0: //BOTTOM
					movObjPos.blockY--;
					break;
				case 1: //TOP
					movObjPos.blockY++;
					break;
				case 2: //EAST
					movObjPos.blockZ--;
					break;
				case 3: //WEST
					movObjPos.blockZ++;
					break;
				case 4: //NORTH
					movObjPos.blockX--;
					break;
				case 5: //SOUTH
					movObjPos.blockX++;
					break;
			}
		}
		// This method creates the explosion! It uses the entity (Can be null) the three coordinates, the strength
		// and if it should spawn smoke particles around after exploding, the last parameter
		// is if it should set neighboring blocks on fire
		float explosionSize;
		if (player != null)
			explosionSize = ItemMyExplosive.explosionSize * ( 1 + DragonPlayer.get(player).getLevel() / 5);
		else
			explosionSize = ItemMyExplosive.explosionSize;
		Explosion explosion = this.worldObj.newExplosion(this, movObjPos.blockX, movObjPos.blockY, movObjPos.blockZ, explosionSize, true, true);
		if (movObjPos.entityHit != null) {
			movObjPos.entityHit.attackEntityFrom(DamageSource.setExplosionSource(explosion), explosionSize);
			if (!movObjPos.entityHit.isImmuneToFire()) {
				int fireSeconds;
				if (player != null)
					fireSeconds = ItemMyFireball.fireSeconds * (1 + DragonPlayer.get(player).getLevel() / 5);
				else
					fireSeconds = ItemMyFireball.fireSeconds;
				movObjPos.entityHit.setFire(fireSeconds);
			}
		}
	}
}
