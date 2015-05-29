package net.wildbill22.draco.items;

import java.util.Iterator;
import java.util.List;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.wildbill22.draco.entities.dragons.DragonRegistry.IDragonEggHandler;
import net.wildbill22.draco.entities.player.DragonPlayer;

public abstract class ItemDragonEgg extends ModItems implements IDragonEggHandler {
    private static Multimap<String, String> dragonFoods = ArrayListMultimap.create();

	public ItemDragonEgg(String name) {
		super(name, name);
	}
	
	// Add this if you want the "enchanted" look in the inventory
	@Override
	public boolean hasEffect(ItemStack itemStack, int pass) {
		return true;
	}

	// Only FoodItems can be a dragon food
	protected void addDragonFood(String dragonName, Item food) {
		dragonFoods.put(dragonName, food.getUnlocalizedName());
	}
	
	public static boolean isDragonFood(String dragonName, Item food) {
		return dragonFoods.containsEntry(dragonName, food.getUnlocalizedName());
	}

	// Stuff below for dragon abilities
	
	/**
     * Sets dragon to be able to swim underwater, adds:
     * Slowness walking only. Nightvision and waterbreathing in water only
     * 
     * @param dragonName unlocalizedName or any unique string 
     */
	protected void addSwimmingUnderWater(String dragonName) {
		Abilities.addAbility(dragonName, Abilities.SLOWNESS);
		Abilities.addAbility(dragonName, Abilities.SWIMMING);
	}
	
	/**
     * Sets dragon to be able to be invisible
     * 
     * @param dragonName unlocalizedName or any unique string 
     */
	protected void addInvisibility(String dragonName) {
		Abilities.addAbility(dragonName, Abilities.INVISIBLE);
	}
	
	/**
     * Sets dragon to have night vision
     * 
     * @param dragonName unlocalizedName or any unique string 
     */
	protected void addNightVision(String dragonName) {
		Abilities.addAbility(dragonName, Abilities.NIGHTVISION);
	}
	
	/**
     * Sets dragon to be able to breathe underwater
     * 
     * @param dragonName unlocalizedName or any unique string 
     */
	protected void addWaterBreathing(String dragonName) {
		Abilities.addAbility(dragonName, Abilities.WATERBREATHING);
	}
	
	/**
     * Sets dragon to be able to swim underwater, adds:
     * nightvision and waterbreathing in water only
     * 
     * @param dragonName unlocalizedName or any unique string 
     */
	protected void addSwimming(String dragonName) {
		Abilities.addAbility(dragonName, Abilities.SWIMMING);
	}
	
	/**
     * Sets dragon to be slow when not flying
     * 
     * @param dragonName unlocalizedName or any unique string 
     */
	protected void addSlowness(String dragonName) {
		Abilities.addAbility(dragonName, Abilities.SLOWNESS);
	}
	
	/**
     * Sets dragon for extra attack damage
     * 
     * @param dragonName unlocalizedName or any unique string 
     */
	protected void addDamageBoost(String dragonName) {
		Abilities.addAbility(dragonName, Abilities.DAMAGEBOOST);
	}
	
	protected void addNoClip(String dragonName) {
		Abilities.addAbility(dragonName, Abilities.NOCLIP);
	}

	/**
     * Sets dragon to push entities away when they get withing 1 block distance
     * 
     * @param dragonName unlocalizedName or any unique string 
     */
	protected void addCollideWithEntities(String dragonName) {
		Abilities.addAbility(dragonName, Abilities.COLLIDEWITHENTITIES);
	}

