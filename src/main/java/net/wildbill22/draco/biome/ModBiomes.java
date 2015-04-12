package net.wildbill22.draco.biome;

import cpw.mods.fml.common.registry.VillagerRegistry;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.BiomeManager.BiomeType;
import net.wildbill22.draco.lib.LogHelper;

/**
 * 
 * @author WILLIAM
 *
 */
public class ModBiomes {
	public static BiomeGenBase biomeTownForest;
	public static BiomeEntry biomeEntryTownForest;
	public static BiomeGenBase biomeCityPlains;
	public static BiomeEntry biomeEntryCityPlains;
	
	public static void init() {
		int biomeID = 10;
		while (BiomeDictionary.isBiomeRegistered(biomeID))
			biomeID++;
		
		// Create Town Forest
		biomeTownForest = new BiomeTownForest(biomeID).setBiomeName(BiomeTownForest.name);
        // Allow villages in this biome
		BiomeManager.addVillageBiome(biomeTownForest, true);
		BiomeDictionary.registerBiomeType(biomeTownForest, Type.MAGICAL);
//		int weight = 10;
		int weight = 40;  // Testing only, 100 is maximum weight
		LogHelper.info("ModBiomes: Town Forest created with weight: " + weight);
		biomeEntryTownForest = new BiomeEntry(biomeTownForest, weight);
		BiomeManager.addBiome(BiomeType.WARM, biomeEntryTownForest);
		
		// Create City Forest
		biomeID++;
		biomeCityPlains = new BiomeCityPlains(biomeID).setBiomeName(BiomeCityPlains.name);
        // Allow villages in this biome
		BiomeManager.addVillageBiome(biomeCityPlains, true);
		BiomeDictionary.registerBiomeType(biomeCityPlains, Type.MAGICAL);
		weight = 20;
//		weight = 50;  // Testing only, 100 is maximum weight
		LogHelper.info("ModBiomes: City Plains created with weight: " + weight);
		biomeEntryCityPlains = new BiomeEntry(biomeCityPlains, weight);
		BiomeManager.addBiome(BiomeType.WARM, biomeEntryCityPlains);
	}	
}
