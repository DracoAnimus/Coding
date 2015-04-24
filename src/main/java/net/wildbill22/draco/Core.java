package net.wildbill22.draco;

import net.minecraftforge.common.MinecraftForge;
import net.wildbill22.draco.biome.ModBiomes;
import net.wildbill22.draco.blocks.ModBlocks;
import net.wildbill22.draco.crafting.ModCraftingRecipes;
import net.wildbill22.draco.entities.ModEntities;
import net.wildbill22.draco.generation.WorldGenDracoAnimus;
import net.wildbill22.draco.generation.villageComponents.ComponentBakery;
import net.wildbill22.draco.generation.villageComponents.VillageBaronCastle;
import net.wildbill22.draco.generation.villageComponents.VillageGuardTower;
import net.wildbill22.draco.generation.villageComponents.VillageTavern;
import net.wildbill22.draco.generation.villageHandlers.BarWenchCreationHandler;
import net.wildbill22.draco.generation.villageHandlers.VillagerBakeryTradeHandler;
import net.wildbill22.draco.generation.villages.VillageBiomes;
import net.wildbill22.draco.generation.villages.VillageGenReplacer;
import net.wildbill22.draco.items.ModItems;
import net.wildbill22.draco.items.weapons.ModWeapons;
import net.wildbill22.draco.lib.LogHelper;
import net.wildbill22.draco.lib.REFERENCE;
import net.wildbill22.draco.network.DragonPlayerUpdatePacket;
import net.wildbill22.draco.network.RequestDragonPlayerUpdatePacket;
import net.wildbill22.draco.proxies.CommonProxy;
import net.wildbill22.draco.stats.ModStats;
import net.wildbill22.draco.tile_entity.ModTileEntities;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
	
/** 
 * @author WILLIAM
 *
 */
@Mod(modid = REFERENCE.MODID, name = REFERENCE.NAME, version = REFERENCE.VERSION, 
	guiFactory = "net.wildbill22.draco.client.gui.ModGuiFactory")
public class Core {

	@SidedProxy(clientSide = "net.wildbill22.draco.proxies.ClientProxy", serverSide = "net.wildbill22.draco.proxies.CommonProxy")
	public static CommonProxy dracoProxy;

	public static SimpleNetworkWrapper modChannel;

	@Instance(REFERENCE.MODID)
	public static Core instance;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event){
		Configs.init(event.getSuggestedConfigurationFile());// Keep first
		Creative_Tab.OtherInfo();
		ModItems.preInit();
		ModBlocks.preInit();
    	ModTileEntities.modRegistry();
		ModBiomes.init(); // Must be before ModEntities
		ModEntities.preInit();
		ModWeapons.preInit();
		dracoProxy.registerRenderer();
		VillageBiomes.preInit(event);
		setupNetwork();
	}

	@EventHandler
	public static void Init(FMLInitializationEvent event) {
		ModCraftingRecipes.init();		
		ModWeapons.init();
		ModItems.init();
		ModBlocks.init();
		ModStats.init();
		GameRegistry.registerWorldGenerator(new WorldGenDracoAnimus(), 1000);
		dracoProxy.registerSounds();
		dracoProxy.registerSubscriptions();
		FMLCommonHandler.instance().bus().register(new Configs());
		if (Configs.VILLAGE.village_gen_enabled) {
			LogHelper.info("Registering replacer for village generation.");
			MinecraftForge.TERRAIN_GEN_BUS.register(new VillageGenReplacer());
			// Register new village building
			if (Configs.VILLAGE.village_taverns_enabled) {
				VillageTavern.init();              // Tavern
				BarWenchCreationHandler.init();    // Bar Wench
			}
			if (Configs.VILLAGE.village_bakery_enabled) {
				VillagerBakeryTradeHandler.init(); // Baker
				ComponentBakery.init();            // Bakery			
			}
			// Register new village building
			if (Configs.VILLAGE.village_guard_tower_enabled) {
				VillageGuardTower.init();  // Guard Tower
			}
			if (Configs.VILLAGE.village_barons_castle_enabled) {
				VillageBaronCastle.init();
			}
		}
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		VillageBiomes.postInit(event);
	}
	
	// For future use:
	private void setupNetwork() {
		NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());
		
		modChannel = NetworkRegistry.INSTANCE.newSimpleChannel(REFERENCE.MODID);

		int id = 0;
		modChannel.registerMessage(DragonPlayerUpdatePacket.Handler.class, DragonPlayerUpdatePacket.class, id++, Side.CLIENT);
//		modChannel.registerMessage(RequestDragonPlayerUpdatePacket.Handler.class, RequestDragonPlayerUpdatePacket.class, id++, Side.SERVER);
	}
}
