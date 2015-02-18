package net.wildbill22.draco;

import net.wildbill22.draco.blocks.ModBlocks;
import net.wildbill22.draco.crafting.ModCraftingRecipes;
import net.wildbill22.draco.entities.ModEntities;
import net.wildbill22.draco.items.weapons.ModWeapons;
import net.wildbill22.draco.lib.Reference;
import net.wildbill22.draco.proxies.CommonProxy;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
	
/** 
 * @author WILLIAM
 *
 */
@Mod(modid = Reference.MODID, name = Reference.NAME, version = Reference.VERSION)
public class Core {

	@SidedProxy(clientSide = "net.wildbill22.draco.proxies.ClientProxy", serverSide = "net.wildbill22.draco.proxies..CommonProxy")
	public static CommonProxy dracoProxy;

	@Instance(Reference.MODID)
	public static Core instance;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event){
		Creative_Tab.OtherInfo();
		ModBlocks.preInit();
		ModEntities.preInit();
		ModWeapons.preInit();
		dracoProxy.registerRenderer();
	}

	@EventHandler
	public static void Init(FMLInitializationEvent event) {
		ModCraftingRecipes.init();		
		ModWeapons.init();
	}
}
