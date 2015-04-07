package net.wildbill22.draco.handlers;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.wildbill22.draco.generation.structures.StructureList;

/**
 * 
 * @author WILLIAM
 *
 */

public class StructureEventHandler {
	@SubscribeEvent
	public void onWorldLoad(WorldEvent.Load event) {
		StructureList.readFromFile(event);
	}	

	@SubscribeEvent
	public void onWorldSave(WorldEvent.Unload event) {
		StructureList.writeToFile(event);
	}
}
