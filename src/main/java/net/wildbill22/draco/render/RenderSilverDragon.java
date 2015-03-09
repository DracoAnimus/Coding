package net.wildbill22.draco.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.wildbill22.draco.lib.REFERENCE;

// Must extend RendererLivingEntity, not RenderLiving, so player can change to dragon!
public class RenderSilverDragon extends RendererLivingEntity {
	protected ResourceLocation dragonTexture;
	
	public RenderSilverDragon(ModelBase par1ModelBase, float parShadowSize) {
		super(par1ModelBase, parShadowSize);
		this.renderManager = RenderManager.instance;
		setEntityTexture();
	}

	// Add logic here for different dragons
	private void setEntityTexture() {
		dragonTexture = new ResourceLocation(REFERENCE.MODID + ":textures/models/silverDragon.png");
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
		return dragonTexture;
	}
}
