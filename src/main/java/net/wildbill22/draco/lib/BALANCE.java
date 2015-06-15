package net.wildbill22.draco.lib;

/**
 * All constants which could be used to balance the mod should be stored here
 *
 */
public final class BALANCE {
	
	public static class LEVELING {
		@DefaultInt(value = 200, minValue = 0, maxValue = 1000, name = "Maximum Coins for Hoard", 
				comment = "Number of coins to reach the highest player dragon level")
		public static int HOARD_COINS_TO_MAX_LEVEL;
		@DefaultInt(value = 10, minValue = 0, maxValue = 12, name = "Maximum Dragon Player Level", 
				comment = "Amount of coins needed for for each player dragon level")
		public static int MAXIMUM_LEVEL;
		@DefaultDouble(value = 0.5D, minValue = 0.3D, maxValue = 1.0D, name = "Level Modifier Type", 
				comment = "0.5 for square root, 1 for linear")
		public static double LEVEL_MOD_TYPE;
	}
	
	public static class CHEST_ITEMS {
		@DefaultInt(value = 20, minValue = 0, maxValue = 100, name = "Diamond Armor", 
				comment = "Chance of finding diamond armor items in village chests.")
		public static int VILLAGE_BLACKSMITH_DIAMOND_ARMOR;
		@DefaultInt(value = 80, minValue = 0, maxValue = 100, name = "Gold Coins", 
				comment = "Chance of finding gold coins in village chests.")
		public static int VILLAGE_BLACKSMITH_GOLD_COINS;
		@DefaultInt(value = 100, minValue = 0, maxValue = 100, name = "Villager Skull", 
				comment = "Chance of finding villager skull in stronghold chest.")
		public static int STRONGHOLD_VILLAGER_SKULL;
//		@DefaultInt(value = 50, minValue = 0, maxValue = 100, name = "Fireballs", 
//				comment = "Chance of finding fireballs in village chests.")
//		public static int VILLAGE_BLACKSMITH_FIREBALLS;
		@DefaultInt(value = 10, minValue = 0, maxValue = 100, name = "Dragon Slayer Sword", 
				comment = "Chance of finding Dragon Slayer Sword in village chests.")
		public static int VILLAGE_BLACKSMITH_DRAGON_SLAYER;
	}

	// Properties for guards
	public static class MOBPROP {
		@DefaultDouble(value = 30.0D, minValue = 5.0D, maxValue = 50.0D, name = "Guard Maximum Health", 
				comment = "Maximum health")
		public static double GUARD_MAX_HEALTH;
		@DefaultDouble(value = 60.0D, minValue = 5.0D, maxValue = 75.0D, name = "Baron Maximum Health", 
				comment = "Maximum health")
		public static double BARON_MAX_HEALTH;
		@DefaultDouble(value = 8.0D, minValue = 1.0D, maxValue = 10.0D, name = "Baron Attack Damage", 
				comment = "Baron attack damage")
		public static double BARON_ATTACK_DAMAGE;
		@DefaultDouble(value = 5.0D, minValue = 1.0D, maxValue = 10.0D, name = "Guard Attack Damage", 
				comment = "Guard attack damage")
		public static double GUARD_ATTACK_DAMAGE;
		@DefaultDouble(value = 3.5D, minValue = 1.0D, maxValue = 10.0D, name = "Guard Spear Damage", 
				comment = "Guard's spear damage")
		public static double GUARD_SPEAR_DAMAGE;
		@DefaultDouble(value = 10.0D, minValue = 1.0D, maxValue = 20.0D, name = "Catapult Rock Damage", 
		comment = "Rock damage from catapult")
		public static double ROCK_DAMAGE;
		@DefaultDouble(value = 7.0D, minValue = 1.0D, maxValue = 20.0D, name = "Ballista Spear Damage", 
				comment = "Ballista's spear damage")
		public static double BALLISTA_SPEAR_DAMAGE;
		@DefaultDouble(value = 0.35F, minValue = 0.1D, maxValue = 1.0D, name = "Guard Movement Speed", 
				comment = "Guard movement speed")
		public static double GUARD_MOVEMENT_SPEED;
		@DefaultInt(value = 10, minValue = 0, maxValue = 100, name = "Guard Spawn Probability", 
				comment = "Probability of spawning guards")
		public static int GUARD_SPAWN_PROB;
		@DefaultInt(value = 6, minValue = 0, maxValue = 20, name = "Maximum Guards per Village", 
				comment = "Maximum number of guards that will spawn in a village")
		public static int GUARD_MAX_PER_VILLAGE;
		@DefaultDouble(value = 0.20F, minValue = 0.1D, maxValue = 1.0D, name = "Knight Movement Speed", 
				comment = "Knight movement speed")
		public static double KNIGHT_MOVEMENT_SPEED;
		
