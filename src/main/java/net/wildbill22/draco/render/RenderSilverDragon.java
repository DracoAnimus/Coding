package net.wildbill22.draco.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.wildbill22.draco.lib.REFERENCE;

public class RenderSilverDragon extends RenderLiving {
	public RenderSilverDragon(ModelBase par1ModelBase, float par2) {
		super(par1ModelBase, par2);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
		return new ResourceLocation(REFERENCE.MODID + ":textures/models/silverDragon.png");
	}
}