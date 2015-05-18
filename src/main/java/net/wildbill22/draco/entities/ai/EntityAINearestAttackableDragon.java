package net.wildbill22.draco.entities.ai;

import java.util.Collections;
import java.util.List;

import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAITarget;
import net.minecraft.entity.player.EntityPlayer;
import net.wildbill22.draco.entities.player.DragonPlayer;

public class EntityAINearestAttackableDragon extends EntityAITarget {

    @SuppressWarnings("rawtypes")
	private final Class targetClass;
    private final int targetChance;
    /** Instance of EntityAINearestAttackableTargetSorter. */
    private final EntityAINearestAttackableTarget.Sorter theNearestAttackableTargetSorter;
    /**
     * This filter is applied to the Entity search.  Only matching entities will be targetted.  (null -> no
     * restrictions)
     */
    private final IEntitySelector targetEntitySelector;
    private EntityLivingBase targetEntity;
//    private static final String __OBFID = "CL_00001620";

    public EntityAINearestAttackableDragon(EntityCreature p_i1663_1_, int p_i1663_3_, boolean p_i1663_4_) {
        this(p_i1663_1_, p_i1663_3_, p_i1663_4_, false);
    }

    public EntityAINearestAttackableDragon(EntityCreature p_i1664_1_, int p_i1664_3_, boolean p_i1664_4_, boolean p_i1664_5_) {
        this(p_i1664_1_, p_i1664_3_, p_i1664_4_, p_i1664_5_, (IEntitySelector)null);
    }

    public EntityAINearestAttackableDragon(EntityCreature attacker, int p_i1665_3_, boolean p_i1665_4_, boolean p_i1665_5_, final IEntitySelector p_i1665_6_) {
        super(attacker, p_i1665_4_, p_i1665_5_);
        this.targetClass = EntityPlayer.class;
        this.targetChance = p_i1665_3_;
        this.theNearestAttackableTargetSorter = new EntityAINearestAttackableTarget.Sorter(attacker);
        this.setMutexBits(1);
        this.targetEntitySelector = new IEntitySelector() {
//            private static final String __OBFID = "CL_00001621";
            /**
             * Return whether the specified entity is applicable to this filter.
             */
            public boolean isEntityApplicable(Entity p_82704_1_) {
                return !(p_82704_1_ instanceof EntityLivingBase) ? false : 
                	(p_i1665_6_ != null && !p_i1665_6_.isEntityApplicable(p_82704_1_) ? false : 
                		EntityAINearestAttackableDragon.this.isSuitableTarget((EntityLivingBase)p_82704_1_, false));
            }
        };
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    @SuppressWarnings("unchecked")
	public boolean shouldExecute() {
        if (this.targetChance > 0 && this.taskOwner.getRNG().nextInt(this.targetChance) != 0) {
            return false;
        }
        else {
            double d0 = this.getTargetDistance();
            List<?> list = this.taskOwner.worldObj.selectEntitiesWithinAABB(this.targetClass, this.taskOwner.boundingBox.expand(d0, 4.0D, d0), this.targetEntitySelector);
            Collections.sort(list, this.theNearestAttackableTargetSorter);

            // Don't attack humans players
            for (int i = 0; i < list.size(); i++) {
                this.targetEntity = (EntityLivingBase)list.get(i);
                if (this.targetEntity instanceof EntityPlayer) {
                	if (DragonPlayer.get((EntityPlayer) this.targetEntity).isDragon() && !this.targetEntity.isInvisible()) {
                		return true;
                	}
                }
                else {
                	return true;
                }
            }
            return false;            
        }
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting() {
        this.taskOwner.setAttackTarget(this.targetEntity);
        super.startExecuting();
    }
 }
