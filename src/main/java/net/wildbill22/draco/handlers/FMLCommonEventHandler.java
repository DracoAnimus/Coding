package net.wildbill22.draco.handlers;

import net.minecraft.entity.player.EntityPlayer;
import net.wildbill22.draco.entities.player.DragonPlayer;
import net.wildbill22.draco.items.ItemDragonEgg;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;

/**
 * 
 * @author WILLIAM
 *
 */
//All events in this class are type FMLCommonHandler.bus()

public class FMLCommonEventHandler {
//	@SubscribeEvent
//	public void onChangeDimension(PlayerEvent.PlayerChangedDimensionEvent event) {
//		if (event.player instanceof EntityPlayer && DragonPlayer.get((EntityPlayer) event.player) != null) {
//			if (DragonPlayer.get(event.player).isDragon()) {
//				if (ItemDragonEgg.hasAbility(event.player, ItemDragonEgg.Abilities.ISMADEOFWATER)) {
//					event.setCanceled(true);
//				}
//			}
//		}
//	}	
}
