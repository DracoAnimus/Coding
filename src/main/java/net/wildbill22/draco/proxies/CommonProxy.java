package net.wildbill22.draco.proxies;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.MinecraftForge;
import net.wildbill22.draco.handlers.DragonPlayerEventHandler;
import net.wildbill22.draco.handlers.EntityGuardEventHandler;
import net.wildbill22.draco.lib.LogHelper;

/**
 * 
 * @author WILLIAM
 *
 */
public class CommonProxy {
	private static final Map<String, NBTTagCompound> extendedEntityData = new HashMap<String, NBTTagCompound>();

	/**
	 * Removes the stored data from map and returns it
	 * 
	 * @param name
	 * @return
	 */
	public static NBTTagCompound getEntityData(String name) {
		return extendedEntityData.remove(name);
	}

	/**
	 * Stores entity data
	 * 
	 * @param name
	 * @param compound
	 */
	public static void storeEntityData(String name, NBTTagCompound compound) {
		extendedEntityData.put(name, compound);
	}

	public void registerRenderer(){}
	
	public void registerTileEntitySpecialRenderer(){}
	
	public void registerSounds() {}
	
	public void registerSubscriptions() {
		LogHelper.info("CommonProxy: Registering subscriptions");
		MinecraftForge.EVENT_BUS.register(new DragonPlayerEventHandler());    // For player
    	MinecraftForge.EVENT_BUS.register(new EntityGuardEventHandler());     // For Guards 
	}
}
