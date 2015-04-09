package net.wildbill22.draco.entities.ai;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.MathHelper;

public class EntityAISpearAttack  extends EntityAIBase {
    /** The entity the AI instance has been applied to */
    private final EntityLiving entityHost;
    /** The entity (as a RangedAttackMob) the AI instance has been applied to. */
    private final IRangedAttackMob rangedAttackEntityHost;
    private EntityLivingBase attackTarget;
    /**
     * A decrementing tick that spawns a ranged attack once this value reaches 0. It is then set back to the
     * maxRangedAttackTime.
     */
    private int rangedAttackTime;
    private double entityMoveSpeed;
    private int field_75318_f;
    private int field_96561_g;
    /** The maximum time the AI has to wait before performing another ranged attack. */
    private int maxRangedAttackTime;
    private float field_96562_i;
    private float field_82642_h;

    public EntityAISpearAttack(IRangedAttackMob p_i1649_1_, double p_i1649_2_, int p_i1649_4_, float p_i1649_5_)
    {
        this(p_i1649_1_, p_i1649_2_, p_i1649_4_, p_i1649_4_, p_i1649_5_);
    }

    public EntityAISpearAttack(IRangedAttackMob entityAttacker, double targetSpeed, int p_i1650_4_, 
    		int attackWaitTime, float p_i1650_6_) {
        this.rangedAttackTime = -1;

        if (!(entityAttacker instanceof EntityLivingBase))
        {
            throw new IllegalArgumentException("ArrowAttackGoal requires Mob implements RangedAttackMob");
        }
        else
        {
            this.rangedAttackEntityHost = entityAttacker;
            this.entityHost = (EntityLiving)entityAttacker;
            this.entityMoveSpeed = targetSpeed;
            this.field_96561_g = p_i1650_4_;
            this.maxRangedAttackTime = attackWaitTime;
            this.field_96562_i = p_i1650_6_;
            this.field_82642_h = p_i1650_6_ * p_i1650_6_;
            this.setMutexBits(3);
        }
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
	@Override
	public boolean shouldExecute() {
        EntityLivingBase entitylivingbase = this.entityHost.getAttackTarget();
        if (entitylivingbase == null) {
            return false;
        }
        else {
            this.attackTarget = entitylivingbase;
            return true;
        }
	}

	/**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean continueExecuting() {
        return this.shouldExecute() || !this.entityHost.getNavigator().noPath();
    }

    /**
     * Resets the task
     */
    public void resetTask() {
        this.attackTarget = null;
        this.field_75318_f = 0;
        this.rangedAttackTime = -1;
    }

    /**
     * Updates the task
     */
    public void updateTask() {
        double d0 = this.entityHost.getDistanceSq(this.attackTarget.posX, this.attackTarget.boundingBox.minY, this.attackTarget.posZ);
        boolean canSee = this.entityHost.getEntitySenses().canSee(this.attackTarget);
        if (canSee) {
            ++this.field_75318_f;
        }
        else {
            this.field_75318_f = 0;
        }

        if (d0 <= (double)this.field_82642_h && this.field_75318_f >= 20) {
            this.entityHost.getNavigator().clearPathEntity();
        }
        else {
            this.entityHost.getNavigator().tryMoveToEntityLiving(this.attackTarget, this.entityMoveSpeed);
        }

        this.entityHost.getLookHelper().setLookPositionWithEntity(this.attackTarget, 30.0F, 30.0F);
        float f;
        if (--this.rangedAttackTime == 0) {
            if (d0 > (double)this.field_82642_h || !canSee) {
                return;
            }
            f = MathHelper.sqrt_double(d0) / this.field_96562_i;
            float f1 = f;
            if (f < 0.1F) {
                f1 = 0.1F;
            }
            if (f1 > 1.0F) {
                f1 = 1.0F;
            }
            this.rangedAttackEntityHost.attackEntityWithRangedAttack(this.attackTarget, f1);
            this.rangedAttackTime = MathHelper.floor_float(f * (float)(this.maxRangedAttackTime - this.field_96561_g) + (float)this.field_96561_g);
        }
        else if (this.rangedAttackTime < 0) {
            f = MathHelper.sqrt_double(d0) / this.field_96562_i;
            this.rangedAttackTime = MathHelper.floor_float(f * (float)(this.maxRangedAttackTime - this.field_96561_g) + (float)this.field_96561_g);
        }
    }
}
