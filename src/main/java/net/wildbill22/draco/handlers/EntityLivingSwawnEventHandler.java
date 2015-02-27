package net.wildbill22.draco.handlers;

import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.wildbill22.draco.entities.hostile.EntityGuard;
import net.wildbill22.draco.lib.LogHelper;

/**
 * 
 * @author WILLIAM
 * This is called from SpawnerAnimals class
 * 2/24/15: It is called for vanilla hostile mobs and just not my mob 
 *
 */
public class EntityLivingSwawnEventHandler {
	@SubscribeEvent
	public void onLivingCheckSpawnEvent(LivingSpawnEvent.SpecialSpawn event) {
//	public void onLivingCheckSpawnEvent(LivingSpawnEvent.CheckSpawn event) {
		if (event.entityLiving instanceof EntityGuard){
//		if (event.entity instanceof EntityGuard){
			EntityGuard guard = (EntityGuard) event.entityLiving;
	    	// If spawned by WorldGen, it will not be looking for home
	    	if (guard.isLookingForHome()) {
	    		if (guard.okToSpawnNearVillage(40)) {
	    			// If I set to ALLOW, need to do all own checks here, so use DEFAULT
		    		event.setResult(Result.DEFAULT);
		    		LogHelper.info("onLivingCheckSpawnEvent: Do spawn Guard at: " + guard.posX + ", " + guard.posY + ", " + guard.posZ);
		    	}
		    	else {
		    		// Prevents spawns not in a village 
		    		LogHelper.info("onLivingCheckSpawnEvent: Don't spawn Guard at: " + guard.posX + ", " + guard.posY + ", " + guard.posZ);
		    		event.setResult(Result.DENY);	    		
		    	}
	    	}
		}
	}	
}
