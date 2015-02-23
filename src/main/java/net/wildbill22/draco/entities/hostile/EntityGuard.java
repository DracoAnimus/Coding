package net.wildbill22.draco.entities.hostile;

import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.village.Village;
import net.minecraft.world.World;
import net.wildbill22.draco.entities.ai.EntityAIDefendVillage;
import net.wildbill22.draco.items.ModItems;
import net.wildbill22.draco.lib.BALANCE;
import net.wildbill22.draco.lib.LogHelper;

public class EntityGuard extends EntityMob{
	private boolean isLookingForHome;

	// Does this need entityAIMoveTowardsTarget?
	public EntityGuard(World world){
		super(world);
		this.getNavigator().setCanSwim(true);
		this.experienceValue = 10;
		this.setSize(0.6F, 1.8F);
//		this.clearAITasks();
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, true));
		this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityCreature.class, 0.9, false));
        this.tasks.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(4, new EntityAIWander(this, 1.0D));

		this.targetTasks.addTask(0, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
		this.targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, EntityCreature.class, 0, true, false, new IEntitySelector() {
			@Override
			// TODO: Add all the creatures the guard should attack
			public boolean isEntityApplicable(Entity entity) {
				if (!isLookingForHome && ((EntityCreature) entity).isWithinHomeDistanceCurrentPosition() && ((EntityCreature) entity).isEntityUndead())
					return true;
				return false;
			}

		}));
        
		// Default to not in a village, will be set to false in
		// WorldGenDracoAnimus when generated on the surface in a village
		isLookingForHome = true;
    }

	public boolean okToSpawnNearVillage(int distanceToLook) {
		World world = this.worldObj;
		int x = (int) this.posX;
		int z = (int) this.posZ;
		int surfaceY = world.getHeightValue(x, z);
		Village v = world.villageCollectionObj.findNearestVillage(x, surfaceY, z, distanceToLook);
		if (v == null) {
			return false;
		}

		int r = v.getVillageRadius();
		AxisAlignedBB box = AxisAlignedBB.getBoundingBox(v.getCenter().posX - r, surfaceY - 10, v.getCenter().posZ - r, 
				v.getCenter().posX + r,	surfaceY + 25, v.getCenter().posZ + r);
		int spawnedGuards = world.getEntitiesWithinAABB(EntityGuard.class, box).size();
		LogHelper.info("GuardSpawn: Found village at: " + v.getCenter().posX + " " + v.getCenter().posY
				+ " " + v.getCenter().posZ + " with " + spawnedGuards + " Guards");
		if (v.isInRange(x, surfaceY, z) && spawnedGuards < BALANCE.MOBPROP.GUARD_MAX_PER_VILLAGE) {
			setFoundHome();
			setHomeArea(v.getCenter().posX, v.getCenter().posY, v.getCenter().posZ, r);
			return true;
		}
		else
			return false;
	}

	/**
     * Checks if the entity's current position is a valid location to spawn this entity.
     */
    public boolean getCanSpawnHere()
    {
    	boolean canSpawnHere = super.getCanSpawnHere();
    	// If spawned by WorldGen, it will not be looking for home
    	if (canSpawnHere && isLookingForHome) {
    		canSpawnHere = okToSpawnNearVillage(40);
    	}
    	if (canSpawnHere) {
    		LogHelper.info("GuardSpawn: Creating a Guard at: " + this.posX + ", " + this.posY + ", " + this.posZ);
    	}
    	return canSpawnHere;
    }
	
	@Override
	protected void applyEntityAttributes(){
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(BALANCE.MOBPROP.GUARD_ATTACK_DAMAGE);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(32.0D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(BALANCE.MOBPROP.GUARD_MOVEMENT_SPEED);
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(BALANCE.MOBPROP.GUARD_MAX_HEALTH);
	}
	
	@Override
	public boolean isAIEnabled()
	{
		return true;
	}
	
	@Override
	public boolean isValidLightLevel()
	{
		return true;
	}
	
	@Override
	public boolean canDespawn()
	{
		return false;
	}

	/**
	 * Ignore light level
	 */
	@Override
	public float getBlockPathWeight(int p_70783_1_, int p_70783_2_, int p_70783_3_) {
		return 0.5F;
	}
	
	protected void clearAITasks()
	{
		tasks.taskEntries.clear();
		targetTasks.taskEntries.clear();
	}
	
	/**
	 * Testing purposes. Entities are having trouble attacking
	 * players; hopefully this will help out.
	 */
    @Override
	protected Entity findPlayerToAttack()
    {
        EntityPlayer entityplayer = this.worldObj.getClosestVulnerablePlayerToEntity(this, 32.0D);
        return entityplayer != null && this.canEntityBeSeen(entityplayer) ? entityplayer : null;
    }
	
	@Override
	protected void dropFewItems(boolean par1, int par2)
	{
		int var = this.rand.nextInt(5);
		if (var == 0)
		{
			this.dropItem(Items.iron_ingot, 1);
		}
		
		int var1 = this.rand.nextInt(3);
		if (var1 == 0)
		{
			this.dropItem(ModItems.spear, 1);
		}
		
		int var2 = this.rand.nextInt(25);
		if (var2 == 0)
		{
			this.dropItem(ModItems.goldCoin, 1);
		}		
	}
	
//    @Override
//    protected String getLivingSound()
//    {
//    	return "mob.villager.idle";
//    }
    
//    @Override
//    protected String getHurtSound()
//    {
//    	return "mob.villager.hit";
//    }
    
//    @Override
//    protected String getDeathSound()
//    {
//    	return "mob.villager.death";
//    }
    
	/**
	 * Returns the home village if the guard has a home
	 * 
	 * @return village or null
	 */
	public Village getHomeVillage() {
		if (isLookingForHome)
			return null;
		ChunkCoordinates cc = this.getHomePosition();
		return this.worldObj.villageCollectionObj.findNearestVillage(cc.posX, cc.posY, cc.posZ, 10);
	}

	/**
	 * Makes the guard not look for a new home anymore and adds village specific AI tasks
	 */
	public void setFoundHome() {
		isLookingForHome = false;
		this.tasks.addTask(3, new EntityAIMoveTowardsRestriction(this, 1.0F));
		this.tasks.addTask(4, new EntityAIMoveThroughVillage(this, 0.9F, false));
		this.targetTasks.addTask(2, new EntityAIDefendVillage(this));
	}
}
