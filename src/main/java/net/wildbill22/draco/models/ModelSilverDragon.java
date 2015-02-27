package net.wildbill22.draco.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

/** 
 * @author WILLIAM
 *
 */
public class ModelSilverDragon extends ModelBase{
    ModelRenderer head, topMouth, bottomMouth;
    ModelRenderer neckSpikes, neck;
    ModelRenderer rWing1, rWing2, rWing3, rWing4;
    ModelRenderer lWing1, lWing2,lWing3, lWing4;
    ModelRenderer tail1, tail2, tail3, tail4, tailWing;
    ModelRenderer body;
    ModelRenderer leftLeg1, leftLeg2, leftFoot;
    ModelRenderer backLeftLeg1, backLeftLeg2, backLeftFoot;
    ModelRenderer rightLeg1, rightLeg2, rightFoot;
    ModelRenderer backRightLeg1, backRightLeg2, backRightFoot;
    
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		// Head
	    this.head.rotateAngleX = f4 / (180F / (float)Math.PI);
	    this.head.rotateAngleY = f3 / (180F / (float)Math.PI);
	    // TODO: Will want mouth to open sometimes
	    this.topMouth.rotateAngleX = f4 / (180F / (float)Math.PI);
		this.topMouth.rotateAngleY = f3 / (180F / (float)Math.PI);
	    this.bottomMouth.rotateAngleX = f4 / (180F / (float)Math.PI);
		this.bottomMouth.rotateAngleY = f3 / (180F / (float)Math.PI);
		
		// Neck does what?
	    this.neckSpikes.rotateAngleX = f4 / (90F / (float)Math.PI);
	    this.neckSpikes.rotateAngleY = f3 / (90F / (float)Math.PI);
	    this.neck.rotateAngleX = f4 / (90F / (float)Math.PI);
	    this.neck.rotateAngleY = f3 / (90F / (float)Math.PI);

		// Left Wing
		this.lWing1.rotateAngleZ = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 3.0F * f1;
		this.lWing2.rotateAngleZ = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 3.0F * f1;
		this.lWing3.rotateAngleZ = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 3.0F * f1;
		this.lWing4.rotateAngleZ = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 3.0F * f1;
		
		// Right Wing
		this.rWing1.rotateAngleZ = MathHelper.cos(f * 0.6662F) * 3.0F * f1;
		this.rWing2.rotateAngleZ = MathHelper.cos(f * 0.6662F) * 3.0F * f1;
		this.rWing3.rotateAngleZ = MathHelper.cos(f * 0.6662F) * 3.0F * f1;
		this.rWing4.rotateAngleZ = MathHelper.cos(f * 0.6662F) * 3.0F * f1;
		
		// Left Leg
		this.leftLeg1.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
		this.leftLeg2.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
		this.leftFoot.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;

		// Right Leg
		this.rightLeg1.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 1.4F * f1;
		this.rightLeg1.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 1.4F * f1;
		this.rightFoot.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 1.4F * f1;
		
		// Back left leg
		this.backLeftLeg1.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 1.4F * f1;
		this.backLeftLeg2.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 1.4F * f1;
		this.backLeftFoot.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 1.4F * f1;

		// Back right leg
		this.backRightLeg1.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
		this.backRightLeg2.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
		this.backRightFoot.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
		
