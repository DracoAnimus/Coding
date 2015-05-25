package net.wildbill22.draco.items.weapons;

import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.common.ChestGenHooks;
import net.minecraftforge.common.util.EnumHelper;
import net.wildbill22.draco.lib.BALANCE;
import cpw.mods.fml.common.registry.GameRegistry;
import net.wildbill22.draco.entities.dragons.DragonRegistry;
import net.wildbill22.draco.entities.dragons.DragonRegistry.IDragonStaffHandler;
import net.wildbill22.draco.items.weapons.ItemDragonSlayerSword;

public class ModWeapons {

	// Tool Materials, has name, harvest level, maxUses, efficiency, damage (added), enchantability
	static ToolMaterial dragonsBreath = EnumHelper.addToolMaterial("DragonsBreath", 3, 1000, 9.5F, 3.5F, 10);
	static ToolMaterial LONGSWORD = EnumHelper.addToolMaterial("LONGSWORD", 3, 1000, 9.5F, 5.5F, 10);
	static ToolMaterial BATTLEAXE = EnumHelper.addToolMaterial("BATTLEAXE", 3, 1000, 9.5F, 3.5F, 10);
	static ToolMaterial MACE = EnumHelper.addToolMaterial("MACE", 3, 1000, 9.5F, 3.5F, 10);
	static ToolMaterial STAFF = EnumHelper.addToolMaterial("STAFF", 3, 2000, 9.5F, 1.5F, 10);
	
	// Weapons
	public static Item dragonSlayerSword = new ItemDragonSlayerSword(dragonsBreath);
	public static Item longSword = new ItemLongSword(LONGSWORD);
	public static Item battleAxe = new ItemBattleAxe(BATTLEAXE);
	public static Item mace = new ItemMace(MACE);
	public static Item longBow = new ItemLongBow();
	public static Item crossbow = new ItemCrossbow();
	public static Item silverDragonStaff = new ItemSilverDragonStaff(STAFF);
	public static Item skeletonDragonStaff = new ItemSkeletonDragonStaff(STAFF);
	public static Item goldDragonStaff = new ItemGoldDragonStaff(STAFF);
	public static Item waterDragonStaff = new ItemWaterDragonStaff(STAFF);
	
	public static void preInit() {
		GameRegistry.registerItem(dragonSlayerSword, ItemDragonSlayerSword.name);
		GameRegistry.registerItem(longSword, ItemLongSword.name);
		GameRegistry.registerItem(battleAxe, ItemBattleAxe.name);
		GameRegistry.registerItem(mace, ItemMace.name);
		GameRegistry.registerItem(longBow, ItemLongBow.name);
		GameRegistry.registerItem(crossbow, ItemCrossbow.name);
		GameRegistry.registerItem(silverDragonStaff, ItemSilverDragonStaff.name);
		GameRegistry.registerItem(skeletonDragonStaff, ItemSkeletonDragonStaff.name);
		GameRegistry.registerItem(goldDragonStaff, ItemGoldDragonStaff.name);
		GameRegistry.registerItem(waterDragonStaff, ItemWaterDragonStaff.name);
		
		// Dragon Staff Registry
//		DragonRegistry.instance().registerDragonStaff((IDragonStaffHandler) goldDragonStaff);
//		DragonRegistry.instance().registerDragonStaff((IDragonStaffHandler) skeletonDragonStaff);
//		DragonRegistry.instance().registerDragonStaff((IDragonStaffHandler) silverDragonStaff);
	}

	public static void init() {
		ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH, new WeightedRandomChestContent(
				new ItemStack(dragonSlayerSword), 1, 1, BALANCE.CHEST_ITEMS.VILLAGE_BLACKSMITH_DRAGON_SLAYER));
//		ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH, new WeightedRandomChestContent(new ItemStack(longSword), 1, 1, 10));
//		ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH, new WeightedRandomChestContent(new ItemStack(battleAxe), 1, 1, 10));
//		ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH, new WeightedRandomChestContent(new ItemStack(mace), 1, 1, 10));
	}
}
