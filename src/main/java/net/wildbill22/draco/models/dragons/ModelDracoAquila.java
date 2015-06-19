package net.wildbill22.draco.models.dragons;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.wildbill22.draco.entities.player.DragonPlayer;

public class ModelDracoAquila extends ModelBase
{
    //fields for Eagle Dragon
    ModelRenderer groundLeftTopLeg;
    ModelRenderer groundLeftLeg;
    ModelRenderer groundLeftFoot;
    ModelRenderer groundRightTopLeg;
    ModelRenderer groundRightLeg;
    ModelRenderer groundRightFoot;
    ModelRenderer groundTailFeathers;
    ModelRenderer groundLeftWing;
    ModelRenderer groundRightWing;
    ModelRenderer groundBeakTop;
    ModelRenderer groundBeakBottom;
    ModelRenderer groundBody;
    ModelRenderer groundHead;
    ModelRenderer flyingLeftTopLeg;
    ModelRenderer flyingLeftLeg;
    ModelRenderer flyingLeftFoot;
    ModelRenderer flyingRightTopLeg;
    ModelRenderer flyingRightLeg;
    ModelRenderer flyingRightFoot;
    ModelRenderer flyingBeakTop;
    ModelRenderer flyingBeakBottom;
    ModelRenderer flyingTail;
    ModelRenderer flyingHead;
    ModelRenderer flyingBody;
    ModelRenderer flyingRightWing;
    ModelRenderer flyingLeftWing;

    DragonPlayer dragonPlayer;

