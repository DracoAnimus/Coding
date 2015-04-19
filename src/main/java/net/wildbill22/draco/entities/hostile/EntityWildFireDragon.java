package net.wildbill22.draco.entities.hostile;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.wildbill22.draco.items.ModItems;
import net.wildbill22.draco.lib.BALANCE;
import net.wildbill22.draco.lib.BALANCE.WILD_FIRE_DRAGON_PROP;

//import net.minecraft.entity.monster.EntityBlaze;

/**
 * @author WILLIAM
 * Similar to Blaze
 *
 */
public class EntityWildFireDragon extends EntityMob {
	public static final String name = "wildFireDragon";

	//	int lastCheckTime = 0;
    /** Random offset used in floating behavior */
    private float heightOffset = 0.5F;
    /** ticks until heightOffset is randomized */
    private int heightOffsetUpdateTime;
    private int field_70846_g;

	public EntityWildFireDragon(World world) {
		super(world);
		this.getDataWatcher().addObject(BALANCE.FIRE_DRAGON_WATCHER, Byte.valueOf((byte)0));
	}

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(WILD_FIRE_DRAGON_PROP.ATTACK_DAMAGE);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(WILD_FIRE_DRAGON_PROP.FOLLOW_RANGE);
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(WILD_FIRE_DRAGON_PROP.MAX_HEALTH);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(WILD_FIRE_DRAGON_PROP.MOVEMENT_SPEED);
    }

	/**
     * Determines if an entity can despawn, used on idle far away entities
     */
	@Override
    protected boolean canDespawn() {
        return true;
    }

	/**
     * Called when the mob is falling. Calculates and applies fall damage.
     */
	@Override
    protected void fall(float p_70069_1_) {}
	
	@Override
	protected void dropFewItems(boolean par1, int par2)	{
		int chance = this.rand.nextInt(3);
		if (chance == 0) {
			this.dropItem(ModItems.dragonScales, 1);
		}		
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
    public boolean attackEntityFrom(DamageSource damageSource, float damage)
    {
        if (this.isEntityInvulnerable()) {
            return false;
        }
        else {
            Entity entity = damageSource.getEntity();
            if (entity != null) {
            	if (entity instanceof EntityPlayer) {            		
            	}
            	// Vulnerable to arrows
            	else if (!(entity instanceof EntityArrow)) {
            		damage = (damage + 1.0F) / 2.0F;
            	}
            }
            return super.attackEntityFrom(damageSource, damage);
        }
    }
    
    // Stuff from blaze
    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
    public void onLivingUpdate()
    {
        if (!this.worldObj.isRemote) {
            if (this.isWet()) {
                this.attackEntityFrom(DamageSource.drown, 1.0F);
            }
            --this.heightOffsetUpdateTime;
            if (this.heightOffsetUpdateTime <= 0) {
                this.heightOffsetUpdateTime = 100;
                this.heightOffset = 0.5F + (float)this.rand.nextGaussian() * 3.0F;
            }

            if (this.getEntityToAttack() != null && this.getEntityToAttack().posY + (double)this.getEntityToAttack().getEyeHeight() > 
            	this.posY + (double)this.getEyeHeight() + (double)this.heightOffset) {
                this.motionY += (0.30000001192092896D - this.motionY) * 0.30000001192092896D;
            }
        }
        if (this.rand.nextInt(24) == 0) {
            this.worldObj.playSoundEffect(this.posX + 0.5D, this.posY + 0.5D, this.posZ + 0.5D, "fire.fire", 1.0F + 
            		this.rand.nextFloat(), this.rand.nextFloat() * 0.7F + 0.3F);
        }
        if (!this.onGround && this.motionY < 0.0D) {
            this.motionY *= 0.6D;
        }
        for (int i = 0; i < 2; ++i) {
            this.worldObj.spawnParticle("largesmoke", this.posX + (this.rand.nextDouble() - 0.5D) * (double)this.width, 
            		this.posY + this.rand.nextDouble() * (double)this.height, this.posZ + (this.rand.nextDouble() - 0.5D) 
            		* (double)this.width, 0.0D, 0.0D, 0.0D);
        }
        super.onLivingUpdate();
    }

    /**
     * Basic mob attack. Default to touch of death in EntityCreature. Overridden by each mob to define their attack.
     */
    protected void attackEntity(Entity p_70785_1_, float p_70785_2_) {
        if (this.attackTime <= 0 && p_70785_2_ < 2.0F && p_70785_1_.boundingBox.maxY > this.boundingBox.minY 
        		&& p_70785_1_.boundingBox.minY < this.boundingBox.maxY) {
            this.attackTime = 20;
            this.attackEntityAsMob(p_70785_1_);
        }
        else if (p_70785_2_ < 30.0F) {
            double d0 = p_70785_1_.posX - this.posX;
            double d1 = p_70785_1_.boundingBox.minY + (double)(p_70785_1_.height / 2.0F) - (this.posY + (double)(this.height / 2.0F));
            double d2 = p_70785_1_.posZ - this.posZ;
            if (this.attackTime == 0) {
                ++this.field_70846_g;
                if (this.field_70846_g == 1) {
                    this.attackTime = 60;
                    this.func_70844_e(true);
                }
                else if (this.field_70846_g <= 4) {
                    this.attackTime = 6;
                }
                else {
                    this.attackTime = 100;
                    this.field_70846_g = 0;
                    this.func_70844_e(false);
                }
                if (this.field_70846_g > 1) {
                    float f1 = MathHelper.sqrt_float(p_70785_2_) * 0.5F;
                    this.worldObj.playAuxSFXAtEntity((EntityPlayer)null, 1009, (int)this.posX, 
                    		(int)this.posY, (int)this.posZ, 0);
                    for (int i = 0; i < 1; ++i) {
                        EntitySmallFireball entitysmallfireball = new EntitySmallFireball(this.worldObj, this, d0 + this.rand.nextGaussian() * (double)f1, d1, d2 + this.rand.nextGaussian() * (double)f1);
                        entitysmallfireball.posY = this.posY + (double)(this.height / 2.0F) + 0.5D;
                        this.worldObj.spawnEntityInWorld(entitysmallfireball);
                    }
                }
            }
            this.rotationYaw = (float)(Math.atan2(d2, d0) * 180.0D / Math.PI) - 90.0F;
            this.hasAttacked = true;
        }
    }
    
    /**
     * Returns true if the entity is on fire. Used by render to add the fire effect on rendering.
     * Returns true if the flag is active for the entity. Known flags: 0) is burning; 1) is sneaking; 2) is riding
     * something; 3) is sprinting; 4) is eating
     */
    public boolean isBurning() {
        return (this.dataWatcher.getWatchableObjectByte(BALANCE.FIRE_DRAGON_WATCHER) & 1) != 0;
    }
    
    public void func_70844_e(boolean p_70844_1_) {
        byte b0 = this.dataWatcher.getWatchableObjectByte(BALANCE.FIRE_DRAGON_WATCHER);
        if (p_70844_1_) {
            b0 = (byte)(b0 | 1);
        }
        else {
            b0 &= -2;
        }
        this.dataWatcher.updateObject(BALANCE.FIRE_DRAGON_WATCHER, Byte.valueOf(b0));
    }    
}
