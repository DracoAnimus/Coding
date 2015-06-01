package net.wildbill22.draco.client.gui;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.wildbill22.draco.Configs;
import net.wildbill22.draco.lib.REFERENCE;
import cpw.mods.fml.client.config.DummyConfigElement;
import cpw.mods.fml.client.config.GuiConfig;
import cpw.mods.fml.client.config.GuiConfigEntries;
import cpw.mods.fml.client.config.GuiConfigEntries.CategoryEntry;
import cpw.mods.fml.client.config.IConfigElement;

public class ModConfigGui extends GuiConfig {

	// Creates the Entry to the DRAGON_PLAYER_ABILITIES Settings
	public static class DragonPlayerAbilitiesEntry extends CategoryEntry {
		@SuppressWarnings({ "rawtypes", "unchecked" })
		private static List<IConfigElement> getConfigElements() {
			List<IConfigElement> list = new ArrayList<IConfigElement>();
			// Add all the settings from BALANCE.DRAGON_PLAYER_ABILITIES class
			list.addAll(new ConfigElement(Configs.config.getCategory(Configs.CATEGORY_BALANCE_DRAGON_PLAYER_ABILITIES)).getChildElements());
			return list;
		}

		@SuppressWarnings("rawtypes")
		public DragonPlayerAbilitiesEntry(GuiConfig owningScreen, GuiConfigEntries owningEntryList, IConfigElement prop) {
			super(owningScreen, owningEntryList, prop);
		}

		// Build the list of settings from BALANCE.DRAGON_PLAYER_ABILITIES class
		@Override
		protected GuiScreen buildChildScreen() {
			return new GuiConfig(this.owningScreen, getConfigElements(), this.owningScreen.modID, Configs.CATEGORY_BALANCE_DRAGON_PLAYER_ABILITIES, 
					false, false, REFERENCE.NAME + "Dragon Player's Abilities");
		}
	}

	// Creates the Entry to the WILD_DRAGON_PROP Settings
	public static class WildDragonPropertiesEntry extends CategoryEntry {
		@SuppressWarnings({ "rawtypes", "unchecked" })
		private static List<IConfigElement> getConfigElements() {
			List<IConfigElement> list = new ArrayList<IConfigElement>();
			// Add all the settings from BALANCE.WILD_DRAGON_PROP class
			list.addAll(new ConfigElement(Configs.config.getCategory(Configs.CATEGORY_BALANCE_WILD_FIRE_DRAGON_PROP)).getChildElements());
			return list;
		}

		@SuppressWarnings("rawtypes")
		public WildDragonPropertiesEntry(GuiConfig owningScreen, GuiConfigEntries owningEntryList, IConfigElement prop) {
			super(owningScreen, owningEntryList, prop);
		}

		// Build the list of settings from BALANCE.MOBPROP class
		@Override
		protected GuiScreen buildChildScreen() {
			return new GuiConfig(this.owningScreen, getConfigElements(), this.owningScreen.modID, Configs.CATEGORY_BALANCE_WILD_FIRE_DRAGON_PROP, 
					false, false, REFERENCE.NAME + " Wild Fire Dragon Properties");
		}
	}

	// Creates the Entry to the DP_MODIFIERS Settings
	public static class DPPropertiesEntry extends CategoryEntry {
		@SuppressWarnings({ "rawtypes", "unchecked" })
		private static List<IConfigElement> getConfigElements() {
			List<IConfigElement> list = new ArrayList<IConfigElement>();
			// Add all the settings from BALANCE.DP_MODIFIERS class
			list.addAll(new ConfigElement(Configs.config.getCategory(Configs.CATEGORY_BALANCE_DP_MODIFIERS)).getChildElements());
			return list;
		}

		@SuppressWarnings("rawtypes")
		public DPPropertiesEntry(GuiConfig owningScreen, GuiConfigEntries owningEntryList, IConfigElement prop) {
			super(owningScreen, owningEntryList, prop);
		}

		// Build the list of settings from BALANCE.DP_MODIFIERS class
		@Override
		protected GuiScreen buildChildScreen() {
			return new GuiConfig(this.owningScreen, getConfigElements(), this.owningScreen.modID, Configs.CATEGORY_BALANCE_DP_MODIFIERS, 
					false, false, REFERENCE.NAME + " Dragon Player Properties");
		}
	}

	// Creates the Entry to the MOBPROP Settings
	public static class MobPropertiesEntry extends CategoryEntry {
		@SuppressWarnings({ "rawtypes", "unchecked" })
		private static List<IConfigElement> getConfigElements() {
			List<IConfigElement> list = new ArrayList<IConfigElement>();
			// Add all the settings from BALANCE.MOBPROP class
			list.addAll(new ConfigElement(Configs.config.getCategory(Configs.CATEGORY_BALANCE_MOBPROP)).getChildElements());
			return list;
		}

		@SuppressWarnings("rawtypes")
		public MobPropertiesEntry(GuiConfig owningScreen, GuiConfigEntries owningEntryList, IConfigElement prop) {
			super(owningScreen, owningEntryList, prop);
		}

		// Build the list of settings from BALANCE.MOBPROP class
		@Override
		protected GuiScreen buildChildScreen() {
			return new GuiConfig(this.owningScreen, getConfigElements(), this.owningScreen.modID, Configs.CATEGORY_BALANCE_MOBPROP, 
					false, false, REFERENCE.NAME + " Mob Properties");
		}
	}

	// Creates the Entry to the CHEST_ITEMS Settings
	public static class ChestItemsEntry extends CategoryEntry {
		@SuppressWarnings({ "rawtypes", "unchecked" })
		private static List<IConfigElement> getConfigElements() {
			List<IConfigElement> list = new ArrayList<IConfigElement>();
			// Add all the settings from BALANCE.CHEST_ITEMS class
			list.addAll(new ConfigElement(Configs.config.getCategory(Configs.CATEGORY_CHEST_ITEMS)).getChildElements());
			return list;
		}

