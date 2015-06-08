package net.wildbill22.draco.models.dragons;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

/** 
 * @author WILLIAM
 *
 */
public class ModelCreeperDragon extends ModelBase{
	  //fields
    ModelRenderer headBottom;
    ModelRenderer body;
    ModelRenderer leg3;
    ModelRenderer leg4;
    ModelRenderer leg1;
    ModelRenderer leg2;
    ModelRenderer headTop;
    ModelRenderer lWing;
    ModelRenderer rWing;
  
  public ModelCreeperDragon()
  {
    textureWidth = 128;
    textureHeight = 64;
    
      body = new ModelRenderer(this, 32, 32);
      body.addBox(-4F, -2F, -6F, 8, 4, 16);
      body.setRotationPoint(0F, 0F, -4F);
      body.setTextureSize(128, 64);
      body.mirror = true;
      setRotation(body, 0F, 0F, 0F);

      leg3 = new ModelRenderer(this, 0, 32);
      leg3.addBox(-4F, 0F, -2F, 4, 8, 4);
      leg3.setRotationPoint(-4F, 0F, -6F);
      leg3.setTextureSize(128, 64);
      leg3.mirror = true;
      setRotation(leg3, 0F, 0F, 0F);

      leg4 = new ModelRenderer(this, 0, 32);
      leg4.addBox(0F, 0F, -2F, 4, 8, 4);
      leg4.setRotationPoint(4F, 0F, -4F);
      leg4.setTextureSize(128, 64);
      leg4.mirror = true;
      setRotation(leg4, 0F, 0F, 0F);

      leg1 = new ModelRenderer(this, 0, 50);
      leg1.addBox(-4F, 0F, -2F, 4, 7, 4);
      leg1.setRotationPoint(-4F, 1F, 4F);
      leg1.setTextureSize(128, 64);
      leg1.mirror = true;
      setRotation(leg1, 0F, 0F, 0F);

      leg2 = new ModelRenderer(this, 0, 50);
      leg2.addBox(0F, 0F, -2F, 4, 7, 4);
      leg2.setRotationPoint(4F, 1F, 4F);
      leg2.setTextureSize(128, 64);
      leg2.mirror = true;
      setRotation(leg2, 0F, 0F, 0F);

      headTop = new ModelRenderer(this, 0, 15);
      headTop.addBox(-4F, -4F, -8F, 8, 8, 8);
      headTop.setRotationPoint(0F, 0F, -10F);
      headTop.setTextureSize(128, 64);
      headTop.mirror = true;
      setRotation(headTop, 0F, 0F, 0F);
      headBottom = new ModelRenderer(this, 0, 0);
      headBottom.addBox(-4.5F, -1F, -9F, 9, 5, 9);
      headBottom.setRotationPoint(0F, 0F, -10F);
      headBottom.setTextureSize(128, 64);
      headBottom.mirror = true;
      setRotation(headBottom, 0F, 0F, 0F);

      lWing = new ModelRenderer(this, 36, 15);
      lWing.addBox(0F, -8F, -4F, 0, 8, 8);
      lWing.setRotationPoint(4F, -2F, -4F);
      lWing.setTextureSize(128, 64);
      lWing.mirror = true;
      setRotation(lWing, 0F, 0F, 0.3490659F);
      rWing = new ModelRenderer(this, 36, 15);
      rWing.addBox(0F, -8F, -4F, 0, 8, 8);
      rWing.setRotationPoint(-4F, -2F, -4F);
      rWing.setTextureSize(128, 64);
      rWing.mirror = true;
      setRotation(rWing, 0F, 0F, -0.3490659F);      
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    body.render(f5);
    leg3.render(f5);
    leg4.render(f5);
    leg1.render(f5);
    leg2.render(f5);
    headTop.render(f5);
    headBottom.render(f5);
    lWing.render(f5);
    rWing.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
  {
    super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    this.headBottom.rotateAngleX = f4 / (180F / (float)Math.PI);
    this.headBottom.rotateAngleY = f3 / (180F / (float)Math.PI);
    this.headTop.rotateAngleX = f4 / (180F / (float)Math.PI);
    this.headTop.rotateAngleY = f3 / (180F / (float)Math.PI);
    this.lWing.rotateAngleZ = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 3.0F * f1;
    this.rWing.rotateAngleZ = MathHelper.cos(f * 0.6662F) * 3.0F * f1;
    this.leg1.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
    this.leg2.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 1.4F * f1;
    this.leg3.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 1.4F * f1;
    this.leg4.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
  }
}
