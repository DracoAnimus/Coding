package net.wildbill22.draco.entities.dragons;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.world.World;
import net.wildbill22.draco.items.ModItems;

/**
 * @author WILLIAM
 *
 */
public class EntitySilverDragon extends EntityLiving {
	int lastCheckTime = 0;
	
	public EntitySilverDragon(World world) {
		super(world);        
	}

	/**
     * Determines if an entity can despawn, used on idle far away entities
     */
	@Override
    protected boolean canDespawn() {
        return false;
    }

	@Override
	// Note: Can not add attack damage here for a tamable, it is added in attackEntityAsMob()
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        // 0.2 to 0.3 = very slow, 0.4 = slow, 0.5 to 0.6 = normal, 0.7 = fast, 0.8 to 0.9 = very fast,	1.0 = a blur
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.3D);

		// 10 is 5 hearts (player has 20, 10 hearts for comparison)
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(35.0D);
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
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
//	@Override
//	public void onLivingUpdate(){
//		super.onLivingUpdate();
//	}
	
    @Override
    protected void func_145780_a(int p_145780_1_, int p_145780_2_, int p_145780_3_, Block p_145780_4_) {
        this.playSound("mob.cow.step", 0.15F, 1.0F);
    }

	@Override
    protected String getLivingSound() {
       	return "mob.wolf.growl";        
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
}
