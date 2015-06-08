package net.wildbill22.draco.models.dragons;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.wildbill22.draco.entities.player.DragonPlayer;

public class ModelDracoAqua extends ModelBase {
  //fields
    ModelRenderer tail1;
    ModelRenderer tail2;
    ModelRenderer tail3;
    ModelRenderer tail4;
    ModelRenderer tailFin;
    ModelRenderer body;
    ModelRenderer head;
    ModelRenderer rightHorn;
    ModelRenderer leftHorn;
    ModelRenderer frontRightLeg;
    ModelRenderer frontLeftLeg;
    ModelRenderer backRightLeg;
    ModelRenderer backLeftLeg;
    ModelRenderer rightTopWing;
    ModelRenderer rightBottomWing;
    ModelRenderer leftTopWing;
    ModelRenderer leftBottomWing;
  
    DragonPlayer dragonPlayer;

  public ModelDracoAqua() {
    textureWidth = 512;
    textureHeight = 256;
    
      tail1 = new ModelRenderer(this, 0, 0);
      tail1.addBox(-5F, -5F, 0F, 10, 10, 20);
      tail1.setRotationPoint(0F, 11F, -10F);
      tail1.setTextureSize(512, 256);
      tail1.mirror = true;
      setRotation(tail1, 0F, 0F, 0F);
      tail2 = new ModelRenderer(this, 0, 0);
      tail2.addBox(-4.5F, -4.5F, 0F, 9, 9, 20);
      tail2.setRotationPoint(0F, 11F, 10F);
      tail2.setTextureSize(512, 256);
      tail2.mirror = true;
      setRotation(tail2, 0F, 0F, 0F);
      tail3 = new ModelRenderer(this, 0, 0);
      tail3.addBox(-4F, 0F, 0F, 8, 8, 10);
      tail3.setRotationPoint(0F, -4F, 40F);
      tail3.setTextureSize(512, 256);
      tail3.mirror = true;
      setRotation(tail3, 0F, 0F, 0F);
      tail4 = new ModelRenderer(this, 0, 0);
      tail4.addBox(-3.5F, -5.5F, -12F, 7, 7, 10);
      tail4.setRotationPoint(0F, 2F, 40F);
      tail4.setTextureSize(512, 256);
      tail4.mirror = true;
      setRotation(tail4, 0F, 0F, 0F);
      tailFin = new ModelRenderer(this, 0, 35);
      tailFin.addBox(0F, -7, -25F, 0, 30, 20);
      tailFin.setRotationPoint(0F, -6F, 40F);
      tailFin.setTextureSize(512, 256);
      tailFin.mirror = true;
      setRotation(tailFin, 0F, 0F, 0F);
      
      body = new ModelRenderer(this, 62, 0);
      body.addBox(-5.5F, -5.5F, -20F, 11, 11, 20);
      body.setRotationPoint(0F, 11F, -10F);
      body.setTextureSize(512, 256);
      body.mirror = true;
      setRotation(body, 0F, 0F, 0F);
      
      head = new ModelRenderer(this, 44, 38);
      head.addBox(-5F, -5F, -15F, 10, 10, 15);
      head.setRotationPoint(0F, 11F, -30F);
      head.setTextureSize(512, 256);
      head.mirror = true;
      setRotation(head, 0F, 0F, 0F);
      rightHorn = new ModelRenderer(this, 127, 0);
      rightHorn.addBox(-6F, 2F, -22F, 1, 1, 15);
      rightHorn.setRotationPoint(0F, 11F, -30F);
      rightHorn.setTextureSize(512, 256);
      rightHorn.mirror = true;
      setRotation(rightHorn, 0F, 0F, 0F);
      leftHorn = new ModelRenderer(this, 127, 0);
      leftHorn.addBox(5F, 2F, -22F, 1, 1, 15);
      leftHorn.setRotationPoint(0F, 11F, -30F);
      leftHorn.setTextureSize(512, 256);
      leftHorn.mirror = true;
      setRotation(leftHorn, 0F, 0F, 0F);
      
      frontRightLeg = new ModelRenderer(this, 100, 40);
      frontRightLeg.addBox(-2F, 0F, -2F, 4, 8, 5);
      frontRightLeg.setRotationPoint(-4F, 16F, -20F);
      frontRightLeg.setTextureSize(512, 256);
      frontRightLeg.mirror = true;
      setRotation(frontRightLeg, 0F, 0F, 0F);
      frontLeftLeg = new ModelRenderer(this, 100, 40);
      frontLeftLeg.addBox(-2F, 0F, -2F, 4, 8, 5);
      frontLeftLeg.setRotationPoint(4F, 16F, -20F);
      frontLeftLeg.setTextureSize(512, 256);
      frontLeftLeg.mirror = true;
      setRotation(frontLeftLeg, 0F, 0F, 0F);
      backRightLeg = new ModelRenderer(this, 100, 40);
      backRightLeg.addBox(-2F, 0F, -2F, 4, 8, 5);
      backRightLeg.setRotationPoint(-4F, 16F, 0F);
      backRightLeg.setTextureSize(512, 256);
      backRightLeg.mirror = true;
      setRotation(backRightLeg, 0F, 0F, 0F);
      backLeftLeg = new ModelRenderer(this, 100, 40);
      backLeftLeg.addBox(-2F, 0F, -2F, 4, 8, 5);
      backLeftLeg.setRotationPoint(4F, 16F, 0F);
      backLeftLeg.setTextureSize(512, 256);
      backLeftLeg.mirror = true;
      setRotation(backLeftLeg, 0F, 0F, 0F);
      
      rightTopWing = new ModelRenderer(this, 120, 40);
      rightTopWing.addBox(-30F, 0F, -15F, 30, 0, 30);
      rightTopWing.setRotationPoint(-5F, 9F, -16F);
      rightTopWing.setTextureSize(512, 256);
      rightTopWing.mirror = true;
      setRotation(rightTopWing, 0F, 0F, 0.4363323F);
      rightBottomWing = new ModelRenderer(this, 120, 40);
      rightBottomWing.addBox(-30F, 0F, -15F, 30, 0, 30);
      rightBottomWing.setRotationPoint(-5F, 13F, -16F);
      rightBottomWing.setTextureSize(512, 256);
      rightBottomWing.mirror = true;
      setRotation(rightBottomWing, 0F, 0F, 0.4363323F);
      leftTopWing = new ModelRenderer(this, 120, 80);
      leftTopWing.addBox(0F, 0F, -15F, 30, 0, 30);
      leftTopWing.setRotationPoint(5F, 9F, -16F);
      leftTopWing.setTextureSize(512, 256);
      leftTopWing.mirror = true;
      setRotation(leftTopWing, 0F, 0F, -0.4363323F);
      leftBottomWing = new ModelRenderer(this, 120, 80);
      leftBottomWing.addBox(0F, 0F, -15F, 30, 0, 30);
      leftBottomWing.setRotationPoint(5F, 13F, -16F);
      leftBottomWing.setTextureSize(512, 256);
      leftBottomWing.mirror = true;
      setRotation(leftBottomWing, 0F, 0F, -0.4363323F);
      convertToChild(tail1, backLeftLeg);
      convertToChild(tail1, backRightLeg);
      convertToChild(tail1, tail2);
      convertToChild(tail2, tail3);
      convertToChild(tail3, tail4);
      convertToChild(tail4, tailFin);
  }

  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    super.render(entity, f, f1, f2, f3, f4, f5);
    if(entity instanceof EntityPlayer) {
        dragonPlayer = DragonPlayer.get((EntityPlayer)entity);
    }
    else
    	dragonPlayer = null;
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    
    tail1.render(f5);
    body.render(f5);
    head.render(f5);
    rightHorn.render(f5);
    leftHorn.render(f5);
    frontRightLeg.render(f5);
    frontLeftLeg.render(f5);
//    backRightLeg.render(f5);
//    backLeftLeg.render(f5);
    
