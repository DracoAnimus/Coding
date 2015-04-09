package net.wildbill22.draco;

import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.wildbill22.draco.tile_entity.TileEntityTemporaryHoard;
import cpw.mods.fml.common.network.IGuiHandler;

/**
 * Gui handler to open Gui's or their container on server side
 */
public class GuiHandler implements IGuiHandler {

	public final static int ID_HOARD=1;
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if(ID==ID_HOARD){
			TileEntityTemporaryHoard tile=(TileEntityTemporaryHoard) world.getTileEntity(x, y, z);
			return tile.getNewInventoryContainer(player.inventory);
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if(ID==ID_HOARD){
			/**
			 * Create a new GuiChest, but replace the container by a modified version
			 */
			TileEntityTemporaryHoard tile=(TileEntityTemporaryHoard) world.getTileEntity(x, y, z);
			GuiChest gui=new GuiChest(player.inventory,tile.getInventory());
			gui.inventorySlots=tile.getNewInventoryContainer(player.inventory);
			return gui;
		}
		return null;
	}

}
