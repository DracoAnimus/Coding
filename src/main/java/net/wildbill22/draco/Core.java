package net.wildbill22.draco;

import net.wildbill22.draco.blocks.ModBlocks;
import net.wildbill22.draco.crafting.ModCraftingRecipes;
import net.wildbill22.draco.lib.Reference;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
	
@Mod(modid = Reference.MODID, name = Reference.NAME, version = Reference.VERSION)
public class Core {

	@EventHandler
	public void preInit(FMLPreInitializationEvent event){
		ModCraftingRecipes.init();
	}

	@EventHandler
	public static void PreInit(FMLPreInitializationEvent event) {
	ModBlocks.init();
	Creative_Tab.OtherInfo();
	}
	
	@EventHandler
	public static void Init(FMLInitializationEvent event) {
		
	}
}
