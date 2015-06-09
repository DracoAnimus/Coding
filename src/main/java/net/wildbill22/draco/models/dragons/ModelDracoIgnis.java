package net.wildbill22.draco.models.dragons;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelDracoIgnis extends ModelBase
{
	  //fields
    ModelRenderer body;
    ModelRenderer rightTopLeg;
    ModelRenderer rightBottomLeg;
    ModelRenderer rightFoot;
    ModelRenderer leftTopLeg;
    ModelRenderer leftBottomLeg;
    ModelRenderer leftFoot;
    ModelRenderer rightArm;
    ModelRenderer leftArm;
    ModelRenderer jaw;
    ModelRenderer tail1;
    ModelRenderer tail2;
    ModelRenderer tail3;
    ModelRenderer tail4;
    ModelRenderer tail5;
    ModelRenderer tail6;
    ModelRenderer neck;
    ModelRenderer leftEye;
    ModelRenderer rightEye;
    ModelRenderer head;
 

//    DragonPlayer dragonPlayer;

  public ModelDracoIgnis()
  {
	    textureWidth = 128;
	    textureHeight = 64;
	    
	      // body
	      body = new ModelRenderer(this, 0, 0);
	      body.addBox(-4.5F, 0F, -10F, 9, 8, 20);
	      body.setRotationPoint(0F, 0F, 0F);
	      body.setTextureSize(128, 64);
	      body.mirror = true;
	      setRotation(body, -0.5235988F, 0F, 0F);

	      // Neck and other head parts
	      neck = new ModelRenderer(this, 60, 24);
	      neck.addBox(-3F, -3F, -8F, 6, 6, 8);
	      neck.setRotationPoint(0F, -1F, -9F);
	      neck.setTextureSize(128, 64);
	      neck.mirror = true;
	      setRotation(neck, -0.8805134F, 0F, 0F);
	      leftEye = new ModelRenderer(this, 43, 29);
	      leftEye.addBox(2.5F, -8.5F, -11F, 1, 1, 2);
	      leftEye.setRotationPoint(0F, -1F, -9F);
	      leftEye.setTextureSize(128, 64);
	      leftEye.mirror = true;
	      setRotation(leftEye, 0F, 0F, 0F);
	      rightEye = new ModelRenderer(this, 50, 29);
	      rightEye.addBox(-3.5F, -8.5F, -11F, 1, 1, 2);
	      rightEye.setRotationPoint(0F, -1F, -9F);
	      rightEye.setTextureSize(128, 64);
	      rightEye.mirror = true;
	      setRotation(rightEye, 0F, 0F, 0F);
	      head = new ModelRenderer(this, 35, 39);
	      head.addBox(-3F, -9F, -12F, 6, 4, 10);
	      head.setRotationPoint(0F, -1F, -9F);
	      head.setTextureSize(128, 64);
	      head.mirror = true;
	      setRotation(head, 0F, 0F, 0F);
	      jaw = new ModelRenderer(this, 60, 10);
	      jaw.addBox(-2.5F, -5F, -11.5F, 5, 2, 9);
	      jaw.setRotationPoint(0F, -1F, -9F);
	      jaw.setTextureSize(128, 64);
	      jaw.mirror = true;
	      setRotation(jaw, 0F, 0F, 0F);
	      convertToChild(neck, head);
	      convertToChild(neck, leftEye);	      
	      convertToChild(neck, rightEye);	      
	      convertToChild(neck, jaw);	      

	      // Right leg
	      rightTopLeg = new ModelRenderer(this, 0, 29);
	      rightTopLeg.addBox(-0.5F, 0F, -3F, 1, 10, 6);
	      rightTopLeg.setRotationPoint(-5F, 4F, 0F);
	      rightTopLeg.setTextureSize(128, 64);
	      rightTopLeg.mirror = true;
	      setRotation(rightTopLeg, 0.5235988F, 0F, 0F);
	      rightBottomLeg = new ModelRenderer(this, 16, 29);
	      rightBottomLeg.addBox(-0.5F, 3F, 6.5F, 1, 15, 3);
	      rightBottomLeg.setRotationPoint(-5F, 4F, 0F);
	      rightBottomLeg.setTextureSize(128, 64);
	      rightBottomLeg.mirror = true;
	      setRotation(rightBottomLeg, -0.5235988F, 0F, 0F);
	      rightFoot = new ModelRenderer(this, 26, 29);
	      rightFoot.addBox(-1.5F, 19F, -5F, 3, 1, 5);
	      rightFoot.setRotationPoint(-5F, 4F, 0F);
	      rightFoot.setTextureSize(128, 64);
	      rightFoot.mirror = true;
	      setRotation(rightFoot, 0F, 0F, 0F);
	      convertToChild(rightTopLeg, rightBottomLeg);
	      convertToChild(rightTopLeg, rightFoot);
	      
	      // left leg
	      leftTopLeg = new ModelRenderer(this, 0, 46);
	      leftTopLeg.addBox(-0.5F, 0F, -3F, 1, 10, 6);
	      leftTopLeg.setRotationPoint(5F, 4F, 0F);
	      leftTopLeg.setTextureSize(128, 64);
	      leftTopLeg.mirror = true;
	      setRotation(leftTopLeg, 0.5235988F, 0F, 0F);
	      leftBottomLeg = new ModelRenderer(this, 26, 45);
	      leftBottomLeg.addBox(-0.5F, 3F, 6.5F, 1, 15, 3);
	      leftBottomLeg.setRotationPoint(5F, 4F, 0F);
	      leftBottomLeg.setTextureSize(128, 64);
	      leftBottomLeg.mirror = true;
	      setRotation(leftBottomLeg, -0.5235988F, 0F, 0F);
	      leftFoot = new ModelRenderer(this, 26, 29);
	      leftFoot.addBox(-1.5F, 19F, -5F, 3, 1, 5);
	      leftFoot.setRotationPoint(5F, 4F, 0F);
	      leftFoot.setTextureSize(128, 64);
	      leftFoot.mirror = true;
	      setRotation(leftFoot, 0F, 0F, 0F);
	      convertToChild(leftTopLeg, leftBottomLeg);
	      convertToChild(leftTopLeg, leftFoot);
	      
	      rightArm = new ModelRenderer(this, 60, 0);
	      rightArm.addBox(-0.5F, -1F, -6F, 1, 2, 6);
	      rightArm.setRotationPoint(-4F, 3F, -9F);
	      rightArm.setTextureSize(128, 64);
	      rightArm.mirror = true;
	      setRotation(rightArm, 0.1745329F, 0F, 0F);

	      leftArm = new ModelRenderer(this, 75, 0);
	      leftArm.addBox(-0.5F, -1F, -6F, 1, 2, 6);
	      leftArm.setRotationPoint(4F, 3F, -9F);
	      leftArm.setTextureSize(128, 64);
	      leftArm.mirror = true;
	      setRotation(leftArm, 0.1745329F, 0F, 0F);

	      tail1 = new ModelRenderer(this, 90, 0);
	      tail1.addBox(-4F, 0.5F, 0F, 8, 7, 5);
	      tail1.setRotationPoint(0F, 4F, 8F);
	      tail1.setTextureSize(128, 64);
	      tail1.mirror = true;
	      setRotation(tail1, -0.5235988F, 0F, 0F);
	      tail2 = new ModelRenderer(this, 90, 13);
	      tail2.addBox(-3.5F, 1F, 5F, 7, 6, 3);
	      tail2.setRotationPoint(0F, 4F, 8F);
	      tail2.setTextureSize(128, 64);
	      tail2.mirror = true;
	      setRotation(tail2, -0.5235988F, 0F, 0F);
	      tail3 = new ModelRenderer(this, 90, 23);
	      tail3.addBox(-3F, 1.5F, 8F, 6, 5, 3);
	      tail3.setRotationPoint(0F, 4F, 8F);
	      tail3.setTextureSize(128, 64);
	      tail3.mirror = true;
	      setRotation(tail3, -0.5235988F, 0F, 0F);
	      tail4 = new ModelRenderer(this, 90, 32);
	      tail4.addBox(-2.5F, 2F, 11F, 5, 4, 3);
	      tail4.setRotationPoint(0F, 4F, 8F);
	      tail4.setTextureSize(128, 64);
	      tail4.mirror = true;
	      setRotation(tail4, -0.5235988F, 0F, 0F);
	      tail5 = new ModelRenderer(this, 90, 40);
	      tail5.addBox(-2F, 2.5F, 14F, 4, 3, 3);
	      tail5.setRotationPoint(0F, 4F, 8F);
	      tail5.setTextureSize(128, 64);
	      tail5.mirror = true;
	      setRotation(tail5, -0.5235988F, 0F, 0F);
	      tail6 = new ModelRenderer(this, 90, 47);
	      tail6.addBox(-1.5F, 3F, 17F, 3, 2, 3);
	      tail6.setRotationPoint(0F, 4F, 8F);
	      tail6.setTextureSize(128, 64);
	      tail6.mirror = true;
	      setRotation(tail6, -0.5235988F, 0F, 0F);
	      convertToChild(tail1, tail2);
	      convertToChild(tail1, tail3);
	      convertToChild(tail1, tail4);
	      convertToChild(tail1, tail5);
	      convertToChild(tail1, tail6);	      
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
	    super.render(entity, f, f1, f2, f3, f4, f5);
//	    if(entity instanceof EntityPlayer) {
//	        dragonPlayer = DragonPlayer.get((EntityPlayer)entity);
//	    }
//	    else
//	    	dragonPlayer = null;
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    
    // scale the whole thing for big or small entities
    GL11.glPushMatrix();
    GL11.glScalef(1.25F, 1.25F, 1.25F);

    body.render(f5);
    neck.render(f5);
    rightTopLeg.render(f5);
    leftTopLeg.render(f5);
    rightArm.render(f5);
    leftArm.render(f5);
    tail1.render(f5);
    
    GL11.glPopMatrix();
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z) {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
//  final float WING_SPEED = 0.6662f;
//  final float MAXIMUM_WING_ROTATION = 1.4f;
  float MAXIMUM_LEG_ROTATION = 1.1f;
  final float LEG_SPEED = 0.8662F;

  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
    super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    
   	this.leftTopLeg.rotateAngleX = MathHelper.cos(f * LEG_SPEED) * MAXIMUM_LEG_ROTATION * f1;
   	this.rightTopLeg.rotateAngleX = MathHelper.cos(f * LEG_SPEED + (float)Math.PI) * MAXIMUM_LEG_ROTATION * f1;
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
