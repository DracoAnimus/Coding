package net.wildbill22.draco.items;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.common.ChestGenHooks;
import net.minecraftforge.common.util.EnumHelper;
import net.wildbill22.draco.Creative_Tab;
import net.wildbill22.draco.entities.dragons.DragonRegistry;
import net.wildbill22.draco.entities.dragons.DragonRegistry.IDragonEggHandler;
import net.wildbill22.draco.items.dragoneggs.ItemEagleDragonEgg;
import net.wildbill22.draco.items.dragoneggs.ItemEarthDragonEgg;
import net.wildbill22.draco.items.dragoneggs.ItemFireDragonEgg;
import net.wildbill22.draco.items.dragoneggs.ItemGoldDragonEgg;
import net.wildbill22.draco.items.dragoneggs.ItemNightDragonEgg;
import net.wildbill22.draco.items.dragoneggs.ItemSilverDragonEgg;
import net.wildbill22.draco.items.dragoneggs.ItemSkeletonDragonEgg;
import net.wildbill22.draco.items.dragoneggs.ItemWaterDragonEgg;
import net.wildbill22.draco.lib.BALANCE;
import net.wildbill22.draco.lib.REFERENCE;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModItems extends Item {
	//Tool Materials
	static ToolMaterial SPEAR = EnumHelper.addToolMaterial("SPEAR", 3, 500, 9.5F, 3.0F, 10);

	// All items in the mod:
	public static Item dragonScales;
	public static Item spear;
	public static Item goldCoin;
	public static Item fireball;
	public static Item explosiveFireball;
	public static Item rock;
	public static Item goldDragonEgg;
	public static Item skeletonDragonEgg;
	public static Item silverDragonEgg;
	public static Item waterDragonEgg;
	public static Item earthDragonEgg;
	public static Item nightDragonEgg;
	public static Item fireDragonEgg;
	public static Item eagleDragonEgg;

	// Food
	public static ItemDragonFood villagerHeart; 
	public static ItemDragonFood squid;
	public static ItemDragonFood villagerSkull;
	
	public static void preInit() {
		dragonScales = new ItemDragonScales();
		GameRegistry.registerItem(dragonScales, ItemDragonScales.name);

		goldCoin = new ItemGoldCoin();
		GameRegistry.registerItem(goldCoin, ItemGoldCoin.name);

		spear = new ItemSpear();
		GameRegistry.registerItem(spear, ItemSpear.name);

		fireball = new ItemMyFireball();
		GameRegistry.registerItem(fireball, ItemMyFireball.name);

		explosiveFireball = new ItemMyExplosive();
		GameRegistry.registerItem(explosiveFireball, ItemMyExplosive.name);

		rock = new ItemMyRock();
		GameRegistry.registerItem(rock, ItemMyRock.name);
		
		// Dragon Food - only dragons can eat this food! Register foods before eggs!
		// In wiki: Food points, Saturation ration. Potion has: id, seconds effect lasts, level (0 - X, depends on potion)
//		GameRegistry.registerItem(villagerHeart = new ItemModFood("villagerHeart", 4, 0.6f, false), "villagerHeart");
		// Poison, like potatoes
		GameRegistry.registerItem(villagerHeart = new ItemDragonFood("villagerHeart", 6, 1.2f, false, 
				new PotionEffect(Potion.poison.id, 5, 0)), "villagerHeart");
		villagerHeart.setAlwaysEdible();
		GameRegistry.registerItem(squid = new ItemDragonFood("squid", 4, 0.6f, false, 
				new PotionEffect(Potion.poison.id, 5, 0)), "squid");
		GameRegistry.registerItem(villagerSkull = new ItemDragonFood("villagerSkull", 4, 0.6f, false, 
				new PotionEffect(Potion.poison.id, 5, 0)), "villagerSkull");

		// Eggs created after food has been registered (else null pointer exception)
		silverDragonEgg = new ItemSilverDragonEgg();
		skeletonDragonEgg = new ItemSkeletonDragonEgg();
		goldDragonEgg = new ItemGoldDragonEgg();
		waterDragonEgg = new ItemWaterDragonEgg();
		earthDragonEgg = new ItemEarthDragonEgg();
		nightDragonEgg = new ItemNightDragonEgg();
		fireDragonEgg = new ItemFireDragonEgg();
		eagleDragonEgg = new ItemEagleDragonEgg();
		GameRegistry.registerItem(goldDragonEgg, ItemGoldDragonEgg.name);
		GameRegistry.registerItem(skeletonDragonEgg, ItemSkeletonDragonEgg.name);
		GameRegistry.registerItem(silverDragonEgg, ItemSilverDragonEgg.name);
		GameRegistry.registerItem(waterDragonEgg, ItemWaterDragonEgg.name);
		GameRegistry.registerItem(earthDragonEgg, ItemEarthDragonEgg.name);
		GameRegistry.registerItem(nightDragonEgg, ItemNightDragonEgg.name);
		GameRegistry.registerItem(fireDragonEgg, ItemFireDragonEgg.name);
		GameRegistry.registerItem(eagleDragonEgg, ItemEagleDragonEgg.name);

		// Dragon Egg Registry
		DragonRegistry.instance().registerDragonEgg((IDragonEggHandler) silverDragonEgg);
		DragonRegistry.instance().registerDragonEgg((IDragonEggHandler) skeletonDragonEgg);
		DragonRegistry.instance().registerDragonEgg((IDragonEggHandler) goldDragonEgg);		
		DragonRegistry.instance().registerDragonEgg((IDragonEggHandler) waterDragonEgg);		
		DragonRegistry.instance().registerDragonEgg((IDragonEggHandler) earthDragonEgg);		
		DragonRegistry.instance().registerDragonEgg((IDragonEggHandler) nightDragonEgg);		
		DragonRegistry.instance().registerDragonEgg((IDragonEggHandler) fireDragonEgg);		
		DragonRegistry.instance().registerDragonEgg((IDragonEggHandler) eagleDragonEgg);		
	}

	public static void init() {
		// Mod items
		ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH, new WeightedRandomChestContent(
				new ItemStack(goldCoin), 1, 10, BALANCE.CHEST_ITEMS.VILLAGE_BLACKSMITH_GOLD_COINS));
//		ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH, new WeightedRandomChestContent(
//				new ItemStack(fireball), 1, 10, BALANCE.CHEST_ITEMS.VILLAGE_BLACKSMITH_FIREBALLS));
//		ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH, new WeightedRandomChestContent(
//				new ItemStack(explosiveFireball), 1, 10, BALANCE.CHEST_ITEMS.VILLAGE_BLACKSMITH_FIREBALLS));
		
		// Other useful stuff in the village blacksmith chest
		ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH, new WeightedRandomChestContent(
				new ItemStack(Items.diamond_sword), 1, 1, BALANCE.CHEST_ITEMS.VILLAGE_BLACKSMITH_DIAMOND_ARMOR));
		ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH, new WeightedRandomChestContent(
				new ItemStack(Items.diamond_boots), 1, 1, BALANCE.CHEST_ITEMS.VILLAGE_BLACKSMITH_DIAMOND_ARMOR));
		ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH, new WeightedRandomChestContent(
				new ItemStack(Items.diamond_chestplate), 1, 1, BALANCE.CHEST_ITEMS.VILLAGE_BLACKSMITH_DIAMOND_ARMOR));
		ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH, new WeightedRandomChestContent(
				new ItemStack(Items.diamond_helmet), 1, 1, BALANCE.CHEST_ITEMS.VILLAGE_BLACKSMITH_DIAMOND_ARMOR));
		ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH, new WeightedRandomChestContent(
				new ItemStack(Items.diamond_leggings), 1, 1, BALANCE.CHEST_ITEMS.VILLAGE_BLACKSMITH_DIAMOND_ARMOR));
		
		// Stuff in the stronghold chests
		ChestGenHooks.addItem(ChestGenHooks.STRONGHOLD_CORRIDOR, new WeightedRandomChestContent(
				new ItemStack(villagerSkull), 1, 5, BALANCE.CHEST_ITEMS.STRONGHOLD_VILLAGER_SKULL));
		ChestGenHooks.addItem(ChestGenHooks.STRONGHOLD_CROSSING, new WeightedRandomChestContent(
				new ItemStack(villagerSkull), 1, 5, BALANCE.CHEST_ITEMS.STRONGHOLD_VILLAGER_SKULL));
		ChestGenHooks.addItem(ChestGenHooks.STRONGHOLD_LIBRARY, new WeightedRandomChestContent(
				new ItemStack(villagerSkull), 1, 5, BALANCE.CHEST_ITEMS.STRONGHOLD_VILLAGER_SKULL));
	}
	
	/**
	 * Makes a item and sets most of its properties (used when calling API)
	 * @param unlocalizedname
	 * @param texturename
	 * @param modid
	 */
	public ModItems(String unlocalizedname, String texturename, String modid){
		super();
		
		this.setCreativeTab(Creative_Tab.TabDraco_Animus);
		this.setUnlocalizedName(modid + "_" + unlocalizedname);
		this.setTextureName(modid + ":" + texturename);
	}
	
	/**
	 * Makes a item and sets most of its properties
	 * @param unlocalizedname
	 * @param texturename
	 */
	public ModItems(String unlocalizedname, String texturename){
		super();
		
		this.setCreativeTab(Creative_Tab.TabDraco_Animus);
		this.setUnlocalizedName(REFERENCE.Unlocalized_Path + unlocalizedname);
		this.setTextureName(REFERENCE.Texture_Path + texturename);
	}
	
	/**
	 * Makes a item and sets most of its properties
	 * Use this one when you want to use registerIcons
	 * @param unlocalizedname
	 */
	public ModItems(String unlocalizedname) {
		super();		

		this.setCreativeTab(Creative_Tab.TabDraco_Animus);
		this.setUnlocalizedName(REFERENCE.Unlocalized_Path + unlocalizedname);
	}
	
	// IconRegister renamed to IIconRegister
//	@Override
//	@SideOnly(Side.CLIENT)
//	public void registerIcons(IIconRegister iconRegister) {
//		int beginIndex = getUnlocalizedName().indexOf('_');
//		itemIcon = iconRegister.registerIcon(Reference.Texture_Path + getUnlocalizedName().substring(beginIndex + 1).toLowerCase());
//	}
}
