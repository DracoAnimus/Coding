package net.wildbill22.draco.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.wildbill22.draco.entities.dragons.EntityDracoMortem;
import net.wildbill22.draco.entities.player.DragonPlayer;

public class ModelDracoMortem extends ModelBase
{
  //fields
    ModelRenderer body;
    ModelRenderer upperBody;
    ModelRenderer rightUpperLeg;
    ModelRenderer rightMiddleLeg;
    ModelRenderer rightLowerLeg;
    ModelRenderer rightFoot;
    ModelRenderer leftUpperLeg;
    ModelRenderer leftMiddleLeg;
    ModelRenderer leftLowerLeg;
    ModelRenderer leftFoot;
    ModelRenderer leftUpperArm;
    ModelRenderer leftLowerArm;
    ModelRenderer rightUpperArm;
    ModelRenderer rightLowerArm;
    ModelRenderer leftWing1;
    ModelRenderer leftWing2;
    ModelRenderer leftWing3;
    ModelRenderer rightWing1;
    ModelRenderer rightWing2;
    ModelRenderer rightWing3;
    ModelRenderer neck;
    ModelRenderer head;
    ModelRenderer leftHorn;
    ModelRenderer rightHorn;
    ModelRenderer topMouth;
    ModelRenderer bottomMouth;
    ModelRenderer tail1;
    ModelRenderer tail2;
  
    DragonPlayer dragonPlayer;

//  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
//  super.render(entity, f, f1, f2, f3, f4, f5);
//  setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    
//  body.render(f5);
//  upperBody.render(f5);
//  rightUpperLeg.render(f5);
//  leftUpperLeg.render(f5);
//  leftUpperArm.render(f5);
//  rightUpperArm.render(f5);
//  leftWing1.render(f5);
//  rightWing1.render(f5);
//  neck.render(f5);
//  tail1.render(f5);
//  tail2.render(f5);
//}

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        if(entity instanceof EntityPlayer) {
            dragonPlayer = DragonPlayer.get((EntityPlayer)entity);
        }
        else
        	dragonPlayer = null;
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        
        neck.render(f5);
        upperBody.render(f5);
        body.render(f5);
        leftUpperArm.render(f5);
        rightUpperArm.render(f5);

