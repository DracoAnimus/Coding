package net.wildbill22.draco.client.models;

import java.util.HashMap;

import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.wildbill22.draco.client.MCAClientLibrary.MCAModelRenderer;
import net.wildbill22.draco.common.MCACommonLibrary.MCAVersionChecker;
import net.wildbill22.draco.common.MCACommonLibrary.animation.AnimationHandler;
import net.wildbill22.draco.common.MCACommonLibrary.math.Matrix4f;
import net.wildbill22.draco.common.MCACommonLibrary.math.Quaternion;
import net.wildbill22.draco.common.entities.dragons.EntityMCSilverDragon;
import net.wildbill22.draco.entities.player.DragonPlayer;

public class ModelMCSilverDragon extends ModelBase {
public final int MCA_MIN_REQUESTED_VERSION = 3;
public HashMap<String, MCAModelRenderer> parts = new HashMap<String, MCAModelRenderer>();

MCAModelRenderer leftLeg1;
MCAModelRenderer backLeftLeg1;
MCAModelRenderer rightLeg1;
MCAModelRenderer backRightLeg1;
MCAModelRenderer topMouth;
MCAModelRenderer bottomMouth;
MCAModelRenderer neck;
MCAModelRenderer head;
MCAModelRenderer rWing1;
MCAModelRenderer lWing1;
MCAModelRenderer neckSpikes;
MCAModelRenderer tail1;
MCAModelRenderer tail2;
MCAModelRenderer tail3;
MCAModelRenderer tail4;
MCAModelRenderer tailWing;
MCAModelRenderer body;
MCAModelRenderer leftLeg2;
MCAModelRenderer leftFoot;
MCAModelRenderer backLeftLeg2;
MCAModelRenderer backLeftFoot;
MCAModelRenderer rightFoot;
MCAModelRenderer rightLeg2;
MCAModelRenderer backRightLeg2;
MCAModelRenderer backRightFoot;
MCAModelRenderer rWing2;
MCAModelRenderer rWing3;
MCAModelRenderer rWing4;
MCAModelRenderer lWing2;
MCAModelRenderer lWing3;
MCAModelRenderer lWing4;
DragonPlayer dragonPlayer;

public ModelMCSilverDragon()
{
	MCAVersionChecker.checkForLibraryVersion(getClass(), MCA_MIN_REQUESTED_VERSION);
	
	textureWidth = 512;
	textureHeight = 256;
	
	leftLeg1 = new MCAModelRenderer(this, "leftLeg1", 140, 0);
	leftLeg1.mirror = false;
	leftLeg1.addBox(-2.5F, -11.0F, -1.5F, 5, 11, 3);
	leftLeg1.setInitialRotationPoint(1.5F, -3.5F, 6.8F);
	leftLeg1.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(-0.34202012F, 0.0F, 0.0F, 0.9396926F)).transpose());
	leftLeg1.setTextureSize(512, 256);
	parts.put(leftLeg1.boxName, leftLeg1);
	
	backLeftLeg1 = new MCAModelRenderer(this, "backLeftLeg1", 140, 20);
	backLeftLeg1.mirror = false;
	backLeftLeg1.addBox(-2.5F, -15.0F, -2.5F, 5, 15, 5);
	backLeftLeg1.setInitialRotationPoint(1.5F, -1.0999999F, -26.8F);
	backLeftLeg1.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(-0.34202012F, 0.0F, 0.0F, 0.9396926F)).transpose());
	backLeftLeg1.setTextureSize(512, 256);
	parts.put(backLeftLeg1.boxName, backLeftLeg1);
	
	rightLeg1 = new MCAModelRenderer(this, "rightLeg1", 140, 0);
	rightLeg1.mirror = false;
	rightLeg1.addBox(-2.5F, -11.0F, -1.5F, 5, 11, 3);
	rightLeg1.setInitialRotationPoint(-7.5F, -3.5F, 6.8F);
	rightLeg1.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(-0.34202012F, 0.0F, 0.0F, 0.9396926F)).transpose());
	rightLeg1.setTextureSize(512, 256);
	parts.put(rightLeg1.boxName, rightLeg1);
	
	backRightLeg1 = new MCAModelRenderer(this, "backRightLeg1", 140, 20);
	backRightLeg1.mirror = false;
	backRightLeg1.addBox(-2.5F, -15.0F, -2.5F, 5, 15, 5);
	backRightLeg1.setInitialRotationPoint(-7.5F, -1.0999999F, -26.8F);
	backRightLeg1.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(-0.34202012F, 0.0F, 0.0F, 0.9396926F)).transpose());
	backRightLeg1.setTextureSize(512, 256);
	parts.put(backRightLeg1.boxName, backRightLeg1);
	
	topMouth = new MCAModelRenderer(this, "topMouth", 0, 60);
	topMouth.mirror = false;
	topMouth.addBox(-4.0F, -2.0F, 0.0F, 8, 4, 8);
	topMouth.setInitialRotationPoint(-3.0F, 12.0F, 41.0F);
	topMouth.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.0F, 0.0F, 0.0F, 1.0F)).transpose());
	topMouth.setTextureSize(512, 256);
	parts.put(topMouth.boxName, topMouth);
	
	bottomMouth = new MCAModelRenderer(this, "bottomMouth", 0, 75);
	bottomMouth.mirror = false;
	bottomMouth.addBox(-4.0F, -0.5F, 0.0F, 8, 1, 8);
	bottomMouth.setInitialRotationPoint(-3.0F, 9.5F, 41.0F);
	bottomMouth.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.0F, 0.0F, 0.0F, 1.0F)).transpose());
	bottomMouth.setTextureSize(512, 256);
	parts.put(bottomMouth.boxName, bottomMouth);
	
	neck = new MCAModelRenderer(this, "neck", 40, 60);
	neck.mirror = false;
	neck.addBox(-3.0F, -3.0F, 0.0F, 6, 6, 25);
	neck.setInitialRotationPoint(-3.0F, -0.5F, 14.0F);
	neck.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(-0.34202012F, 0.0F, 0.0F, 0.9396926F)).transpose());
	neck.setTextureSize(512, 256);
	parts.put(neck.boxName, neck);
	
	head = new MCAModelRenderer(this, "head", 125, 45);
	head.mirror = false;
	head.addBox(-5.0F, -5.0F, 0.0F, 10, 10, 10);
	head.setInitialRotationPoint(-3.0F, 14.0F, 31.0F);
	head.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.0F, 0.0F, 0.0F, 1.0F)).transpose());
	head.setTextureSize(512, 256);
	parts.put(head.boxName, head);
	
	rWing1 = new MCAModelRenderer(this, "rWing1", 0, 100);
	rWing1.mirror = false;
	rWing1.addBox(-30.0F, -2.0F, -2.0F, 30, 4, 4);
	rWing1.setInitialRotationPoint(-9.0F, 2.0F, 13.0F);
	rWing1.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.0F, 0.0F, -0.21643962F, 0.976296F)).transpose());
	rWing1.setTextureSize(512, 256);
	parts.put(rWing1.boxName, rWing1);
	
	lWing1 = new MCAModelRenderer(this, "lWing1", 0, 100);
	lWing1.mirror = false;
	lWing1.addBox(0.0F, -2.0F, -2.0F, 30, 4, 4);
	lWing1.setInitialRotationPoint(3.0F, 2.0F, 13.0F);
	lWing1.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.0F, 0.0F, 0.21643962F, 0.976296F)).transpose());
	lWing1.setTextureSize(512, 256);
	parts.put(lWing1.boxName, lWing1);
	
	neckSpikes = new MCAModelRenderer(this, "neckSpikes", 0, 120);
	neckSpikes.mirror = false;
	neckSpikes.addBox(-15.0F, -15.0F, 2.0F, 30, 30, 0);
	neckSpikes.setInitialRotationPoint(-3.0F, 14.0F, 31.0F);
	neckSpikes.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(-0.21643962F, 0.0F, 0.0F, 0.976296F)).transpose());
	neckSpikes.setTextureSize(512, 256);
	parts.put(neckSpikes.boxName, neckSpikes);
	
	tail1 = new MCAModelRenderer(this, "tail1", 220, 0);
	tail1.mirror = false;
	tail1.addBox(-6.0F, -4.0F, -10.0F, 12, 8, 10);
	tail1.setInitialRotationPoint(-3.0F, 0.0F, -28.0F);
	tail1.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.0F, 0.0F, 0.0F, 1.0F)).transpose());
	tail1.setTextureSize(512, 256);
	parts.put(tail1.boxName, tail1);
	
	tail2 = new MCAModelRenderer(this, "tail2", 270, 0);
	tail2.mirror = false;
	tail2.addBox(-5.0F, -3.0F, -10.0F, 10, 6, 10);
	tail2.setInitialRotationPoint(-3.0F, 0.0F, -38.0F);
	tail2.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.0F, 0.0F, 0.0F, 1.0F)).transpose());
	tail2.setTextureSize(512, 256);
	parts.put(tail2.boxName, tail2);
	
	tail3 = new MCAModelRenderer(this, "tail3", 240, 20);
	tail3.mirror = false;
	tail3.addBox(-4.0F, -2.0F, -10.0F, 8, 4, 10);
	tail3.setInitialRotationPoint(-3.0F, 0.0F, -48.0F);
	tail3.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.0F, 0.0F, 0.0F, 1.0F)).transpose());
	tail3.setTextureSize(512, 256);
	parts.put(tail3.boxName, tail3);
	
	tail4 = new MCAModelRenderer(this, "tail4", 280, 20);
	tail4.mirror = false;
	tail4.addBox(-3.0F, -1.0F, -10.0F, 6, 2, 10);
	tail4.setInitialRotationPoint(-3.0F, 0.0F, -58.0F);
	tail4.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.0F, 0.0F, 0.0F, 1.0F)).transpose());
	tail4.setTextureSize(512, 256);
	parts.put(tail4.boxName, tail4);
	
	tailWing = new MCAModelRenderer(this, "tailWing", 320, 0);
	tailWing.mirror = false;
	tailWing.addBox(-9.0F, 1.0F, -10.0F, 18, 0, 10);
	tailWing.setInitialRotationPoint(-3.0F, 0.0F, -58.0F);
	tailWing.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.0F, 0.0F, 0.0F, 1.0F)).transpose());
	tailWing.setTextureSize(512, 256);
	parts.put(tailWing.boxName, tailWing);
	
	body = new MCAModelRenderer(this, "body", 0, 0);
	body.mirror = false;
	body.addBox(0.0F, -10.0F, -45.0F, 14, 10, 45);
	body.setInitialRotationPoint(-10.0F, 5.0F, 16.0F);
	body.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.0F, 0.0F, 0.0F, 1.0F)).transpose());
	body.setTextureSize(512, 256);
	parts.put(body.boxName, body);
	
	leftLeg2 = new MCAModelRenderer(this, "leftLeg2", 160, 0);
	leftLeg2.mirror = false;
	leftLeg2.addBox(-2.5F, -10.0F, -1.5F, 5, 10, 3);
	leftLeg2.setInitialRotationPoint(0.0F, -10.0F, 0.5F);
	leftLeg2.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.34202012F, 0.0F, 0.0F, 0.9396926F)).transpose());
	leftLeg2.setTextureSize(512, 256);
	parts.put(leftLeg2.boxName, leftLeg2);
	leftLeg1.addChild(leftLeg2);
	
	leftFoot = new MCAModelRenderer(this, "leftFoot", 180, 0);
	leftFoot.mirror = false;
	leftFoot.addBox(-2.5F, -2.0F, -0.5F, 5, 2, 10);
	leftFoot.setInitialRotationPoint(0.0F, -16.0F, -5.5F);
	leftFoot.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.34202012F, 0.0F, 0.0F, 0.9396926F)).transpose());
	leftFoot.setTextureSize(512, 256);
	parts.put(leftFoot.boxName, leftFoot);
	leftLeg1.addChild(leftFoot);
	
	backLeftLeg2 = new MCAModelRenderer(this, "backLeftLeg2", 170, 20);
	backLeftLeg2.mirror = false;
	backLeftLeg2.addBox(-2.5F, -10.0F, -2.5F, 5, 10, 5);
	backLeftLeg2.setInitialRotationPoint(0.0F, -13.5F, 0.5F);
	backLeftLeg2.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.34202012F, 0.0F, 0.0F, 0.9396926F)).transpose());
	backLeftLeg2.setTextureSize(512, 256);
	parts.put(backLeftLeg2.boxName, backLeftLeg2);
	backLeftLeg1.addChild(backLeftLeg2);
	
	backLeftFoot = new MCAModelRenderer(this, "backLeftFoot", 180, 0);
	backLeftFoot.mirror = false;
	backLeftFoot.addBox(-2.5F, -2.0F, -0.5F, 5, 2, 10);
	backLeftFoot.setInitialRotationPoint(0.0F, -19.0F, -6.7F);
	backLeftFoot.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.34202012F, 0.0F, 0.0F, 0.9396926F)).transpose());
	backLeftFoot.setTextureSize(512, 256);
	parts.put(backLeftFoot.boxName, backLeftFoot);
	backLeftLeg1.addChild(backLeftFoot);
	
	rightFoot = new MCAModelRenderer(this, "rightFoot", 180, 0);
	rightFoot.mirror = false;
	rightFoot.addBox(-2.5F, -2.0F, -0.5F, 5, 2, 10);
	rightFoot.setInitialRotationPoint(0.0F, -16.0F, -5.5F);
	rightFoot.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.34202012F, 0.0F, 0.0F, 0.9396926F)).transpose());
	rightFoot.setTextureSize(512, 256);
	parts.put(rightFoot.boxName, rightFoot);
	rightLeg1.addChild(rightFoot);
	
	rightLeg2 = new MCAModelRenderer(this, "rightLeg2", 160, 0);
	rightLeg2.mirror = false;
	rightLeg2.addBox(-2.5F, -10.0F, -1.5F, 5, 10, 3);
	rightLeg2.setInitialRotationPoint(0.0F, -10.0F, 0.5F);
	rightLeg2.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.34202012F, 0.0F, 0.0F, 0.9396926F)).transpose());
	rightLeg2.setTextureSize(512, 256);
	parts.put(rightLeg2.boxName, rightLeg2);
	rightLeg1.addChild(rightLeg2);
	
	backRightLeg2 = new MCAModelRenderer(this, "backRightLeg2", 170, 20);
	backRightLeg2.mirror = false;
	backRightLeg2.addBox(-2.5F, -10.0F, -2.5F, 5, 10, 5);
	backRightLeg2.setInitialRotationPoint(0.0F, -13.5F, 0.5F);
	backRightLeg2.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.34202012F, 0.0F, 0.0F, 0.9396926F)).transpose());
	backRightLeg2.setTextureSize(512, 256);
	parts.put(backRightLeg2.boxName, backRightLeg2);
	backRightLeg1.addChild(backRightLeg2);
	
	backRightFoot = new MCAModelRenderer(this, "backRightFoot", 180, 0);
	backRightFoot.mirror = false;
	backRightFoot.addBox(-2.5F, -2.0F, -0.5F, 5, 2, 10);
	backRightFoot.setInitialRotationPoint(0.0F, -19.0F, -6.7F);
	backRightFoot.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.34202012F, 0.0F, 0.0F, 0.9396926F)).transpose());
	backRightFoot.setTextureSize(512, 256);
	parts.put(backRightFoot.boxName, backRightFoot);
	backRightLeg1.addChild(backRightFoot);
	
	rWing2 = new MCAModelRenderer(this, "rWing2", 0, 110);
	rWing2.mirror = false;
	rWing2.addBox(-25.0F, -2.0F, -2.0F, 25, 4, 4);
	rWing2.setInitialRotationPoint(-29.2F, 0.2F, 0.0F);
	rWing2.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.0F, 0.0F, 0.21643962F, 0.976296F)).transpose());
	rWing2.setTextureSize(512, 256);
	parts.put(rWing2.boxName, rWing2);
	rWing1.addChild(rWing2);
	
	rWing3 = new MCAModelRenderer(this, "rWing3", 80, 100);
	rWing3.mirror = false;
	rWing3.addBox(-30.0F, 2.0F, -32.0F, 30, 0, 30);
	rWing3.setInitialRotationPoint(0.0F, 0.0F, 0.0F);
	rWing3.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.0F, 0.0F, 0.0F, 1.0F)).transpose());
	rWing3.setTextureSize(512, 256);
	parts.put(rWing3.boxName, rWing3);
	rWing1.addChild(rWing3);
	
	rWing4 = new MCAModelRenderer(this, "rWing4", 80, 140);
	rWing4.mirror = false;
	rWing4.addBox(-25.0F, 2.0F, -32.0F, 25, 0, 30);
	rWing4.setInitialRotationPoint(-29.2F, 0.2F, 0.0F);
	rWing4.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.0F, 0.0F, 0.21643962F, 0.976296F)).transpose());
	rWing4.setTextureSize(512, 256);
	parts.put(rWing4.boxName, rWing4);
	rWing1.addChild(rWing4);
	
	lWing2 = new MCAModelRenderer(this, "lWing2", 0, 110);
	lWing2.mirror = false;
	lWing2.addBox(0.0F, -2.0F, -2.0F, 25, 4, 4);
	lWing2.setInitialRotationPoint(29.2F, -0.2F, 0.0F);
	lWing2.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.0F, 0.0F, -0.21643962F, 0.976296F)).transpose());
	lWing2.setTextureSize(512, 256);
	parts.put(lWing2.boxName, lWing2);
	lWing1.addChild(lWing2);
	
	lWing3 = new MCAModelRenderer(this, "lWing3", 210, 100);
	lWing3.mirror = false;
	lWing3.addBox(0.0F, 2.0F, -32.0F, 30, 0, 30);
	lWing3.setInitialRotationPoint(0.0F, 0.0F, 0.0F);
	lWing3.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.0F, 0.0F, 0.0F, 1.0F)).transpose());
	lWing3.setTextureSize(512, 256);
	parts.put(lWing3.boxName, lWing3);
	lWing1.addChild(lWing3);
	
	lWing4 = new MCAModelRenderer(this, "lWing4", 210, 140);
	lWing4.mirror = false;
	lWing4.addBox(0.0F, 2.0F, -32.0F, 25, 0, 30);
	lWing4.setInitialRotationPoint(29.2F, 0.2F, 0.0F);
	lWing4.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.0F, 0.0F, -0.21643962F, 0.976296F)).transpose());
	lWing4.setTextureSize(512, 256);
	parts.put(lWing4.boxName, lWing4);
	lWing1.addChild(lWing4);	
}

@Override
public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7) 
{
	EntityMCSilverDragon entity;
    if (par1Entity instanceof EntityPlayer) {
        dragonPlayer = DragonPlayer.get((EntityPlayer)par1Entity);
        entity = (EntityMCSilverDragon) dragonPlayer.getDragon();
//        entity.setPlayerAnimationsToRun();
        entity.setPlayerAnimationsToRun(par3);
    }
    else {
        entity = (EntityMCSilverDragon) par1Entity;
    }	
	AnimationHandler.performAnimationInModel(parts, entity);
	
	//Render every non-child part
	leftLeg1.render(par7);
	backLeftLeg1.render(par7);
	rightLeg1.render(par7);
	backRightLeg1.render(par7);
	topMouth.render(par7);
	bottomMouth.render(par7);
	neck.render(par7);
	head.render(par7);
	rWing1.render(par7);
	lWing1.render(par7);
	neckSpikes.render(par7);
	tail1.render(par7);
	tail2.render(par7);
	tail3.render(par7);
	tail4.render(par7);
	tailWing.render(par7);
	body.render(par7);
}

@Override
public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {}

public MCAModelRenderer getModelRendererFromName(String name)
{
return parts.get(name);
}
}