	public static void applyAbilities(EntityPlayer player) {
		if (player.capabilities.isCreativeMode)
			return;
		
		String dragonName = DragonPlayer.get(player).getDragonName();
		int amplifier = Math.max(1, DragonPlayer.get(player).getLevel());

		if (Abilities.dragonAbilities.containsEntry(dragonName, Abilities.INVISIBLE)) {
			amplifier = amplifier / 2;
            player.addPotionEffect(new PotionEffect(Potion.invisibility.getId(), 10, amplifier));
		}
		if (Abilities.dragonAbilities.containsEntry(dragonName, Abilities.NIGHTVISION)) {
			amplifier = amplifier / 2;
            player.addPotionEffect(new PotionEffect(Potion.nightVision.getId(), 10, amplifier));
		}
		if (Abilities.dragonAbilities.containsEntry(dragonName, Abilities.WATERBREATHING) && player.isInWater()) {
            player.addPotionEffect(new PotionEffect(Potion.waterBreathing.getId(), 10, 3));
		}
		if (Abilities.dragonAbilities.containsEntry(dragonName, Abilities.SWIMMING) && player.isInWater()) {
			amplifier = amplifier / 2;
            player.addPotionEffect(new PotionEffect(Potion.nightVision.getId(), 5, amplifier));
            player.addPotionEffect(new PotionEffect(Potion.waterBreathing.getId(), 5, 3));
		}
		if (Abilities.dragonAbilities.containsEntry(dragonName, Abilities.SLOWNESS) && !player.capabilities.isFlying) {
            player.addPotionEffect(new PotionEffect(Potion.moveSlowdown.getId(), 5, 3));			
		}
		if (Abilities.dragonAbilities.containsEntry(dragonName, Abilities.DAMAGEBOOST)) {
			amplifier = amplifier / 3;
            player.addPotionEffect(new PotionEffect(Potion.damageBoost.getId(), 10, amplifier));
		}
		if (Abilities.dragonAbilities.containsEntry(dragonName, Abilities.NOCLIP)) {
//            player.noClip = true;
//            player.capabilities.isFlying = true;
            // player.canBeCollidedWith(); // Dragon returns false
		}
		if (Abilities.dragonAbilities.containsEntry(dragonName, Abilities.COLLIDEWITHENTITIES)) {
			amplifier = amplifier / 2;
			collideWithEntities(player.worldObj, player, amplifier);
		}
	}
	
    /**
     * Pushes all entities inside the list away from the player
     */
    @SuppressWarnings("rawtypes")
	private static void collideWithEntities(World world, EntityPlayer player, int amplifier) {
    	double repelDistance = 1.1D;
    	List entities = world.getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.
    			expand(repelDistance, repelDistance, repelDistance).offset(0.0D, 0.0D - (repelDistance / 2), 0.0D));
    	
    	double d0 = (player.boundingBox.minX + player.boundingBox.maxX) / 2.0D;
    	double d1 = (player.boundingBox.minZ + player.boundingBox.maxZ) / 2.0D;
        Iterator iterator = entities.iterator();

        while (iterator.hasNext())
        {
            Entity entity = (Entity)iterator.next();

            if (entity instanceof EntityLivingBase)
            {
                double d2 = entity.posX - d0;
                double d3 = entity.posZ - d1;
                double d4 = d2 * d2 + d3 * d3;
                double distance = 2.0D * amplifier;  // Was 4.0D
                entity.addVelocity(d2 / d4 * distance, 0.20000000298023224D, d3 / d4 * distance);
            }
        }
    }
	
	public static void setHumanAbilities(EntityPlayer player) {
		player.clearActivePotions();
//        player.noClip = false;
//        player.capabilities.isFlying = false;		
	}
	
    public static class Abilities {
	    private static Multimap<String, Integer> dragonAbilities = ArrayListMultimap.create();
		private static final int INVISIBLE = 0;
		private static final int NIGHTVISION = 1;
		private static final int WATERBREATHING = 2;
		private static final int SWIMMING = 3;
		public static final int SLOWNESS = 4;
		public static final int DAMAGEBOOST = 5;
		public static final int NOCLIP = 6;
		public static final int COLLIDEWITHENTITIES = 7;

		protected static void addAbility(String dragonName, Integer ability) {
			dragonAbilities.put(dragonName, ability);
		}		
    }
}
