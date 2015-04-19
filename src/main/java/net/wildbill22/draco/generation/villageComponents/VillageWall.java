package net.wildbill22.draco.generation.villageComponents;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureVillagePieces;
import net.wildbill22.draco.lib.LogHelper;

public class VillageWall extends StructureVillagePieces.Village
{
	private int averageGroundLevel = -1;
    private static final int HEIGHT = 10;

    public VillageWall() {}
    
	public VillageWall(StructureVillagePieces.Start startPiece, int type, Random random, StructureBoundingBox _boundingBox, int direction){
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
        StructureBoundingBox _boundingBox = StructureBoundingBox.getComponentToAddBoundingBox(x, y, z, 0, 0, 0, 14, 10, 8, direction);
        if(canVillageGoDeeper(_boundingBox)){ 
        	if(StructureComponent.findIntersecting(pieces, _boundingBox) == null){
        		return new VillageWall(startPiece, type, random, _boundingBox, direction);
        	}
        }
		return null;
	}
	
	// FIXME: This is not going to work!
	@Override
	public boolean addComponentParts(World world, Random random, StructureBoundingBox box) {
		LogHelper.info("VillageWall: Build wall at: " + box.minX + ", " + box.minZ);
        if (averageGroundLevel < 0){
            averageGroundLevel = getAverageGroundLevel(world, box);
            if (averageGroundLevel < 0){
                return true;
            }
            boundingBox.offset(0, this.averageGroundLevel - boundingBox.maxY + HEIGHT - 2, 0);
        }

        Block block = this.func_151558_b(Blocks.cobblestone_wall, 0);

        for (int x = this.boundingBox.minX; x <= this.boundingBox.maxX; ++x) {
            for (int z = this.boundingBox.minZ; z <= this.boundingBox.maxZ; ++z) {
                if (box.isVecInside(x, 64, z)) {
                    int y = world.getTopSolidOrLiquidBlock(x, z) - 1;
                    world.setBlock(x, y, z, block, 0, 2);
                    world.setBlock(x, y+1, z, block, 0, 2);
                    world.setBlock(x, y+2, z, block, 0, 2);
                    world.setBlock(x, y+3, z, block, 0, 2);
                }
            }
        }

        return true;
    }

	// Call from mod's init
	public static void init() {
		VillageWall.addVillagePiece(VillageWall.class, "ViWall");
// 		WallCreationHandler wallCreator = new WallCreationHandler();
//		VillagerRegistry.instance().registerVillageCreationHandler(wallCreator);

	}
}