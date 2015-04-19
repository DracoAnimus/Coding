package net.wildbill22.draco.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;
import net.wildbill22.draco.Core;
import net.wildbill22.draco.biome.ModBiomes;
import net.wildbill22.draco.entities.dragons.EntityCreeperDragon;
import net.wildbill22.draco.entities.dragons.EntitySilverDragon;
import net.wildbill22.draco.entities.hostile.EntityBaron;
import net.wildbill22.draco.entities.hostile.EntityCrossbowGuard;
import net.wildbill22.draco.entities.hostile.EntityGuard;
import net.wildbill22.draco.entities.hostile.EntityKnightGuard;
import net.wildbill22.draco.entities.hostile.EntityLongbowGuard;
import net.wildbill22.draco.entities.hostile.EntitySpearGuard;
import net.wildbill22.draco.entities.hostile.EntityWildFireDragon;
import net.wildbill22.draco.items.ItemMyExplosive;
import net.wildbill22.draco.items.ItemMyFireball;
import net.wildbill22.draco.items.ItemSpear;
import net.wildbill22.draco.lib.BALANCE;
import cpw.mods.fml.common.registry.EntityRegistry;

/**
 * @author WILLIAM
 *
 */
public class ModEntities {
	private static int modEntityID = 0;
	
	public static void preInit() {
		// Overworld eggs
		registerEntityEgg(EntityCreeperDragon.class, 0xd8bb9d, 0xa63c1a);
		registerEntityEgg(EntitySilverDragon.class, 0xd8bb9d, 0xa73c1a);
		registerEntityEgg(EntityWildFireDragon.class, 0xd8bb9d, 0xa83c1a);
		
		// Overworld mod entities 
		EntityRegistry.registerModEntity(EntityCreeperDragon.class, EntityCreeperDragon.name, ++modEntityID, Core.instance, 80, 1, true);
		EntityRegistry.registerModEntity(EntitySilverDragon.class, EntitySilverDragon.name, ++modEntityID, Core.instance, 80, 1, true);
//		EntityRegistry.registerModEntity(EntityGuard.class, EntityGuard.name, ++modEntityID, Core.instance, 80, 1, true);
		EntityRegistry.registerModEntity(EntitySpearGuard.class, EntitySpearGuard.name, ++modEntityID, Core.instance, 80, 1, true);
//    	EntityRegistry.registerModEntity(EntityMCSilverDragon.class, EntityMCSilverDragon.name, ++modEntityID, Core.instance, 80, 1, true);
		EntityRegistry.registerModEntity(EntityWildFireDragon.class, EntityWildFireDragon.name, ++modEntityID, Core.instance, 80, 1, true);

		EntityRegistry.registerModEntity(EntitySpear.class, ItemSpear.name, ++modEntityID, Core.instance, 64, 10, true);
		EntityRegistry.registerModEntity(EntityMyFireball.class, ItemMyFireball.name, ++modEntityID, Core.instance, 64, 10, true);
		EntityRegistry.registerModEntity(EntityMyExplosive.class, ItemMyExplosive.name, ++modEntityID, Core.instance, 64, 10, true);

		EntityRegistry.registerModEntity(EntityKnightGuard.class, EntityKnightGuard.name, ++modEntityID, Core.instance, 80, 1, true);
		EntityRegistry.registerModEntity(EntityLongbowGuard.class, EntityLongbowGuard.name, ++modEntityID, Core.instance, 80, 1, true);
		EntityRegistry.registerModEntity(EntityCrossbowGuard.class, EntityCrossbowGuard.name, ++modEntityID, Core.instance, 80, 1, true);
		EntityRegistry.registerModEntity(EntityBaron.class, EntityBaron.name, ++modEntityID, Core.instance, 80, 1, true);
		
		addMobSpawns();
	}

	private static void addMobSpawns() {
		//Overworld
		/**
		 * Weighted Probability info:
		 * 
		 * CREATURE (day): Cows (8), Pigs (10), Chickens (10), Sheep (12)
		 * MONSTER (night): All 100 except Endermen (10), and Witches (5)
		 * AMBIENT (ambient creatures): Bats (10)
		 * WATER (water, duh): Squids (10)
		 * 
		 * Make sure these balance out; if not balanced, it could cause excess spawning/not spawning
		 * at all, plus crashes.
		 */
		EntityRegistry.addSpawn(EntityCreeperDragon.class, BALANCE.MOBPROP.DRAGON_SPAWN_PROB, 1, 2, EnumCreatureType.creature, 
				BiomeGenBase.plains, BiomeGenBase.forest, BiomeGenBase.birchForest, BiomeGenBase.birchForestHills, 
				BiomeGenBase.forestHills, BiomeGenBase.jungle, BiomeGenBase.jungleEdge, BiomeGenBase.jungleHills, 
				BiomeGenBase.roofedForest, BiomeGenBase.desert);
		EntityRegistry.addSpawn(EntityWildFireDragon.class, BALANCE.WILD_FIRE_DRAGON_PROP.SPAWN_PROB, 1, 2, EnumCreatureType.creature, 
				BiomeGenBase.plains, BiomeGenBase.forest, BiomeGenBase.birchForest, BiomeGenBase.birchForestHills, 
				BiomeGenBase.forestHills, BiomeGenBase.jungle, BiomeGenBase.jungleEdge, BiomeGenBase.jungleHills, 
				BiomeGenBase.roofedForest, BiomeGenBase.desert);
		
		// TODO: Needs to match guard spawn to the village biomes somehow
		EntityRegistry.addSpawn(EntitySpearGuard.class, BALANCE.MOBPROP.GUARD_SPAWN_PROB, 1, 2, EnumCreatureType.creature, 
				BiomeGenBase.plains, BiomeGenBase.forest, BiomeGenBase.birchForest, BiomeGenBase.birchForestHills, 
				BiomeGenBase.forestHills, BiomeGenBase.jungle, BiomeGenBase.jungleEdge, BiomeGenBase.jungleHills, 
				BiomeGenBase.roofedForest,  BiomeGenBase.mesa, BiomeGenBase.taiga, BiomeGenBase.swampland, 
				BiomeGenBase.mushroomIsland);
		EntityRegistry.addSpawn(EntityKnightGuard.class, BALANCE.MOBPROP.GUARD_SPAWN_PROB, 1, 2, EnumCreatureType.creature, 
				BiomeGenBase.desert, BiomeGenBase.coldTaiga, BiomeGenBase.icePlains, BiomeGenBase.savanna);
		EntityRegistry.addSpawn(EntityLongbowGuard.class, BALANCE.MOBPROP.GUARD_SPAWN_PROB, 1, 2, EnumCreatureType.creature, 
				ModBiomes.biomeTownForest);
		EntityRegistry.addSpawn(EntityCrossbowGuard.class, BALANCE.MOBPROP.GUARD_SPAWN_PROB, 1, 2, EnumCreatureType.creature, 
				ModBiomes.biomeCityPlains);
	}
	
    static int startEntityId = 300;
    
    @SuppressWarnings("unchecked")
    public static void registerEntityEgg(Class<? extends Entity> entity, int primaryColor, int secondaryColor) {
    	int id = getUniqueEntityId();
    	EntityList.IDtoClassMapping.put(id, entity);
    	EntityList.entityEggs.put(id, new EntityList.EntityEggInfo(id, primaryColor, secondaryColor));
    }
        
    public static int getUniqueEntityId() {
    	do {
    		startEntityId++;
    	}
    	while (EntityList.getStringFromID(startEntityId) != null);

    	return startEntityId;
    }
}
