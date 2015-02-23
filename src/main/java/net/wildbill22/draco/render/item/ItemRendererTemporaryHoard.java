package net.wildbill22.draco.render.item;

import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import net.wildbill22.draco.tile_entity.TileEntityTemporaryHoard;

/**
 * 
 * @author WILLIAM
 *
 */
public class ItemRendererTemporaryHoard implements IItemRenderer {

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		return true;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item,
			ItemRendererHelper helper) {
		return true;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {

		TileEntityRendererDispatcher.instance.renderTileEntityAt(new TileEntityTemporaryHoard(), 0.0D, 0.0D, 0.0D, 0.0F);
	}
}
