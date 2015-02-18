package net.wildbill22.draco.items.weapons;

import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.common.ChestGenHooks;
import net.minecraftforge.common.util.EnumHelper;
import net.wildbill22.draco.lib.Reference;
import cpw.mods.fml.common.registry.GameRegistry;
import net.wildbill22.draco.items.weapons.ItemDragonSlayerSword;;

public class ModWeapons {

	// Tool Materials
	static ToolMaterial dragonsBreath = EnumHelper.addToolMaterial("DragonsBreath", 3, 1000, 9.5F, 3.5F, 10);
	
	// Weapons
	public static Item dragonSlayerSword = new ItemDragonSlayerSword(dragonsBreath, "dragonSlayerSword");
	
	public static void preInit() {
		GameRegistry.registerItem(dragonSlayerSword, Reference.MODID + dragonSlayerSword.getUnlocalizedName().substring(5));
	}

	public static void init() {
		ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH, new WeightedRandomChestContent(new ItemStack(dragonSlayerSword), 1, 1, 10));
	}
}
