package net.wildbill22.draco.generation;

import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.village.Village;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.wildbill22.draco.entities.hostile.EntityGuard;
import net.wildbill22.draco.lib.BALANCE;
import net.wildbill22.draco.lib.LogHelper;
import cpw.mods.fml.common.IWorldGenerator;

/**
 * 
 * @author WILLIAM
 *
 */
public class WorldGenDracoAnimus implements IWorldGenerator {
	/**
	 * Generates entities
	 * 
	 * @param world World
	 * @param random Random
	 * @param x xCoord
	 * @param z ZCoord
	 */
	private void addEntities(World world, Random random, int x, int z) {

		int surfaceY = world.getHeightValue(x, z);
		Village v = world.villageCollectionObj.findNearestVillage(x, surfaceY, z, 16);
		if (v == null) {
			return;
		}

		int r = v.getVillageRadius();
		AxisAlignedBB box = AxisAlignedBB.getBoundingBox(v.getCenter().posX - r, surfaceY - 20, v.getCenter().posZ - r, 
				v.getCenter().posX + r,	surfaceY + 35, v.getCenter().posZ + r);

		int spawnedGuards = world.getEntitiesWithinAABB(EntityGuard.class, box).size();
//		if (spawnedGuards < BALANCE.MOBPROP.GUARD_MAX_PER_VILLAGE){
//			LogHelper.info("WorldGen: Found village at: " + v.getCenter().posX + " " + v.getCenter().posY
//					+ " " + v.getCenter().posZ + " with " + spawnedGuards + " Guards");
//		}

		// All guards are currently spawning at the center of the village
		for (int i = 1; i < (BALANCE.MOBPROP.GUARD_MAX_PER_VILLAGE * 2) && spawnedGuards < BALANCE.MOBPROP.GUARD_MAX_PER_VILLAGE; i++) {
			int x1 = v.getCenter().posX + world.rand.nextInt((int) (1.2 * r)) - (int) (0.6 * r);
			int z1 = v.getCenter().posZ + world.rand.nextInt((int) (1.2 * r)) - (int) (0.6 * r);
			// Don't spawn in well
			int xFromCenter = MathHelper.abs_int(v.getCenter().posX - x1);
			int zFromCenter = MathHelper.abs_int(v.getCenter().posZ - z1);
			if (xFromCenter < 3 && zFromCenter < 3)
				continue;
			int y1 = world.getHeightValue(x1, z1);
			if (v.isInRange(x1, y1, z1)) {
//				LogHelper.info("WorldGen: going to spawn Guard!");
				Entity e = EntityList.createEntityByName(EntityGuard.getFullName(), world);
				if (e == null){
					LogHelper.error("WorldGen: Could not createEntityByName: " + EntityGuard.getFullName());
					return;
				}
				e.setLocationAndAngles(x1, y1, z1, 0.0F, 0.0F);
				((EntityGuard) e).setFoundHome();
				if (((EntityGuard) e).getCanSpawnHere()) {
					((EntityGuard) e).setHomeArea(v.getCenter().posX, v.getCenter().posY, v.getCenter().posZ, r);
					((EntityGuard) e).setGuardTypePerBiome(world);
					world.spawnEntityInWorld(e);
//					LogHelper.info("WorldGen: Spawned guard at: " + x1 + " " + y1 + " " + z1);
					spawnedGuards++;
				} else {
					e.setDead();
				}
			}
		}
	}

	private void addStructures(World world, Random random, int x, int z) {
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {

		switch (world.provider.dimensionId) {
		case -1:
			generateNether(world, random, chunkX * 16, chunkZ * 16);
		case 0:
			generateSurface(world, random, chunkX * 16, chunkZ * 16);
		case 1:
			generateEnd(world, random, chunkX * 16, chunkZ * 16);
		}

	}

	private void generateEnd(World world, Random random, int x, int z) {

	}

	private void generateNether(World world, Random random, int x, int z) {

	}

	private void generateSurface(World world, Random random, int x, int z) {
		addStructures(world, random, x, z);
		addEntities(world, random, x + 8, z + 8);
	}
}
