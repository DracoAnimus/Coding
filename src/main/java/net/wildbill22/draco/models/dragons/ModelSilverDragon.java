package net.wildbill22.draco.models.dragons;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.wildbill22.draco.entities.player.DragonPlayer;

/** 
 * @author WILLIAM
 *
 */
public class ModelSilverDragon extends ModelBase{
    ModelRenderer neck;
    ModelRenderer neckSpikes;
    ModelRenderer head, mouth;
//    ModelRenderer topMouth, bottomMouth;
    ModelRenderer rWing1, rWing2, rWing3, rWing4;
    ModelRenderer lWing1, lWing2,lWing3, lWing4;
    ModelRenderer tail1, tail2, tail3, tail4, tailWing;
    ModelRenderer body;
    ModelRenderer leftLeg1, leftLeg2, leftFoot;
    ModelRenderer backLeftLeg1, backLeftLeg2, backLeftFoot;
    ModelRenderer rightLeg1, rightLeg2, rightFoot;
    ModelRenderer backRightLeg1, backRightLeg2, backRightFoot;
    
    final float WING_SPEED = 0.6662f;
    final float MAXIMUM_WING_ROTATION = 1.4f; // 4-29-15: was 1.5
    DragonPlayer dragonPlayer;
    
    // f = time, f1 = how far to move legs
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		
		// Neck does what?
//	    this.neck.rotateAngleX = f4 / (180F / (float)Math.PI);
//	    this.neck.rotateAngleY = f3 / (180F / (float)Math.PI);

		// Head
	    this.head.rotateAngleX = f4 / (180F / (float)Math.PI);
	    this.head.rotateAngleY = f3 / (180F / (float)Math.PI);

	    // TODO: Will want mouth to open sometimes
//	    this.topMouth.rotateAngleX = f4 / (180F / (float)Math.PI);
//		this.topMouth.rotateAngleY = f3 / (180F / (float)Math.PI);
//	    this.bottomMouth.rotateAngleX = f4 / (180F / (float)Math.PI);
//		this.bottomMouth.rotateAngleY = f3 / (180F / (float)Math.PI);
		
		// Left Wing
		this.lWing1.rotateAngleZ = MathHelper.cos(f * WING_SPEED + (float)Math.PI) * MAXIMUM_WING_ROTATION * f1;
		
		// Right Wing
		this.rWing1.rotateAngleZ = MathHelper.cos(f * WING_SPEED) * MAXIMUM_WING_ROTATION * f1;
		
		// Left Leg
		this.leftLeg1.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;

		// Right Leg
		this.rightLeg1.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 1.4F * f1;
		
		// Back left leg
		this.backLeftLeg1.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 1.4F * f1;

		// Back right leg
		this.backRightLeg1.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
		
		// Tail does what? Maybe swish side to side?
	    this.tail1.rotateAngleY = f1 / (90F / (float)Math.PI);
    }
    
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        if(entity instanceof EntityPlayer) {
            dragonPlayer = DragonPlayer.get((EntityPlayer)entity);
        }
        else
        	dragonPlayer = null;

        // For when shown in inventory
//        if (entity.)
//        	GL11.glPushMatrix();
//          GL11.glScalef(0.5, 0.5, 0.5);
        // or this
//        float scaleFactor = 2.5F;
//        GL11.glPushMatrix();
//        GL11.glTranslatef(0F, 1.5F-1.5F*scaleFactor, 0F); 
//        GL11.glScalef(scaleFactor, scaleFactor, scaleFactor);
        
        neck.render(f5);
        head.render(f5);        
        if (dragonPlayer == null) {
	        rWing1.render(f5);
	        lWing1.render(f5);
        } 
        else if (((EntityPlayer)entity).capabilities.isFlying) {
	        rWing1.render(f5);
	        lWing1.render(f5);
        } 
        else {
            leftLeg1.render(f5);  // Front Left Leg
            backLeftLeg1.render(f5); // Back Left Leg
            rightLeg1.render(f5); // Front Right Leg
            backRightLeg1.render(f5); // Back right leg
        }       
        tail1.render(f5);
        body.render(f5);
        // For when shown in inventory
