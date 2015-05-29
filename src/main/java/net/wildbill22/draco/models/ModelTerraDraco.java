package net.wildbill22.draco.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.wildbill22.draco.entities.player.DragonPlayer;

public class ModelTerraDraco extends ModelBase
{
  //fields
    ModelRenderer body;
    ModelRenderer backSpikes;
    ModelRenderer head;
    ModelRenderer jaw;
    ModelRenderer frontRightLeg;
    ModelRenderer frontRightFoot;
    ModelRenderer frontLeftLeg;
    ModelRenderer frontLeftFoot;
    ModelRenderer backLeftLeg;
    ModelRenderer backLeftFoot;
    ModelRenderer backRightLeg;
    ModelRenderer backRightFoot;
    ModelRenderer leftWing;
    ModelRenderer rightWing;

    DragonPlayer dragonPlayer;

  public ModelTerraDraco()
  {
    textureWidth = 512;
    textureHeight = 256;
    
      body = new ModelRenderer(this, 0, 0);
      body.addBox(-15F, 0F, 0F, 30, 16, 50);
      body.setRotationPoint(0F, -10F, -25F);
      body.setTextureSize(512, 256);
      body.mirror = true;
      setRotation(body, 0F, 0F, 0F);      
      backSpikes = new ModelRenderer(this, 0, 22);
      backSpikes.addBox(-0.5F, -16F, 0F, 0, 16, 50);
      backSpikes.setRotationPoint(0F, -10F, -25F);
      backSpikes.setTextureSize(512, 256);
      backSpikes.mirror = true;
      setRotation(backSpikes, 0F, 0F, 0F);
      convertToChild(body, backSpikes);
      
      head = new ModelRenderer(this, 161, 0);
      head.addBox(-10F, -10F, -22F, 20, 10, 22);
      head.setRotationPoint(0F, -10F, -25F);
      head.setTextureSize(512, 256);
      head.mirror = true;
      setRotation(head, 0F, 0F, 0F);
      jaw = new ModelRenderer(this, 0, 97);
      jaw.addBox(-12.5F, 0F, -25F, 25, 15, 25);
      jaw.setRotationPoint(0F, -10F, -25F);
      jaw.setTextureSize(512, 256);
      jaw.mirror = true;
      setRotation(jaw, 0F, 0F, 0F);
      convertToChild(head, jaw);
      
      frontRightLeg = new ModelRenderer(this, 0, 150);
      frontRightLeg.addBox(-5F, 0F, -5F, 10, 15, 10);
      frontRightLeg.setRotationPoint(-15F, -3F, -18F);
      frontRightLeg.setTextureSize(512, 256);
      frontRightLeg.mirror = true;
      setRotation(frontRightLeg, 0F, 0F, 0F);
      frontRightFoot = new ModelRenderer(this, 0, 180);
      frontRightFoot.addBox(-6F, 15F, -6F, 12, 12, 12);
      frontRightFoot.setRotationPoint(-15F, -3F, -18F);
      frontRightFoot.setTextureSize(512, 256);
      frontRightFoot.mirror = true;
      setRotation(frontRightFoot, 0F, 0F, 0F);
      convertToChild(frontRightLeg, frontRightFoot);
      
      frontLeftLeg = new ModelRenderer(this, 50, 150);
      frontLeftLeg.addBox(-5F, 0F, -5F, 10, 15, 10);
      frontLeftLeg.setRotationPoint(15F, -3F, -18F);
      frontLeftLeg.setTextureSize(512, 256);
      frontLeftLeg.mirror = true;
      setRotation(frontLeftLeg, 0F, 0F, 0F);
      frontLeftFoot = new ModelRenderer(this, 50, 180);
      frontLeftFoot.addBox(-6F, 15F, -6F, 12, 12, 12);
      frontLeftFoot.setRotationPoint(15F, -3F, -18F);
      frontLeftFoot.setTextureSize(512, 256);
      frontLeftFoot.mirror = true;
      setRotation(frontLeftFoot, 0F, 0F, 0F);
      convertToChild(frontLeftLeg, frontLeftFoot);
      
      backLeftLeg = new ModelRenderer(this, 100, 150);
      backLeftLeg.addBox(-5F, 0F, -5F, 10, 15, 10);
      backLeftLeg.setRotationPoint(11F, -3F, 15F);
      backLeftLeg.setTextureSize(512, 256);
      backLeftLeg.mirror = true;
      setRotation(backLeftLeg, 0F, 0F, 0F);
      backLeftFoot = new ModelRenderer(this, 100, 180);
      backLeftFoot.addBox(-6F, 15F, -6F, 12, 12, 12);
      backLeftFoot.setRotationPoint(11F, -3F, 15F);
      backLeftFoot.setTextureSize(512, 256);
      backLeftFoot.mirror = true;
      setRotation(backLeftFoot, 0F, 0F, 0F);
      convertToChild(backLeftLeg, backLeftFoot);
      
      backRightLeg = new ModelRenderer(this, 150, 150);
      backRightLeg.addBox(-5F, 0F, -5F, 10, 15, 10);
      backRightLeg.setRotationPoint(-11F, -3F, 15F);
      backRightLeg.setTextureSize(512, 256);
      backRightLeg.mirror = true;
      setRotation(backRightLeg, 0F, 0F, 0F);
      backRightFoot = new ModelRenderer(this, 150, 180);
      backRightFoot.addBox(-6F, 15F, -6F, 12, 12, 12);
      backRightFoot.setRotationPoint(-11F, -3F, 15F);
      backRightFoot.setTextureSize(512, 256);
      backRightFoot.mirror = true;
      setRotation(backRightFoot, 0F, 0F, 0F);
      convertToChild(backRightLeg, backRightFoot);
      
      leftWing = new ModelRenderer(this, 210, 70);
      leftWing.addBox(0F, 0F, -25F, 50, 0, 50);
      leftWing.setRotationPoint(14F, -8F, 0F);
      leftWing.setTextureSize(512, 256);
      leftWing.mirror = true;
      setRotation(leftWing, 0F, 0F, -1.22173F);

      rightWing = new ModelRenderer(this, 210, 140);
      rightWing.addBox(-50F, 0F, -25F, 50, 0, 50);
      rightWing.setRotationPoint(-14F, -8F, 0F);
      rightWing.setTextureSize(512, 256);
      rightWing.mirror = true;
      setRotation(rightWing, 0F, 0F, 1.22173F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
	    super.render(entity, f, f1, f2, f3, f4, f5);
	    if(entity instanceof EntityPlayer) {
	        dragonPlayer = DragonPlayer.get((EntityPlayer)entity);
	    }
	    else
	    	dragonPlayer = null;
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    
    body.render(f5);
//    backSpikes.render(f5);
    head.render(f5);
//    jaw.render(f5);
    frontRightLeg.render(f5);
//    frontRightFoot.render(f5);
    frontLeftLeg.render(f5);
//    frontLeftFoot.render(f5);
    backLeftLeg.render(f5);
//    backLeftFoot.render(f5);
    backRightLeg.render(f5);
//    backRightFoot.render(f5);
    leftWing.render(f5);
    rightWing.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z) {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  final float WING_SPEED = 0.6662f;
  final float MAXIMUM_WING_ROTATION = 1.4f;
  float MAXIMUM_LEG_ROTATION = 1.4f;
  final float LEG_SPEED = 0.8662F;

  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
    super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    
    // As a mob, always flying
    if (dragonPlayer == null) {
    	MAXIMUM_LEG_ROTATION = 1.4f / 8;
		this.frontRightLeg.rotateAngleX = MathHelper.cos(f * LEG_SPEED) * MAXIMUM_LEG_ROTATION * f1;
		this.backRightLeg.rotateAngleX = MathHelper.cos(f * LEG_SPEED) * MAXIMUM_LEG_ROTATION * f1;
		this.frontLeftLeg.rotateAngleX = MathHelper.cos(f * LEG_SPEED + (float)Math.PI) * MAXIMUM_LEG_ROTATION * f1;
		this.backLeftLeg.rotateAngleX = MathHelper.cos(f * LEG_SPEED + (float)Math.PI) * MAXIMUM_LEG_ROTATION * f1;
		
		this.leftWing.rotateAngleZ = MathHelper.cos(f * WING_SPEED + (float)Math.PI) * MAXIMUM_WING_ROTATION * f1;
		this.rightWing.rotateAngleZ = MathHelper.cos(f * WING_SPEED) * MAXIMUM_WING_ROTATION * f1;
    }
    // Player flying
    else if (((EntityPlayer)entity).capabilities.isFlying) {
    	MAXIMUM_LEG_ROTATION = 1.4f / 8;
		this.frontRightLeg.rotateAngleX = MathHelper.cos(f * LEG_SPEED) * MAXIMUM_LEG_ROTATION * f1;
		this.backRightLeg.rotateAngleX = MathHelper.cos(f * LEG_SPEED) * MAXIMUM_LEG_ROTATION * f1;
		this.frontLeftLeg.rotateAngleX = MathHelper.cos(f * LEG_SPEED + (float)Math.PI) * MAXIMUM_LEG_ROTATION * f1;
		this.backLeftLeg.rotateAngleX = MathHelper.cos(f * LEG_SPEED + (float)Math.PI) * MAXIMUM_LEG_ROTATION * f1;

		this.leftWing.rotateAngleZ = MathHelper.cos(f * WING_SPEED + (float)Math.PI) * MAXIMUM_WING_ROTATION * f1;
		this.rightWing.rotateAngleZ = MathHelper.cos(f * WING_SPEED) * MAXIMUM_WING_ROTATION * f1;
    }		
    // Player not flying
    else {
		this.frontRightLeg.rotateAngleX = MathHelper.cos(f * LEG_SPEED) * MAXIMUM_LEG_ROTATION * f1;
		this.backRightLeg.rotateAngleX = MathHelper.cos(f * LEG_SPEED) * MAXIMUM_LEG_ROTATION * f1;
		this.frontLeftLeg.rotateAngleX = MathHelper.cos(f * LEG_SPEED + (float)Math.PI) * MAXIMUM_LEG_ROTATION * f1;
		this.backLeftLeg.rotateAngleX = MathHelper.cos(f * LEG_SPEED + (float)Math.PI) * MAXIMUM_LEG_ROTATION * f1;
    }	
  }

  protected void convertToChild(ModelRenderer parParent, ModelRenderer parChild) {
	// move child rotation point to be relative to parent
    parChild.rotationPointX -= parParent.rotationPointX;
    parChild.rotationPointY -= parParent.rotationPointY;
    parChild.rotationPointZ -= parParent.rotationPointZ;
    // make rotations relative to parent
    parChild.rotateAngleX -= parParent.rotateAngleX;
    parChild.rotateAngleY -= parParent.rotateAngleY;
    parChild.rotateAngleZ -= parParent.rotateAngleZ;
    // create relationship
    parParent.addChild(parChild);
  }
}
