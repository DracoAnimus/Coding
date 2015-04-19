package net.wildbill22.draco.entities.ai;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAITarget;
import net.wildbill22.draco.entities.hostile.EntityGuard;

public class EntityAISummonGuards extends EntityAITarget {
    private final Class targetClass;
    /** Instance of EntityAISummonGuards.Sorter */
    private final EntityAISummonGuards.Sorter theNearestAttackableTargetSorter;
    private List targetList;
    private List guardList;
    private int numGuardsToSpawn;
    
	@SuppressWarnings("rawtypes")
	public EntityAISummonGuards(EntityCreature taskOwner, Class targetClass, boolean shouldCheckSight, boolean nearbyOnly, int numGuardsToSpawn) {
		super(taskOwner, shouldCheckSight, nearbyOnly);
		this.targetClass = targetClass;
        this.theNearestAttackableTargetSorter = new EntityAISummonGuards.Sorter(taskOwner);
        this.setMutexBits(1); // ???
        this.numGuardsToSpawn = numGuardsToSpawn;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean shouldExecute() {
        double d0 = this.getTargetDistance();
        targetList = this.taskOwner.worldObj.selectEntitiesWithinAABB(this.targetClass, this.taskOwner.boundingBox.expand(d0, 4.0D, d0), null);
        if (targetList.isEmpty())
            return false;

        guardList = this.taskOwner.worldObj.selectEntitiesWithinAABB(EntityGuard.class, this.taskOwner.boundingBox.expand(d0, 4.0D, d0), null);
        if (guardList.size() >= numGuardsToSpawn)
            return false;

        // Sort them to closest first
        Collections.sort(targetList, this.theNearestAttackableTargetSorter);
        numGuardsToSpawn = numGuardsToSpawn - guardList.size();

        return true;
	}
    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting() {
    	summonGuards();
        super.startExecuting();
    }

    // Summons Guards near the target
    private void summonGuards() {
    	int spawnedGuards = 0;
		int r = 5;
    	for (int trys = 0; spawnedGuards < targetList.size() && spawnedGuards < numGuardsToSpawn && trys < 10; trys++) {
    		EntityLivingBase target = (EntityLivingBase)targetList.get(spawnedGuards);
			int x1 = (int) target.posX + this.taskOwner.worldObj.rand.nextInt((int) (1.2 * r)) - (int) (0.6 * r);
			int z1 = (int) target.posZ + this.taskOwner.worldObj.rand.nextInt((int) (1.2 * r)) - (int) (0.6 * r);
			int y1 = this.taskOwner.worldObj.getHeightValue(x1, z1);
			EntityGuard guard = EntityGuard.createGuardTypePerBiome(this.taskOwner.worldObj, x1, z1);
			if (guard == null)
				return;
			guard.setLocationAndAngles(x1, y1, z1, 0.0F, 0.0F);
//			guard.setFoundHome();
			if (guard.getCanSpawnHere()) {
//				guard.setHomeArea(v.getCenter().posX, v.getCenter().posY, v.getCenter().posZ, r);
				this.taskOwner.worldObj.spawnEntityInWorld(guard);
				spawnedGuards++;
			} else {
				guard.setDead();
			}
    	}
	}

	public static class Sorter implements Comparator {
    	private final Entity theEntity;

    	public Sorter(Entity p_i1662_1_) {
            this.theEntity = p_i1662_1_;
        }

        public int compare(Entity p_compare_1_, Entity p_compare_2_) {
            double d0 = this.theEntity.getDistanceSqToEntity(p_compare_1_);
            double d1 = this.theEntity.getDistanceSqToEntity(p_compare_2_);
            return d0 < d1 ? -1 : (d0 > d1 ? 1 : 0);
        }

        public int compare(Object p_compare_1_, Object p_compare_2_) {
            return this.compare((Entity)p_compare_1_, (Entity)p_compare_2_);
        }
    }
}
