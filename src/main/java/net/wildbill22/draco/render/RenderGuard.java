package net.wildbill22.draco.render;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.wildbill22.draco.entities.hostile.EntityGuard;
import net.wildbill22.draco.lib.LogHelper;
import net.wildbill22.draco.lib.REFERENCE;

public class RenderGuard extends RenderBiped{
	private static ResourceLocation[] texture;
	
	public RenderGuard(ModelBiped par1ModelBiped, float shadowSize) {
		super(par1ModelBiped, shadowSize);
		texture = new ResourceLocation[5];
		texture[0] = new ResourceLocation(REFERENCE.MODID + ":textures/models/guardTexture.png");
		texture[1] = new ResourceLocation(REFERENCE.MODID + ":textures/models/knightTexture.png");
		texture[2] = new ResourceLocation(REFERENCE.MODID + ":textures/models/guardTexture.png");
		texture[3] = new ResourceLocation(REFERENCE.MODID + ":textures/models/guardTexture.png");
		texture[4] = new ResourceLocation(REFERENCE.MODID + ":textures/models/guardTexture.png");
//		LogHelper.info("RenderGuard: Setting new texture");
	}


	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return texture[((EntityGuard) entity).getGuardType().getGuardId()];
	}	
}
