package net.wildbill22.draco.generation.villageComponents;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.common.registry.VillagerRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureVillagePieces;
import net.wildbill22.draco.biome.ModBiomes;
import net.wildbill22.draco.entities.hostile.EntityBallista;
import net.wildbill22.draco.generation.villageHandlers.TestBallistaTowerCreationHandler;
import net.wildbill22.draco.lib.LogHelper;

public class TestBallistaTower extends StructureVillagePieces.Village
{
	private int averageGroundLevel = -1;
    private static final int HEIGHT = 10;

    public TestBallistaTower() {}
    
	public TestBallistaTower(StructureVillagePieces.Start startPiece, int type, Random random, StructureBoundingBox _boundingBox, int direction){
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
        StructureBoundingBox _boundingBox = StructureBoundingBox.getComponentToAddBoundingBox(x, y, z, 0, 0, 0, 5, 10, 5, direction);
        if (canVillageGoDeeper(_boundingBox)) { 
        	if (StructureComponent.findIntersecting(pieces, _boundingBox) == null) {
        		return new TestBallistaTower(startPiece, type, random, _boundingBox, direction);
        	}
        }
		return null;
	}
	
	// Only create the Guard Tower in biomes with Longbow or Crossbow guards
	@Override
	public boolean addComponentParts(World world, Random random, StructureBoundingBox box) {
		BiomeGenBase biome = world.getBiomeGenForCoords(box.minX, box.minZ);
		if (biome != ModBiomes.biomeCityPlains && biome != ModBiomes.biomeTownForest)
			return true;

		LogHelper.info("TestBallistaTower: Build ballista tower at: " + box.minX + ", " + box.minZ);
        if (averageGroundLevel < 0){
            averageGroundLevel = getAverageGroundLevel(world, box);
            if (averageGroundLevel < 0){
                return true;
            }
            boundingBox.offset(0, this.averageGroundLevel - boundingBox.maxY + HEIGHT - 2, 0);
        }
		
        // Clear out the area for the building and make a base
        for (int xx = 0; xx <=5 ; xx++){
            for (int zz = 0; zz <= 5; zz++){
                clearCurrentPositionBlocksUpwards(world, xx,0,zz, box);
                this.func_151554_b(world, Blocks.cobblestone, 0, xx, -1, zz, box);
            }
        }
		
        // Top floor
		fillWithBlocks(world, box, 0,9,0, 5,9,5, Blocks.cobblestone, Blocks.cobblestone, false);
        
		// walls
		fillWithBlocks(world, box, 0,1,0, 5,8,0, Blocks.cobblestone, Blocks.cobblestone, false);
		fillWithBlocks(world, box, 5,1,0, 5,8,5, Blocks.cobblestone, Blocks.cobblestone, false);
		fillWithBlocks(world, box, 0,1,5, 5,8,5, Blocks.cobblestone, Blocks.cobblestone, false);
		fillWithBlocks(world, box, 0,1,0, 0,8,5, Blocks.cobblestone, Blocks.cobblestone, false);
		
		// Interior
		fillWithBlocks(world, box, 1,7,1, 4,7,4, Blocks.cobblestone, Blocks.cobblestone, false);

		// Corner blocks
		fillWithBlocks(world, box, 0,10,0, 5,10,0, Blocks.cobblestone, Blocks.cobblestone, false);
		fillWithBlocks(world, box, 5,10,1, 5,10,5, Blocks.cobblestone, Blocks.cobblestone, false);
		fillWithBlocks(world, box, 0,10,5, 4,10,5, Blocks.cobblestone, Blocks.cobblestone, false);
		fillWithBlocks(world, box, 0,10,1, 0,10,4, Blocks.cobblestone, Blocks.cobblestone, false);
		
		// torch on each corner, on top of a block
		// world, block, metadata, x, y, z, bounding box
        placeBlockAtCurrentPosition(world, Blocks.torch, 0, 0,11,0, box);
        placeBlockAtCurrentPosition(world, Blocks.torch, 0, 0,11,5, box);
        placeBlockAtCurrentPosition(world, Blocks.torch, 0, 5,11,5, box);
        placeBlockAtCurrentPosition(world, Blocks.torch, 0, 5,11,0, box);
        
        // Spawn guards on top:
        // world, box, x, y, z, number of villages
        spawnBallista(world, box, 2,11,3);
        
        return true;
    }

    /**
     * Spawns a number of guards in this component.
     * 
     * @param World
     * @param StructureBoundingBox
     * @param x
     * @param y
     * @param z
     */
    protected void spawnBallista(World world, StructureBoundingBox box, int x, int y, int z) {
        int xx, yy, zz;
        xx = this.getXWithOffset(x, z);
        yy = this.getYWithOffset(y);
        zz = this.getZWithOffset(x, z);

        if (!box.isVecInside(xx, yy, zz)) {
            return;
        }

		EntityBallista ballista = new EntityBallista(world);
		ballista.setLocationAndAngles((double)xx + 0.5D, (double)yy, (double)zz + 0.5D, 0.0F, 0.0F);
        world.spawnEntityInWorld(ballista);
     }

	// Call from mod's init
	public static void init() {
		TestBallistaTower.addVillagePiece(TestBallistaTower.class, "ViBallTwr");
		TestBallistaTowerCreationHandler ballistaTower = new TestBallistaTowerCreationHandler();
		VillagerRegistry.instance().registerVillageCreationHandler(ballistaTower);
	}
}