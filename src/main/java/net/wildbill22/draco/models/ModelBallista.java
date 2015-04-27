package net.wildbill22.draco.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

/** 
 * @author WILLIAM
 *
 */
public class ModelBallista extends ModelBase {
	//fields
    ModelRenderer mainBody;
    ModelRenderer part12;
    ModelRenderer part13;
    ModelRenderer stringPlane;
    ModelRenderer arm1;
    ModelRenderer arm2;
  
    public ModelBallista() {
    	textureWidth = 256;
    	textureHeight = 128;

    	// part11
    	mainBody = new ModelRenderer(this, "mainBody");
    	mainBody.setRotationPoint(0F, 10F, -19F);
    	mainBody.setTextureSize(256, 128);
    	mainBody.mirror = true;
    	setRotation(mainBody, -0.0523599F, 0F, 0F);
    	
    	// Textures
    	setTextureOffset("mainBody.body", 0, 0);
    	setTextureOffset("mainBody.wheel", 0, 65);
    	setTextureOffset("mainBody.stringPlane", 26, 78);
    	setTextureOffset("mainBody.arm", 129, 0);
    	
    	// leftWheel
    	mainBody.addBox("wheel", 15F, 4F, -5F, 1, 10, 10);
    	// rightWheel
    	mainBody.addBox("wheel", -16F, 4F, -5F, 1, 10, 10);
    	// part1
    	mainBody.addBox("body", -15F, 8F, -1F, 30, 2, 2);
    	// part2
    	mainBody.addBox("body", -14F, 0F, -1F, 2, 8, 2);
    	// part3
    	mainBody.addBox("body", 12F, 0F, -1F, 2, 8, 2);
    	// part4
    	mainBody.addBox("body", -2.5F, -8F, -5F, 1, 8, 7);    	
    	// part5 (leg in back)
    	mainBody.addBox("body", 0F, 0F, 42F, 1, 12, 1);
    	// part6
    	mainBody.addBox("body", -14F, -8F, -5F, 28, 1, 7);
    	// part7
    	mainBody.addBox("body", -14F, -8F, -5F, 1, 8, 6);    	
    	// part8
    	mainBody.addBox("body", 13F, -8F, -5F, 1, 8, 6);
    	// part9
    	mainBody.addBox("body", 1.5F, -8F, -5F, 1, 8, 7);
    	// part10
    	mainBody.addBox("body", -14F, -1F, -5F, 28, 1, 10);
    	// part11    	
    	mainBody.addBox("body", -1.5F, -2F, -5F, 3, 2, 60);
    	
    	// part12 (crank)
    	part12 = new ModelRenderer(this, "mainBody");
    	part12.addBox("body", 1.5F, 3.5F, 48.5F, 1, 1, 5);
        setRotation(part12, 0.0872665F, 0F, 0F);
	    mainBody.addChild(part12);
    	// part13 (crank)
    	part13 = new ModelRenderer(this, "mainBody");
    	part13.addBox("body", 1.5F, 1.5F, 50.5F, 1, 5, 1);
        setRotation(part13, 0.0872665F, 0F, 0F);
	    mainBody.addChild(part13);
    	
    	// stringPlane
    	stringPlane = new ModelRenderer(this, "mainBody");
    	stringPlane.addBox("stringPlane", -25F, -2F, -5F, 50, 0, 50);
    	setRotation(stringPlane, -0.0523599F, 0F, 0F);
	    mainBody.addChild(stringPlane);
    	
    	// arm1
    	arm1 = new ModelRenderer(this, "mainBody");
    	arm1.addBox("arm", 2F, -3.3F, -0.5F, 2, 2, 31);
	    setRotation(arm1, -0.0349066F, 0.7853982F, -0.0349066F);
	    mainBody.addChild(arm1);
    	// arm2
    	arm2 = new ModelRenderer(this, "mainBody");
    	arm2.addBox("arm", -4F, -3.3F, -0.5F, 2, 2, 31);
    	setRotation(arm2, -0.0349066F, -0.7853982F, -0.0349066F);
	    mainBody.addChild(arm2);  
    }
  
  	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		mainBody.render(f5);
	}
  
  	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
  
  	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
  		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
  	}
}
