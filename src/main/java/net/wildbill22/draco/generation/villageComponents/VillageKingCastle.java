package net.wildbill22.draco.generation.villageComponents;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.common.registry.VillagerRegistry;
import net.minecraft.entity.EntityList;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureVillagePieces;
import net.wildbill22.draco.biome.ModBiomes;
import net.wildbill22.draco.entities.hostile.EntityBaron;
import net.wildbill22.draco.entities.hostile.EntityGuard;
import net.wildbill22.draco.generation.villageHandlers.KingCastleCreationHandler;
import net.wildbill22.draco.lib.LogHelper;

public class VillageKingCastle extends StructureVillagePieces.Village
{
	private int averageGroundLevel = -1;
    private static final int HEIGHT = 12;
//	private final int SOUTH = 3;
//	private final int WEST  = 0;
//	private final int NORTH = 2;
//	private final int EAST  = 1;

    public VillageKingCastle() {}
    
	public VillageKingCastle(StructureVillagePieces.Start startPiece, int type, Random random, StructureBoundingBox _boundingBox, int direction){
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

	public static StructureVillagePieces.Village buildComponent(StructureVillagePieces.Start startPiece, 
			@SuppressWarnings("rawtypes") List pieces, Random random, int x, int y, int z, int direction, int type) {
        StructureBoundingBox _boundingBox = StructureBoundingBox.getComponentToAddBoundingBox(x, y, z, 0, 0, 0, 23, 12, 21, direction);
        if (canVillageGoDeeper(_boundingBox)) { 
        	if (StructureComponent.findIntersecting(pieces, _boundingBox) == null) {
        		return new VillageKingCastle(startPiece, type, random, _boundingBox, direction);
        	}
        }
		return null;
	}
	
	// Only create the Baron Castle in city biomes
	@SuppressWarnings("unused")
	@Override
	public boolean addComponentParts(World world, Random random, StructureBoundingBox box) {
		BiomeGenBase biome = world.getBiomeGenForCoords(box.minX, box.minZ);
		// TODO: Make capitol biome?
		if (biome != ModBiomes.biomeCityPlains)
			return true;

		LogHelper.info("VillageKingCastle: Build King's Castle at: " + box.minX + ", " + box.minZ);
        if (averageGroundLevel < 0){
            averageGroundLevel = getAverageGroundLevel(world, box);
            if (averageGroundLevel < 0){
                return true;
            }
            boundingBox.offset(0, this.averageGroundLevel - boundingBox.maxY + HEIGHT - 2, 0);
        }
		
        int eastStair = this.getMetadataWithOffset(Blocks.stone_brick_stairs, 0);
        int westStair = this.getMetadataWithOffset(Blocks.stone_brick_stairs, 1);
        int southStair = this.getMetadataWithOffset(Blocks.stone_brick_stairs, 2);
        int northStair = this.getMetadataWithOffset(Blocks.stone_brick_stairs, 3);

		// floors
//      placeBlockAtCurrentPosition(world, Blocks.stone_stairs, getMetadataWithOffset(Blocks.stone_stairs, 0), 1,0,1, box);

      // Bottom floor
//		for(int xx = 0; xx <= 23; xx++)
//			for (int zz = 0; zz < 21; zz++)
//				placeBlockAtCurrentPosition(world, Blocks.stonebrick, 0, xx,-1,zz, box);

  	
//		placeBlockAtCurrentPosition(world, Blocks.stone_stairs, getMetadataWithOffset(Blocks.stone_stairs, 1), 1,0,2, box);
//		for(int xx = 2; xx <= 8; xx++)
//			placeBlockAtCurrentPosition(world, Blocks.cobblestone, getMetadataWithOffset(Blocks.cobblestone, 0), xx,0,2, box);
//		placeBlockAtCurrentPosition(world, Blocks.stone_stairs, getMetadataWithOffset(Blocks.stone_stairs, 1), 9,0,2, box);

		fillWithAir(world, box, 1,0,1, 22,12,20);

        // Clear out the area for the building and make a base
    	clearBlocks(world, 3,0, 4,0, box);    // Front pillar
    	clearBlocks(world, 20,0, 21,0, box);  // Front pillar
    	clearBlocks(world, 1,1, 23,6, box);   // Front rooms
    	clearBlocks(world, 0,3, 0,4, box);    // left side pillar
    	clearBlocks(world, 3,7, 21,18, box);  // center courtyard
    	clearBlocks(world, 4,19, 20,19, box);
    	clearBlocks(world, 5,20, 19,20, box);
    	clearBlocks(world, 6,21, 18,21, box); // Back row
		
    	// *** FLOORS ***
    	// front left room 1st floor
		fillWithBlocks(world, box, 2,1,2, 5,1,5, Blocks.stonebrick, Blocks.stonebrick, false);
		fillWithBlocks(world, box, 1,1,3, 1,1,4, Blocks.stonebrick, Blocks.stonebrick, false);
		fillWithBlocks(world, box, 6,1,3, 6,1,4, Blocks.stonebrick, Blocks.stonebrick, false);
		fillWithBlocks(world, box, 3,1,1, 4,1,1, Blocks.stonebrick, Blocks.stonebrick, false);
		fillWithBlocks(world, box, 3,1,6, 4,1,6, Blocks.stonebrick, Blocks.stonebrick, false);

    	// front right room 1st floor
		fillWithBlocks(world, box, 19,1,2, 22,1,5, Blocks.stonebrick, Blocks.stonebrick, false);
		fillWithBlocks(world, box, 18,1,3, 18,1,4, Blocks.stonebrick, Blocks.stonebrick, false);
		fillWithBlocks(world, box, 23,1,3, 23,1,4, Blocks.stonebrick, Blocks.stonebrick, false);
		fillWithBlocks(world, box, 20,1,1, 21,1,1, Blocks.stonebrick, Blocks.stonebrick, false);
		fillWithBlocks(world, box, 20,1,6, 21,1,6, Blocks.stonebrick, Blocks.stonebrick, false);		
		
		// front stairs and doors 1st floor 
        placeBlockAtCurrentPosition(world, Blocks.stone_brick_stairs, eastStair, 7,1,3, box);
        placeBlockAtCurrentPosition(world, Blocks.stone_brick_stairs, eastStair, 7,1,4, box);
        placeBlockAtCurrentPosition(world, Blocks.stone_brick_stairs, westStair, 16,1,3, box);
        placeBlockAtCurrentPosition(world, Blocks.stone_brick_stairs, westStair, 16,1,4, box);
        placeDoorAtCurrentPosition(world, boundingBox, random, 3, 11,1,2);        
        placeDoorAtCurrentPosition(world, boundingBox, random, 3, 12,1,2);        
        placeDoorAtCurrentPosition(world, boundingBox, random, 3, 11,1,5);        
        placeDoorAtCurrentPosition(world, boundingBox, random, 3, 12,1,5);        
        placeBlockAtCurrentPosition(world, Blocks.stone_brick_stairs, northStair, 11,1,12, box);
        placeBlockAtCurrentPosition(world, Blocks.stone_brick_stairs, northStair, 12,1,12, box);

    	// front left room 4th floor
		fillWithBlocks(world, box, 2,4,2, 5,4,5, Blocks.stonebrick, Blocks.stonebrick, false);
		fillWithBlocks(world, box, 1,4,3, 1,4,4, Blocks.stonebrick, Blocks.stonebrick, false);
		fillWithBlocks(world, box, 6,4,3, 6,4,4, Blocks.stonebrick, Blocks.stonebrick, false);
		fillWithBlocks(world, box, 3,4,1, 4,4,1, Blocks.stonebrick, Blocks.stonebrick, false);
		fillWithBlocks(world, box, 3,4,6, 4,4,6, Blocks.stonebrick, Blocks.stonebrick, false);
    	// front right room 4th floor
		fillWithBlocks(world, box, 19,4,2, 22,4,5, Blocks.stonebrick, Blocks.stonebrick, false);
		fillWithBlocks(world, box, 18,4,3, 18,4,4, Blocks.stonebrick, Blocks.stonebrick, false);
		fillWithBlocks(world, box, 23,4,3, 23,4,4, Blocks.stonebrick, Blocks.stonebrick, false);
		fillWithBlocks(world, box, 20,4,1, 21,4,1, Blocks.stonebrick, Blocks.stonebrick, false);
		fillWithBlocks(world, box, 20,4,6, 21,4,6, Blocks.stonebrick, Blocks.stonebrick, false);		
    	// front left room 8th floor
		fillWithBlocks(world, box, 2,8,2, 5,8,5, Blocks.stonebrick, Blocks.stonebrick, false);
		fillWithBlocks(world, box, 1,8,3, 1,8,4, Blocks.stonebrick, Blocks.stonebrick, false);
		fillWithBlocks(world, box, 6,8,3, 6,8,4, Blocks.stonebrick, Blocks.stonebrick, false);
		fillWithBlocks(world, box, 3,8,1, 4,8,1, Blocks.stonebrick, Blocks.stonebrick, false);
		fillWithBlocks(world, box, 3,8,6, 4,8,6, Blocks.stonebrick, Blocks.stonebrick, false);
    	// front right room 8th floor
		fillWithBlocks(world, box, 19,8,2, 22,8,5, Blocks.stonebrick, Blocks.stonebrick, false);
		fillWithBlocks(world, box, 18,8,3, 18,8,4, Blocks.stonebrick, Blocks.stonebrick, false);
		fillWithBlocks(world, box, 23,8,3, 23,8,4, Blocks.stonebrick, Blocks.stonebrick, false);
		fillWithBlocks(world, box, 20,8,1, 21,8,1, Blocks.stonebrick, Blocks.stonebrick, false);
		fillWithBlocks(world, box, 20,8,6, 21,8,6, Blocks.stonebrick, Blocks.stonebrick, false);
		// back room floor
		fillWithBlocks(world, box, 8,1,14, 16,1,20, Blocks.stonebrick, Blocks.stonebrick, false);
		fillWithBlocks(world, box, 8,5,14, 16,5,20, Blocks.stonebrick, Blocks.stonebrick, false);
		fillWithBlocks(world, box, 8,8,14, 16,8,20, Blocks.stonebrick, Blocks.stonebrick, false);
        // back room top floor
		fillWithBlocks(world, box, 6,11,18, 10,11,21, Blocks.stonebrick, Blocks.stonebrick, false);
		fillWithBlocks(world, box, 13,11,18, 17,11,21, Blocks.stonebrick, Blocks.stonebrick, false);
		placeBlockAtCurrentPosition(world, Blocks.air, 0, 20,11,21, box);

		// *** WALLS ***
		// left room
		fillWithBlocks(world, box, 3,1,0, 4,9,0, Blocks.stonebrick, Blocks.stonebrick, false);
		fillWithBlocks(world, box, 1,1,1, 2,9,1, Blocks.stonebrick, Blocks.stonebrick, false);
		fillWithBlocks(world, box, 5,1,1, 6,9,1, Blocks.stonebrick, Blocks.stonebrick, false);
		fillWithBlocks(world, box, 1,1,2, 1,9,2, Blocks.stonebrick, Blocks.stonebrick, false);
		fillWithBlocks(world, box, 6,1,2, 7,9,2, Blocks.stonebrick, Blocks.stonebrick, false);
		fillWithBlocks(world, box, 0,1,3, 0,9,4, Blocks.stonebrick, Blocks.stonebrick, false);
		fillWithBlocks(world, box, 7,1,3, 7,9,4, Blocks.stonebrick, Blocks.stonebrick, false);
		fillWithBlocks(world, box, 1,1,5, 1,9,5, Blocks.stonebrick, Blocks.stonebrick, false);
		fillWithBlocks(world, box, 6,1,5, 7,9,5, Blocks.stonebrick, Blocks.stonebrick, false);
		fillWithBlocks(world, box, 1,1,6, 2,9,6, Blocks.stonebrick, Blocks.stonebrick, false);
		fillWithBlocks(world, box, 5,1,6, 6,9,6, Blocks.stonebrick, Blocks.stonebrick, false);
		fillWithBlocks(world, box, 3,1,7, 4,9,7, Blocks.stonebrick, Blocks.stonebrick, false);
		// right room
		fillWithBlocks(world, box, 20,1,0, 21,9,0, Blocks.stonebrick, Blocks.stonebrick, false);
		fillWithBlocks(world, box, 18,1,1, 19,9,1, Blocks.stonebrick, Blocks.stonebrick, false);
		fillWithBlocks(world, box, 22,1,1, 23,9,1, Blocks.stonebrick, Blocks.stonebrick, false);
		fillWithBlocks(world, box, 17,1,2, 18,9,2, Blocks.stonebrick, Blocks.stonebrick, false);
		fillWithBlocks(world, box, 23,1,2, 23,9,2, Blocks.stonebrick, Blocks.stonebrick, false);
		fillWithBlocks(world, box, 23,1,3, 23,9,4, Blocks.stonebrick, Blocks.stonebrick, false);
		fillWithBlocks(world, box, 17,1,5, 18,9,5, Blocks.stonebrick, Blocks.stonebrick, false);
		fillWithBlocks(world, box, 23,1,5, 23,9,5, Blocks.stonebrick, Blocks.stonebrick, false);
		fillWithBlocks(world, box, 18,1,6, 19,9,6, Blocks.stonebrick, Blocks.stonebrick, false);
		fillWithBlocks(world, box, 22,1,6, 23,9,6, Blocks.stonebrick, Blocks.stonebrick, false);
		fillWithBlocks(world, box, 20,1,7, 21,9,7, Blocks.stonebrick, Blocks.stonebrick, false);
		// left wall
		fillWithBlocks(world, box, 3,1,8, 3,5,18, Blocks.stonebrick, Blocks.stonebrick, false);
		// right wall
		fillWithBlocks(world, box, 21,1,8, 21,5,18, Blocks.stonebrick, Blocks.stonebrick, false);
		// pillars
		fillWithBlocks(world, box, 4,1,19, 4,5,19, Blocks.stonebrick, Blocks.stonebrick, false);
		fillWithBlocks(world, box, 5,1,20, 5,5,20, Blocks.stonebrick, Blocks.stonebrick, false);
		fillWithBlocks(world, box, 6,1,21, 6,5,21, Blocks.stonebrick, Blocks.stonebrick, false);
		fillWithBlocks(world, box, 20,1,19, 20,5,19, Blocks.stonebrick, Blocks.stonebrick, false);
		fillWithBlocks(world, box, 19,1,20, 19,5,20, Blocks.stonebrick, Blocks.stonebrick, false);
		fillWithBlocks(world, box, 18,1,21, 18,5,21, Blocks.stonebrick, Blocks.stonebrick, false);
		
		// back room 1st floor front wall
		fillWithBlocks(world, box, 7,1,13, 17,5,13, Blocks.stonebrick, Blocks.stonebrick, false);
		// back room 1st floor left wall
		fillWithBlocks(world, box, 7,1,14, 7,5,20, Blocks.stonebrick, Blocks.stonebrick, false);
		// back room 1st floor right wall
		fillWithBlocks(world, box, 16,1,14, 16,5,20, Blocks.stonebrick, Blocks.stonebrick, false);
		// back room 1st floor back wall
		fillWithBlocks(world, box, 7,1,21, 17,5,21, Blocks.stonebrick, Blocks.stonebrick, false);
		
		// front center 1st wall
		fillWithBlocks(world, box, 8,1,2, 16,5,2, Blocks.stonebrick, Blocks.stonebrick, false);
		// front center 2nd wall
		fillWithBlocks(world, box, 8,1,5, 16,5,5, Blocks.stonebrick, Blocks.stonebrick, false);
		
		// back room 2nd floor front wall
		fillWithBlocks(world, box, 8,6,12, 16,7,12, Blocks.stonebrick, Blocks.stonebrick, false);
		// back room 2nd floor left wall
		fillWithBlocks(world, box, 8,6,13, 8,7,20, Blocks.stonebrick, Blocks.stonebrick, false);
		// back room 2nd floor right wall
		fillWithBlocks(world, box, 11,6,13, 11,7,20, Blocks.stonebrick, Blocks.stonebrick, false);
		// back room 2nd floor back wall
		fillWithBlocks(world, box, 8,6,20, 16,7,20, Blocks.stonebrick, Blocks.stonebrick, false);

		// back rooms top level
		fillWithBlocks(world, box, 6,12,18, 6,12,21, Blocks.stonebrick, Blocks.stonebrick, false);
		fillWithBlocks(world, box, 7,12,18, 10,12,18, Blocks.stonebrick, Blocks.stonebrick, false);
		fillWithBlocks(world, box, 10,12,19, 10,12,21, Blocks.stonebrick, Blocks.stonebrick, false);
		fillWithBlocks(world, box, 13,12,18, 13,12,21, Blocks.stonebrick, Blocks.stonebrick, false);
		fillWithBlocks(world, box, 14,12,18, 17,12,18, Blocks.stonebrick, Blocks.stonebrick, false);
		fillWithBlocks(world, box, 17,12,19, 17,12,21, Blocks.stonebrick, Blocks.stonebrick, false);

		// back rooms level 10
		fillWithBlocks(world, box, 6,10,18, 6,10,21, Blocks.stonebrick, Blocks.stonebrick, false);
		fillWithBlocks(world, box, 7,10,18, 8,10,18, Blocks.stonebrick, Blocks.stonebrick, false);
		fillWithBlocks(world, box, 10,10,20, 10,10,21, Blocks.stonebrick, Blocks.stonebrick, false);
		fillWithBlocks(world, box, 13,10,20, 13,10,21, Blocks.stonebrick, Blocks.stonebrick, false);
		fillWithBlocks(world, box, 15,10,18, 17,10,18, Blocks.stonebrick, Blocks.stonebrick, false);
		fillWithBlocks(world, box, 17,10,19, 17,10,21, Blocks.stonebrick, Blocks.stonebrick, false);

		// back rooms level 9
		fillWithBlocks(world, box, 6,9,18, 6,9,21, Blocks.stonebrick, Blocks.stonebrick, false);
		fillWithBlocks(world, box, 7,9,18, 8,9,18, Blocks.stonebrick, Blocks.stonebrick, false);
		fillWithBlocks(world, box, 8,9,15, 10,9,17, Blocks.stonebrick, Blocks.stonebrick, false);
		fillWithBlocks(world, box, 8,9,14, 15,9,14, Blocks.stonebrick, Blocks.stonebrick, false);
		fillWithBlocks(world, box, 15,9,15, 15,9,18, Blocks.stonebrick, Blocks.stonebrick, false);
		fillWithBlocks(world, box, 16,9,18, 17,9,18, Blocks.stonebrick, Blocks.stonebrick, false);
		fillWithBlocks(world, box, 16,9,19, 17,9,21, Blocks.stonebrick, Blocks.stonebrick, false);
		fillWithBlocks(world, box, 10,9,20, 10,9,21, Blocks.stonebrick, Blocks.stonebrick, false);
		fillWithBlocks(world, box, 11,9,20, 13,9,20, Blocks.stonebrick, Blocks.stonebrick, false);
		fillWithBlocks(world, box, 13,9,21, 13,9,21, Blocks.stonebrick, Blocks.stonebrick, false);

		// Interior
//		fillWithAir(world, box, 1,7,1, 4,7,4);
//		fillWithBlocks(world, box, 1,7,1, 4,7,4, Blocks.cobblestone, Blocks.cobblestone, false);

		// torch on each corner, on top of a block
		// world, block, metadata, x, y, z, bounding box
//        placeBlockAtCurrentPosition(world, Blocks.torch, 0, 0,11,0, box);
//        placeBlockAtCurrentPosition(world, Blocks.torch, 0, 0,11,5, box);
//        placeBlockAtCurrentPosition(world, Blocks.torch, 0, 5,11,5, box);
//        placeBlockAtCurrentPosition(world, Blocks.torch, 0, 5,11,0, box);
        
        // Spawn guards on top:
        // world, box, x, y, z, number of villages
        spawnGuards(world, box, 2,11,2, 2);
        
        return true;
    }

    /**
     * Spawns a number of guards in this component. Parameters: world, component bounding box, x offset, y
     * offset, z offset, number of guards
     */
    protected void spawnGuards(World world, StructureBoundingBox box, int x, int y, int z, int numGuards) {
    	// Spawn guards
    	if (numGuards == 0)
    		return;
        int i1, xx, yy, zz;
		for (i1 = 0; i1 < numGuards; ++i1) {
            xx = this.getXWithOffset(x + i1, z);
            yy = this.getYWithOffset(y);
            zz = this.getZWithOffset(x + i1, z);

            if (!box.isVecInside(xx, yy, zz)) {
                return;
            }

			EntityGuard guard = EntityGuard.createGuardTypePerBiome(world, xx, zz);
			guard.setLocationAndAngles((double)xx + 0.5D, (double)yy, (double)zz + 0.5D, 0.0F, 0.0F);
            world.spawnEntityInWorld(guard);
        }
		
		// Spawn Baron
        xx = this.getXWithOffset(x + i1, z);
        yy = this.getYWithOffset(y);
        zz = this.getZWithOffset(x + i1, z);
        if (!box.isVecInside(xx, yy, zz)) {
            return;
        }
        if (EntityGuard.isBaronBiome(world, xx, zz)) {
        	EntityBaron baron = (EntityBaron) EntityList.createEntityByName(EntityBaron.getFullName(), world);
			baron.setLocationAndAngles((double)xx + 0.5D, (double)yy, (double)zz + 0.5D, 0.0F, 0.0F);
            world.spawnEntityInWorld(baron);
			LogHelper.info("VillageBaronCastle: Spawned Baron at: " + xx + " " + yy + " " + zz);
        }
    }

    // Helper functions
    
    /**
     * Clears out an area for building
     * @param World
     * @param xMin
     * @param zMin
     * @param xMax
     * @param zMax
     * @param StructureBoundingBox
     */
    private void clearBlocks(World world, int xMin, int zMin, int xMax, int zMax, StructureBoundingBox box) {
        for (int xx = xMin; xx <= xMax ; xx++){
            for (int zz = zMin; zz <= zMax; zz++){
            	clearBlock(world, xx,0,zz, box);
            }
        }
    }
    
    /**
     * Clears out an block at x,y,z for building
     * @param World
     * @param x
     * @param y Clears from y up, puts down base from y-1 down
     * @param z
     * @param StructureBoundingBox
     */
    private void clearBlock(World world, int x, int y, int z, StructureBoundingBox box) {
        clearCurrentPositionBlocksUpwards(world, x,y,z, box);
        this.func_151554_b(world, Blocks.stonebrick, 0, x,y-1,z, box);	
    }
    
	// Call from mod's init
	public static void init() {
		VillageKingCastle.addVillagePiece(VillageKingCastle.class, "ViKingCastle");
		KingCastleCreationHandler kingCastleCreator = new KingCastleCreationHandler();
		VillagerRegistry.instance().registerVillageCreationHandler(kingCastleCreator);
	}
}