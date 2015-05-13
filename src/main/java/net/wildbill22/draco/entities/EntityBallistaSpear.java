package net.wildbill22.draco.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.wildbill22.draco.lib.BALANCE;
import net.wildbill22.draco.lib.LogHelper;

public class EntityBallistaSpear extends EntitySpear {

	public EntityBallistaSpear(World world, double p_i1778_2_, double p_i1778_4_, double p_i1778_6_) {
		super(world, p_i1778_2_, p_i1778_4_, p_i1778_6_);
	}
	
	public EntityBallistaSpear(World par1World, EntityLivingBase par2EntityLivingBase) {
		super(par1World, par2EntityLivingBase);
	}
	
	public EntityBallistaSpear(World par1World) {
		super(par1World);
	}
	
	// Used by Ballista
    public EntityBallistaSpear(World world, EntityLivingBase attacker, EntityLivingBase target, float p_i1755_4_, float p_i1755_5_) {
    	super(world, attacker, target, p_i1755_4_, p_i1755_5_);
    }

	@Override
	protected void onImpact(MovingObjectPosition movObjPos) {
		if (!this.worldObj.isRemote)
			LogHelper.info("EntityBallistaSpear landed!");
			
		if (movObjPos != null) {
			if (movObjPos.entityHit instanceof Entity) {
				movObjPos.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), 
						(float) BALANCE.MOBPROP.BALLISTA_SPEAR_DAMAGE);
			}
			else {
				// Do something for instance if it hits a tree
				return;  // no crit and not dead
			}
		}
		
		for (int i = 0; i < 4; ++i)	{
			this.worldObj.spawnParticle("crit", this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
		}
		// Get rid of the used spear
		if (!this.worldObj.isRemote)
			this.setDead();
	}	
}
