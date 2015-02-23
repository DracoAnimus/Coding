package net.wildbill22.draco.generation.villages;

import java.io.File;
import java.util.regex.Pattern;

import net.minecraftforge.common.MinecraftForge;
import net.wildbill22.draco.lib.LogHelper;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

/**
 * All the initialization for new Village Biomes
 * 
 * @author WILLIAM
 *
 */
public class VillageBiomes {

	// TODO: add to vampirism.java
	public static void postInit(FMLPostInitializationEvent ev) {
		// All other mods should be done registering by now.
		BiomeRegistrant.init();

		for (String name : ConfigHandler.getAddBiomes()) {
			if (Pattern.matches("\\d+", name))
				BiomeRegistrant.addBiomeById(Integer.parseInt(name));
			else
				BiomeRegistrant.addBiomeByName(name);
		}
		for (String name : ConfigHandler.getAddTypes()) {
			LogHelper.info("VillageBiomes: " + String.format("Adding all %s biomes as village biomes.", name));
			BiomeRegistrant.addBiomesByTypeName(name);
		}

		for (String name : ConfigHandler.getRemoveBiomes()) {
			if (Pattern.matches("\\d+", name))
				BiomeRegistrant.removeBiomeById(Integer.parseInt(name));
			else
				BiomeRegistrant.removeBiomeByName(name);
		}
		for (String name : ConfigHandler.getRemoveTypes()) {
			LogHelper.info("VillageBiomes: Removing all " + name + " biomes from village biomes.");
			BiomeRegistrant.removeBiomesByTypeName(name);
		}

		// Register the custom village block replacer
		MinecraftForge.TERRAIN_GEN_BUS.register(new BiomeBlockReplacer());
	}

	public static void preInit(FMLPreInitializationEvent event) {
		// Load Config
		File ConfigFile = new File(event.getModConfigurationDirectory(), "VillageBiomes.cfg");
		ConfigHandler.loadConfig(ConfigFile);
	}
}
