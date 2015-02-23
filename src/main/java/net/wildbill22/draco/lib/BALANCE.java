package net.wildbill22.draco.lib;

/**
 * All constants which could be used to balance the mod should be stored here
 *
 */
public final class BALANCE {
	public static class LEVELING {
		@DefaultInt(value = 20, comment = "Amount of coins needed for for each player dragon level")
		public static int TEMPORARY_HOARD_COINS_TO_NEXT_LEVEL;
	}
	public static class MOBPROP {
		@DefaultDouble(value = 35.0D, comment = "Maximum health")
		public static double GUARD_MAX_HEALTH;
		@DefaultDouble(value = 5.0D, comment = "Attack damage")
		public static double GUARD_ATTACK_DAMAGE;
		@DefaultDouble(value = 0.35F, comment = "Movement speed")
		public static double GUARD_MOVEMENT_SPEED;
		@DefaultInt(value = 75, comment = "Probability of spawning")
		public static int GUARD_SPAWN_PROB;
		@DefaultInt(value = 6, comment = "Maximum number of guards that will spawn in a village")
		public static int GUARD_MAX_PER_VILLAGE;
	}
	
	// Mob behavior
	@DefaultInt(20)
	public static int GUARD_SPAWN_PROBE;

	// Dragon player
	@DefaultBoolean(value = true, comment = "Whether player should spawn initially as a silver dragon")
	public static boolean PLAYER_AS_DRAGON_INITIALLY;

	@DefaultInt(value = 10, comment = "How far away creepers will stay from dragon")
	public static int CREEPER_AVOID_LEVEL;
}