//      if (entity.)
//      	GL11.glPopMatrix();
      }
      
    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
	    model.rotateAngleX = x;
	    model.rotateAngleY = y;
	    model.rotateAngleZ = z;
    }
    
    // From version 4.0
    public ModelSilverDragon()
    {
    	dragonPlayer = null;
    	
    	textureWidth = 512;
    	textureHeight = 256;
    
    	// Neck
		neck = new ModelRenderer(this, 40, 60);
		neck.addBox(-3F, -3F, -22F, 6, 6, 25);
		neck.setRotationPoint(0F, 0F, -15F);
		neck.setTextureSize(512, 256);
		neck.mirror = true;
		setRotation(neck, -0.6981317F, 0F, 0F);
			
		// Head & neck spikes have same rotation point
		head = new ModelRenderer(this, 125, 45);
		head.addBox(-5F, -5F, -10F, 10, 10, 10);
		head.setRotationPoint(0F, -13F, -30F);
		head.setTextureSize(512, 256);
		head.mirror = true;
		setRotation(head, 0F, 0F, 0F);
//		convertToChild(neck, head);

		// Spikes (good)
        neckSpikes = new ModelRenderer(this, 0, 120);
		neckSpikes.addBox(-15F, -15F, -2F, 30, 30, 0);
        neckSpikes.setRotationPoint(0F, -13F, -30F);
        setRotation(neckSpikes, -0.4363323F, 0F, 0F);
		convertToChild(head, neckSpikes);

		// Mouth
		// Mouth won't open for the first phase
		mouth = new ModelRenderer(this, 0, 60);
		mouth.addBox(-4F, 0F, -18F, 8, 4, 8);   // top mouth
		mouth.addBox(-4F, 4F, -18F, 8, 1, 8); // bottom mouth
		mouth.setRotationPoint(0F, -13F, -30F);
	    mouth.setTextureSize(512, 256);
	    mouth.mirror = true;
		convertToChild(head, mouth);
	
		// Right Wing inner (1, 3) and outer (2, 4) (good)
		rWing1 = new ModelRenderer(this, 0, 100);
		rWing1.addBox(-30F, -2F, -2F, 30, 4, 4);
		rWing1.setRotationPoint(-6F, 0F, -11F);
		rWing1.setTextureSize(512, 256);
		rWing1.mirror = true;
		setRotation(rWing1, 0F, 0F, 0.4363323F);
		rWing3 = new ModelRenderer(this, 80, 100);
		rWing3.addBox(-30F, -2F, 2F, 30, 0, 30);
		rWing3.setRotationPoint(-6F, 0F, -11F);
		rWing3.setTextureSize(512, 256);
		rWing3.mirror = true;
		setRotation(rWing3, 0F, 0F, 0.4363323F);
		convertToChild(rWing1, rWing3);
		rWing2 = new ModelRenderer(this, 0, 110);
		rWing2.addBox(-51.4F, -14.4F, -2F, 25, 4, 4);
		rWing2.setRotationPoint(-6F, 0F, -11F);
		rWing2.setTextureSize(512, 256);
		rWing2.mirror = true;
		setRotation(rWing2, 0F, 0F, 0F);
		convertToChild(rWing1, rWing2);
		rWing4 = new ModelRenderer(this, 80, 140);
		rWing4.addBox(-51.4F, -14.4F, 2F, 25, 0, 30);
		rWing4.setRotationPoint(-6F, 0F, -11);
		rWing4.setTextureSize(512, 256);
		rWing4.mirror = true;
		setRotation(rWing4, 0F, 0F, 0F);
		convertToChild(rWing1, rWing4);
		
		// Left Wing inner (1, 3) and outer (2, 4) (good)
		lWing1 = new ModelRenderer(this, 0, 100);
		lWing1.addBox(0F, -2F, -2F, 30, 4, 4);
		lWing1.setRotationPoint(6F, 0F, -11F);
		lWing1.setTextureSize(512, 256);
		lWing1.mirror = true;
		setRotation(lWing1, 0F, 0F, -0.4363323F);
		lWing3 = new ModelRenderer(this, 210, 100);
		lWing3.addBox(0F, -2F, 2F, 30, 0, 30);
		lWing3.setRotationPoint(6F, 0F, -11F);
		lWing3.setTextureSize(512, 256);
		lWing3.mirror = true;
		setRotation(lWing3, 0F, 0F, -0.4363323F);
		convertToChild(lWing1, lWing3);
		lWing2 = new ModelRenderer(this, 0, 110);
		lWing2.addBox(26.4F, -14.4F, -2F, 25, 4, 4);
		lWing2.setRotationPoint(6F, 0F, -11F);
		lWing2.setTextureSize(512, 256);
		lWing2.mirror = true;
		setRotation(lWing2, 0F, 0F, 0F);
		convertToChild(lWing1, lWing2);
		lWing4 = new ModelRenderer(this, 210, 140);
		lWing4.addBox(26.4F, -14.4F, 2F, 25, 0, 30);
		lWing4.setRotationPoint(6F, 0F, -11F);
		lWing4.setTextureSize(512, 256);
		lWing4.mirror = true;
		setRotation(lWing4, 0F, 0F, 0F);
		convertToChild(lWing1, lWing4);
		
		// Front left leg (good)
    	leftLeg1 = new ModelRenderer(this, 140, 0);
		leftLeg1.addBox(-2.5F, 0F, -1.5F, 5, 11, 3);
		leftLeg1.setRotationPoint(4.5F, 5.0F, -10.8F);
		leftLeg1.setTextureSize(512, 256);
		leftLeg1.mirror = true;
		setRotation(leftLeg1, -0.6981317F, 0F, 0F);
		leftLeg2 = new ModelRenderer(this, 160, 0);
		leftLeg2.addBox(-2.5F, 7F, -8F, 5, 10, 3);
		leftLeg2.setRotationPoint(4.5F, 5F, -10.8F);
		leftLeg2.setTextureSize(512, 256);
		leftLeg2.mirror = true;
		setRotation(leftLeg2, 0F, 0F, 0F);
		leftFoot = new ModelRenderer(this, 180, 0);
		leftFoot.addBox(-2.5F, 17F, -15F, 5, 2, 10);
		leftFoot.setRotationPoint(4.5F, 5F, -10.8F);
		leftFoot.setTextureSize(512, 256);
		leftFoot.mirror = true;
		setRotation(leftFoot, 0F, 0F, 0F);
		convertToChild(leftLeg2, leftFoot);
		convertToChild(leftLeg1, leftLeg2);
		
		// Back left leg (good)
		backLeftLeg1 = new ModelRenderer(this, 140, 20);
		backLeftLeg1.addBox(-2.5F, 0F, -2.5F, 5, 15, 5);
		backLeftLeg1.setRotationPoint(4.5F, 3.1F, 24.8F);
		backLeftLeg1.setTextureSize(512, 256);
		backLeftLeg1.mirror = true;
		setRotation(backLeftLeg1, -0.6981317F, 0F, 0F);
		backLeftLeg2 = new ModelRenderer(this, 170, 20);
		backLeftLeg2.addBox(-2.5F, 9F, -11.5F, 5, 10, 5);
		backLeftLeg2.setRotationPoint(4.5F, 3.1F, 24.8F);
		backLeftLeg2.setTextureSize(512, 256);
		backLeftLeg2.mirror = true;
		setRotation(backLeftLeg2, 0F, 0F, 0F);
		backLeftFoot = new ModelRenderer(this, 180, 0);
		backLeftFoot.addBox(-2.5F, 19F, -16.5F, 5, 2, 10);
		backLeftFoot.setRotationPoint(4.5F, 3.1F, 24.8F);
		backLeftFoot.setTextureSize(512, 256);
		backLeftFoot.mirror = true;
		setRotation(backLeftFoot, 0F, 0F, 0F);
		convertToChild(backLeftLeg2, backLeftFoot);
		convertToChild(backLeftLeg1, backLeftLeg2);
		
		// Front right leg (good)
		rightLeg1 = new ModelRenderer(this, 140, 0);
		rightLeg1.addBox(-2.5F, 0F, -1.5F, 5, 11, 3);
		rightLeg1.setRotationPoint(-4.5F, 5.0F, -10.8F);
		rightLeg1.setTextureSize(512, 256);
		rightLeg1.mirror = true;
		setRotation(rightLeg1, -0.6981317F, 0F, 0F);
		rightLeg2 = new ModelRenderer(this, 160, 0);
		rightLeg2.addBox(-2.5F, 7F, -8F, 5, 10, 3);
		rightLeg2.setRotationPoint(-4.5F, 5F, -10.8F);
		rightLeg2.setTextureSize(512, 256);
		rightLeg2.mirror = true;
		setRotation(rightLeg2, 0F, 0F, 0F);
		rightFoot = new ModelRenderer(this, 180, 0);
		rightFoot.addBox(-2.5F, 17F, -15F, 5, 2, 10);
		rightFoot.setRotationPoint(-4.5F, 5F, -10.8F);
		rightFoot.setTextureSize(512, 256);
		rightFoot.mirror = true;
		setRotation(rightFoot, 0F, 0F, 0F);
		convertToChild(rightLeg2, rightFoot);
		convertToChild(rightLeg1, rightLeg2);
		
		// Back right leg (good)
		backRightLeg1 = new ModelRenderer(this, 140, 20);
		backRightLeg1.addBox(-2.5F, 0F, -2.5F, 5, 15, 5);
		backRightLeg1.setRotationPoint(-4.5F, 3.1F, 24.8F);
		backRightLeg1.setTextureSize(512, 256);
		backRightLeg1.mirror = true;
		setRotation(backRightLeg1, -0.6981317F, 0F, 0F);
		backRightLeg2 = new ModelRenderer(this, 170, 20);
		backRightLeg2.addBox(-2.5F, 9F, -11.5F, 5, 10, 5);
		backRightLeg2.setRotationPoint(-4.5F, 3.1F, 24.8F);
		backRightLeg2.setTextureSize(512, 256);
		backRightLeg2.mirror = true;
		setRotation(backRightLeg2, 0F, 0F, 0F);
		backRightFoot = new ModelRenderer(this, 180, 0);
		backRightFoot.addBox(-2.5F, 19F, -16.5F, 5, 2, 10);
		backRightFoot.setRotationPoint(-4.5F, 3.1F, 24.8F);
		backRightFoot.setTextureSize(512, 256);
		backRightFoot.mirror = true;
		setRotation(backRightFoot, 0F, 0F, 0F);
		convertToChild(backRightLeg2, backRightFoot);
		convertToChild(backRightLeg1, backRightLeg2);

		// Tail pieces (good)
		tail1 = new ModelRenderer(this, 220, 0);
		tail1.addBox(-6F, -4F, 0F, 12, 8, 10);
		tail1.setRotationPoint(0F, 0F, 30F);
		tail1.setTextureSize(512, 256);
		tail1.mirror = true;
		setRotation(tail1, 0F, 0F, 0F);
		tail2 = new ModelRenderer(this, 270, 0);
		tail2.addBox(-5F, -3F, 0F, 10, 6, 10);
		tail2.setRotationPoint(1F, 0F, 40F);
		tail2.setTextureSize(512, 256);
		tail2.mirror = true;
		setRotation(tail2, 0F, 0F, 0F);
		tail3 = new ModelRenderer(this, 240, 20);
		tail3.addBox(-4F, -2F, 0F, 8, 4, 10);
		tail3.setRotationPoint(1F, 0F, 50F);
		tail3.setTextureSize(512, 256);
		tail3.mirror = true;
		setRotation(tail3, 0F, 0F, 0F);
		tail4 = new ModelRenderer(this, 280, 20);
		tail4.addBox(-3F, -1F, 0F, 6, 2, 10);
		tail4.setRotationPoint(1F, 0F, 60F);
		tail4.setTextureSize(512, 256);
		tail4.mirror = true;
		setRotation(tail4, 0F, 0F, 0F);
		tailWing = new ModelRenderer(this, 320, 0);
		tailWing.addBox(-9F, -1F, 0F, 18, 0, 10);
		tailWing.setRotationPoint(1F, 2F, 60F);
		tailWing.setTextureSize(512, 256);
		tailWing.mirror = true;
		setRotation(tailWing, 0F, 0F, 0F);
		convertToChild(tail4, tailWing);
		convertToChild(tail3, tail4);
		convertToChild(tail2, tail3);
		convertToChild(tail1, tail2);
		
		// Body (good)
		body = new ModelRenderer(this, 0, 0);
		body.addBox(-7F, -5F, -15F, 14, 10, 45);
		body.setRotationPoint(0F, 0F, 0F);
		body.setTextureSize(512, 256);
		body.mirror = true;
		setRotation(body, 0F, 0F, 0F);
    }
    
    // This is really useful for converting the source from a Techne model export
    // which will have absolute rotation points that need to be converted before
    // creating the addChild() relationship
    protected void convertToChild(ModelRenderer parParent, ModelRenderer parChild)
    {
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
