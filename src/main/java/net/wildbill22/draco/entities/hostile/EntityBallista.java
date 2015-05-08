package net.wildbill22.draco.entities.hostile;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.wildbill22.draco.entities.EntitySpear;
import net.wildbill22.draco.lib.BALANCE;
import net.wildbill22.draco.lib.LogHelper;
import net.wildbill22.draco.lib.REFERENCE;

/**
 * @author WILLIAM
 *
 */
public class EntityBallista extends EntityMob {
	public static final String name = "ballista";
    private int field_70846_g;  // Something to do with when and how attacks occur

	public EntityBallista(World world) {
		super(world);
		this.getDataWatcher().addObject(BALANCE.BOW_POSITION_WATCHER, Byte.valueOf((byte)0));
		this.setSize(1.9F, 1.0F);
		jumpMovementFactor = 0.0F;
	}

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(BALANCE.MOBPROP.TOWER_MAX_HEALTH);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0);
    }

	/**
     * Determines if an entity can despawn, used on idle far away entities
     */
	@Override
    protected boolean canDespawn() {
        return false;
    }

	@Override
	protected void dropFewItems(boolean par1, int par2)	{
//		int chance = this.rand.nextInt(3);
//		if (chance == 0) {
//			this.dropItem(ModItems.dragonScales, 1);
//		}		
	}

    /**
     * Returns the sound this mob makes while it's alive.
     */
	@Override
    protected String getLivingSound() {
		return REFERENCE.MODID + ":" + "crank-01";
        //return "mob.blaze.breathe";
    }
     
    @Override
    protected String getHurtSound() {
        return "mob.blaze.hit";
    }

    /**
     * Returns the sound this mob makes on death.
     */
    @Override
    protected String getDeathSound() {
        return "mob.blaze.death";
    }

    /**
     * Returns the volume for the sounds this mob makes.
     */
    @Override
    protected float getSoundVolume() {
        return 0.45F;
    }

    /**
     * Called when the entity is attacked.
     */
    @Override
    public boolean attackEntityFrom(DamageSource damageSource, float damage) {
        if (this.isEntityInvulnerable()) {
            return false;
        }
        else {
            return super.attackEntityFrom(damageSource, damage);
        }
    }
    
    /**
     * Basic mob attack. Default to touch of death in EntityCreature. Overridden by each mob to define their attack.
     * This is from blaze
     */
    @Override
    protected void attackEntity(Entity target, float distance) {
        if (this.attackTime <= 0 && distance < 2.0F && target.boundingBox.maxY > this.boundingBox.minY 
        		&& target.boundingBox.minY < this.boundingBox.maxY) {
            this.attackTime = 20;
//            this.attackEntityAsMob(p_70785_1_);
        }
        // TODO: Set to the follow range? Maybe new attack distance setting in config?
        else if (distance < 30.0F) {
        	// Adding offset so it doesn't shoot itself
        	double posOffsetX = this.posX > 0 ? 1.6 : -1.6;
        	double posOffsetZ = this.posZ > 0 ? 1.6 : -1.6;
            double targetX = target.posX - this.posX + posOffsetX;
//            double targetY = target.boundingBox.minY + (double)(target.height / 2.0F) - (this.posY + (double)(this.height / 2.0F));
            double targetZ = target.posZ - this.posZ + posOffsetZ;
            // Pull the bow
            if (this.attackTime < 4) {
            	setBowPosition(this.attackTime);
            }
            if (this.attackTime == 0) {
                ++this.field_70846_g;
                if (this.field_70846_g == 1) {
                    this.attackTime = 60;
                }
                else if (this.field_70846_g <= 4) {
                    this.attackTime = 6;
                }
                else {
                    this.attackTime = 100;
                    this.field_70846_g = 0;
                }
                if (this.field_70846_g > 1) {
                    for (int i = 0; i < 1; ++i) {
                		EntitySpear entitySpear = new EntitySpear(this.worldObj, (EntityLivingBase)this, (EntityLivingBase)target, 1.6F, (float)(14 - this.worldObj.difficultySetting.getDifficultyId() * 4));
                		// TODO: Could have enchantments
                		entitySpear.posY = this.posY + (double)(this.height / 2.0F) + 0.5D;
                		entitySpear.posX = this.posX + posOffsetX;
                		entitySpear.posZ = this.posZ + posOffsetZ;
                		this.playSound("random.bow", 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
                        this.worldObj.spawnEntityInWorld(entitySpear);
                    }
                }
            }
            this.rotationYaw = (float)(Math.atan2(targetZ, targetX) * 180.0D / Math.PI) - 90.0F;
            this.hasAttacked = true;
        }
    }

	public int getBowPosition() {
        return this.dataWatcher.getWatchableObjectByte(BALANCE.BOW_POSITION_WATCHER);
	}

	public void setBowPosition(int newBowPosition) {
		 int bowPosition = newBowPosition;
		 if (bowPosition > 3)
			 return;
		
        this.dataWatcher.updateObject(BALANCE.BOW_POSITION_WATCHER, Byte.valueOf((byte) bowPosition));
        LogHelper.info("EntityBallista sync: Set bow position of " + bowPosition + ".");
	}    
}
