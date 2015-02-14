package net.wildbill22.draco.entities;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.wildbill22.draco.items.ModItems;
import net.wildbill22.draco.lib.LogHelper;

/**
 * @author WILLIAM
 * First we will make it passive, latter may extend EntityMob
 *
 */
public class EntityCreeperDragon extends EntityAnimal {

	public EntityCreeperDragon(World world) {
		super(world);
		this.getNavigator().setAvoidsWater(false);
		this.setSize(1F, 1.6F);
		
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIPanic(this, 1.25D));
        this.tasks.addTask(2, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(4, new EntityAILookIdle(this));		
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
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.3D);
    }

	/**
     * Called when the mob is falling. Calculates and applies fall damage.
     */
    protected void fall(float p_70069_1_) {}
	
	@Override
	protected void dropFewItems(boolean par1, int par2)
	{
		int chance = this.rand.nextInt(3);
		if (chance == 0)
		{
			this.dropItem(ModItems.dragonScales, 1);
		}		
	}

	@Override
	public EntityAgeable createChild(EntityAgeable p_90011_1_) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
    protected String getLivingSound()
    {
        return "mob.enderdragon.growl";
    }
    
    @Override
    protected String getHurtSound()
    {
        return "mob.enderdragon.hit";
    }

    /**
     * Returns the sound this mob makes on death.
     */
    @Override
    protected String getDeathSound()
    {
        return "mob.wolf.death";
    }

    /**
     * Returns the volume for the sounds this mob makes.
     */
    @Override
    protected float getSoundVolume()
    {
        return 8.0F;
    }

//    @Override
//    protected boolean isValidLightLevel(){
//    	LogHelper.info("Lighting is OK for a mob");
//		return true;
//    }    
}
