package net.wildbill22.draco.handlers;

import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.village.Village;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.wildbill22.draco.entities.hostile.EntityGuard;
import net.wildbill22.draco.lib.BALANCE;

/**
 * 
 * @author WILLIAM
 * This is called from SpawnerAnimals class
 * 2/24/15: It is called for vanilla hostile mobs and guards 
 *
 */
public class EntityGuardEventHandler {
	@SubscribeEvent
	public void onEntityJoinWorldEvent(EntityJoinWorldEvent event) {
		if (event.entity instanceof EntityGuard){
			EntityGuard guard = (EntityGuard) event.entity;
	    	// If spawned by WorldGen, it will not be looking for home
	    	if (guard.isLookingForHome()) {
	    		if (okToSpawnNearVillage(event, 50)) {
//		    		LogHelper.info("EntityJoinWorldEvent: Do spawn " + guard.getGuardType() + " at: " + guard.posX + ", " + guard.posY + ", " + guard.posZ);
	    			// If I set to ALLOW, need to do all own checks here, so use DEFAULT
		    		event.setResult(Result.DEFAULT);
		    	}
		    	else {
		    		// Prevents spawns not in a village 
//		    		LogHelper.info("onLivingCheckSpawnEvent: Don't spawn Guard at: " + guard.posX + ", " + guard.posY + ", " + guard.posZ);
		    		event.setResult(Result.DENY);	    		
		    	}
	    	}
		}
	}
	
	public boolean okToSpawnNearVillage(EntityJoinWorldEvent event, int distanceToLook) {
		World world = event.world;
		int x = (int) event.entity.posX;
		int z = (int) event.entity.posZ;
		int surfaceY = world.getHeightValue(x, z);
		Village v = world.villageCollectionObj.findNearestVillage(x, surfaceY, z, distanceToLook);
		if (v == null) {
			return false;
		}

		int r = v.getVillageRadius();
		AxisAlignedBB box = AxisAlignedBB.getBoundingBox(v.getCenter().posX - r, surfaceY - 20, v.getCenter().posZ - r, 
				v.getCenter().posX + r,	surfaceY + 35, v.getCenter().posZ + r);
		int spawnedGuards = world.getEntitiesWithinAABB(EntityGuard.class, box).size();
//		LogHelper.info("GuardSpawn: Found village at: " + v.getCenter().posX + " " + v.getCenter().posY
//				+ " " + v.getCenter().posZ + " with " + spawnedGuards + " Guards");
		if (v.isInRange(x, surfaceY, z) && spawnedGuards < BALANCE.MOBPROP.GUARD_MAX_PER_VILLAGE) {
			EntityGuard guard = (EntityGuard) event.entity;
			if (guard instanceof EntityGuard) {
				guard.setHomeArea(v.getCenter().posX, v.getCenter().posY, v.getCenter().posZ, r);
				guard.setFoundHome();
				return true;
			}
		}
		return false;
	}
}
