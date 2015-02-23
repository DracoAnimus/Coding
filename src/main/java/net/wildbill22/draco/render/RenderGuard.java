package net.wildbill22.draco.render;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.wildbill22.draco.lib.REFERENCE;

public class RenderGuard extends RenderLiving{
	public RenderGuard(ModelBiped par1ModelBiped, float shadowSize) {
		super(par1ModelBiped, shadowSize);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
		return new ResourceLocation(REFERENCE.MODID + ":textures/models/guardTexture.png");
	}
}
