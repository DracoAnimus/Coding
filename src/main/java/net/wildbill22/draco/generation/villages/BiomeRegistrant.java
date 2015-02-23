package net.wildbill22.draco.generation.villages;

import java.util.HashSet;
import java.util.Set;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager;
import net.wildbill22.draco.lib.LogHelper;

/**
 * Adds and removes biomes where villages will be generated
 * 
 * @author WILLIAM
 *
 */
public class BiomeRegistrant {

	public static void addBiome(BiomeGenBase biome) {
		BiomeManager.addVillageBiome(biome, true);
		LogHelper.info("BiomeRegistrant: " + String.format("Added %s (ID:%d) as a village biome.", biome.biomeName, biome.biomeID));
	}

	public static void addBiomeById(int id) {
		BiomeGenBase biomeForId = null;
		for (BiomeGenBase biome : biomeSet) {
			if (biome.biomeID == id)
				biomeForId = biome;
		}

		if (biomeForId != null)
			addBiome(biomeForId);
		else
			LogHelper.warn("BiomeRegistrant: Can't find biome with ID " + id);
	}

	public static void addBiomeByName(String name) {
		BiomeGenBase biomeForId = null;
		for (BiomeGenBase biome : biomeSet) {
			if (biome.biomeName.equals(name))
				biomeForId = biome;
		}

		if (biomeForId != null)
			addBiome(biomeForId);
		else
			LogHelper.warn("BiomeRegistrant: Can't find biome with name " + name);
	}

	public static void addBiomesByType(Type type) {
		for (BiomeGenBase biome : BiomeDictionary.getBiomesForType(type)) {
			addBiome(biome);
		}
	}

	public static void addBiomesByTypeName(String name) {
		Type type = Type.valueOf(name);
		if (type != null)
			addBiomesByType(type);
		else
			LogHelper.warn("BiomeRegistrant: Can't find type with name " + name);
	}

	private static Set<BiomeGenBase> fetchAllBiomes() {
		HashSet<BiomeGenBase> biomes = new HashSet<BiomeGenBase>();
		for (BiomeGenBase biome : BiomeGenBase.getBiomeGenArray()) {
			if (biome != null)
				biomes.add(biome);
		}

		return biomes;
	}

	public static void init() {
		biomeSet = fetchAllBiomes();
	}

	// Removals

	public static void removeBiome(BiomeGenBase biome) {
		BiomeManager.removeVillageBiome(biome);
		LogHelper.info("BiomeRegistrant: " + String.format("Removed %s (ID:%d) from village biomes.", biome.biomeName, biome.biomeID));
	}

	public static void removeBiomeById(int id) {
		BiomeGenBase biomeForId = null;
		for (BiomeGenBase biome : biomeSet) {
			if (biome.biomeID == id)
				biomeForId = biome;
		}

		if (biomeForId != null)
			removeBiome(biomeForId);
		else
			LogHelper.warn("BiomeRegistrant: Can't find biome with ID " + id);
	}

	public static void removeBiomeByName(String name) {
		BiomeGenBase biomeForId = null;
		for (BiomeGenBase biome : biomeSet) {
			if (biome.biomeName.equals(name))
				biomeForId = biome;
		}

		if (biomeForId != null)
			removeBiome(biomeForId);
		else
			LogHelper.warn("BiomeRegistrant: Can't find biome with name " + name);
	}

	public static void removeBiomesByType(BiomeDictionary.Type type) {
		for (BiomeGenBase biome : BiomeDictionary.getBiomesForType(type)) {
			removeBiome(biome);
		}
	}

	public static void removeBiomesByTypeName(String name) {
		Type type = Type.valueOf(name);
		if (type != null)
			removeBiomesByType(type);
		else
			LogHelper.warn("BiomeRegistrant: Can't find type with name " + name);
	}

	private static Set<BiomeGenBase> biomeSet;
}
