package net.wildbill22.draco.crafting;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.wildbill22.draco.blocks.ModBlocks;
import net.wildbill22.draco.items.ModItems;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModCraftingRecipes {
	public static void init() {
		// Dragon Staffs
//		GameRegistry.addShapedRecipe(new ItemStack(ModItems.staff, 1), 
//				" E ",
//				" S ",
//				" S ",
//				'S', Items.stick, 'E', ModItems.Egg);
		
		// Need to change to crafting an silver dragon egg
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.silverDragonEgg, 1), 
				Items.egg, ModItems.villagerHeart);

		// Blocks
		GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.scorchedCobblestone, 1), 
				" C ",
				"CRC",
				" C ",
				'C', Items.coal, 'R', Blocks.cobblestone);
		GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.temporaryHoard, 1), 
				"CCC",
				"CGC",
				"CCC",
				'C', Blocks.cobblestone, 'G', ModItems.goldCoin);
	}	
}