		// Properties for Wild (Creeper) Dragon
		@DefaultDouble(value = 25.0D, minValue = 10.0D, maxValue = 50.0D, name = "Tamed Dragon Maximum Health", 
				comment = "Maximum health for tamed Creeper dragon")
		public static double DRAGON_MAX_HEALTH_TAMED;
		@DefaultDouble(value = 10.0D, minValue = 1.0D, maxValue = 20.0D, name = "Wild Dragon Maximum Health", 
				comment = "Maximum health for wild Creeper dragon")
		public static double DRAGON_MAX_HEALTH_WILD;
		@DefaultInt(value = 6, minValue = 0, maxValue = 20, name = "Tamed Dragon Attack Damage", 
				comment = "Tamed Creeper dragon attack damage")
		public static int DRAGON_ATTACK_DAMAGE_TAMED;
		@DefaultInt(value = 2, minValue = 0, maxValue = 10, name = "Wild Dragon Attack Damage", 
				comment = "Wild Creeper dragon attack damage")
		public static int DRAGON_ATTACK_DAMAGE_WILD;
		@DefaultDouble(value = 0.3F, minValue = 0.2D, maxValue = 1.0D, name = "Dragon Movement Speed", 
				comment = "Creeper dragon movement speed")
		public static double DRAGON_MOVEMENT_SPEED;
		@DefaultInt(value = 55, minValue = 0, maxValue = 100, name = "Dragon Spawn Probability", 
				comment = "Probability of spawning creeper dragons")
		public static int DRAGON_SPAWN_PROB;		
		@DefaultInt(value = 10, minValue = 0, maxValue = 80, name = "Creeper Avoidance Distance", 
				comment = "How far away creepers will stay from dragon")
		public static int CREEPER_AVOID_LEVEL;
		
		// Properties for tower weapons
		@DefaultDouble(value = 50.0D, minValue = 10.0D, maxValue = 100.0D, name = "Tower Weapon Maximum Health", 
				comment = "Maximum health for tower weapon")
		public static double TOWER_MAX_HEALTH;
	}
	
	public static class WILD_FIRE_DRAGON_PROP {
		@DefaultDouble(value = 3.0F, minValue = 1.0D, maxValue = 10.0D, name = "Attack Damage", 
				comment = "")
		public static double ATTACK_DAMAGE;
		@DefaultDouble(value = 20.0F, minValue = 10.0D, maxValue = 50.0D, name = "Follow Range", 
				comment = "")
		public static double FOLLOW_RANGE;
		@DefaultDouble(value = 25.0F, minValue = 5.D, maxValue = 50.0D, name = "Max Health", 
				comment = "")
		public static double MAX_HEALTH;
		@DefaultDouble(value = 0.5F, minValue = 0.1D, maxValue = 1.0D, name = "Movement Speed", 
				comment = "")
		public static double MOVEMENT_SPEED;
		@DefaultInt(value = 55, minValue = 0, maxValue = 100, name = "Spawn Probability", 
				comment = "Probability of spawning wild fire dragons")
		public static int SPAWN_PROB;		
	}

	// Setting values for the special abilities for different dragons and their staffs
	public static class DRAGON_PLAYER_ABILITIES {
		@DefaultDouble(value = 1.0F, minValue = 0.5D, maxValue = 4.0D, name = "Exploding Fireball Damage", 
				comment = "1.0 is normal, this is multiplied by base value")
		public static double EXPLODING_FIREBALL_MULTIPLIER;
		@DefaultBoolean(value = false, name = "Receive damage from explosions?", 
				comment = "Whether the player is damaged by explosions")
		public static boolean RECEIVE_EXPLODING_FIREBALL_DAMAGE;
		@DefaultDouble(value = 10.0D, minValue = 4.5D, maxValue = 20.0D, name = "Extended reach for level 1 dragon", 
				comment = "A level 10 dragon get this value + 20")
		public static double MIN_EXTENDED_REACH;
	}

