package net.wildbill22.draco;

import java.io.File;
import java.lang.reflect.Field;

import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.Configuration;
import net.wildbill22.draco.lib.BALANCE;
import net.wildbill22.draco.lib.DefaultBoolean;
import net.wildbill22.draco.lib.DefaultDouble;
import net.wildbill22.draco.lib.DefaultInt;
import net.wildbill22.draco.lib.LogHelper;
import net.wildbill22.draco.lib.REFERENCE;
import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class Configs {
	public static final String CATEGORY_GENERAL = Configuration.CATEGORY_GENERAL;
	public static final String CATEGORY_BALANCE = "balance";
	public static final String CATEGORY_BALANCE_LEVELING = "balance_leveling";
	public static final String CATEGORY_BALANCE_MOBPROP = "balance_mob_properties";
	public static final String CATEGORY_BALANCE_WILD_FIRE_DRAGON_PROP = "balance_wild_fire_dragon_properties";
	public static final String CATEGORY_CHEST_ITEMS = "chest_items";
	public static final String CATEGORY_BALANCE_DP_MODIFIERS = "balance_dragon_player_properties";
	public static final String CATEGORY_VILLAGE = "village_settings";

	public static void init(File configFile) {
		if (config == null) {
			config = new Configuration(configFile);
		}
		loadConfiguration();
	}
	/**
	 * Loads/refreshes the configuration and adds comments if there aren't any
	 * {@link #init(File) init} has to be called once before using this
	 */
	private static void loadConfiguration() {

		// Village
		villageConfiguration();

		// Balance
		balanceConfiguration();
		balanceLevelingConfiguration();
		balanceMobpropConfiguration();
		balanceWildDragonPropConfiguration();
		balanceChestItemsConfiguration();
		balanceDPModifiersConfiguration();
		
		// Balance Leveling
		balanceLevelingConfiguration();

		if (config.hasChanged()) {
			LogHelper.info("Configs: Configuration has changed, saving.");
			config.save();
		}
	}
	
	public static void villageConfiguration() {
		ConfigCategory cat_village = config.getCategory(CATEGORY_VILLAGE);
		cat_village.setComment("Here you can configure the village generation");
		loadFields(cat_village, VILLAGE.class);
		
		if (VILLAGE.village_minDist < 0) {
			LogHelper.error("VillageDensity: Invalid config: Minimal distance must be non-negative.");
			VILLAGE.village_gen_enabled = false;
		}
		if (VILLAGE.village_minDist >= VILLAGE.village_density) {
			LogHelper.error("VillageDensity: Invalid config: Minimal distance must be smaller than density.");
			VILLAGE.village_gen_enabled = false;
		}
		if (VILLAGE.village_size < 0) {
			VILLAGE.village_gen_enabled = false;
			LogHelper.error("VillageDensity: Invalid config: Size must be non-negative.");
		}		
	}
	
	public static void balanceConfiguration() {
		// Categories
		ConfigCategory cat_balance = config.getCategory(CATEGORY_BALANCE);
		cat_balance.setComment("You can adjust these values to change the balancing of this mod");
		loadFields(cat_balance, BALANCE.class);

		// This can only fix a problem for the game to be able to run, has no effect on what 
		// is in the configuration.
//		if (BALANCE.PLAYER_AS_SILVER_DRAGON_INITIALLY != true)
//			BALANCE.PLAYER_AS_SILVER_DRAGON_INITIALLY = true;
	}

	public static void balanceLevelingConfiguration() {	
		ConfigCategory cat_balance_leveling = config.getCategory(CATEGORY_BALANCE_LEVELING);
		cat_balance_leveling.setComment("You can adjust these values to change the level up requirements");
		loadFields(cat_balance_leveling, BALANCE.LEVELING.class);
	}

	public static void balanceWildDragonPropConfiguration() {
		ConfigCategory cat_balance_mobprop = config.getCategory(CATEGORY_BALANCE_MOBPROP);
		cat_balance_mobprop.setComment("You can adjust the properties of the added mobs");
		loadFields(cat_balance_mobprop, BALANCE.MOBPROP.class);		
	}

	public static void balanceMobpropConfiguration() {
		ConfigCategory cat_balance_wild_dragon_prop = config.getCategory(CATEGORY_BALANCE_WILD_FIRE_DRAGON_PROP);
		cat_balance_wild_dragon_prop.setComment("You can adjust the properties of the wild fire dragon");
		loadFields(cat_balance_wild_dragon_prop, BALANCE.WILD_FIRE_DRAGON_PROP.class);		
	}

	public static void balanceChestItemsConfiguration() {
		ConfigCategory cat_chest_items = config.getCategory(CATEGORY_CHEST_ITEMS);
		cat_chest_items.setComment("You can adjust the probability of items in village blacksmith chests");
		loadFields(cat_chest_items, BALANCE.CHEST_ITEMS.class);
	}

	public static void balanceDPModifiersConfiguration() {
		ConfigCategory cat_balance_dp_modifiers = config.getCategory(CATEGORY_BALANCE_DP_MODIFIERS);
		cat_balance_dp_modifiers.setComment("You can adjust the dragon player modifiers");
		loadFields(cat_balance_dp_modifiers, BALANCE.DP_MODIFIERS.class);		
	}

	/**
	 * This method makes variables from a class available in the config file.
	 * To use this, the given class can only contain static non-final variables,
	 * which should have a '@Default*' annotation containing the default value.
	 * Currently only boolean, int, and double are supported
	 * 
	 * @param cat
	 *            Config Category to put the properties inside
	 * @param cls
	 *            Class to go through
	 * @author Maxanier
	 */
	@SuppressWarnings("rawtypes")
	private static void loadFields(ConfigCategory cat, Class cls) {
		for (Field f : cls.getDeclaredFields()) {
			String name = f.getName();
			Class type = f.getType();
			try {
				if (type == int.class) {
					// Possible exception should not be caught so you can't forget a default value					
					DefaultInt a = f.getAnnotation(DefaultInt.class); 
					f.set(null, config.get(cat.getQualifiedName(), a.name(), a.value(), 
							a.comment(), a.minValue(), a.maxValue()).getInt());
				} else if (type == double.class) {
					// Possible exception should not be caught so you can't forget a default value					
					DefaultDouble a = f.getAnnotation(DefaultDouble.class); 
					f.set(null, config.get(cat.getQualifiedName(), a.name(), a.value(), 
							a.comment(), a.minValue(), a.maxValue()).getDouble());
				} else if (type == boolean.class) {
					DefaultBoolean a = f.getAnnotation(DefaultBoolean.class); 
					f.set(null, config.get(cat.getQualifiedName(), a.name(), a.value(), a.comment()).getBoolean());
				}
			} catch (NullPointerException e1) {
				LogHelper.error("Configs: Author probably forgot to specify a default value for " + name + " in " + cls.getCanonicalName() + e1);
				throw new Error("Please check your default values");
			} catch (Exception e) {
				LogHelper.error("Configs: Can't set " + cls.getName() + " values" + e);
				throw new Error("Please check your DracoAnimus config file");
			}
		}
	}
	
	public static Configuration config;

	@SubscribeEvent
	public void onConfigurationChanged(ConfigChangedEvent.OnConfigChangedEvent e) {
		if (e.modID.equalsIgnoreCase(REFERENCE.MODID)) {
			// Resync configs
			LogHelper.info("Configs: Configuration has changed, loading.");
			Configs.loadConfiguration();
		}
	}

	public static class VILLAGE {
		@DefaultBoolean(value = true, name = "Enable More Villages", comment = "Should the custom generator be injected? (Enables/Disables the village mod)")
		public static boolean village_gen_enabled;
		@DefaultInt(value = 15, minValue = 6, maxValue = 64, name = "Density", 
				comment = "Minecraft will try to generate 1 village per NxN chunk area. Vanilla: 32")
		public static int village_density;
		@DefaultInt(value = 4, minValue = 2, maxValue = 16, name = "Minimum Distance", 
				comment = "Village centers will be at least N chunks apart. Must be smaller than density. Vanilla: 8")
		public static int village_minDist;
		@DefaultInt(value = 0, minValue = 0, maxValue = 10, name = "Size", 
				comment = "NO CHANGE RECOMMENED: A higher size increases the overall spawn weight of buildings.")
		public static int village_size;
		@DefaultBoolean(value = true, name = "Enable Taverns", comment = "Should Taverns be added to villages?")
		public static boolean village_taverns_enabled;		
		@DefaultBoolean(value = true, name = "Enable Guard Towers", comment = "Should Guard Towers be added to villages?")
		public static boolean village_guard_tower_enabled;		
		@DefaultInt(value = 20, minValue = 0, maxValue = 100, name = "City Probability", 
				comment = "Controls how often cities can appear, 10 would be an average value.")
		public static int city_weight;
		@DefaultInt(value = 30, minValue = 0, maxValue = 100, name = "Town Probability", 
				comment = "Controls how often towns can appear, 10 would be an average value.")
		public static int town_weight;
	}
}