		// Tail does what? Maybe swish side to side?
	    this.tail1.rotateAngleY = f1 / (90F / (float)Math.PI);
	    this.tail2.rotateAngleY = f1 / (90F / (float)Math.PI);
	    this.tail3.rotateAngleY = f1 / (90F / (float)Math.PI);
	    this.tail4.rotateAngleY = f1 / (90F / (float)Math.PI);
	    this.tailWing.rotateAngleY = f1 / (90F / (float)Math.PI);
    }
    
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        leftLeg1.render(f5);
        leftLeg2.render(f5);
        leftFoot.render(f5);
        backLeftLeg1.render(f5);
        backLeftLeg2.render(f5);
        backLeftFoot.render(f5);
        rightLeg1.render(f5);
        rightLeg2.render(f5);
        rightFoot.render(f5);
        backRightLeg1.render(f5);
        backRightLeg2.render(f5);
        backRightFoot.render(f5);
        topMouth.render(f5);
        bottomMouth.render(f5);
        neck.render(f5);
        head.render(f5);
        rWing1.render(f5);
        rWing2.render(f5);
        rWing3.render(f5);
        rWing4.render(f5);
        lWing1.render(f5);
        lWing2.render(f5);
        lWing3.render(f5);
        lWing4.render(f5);
        neckSpikes.render(f5);
        tail1.render(f5);
        tail2.render(f5);
        tail3.render(f5);
        tail4.render(f5);
        tailWing.render(f5);
        body.render(f5);
      }
      
    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
	    model.rotateAngleX = x;
	    model.rotateAngleY = y;
	    model.rotateAngleZ = z;
    }
    
  public ModelSilverDragon()
  {
    textureWidth = 512;
    textureHeight = 256;
    
      leftLeg1 = new ModelRenderer(this, 140, 0);
      leftLeg1.addBox(-2.5F, 0F, -1.5F, 5, 11, 3);
      leftLeg1.setRotationPoint(1.5F, 5.5F, -4.8F);
      leftLeg1.setTextureSize(512, 256);
      leftLeg1.mirror = true;
      setRotation(leftLeg1, -0.6981317F, 0F, 0F);
      leftLeg2 = new ModelRenderer(this, 160, 0);
      leftLeg2.addBox(-2.5F, 0F, -1.5F, 5, 10, 3);
      leftLeg2.setRotationPoint(1.5F, 13F, -11.5F);
      leftLeg2.setTextureSize(512, 256);
      leftLeg2.mirror = true;
      setRotation(leftLeg2, 0F, 0F, 0F);
      leftFoot = new ModelRenderer(this, 180, 0);
      leftFoot.addBox(-2.5F, 0F, -9.5F, 5, 2, 10);
      leftFoot.setRotationPoint(1.5F, 22F, -10.5F);
      leftFoot.setTextureSize(512, 256);
      leftFoot.mirror = true;
      setRotation(leftFoot, 0F, 0F, 0F);
      backLeftLeg1 = new ModelRenderer(this, 140, 20);
      backLeftLeg1.addBox(-2.5F, 0F, -2.5F, 5, 15, 5);
      backLeftLeg1.setRotationPoint(1.5F, 3.1F, 28.8F);
      backLeftLeg1.setTextureSize(512, 256);
      backLeftLeg1.mirror = true;
      setRotation(backLeftLeg1, -0.6981317F, 0F, 0F);
      backLeftLeg2 = new ModelRenderer(this, 170, 20);
      backLeftLeg2.addBox(-2.5F, 0F, -2.5F, 5, 10, 5);
      backLeftLeg2.setRotationPoint(1.5F, 13F, 19.8F);
      backLeftLeg2.setTextureSize(512, 256);
      backLeftLeg2.mirror = true;
      setRotation(backLeftLeg2, 0F, 0F, 0F);
      backLeftFoot = new ModelRenderer(this, 180, 0);
      backLeftFoot.addBox(-2.5F, 0F, -9.5F, 5, 2, 10);
      backLeftFoot.setRotationPoint(1.5F, 22F, 21.8F);
      backLeftFoot.setTextureSize(512, 256);
      backLeftFoot.mirror = true;
      setRotation(backLeftFoot, 0F, 0F, 0F);
      rightLeg1 = new ModelRenderer(this, 140, 0);
      rightLeg1.addBox(-2.5F, 0F, -1.5F, 5, 11, 3);
      rightLeg1.setRotationPoint(-7.5F, 5.5F, -4.8F);
      rightLeg1.setTextureSize(512, 256);
      rightLeg1.mirror = true;
      setRotation(rightLeg1, -0.6981317F, 0F, 0F);
      rightLeg2 = new ModelRenderer(this, 160, 0);
      rightLeg2.addBox(-2.5F, 0F, -1.5F, 5, 10, 3);
      rightLeg2.setRotationPoint(-7.5F, 13F, -11.5F);
      rightLeg2.setTextureSize(512, 256);
      rightLeg2.mirror = true;
      setRotation(rightLeg2, 0F, 0F, 0F);
      rightFoot = new ModelRenderer(this, 180, 0);
      rightFoot.addBox(-2.5F, 0F, -9.5F, 5, 2, 10);
      rightFoot.setRotationPoint(-7.5F, 22F, -10.5F);
      rightFoot.setTextureSize(512, 256);
      rightFoot.mirror = true;
      setRotation(rightFoot, 0F, 0F, 0F);
      backRightLeg1 = new ModelRenderer(this, 140, 20);
      backRightLeg1.addBox(-2.5F, 0F, -2.5F, 5, 15, 5);
      backRightLeg1.setRotationPoint(-7.5F, 3.1F, 28.8F);
      backRightLeg1.setTextureSize(512, 256);
      backRightLeg1.mirror = true;
      setRotation(backRightLeg1, -0.6981317F, 0F, 0F);
      backRightLeg2 = new ModelRenderer(this, 170, 20);
      backRightLeg2.addBox(-2.5F, 0F, -2.5F, 5, 10, 5);
      backRightLeg2.setRotationPoint(-7.5F, 13F, 19.8F);
      backRightLeg2.setTextureSize(512, 256);
      backRightLeg2.mirror = true;
      setRotation(backRightLeg2, 0F, 0F, 0F);
      backRightFoot = new ModelRenderer(this, 180, 0);
      backRightFoot.addBox(-2.5F, 0F, -9.5F, 5, 2, 10);
      backRightFoot.setRotationPoint(-7.5F, 22F, 21.8F);
      backRightFoot.setTextureSize(512, 256);
      backRightFoot.mirror = true;
      setRotation(backRightFoot, 0F, 0F, 0F);
      topMouth = new ModelRenderer(this, 0, 60);
      topMouth.addBox(-4F, -2F, -8F, 8, 4, 8);
      topMouth.setRotationPoint(-3F, -10F, -39F);
      topMouth.setTextureSize(512, 256);
      topMouth.mirror = true;
      setRotation(topMouth, 0F, 0F, 0F);
      bottomMouth = new ModelRenderer(this, 0, 75);
      bottomMouth.addBox(-4F, -0.5F, -8F, 8, 1, 8);
      bottomMouth.setRotationPoint(-3F, -7.5F, -39F);
      bottomMouth.setTextureSize(512, 256);
      bottomMouth.mirror = true;
      setRotation(bottomMouth, 0F, 0F, 0F);
      neck = new ModelRenderer(this, 40, 60);
      neck.addBox(-3F, -3F, -25F, 6, 6, 25);
      neck.setRotationPoint(-3F, 2.5F, -12F);
      neck.setTextureSize(512, 256);
      neck.mirror = true;
      setRotation(neck, -0.6981317F, 0F, 0F);
      head = new ModelRenderer(this, 125, 45);
      head.addBox(-5F, -5F, -10F, 10, 10, 10);
      head.setRotationPoint(-3F, -12F, -29F);
      head.setTextureSize(512, 256);
      head.mirror = true;
      setRotation(head, 0F, 0F, 0F);
      rWing1 = new ModelRenderer(this, 0, 100);
      rWing1.addBox(-30F, -2F, -2F, 30, 4, 4);
      rWing1.setRotationPoint(-9F, 0F, -11F);
      rWing1.setTextureSize(512, 256);
      rWing1.mirror = true;
      setRotation(rWing1, 0F, 0F, 0.4363323F);
      rWing2 = new ModelRenderer(this, 0, 110);
      rWing2.addBox(-25F, -2F, -2F, 25, 4, 4);
      rWing2.setRotationPoint(-35.4F, -12.4F, -11F);
      rWing2.setTextureSize(512, 256);
      rWing2.mirror = true;
      setRotation(rWing2, 0F, 0F, 0F);
      rWing3 = new ModelRenderer(this, 80, 100);
      rWing3.addBox(-30F, -2F, -2F, 30, 0, 30);
      rWing3.setRotationPoint(-9F, 0F, -7F);
      rWing3.setTextureSize(512, 256);
      rWing3.mirror = true;
      setRotation(rWing3, 0F, 0F, 0.4363323F);
      rWing4 = new ModelRenderer(this, 80, 140);
      rWing4.addBox(-25F, -2F, -2F, 25, 0, 30);
      rWing4.setRotationPoint(-35.4F, -12.4F, -7F);
      rWing4.setTextureSize(512, 256);
      rWing4.mirror = true;
      setRotation(rWing4, 0F, 0F, 0F);
      lWing1 = new ModelRenderer(this, 0, 100);
      lWing1.addBox(0F, -2F, -2F, 30, 4, 4);
      lWing1.setRotationPoint(3F, 0F, -11F);
      lWing1.setTextureSize(512, 256);
      lWing1.mirror = true;
      setRotation(lWing1, 0F, 0F, -0.4363323F);
      lWing2 = new ModelRenderer(this, 0, 110);
      lWing2.addBox(0F, -2F, -2F, 25, 4, 4);
      lWing2.setRotationPoint(29.4F, -12.4F, -11F);
      lWing2.setTextureSize(512, 256);
      lWing2.mirror = true;
      setRotation(lWing2, 0F, 0F, 0F);
      lWing3 = new ModelRenderer(this, 210, 100);
      lWing3.addBox(0F, -2F, -2F, 30, 0, 30);
      lWing3.setRotationPoint(3F, 0F, -7F);
      lWing3.setTextureSize(512, 256);
      lWing3.mirror = true;
      setRotation(lWing3, 0F, 0F, -0.4363323F);
      lWing4 = new ModelRenderer(this, 210, 140);
      lWing4.addBox(0F, -2F, -2F, 25, 0, 30);
      lWing4.setRotationPoint(29.4F, -12.4F, -7F);
      lWing4.setTextureSize(512, 256);
      lWing4.mirror = true;
      setRotation(lWing4, 0F, 0F, 0F);
      neckSpikes = new ModelRenderer(this, 0, 120);
      neckSpikes.addBox(-15F, -15F, -2F, 30, 30, 0);
      neckSpikes.setRotationPoint(-3F, -12F, -29F);
      neckSpikes.setTextureSize(512, 256);
      neckSpikes.mirror = true;
      setRotation(neckSpikes, -0.4363323F, 0F, 0F);
      tail1 = new ModelRenderer(this, 220, 0);
      tail1.addBox(-6F, -4F, 0F, 12, 8, 10);
      tail1.setRotationPoint(-3F, 2F, 30F);
      tail1.setTextureSize(512, 256);
      tail1.mirror = true;
      setRotation(tail1, 0F, 0F, 0F);
      tail2 = new ModelRenderer(this, 270, 0);
      tail2.addBox(-5F, -3F, 0F, 10, 6, 10);
      tail2.setRotationPoint(-3F, 2F, 40F);
      tail2.setTextureSize(512, 256);
      tail2.mirror = true;
      setRotation(tail2, 0F, 0F, 0F);
      tail3 = new ModelRenderer(this, 240, 20);
      tail3.addBox(-4F, -2F, 0F, 8, 4, 10);
      tail3.setRotationPoint(-3F, 2F, 50F);
      tail3.setTextureSize(512, 256);
      tail3.mirror = true;
      setRotation(tail3, 0F, 0F, 0F);
      tail4 = new ModelRenderer(this, 280, 20);
      tail4.addBox(-3F, -1F, 0F, 6, 2, 10);
      tail4.setRotationPoint(-3F, 2F, 60F);
      tail4.setTextureSize(512, 256);
      tail4.mirror = true;
      setRotation(tail4, 0F, 0F, 0F);
      tailWing = new ModelRenderer(this, 320, 0);
      tailWing.addBox(-9F, -1F, 0F, 18, 0, 10);
      tailWing.setRotationPoint(-3F, 2F, 60F);
      tailWing.setTextureSize(512, 256);
      tailWing.mirror = true;
      setRotation(tailWing, 0F, 0F, 0F);
      body = new ModelRenderer(this, 0, 0);
      body.addBox(0F, 0F, 0F, 14, 10, 45);
      body.setRotationPoint(-10F, -3F, -14F);
      body.setTextureSize(512, 256);
      body.mirror = true;
      setRotation(body, 0F, 0F, 0F);
  	}
  }
