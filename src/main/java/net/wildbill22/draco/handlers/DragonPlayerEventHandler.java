package net.wildbill22.draco.handlers;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.wildbill22.draco.blocks.TemporaryHoard;
import net.wildbill22.draco.client.renders.RenderMCSilverDragon;
import net.wildbill22.draco.common.entities.dragons.EntityMCDragon;
import net.wildbill22.draco.common.entities.dragons.EntityMCSilverDragon;
import net.wildbill22.draco.entities.player.DragonPlayer;
import net.wildbill22.draco.items.ModItems;
import net.wildbill22.draco.lib.LogHelper;
import net.wildbill22.draco.models.ModelSilverDragon;
import net.wildbill22.draco.render.RenderSilverDragon;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class DragonPlayerEventHandler {
	Render renderPlayerDragon = new RenderSilverDragon(new ModelSilverDragon(), 0.5F);
//	Render renderPlayerDragon = new RenderMCSilverDragon();

	// Add DragonPlayer properties to player
	@SubscribeEvent
	public void onEntityConstructing(EntityConstructing event) {
		if (event.entity instanceof EntityPlayer && DragonPlayer.get((EntityPlayer) event.entity) == null) {
			DragonPlayer.register((EntityPlayer) event.entity, event.entity.worldObj);
			LogHelper.info("DragonPlayerEventHandler: Registered a new DragonPlayer");
		}
	}
	
	// This is supposed to ensure that the player can fly, but still can lose ability if changing from creative
	@SubscribeEvent
	public void onEntityJoinWorld(EntityJoinWorldEvent event) {
		if (!event.entity.worldObj.isRemote && event.entity instanceof EntityPlayer) {
			DragonPlayer.loadProxyData((EntityPlayer) event.entity);
        	if (DragonPlayer.get((EntityPlayer) event.entity).isDragon()) {
        		((EntityPlayer)event.entity).capabilities.allowFlying = true;
        		if (DragonPlayer.get((EntityPlayer) event.entity).wasFlyingOnExit)
        			((EntityPlayer)event.entity).capabilities.isFlying = true;
        		// ((EntityPlayer)event.entity).sendPlayerAbilities();
        	}
		}
	}

	// Need to call this until I figure out how to detect switching from creative to survival mode (makes you not fly)
	@SubscribeEvent
    public void onLivingUpdateEvent(LivingUpdateEvent event) {
        if (event.entityLiving != null) {
            if(event.entityLiving instanceof EntityPlayer) {
            	if (DragonPlayer.get((EntityPlayer) event.entity).isDragon()) {
            		((EntityPlayer)event.entity).capabilities.allowFlying = true;
            		((EntityPlayer)event.entity).sendPlayerAbilities();
            	}
            }
        }
    }
	
	// Some other events to maybe use, that are on FMLCommonHandler.bus(), so need a separate handler
	// PlayerEvent.PlayerRespawnEvent - maybe to fix setting can fly after dying

    // Added to remove chest location
	@SubscribeEvent
	public void onPlacingBlock(BlockEvent.BreakEvent event) {
		EntityPlayer player = event.getPlayer();
		if (!player.worldObj.isRemote && event.block instanceof TemporaryHoard) {
	    	DragonPlayer.get(player).removeHoard(event.x, event.y, event.z);		
	    	DragonPlayer.get(player).calculateHoardSize(event.world);
			DragonPlayer.saveProxyData(event.getPlayer());
		}
	}

	// Added to save chest location when a chest is placed
	@SubscribeEvent
	public void onHarvestBlock(BlockEvent.PlaceEvent event) {
		if (!event.world.isRemote && event.block instanceof TemporaryHoard) {
			DragonPlayer.get((EntityPlayer) event.player).addHoard(event.x, event.y, event.z);
			DragonPlayer.saveProxyData(event.player);
		}
	}

	// Added to track how many gold coins the Player Dragon has (would be better if closing container)
	@SubscribeEvent
    public void onPlayerInteractBlock(PlayerInteractEvent event) {
//		Not required anymore
//		EntityPlayer player = event.entityPlayer;
//		Block block = event.world.getBlock(event.x, event.y, event.z);
//		if (!player.worldObj.isRemote && block instanceof TemporaryHoard) {
//			if (DragonPlayer.get(player).calculateHoardSize(event.world))
//				player.addChatMessage(new ChatComponentText("Put gold coins in the hoard."));
//		}
	}
	
	// Gives fireballs or explosive fireball each time you kill something when a dragon
	@SubscribeEvent
	public void onLivingDropsEvent(LivingDropsEvent event) {
		if (event.source.getEntity() instanceof EntityPlayer) {
			LogHelper.info("DragonPlayerEventHandler: Player killed something!");
			EntityPlayer player = (EntityPlayer) event.source.getEntity();
			if (DragonPlayer.get(player).isDragon()) {
				int chance  = Math.max((DragonPlayer.get(player).getLevel() / 2), 1);
				int num = new Random().nextInt(chance) + 1;
				LogHelper.info("DragonPlayerEventHandler: Player killed something! Adding " + num + " Fireballs!");
				for (int i = 0; i < num; i++) {
					if (!addItemToHotbar(player.inventory, ModItems.fireball))
						addItemToHotbar(player.inventory, ModItems.explosiveFireball);
				}
			}
		}
	}
	
	private static boolean addItemToHotbar(InventoryPlayer hotbar, Item item) {
		int hotbarSize = InventoryPlayer.getHotbarSize();
		if (hotbar.hasItem(item)) {
			for (int i = 0; i < hotbarSize; i++) {
				ItemStack itemStack = hotbar.getStackInSlot(i);
				if (itemStack != null && itemStack.getItem().equals(item)) {
					if (itemStack.stackSize < itemStack.getMaxStackSize()) {
						itemStack.stackSize++;
						return true;
					}
				}					
			}
		}
		else {
			int slot = hotbar.getFirstEmptyStack();
			if (slot >= 0) {
				ItemStack items = new ItemStack(item);
				hotbar.setInventorySlotContents(slot, items);
				return true;
			}
		}
		return false;
	}
	
	// On MinecraftForge.EVENT_BUS
//	@SubscribeEvent
//	public void onEntityWorldSave(PlayerEvent.SaveToFile event) {
//		if (!event.entity.worldObj.isRemote && event.entity instanceof EntityPlayer) {
//			DragonPlayer.saveProxyData((EntityPlayer) event.entity, true);
//		}		
//	}

	// Will need this when player dragon looses a level on death
	// Also needed to save data, since it gets loaded again when player respawns
	@SubscribeEvent
	public void onLivingDeathEvent(LivingDeathEvent event) {
		if (!event.entity.worldObj.isRemote && event.entity instanceof EntityPlayer) {
			DragonPlayer.saveProxyData((EntityPlayer) event.entity);
		}
	}
	
	// To prevent death by lava for a dragon
	@SubscribeEvent
	public void onLivingHurtEvent(LivingHurtEvent event) {
		if (!event.entity.worldObj.isRemote && event.entity instanceof EntityPlayer && DragonPlayer.get((EntityPlayer) event.entity).isDragon()) {
			if (event.source.equals(DamageSource.lava) || event.source.equals(DamageSource.onFire) || event.source.equals(DamageSource.inFire)) {
				event.ammount = 0;
				LogHelper.info("DragonPlayerEventHandler: onLivingHurtEvent, no damage from lava!");
			}
		}
	}
	
	// Render player as dragon if a dragon
	@SubscribeEvent
	public void OnPlayerRender(RenderPlayerEvent.Pre event) {
    	if (DragonPlayer.get((EntityPlayer) event.entity).isDragon()) {
			event.setCanceled(true);
			renderPlayerDragon.doRender(event.entityPlayer, 0D, -1.4D, 0D, 0F, 0F);
    	}
	}
}
