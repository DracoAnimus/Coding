package net.wildbill22.draco.entities.dragons;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIFollowOwner;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIOwnerHurtByTarget;
import net.minecraft.entity.ai.EntityAIOwnerHurtTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITargetNonTamed;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.wildbill22.draco.entities.ai.EntityAIAvoidDragon;
import net.wildbill22.draco.items.ModItems;
import net.wildbill22.draco.lib.BALANCE;
import net.wildbill22.draco.lib.LogHelper;

/**
 * @author WILLIAM
 * First we will make it passive, latter may extend EntityMob
 *
 */
public class EntityCreeperDragon extends EntityTameable {
	int lastCheckTime = 0;
	public static final String name = "creeperDragon";
	
	public EntityCreeperDragon(World world) {
		super(world);
		this.getNavigator().setAvoidsWater(false);
		this.setSize(1.8F, 1.9F);
        this.isImmuneToFire = true;
        this.experienceValue = 5;
//		clearAITasks();
		
		// AI
		int p = 1;
        this.tasks.addTask(p++, new EntityAISwimming(this));
//        this.tasks.addTask(p++, new EntityAIPanic(this, 1.25D));
        this.tasks.addTask(p++, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(p++, new EntityAIAttackOnCollide(this, 1.0D, true));
        this.tasks.addTask(p++, new EntityAIFollowOwner(this, 1.0D, 10.0F, 2.0F));
        this.tasks.addTask(p++, new EntityAIWatchClosest(this, EntityPlayer.class, 10.0F));
        this.tasks.addTask(p++, this.aiSit);
        this.tasks.addTask(p++, new EntityAILookIdle(this));
        
        // Target tasks
        p = 1;
        this.targetTasks.addTask(p++, new EntityAIOwnerHurtByTarget(this));
        this.targetTasks.addTask(p++, new EntityAIOwnerHurtTarget(this));
        this.targetTasks.addTask(p++, new EntityAIHurtByTarget(this, true));
        this.targetTasks.addTask(p++, new EntityAITargetNonTamed(this, EntitySheep.class, 200, false));

        this.setTamed(false);
	}

	/**
	 * Clears previous AI Tasks, so the ones defined above will
	 * actually perform.
	 */
	protected void clearAITasks() {
		tasks.taskEntries.clear();
		targetTasks.taskEntries.clear();
	}

    /**
     * Returns true if the newer Entity AI code should be run
     */
	@Override
    public boolean isAIEnabled() {
        return true;
    }

	/**
     * Determines if an entity can despawn, used on idle far away entities
     */
	@Override
    protected boolean canDespawn() {
        return !this.isTamed() && this.ticksExisted > 2400;
    }

	@Override
	// Note: Can not add attack damage here for a tamable, it is added in attackEntityAsMob()
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        // 0.2 to 0.3 = very slow, 0.4 = slow, 0.5 to 0.6 = normal, 0.7 = fast, 0.8 to 0.9 = very fast,	1.0 = a blur
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(BALANCE.MOBPROP.DRAGON_MOVEMENT_SPEED);

		// 10 is 5 hearts (player has 20, 10 hearts for comparison)
        if (this.isTamed()) {
            this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(BALANCE.MOBPROP.DRAGON_MAX_HEALTH_TAMED);
        }
        else {
            this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(BALANCE.MOBPROP.DRAGON_MAX_HEALTH_WILD);
        }
    }
	
