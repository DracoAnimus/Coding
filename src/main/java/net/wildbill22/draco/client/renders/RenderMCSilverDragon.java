package net.wildbill22.draco.client.renders;

import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import net.wildbill22.draco.client.models.ModelMCSilverDragon;
import net.wildbill22.draco.common.entities.dragons.EntityMCSilverDragon;
import net.wildbill22.draco.lib.REFERENCE;

public class RenderMCSilverDragon extends RendererLivingEntity {

	public static final ResourceLocation SilverDragon_texture = new ResourceLocation(REFERENCE.MODID + ":textures/models/mcSilverDragon.png");
	public static ModelMCSilverDragon modelSilverDragon = new ModelMCSilverDragon();	
	public static float modelHeight = 2F;
	
	public RenderMCSilverDragon() {
        super(modelSilverDragon, 1F);
		this.renderManager = RenderManager.instance;
  }
	
	@Override
	public void doRender(Entity _entity, double posX, double posY, double posZ, float var8, float var9) {
//		EntityMCSilverDragon entity = (EntityMCSilverDragon) _entity;
				
		GL11.glPushMatrix();
		GL11.glDisable(GL11.GL_CULL_FACE);
		super.doRender(_entity, posX, posY, posZ, var8, var9);
//		super.doRender(entity, posX, posY, posZ, var8, var9);
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glPopMatrix();
	}
	
	@Override
	protected void preRenderCallback(EntityLivingBase entityliving, float f)
	{
		GL11.glRotatef(180F, 0, 1F, 0F);
		GL11.glRotatef(180F, 0, 0, 1F);
		GL11.glTranslatef(0, modelHeight, 0);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity var1) {
		return SilverDragon_texture;
	}
}