package net.wildbill22.draco.models.dragons;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.wildbill22.draco.entities.player.DragonPlayer;

// Very similar to Draco Mortem (Skeleton Dragon)
public class ModelDracoTenebrosus extends ModelBase
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

  public ModelDracoTenebrosus()
  {
	    textureWidth = 512;
	    textureHeight = 512;
	    
	      body = new ModelRenderer(this, 0, 0);
	      body.addBox(-8F, -8F, -26F, 16, 16, 32);
	      body.setRotationPoint(0F, -16F, 20F);
	      body.setTextureSize(512, 512);
	      body.mirror = true;
	      setRotation(body, -0.3490659F, 0F, 0F);
	      upperBody = new ModelRenderer(this, 0, 0);
	      upperBody.addBox(-16F, -8F, -51F, 32, 25, 25);
	      upperBody.setRotationPoint(0F, -16F, 20F);
	      upperBody.setTextureSize(512, 512);
	      upperBody.mirror = true;
	      setRotation(upperBody, -0.3490659F, 0F, 0F);
	      
	      rightUpperLeg = new ModelRenderer(this, 0, 0);
	      rightUpperLeg.addBox(-2.5F, 0F, -8F, 5, 16, 16);
	      rightUpperLeg.setRotationPoint(-11F, -16F, 16F);
	      rightUpperLeg.setTextureSize(512, 512);
	      rightUpperLeg.mirror = true;
	      setRotation(rightUpperLeg, -0.3490659F, 0F, 0F);
	      rightMiddleLeg = new ModelRenderer(this, 0, 0);
	      rightMiddleLeg.addBox(-2.5F, 8F, -15F, 5, 17, 10);
	      rightMiddleLeg.setRotationPoint(-11F, -16F, 16F);
	      rightMiddleLeg.setTextureSize(512, 512);
	      rightMiddleLeg.mirror = true;
	      setRotation(rightMiddleLeg, 0.3490659F, 0F, 0F);
	      rightLowerLeg = new ModelRenderer(this, 0, 0);
	      rightLowerLeg.addBox(-2.5F, 25F, -5F, 5, 15, 8);
	      rightLowerLeg.setRotationPoint(-11F, -16F, 16F);
	      rightLowerLeg.setTextureSize(512, 512);
	      rightLowerLeg.mirror = true;
	      setRotation(rightLowerLeg, 0F, 0F, 0F);
	      rightFoot = new ModelRenderer(this, 0, 300);
	      rightFoot.addBox(-5.5F, 40F, -11F, 11, 0, 12);
	      rightFoot.setRotationPoint(-11F, -16F, 16F);
	      rightFoot.setTextureSize(512, 512);
	      rightFoot.mirror = true;
	      setRotation(rightFoot, 0F, 0F, 0F);
	      convertToChild(rightLowerLeg, rightFoot);
	      convertToChild(rightMiddleLeg, rightLowerLeg);
	      convertToChild(rightUpperLeg, rightMiddleLeg);
	      
	      leftUpperLeg = new ModelRenderer(this, 0, 0);
	      leftUpperLeg.addBox(-2.5F, 0F, -8F, 5, 16, 16);
	      leftUpperLeg.setRotationPoint(11F, -16F, 16F);
	      leftUpperLeg.setTextureSize(512, 512);
	      leftUpperLeg.mirror = true;
	      setRotation(leftUpperLeg, -0.3490659F, 0F, 0F);
	      leftMiddleLeg = new ModelRenderer(this, 0, 0);
	      leftMiddleLeg.addBox(-2.5F, 8F, -15F, 5, 17, 10);
	      leftMiddleLeg.setRotationPoint(11F, -16F, 16F);
	      leftMiddleLeg.setTextureSize(512, 512);
	      leftMiddleLeg.mirror = true;
	      setRotation(leftMiddleLeg, 0.3490659F, 0F, 0F);
	      leftLowerLeg = new ModelRenderer(this, 0, 0);
	      leftLowerLeg.addBox(-2.5F, 25F, -5F, 5, 15, 8);
	      leftLowerLeg.setRotationPoint(11F, -16F, 16F);
	      leftLowerLeg.setTextureSize(512, 512);
	      leftLowerLeg.mirror = true;
	      setRotation(leftLowerLeg, 0F, 0F, 0F);
	      leftFoot = new ModelRenderer(this, 0, 320);
	      leftFoot.addBox(-5.5F, 40F, -11F, 11, 0, 12);
	      leftFoot.setRotationPoint(11F, -16F, 16F);
	      leftFoot.setTextureSize(512, 512);
	      leftFoot.mirror = true;
	      setRotation(leftFoot, 0F, 0F, 0F);
	      convertToChild(leftLowerLeg, leftFoot);
	      convertToChild(leftMiddleLeg, leftLowerLeg);
	      convertToChild(leftUpperLeg, leftMiddleLeg);	      
	      
	      leftWing1 = new ModelRenderer(this, 200, 0);
	      leftWing1.addBox(0F, -2.5F, -2.5F, 80, 5, 5);
	      leftWing1.setRotationPoint(16F, -31F, -19F);
	      leftWing1.setTextureSize(512, 512);
	      leftWing1.mirror = true;
	      setRotation(leftWing1, 0F, 0F, -0.3490659F);
	      leftWing2 = new ModelRenderer(this, 0, 51);
	      leftWing2.addBox(0F, -2.5F, 2.5F, 80, 0, 75);
	      leftWing2.setRotationPoint(16F, -31F, -19F);
	      leftWing2.setTextureSize(512, 512);
	      leftWing2.mirror = true;
	      setRotation(leftWing2, 0F, 0F, -0.3490659F);
	      leftWing3 = new ModelRenderer(this, 0, 320);
	      leftWing3.addBox(74F, -30F, -28F, 90, 0, 105);
	      leftWing3.setRotationPoint(16F, -31F, -19F);
	      leftWing3.setTextureSize(512, 512);
	      leftWing3.mirror = true;
	      setRotation(leftWing3, 0F, 0F, 0F);
	      convertToChild(leftWing1, leftWing3);
	      convertToChild(leftWing1, leftWing2);
	      
	      rightWing1 = new ModelRenderer(this, 200, 0);
	      rightWing1.addBox(-79F, -2.5F, -2.5F, 80, 5, 5);
	      rightWing1.setRotationPoint(-16F, -31F, -19F);
	      rightWing1.setTextureSize(512, 512);
	      rightWing1.mirror = true;
	      setRotation(rightWing1, 0F, 0F, 0.3490659F);
	      rightWing2 = new ModelRenderer(this, 0, 130);
	      rightWing2.addBox(-79F, -2.5F, 1.5F, 80, 0, 75);
	      rightWing2.setRotationPoint(-16F, -31F, -19F);
	      rightWing2.setTextureSize(512, 512);
	      rightWing2.mirror = true;
	      setRotation(rightWing2, 0F, 0F, 0.3490659F);
	      rightWing3 = new ModelRenderer(this, 0, 210);
	      rightWing3.addBox(-163F, -29F, -29F, 90, 0, 105);
	      rightWing3.setRotationPoint(-16F, -31F, -19F);
	      rightWing3.setTextureSize(512, 512);
	      rightWing3.mirror = true;
	      setRotation(rightWing3, 0F, 0F, 0F);
	      convertToChild(rightWing1, rightWing3);
	      convertToChild(rightWing1, rightWing2);

	      neck = new ModelRenderer(this, 0, 0);
	      neck.addBox(-7.5F, -7.5F, -30F, 15, 15, 30);
	      neck.setRotationPoint(0F, -32F, -26F);
	      neck.setTextureSize(512, 512);
	      neck.mirror = true;
	      setRotation(neck, -0.6981317F, 0F, 0F);
	      head = new ModelRenderer(this, 0, 220);
	      head.addBox(-10F, -27F, -36F, 20, 20, 20);
	      head.setRotationPoint(0F, -32F, -26F);
	      head.setTextureSize(512, 512);
	      head.mirror = true;
	      setRotation(head, 0F, 0F, 0F);
	      leftHorn = new ModelRenderer(this, 0, 0);
	      leftHorn.addBox(4F, -25F, -32F, 4, 10, 4);
	      leftHorn.setRotationPoint(0F, -32F, -26F);
	      leftHorn.setTextureSize(512, 512);
	      leftHorn.mirror = true;
	      setRotation(leftHorn, -0.4164004F, 0F, 0F);
	      rightHorn = new ModelRenderer(this, 0, 0);
	      rightHorn.addBox(-8F, -25F, -32F, 4, 10, 4);
	      rightHorn.setRotationPoint(0F, -32F, -26F);
	      rightHorn.setTextureSize(512, 512);
	      rightHorn.mirror = true;
	      setRotation(rightHorn, -0.4164004F, 0F, 0F);
	      topMouth = new ModelRenderer(this, 0, 100);
	      topMouth.addBox(-7.5F, -17F, -47F, 15, 4, 11);
	      topMouth.setRotationPoint(0F, -32F, -26F);
	      topMouth.setTextureSize(512, 512);
	      topMouth.mirror = true;
	      setRotation(topMouth, 0F, 0F, 0F);
	      bottomMouth = new ModelRenderer(this, 0, 84);
	      bottomMouth.addBox(-7.5F, -13F, -47F, 15, 3, 11);
	      bottomMouth.setRotationPoint(0F, -32F, -26F);
	      bottomMouth.setTextureSize(512, 512);
	      bottomMouth.mirror = true;
	      setRotation(bottomMouth, 0F, 0F, 0F);
	      convertToChild(neck, bottomMouth);
	      convertToChild(neck, topMouth);
	      convertToChild(neck, rightHorn);
	      convertToChild(neck, leftHorn);
	      convertToChild(neck, head);
	      
	      tail1 = new ModelRenderer(this, 0, 0);
	      tail1.addBox(-6F, -6F, 1F, 12, 12, 32);
	      tail1.setRotationPoint(0F, -16F, 25F);
	      tail1.setTextureSize(512, 512);
	      tail1.mirror = true;
	      setRotation(tail1, -0.3490659F, 0F, 0F);
	      tail2 = new ModelRenderer(this, 0, 0);
	      tail2.addBox(-4F, -6F, 33F, 8, 8, 16);
	      tail2.setRotationPoint(0F, -16F, 25F);
	      tail2.setTextureSize(512, 512);
	      tail2.mirror = true;
	      setRotation(tail2, -0.3490659F, 0F, 0F);
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
    upperBody.render(f5);
    
    neck.render(f5);
//    head.render(f5);
//    leftHorn.render(f5);
//    rightHorn.render(f5);
//    topMouth.render(f5);
//    bottomMouth.render(f5);

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
    
    rightUpperLeg.render(f5);
    leftUpperLeg.render(f5);
    tail1.render(f5);
    tail2.render(f5);

//    rightMiddleLeg.render(f5);
//    rightLowerLeg.render(f5);
//    rightFoot.render(f5);
    
//    leftMiddleLeg.render(f5);
//    leftLowerLeg.render(f5);
//    leftFoot.render(f5);

//    leftWing2.render(f5);
//    leftWing3.render(f5);

//    rightWing2.render(f5);
//    rightWing3.render(f5);
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
