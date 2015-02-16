package net.wildbill22.draco.entities;

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
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITargetNonTamed;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.wildbill22.draco.items.ModItems;
import net.wildbill22.draco.lib.LogHelper;

/**
 * @author WILLIAM
 * First we will make it passive, latter may extend EntityMob
 *
 */
public class EntityCreeperDragon extends EntityTameable {

	public EntityCreeperDragon(World world) {
		super(world);
		this.getNavigator().setAvoidsWater(false);
		this.setSize(1.6F, 2.0F);
		clearAITasks();
		
		// AI
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIPanic(this, 1.25D));
        this.tasks.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(4, new EntityAIFollowOwner(this, 1.0D, 10.0F, 2.0F));
        this.tasks.addTask(5, new EntityAIAttackOnCollide(this, 1.0D, true));
        this.tasks.addTask(6, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(7, new EntityAILookIdle(this));
        
        // Target tasks
        this.targetTasks.addTask(1, new EntityAIOwnerHurtByTarget(this));
        this.targetTasks.addTask(2, new EntityAIOwnerHurtTarget(this));
        this.targetTasks.addTask(3, new EntityAIHurtByTarget(this, true));
        this.targetTasks.addTask(4, new EntityAITargetNonTamed(this, EntitySheep.class, 200, false));

        this.setTamed(false);
	}

	/**
	 * Clears previous AI Tasks, so the ones defined above will
	 * actually perform.
	 */
	protected void clearAITasks()
	{
		tasks.taskEntries.clear();
		targetTasks.taskEntries.clear();
	}

    /**
     * Returns true if the newer Entity AI code should be run
     */
	@Override
    public boolean isAIEnabled()
    {
        return true;
    }

	@Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        // 0.2 to 0.3 = very slow, 0.4 = slow, 0.5 to 0.6 = normal, 0.7 = fast, 0.8 to 0.9 = very fast,	1.0 = a blur
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.3D);
        // double the attack (from base of 2.0D
//		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(4.0D);

		// 10 is 5 hearts (player has 20, 10 hearts for comparison)
        if (this.isTamed()) {
            this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(25.0D);
        }
        else {
            this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(10.0D);
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
//    public void onLivingUpdate()
//    {
//        float f;
//        float f1;
//
//        if (this.worldObj.isRemote)
//        {
//            f = MathHelper.cos(this.animTime * (float)Math.PI * 2.0F);
//            f1 = MathHelper.cos(this.prevAnimTime * (float)Math.PI * 2.0F);
//
//            if (f1 <= -0.3F && f >= -0.3F)
//            {
//                this.worldObj.playSound(this.posX, this.posY, this.posZ, "mob.enderdragon.wings", 5.0F, 0.8F + this.rand.nextFloat() * 0.3F, false);
//            }
//            this.prevAnimTime = this.animTime;
//            this.animTime += f * 0.5F;
//        }
//    }

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
        return 0.4F;
    }

    /**
     * Called when the entity is attacked.
     */
    @Override
    public boolean attackEntityFrom(DamageSource p_70097_1_, float p_70097_2_)
    {
        if (this.isEntityInvulnerable())
        {
            return false;
        }
        else
        {
            Entity entity = p_70097_1_.getEntity();
//            this.aiSit.setSitting(false);

            if (entity != null && !(entity instanceof EntityPlayer) && !(entity instanceof EntityArrow))
            {
                p_70097_2_ = (p_70097_2_ + 1.0F) / 2.0F;
            }

            return super.attackEntityFrom(p_70097_1_, p_70097_2_);
        }
    }

    @Override
    public boolean attackEntityAsMob(Entity p_70652_1_)
    {
        int i = this.isTamed() ? 4 : 2;
        return p_70652_1_.attackEntityFrom(DamageSource.causeMobDamage(this), (float)i);
    }

    @Override
    public void setTamed(boolean isTamed) {
        super.setTamed(isTamed);

        if (isTamed) {
            this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(25.0D);
        } else {
            this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(10.0D);
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
        // Tame the dragon with a bone?
        else if (itemstack != null && itemstack.getItem() == Items.bone) {
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
        return super.interact(player);
    }

//    protected boolean isValidLightLevel(){
//    	LogHelper.info("Lighting is OK for a mob");
//		return true;
//    }    
}