        // As a mob, always flying
        if (dragonPlayer == null) {
        	leftWing1.render(f5);
        	rightWing1.render(f5);
        }
        // Player flying
        else if (((EntityPlayer)entity).capabilities.isFlying) {       
        	leftWing1.render(f5);
        	rightWing1.render(f5);
        }
    	leftUpperLeg.render(f5);
    	rightUpperLeg.render(f5);
        tail1.render(f5);
        tail2.render(f5);
    }
    
    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
      
    final float WING_SPEED = 0.6662f;
    final float LEG_SPEED = 0.8662F;
    final float MAXIMUM_WING_ROTATION = 1.4f;
    float MAXIMUM_LEG_ROTATION = 1.4f;
      
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);

        // As a mob, always flying
        if (dragonPlayer == null) {
        	MAXIMUM_LEG_ROTATION = 1.4f / 8;
        	this.leftUpperLeg.rotateAngleX = MathHelper.cos(f * LEG_SPEED) * MAXIMUM_LEG_ROTATION * f1;
        	this.rightUpperLeg.rotateAngleX = MathHelper.cos(f * LEG_SPEED + (float)Math.PI) * MAXIMUM_LEG_ROTATION * f1;
        }
        // Player flying
        else if (((EntityPlayer)entity).capabilities.isFlying) {
        	MAXIMUM_LEG_ROTATION = 1.4f / 8;
        	this.leftUpperLeg.rotateAngleX = MathHelper.cos(f * LEG_SPEED) * MAXIMUM_LEG_ROTATION * f1;
        	this.rightUpperLeg.rotateAngleX = MathHelper.cos(f * LEG_SPEED + (float)Math.PI) * MAXIMUM_LEG_ROTATION * f1;
    		this.leftWing1.rotateAngleZ = MathHelper.cos(f * WING_SPEED + (float)Math.PI) * MAXIMUM_WING_ROTATION * f1;
    		this.rightWing1.rotateAngleZ = MathHelper.cos(f * WING_SPEED) * MAXIMUM_WING_ROTATION * f1;	
        }
        // Player not flying
        else {
        	this.leftUpperLeg.rotateAngleX = MathHelper.cos(f * LEG_SPEED) * MAXIMUM_LEG_ROTATION * f1;
        	this.rightUpperLeg.rotateAngleX = MathHelper.cos(f * LEG_SPEED + (float)Math.PI) * MAXIMUM_LEG_ROTATION * f1;
        }        
    }   
      
    public ModelDracoMortem() {
    textureWidth = 512;
    textureHeight = 256;
    
      body = new ModelRenderer(this, 0, 0);
      body.addBox(-8F, -8F, -26F, 16, 16, 32);
      body.setRotationPoint(0F, -16F, 20F);
      body.setTextureSize(512, 256);
      body.mirror = true;
      setRotation(body, -0.3490659F, 0F, 0F);
      upperBody = new ModelRenderer(this, 300, 0);
      upperBody.addBox(-16F, -8F, -51F, 32, 25, 25);
      upperBody.setRotationPoint(0F, -16F, 20F);
      upperBody.setTextureSize(512, 256);
      upperBody.mirror = true;
      setRotation(upperBody, -0.3490659F, 0F, 0F);
      rightUpperLeg = new ModelRenderer(this, 0, 55);
      rightUpperLeg.addBox(-2.5F, 0F, -8F, 5, 16, 16);
      rightUpperLeg.setRotationPoint(-11F, -16F, 16F);
      rightUpperLeg.setTextureSize(512, 256);
      rightUpperLeg.mirror = true;
      setRotation(rightUpperLeg, -0.3490659F, 0F, 0F);
      rightMiddleLeg = new ModelRenderer(this, 0, 90);
      rightMiddleLeg.addBox(-2.5F, 8F, -15F, 5, 17, 10);
      rightMiddleLeg.setRotationPoint(-11F, -16F, 16F);
      rightMiddleLeg.setTextureSize(512, 256);
      rightMiddleLeg.mirror = true;
      setRotation(rightMiddleLeg, 0.3490659F, 0F, 0F);
      rightLowerLeg = new ModelRenderer(this, 0, 120);
      rightLowerLeg.addBox(-2.5F, 25F, -5F, 5, 15, 8);
      rightLowerLeg.setRotationPoint(-11F, -16F, 16F);
      rightLowerLeg.setTextureSize(512, 256);
      rightLowerLeg.mirror = true;
      setRotation(rightLowerLeg, 0F, 0F, 0F);
      rightFoot = new ModelRenderer(this, 0, 145);
      rightFoot.addBox(-5F, 40F, -11F, 10, 0, 12);
      rightFoot.setRotationPoint(-11F, -16F, 16F);
      rightFoot.setTextureSize(512, 256);
      rightFoot.mirror = true;
      setRotation(rightFoot, 0F, 0F, 0F);
      convertToChild(rightLowerLeg, rightFoot);
      convertToChild(rightMiddleLeg, rightLowerLeg);
      convertToChild(rightUpperLeg, rightMiddleLeg);
      leftUpperLeg = new ModelRenderer(this, 0, 55);
      leftUpperLeg.addBox(-2.5F, 0F, -8F, 5, 16, 16);
      leftUpperLeg.setRotationPoint(11F, -16F, 16F);
      leftUpperLeg.setTextureSize(512, 256);
      leftUpperLeg.mirror = true;
      setRotation(leftUpperLeg, -0.3490659F, 0F, 0F);
      leftMiddleLeg = new ModelRenderer(this, 0, 90);
      leftMiddleLeg.addBox(-2.5F, 8F, -15F, 5, 17, 10);
      leftMiddleLeg.setRotationPoint(11F, -16F, 16F);
      leftMiddleLeg.setTextureSize(512, 256);
      leftMiddleLeg.mirror = true;
      setRotation(leftMiddleLeg, 0.3490659F, 0F, 0F);
      leftLowerLeg = new ModelRenderer(this, 0, 120);
      leftLowerLeg.addBox(-2.5F, 25F, -5F, 5, 15, 8);
      leftLowerLeg.setRotationPoint(11F, -16F, 16F);
      leftLowerLeg.setTextureSize(512, 256);
      leftLowerLeg.mirror = true;
      setRotation(leftLowerLeg, 0F, 0F, 0F);
      leftFoot = new ModelRenderer(this, 0, 145);
      leftFoot.addBox(-5F, 40F, -11F, 10, 0, 12);
      leftFoot.setRotationPoint(11F, -16F, 16F);
      leftFoot.setTextureSize(512, 256);
      leftFoot.mirror = true;
      setRotation(leftFoot, 0F, 0F, 0F);
      convertToChild(leftLowerLeg, leftFoot);
      convertToChild(leftMiddleLeg, leftLowerLeg);
      convertToChild(leftUpperLeg, leftMiddleLeg);
      leftUpperArm = new ModelRenderer(this, 0, 55);
      leftUpperArm.addBox(-0.5F, 0F, -8F, 5, 16, 16);
      leftUpperArm.setRotationPoint(16F, -27F, -19F);
      leftUpperArm.setTextureSize(512, 256);
      leftUpperArm.mirror = true;
      setRotation(leftUpperArm, 0F, 0F, 0F);
      leftLowerArm = new ModelRenderer(this, 50, 55);
      leftLowerArm.addBox(0F, 16F, -4F, 4, 16, 8);
      leftLowerArm.setRotationPoint(16F, -27F, -19F);
      leftLowerArm.setTextureSize(512, 256);
      leftLowerArm.mirror = true;
      setRotation(leftLowerArm, 0F, 0F, 0F);
      convertToChild(leftUpperArm, leftLowerArm);
      rightUpperArm = new ModelRenderer(this, 0, 55);
      rightUpperArm.addBox(-4.5F, 0F, -8F, 5, 16, 16);
      rightUpperArm.setRotationPoint(-16F, -27F, -19F);
      rightUpperArm.setTextureSize(512, 256);
      rightUpperArm.mirror = true;
      setRotation(rightUpperArm, 0F, 0F, 0F);
      rightLowerArm = new ModelRenderer(this, 50, 55);
      rightLowerArm.addBox(-4F, 16F, -4F, 4, 16, 8);
      rightLowerArm.setRotationPoint(-16F, -27F, -19F);
      rightLowerArm.setTextureSize(512, 256);
      rightLowerArm.mirror = true;
      setRotation(rightLowerArm, 0F, 0F, 0F);
      convertToChild(rightUpperArm, rightLowerArm);
      leftWing1 = new ModelRenderer(this, 100, 0);
      leftWing1.addBox(-1.5F, -2.5F, -2.5F, 41, 5, 5);
      leftWing1.setRotationPoint(16F, -31F, -19F);
      leftWing1.setTextureSize(512, 256);
      leftWing1.mirror = true;
      setRotation(leftWing1, 0F, 0F, -0.3490659F);
      leftWing2 = new ModelRenderer(this, 100, 126);
      leftWing2.addBox(-1.5F, -2.5F, 2.5F, 41, 0, 30);
      leftWing2.setRotationPoint(16F, -31F, -19F);
      leftWing2.setTextureSize(512, 256);
      leftWing2.mirror = true;
      setRotation(leftWing2, 0F, 0F, -0.3490659F);
      leftWing3 = new ModelRenderer(this, 100, 159);
      leftWing3.addBox(36.3F, -15.8F, -2.5F, 30, 0, 35);
      leftWing3.setRotationPoint(16F, -31F, -19F);
      leftWing3.setTextureSize(512, 256);
      leftWing3.mirror = true;
      setRotation(leftWing3, 0F, 0F, 0F);
      convertToChild(leftWing1, leftWing3);
      convertToChild(leftWing1, leftWing2);
      rightWing1 = new ModelRenderer(this, 100, 0);
      rightWing1.addBox(-40F, -2.5F, -2.5F, 41, 5, 5);
      rightWing1.setRotationPoint(-16F, -31F, -19F);
      rightWing1.setTextureSize(512, 256);
      rightWing1.mirror = true;
      setRotation(rightWing1, 0F, 0F, 0.3490659F);
      rightWing2 = new ModelRenderer(this, 100, 50);
      rightWing2.addBox(-40F, -2.5F, 2.5F, 41, 0, 30);
      rightWing2.setRotationPoint(-16F, -31F, -19F);
      rightWing2.setTextureSize(512, 256);
      rightWing2.mirror = true;
      setRotation(rightWing2, 0F, 0F, 0.3490659F);
      rightWing3 = new ModelRenderer(this, 100, 85);
      rightWing3.addBox(-66.7F, -16F, -2.5F, 30, 0, 35);
      rightWing3.setRotationPoint(-16F, -31F, -19F);
      rightWing3.setTextureSize(512, 256);
      rightWing3.mirror = true;
      setRotation(rightWing3, 0F, 0F, 0F);
      convertToChild(rightWing1, rightWing3);
      convertToChild(rightWing1, rightWing2);
      neck = new ModelRenderer(this, 200, 0);
      neck.addBox(-7.5F, -7.5F, -30F, 15, 15, 30);
      neck.setRotationPoint(0F, -32F, -26F);
      neck.setTextureSize(512, 256);
      neck.mirror = true;
      setRotation(neck, -0.6981317F, 0F, 0F);
      head = new ModelRenderer(this, 250, 50);
      head.addBox(-10F, -27F, -36F, 20, 20, 20);
      head.setRotationPoint(0F, -32F, -26F);
      head.setTextureSize(512, 256);
      head.mirror = true;
      setRotation(head, 0F, 0F, 0F);
      leftHorn = new ModelRenderer(this, 115, 20);
      leftHorn.addBox(4F, -25F, -32F, 4, 10, 4);
      leftHorn.setRotationPoint(0F, -32F, -26F);
      leftHorn.setTextureSize(512, 256);
      leftHorn.mirror = true;
      setRotation(leftHorn, -0.4164004F, 0F, 0F);
      rightHorn = new ModelRenderer(this, 115, 20);
      rightHorn.addBox(-8F, -25F, -32F, 4, 10, 4);
      rightHorn.setRotationPoint(0F, -32F, -26F);
      rightHorn.setTextureSize(512, 256);
      rightHorn.mirror = true;
      setRotation(rightHorn, -0.4164004F, 0F, 0F);
      topMouth = new ModelRenderer(this, 35, 100);
      topMouth.addBox(-7.5F, -17F, -47F, 15, 4, 11);
      topMouth.setRotationPoint(0F, -32F, -26F);
      topMouth.setTextureSize(512, 256);
      topMouth.mirror = true;
      setRotation(topMouth, 0F, 0F, 0F);
      bottomMouth = new ModelRenderer(this, 43, 84);
      bottomMouth.addBox(-7.5F, -13F, -47F, 15, 3, 11);
      bottomMouth.setRotationPoint(0F, -32F, -26F);
      bottomMouth.setTextureSize(512, 256);
      bottomMouth.mirror = true;
      setRotation(bottomMouth, 0F, 0F, 0F);
      convertToChild(neck, bottomMouth);
      convertToChild(neck, topMouth);
      convertToChild(neck, rightHorn);
      convertToChild(neck, leftHorn);
      convertToChild(neck, head);
      tail1 = new ModelRenderer(this, 0, 170);
      tail1.addBox(-6F, -6F, 1F, 12, 12, 32);
      tail1.setRotationPoint(0F, -16F, 25F);
      tail1.setTextureSize(512, 256);
      tail1.mirror = true;
      setRotation(tail1, -0.3490659F, 0F, 0F);
      tail2 = new ModelRenderer(this, 50, 140);
      tail2.addBox(-4F, -6F, 33F, 8, 8, 16);
      tail2.setRotationPoint(0F, -16F, 25F);
      tail2.setTextureSize(512, 256);
      tail2.mirror = true;
      setRotation(tail2, -0.3490659F, 0F, 0F);
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
