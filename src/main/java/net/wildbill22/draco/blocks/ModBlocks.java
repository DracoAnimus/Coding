package net.wildbill22.draco.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.common.ChestGenHooks;
import net.wildbill22.draco.Creative_Tab;
import net.wildbill22.draco.lib.REFERENCE;

public class ModBlocks extends Block {
	// Constants
	public static final String TEMPORARY_HOARD_TEXTURENAME = "temporaryHoard";
	public static final String TEMPORARY_HOARD_DISPLAYNAME = "Temporary Hoard";
	
	public static Block charredGround;
 	public static Block scorchedCobblestone;
 	public static Block temporaryHoard;
	
	public static void preInit() {
			
		charredGround = new BlockCharredGround("charredGround", "charredGround").setLightLevel(0.5F);
		scorchedCobblestone = new BlockScorchedCobblestone("scorchedCobblestone", "scorchedCobblestone");
		scorchedCobblestone = new BlockScorchedCobblestone("scorchedCobblestone", "scorchedCobblestone");
		temporaryHoard = new TemporaryHoard().setBlockName(TEMPORARY_HOARD_TEXTURENAME).setCreativeTab(Creative_Tab.TabDraco_Animus);
				
		GameRegistry.registerBlock(charredGround, "charredGround");
		GameRegistry.registerBlock(scorchedCobblestone, "scorchedCobblestone");
//		GameRegistry.registerBlock(temporaryHoard, "temporaryHoard");
		GameRegistry.registerBlock(temporaryHoard, TEMPORARY_HOARD_TEXTURENAME);
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
