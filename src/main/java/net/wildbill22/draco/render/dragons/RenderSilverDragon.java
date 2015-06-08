package net.wildbill22.draco.render.dragons;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.wildbill22.draco.entities.dragons.DragonRegistry.IDragonRendererCreationHandler;
import net.wildbill22.draco.entities.dragons.EntityGoldDragon;
import net.wildbill22.draco.entities.dragons.EntitySilverDragon;
import net.wildbill22.draco.lib.REFERENCE;

// Must extend RendererLivingEntity, not RenderLiving, so player can change to dragon!
public class RenderSilverDragon extends RendererLivingEntity implements IDragonRendererCreationHandler {
	protected ResourceLocation dragonTexture;
	private int type;
	
	public RenderSilverDragon(ModelBase par1ModelBase, float parShadowSize, int type) {
		super(par1ModelBase, parShadowSize);
		this.renderManager = RenderManager.instance;
		setEntityTexture(type);
		this.type = type;
	}

	// Add logic here for different dragons
	private void setEntityTexture(int type) {
		if (type == 0)
			dragonTexture = new ResourceLocation(REFERENCE.MODID + ":textures/models/silverDragon.png");
		else
			dragonTexture = new ResourceLocation(REFERENCE.MODID + ":textures/models/goldDragon.png");
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
		return dragonTexture;
	}

	@Override
	public String getKey() {
		if (type == 0)
			return EntitySilverDragon.name;
		else
			return EntityGoldDragon.name;
	}
}
