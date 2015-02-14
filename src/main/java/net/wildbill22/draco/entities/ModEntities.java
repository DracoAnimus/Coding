package net.wildbill22.draco.entities;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;
import net.wildbill22.draco.handlers.EntityHandler;
import cpw.mods.fml.common.registry.EntityRegistry;

/**
 * @author WILLIAM
 *
 */
public class ModEntities {

	public static void preInit()
	{
		//Overworld
		EntityHandler.registerMonsters(EntityCreeperDragon.class, "creeperDragon");
		EntityHandler.registerEntityEgg(EntityCreeperDragon.class, 0xd8bb9d, 0xa63c1a);

		addMobSpawns();
	}

	public static void addMobSpawns()
	{
		//Overworld
		/**
		 * Weigthed Probability info:
		 * 
		 * CREATURE (day): Cows (8), Pigs (10), Chickens (10), Sheep (12)
		 * MONSTER (night): All 100 except Endermen (10), and Witches (5)
		 * AMBIENT (ambient creatures): Bats (10)
		 * WATER (water, duh): Squids (10)
		 * 
		 * Make sure these balance out; if not balanced, it could cause excess spawning/not spawning
		 * at all, plus crashes.
		 */
		EntityRegistry.addSpawn(EntityCreeperDragon.class, 35, 1, 2, EnumCreatureType.creature, BiomeGenBase.plains, BiomeGenBase.forest, BiomeGenBase.birchForest, BiomeGenBase.birchForestHills, BiomeGenBase.forestHills, BiomeGenBase.jungle, BiomeGenBase.jungleEdge, BiomeGenBase.jungleHills, BiomeGenBase.roofedForest, BiomeGenBase.desert);
	}
}
