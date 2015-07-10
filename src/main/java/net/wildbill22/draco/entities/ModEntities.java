package net.wildbill22.draco.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;
import net.wildbill22.draco.Core;
import net.wildbill22.draco.biome.ModBiomes;
import net.wildbill22.draco.entities.dragons.EntityDracoAqua;
import net.wildbill22.draco.entities.dragons.EntityCreeperDragon;
import net.wildbill22.draco.entities.dragons.EntityDracoAquila;
import net.wildbill22.draco.entities.dragons.EntityDracoMortem;
import net.wildbill22.draco.entities.dragons.EntityGoldDragon;
import net.wildbill22.draco.entities.dragons.EntityDracoIgnis;
import net.wildbill22.draco.entities.dragons.EntitySilverDragon;
import net.wildbill22.draco.entities.dragons.EntityDracoTenebrosus;
import net.wildbill22.draco.entities.dragons.EntityDracoTerra;
import net.wildbill22.draco.entities.hostile.EntityBallista;
import net.wildbill22.draco.entities.hostile.EntityBaron;
import net.wildbill22.draco.entities.hostile.EntityCatapult;
import net.wildbill22.draco.entities.hostile.EntityCrossbowGuard;
import net.wildbill22.draco.entities.hostile.EntityKnightGuard;
import net.wildbill22.draco.entities.hostile.EntityLongbowGuard;
import net.wildbill22.draco.entities.hostile.EntitySpearGuard;
import net.wildbill22.draco.entities.hostile.EntityWildFireDragon;
import net.wildbill22.draco.items.ItemMyExplosive;
import net.wildbill22.draco.items.ItemMyFireball;
import net.wildbill22.draco.items.ItemMyRock;
import net.wildbill22.draco.items.ItemSpear;
import net.wildbill22.draco.lib.BALANCE;
import net.wildbill22.draco.lib.LogHelper;
import cpw.mods.fml.common.registry.EntityRegistry;

/**
 * @author WILLIAM
 *
 */
public class ModEntities {
	private static int modEntityID = 0;
	
	public static void preInit() {
		// Overworld mod entities 
		EntityRegistry.registerModEntity(EntityCreeperDragon.class, EntityCreeperDragon.name, ++modEntityID, Core.instance, 80, 3, true);
		EntityRegistry.registerModEntity(EntitySilverDragon.class, EntitySilverDragon.name, ++modEntityID, Core.instance, 80, 3, true);
//		EntityRegistry.registerModEntity(EntityGuard.class, EntityGuard.name, ++modEntityID, Core.instance, 80, 1, true);
		EntityRegistry.registerModEntity(EntitySpearGuard.class, EntitySpearGuard.name, ++modEntityID, Core.instance, 80, 3, true);
//    	EntityRegistry.registerModEntity(EntityMCSilverDragon.class, EntityMCSilverDragon.name, ++modEntityID, Core.instance, 80, 3, true);
		EntityRegistry.registerModEntity(EntityWildFireDragon.class, EntityWildFireDragon.name, ++modEntityID, Core.instance, 80, 3, true);

		EntityRegistry.registerModEntity(EntitySpear.class, ItemSpear.name, ++modEntityID, Core.instance, 64, 10, true);
		EntityRegistry.registerModEntity(EntityMyFireball.class, ItemMyFireball.name, ++modEntityID, Core.instance, 64, 10, true);
		EntityRegistry.registerModEntity(EntityMyExplosive.class, ItemMyExplosive.name, ++modEntityID, Core.instance, 64, 10, true);

		EntityRegistry.registerModEntity(EntityKnightGuard.class, EntityKnightGuard.name, ++modEntityID, Core.instance, 80, 3, true);
		EntityRegistry.registerModEntity(EntityLongbowGuard.class, EntityLongbowGuard.name, ++modEntityID, Core.instance, 80, 3, true);
		EntityRegistry.registerModEntity(EntityCrossbowGuard.class, EntityCrossbowGuard.name, ++modEntityID, Core.instance, 80, 3, true);
		EntityRegistry.registerModEntity(EntityBaron.class, EntityBaron.name, ++modEntityID, Core.instance, 80, 3, true);
		EntityRegistry.registerModEntity(EntityBallista.class, EntityBallista.name, ++modEntityID, Core.instance, 80, 3, true);
		EntityRegistry.registerModEntity(EntityCatapult.class, EntityCatapult.name, ++modEntityID, Core.instance, 80, 3, true);
		EntityRegistry.registerModEntity(EntityMyRock.class, ItemMyRock.name, ++modEntityID, Core.instance, 64, 20, true);
		
		// New in 0.3.0
		EntityRegistry.registerModEntity(EntityDracoMortem.class, EntityDracoMortem.name, ++modEntityID, Core.instance, 80, 3, true); // Skeleton dragon
		EntityRegistry.registerModEntity(EntityDracoAqua.class, EntityDracoAqua.name, ++modEntityID, Core.instance, 80, 3, true); // Water dragon
		EntityRegistry.registerModEntity(EntityGoldDragon.class, EntityGoldDragon.name, ++modEntityID, Core.instance, 80, 3, true); // Gold dragon
		EntityRegistry.registerModEntity(EntityDracoTerra.class, EntityDracoTerra.name, ++modEntityID, Core.instance, 80, 3, true); // Earth dragon
		EntityRegistry.registerModEntity(EntityDracoTenebrosus.class, EntityDracoTenebrosus.name, ++modEntityID, Core.instance, 80, 3, true); // Night dragon
		EntityRegistry.registerModEntity(EntityDracoIgnis.class, EntityDracoIgnis.name, ++modEntityID, Core.instance, 80, 3, true);   // Fire dragon
		EntityRegistry.registerModEntity(EntityDracoAquila.class, EntityDracoAquila.name, ++modEntityID, Core.instance, 80, 3, true); // Eagle dragon
		EntityRegistry.registerModEntity(EntityBallistaSpear.class, "ballista_" + ItemSpear.name, ++modEntityID, Core.instance, 64, 10, true);

		addMobSpawns();

		// Eggs for some of the entities below for creative mode (this must be after the entities are created)
		registerEggs();
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
    
	// Only in dev environment:
	public static void registerEggsForDev() {		
		// Overworld eggs
		LogHelper.info("ModEntities: Registering the dev eggs!");
		registerEntityEgg(EntitySilverDragon.class, 0xdabb9d, 0xa53c1a);
	}
	
	// For creative mode:
	public static void registerEggs() {		
		// Overworld eggs
		LogHelper.info("ModEntities: Registering the creative eggs!");
		
		// Dragons
		registerEntityEgg(EntityCreeperDragon.class, 0xdabb9d, 0xa73c1a);
		registerEntityEgg(EntityWildFireDragon.class, 0xdabb9d, 0xa93c1a);
		
		// Fixed weapons
		registerEntityEgg(EntityBallista.class, 0xd6bb9d, 0xa93c1a);
		registerEntityEgg(EntityCatapult.class, 0xd6bb9d, 0xaa3c1a);

		// Guards
		registerEntityEgg(EntitySpearGuard.class, 0xd8bb9d, 0xa23c1a);
		registerEntityEgg(EntityKnightGuard.class, 0xd8bb9d, 0xa43c1a);
		registerEntityEgg(EntityLongbowGuard.class, 0xd8bb9d, 0xa63c1a);
		registerEntityEgg(EntityCrossbowGuard.class, 0xd8bb9d, 0xa63c1a);
		registerEntityEgg(EntityBaron.class, 0xd8bb9d, 0xa83c1a);
	}
	
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