	/**
	 * Class to store all constants related to the player modifiers See {@link:
	 * entity.player.PlayerModifiers#applyModifiers(int, EntityPlayer)} for impact
	 *
	 */
	public static class DP_MODIFIERS {
		@DefaultDouble(value = 1.0D, minValue = 0.1D, maxValue = 2.0D, name = "Jump multiplier", 
				comment = "(Math.pow(level, type) / Math.pow(level cap, type)) * multiplier")
		public static double JUMP_MAX_BOOST;
		@DefaultInt(value = 10, minValue = 3, maxValue = 100, name = "Jump Level Cap", 
				comment = "Dragon level where this modifier does not get stronger")
		public static int JUMP_LCAP;
		@DefaultDouble(value = 0.5D, minValue = 0.1D, maxValue = 1.0D, name = "Jump Modifier Type", 
				comment = "0.5 for square root, 1 for linear")
		public static double JUMP_TYPE;
		@DefaultDouble(value = 1.0D, minValue = 0.3D, maxValue = 2.0D, name = "Health multiplier", 
				comment = "(Math.pow(level, type) / Math.pow(level cap, type)) * multiplier")
		public static double HEALTH_MAX_MOD;
		@DefaultInt(value = 20, minValue = 0, maxValue = 40, name = "Health Level Cap", 
				comment = "Dragon level where this modifier does not get stronger")
		public static int HEALTH_LCAP;
		@DefaultDouble(value = 0.5D, minValue = 0.3D, maxValue = 1.0D, name = "Health Modifier Type", 
				comment = "0.5 for square root, 1 for linear")
		public static double HEALTH_TYPE;
		@DefaultDouble(value = 1.0D, minValue = 0.3D, maxValue = 2.0D, name = "Strength multiplier", 
				comment = "(Math.pow(level, type) / Math.pow(level cap, type)) * multiplier")
		public static double STRENGTH_MAX_MOD;
		@DefaultInt(value = 20, minValue = 0, maxValue = 40, name = "Strength Level Cap", 
				comment = "Dragon level where this modifier does not get stronger")
		public static int STRENGTH_LCAP;
		@DefaultDouble(value = 0.5D, minValue = 0.3D, maxValue = 1.0D, name = "Strength Modifier Type", 
				comment = "0.5 for square root, 1 for linear")
		public static double STRENGTH_TYPE;
		@DefaultDouble(value = 0.3D, minValue = 0.2D, maxValue = 1.0D, name = "Speed multiplier", 
				comment = "(Math.pow(level, type) / Math.pow(level cap, type)) * multiplier")
		public static double SPEED_MAX_MOD;
		@DefaultInt(value = 6, minValue = 0, maxValue = 30, name = "Speed Level Cap", 
				comment = "Dragon level where this modifier does not get stronger")
		public static int SPEED_LCAP;
		@DefaultDouble(value = 0.5D, minValue = 0.3D, maxValue = 1.0D, name = "Speed Modifier Type", 
				comment = "0.5 for square root, 1 for linear")
		public static double SPEED_TYPE;
	}
	
	// Dragon player (add more as needed)
	@DefaultBoolean(value = false, name = "Player as silver dragon initially?", 
			comment = "Whether player should spawn initially as a silver dragon")
	public static boolean PLAYER_AS_SILVER_DRAGON_INITIALLY;
	@DefaultInt(value = 25, minValue = 0, maxValue = 1000, name = "Dragon egg spawn chance", 
			comment = "n in 1000 chance of spawning dragon eggs")
	public static int DRAGON_EGG_SPAWN_CHANCE;
	@DefaultInt(value = 20, minValue = 20, maxValue = 31, name = "Bow Position Data Watcher", 
			comment = "If there is a conflict with another mod, this can be changed.")
	public static int BOW_POSITION_WATCHER;	
	@DefaultInt(value = 21, minValue = 20, maxValue = 31, name = "Fire Dragon Data Watcher", 
			comment = "If there is a conflict with another mod, this can be changed.")
	public static int FIRE_DRAGON_WATCHER;
}