    // As a mob, always flying
    if (dragonPlayer == null) {
	    rightTopWing.render(f5);
	    rightBottomWing.render(f5);
	    leftTopWing.render(f5);
	    leftBottomWing.render(f5);
    }
    // Player flying
    else if (((EntityPlayer)entity).capabilities.isFlying) {       
	    rightTopWing.render(f5);
	    rightBottomWing.render(f5);
	    leftTopWing.render(f5);
	    leftBottomWing.render(f5);
    }
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
		this.leftTopWing.rotateAngleZ = MathHelper.cos(f * WING_SPEED + (float)Math.PI) * MAXIMUM_WING_ROTATION * f1;
		this.leftBottomWing.rotateAngleZ = MathHelper.cos(f * WING_SPEED + (float)Math.PI) * MAXIMUM_WING_ROTATION * f1;
		this.rightTopWing.rotateAngleZ = MathHelper.cos(f * WING_SPEED) * MAXIMUM_WING_ROTATION * f1;
		this.rightBottomWing.rotateAngleZ = MathHelper.cos(f * WING_SPEED) * MAXIMUM_WING_ROTATION * f1;
    }
    // Player flying
    else if (((EntityPlayer)entity).capabilities.isFlying) {
    	MAXIMUM_LEG_ROTATION = 1.4f / 8;
		this.frontRightLeg.rotateAngleX = MathHelper.cos(f * LEG_SPEED) * MAXIMUM_LEG_ROTATION * f1;
		this.backRightLeg.rotateAngleX = MathHelper.cos(f * LEG_SPEED) * MAXIMUM_LEG_ROTATION * f1;
		this.frontLeftLeg.rotateAngleX = MathHelper.cos(f * LEG_SPEED + (float)Math.PI) * MAXIMUM_LEG_ROTATION * f1;
		this.backLeftLeg.rotateAngleX = MathHelper.cos(f * LEG_SPEED + (float)Math.PI) * MAXIMUM_LEG_ROTATION * f1;
		this.leftTopWing.rotateAngleZ = MathHelper.cos(f * WING_SPEED + (float)Math.PI) * MAXIMUM_WING_ROTATION * f1;
		this.leftBottomWing.rotateAngleZ = MathHelper.cos(f * WING_SPEED + (float)Math.PI) * MAXIMUM_WING_ROTATION * f1;
		this.rightTopWing.rotateAngleZ = MathHelper.cos(f * WING_SPEED) * MAXIMUM_WING_ROTATION * f1;
		this.rightBottomWing.rotateAngleZ = MathHelper.cos(f * WING_SPEED) * MAXIMUM_WING_ROTATION * f1;
    }		
    // Player not flying
    else {
		this.frontRightLeg.rotateAngleX = MathHelper.cos(f * LEG_SPEED) * MAXIMUM_LEG_ROTATION * f1;
		this.backRightLeg.rotateAngleX = MathHelper.cos(f * LEG_SPEED) * MAXIMUM_LEG_ROTATION * f1;
		this.frontLeftLeg.rotateAngleX = MathHelper.cos(f * LEG_SPEED + (float)Math.PI) * MAXIMUM_LEG_ROTATION * f1;
		this.backLeftLeg.rotateAngleX = MathHelper.cos(f * LEG_SPEED + (float)Math.PI) * MAXIMUM_LEG_ROTATION * f1;
    }
	
	// Tail wagging
	this.tail1.rotateAngleY = MathHelper.cos(f * 0.8662F) * 1.4F * f1;
	this.tail2.rotateAngleY = MathHelper.cos(f * 0.8662F + (float)Math.PI) * 1.3F * f1;
	this.tail3.rotateAngleY = MathHelper.cos(f * 0.8662F) * 1.2F * f1;
	this.tail4.rotateAngleY = MathHelper.cos(f * 0.8662F + (float)Math.PI) * 1F * f1;		
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
