package net.wildbill22.draco.generation.villageHandlers;

import java.util.List;
import java.util.Random;

import net.minecraft.util.MathHelper;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.structure.StructureVillagePieces;
import net.minecraft.world.gen.structure.StructureVillagePieces.PieceWeight;
import net.minecraft.world.gen.structure.StructureVillagePieces.Start;
import net.wildbill22.draco.biome.ModBiomes;
import cpw.mods.fml.common.registry.VillagerRegistry;

public class WoodHutCreationHandler implements VillagerRegistry.IVillageCreationHandler {
    
	@Override
    public Class<?> getComponentClass() {
        return StructureVillagePieces.House3.class;
    }

	@Override
	public PieceWeight getVillagePieceWeight(Random random, int i) {
		// First number is likelihood of spawning, second is maximum to spawn
        return new PieceWeight(StructureVillagePieces.WoodHut.class, 20, MathHelper.getRandomIntegerInRange(random, 2 + i, 4 + i));
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Object buildComponent(PieceWeight villagePiece, Start startPiece, List pieces, Random random, 
			int p1, int p2, int p3, int p4, int p5) {
//				BiomeGenBase biome = 
//						world.getBiomeGenForCoords(startPiece.getBoundingBox().minX, startPiece.getBoundingBox().minZ);
//		if (biome != ModBiomes.biomeCityPlains)
//			return null;
		return StructureVillagePieces.WoodHut.func_74908_a(startPiece, pieces, random, p1, p2, p3, p4, p5);
	}
	
	// Call from mod's init
	public static void init() {
 		WoodHutCreationHandler woodHutCreator = new WoodHutCreationHandler();
		VillagerRegistry.instance().registerVillageCreationHandler(woodHutCreator);
	}
}
