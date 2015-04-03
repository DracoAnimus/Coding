package net.wildbill22.draco.entities.hostile;

import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.village.Village;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.wildbill22.draco.entities.ai.EntityAIDefendVillage;
import net.wildbill22.draco.items.ModItems;
import net.wildbill22.draco.items.weapons.ModWeapons;
import net.wildbill22.draco.lib.BALANCE;
import net.wildbill22.draco.lib.LogHelper;
import net.wildbill22.draco.lib.REFERENCE;

public class EntityGuard extends EntityMob{
	public static final String name = "guard";
	private boolean isLookingForHome;
	private Village homeVillage = null;

	public static String getFullName() {
		return REFERENCE.MODID + "." + name;
	}

	public enum GuardType {
	    GUARD(0),
	    KNIGHT(1);
		private final int type;
	    
	    private GuardType(int id) {
	        this.type = id;
	    }

		public int getType() {
			return type;
		}
	}
	public GuardType type;

	// Does this need entityAIMoveTowardsTarget?
	public EntityGuard(World world){
		super(world);
		this.getNavigator().setCanSwim(true);
		this.getNavigator().setAvoidsWater(true);
		this.experienceValue = 10;
		this.setSize(0.6F, 1.8F);
//		this.clearAITasks();
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(2, new EntityAIWander(this, 1.0D));
		this.tasks.addTask(3, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, true));
		this.tasks.addTask(3, new EntityAIAttackOnCollide(this, EntityCreature.class, 0.9, false));
        this.tasks.addTask(4, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));

        this.targetTasks.addTask(0, new EntityAIHurtByTarget(this, true));
		this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityCreature.class, 0, true, false, new IEntitySelector() {
			@Override
			// TODO: Add all the creatures the guard should attack
			public boolean isEntityApplicable(Entity entity) {
				if (!isLookingForHome && ((EntityCreature) entity).isWithinHomeDistanceCurrentPosition() && entity instanceof EntityZombie)
					return true;
				return false;
			}

		}));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
        
		// Default to not in a village, will be set to false in
		// WorldGenDracoAnimus when generated on the surface in a village
		isLookingForHome = true;
		
		// Default to Guard
		type = EntityGuard.GuardType.GUARD;
	}
	
	public void setGuardTypePerBiome(World world){
		BiomeGenBase biome = world.getBiomeGenForCoords((int)this.posX, (int)this.posZ);
		if (biome == BiomeGenBase.desert || biome == BiomeGenBase.savanna
				|| biome == BiomeGenBase.coldTaiga || biome == BiomeGenBase.icePlains) {
			setGuardType(EntityGuard.GuardType.KNIGHT);
		}
	}

	// Counter part for getHeldItem from EntityLiving
	public void setGuardType(GuardType type){
		if (type == GuardType.GUARD) {
			// Hold a spear
			if (this.worldObj.difficultySetting != EnumDifficulty.EASY){
				this.setCurrentItemOrArmor(0, new ItemStack(ModItems.spear));
			}
			this.type = type;
		}
		else if (type == GuardType.KNIGHT){
			// Hold a random weapon, either mace, longSword, or battleAxe
			if (this.worldObj.difficultySetting != EnumDifficulty.EASY){
				int chance = this.rand.nextInt(3);
				switch (chance){
				case 0:
					this.setCurrentItemOrArmor(0, new ItemStack(ModWeapons.mace));
					break;
				case 1:
					this.setCurrentItemOrArmor(0, new ItemStack(ModWeapons.longSword));
					break;
				case 2:
					this.setCurrentItemOrArmor(0, new ItemStack(ModWeapons.battleAxe));
					break;
				}
				this.setCurrentItemOrArmor(1, new ItemStack(Items.iron_helmet));
				this.setCurrentItemOrArmor(2, new ItemStack(Items.iron_chestplate));
				this.setCurrentItemOrArmor(3, new ItemStack(Items.iron_leggings));
				this.setCurrentItemOrArmor(4, new ItemStack(Items.iron_boots));
				this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(BALANCE.MOBPROP.GUARD_MOVEMENT_SPEED);
			}
			this.type = type;			
		}
	}
	