		@SuppressWarnings("rawtypes")
		public ChestItemsEntry(GuiConfig owningScreen, GuiConfigEntries owningEntryList, IConfigElement prop) {
			super(owningScreen, owningEntryList, prop);
		}

		// Build the list of settings from BALANCE.CHEST_ITEMS class
		@Override
		protected GuiScreen buildChildScreen() {
			return new GuiConfig(this.owningScreen, getConfigElements(), this.owningScreen.modID, Configs.CATEGORY_CHEST_ITEMS, 
					false, false, REFERENCE.NAME + " Village Blacksmith Chest Item Probablities");
		}
	}

	// Creates the Entry to the Village Settings
	public static class VillageEntry extends CategoryEntry {
		@SuppressWarnings({ "rawtypes", "unchecked" })
		private static List<IConfigElement> getConfigElements() {
			List<IConfigElement> list = new ArrayList<IConfigElement>();
			// Add all the settings from Configs.VILLAGE class
			list.addAll(new ConfigElement(Configs.config.getCategory(Configs.CATEGORY_VILLAGE)).getChildElements());
			return list;
		}

		@SuppressWarnings("rawtypes")
		public VillageEntry(GuiConfig owningScreen, GuiConfigEntries owningEntryList, IConfigElement prop) {
			super(owningScreen, owningEntryList, prop);
		}

		// Build the list of settings from Configs.VILLAGE class
		@Override
		protected GuiScreen buildChildScreen() {
			return new GuiConfig(this.owningScreen, getConfigElements(), this.owningScreen.modID, Configs.CATEGORY_VILLAGE, 
					false, false, REFERENCE.NAME + " Village Settings");
		}
	}

	// Creates the Entry to the Balance Settings
	public static class BalanceEntry extends CategoryEntry {
		@SuppressWarnings({ "rawtypes", "unchecked" })
		private static List<IConfigElement> getConfigElements() {
			List<IConfigElement> list = new ArrayList<IConfigElement>();
			list.add(new DummyConfigElement.DummyCategoryElement("Balance Level", "category.wildbill22_draco.balance_level", 
					BalanceLevelEntry.class));
			list.addAll(new ConfigElement(Configs.config.getCategory(Configs.CATEGORY_BALANCE)).getChildElements());
			return list;
		}

		@SuppressWarnings("rawtypes")
		public BalanceEntry(GuiConfig owningScreen, GuiConfigEntries owningEntryList, IConfigElement prop) {
			super(owningScreen, owningEntryList, prop);
		}

		@Override
		protected GuiScreen buildChildScreen() {
			return new GuiConfig(this.owningScreen, getConfigElements(), this.owningScreen.modID, Configs.CATEGORY_BALANCE, 
					false, false, REFERENCE.NAME + " Balance Settings");
		}
	}

	// Creates 2nd level Draco Animus Balance screen
	public static class BalanceLevelEntry extends CategoryEntry {

		@SuppressWarnings("rawtypes")
		public BalanceLevelEntry(GuiConfig owningScreen, GuiConfigEntries owningEntryList, IConfigElement configElement) {
			super(owningScreen, owningEntryList, configElement);
		}

		@SuppressWarnings({ "unchecked", "rawtypes" })
		@Override
		protected GuiScreen buildChildScreen() {
			return new GuiConfig(this.owningScreen,
					(new ConfigElement(Configs.config.getCategory(Configs.CATEGORY_BALANCE_LEVELING))).getChildElements(), 
					this.owningScreen.modID, Configs.CATEGORY_BALANCE_LEVELING, false, false, REFERENCE.NAME + " Balance");
		}
	}

	public static class BalancePlayerModEntry extends CategoryEntry {
		@SuppressWarnings("rawtypes")
		public BalancePlayerModEntry(GuiConfig owningScreen, GuiConfigEntries owningEntryList, IConfigElement configElement) {
			super(owningScreen, owningEntryList, configElement);
		}
	}

	// This creates the Initial Screen (Draco Animus Config)
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static List<IConfigElement> getConfigElements() {
		List<IConfigElement> list = new ArrayList<IConfigElement>();
		list.addAll(new ConfigElement(Configs.config.getCategory(Configs.CATEGORY_GENERAL)).getChildElements());
		list.add(new DummyConfigElement.DummyCategoryElement("balance_dragon_player_properties", "category.wildbill22_draco.balance_dragon_player_properties", DPPropertiesEntry.class));
		list.add(new DummyConfigElement.DummyCategoryElement("mob_properties", "category.wildbill22_draco.mob_properties", MobPropertiesEntry.class));
		list.add(new DummyConfigElement.DummyCategoryElement("wild_fire_dragon_properties", "category.wildbill22_draco.wild_fire_dragon_properties", WildDragonPropertiesEntry.class));
		list.add(new DummyConfigElement.DummyCategoryElement("chest_items", "category.wildbill22_draco.chest_items", ChestItemsEntry.class));
		list.add(new DummyConfigElement.DummyCategoryElement("village", "category.wildbill22_draco.village", VillageEntry.class));
		list.add(new DummyConfigElement.DummyCategoryElement("balance", "category.wildbill22_draco.balance", BalanceEntry.class));
		list.add(new DummyConfigElement.DummyCategoryElement("dragon_player_abilities", "category.wildbill22_draco.dragon_player_abilities", DragonPlayerAbilitiesEntry.class));
		return list;
	}

	public ModConfigGui(GuiScreen parentScreen) {
		super(parentScreen, getConfigElements(), REFERENCE.MODID, true, false, REFERENCE.NAME + " Config");
	}
}
