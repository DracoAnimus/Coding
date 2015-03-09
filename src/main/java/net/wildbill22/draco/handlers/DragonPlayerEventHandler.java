package net.wildbill22.draco.handlers;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.wildbill22.draco.entities.player.DragonPlayer;
import net.wildbill22.draco.models.ModelSilverDragon;
import net.wildbill22.draco.render.RenderSilverDragon;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class DragonPlayerEventHandler {
	// Add DragonPlayer properties to player
	@SubscribeEvent
	public void onEntityConstructing(EntityConstructing event) {
		if (event.entity instanceof EntityPlayer && DragonPlayer.get((EntityPlayer) event.entity) == null) {
			DragonPlayer.register((EntityPlayer) event.entity);
		}
	}
	
	@SubscribeEvent
	public void onEntityJoinWorld(EntityJoinWorldEvent event) {
		if (!event.entity.worldObj.isRemote && event.entity instanceof EntityPlayer) {
//			DragonPlayer.loadProxyData((EntityPlayer) event.entity);
        	if (DragonPlayer.get((EntityPlayer) event.entity).isDragon()) {
        		((EntityPlayer)event.entity).capabilities.allowFlying = true;
        		if (DragonPlayer.get((EntityPlayer) event.entity).wasFlyingOnExit)
        			((EntityPlayer)event.entity).capabilities.isFlying = true;
        		((EntityPlayer)event.entity).sendPlayerAbilities();
        	}
		}
	}

//	@SubscribeEvent
//	public void onServerChatEvent(CommandEvent event) {
//		LogHelper.info("DragonPlayerEventHandler: CommandEvent is about to occur!");
//	}
	
//	@SubscribeEvent
//	public void onEntityWorldSave(PlayerEvent.SaveToFile event) {
//		if (!event.entity.worldObj.isRemote && event.entity instanceof EntityPlayer) {
//			DragonPlayer.saveProxyData((EntityPlayer) event.entity, true);
//		}		
//	}

	// Need to call this until I figure out how to detect switching from creative to survival mode (makes you not fly)
	@SubscribeEvent
    public void onLivingUpdateEvent(LivingUpdateEvent event)
    {
        if (event.entityLiving != null) {
            if(event.entityLiving instanceof EntityPlayer) {
            	if (DragonPlayer.get((EntityPlayer) event.entity).isDragon()) {
            		((EntityPlayer)event.entity).capabilities.allowFlying = true;
            		((EntityPlayer)event.entity).sendPlayerAbilities();
            	}
            }
        }
    }

	// Will need this when player dragon looses a level on death
//	@SubscribeEvent
//	public void onLivingDeathEvent(LivingDeathEvent event) {
//		if (!event.entity.worldObj.isRemote && event.entity instanceof EntityPlayer) {
//			DragonPlayer.saveProxyData((EntityPlayer) event.entity, true);
//		}
//	}
	
	Render renderPlayerDragon = new RenderSilverDragon(new ModelSilverDragon(), 0.5F);

	// Render player as dragon if a dragon
	@SubscribeEvent
	public void OnPlayerRender(RenderPlayerEvent.Pre event) {
    	if (DragonPlayer.get((EntityPlayer) event.entity).isDragon()) {
			event.setCanceled(true);
			renderPlayerDragon.doRender(event.entityPlayer, 0D, 0D, 0D, 0F, 0F);
    	}
	}
}
