package net.wildbill22.draco.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;

public class ModBlocks {
	
	public static Block charredGround;
 	public static Block scorchedCobblestone;	
	
	public static void preInit() {
			
		charredGround = new BlockCharredGround();
		scorchedCobblestone = new BlockScorchedCobblestone();
				
		GameRegistry.registerBlock(charredGround, "charredGround");
		GameRegistry.registerBlock(scorchedCobblestone, "scorchedCobblestone");
	}
}