	/**
     * Like actual Ocelots and Cats, the dragon will scare away Creepers.
     *
     */
    @SuppressWarnings("unchecked")
    public void checkForCreepers() {
        lastCheckTime--;
        if (lastCheckTime <= 0) {
            lastCheckTime = 30;
            List<EntityCreeper> creepers = this.worldObj.getEntitiesWithinAABB(EntityCreeper.class,
                    AxisAlignedBB.getBoundingBox(this.posX, this.posY, this.posZ,
                            this.posX + 1.0D, this.posY + 1.0D,
                            this.posZ + 1.0D).expand(16.0D, 4.0D, 16.0D));

            for (EntityCreeper creeper : creepers) {
                boolean set = true;
                EntityAIAvoidDragon task = new EntityAIAvoidDragon(creeper, EntityCreeperDragon.class, 14.0F, 1.0, 1.3);

                for (Object entry : creeper.tasks.taskEntries) {
                    if (((EntityAITasks.EntityAITaskEntry) entry).action instanceof EntityAIAvoidDragon) {
                        set = false;
                        break;
                    }
                }

                if (set) {
                	LogHelper.info("Found creeper who doesn't know to fear the dragon :-)");
                    creeper.tasks.addTask(3, task);
                }
            }
        }
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

	@Override
	public EntityAgeable createChild(EntityAgeable p_90011_1_) {
		return null;
	}

    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
	@Override
	public void onLivingUpdate()
	{
		super.onLivingUpdate();
		checkForCreepers();
		// TODO: Add code to attack and fly, something like this:
//		if (this.angerLevel <= 0)
//		{
//		//BAT MOVEMENT
//		}
//		else
//		{
//		if (this.angerLevel != 0)
//		{
//		// BLAZE MOVEMENT
//		}
//		}
		
//		if (this.attackCounter == 20)
//		{
//		this.worldObj.playAuxSFXAtEntity(null, 1008, (int)this.posX, (int)this.posY, (int)this.posZ, 0);
//		EntityLargeFireball localEntityLargeFireball = new EntityLargeFireball(this.worldObj, this, d6, d7, d8);
//		localEntityLargeFireball.field_92057_e = this.explosionStrength;
//		double d9 = 4.0D;
//		Vec3 localVec3 = getLook(1.0F);
//		localEntityLargeFireball.posX = (this.posX + localVec3.xCoord * d9);
//		localEntityLargeFireball.posY = (this.posY + this.height / 2.0F + 0.5D);
//		localEntityLargeFireball.posZ = (this.posZ + localVec3.zCoord * d9);
//		this.worldObj.spawnEntityInWorld(localEntityLargeFireball);
//		this.attackCounter = -40;
//		}
	}
	
    @Override
    protected void func_145780_a(int p_145780_1_, int p_145780_2_, int p_145780_3_, Block p_145780_4_) {
        this.playSound("mob.cow.step", 0.15F, 1.0F);
    }

	@Override
    protected String getLivingSound() {
        if (this.isTamed())
            return "mob.cat.purr";        	
        else
        	return "mob.wolf.growl";        
        	// return "mob.enderdragon.growl";
    }
      
    @Override
    protected String getHurtSound() {
        return "mob.enderdragon.hit";
    }

    /**
     * Returns the sound this mob makes on death.
     */
    @Override
    protected String getDeathSound() {
        return "mob.wolf.death";
    }

    /**
     * Returns the volume for the sounds this mob makes.
     */
    @Override
    protected float getSoundVolume() {
        return 0.45F;
    }

    /**
     * Called when the entity is attacked. (Needed to attack other mobs)
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
                // Reduce damage if from player
            	else if ((entity instanceof EntityPlayer) && this.isTamed()) {
            		damage = (damage + 1.0F) / 2.0F;
            	}
            	// Vulnerable to arrows
            	else if (!(entity instanceof EntityArrow)) {
            		damage = (damage + 1.0F) / 2.0F;
            	}
            }
            return super.attackEntityFrom(damageSource, damage);
        }
    }

    // I think this is needed to attack other mobs
    @Override
    public boolean attackEntityAsMob(Entity entity) {
    	// Does 6 damage when tamed (wolves do 4), 2 when not
        int i = this.isTamed() ? BALANCE.MOBPROP.DRAGON_ATTACK_DAMAGE_TAMED : BALANCE.MOBPROP.DRAGON_ATTACK_DAMAGE_WILD;
		//	entity.setFire(3);
        return entity.attackEntityFrom(DamageSource.causeMobDamage(this), (float)i);
    }

    @Override
    public void setTamed(boolean isTamed) {
        super.setTamed(isTamed);

        if (isTamed) {
            this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(BALANCE.MOBPROP.DRAGON_MAX_HEALTH_TAMED);
        } else {
            this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(BALANCE.MOBPROP.DRAGON_MAX_HEALTH_WILD);
        }
    }

    /**
     * Called when a player interacts with a mob. e.g. gets milk from a cow, gets into the saddle on a pig.
     */
    @Override
    public boolean interact(EntityPlayer player) {
        ItemStack itemstack = player.inventory.getCurrentItem();

        if (this.isTamed()) {
            if (itemstack != null) {
                if (itemstack.getItem() instanceof ItemFood) {
                    ItemFood itemfood = (ItemFood)itemstack.getItem();
                    if (!player.capabilities.isCreativeMode) {
                         --itemstack.stackSize;
                    }
                    this.heal((float)itemfood.func_150905_g(itemstack));
                    if (itemstack.stackSize <= 0) {
                        player.inventory.setInventorySlotContents(player.inventory.currentItem, (ItemStack)null);
                    }
                    return true;
                }
            }
        }
        // Tame the dragon with gunpowder
        else if (itemstack != null && itemstack.getItem() == Items.gunpowder) {
            if (!player.capabilities.isCreativeMode) {
                --itemstack.stackSize;
            }
            if (itemstack.stackSize <= 0) {
                player.inventory.setInventorySlotContents(player.inventory.currentItem, (ItemStack)null);
            }

            if (!this.worldObj.isRemote) {
                if (this.rand.nextInt(2) == 0) {
                    this.setTamed(true);
                    this.setPathToEntity((PathEntity)null);
                    this.setAttackTarget((EntityLivingBase)null);
//                    this.aiSit.setSitting(true);
                    this.setHealth(25.0F);
                    this.func_152115_b(player.getUniqueID().toString());
                    this.playTameEffect(true);
                    this.worldObj.setEntityState(this, (byte)7);
                }
                else {
                    this.playTameEffect(false);
                    this.worldObj.setEntityState(this, (byte)6);
                }
            }
            return true;
        }
        else {
			player.addChatMessage(new ChatComponentText("Tame the dragon with gunpowder"));
        }
        return super.interact(player);
    }

    // I think the following two functions need to be present to remember its owner:
    
    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound p_70014_1_) {
        super.writeEntityToNBT(p_70014_1_);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound p_70037_1_) {
        super.readEntityFromNBT(p_70037_1_);
    }
}
