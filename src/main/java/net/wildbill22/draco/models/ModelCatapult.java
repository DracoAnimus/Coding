package net.wildbill22.draco.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.wildbill22.draco.entities.hostile.EntityCatapult;

/** 
 * @author WILLIAM
 *
 */
public class ModelCatapult extends ModelBase {
	//fields
    ModelRenderer mainBody; // part7
    ModelRenderer part4;
    ModelRenderer part5;
    ModelRenderer arm;
  
    public ModelCatapult() {
    	textureWidth = 256;
    	textureHeight = 128;

    	// Textures
    	setTextureOffset("mainBody.body", 0, 0);
    	setTextureOffset("mainBody.wheel", 0, 70);
    	setTextureOffset("mainBody.bucket", 0, 100);
    	setTextureOffset("mainBody.arm", 138, 0);
    	
    	// part7
    	mainBody = new ModelRenderer(this, "mainBody");
    	mainBody.addBox("body", -14F, -2F, -2F, 28, 4, 4);    	
    	mainBody.setRotationPoint(1F, 13F, -7.5F);
    	mainBody.setTextureSize(256, 128);
    	mainBody.mirror = true;
        setRotation(mainBody, 0F, 0F, 0F);
    	
    	// part1
    	mainBody.addBox("body", -14F, 4.5F, 31F, 28, 3, 3);
    	// part2
    	mainBody.addBox("body", 14F, 3F, -24.5F, 2, 5, 64);
    	// part3
    	mainBody.addBox("body", -16F, 3F, -24.5F, 2, 5, 64);
    	
    	// part4 (diagonal support)
        part4 = new ModelRenderer(this, "mainBody");
        part4.addBox("body", -16F, -10.4F, -13.7F, 2, 28, 5);
        part4.setRotationPoint(1F, 13F, -7.5F);
        part4.setTextureSize(256, 128);
        setRotation(part4, -0.7853982F, 0F, 0F);
        part4.mirror = true;
        convertToChild(mainBody, part4);
        
    	// part5 (diagonal support)
        part5 = new ModelRenderer(this, "mainBody");
        part5.addBox("body", 14F, -10.4F, -13.7F, 2, 28, 5);
        part5.setRotationPoint(1F, 13F, -7.5F);
        part5.setTextureSize(256, 128);
        setRotation(part5, -0.7853982F, 0F, 0F);
        part5.mirror = true;
        convertToChild(mainBody, part5);
  	
    	// part6
    	mainBody.addBox("body", -14F, -17F, -0.5F, 28, 4, 1);
    	// part8
    	mainBody.addBox("body", -14F, 4.5F, -19F, 28, 3, 3);
    	// part9
    	mainBody.addBox("body", -16F, -17F, -2.5F, 2, 20, 5);
    	// part10
    	mainBody.addBox("body", 14F, -17F, -2.5F, 2, 20, 5);
    	
    	// wheel1  	
    	mainBody.addBox("wheel", -18F, 2F, 28.5F, 2, 8, 8);
    	// wheel2  	
    	mainBody.addBox("wheel", 16F, 2F, 28.5F, 2, 8, 8);
    	// wheel3  	
    	mainBody.addBox("wheel", -18F, 2F, -21.5F, 2, 8, 8);
    	// wheel4  	
    	mainBody.addBox("wheel", 16F, 2F, -21.5F, 2, 8, 8);
    	
    	// arm
    	arm = new ModelRenderer(this, "mainBody");
    	arm.addBox("arm", -2.5F, -0.5F, 2F, 5, 1, 30);
        arm.setRotationPoint(1F, 13F, -7.5F);
        arm.setTextureSize(256, 128);
        setRotation(arm, 0.5235988F, 0F, 0F);

	    // bucket
    	arm.addBox("bucket", -4.5F, -0.5F, 32F, 9, 4, 9);
    }
  
  	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		mainBody.render(f5);
		arm.render(f5);
	}
  
  	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
  
  	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
  		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
  		arm.rotateAngleX = ((EntityCatapult)entity).getArmPosition();
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
