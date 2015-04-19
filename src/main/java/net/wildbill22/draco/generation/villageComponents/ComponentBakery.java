package net.wildbill22.draco.generation.villageComponents;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.common.registry.VillagerRegistry;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureVillagePieces;
import net.minecraft.world.gen.structure.StructureVillagePieces.Start;
import net.wildbill22.draco.generation.villageHandlers.BakeryHandler;
import net.wildbill22.draco.generation.villageHandlers.VillagerBakeryTradeHandler;

public class ComponentBakery extends StructureVillagePieces.Village{	
	private int averageGroundLevel = -1;

	public ComponentBakery(){}
	
	public ComponentBakery(Start startPiece, int type, Random random, StructureBoundingBox _boundingBox, int direction){
		super(startPiece, type);
        coordBaseMode = direction;
    	boundingBox = _boundingBox;
	}
	
	public static void addVillagePiece(@SuppressWarnings("rawtypes") Class c, String s) { 
		try { 
			MapGenStructureIO.func_143031_a(c, s); 
		} 
		catch (Exception localException) {} 
	} 

	public static StructureVillagePieces.Village buildComponent(StructureVillagePieces.Start startPiece, @SuppressWarnings("rawtypes") List pieces, Random random, int x, int y, int z, int direction, int type) {
        StructureBoundingBox _boundingBox = StructureBoundingBox.getComponentToAddBoundingBox(x, y, z, 0, 0, 0, 11, 6, 11, direction);
        if(canVillageGoDeeper(_boundingBox)){ 
        	if(StructureComponent.findIntersecting(pieces, _boundingBox) == null){
        		return new ComponentBakery(startPiece, type, random, _boundingBox, direction);
        	}
        }
		return null;
	}
	
	@Override
	public boolean addComponentParts(World world, Random random, StructureBoundingBox sbb) {
        if (this.averageGroundLevel < 0) 
        {
            this.averageGroundLevel = this.getAverageGroundLevel(world, sbb);

            if (this.averageGroundLevel < 0)
                return true;

            this.boundingBox.offset(0, this.averageGroundLevel - this.boundingBox.maxY + 4, 0);
        }
        Block walls = Blocks.log;
        Block roof = Blocks.oak_stairs;
        Block roofPeak = Blocks.planks;
        Block Floor = Blocks.planks;
        int eastStair = this.getMetadataWithOffset(roof, 0);
        int westStair = this.getMetadataWithOffset(roof, 1);
        int southStair = this.getMetadataWithOffset(roof, 2);
        int northStair = this.getMetadataWithOffset(roof, 3);

        // Clear out for building and make foundation
        for (int xx = 1; xx < 11; xx++){
            for (int zz = 0; zz < 6; zz++){
                clearCurrentPositionBlocksUpwards(world, xx,0,zz, sbb);
                this.func_151554_b(world, Blocks.cobblestone, 0, xx, -1, zz, sbb);
            }
        }

        //Floor
        fillWithBlocks(world, sbb, 2, 0, 2, 9, 0, 6, Floor, Floor, false);

        //front
        fillWithBlocks(world, sbb, 2,1,2, 9,3,2, walls, walls, false);
        placeBlockAtCurrentPosition(world, Blocks.air, 3, 1,2,0, sbb);
        placeBlockAtCurrentPosition(world, Blocks.air, 3, 2,2,0, sbb);
        placeDoorAtCurrentPosition(world, boundingBox, random, 3, 1, 2, 0);        
        this.placeBlockAtCurrentPosition(world, roof, northStair, 3,0,1, sbb);
        fillWithBlocks(world, sbb, 5, 2, 2, 7, 2, 2, Blocks.glass, Blocks.glass, false);

        //Side
        fillWithBlocks(world, sbb, 2, 1, 2, 2, 4, 6, walls, walls, false);
        fillWithBlocks(world, sbb, 2, 2, 3, 2, 2, 5, Blocks.glass, Blocks.glass, false);
        placeBlockAtCurrentPosition(world, walls, 0, 2,5,4, sbb);

        //Back
        fillWithBlocks(world, sbb, 3,1,6, 9,3,6, walls, walls, false);
        fillWithBlocks(world, sbb, 3,2,6, 7,2,6, Blocks.glass, Blocks.glass, false);

        //Oven
        fillWithBlocks(world, sbb, 8, 1, 3, 9, 4, 5, Blocks.brick_block, Blocks.brick_block, false);
        fillWithBlocks(world, sbb, 9, 5, 4, 9, 7, 4, Blocks.brick_block, Blocks.brick_block, false);
        fillWithBlocks(world, sbb, 10, 1, 4, 10, 3, 4, Blocks.brick_block, Blocks.brick_block, false);
        placeBlockAtCurrentPosition(world, Blocks.air, 0, 8, 2, 4, sbb);
        placeBlockAtCurrentPosition(world, Blocks.furnace, 0, 9, 2, 4, sbb);

        //Interior
        this.placeBlockAtCurrentPosition(world, roof, westStair, 3,1,4, sbb);
        this.placeBlockAtCurrentPosition(world, roof, westStair, 3,1,5, sbb);        
        fillWithBlocks(world, sbb, 4,1,4, 4,1,5, Blocks.fence, Blocks.fence, false);
        fillWithBlocks(world, sbb, 4,2,4, 4,2,5, Blocks.wooden_pressure_plate, Blocks.wooden_pressure_plate, false);
        fillWithBlocks(world, sbb, 4,2,6, 4,2,6, walls, walls, true);
        this.placeBlockAtCurrentPosition(world, roof, eastStair, 5,1,4, sbb);
        this.placeBlockAtCurrentPosition(world, roof, eastStair, 5,1,5, sbb);        
        
        // Roof
        int height = 3; // Roof height at bottom
        for (int yy = 0; yy <= 2; ++yy) {
            for (int xx = 1; xx <= 10; ++xx) {
            	this.placeBlockAtCurrentPosition(world, roof, northStair, xx, height+yy, yy+1, sbb);
                this.placeBlockAtCurrentPosition(world, roof, southStair, xx, height+yy, 7-yy, sbb);
            }
        }
        // Center of roof
        for (int xx = 1; xx <= 8; ++xx)
        	placeBlockAtCurrentPosition(world, roofPeak, 0, xx,height+3,4, sbb);
       	placeBlockAtCurrentPosition(world, roofPeak, 0, 10,6,4, sbb);
        
        spawnVillagers(world, sbb, 3,1,4, 2);

        return true;    	
	}

	@Override
    protected int getVillagerType(int par1) {
        return VillagerBakeryTradeHandler.BAKER;
    }
    
	// Call from mod's init
	public static void init() {
		ComponentBakery.addVillagePiece(ComponentBakery.class, "ViBakery");
		BakeryHandler bakeryHandler = new BakeryHandler();
		VillagerRegistry.instance().registerVillageCreationHandler(bakeryHandler);
	}
}