package net.wildbill22.draco.entities.hostile;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.wildbill22.draco.entities.EntitySpear;
import net.wildbill22.draco.entities.ai.EntityAISpearAttack;
import net.wildbill22.draco.items.ModItems;
import net.wildbill22.draco.lib.LogHelper;

public class EntitySpearGuard extends EntityGuard implements IRangedAttackMob {
	public static final String name = "spearGuard";

	// Stuff for Spear attack:
    private EntityAISpearAttack aiSpearAttack = new EntityAISpearAttack(this, 1.0D, 20, 60, 15.0F);
    private EntityAIAttackOnCollide aiAttackOnCollide = new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, true);
    
	// Does this need entityAIMoveTowardsTarget?
	public EntitySpearGuard(World world){
		super(world);
		this.type = GuardType.SPEAR;
		// Also sets task 3 to one of  EntityAIAttackOnCollide or EntityAISpearAttack
		setGuardWeaponType();
		if (world != null && !world.isRemote)
			setCombatTask();
	}
	
	@Override
	protected void dropFewItems(boolean par1, int par2)	{
		super.dropFewItems(par1, par2);
//		if (this.rand.nextInt(3) == 0) {
//			this.dropItem(ModItems.spear, 1);
//		}
	}
	
    /**
     * Attack the specified entity using a ranged attack.
     */
	@Override
	public void attackEntityWithRangedAttack(EntityLivingBase entity, float attackDamage) {
		EntitySpear entitySpear = new EntitySpear(this.worldObj, this, entity, 1.6F, (float)(14 - this.worldObj.difficultySetting.getDifficultyId() * 4));
		// TODO: Could have enchantments		
		this.playSound("random.bow", 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
        this.worldObj.spawnEntityInWorld(entitySpear);
	}
	
    /**
     * sets this entity's combat AI.
     */
    public void setCombatTask() {
        this.tasks.removeTask(this.aiAttackOnCollide);
        this.tasks.removeTask(this.aiSpearAttack);
        ItemStack itemstack = this.getHeldItem();

        if (itemstack != null && itemstack.getItem() == ModItems.spear) {
            this.tasks.addTask(4, this.aiSpearAttack);
            LogHelper.info("SetCombatTask: Setting attack to aiSpearAttack for " + this.type.toString());
        }
        else {
            this.tasks.addTask(4, this.aiAttackOnCollide);
            LogHelper.info("SetCombatTask: Setting attack to aiAttackOnCollide for " + this.type.toString());
        }
    }
}
