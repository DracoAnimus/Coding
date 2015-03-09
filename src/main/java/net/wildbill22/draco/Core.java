package net.wildbill22.draco;

import net.minecraftforge.common.MinecraftForge;
import net.wildbill22.draco.blocks.ModBlocks;
import net.wildbill22.draco.crafting.ModCraftingRecipes;
import net.wildbill22.draco.entities.ModEntities;
import net.wildbill22.draco.generation.WorldGenDracoAnimus;
import net.wildbill22.draco.generation.villages.VillageBiomes;
import net.wildbill22.draco.generation.villages.VillageGenReplacer;
import net.wildbill22.draco.items.ModItems;
import net.wildbill22.draco.items.weapons.ModWeapons;
import net.wildbill22.draco.lib.LogHelper;
import net.wildbill22.draco.lib.REFERENCE;
import net.wildbill22.draco.proxies.CommonProxy;
import net.wildbill22.draco.tile_entity.ModTileEntities;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
	
/** 
 * @author WILLIAM
 *
 */
@Mod(modid = REFERENCE.MODID, name = REFERENCE.NAME, version = REFERENCE.VERSION, 
	guiFactory = "net.wildbill22.draco.client.gui.ModGuiFactory")
public class Core {

	@SidedProxy(clientSide = "net.wildbill22.draco.proxies.ClientProxy", serverSide = "net.wildbill22.draco.proxies.CommonProxy")
	public static CommonProxy dracoProxy;

	@Instance(REFERENCE.MODID)
	public static Core instance;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event){
		Configs.init(event.getSuggestedConfigurationFile());// Keep first
		Creative_Tab.OtherInfo();
		ModItems.preInit();
		ModBlocks.preInit();
    	ModTileEntities.modRegistry();
		ModEntities.preInit();
		ModWeapons.preInit();
		dracoProxy.registerRenderer();
		VillageBiomes.preInit(event);
	}

	@EventHandler
	public static void Init(FMLInitializationEvent event) {
		ModCraftingRecipes.init();		
		ModWeapons.init();
		GameRegistry.registerWorldGenerator(new WorldGenDracoAnimus(), 100);
		dracoProxy.registerSounds();
		dracoProxy.registerSubscriptions();
		FMLCommonHandler.instance().bus().register(new Configs());
		if (Configs.village_gen_enabled) {
			LogHelper.info("Registering replacer for village generation.");
			MinecraftForge.TERRAIN_GEN_BUS.register(new VillageGenReplacer());
		}
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		VillageBiomes.postInit(event);
	}
}
