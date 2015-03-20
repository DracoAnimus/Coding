package net.wildbill22.draco.render;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.wildbill22.draco.entities.hostile.EntityGuard;
import net.wildbill22.draco.lib.REFERENCE;

public class RenderGuard extends RenderBiped{
	private static ResourceLocation[] texture;
	
	public RenderGuard(ModelBiped par1ModelBiped, float shadowSize) {
		super(par1ModelBiped, shadowSize);
		texture = new ResourceLocation[2];
		texture[0] = new ResourceLocation(REFERENCE.MODID + ":textures/models/guardTexture.png");
		texture[1] = new ResourceLocation(REFERENCE.MODID + ":textures/models/knightTexture.png");
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return texture[((EntityGuard) entity).type.getType()];
	}
}
