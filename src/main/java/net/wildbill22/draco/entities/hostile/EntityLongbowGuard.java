package net.wildbill22.draco.entities.hostile;

import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.ai.EntityAIArrowAttack;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.world.World;
import net.wildbill22.draco.lib.LogHelper;
import net.wildbill22.draco.lib.REFERENCE;

public class EntityLongbowGuard extends EntityGuard implements IRangedAttackMob {
	public static final String name = "longbowGuard";
	public static String getFullName() {
		return REFERENCE.MODID + "." + name;
	}
	public final GuardType type = GuardType.LONGBOW;
	public GuardType getGuardType() {
		return type;
	}

	public EntityLongbowGuard(World world){
		super(world);
		this.tasks.addTask(1, new EntityAIArrowAttack(this, 1.0D, 20, 60, 15.0F));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityCreature.class, 0, true, false, 
				new IEntitySelector() {
					public boolean isEntityApplicable(Entity entity) {
						if (!isLookingForHome && ((EntityCreature) entity).isWithinHomeDistanceCurrentPosition() && entity instanceof EntityZombie)
							return true;
						return false;
					}
			}));		        
		setGuardWeaponType(type);
	}
	
	@Override
	protected void dropFewItems(boolean par1, int par2)	{
		super.dropFewItems(par1, par2);
//		if (this.rand.nextInt(3) == 0) {
//			this.dropItem(ModWeapons.longBow, 1);
//		}
	}
	
    /**
     * Attack the specified entity using a ranged attack.
     */
	@Override
	public void attackEntityWithRangedAttack(EntityLivingBase entity, float attackDamage) {
		EntityArrow entityArrow = new EntityArrow(this.worldObj, this, entity, 1.6F, (float)(14 - this.worldObj.difficultySetting.getDifficultyId() * 4));
		// TODO: Could have enchantments		
		this.playSound("random.bow", 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
        this.worldObj.spawnEntityInWorld(entityArrow);			
	}	
}
