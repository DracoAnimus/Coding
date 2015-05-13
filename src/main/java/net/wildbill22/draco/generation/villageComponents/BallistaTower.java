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
import net.wildbill22.draco.generation.villageHandlers.BallistaTowerCreationHandler;
import net.wildbill22.draco.lib.LogHelper;

public class BallistaTower extends StructureVillagePieces.Village
{
	private int averageGroundLevel = -1;
    private static final int HEIGHT = 9;

    public BallistaTower() {}
    
	public BallistaTower(StructureVillagePieces.Start startPiece, int type, Random random, StructureBoundingBox _boundingBox, int direction){
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
        StructureBoundingBox _boundingBox = StructureBoundingBox.getComponentToAddBoundingBox(x, y, z, 0, 0, 0, 5, 8, 5, direction);
        if (canVillageGoDeeper(_boundingBox)) { 
        	if (StructureComponent.findIntersecting(pieces, _boundingBox) == null) {
        		return new BallistaTower(startPiece, type, random, _boundingBox, direction);
        	}
        }
		return null;
	}
	
	// Only create the Guard Tower in biomes with Longbow or Crossbow guards
	@Override
	public boolean addComponentParts(World world, Random random, StructureBoundingBox box) {
        if (averageGroundLevel < 0){
    		BiomeGenBase biome = world.getBiomeGenForCoords(box.minX, box.minZ);
    		if (biome != ModBiomes.biomeCityPlains && biome != ModBiomes.biomeTownForest)
    			return false;

    		LogHelper.info("BallistaTower: Build ballista tower at: " + box.minX + ", " + box.minZ);
            averageGroundLevel = getAverageGroundLevel(world, box);
            if (averageGroundLevel < 0){
                return true;
            }
            boundingBox.offset(0, this.averageGroundLevel - boundingBox.maxY + HEIGHT - 2, 0);
        }
		
        // Clear out the area for the building and make a base
        for (int xx = 0; xx <= 5 ; xx++){
            for (int zz = 0; zz <= 5; zz++){
                clearCurrentPositionBlocksUpwards(world, xx,0,zz, box);
                this.func_151554_b(world, Blocks.stonebrick, 0, xx, -1, zz, box);
            }
        }
		
        // Level 0 - 2
        int lvl = 0;
		fillWithBlocks(world, box, 0,lvl,0, 1,2,0, Blocks.fence, Blocks.fence, false);
		fillWithBlocks(world, box, 4,lvl,0, 5,2,0, Blocks.fence, Blocks.fence, false);
		fillWithBlocks(world, box, 0,lvl,1, 0,2,1, Blocks.fence, Blocks.fence, false);
		fillWithBlocks(world, box, 5,lvl,1, 5,2,1, Blocks.fence, Blocks.fence, false);
		fillWithBlocks(world, box, 0,lvl,4, 0,2,4, Blocks.fence, Blocks.fence, false);
		fillWithBlocks(world, box, 5,lvl,4, 5,2,4, Blocks.fence, Blocks.fence, false);
		fillWithBlocks(world, box, 0,lvl,5, 1,2,5, Blocks.fence, Blocks.fence, false);
		fillWithBlocks(world, box, 4,lvl,5, 5,2,5, Blocks.fence, Blocks.fence, false);

        // Level 3 - 6
        lvl = 3;
		fillWithBlocks(world, box, 0,lvl,0, 5,6,0, Blocks.fence, Blocks.fence, false);
		fillWithBlocks(world, box, 0,lvl,1, 0,6,4, Blocks.fence, Blocks.fence, false);
		fillWithBlocks(world, box, 5,lvl,1, 5,6,4, Blocks.fence, Blocks.fence, false);
		fillWithBlocks(world, box, 0,lvl,5, 5,6,5, Blocks.fence, Blocks.fence, false);

        // Top floor
        lvl = 7;
		fillWithBlocks(world, box, 0,lvl,0, 5,lvl,5, Blocks.planks, Blocks.planks, false);

        // Fence
        lvl = 8;
		fillWithBlocks(world, box, 0,lvl,0, 5,lvl,0, Blocks.fence, Blocks.fence, false);
		fillWithBlocks(world, box, 0,lvl,1, 0,lvl,4, Blocks.fence, Blocks.fence, false);
		fillWithBlocks(world, box, 5,lvl,1, 5,lvl,4, Blocks.fence, Blocks.fence, false);
		fillWithBlocks(world, box, 0,lvl,5, 5,lvl,5, Blocks.fence, Blocks.fence, false);
	       
        // Spawn ballista on top:
        // world, box, x, y, z
        spawnBallista(world, box, 2,8,3);
        
        return true;
    }

    /**
     * Spawns a ballista in this component.
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
		BallistaTower.addVillagePiece(BallistaTower.class, "ViBallTwr");
		BallistaTowerCreationHandler ballistaTower = new BallistaTowerCreationHandler();
		VillagerRegistry.instance().registerVillageCreationHandler(ballistaTower);
	}
}