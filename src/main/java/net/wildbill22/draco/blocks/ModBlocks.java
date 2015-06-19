package net.wildbill22.draco.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.wildbill22.draco.Creative_Tab;
import net.wildbill22.draco.items.ModItems;
import net.wildbill22.draco.lib.REFERENCE;

public class ModBlocks extends Block {
	public static Block charredGround;
 	public static Block scorchedCobblestone;
 	public static Block temporaryHoard;
 	
 	// Eggs
 	public static Block goldDragonEgg;
 	public static Block silverDragonEgg;
 	public static Block skeletonDragonEgg;
 	public static Block waterDragonEgg;
 	public static Block earthDragonEgg;
 	public static Block nightDragonEgg;
 	public static Block fireDragonEgg;
 	public static Block eagleDragonEgg;
	
	public static void preInit() {			
		// Misc 
		charredGround = new BlockCharredGround().setLightLevel(0.5F);
		scorchedCobblestone = new BlockScorchedCobblestone();
		temporaryHoard = new TemporaryHoard().setBlockName(TemporaryHoard.textureName).setCreativeTab(Creative_Tab.TabDraco_Animus);
		GameRegistry.registerBlock(charredGround, BlockCharredGround.name);
		GameRegistry.registerBlock(scorchedCobblestone, BlockScorchedCobblestone.name);
		GameRegistry.registerBlock(temporaryHoard, TemporaryHoard.textureName);
		
		// Eggs
//		goldDragonEgg = new BlockGoldDragonEgg();
//		silverDragonEgg = new BlockSilverDragonEgg();
//		skeletonDragonEgg = new BlockSkeletonDragonEgg();
//		waterDragonEgg = new BlockWaterDragonEgg();
//		earthDragonEgg = new BlockEarthDragonEgg();
//		nightDragonEgg = new BlockNightDragonEgg();
//		fireDragonEgg = new BlockFireDragonEgg();
//		eagleDragonEgg = new BlockEagleDragonEgg();
		goldDragonEgg = new BlockDracoEgg("goldDragonEggBlock", ModItems.goldDragonEgg);
		silverDragonEgg = new BlockDracoEgg("silverDragonEggBlock", ModItems.silverDragonEgg);
		skeletonDragonEgg = new BlockDracoEgg("skeletonDragonEggBlock", ModItems.skeletonDragonEgg);
		waterDragonEgg = new BlockDracoEgg("waterDragonEggBlock", ModItems.waterDragonEgg);
		earthDragonEgg = new BlockDracoEgg("earthDragonEggBlock", ModItems.earthDragonEgg);
		nightDragonEgg = new BlockDracoEgg("nightDragonEggBlock", ModItems.nightDragonEgg);
		fireDragonEgg = new BlockDracoEgg("fireDragonEggBlock", ModItems.fireDragonEgg);
		eagleDragonEgg = new BlockDracoEgg("eagleDragonEggBlock", ModItems.eagleDragonEgg);
		GameRegistry.registerBlock(goldDragonEgg, "goldDragonEggBlock");
		GameRegistry.registerBlock(silverDragonEgg, "silverDragonEggBlock");
		GameRegistry.registerBlock(skeletonDragonEgg, "skeletonDragonEggBlock");
		GameRegistry.registerBlock(waterDragonEgg, "waterDragonEggBlock");		
		GameRegistry.registerBlock(earthDragonEgg, "earthDragonEggBlock");		
		GameRegistry.registerBlock(nightDragonEgg, "nightDragonEggBlock");		
		GameRegistry.registerBlock(fireDragonEgg, "fireDragonEggBlock");		
		GameRegistry.registerBlock(eagleDragonEgg, "eagleDragonEggBlock");		
	}

	public static void init() {
//		ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH, new WeightedRandomChestContent(new ItemStack(goldCoin), 1, 3, 50));
	}

	/**
	 * Makes a block and sets most of its properties
	 * @param unlocalizedname
	 * @param texturename
	 * @param material
	 */
	public ModBlocks(String unlocalizedname, String texturename, Material material){
		super(material);
		
		setBlockName(unlocalizedname);
		setBlockTextureName(REFERENCE.MODID + ":" + unlocalizedname);
		setCreativeTab(Creative_Tab.TabDraco_Animus);
	}
}
