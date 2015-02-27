package net.wildbill22.draco.render;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.wildbill22.draco.entities.hostile.EntityGuard;
import net.wildbill22.draco.lib.REFERENCE;

public class RenderGuard extends RenderBiped{
	private static ResourceLocation resourceLocation;
	
	public RenderGuard(ModelBiped par1ModelBiped, float shadowSize) {
		super(par1ModelBiped, shadowSize);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		resourceLocation = new ResourceLocation(REFERENCE.MODID + ((EntityGuard)entity).type.getGuardResourceKey());
		return resourceLocation;
	}
}
