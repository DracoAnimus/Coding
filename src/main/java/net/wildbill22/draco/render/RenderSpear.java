package net.wildbill22.draco.render;

import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.wildbill22.draco.lib.REFERENCE;

public class RenderSpear extends RenderSnowball {

	public RenderSpear(Item item) {
		super(item);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
		return new ResourceLocation(REFERENCE.MODID + ":textures/items/spear.png");
	}
}
