package net.wildbill22.draco.entities.ai;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAITarget;
import net.minecraft.village.Village;
import net.wildbill22.draco.entities.hostile.EntityGuard;

public class EntityAIDefendVillage extends EntityAITarget {

	private EntityGuard guard;
	/**
	 * The aggressor of the iron golem's village which is now the golem's attack
	 * target.
	 */
	EntityLivingBase villageAgressorTarget;

	public EntityAIDefendVillage(EntityGuard guard) {
		super(guard, false, true);
		this.guard = guard;
		this.setMutexBits(1);
	}

	@Override
	public boolean shouldExecute() {
		Village v = guard.getHomeVillage();
		if (v == null)
			return false;

		this.villageAgressorTarget = v.findNearestVillageAggressor(guard);

		// TODO: Narrow this down?
		if (this.isSuitableTarget(villageAgressorTarget, false)) {
			return true;
		}
		return false;
	}

	@Override
	public void startExecuting() {
		this.guard.setAttackTarget(this.villageAgressorTarget);
		super.startExecuting();
	}
}
