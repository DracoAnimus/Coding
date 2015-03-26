package net.wildbill22.draco.common.entities.dragons;

import java.util.Random;

import net.wildbill22.draco.common.MCACommonLibrary.animation.AnimationHandler;
import net.wildbill22.draco.common.animations.SilverDragon.AnimationHandlerSilverDragon;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityMCSilverDragon extends EntityMCDragon {
	protected AnimationHandler animHandler = new AnimationHandlerSilverDragon(this);
	public static final String name = "mcSilverDragon";
	private int idleInterval;
	private Random rand;

	public EntityMCSilverDragon(World par1World) {
		super(par1World, name);
		idleInterval = 0;
		rand = new Random();
	}

	@Override
	protected void entityInit() {
		super.entityInit();
	}
	
	@Override
	public AnimationHandler getAnimationHandler() {
		return animHandler;
	}

//	public void setPlayerAnimationsToRun() {
	public void setPlayerAnimationsToRun(float movement) {
		if (dragonPlayer == null)
			return;
//		if (this.isMoving()) {
		if (movement >= 0.01F) {
			if (!this.getAnimationHandler().isAnimationActive("Idle")) {
				this.getAnimationHandler().stopAnimation("Idle");
			}
			if (this.dragonPlayer.capabilities.isFlying) {
				if(!this.getAnimationHandler().isAnimationActive("Fly")) {
				    this.getAnimationHandler().activateAnimation("Fly", 0);
				}
			}
			else {
				if (!this.getAnimationHandler().isAnimationActive("Walk")) {
					this.getAnimationHandler().activateAnimation("Walk", 0);
				}
			}
		}
		// idle
		else {
			if (this.getAnimationHandler().isAnimationActive("Fly")) {
				this.getAnimationHandler().stopAnimation("Fly");
			}
			if (this.getAnimationHandler().isAnimationActive("Walk")) {
				this.getAnimationHandler().stopAnimation("Walk");
			}
			// Randomly every 5 - 15 seconds, play the idle animation
			if (idleInterval > (rand.nextInt(200) + 300)) {
				if (!this.getAnimationHandler().isAnimationActive("Idle")) {
					this.getAnimationHandler().activateAnimation("Idle", 0);
				}
				idleInterval = 0;
			}
			idleInterval++; 
		}
	}
	
	@Override
	public void onUpdate() {
		super.onUpdate();
    }

	@Override
	public void readEntityFromNBT(NBTTagCompound nbttagcompound) {
		super.readEntityFromNBT(nbttagcompound);
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbttagcompound) {
		super.writeEntityToNBT(nbttagcompound);
	}
}