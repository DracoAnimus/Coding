package net.wildbill22.draco.entities.ai;

import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.util.Vec3;
import net.wildbill22.draco.entities.dragons.EntityCreeperDragon;

import java.util.List;

/**
 * Created by Darkona on 12/10/2014 orginally, adapted by wildbill22 for draco.
 */
public class EntityAIAvoidDragon extends EntityAIBase
{
    public final IEntitySelector field_98218_a = new AvoidEntitySelector(this);

    /**
     * The entity we are attached to
     */
    public EntityCreature theEntity;
    private double farSpeed;
    private double nearSpeed;
    private Entity closestLivingEntity;
    private float distanceFromEntity;

    /**
     * The PathEntity
    */
    private PathEntity entityPathEntity;

    /**
     * The PathNavigate of entity
     */
    private PathNavigate entityPathNavigate;

    /**
     * The class of the entity we should avoid
     */
    @SuppressWarnings("rawtypes")
	private Class targetEntityClass;

    @SuppressWarnings("rawtypes")
	public EntityAIAvoidDragon(EntityCreature par1EntityCreature, Class targetClass, float distance, double par4, double par6)
    {
        this.theEntity = par1EntityCreature;
        this.targetEntityClass = targetClass;
        this.distanceFromEntity = distance;
        this.farSpeed = par4;
        this.nearSpeed = par6;
        this.entityPathNavigate = par1EntityCreature.getNavigator();
        this.setMutexBits(1);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        if (this.targetEntityClass == EntityCreeperDragon.class)
        {
            List<?> list = this.theEntity.worldObj.selectEntitiesWithinAABB(this.targetEntityClass, this.theEntity.boundingBox.expand(this.distanceFromEntity, 3.0D, this.distanceFromEntity), this.field_98218_a);

            if (list.isEmpty()) {
                return false;
            }
            for (Object dragon : list) {
            	if (dragon instanceof EntityCreeperDragon && ((EntityCreeperDragon) dragon).isTamed())
            		this.closestLivingEntity = (Entity) dragon;
            }
            if (this.closestLivingEntity == null) {
                return false;
            }

            Vec3 vec3 = RandomPositionGenerator.findRandomTargetBlockAwayFrom(this.theEntity, 16, 7, Vec3.createVectorHelper(this.closestLivingEntity.posX, this.closestLivingEntity.posY, this.closestLivingEntity.posZ));

            if (vec3 == null) {
                return false;
            } else if (this.closestLivingEntity.getDistanceSq(vec3.xCoord, vec3.yCoord, vec3.zCoord) < this.closestLivingEntity.getDistanceSqToEntity(this.theEntity)) {
                return false;
            } else {
                this.entityPathEntity = this.entityPathNavigate.getPathToXYZ(vec3.xCoord, vec3.yCoord, vec3.zCoord);
                return this.entityPathEntity != null && this.entityPathEntity.isDestinationSame(vec3);
            }
        }
        return false;
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean continueExecuting()
    {
        return !this.entityPathNavigate.noPath();
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting()
    {
        this.entityPathNavigate.setPath(this.entityPathEntity, this.farSpeed);
    }

    /**
     * Resets the task
     */
    public void resetTask()
    {
        this.closestLivingEntity = null;
    }

    /**
     * Updates the task
     */
    public void updateTask()
    {
        if (this.theEntity.getDistanceSqToEntity(this.closestLivingEntity) < 49.0D)
        {
            this.theEntity.getNavigator().setSpeed(this.nearSpeed);
        } else
        {
            this.theEntity.getNavigator().setSpeed(this.farSpeed);
        }
    }

    static EntityCreature func_98217_a(EntityAIAvoidDragon par0EntityAIAvoidEntity)
    {
        return par0EntityAIAvoidEntity.theEntity;
    }
}
