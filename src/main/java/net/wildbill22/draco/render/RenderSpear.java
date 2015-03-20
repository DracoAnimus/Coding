package net.wildbill22.draco.render;

import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.wildbill22.draco.lib.REFERENCE;

public class RenderSpear extends RenderSnowball {
	private static ResourceLocation texture;

	public RenderSpear(Item item) {
		super(item);
		texture = new ResourceLocation(REFERENCE.MODID + ":textures/items/spear.png");
	}
	
	@Override
	protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
		return texture;
	}
}