  public ModelDracoAquila()
  {
    textureWidth = 512;
    textureHeight = 256;

    // On Ground
    groundBeakTop = new ModelRenderer(this, 41, 54);
    groundBeakTop.addBox(-2.5F, -5F, -13.5F, 5, 1, 5);
    groundBeakTop.setRotationPoint(0F, -25F, -6F);
    groundBeakTop.setTextureSize(512, 256);
//    groundBeakTop.mirror = true;
    setRotation(groundBeakTop, 0F, 0F, 0F);
    groundBeakBottom = new ModelRenderer(this, 41, 61);
    groundBeakBottom.addBox(-2.5F, -4.146667F, -13.5F, 5, 1, 5);
    groundBeakBottom.setRotationPoint(0F, -25F, -6F);
    groundBeakBottom.setTextureSize(512, 256);
//    groundBeakBottom.mirror = true;
    setRotation(groundBeakBottom, 0F, 0F, 0F);
    groundHead = new ModelRenderer(this, 112, 14);
    groundHead.addBox(-8.5F, -15F, -8.5F, 17, 17, 17);
    groundHead.setRotationPoint(0F, -25F, -6F);
    groundHead.setTextureSize(512, 256);
//    groundHead.mirror = true;
    setRotation(groundHead, 0F, 0F, 0F);
    convertToChild(groundHead, groundBeakTop);
    convertToChild(groundHead, groundBeakBottom);	      

    groundBody = new ModelRenderer(this, 0, 0);
    groundBody.addBox(-10F, -30F, -10F, 20, 30, 20);
    groundBody.setRotationPoint(0F, 5F, 0F);
    groundBody.setTextureSize(512, 256);
//    groundBody.mirror = true;
    setRotation(groundBody, 0.1745329F, 0F, 0F);

    groundLeftWing = new ModelRenderer(this, 81, 4);
    groundLeftWing.addBox(12F, -30F, -8F, 0, 40, 15);
    groundLeftWing.setRotationPoint(-1F, 5F, 0F);
    groundLeftWing.setTextureSize(512, 256);
    groundLeftWing.mirror = false;
    setRotation(groundLeftWing, 0.1745329F, 0F, -0.0349066F);

    groundRightWing = new ModelRenderer(this, 181, 4);
    groundRightWing.addBox(-10F, -30F, -8F, 0, 40, 15);
    groundRightWing.setRotationPoint(-1F, 5F, 0F);
    groundRightWing.setTextureSize(512, 256);
//    groundRightWing.mirror = true;
    setRotation(groundRightWing, 0.1745329F, 0F, 0.0349066F);

    groundLeftTopLeg = new ModelRenderer(this, 99, 0);
    groundLeftTopLeg.addBox(-2.5F, 0F, -2.5F, 5, 7, 5);
    groundLeftTopLeg.setRotationPoint(6F, 4F, 0F);
    groundLeftTopLeg.setTextureSize(512, 256);
//    groundLeftTopLeg.mirror = true;
    setRotation(groundLeftTopLeg, 0F, 0F, 0F);
    groundLeftLeg = new ModelRenderer(this, 82, 0);
    groundLeftLeg.addBox(-1.5F, 7F, -1.5F, 3, 13, 3);
    groundLeftLeg.setRotationPoint(6F, 4F, 0F);
    groundLeftLeg.setTextureSize(512, 256);
//    groundLeftLeg.mirror = true;
    setRotation(groundLeftLeg, 0F, 0F, 0F);
    groundLeftFoot = new ModelRenderer(this, 111, 0);
    groundLeftFoot.addBox(-5.5F, 20F, -9.5F, 11, 0, 11);
    groundLeftFoot.setRotationPoint(6F, 4F, 0F);
    groundLeftFoot.setTextureSize(512, 256);
//    groundLeftFoot.mirror = true;
    setRotation(groundLeftFoot, 0F, 0F, 0F);
    convertToChild(groundLeftTopLeg, groundLeftLeg);
    convertToChild(groundLeftTopLeg, groundLeftFoot);	      

    groundRightTopLeg = new ModelRenderer(this, 99, 0);
    groundRightTopLeg.addBox(-2.5F, 0F, -2.5F, 5, 7, 5);
    groundRightTopLeg.setRotationPoint(-6F, 4F, 0F);
    groundRightTopLeg.setTextureSize(512, 256);
//    groundRightTopLeg.mirror = true;
    setRotation(groundRightTopLeg, 0F, 0F, 0F);
    groundRightLeg = new ModelRenderer(this, 82, 0);
    groundRightLeg.addBox(-1.5F, 7F, -1.5F, 3, 13, 3);
    groundRightLeg.setRotationPoint(-6F, 4F, 0F);
    groundRightLeg.setTextureSize(512, 256);
//    groundRightLeg.mirror = true;
    setRotation(groundRightLeg, 0F, 0F, 0F);
    groundRightFoot = new ModelRenderer(this, 111, 0);
    groundRightFoot.addBox(-5.5F, 20F, -9.5F, 11, 0, 11);
    groundRightFoot.setRotationPoint(-6F, 4F, 0F);
    groundRightFoot.setTextureSize(512, 256);
//    groundRightFoot.mirror = true;
    setRotation(groundRightFoot, 0F, 0F, 0F);
    convertToChild(groundRightTopLeg, groundRightLeg);
    convertToChild(groundRightTopLeg, groundRightFoot);	      

    groundTailFeathers = new ModelRenderer(this, 0, 57);
    groundTailFeathers.addBox(-10F, 0F, 10F, 20, 15, 0);
    groundTailFeathers.setRotationPoint(0F, 5F, 0F);
    groundTailFeathers.setTextureSize(512, 256);
//    groundTailFeathers.mirror = true;
    setRotation(groundTailFeathers, 0.1745329F, 0F, 0F);

    // Flying
    flyingBeakTop = new ModelRenderer(this, 357, 199);
    flyingBeakTop.addBox(-2.5F, 0F, -22F, 5, 1, 5);
    flyingBeakTop.setRotationPoint(0F, -4F, -30F);
    flyingBeakTop.setTextureSize(512, 256);
//    flyingBeakTop.mirror = true;
    setRotation(flyingBeakTop, 0F, 0F, 0F);
    flyingBeakBottom = new ModelRenderer(this, 331, 199);
    flyingBeakBottom.addBox(-2.5F, 1F, -22F, 5, 1, 5);
    flyingBeakBottom.setRotationPoint(0F, -4F, -30F);
    flyingBeakBottom.setTextureSize(512, 256);
//    flyingBeakBottom.mirror = true;
    setRotation(flyingBeakBottom, 0F, 0F, 0F);
    flyingHead = new ModelRenderer(this, 400, 158);
    flyingHead.addBox(-8.5F, -10F, -17F, 17, 17, 17);
    flyingHead.setRotationPoint(0F, -4F, -30F);
    flyingHead.setTextureSize(512, 256);
//    flyingHead.mirror = true;
    setRotation(flyingHead, 0F, 0F, 0F);
    convertToChild(flyingHead, flyingBeakTop);
    convertToChild(flyingHead, flyingBeakBottom);	      

    flyingBody = new ModelRenderer(this, 392, 196);
    flyingBody.addBox(-10F, -20F, -30F, 20, 20, 40);
    flyingBody.setRotationPoint(0F, 5F, 0F);
    flyingBody.setTextureSize(512, 256);
//    flyingBody.mirror = true;
    setRotation(flyingBody, 0F, 0F, 0F);

    flyingLeftWing = new ModelRenderer(this, 0, 131);
    flyingLeftWing.addBox(10F, 0F, -20F, 80, 0, 60);
    flyingLeftWing.setRotationPoint(0F, -15F, -20F);
    flyingLeftWing.setTextureSize(512, 256);
    flyingLeftWing.mirror = false;
    setRotation(flyingLeftWing, 0F, 0F, 0F);
    
    flyingRightWing = new ModelRenderer(this, 0, 194);
    flyingRightWing.addBox(-90F, 0F, -20F, 80, 0, 60);
    flyingRightWing.setRotationPoint(0F, -15F, -20F);
    flyingRightWing.setTextureSize(512, 256);
//    flyingRightWing.mirror = true;
    setRotation(flyingRightWing, 0F, 0F, 0F);

    flyingLeftTopLeg = new ModelRenderer(this, 400, 200);
    flyingLeftTopLeg.addBox(-2.5F, 0F, -2.5F, 5, 7, 5);
    flyingLeftTopLeg.setRotationPoint(6F, 4F, 0F);
    flyingLeftTopLeg.setTextureSize(512, 256);
//    flyingLeftTopLeg.mirror = true;
    setRotation(flyingLeftTopLeg, 0.9815153F, 0F, 0F);
    flyingLeftLeg = new ModelRenderer(this, 380, 200);
    flyingLeftLeg.addBox(-1.5F, 7F, -1.5F, 3, 13, 3);
    flyingLeftLeg.setRotationPoint(6F, 4F, 0F);
    flyingLeftLeg.setTextureSize(512, 256);
//    flyingLeftLeg.mirror = true;
    setRotation(flyingLeftLeg, 0.9815153F, 0F, 0F);
    flyingLeftFoot = new ModelRenderer(this, 340, 210);
    flyingLeftFoot.addBox(-5.5F, 20F, -9.5F, 11, 0, 11);
    flyingLeftFoot.setRotationPoint(6F, 4F, 0F);
    flyingLeftFoot.setTextureSize(512, 256);
//    flyingLeftFoot.mirror = true;
    setRotation(flyingLeftFoot, 0.9815153F, 0F, 0F);
    convertToChild(flyingLeftTopLeg, flyingLeftLeg);
    convertToChild(flyingLeftTopLeg, flyingLeftFoot);	      

    flyingRightTopLeg = new ModelRenderer(this, 400, 200);
    flyingRightTopLeg.addBox(-2.5F, 0F, -2.5F, 5, 7, 5);
    flyingRightTopLeg.setRotationPoint(-6F, 4F, 0F);
    flyingRightTopLeg.setTextureSize(512, 256);
//    flyingRightTopLeg.mirror = true;
    setRotation(flyingRightTopLeg, 0.9815153F, 0F, 0F);
    flyingRightLeg = new ModelRenderer(this, 380, 200);
    flyingRightLeg.addBox(-1.5F, 7F, -1.5F, 3, 13, 3);
    flyingRightLeg.setRotationPoint(-6F, 4F, 0F);
    flyingRightLeg.setTextureSize(512, 256);
    flyingRightLeg.mirror = true;
    setRotation(flyingRightLeg, 0.9815153F, 0F, 0F);
    flyingRightFoot = new ModelRenderer(this, 340, 210);
    flyingRightFoot.addBox(-5.5F, 20F, -9.5F, 11, 0, 11);
    flyingRightFoot.setRotationPoint(-6F, 4F, 0F);
    flyingRightFoot.setTextureSize(512, 256);
//    flyingRightFoot.mirror = true;
    setRotation(flyingRightFoot, 0.9815153F, 0F, 0F);
    convertToChild(flyingRightTopLeg, flyingRightLeg);
    convertToChild(flyingRightTopLeg, flyingRightFoot);	      
    
    flyingTail = new ModelRenderer(this, 250, 222);
    flyingTail.addBox(-20F, 0F, 0F, 40, 0, 30);
    flyingTail.setRotationPoint(0F, -15F, 10F);
    flyingTail.setTextureSize(512, 256);
//    flyingTail.mirror = true;
    setRotation(flyingTail, -0.4363323F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
	    super.render(entity, f, f1, f2, f3, f4, f5);
	    if(entity instanceof EntityPlayer) {
	        dragonPlayer = DragonPlayer.get((EntityPlayer)entity);
	    }
	    else
	    	dragonPlayer = null;
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    
    // As a mob, always flying
    if (dragonPlayer == null) {
    	flyingHead.render(f5);
    	flyingBody.render(f5);
    	flyingRightWing.render(f5);
    	flyingLeftWing.render(f5);
    	flyingLeftTopLeg.render(f5);
    	flyingRightTopLeg.render(f5);
    	flyingTail.render(f5);
    }
    // Player flying
    else if (((EntityPlayer)entity).capabilities.isFlying) {       
    	flyingHead.render(f5);
    	flyingBody.render(f5);
    	flyingLeftWing.render(f5);
    	flyingRightWing.render(f5);
    	flyingLeftTopLeg.render(f5);
    	flyingRightTopLeg.render(f5);
    	flyingTail.render(f5);
    }
    // On the ground
    else {
    	groundHead.render(f5);
    	groundBody.render(f5);
    	groundLeftWing.render(f5);
    	groundRightWing.render(f5);
    	groundLeftTopLeg.render(f5);
    	groundRightTopLeg.render(f5);
    	groundTailFeathers.render(f5);    	
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
		this.flyingRightTopLeg.rotateAngleX = MathHelper.cos(f * LEG_SPEED) * MAXIMUM_LEG_ROTATION * f1;
		this.flyingLeftTopLeg.rotateAngleX = MathHelper.cos(f * LEG_SPEED + (float)Math.PI) * MAXIMUM_LEG_ROTATION * f1;
		
		this.flyingLeftWing.rotateAngleZ = MathHelper.cos(f * WING_SPEED + (float)Math.PI) * MAXIMUM_WING_ROTATION * f1;
		this.flyingRightWing.rotateAngleZ = MathHelper.cos(f * WING_SPEED) * MAXIMUM_WING_ROTATION * f1;
    }
    // Player flying
    else if (((EntityPlayer)entity).capabilities.isFlying) {
    	MAXIMUM_LEG_ROTATION = 1.4f / 8;
		this.flyingRightTopLeg.rotateAngleX = MathHelper.cos(f * LEG_SPEED) * MAXIMUM_LEG_ROTATION * f1;
		this.flyingLeftTopLeg.rotateAngleX = MathHelper.cos(f * LEG_SPEED + (float)Math.PI) * MAXIMUM_LEG_ROTATION * f1;
		
		this.flyingLeftWing.rotateAngleZ = MathHelper.cos(f * WING_SPEED + (float)Math.PI) * MAXIMUM_WING_ROTATION * f1;
		this.flyingRightWing.rotateAngleZ = MathHelper.cos(f * WING_SPEED) * MAXIMUM_WING_ROTATION * f1;
    }		
    // Player not flying
    else {
		this.groundRightTopLeg.rotateAngleX = MathHelper.cos(f * LEG_SPEED) * MAXIMUM_LEG_ROTATION * f1;
		this.groundLeftTopLeg.rotateAngleX = MathHelper.cos(f * LEG_SPEED + (float)Math.PI) * MAXIMUM_LEG_ROTATION * f1;		
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
