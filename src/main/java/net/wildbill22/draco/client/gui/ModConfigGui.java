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
					false, false, REFERENCE.NAME + " Balance");
		}
	}

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

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static List<IConfigElement> getConfigElements() {
		List<IConfigElement> list = new ArrayList<IConfigElement>();
		list.addAll(new ConfigElement(Configs.config.getCategory(Configs.CATEGORY_GENERAL)).getChildElements());
		list.addAll(new ConfigElement(Configs.config.getCategory(Configs.CATEGORY_VILLAGE)).getChildElements());
		list.add(new DummyConfigElement.DummyCategoryElement("balance", "category.wildbill22_draco.balance", BalanceEntry.class));
		return list;
	}

	public ModConfigGui(GuiScreen parentScreen) {
		super(parentScreen, getConfigElements(), REFERENCE.MODID, true, false, REFERENCE.NAME + " Config");
	}
}
