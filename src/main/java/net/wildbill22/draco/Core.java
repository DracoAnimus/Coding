package net.wildbill22.draco;

import net.minecraft.launchwrapper.Launch;
import net.minecraftforge.common.MinecraftForge;
import net.wildbill22.draco.api.EnchiridionAPI;
import net.wildbill22.draco.biome.ModBiomes;
import net.wildbill22.draco.blocks.ModBlocks;
import net.wildbill22.draco.crafting.ModCraftingRecipes;
import net.wildbill22.draco.entities.ModEntities;
import net.wildbill22.draco.entities.dragons.DragonRegistry;
import net.wildbill22.draco.generation.WorldGenDracoAnimus;
import net.wildbill22.draco.generation.villageComponents.BallistaTower;
import net.wildbill22.draco.generation.villageComponents.CatapultTower;
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
import net.wildbill22.draco.lib.KeyBindings;
import net.wildbill22.draco.lib.LogHelper;
import net.wildbill22.draco.lib.REFERENCE;
import net.wildbill22.draco.network.DragonAbilityLavaToObsidian;
import net.wildbill22.draco.network.DragonPlayerUpdateDragonName;
import net.wildbill22.draco.network.DragonPlayerUpdateIsDragon;
import net.wildbill22.draco.network.DragonPlayerUpdateLevel;
import net.wildbill22.draco.network.StaffUpdateDamageTarget;
import net.wildbill22.draco.network.StaffUpdateDestroyBlock;
import net.wildbill22.draco.network.StaffUpdateDropEntity;
import net.wildbill22.draco.network.StaffUpdateMountEntity;
import net.wildbill22.draco.network.StaffUpdatePoisonTarget;
import net.wildbill22.draco.network.StaffUpdateSetTargetOnFire;
import net.wildbill22.draco.network.StaffUpdateTeleportThroughWall;
import net.wildbill22.draco.network.StaffUpdateTeleportThroughWallInDark;
import net.wildbill22.draco.network.StaffUpdateWitherTarget;
import net.wildbill22.draco.proxies.CommonProxy;
import net.wildbill22.draco.tile_entity.ModTileEntities;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
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
dependencies = "required-after: Enchiridion2",
//dependencies = "required-after: MCVanillaTweaks",
guiFactory = "net.wildbill22.draco.client.gui.ModGuiFactory"
)
public class Core {
	@SidedProxy(clientSide = "net.wildbill22.draco.proxies.ClientProxy", serverSide = "net.wildbill22.draco.proxies.CommonProxy")
	public static CommonProxy dracoProxy;

	public static SimpleNetworkWrapper modChannel;
	public boolean devEnv = false;

	@Instance(REFERENCE.MODID)
	public static Core instance;
	
//	public static boolean isMCModLoaded;

	public static boolean isEnchiridionModLoaded;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event){
		devEnv = (Boolean) Launch.blackboard.get("fml.deobfuscatedEnvironment");
		Configs.init(event.getSuggestedConfigurationFile());// Keep first
		Creative_Tab.OtherInfo();
		ModItems.preInit();
		ModBlocks.preInit();
    	ModTileEntities.modRegistry();
		ModBiomes.preInit(); // Must be before ModEntities
		ModEntities.preInit();
		if (devEnv)
			ModEntities.registerEggsForDev();
		ModWeapons.preInit();
		dracoProxy.registerRenderer();
		VillageBiomes.preInit(event);
		setupNetwork();
		KeyBindings.preInit();
	}

	@EventHandler
	public static void Init(FMLInitializationEvent event) {
		ModCraftingRecipes.init();		
		ModWeapons.init();
		ModItems.init();
		ModBlocks.init();
		GameRegistry.registerWorldGenerator(new WorldGenDracoAnimus(), 1000);
		dracoProxy.registerSounds();
		dracoProxy.registerSubscriptions();
		dracoProxy.registerStats();
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
				ComponentBakery.init();            // Bakery			
				VillagerBakeryTradeHandler.init(); // Baker
			}
			// Register new village building
			if (Configs.VILLAGE.village_guard_tower_enabled) {
				VillageGuardTower.init();  // Guard Tower
				BallistaTower.init();
				CatapultTower.init();
			}
			if (Configs.VILLAGE.village_barons_castle_enabled) {
				VillageBaronCastle.init();
				// Extra houses to make the city a bit bigger
//				House3CreationHandler.init();
//				WoodHutCreationHandler.init();
			}
		}
		// Dragons
		DragonRegistry.init();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		VillageBiomes.postInit(event);
//		isMCModLoaded = Loader.isModLoaded("MCVanillaTweaks");
//		if (isMCModLoaded)
//			LogHelper.info("MCVanillaTweaks is loaded!");
		
		isEnchiridionModLoaded = Loader.isModLoaded(REFERENCE.EAPIMODID);
		if (isEnchiridionModLoaded) {
	        /** Register this mod as containing books **/
//	        FMLInterModComms.sendMessage("Enchiridion2", "registerBookMod", REFERENCE.MODID + ":" + "assets/books");
//	        EnchiridionAPI.registerBookItem(REFERENCE.dracoAnimusBookName, 0);
			EnchiridionAPI.init();
			LogHelper.info("Enchiridion 2 is loaded!");
		}
	}
	
	// For future use:
	private void setupNetwork() {
		NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());
		
		modChannel = NetworkRegistry.INSTANCE.newSimpleChannel(REFERENCE.MODID);

		int id = 0;
		modChannel.registerMessage(DragonPlayerUpdateLevel.Handler.class, DragonPlayerUpdateLevel.class, id++, Side.CLIENT);
		modChannel.registerMessage(DragonPlayerUpdateIsDragon.Handler.class, DragonPlayerUpdateIsDragon.class, id++, Side.CLIENT);
		modChannel.registerMessage(DragonPlayerUpdateDragonName.Handler.class, DragonPlayerUpdateDragonName.class, id++, Side.CLIENT);
		modChannel.registerMessage(StaffUpdateSetTargetOnFire.Handler.class, StaffUpdateSetTargetOnFire.class, id++, Side.SERVER);
		modChannel.registerMessage(StaffUpdateDamageTarget.Handler.class, StaffUpdateDamageTarget.class, id++, Side.SERVER);
		modChannel.registerMessage(StaffUpdateWitherTarget.Handler.class, StaffUpdateWitherTarget.class, id++, Side.SERVER);
		modChannel.registerMessage(StaffUpdatePoisonTarget.Handler.class, StaffUpdatePoisonTarget.class, id++, Side.SERVER);
		modChannel.registerMessage(DragonAbilityLavaToObsidian.Handler.class, DragonAbilityLavaToObsidian.class, id++, Side.CLIENT);
		modChannel.registerMessage(StaffUpdateTeleportThroughWall.Handler.class, StaffUpdateTeleportThroughWall.class, id++, Side.SERVER);
		modChannel.registerMessage(StaffUpdateTeleportThroughWallInDark.Handler.class, StaffUpdateTeleportThroughWallInDark.class, id++, Side.SERVER);
		modChannel.registerMessage(StaffUpdateDropEntity.Handler.class, StaffUpdateDropEntity.class, id++, Side.SERVER);
		modChannel.registerMessage(StaffUpdateMountEntity.Handler.class, StaffUpdateMountEntity.class, id++, Side.SERVER);
		modChannel.registerMessage(StaffUpdateDestroyBlock.Handler.class, StaffUpdateDestroyBlock.class, id++, Side.SERVER);
	}
}
