package net.wildbill22.draco.generation.villageHandlers;

import java.util.List;
import java.util.Random;

import net.minecraft.world.gen.structure.StructureVillagePieces.PieceWeight;
import net.minecraft.world.gen.structure.StructureVillagePieces.Start;
import net.wildbill22.draco.generation.villageComponents.VillageKingCastle;
import cpw.mods.fml.common.registry.VillagerRegistry;

public class KingCastleCreationHandler implements VillagerRegistry.IVillageCreationHandler {
    
	@Override
    public Class<?> getComponentClass() {
        return VillageKingCastle.class;
    }

	@Override
	public PieceWeight getVillagePieceWeight(Random random, int i) {
		// First number is likelihood of spawning, second is maximum to spawn
        return new PieceWeight(VillageKingCastle.class, 25, 1);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Object buildComponent(PieceWeight villagePiece, Start startPiece, List pieces, Random random, 
			int p1, int p2, int p3, int p4, int p5) {
		return VillageKingCastle.buildComponent(startPiece, pieces, random, p1, p2, p3, p4, p5);
	}  
}
