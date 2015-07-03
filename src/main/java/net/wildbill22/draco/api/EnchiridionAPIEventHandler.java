package net.wildbill22.draco.api;

import joshie.enchiridion.designer.ItemBook;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.wildbill22.draco.Core;
import net.wildbill22.draco.lib.LogHelper;
import net.wildbill22.draco.lib.REFERENCE;

/**
 * 
 * @author WILLIAM
 *
 */
//All events in this class are type MinecraftForge.EVENT_BUS

public class EnchiridionAPIEventHandler {
	// Give the player a book
	@SubscribeEvent
	public void onEntityJoinWorld(EntityJoinWorldEvent event) {
		if (event.entity instanceof EntityPlayer) {
			if (!event.entity.worldObj.isRemote) { // On server
				if (Core.isEnchiridionModLoaded && event.world.getTotalWorldTime() < 100) {
					EntityPlayer player = (EntityPlayer) event.entity;
					LogHelper.info("WorldTotalTime: " + event.world.getTotalWorldTime());
					copyBookToInventory(player);
				}
			}
		}
	}
	
	// For survival mode or creative
	private void copyBookToInventory(EntityPlayer player) {
		ItemBook itemBook = (ItemBook) GameRegistry.findItem(REFERENCE.EAPIMODID, REFERENCE.enchiridionItemBookName);
		ItemStack stack = new ItemStack(itemBook, 1, 0);
		if (!player.inventory.hasItem(itemBook)) {
			stack.setTagCompound(new NBTTagCompound());
	        stack.stackTagCompound.setString("identifier", REFERENCE.dracoAnimusBookName);
			player.inventory.addItemStackToInventory(stack);
		}
	}
}
