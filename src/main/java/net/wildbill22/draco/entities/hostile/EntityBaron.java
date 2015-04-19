package net.wildbill22.draco.entities.hostile;

import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.wildbill22.draco.entities.ai.EntityAISummonGuards;
import net.wildbill22.draco.lib.BALANCE;
import net.wildbill22.draco.lib.REFERENCE;

public class EntityBaron extends EntityGuard {
	public static final String name = "baron";
	public static String getFullName() {
		return REFERENCE.MODID + "." + name;
	}
	public final GuardType type = GuardType.BARON;
	public GuardType getGuardType() {
		return type;
	}

	public EntityBaron(World world){
		super(world);
		// TODO: Add a new AI that summons 2 guards if no guards are nearby
		this.tasks.addTask(1, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, true));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityCreature.class, 0, true, false, 
				new IEntitySelector() {
					public boolean isEntityApplicable(Entity entity) {
						if (!isLookingForHome && ((EntityCreature) entity).isWithinHomeDistanceCurrentPosition() && entity instanceof EntityZombie)
							return true;
						return false;
					}
			}));		
		// Summons 2 guards
		this.targetTasks.addTask(3, new EntityAISummonGuards(this, EntityPlayer.class, true, true, 2));

		setGuardWeaponType(type);
	}
	
	@Override
	protected void applyEntityAttributes(){
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(BALANCE.MOBPROP.BARON_MAX_HEALTH);
	}
	
	@Override
	protected void dropFewItems(boolean par1, int par2)	{
		super.dropFewItems(par1, par2);
		// TODO: Anything else?
//		if (this.rand.nextInt(5) == 0) {
//			this.dropItem(ModWeapons.battleAxe, 1);
//		}
	}	
}