//	@Override
//	public void onLivingUpdate(){
//		super.onLivingUpdate();
//	}
	
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
		AxisAlignedBB box = AxisAlignedBB.getBoundingBox(v.getCenter().posX - r, surfaceY - 20, v.getCenter().posZ - r, 
				v.getCenter().posX + r,	surfaceY + 35, v.getCenter().posZ + r);
		int spawnedGuards = world.getEntitiesWithinAABB(EntityGuard.class, box).size();
		LogHelper.info("GuardSpawn: Found village at: " + v.getCenter().posX + " " + v.getCenter().posY
				+ " " + v.getCenter().posZ + " with " + spawnedGuards + " Guards");
		if (v.isInRange(x, surfaceY, z) && spawnedGuards < BALANCE.MOBPROP.GUARD_MAX_PER_VILLAGE) {
			setHomeArea(v.getCenter().posX, v.getCenter().posY, v.getCenter().posZ, r);
			setFoundHome();
			return true;
		}
		else
			return false;
	}

	/**
     * Checks if the entity's current position is a valid location to spawn this entity.
     */
	// this does not get called when random spawns occur
//	@Override
//    public boolean getCanSpawnHere()
//    {
//    	boolean canSpawnHere = super.getCanSpawnHere();
//    	// If spawned by WorldGen, it will not be looking for home
//    	if (canSpawnHere && isLookingForHome) {
//    		canSpawnHere = okToSpawnNearVillage(40);
//    	}
//    	if (canSpawnHere) {
//    		LogHelper.info("GuardSpawn: Creating a Guard at: " + this.posX + ", " + this.posY + ", " + this.posZ);
//    	}
//    	return canSpawnHere;
//    }
	
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
	
	// Only gets called if called with EntityGuard class
	@Override
	public boolean isValidLightLevel()
	{
//		LogHelper.info("GuardSpawn: Checking light level at: " + this.posX + ", " + this.posY + ", " + this.posZ);
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
	// Gets called a lot, not sure why
	@Override
	public float getBlockPathWeight(int p_70783_1_, int p_70783_2_, int p_70783_3_) {
//		LogHelper.info("GuardSpawn: Checking for getBlockPathWeight at: " + this.posX + ", " + this.posY + ", " + this.posZ);
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
		int var = this.rand.nextInt(4);
		if (var == 0)
		{
			this.dropItem(Items.iron_ingot, 1);
		}
		
		int var1 = this.rand.nextInt(3);
		if (var1 == 0)
		{
			this.dropItem(ModItems.spear, 1);
		}
		
		int var2 = this.rand.nextInt(5);
		if (var2 == 0)
		{
			this.dropItem(ModItems.goldCoin, 1);
		}		
	}
	
    @Override
    protected void dropEquipment(boolean p_82160_1_, int p_82160_2_) {
    	// Don't drop the armor, just stuff in hand
        ItemStack itemstack = this.getEquipmentInSlot(0);
        boolean flag1 = this.equipmentDropChances[0] > 1.0F;
        if (itemstack != null && (p_82160_1_ || flag1) && this.rand.nextFloat() - (float)p_82160_2_ * 0.01F < this.equipmentDropChances[0]) {
            this.entityDropItem(itemstack, 0.0F);
        }
    }

    @Override
    protected String getLivingSound()
    {
    	return "mob.villager.idle";
    }
    
    @Override
    protected String getHurtSound()
    {
    	return "mob.villager.hit";
    }
    
    @Override
    protected String getDeathSound()
    {
    	return "mob.villager.death";
    }
    
	/**
	 * Returns the home village if the guard has a home
	 * 
	 * @return village or null
	 */
    // TODO: This is called a lot, could be optimized somehow
	public Village getHomeVillage() {
		if (isLookingForHome)
			return null;
		if (homeVillage != null)
			return homeVillage;
		else {
//			LogHelper.info("EntityGuard: setting home village location");
			ChunkCoordinates cc = this.getHomePosition();
			homeVillage = this.worldObj.villageCollectionObj.findNearestVillage(cc.posX, cc.posY, cc.posZ, 10); 
			return homeVillage;
		}
	}

	/**
	 * Makes the guard not look for a new home anymore and adds village specific AI tasks
	 */
	public void setFoundHome() {
		isLookingForHome = false;
		// This moves the entity towards its home position
		this.tasks.addTask(1, new EntityAIMoveTowardsRestriction(this, 1.0F));
		// sets entity, speed, and whether nocturnal or not, I think this AI used by zombie
//		this.tasks.addTask(4, new EntityAIMoveThroughVillage(this, 0.9F, false));
		this.targetTasks.addTask(1, new EntityAIDefendVillage(this));
	}

	public boolean isLookingForHome() {
		return isLookingForHome;
	}
}
