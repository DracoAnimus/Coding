package net.wildbill22.draco.tile_entity;

import net.wildbill22.draco.lib.REFERENCE;

import cpw.mods.fml.common.registry.GameRegistry;

public class ModTileEntities {

	public static void modRegistry(){
		registerTileEntities();
	}
	
	private static void registerTileEntities() {
		GameRegistry.registerTileEntity(TileEntityTemporaryHoard.class, REFERENCE.MODID);		
	}
}
