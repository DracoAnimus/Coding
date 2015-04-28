package net.wildbill22.draco.entities.hostile;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.wildbill22.draco.entities.EntitySpear;
import net.wildbill22.draco.lib.BALANCE;
import net.wildbill22.draco.lib.LogHelper;
import net.wildbill22.draco.lib.BALANCE.WILD_FIRE_DRAGON_PROP;

/**
 * @author WILLIAM
 *
 */
public class EntityCatapult extends EntityMob {
	public static final String name = "catapult";
    private int field_70846_g;  // Something to do with when and how attacks occur

	public EntityCatapult(World world) {
		super(world);
		this.getDataWatcher().addObject(BALANCE.BOW_POSITION_WATCHER, Byte.valueOf((byte)0));
		this.setSize(2.0F, 1.0F);
	}

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(WILD_FIRE_DRAGON_PROP.ATTACK_DAMAGE);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(WILD_FIRE_DRAGON_PROP.FOLLOW_RANGE);
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(WILD_FIRE_DRAGON_PROP.MAX_HEALTH);
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
        return "mob.blaze.breathe";
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
            double targetX = target.posX - this.posX;
//            double targetY = target.boundingBox.minY + (double)(target.height / 2.0F) - (this.posY + (double)(this.height / 2.0F));
            double targetZ = target.posZ - this.posZ;
            // Pull the bow
            if (this.attackTime < 6) {
            	setArmPosition(this.attackTime);
            }
            if (this.attackTime == 0) {
                ++this.field_70846_g;
                if (this.field_70846_g == 1) {
                    this.attackTime = 60;
                }
                else if (this.field_70846_g <= 4) {
                    this.attackTime = 30;
                }
                else {
                    this.attackTime = 100;
                    this.field_70846_g = 0;
                }
                if (this.field_70846_g > 1) {
                    for (int i = 0; i < 1; ++i) {
                		EntitySnowball entitySnowball = new EntitySnowball(this.worldObj, this);
                		// TODO: Could have enchantments
                		entitySnowball.posY = this.posY + (double)(this.height / 2.0F) + 1.5D;
                		entitySnowball.posX = this.posX;
                		entitySnowball.posZ = this.posZ;
//                		entitySnowball.posX += target.posX > 0 ? 1 : -1;
//                		entitySnowball.posY += target.posY > 0 ? 1 : -1;
                		this.playSound("random.bow", 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
                        this.worldObj.spawnEntityInWorld(entitySnowball);
                    }
                }
            }
            this.rotationYaw = (float)(Math.atan2(targetZ, targetX) * 180.0D / Math.PI) - 90.0F;
            this.hasAttacked = true;
        }
    }

	public float getArmPosition() {
		int pos = this.dataWatcher.getWatchableObjectByte(BALANCE.BOW_POSITION_WATCHER);
		float angle = 30;
		switch (pos) {
		case 0:
			angle = 30;
			break;
		case 1:
			angle = 45;
			break;
		case 2:
			angle = 60;
			break;
		case 3:
			angle = 45;
			break;
		case 4:
			angle = 30;
			break;
		case 5:
			angle = 15;
			break;
		default:
			angle = 30;
		}
		float radians = (float) (Math.PI / 180.0F * angle);
        return radians;
	}

	public void setArmPosition(int newArmPosition) {
		 int armPosition = newArmPosition;
		 if (armPosition > 5)
			 return;
		
        this.dataWatcher.updateObject(BALANCE.BOW_POSITION_WATCHER, Byte.valueOf((byte) armPosition));
        LogHelper.info("EntityBallista sync: Set bow position of " + armPosition + ".");
	}    
}
