package net.wildbill22.draco.generation.villages;

import java.util.Random;

import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.wildbill22.draco.lib.REFERENCE;
import cpw.mods.fml.common.registry.VillagerRegistry;

public class BarWenchCreationHandler implements VillagerRegistry.IVillageTradeHandler {
	public static final int BAR_WENCH = 42;
	private static ResourceLocation texture;
	
	@SuppressWarnings("unchecked")
	public void manipulateTradesForVillager(EntityVillager villager, MerchantRecipeList recipeList, Random random) {
		// Awkward potion
		recipeList.add(new MerchantRecipe(new ItemStack(Items.potionitem, 3, 16), new ItemStack(Items.emerald, 2)));
		 // night vision
		recipeList.add(new MerchantRecipe(new ItemStack(Items.emerald, 1), new ItemStack(Items.potionitem, 2, 33)));		
	}
	
	// Call from mod's init
	public static void init() {
		BarWenchCreationHandler barWench = new BarWenchCreationHandler();
		VillagerRegistry.instance().registerVillageTradeHandler(BarWenchCreationHandler.BAR_WENCH, barWench);
		VillagerRegistry.instance().registerVillagerId(BarWenchCreationHandler.BAR_WENCH);
		texture = new ResourceLocation(REFERENCE.MODID + ":textures/entity/barwench.png");
		VillagerRegistry.instance().registerVillagerSkin(BarWenchCreationHandler.BAR_WENCH, texture);		
	}
}
