package net.wildbill22.draco.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.wildbill22.draco.entities.hostile.EntityBallista;
import net.wildbill22.draco.lib.REFERENCE;

public class RenderBallista extends RenderLiving {
	private static ResourceLocation[] texture;

	public RenderBallista(ModelBase par1ModelBase, float par2) {
		super(par1ModelBase, par2);
		texture = new ResourceLocation[4];
		texture[0] = new ResourceLocation(REFERENCE.MODID + ":textures/models/ballistaIdle.png");
		texture[1] = new ResourceLocation(REFERENCE.MODID + ":textures/models/ballistaPulling1.png");
		texture[2] = new ResourceLocation(REFERENCE.MODID + ":textures/models/ballistaPulling2.png");
		texture[3] = new ResourceLocation(REFERENCE.MODID + ":textures/models/ballistaPulling3.png");
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return texture[((EntityBallista)entity).getBowPosition()];
	}
}
