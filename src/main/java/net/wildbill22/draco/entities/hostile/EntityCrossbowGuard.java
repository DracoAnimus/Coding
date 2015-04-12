package net.wildbill22.draco.entities.hostile;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.ai.EntityAIArrowAttack;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.wildbill22.draco.items.weapons.ModWeapons;
import net.wildbill22.draco.lib.LogHelper;

public class EntityCrossbowGuard extends EntityGuard implements IRangedAttackMob {
	public static final String name = "crossbowGuard";

	// Stuff for Spear attack:
    private EntityAIArrowAttack aiArrowAttack = new EntityAIArrowAttack(this, 1.0D, 20, 60, 15.0F);
    private EntityAIAttackOnCollide aiAttackOnCollide = new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, true);
    
	// Does this need entityAIMoveTowardsTarget?
	public EntityCrossbowGuard(World world){
		super(world);
		this.type = GuardType.CROSSBOW;
		// Also sets task 3 to one of  EntityAIAttackOnCollide or EntityAISpearAttack
		setGuardWeaponType();
		if (world != null && !world.isRemote)
			setCombatTask();
	}
	
	@Override
	protected void dropFewItems(boolean par1, int par2)	{
		super.dropFewItems(par1, par2);
//		if (this.rand.nextInt(3) == 0) {
//			this.dropItem(ModWeapons.crossbow, 1);
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
	
    /**
     * sets this entity's combat AI.
     */
    public void setCombatTask() {
        this.tasks.removeTask(this.aiAttackOnCollide);
        this.tasks.removeTask(this.aiArrowAttack);
        ItemStack itemstack = this.getHeldItem();

        if (itemstack != null && itemstack.getItem() == ModWeapons.crossbow) {
            this.tasks.addTask(4, this.aiArrowAttack);
            LogHelper.info("SetCombatTask: Setting attack to aiArrowAttack for " + name);
        }
        else {
            this.tasks.addTask(4, this.aiAttackOnCollide);
            LogHelper.info("SetCombatTask: Setting attack to aiAttackOnCollide for " + name);
        }
    }
}
