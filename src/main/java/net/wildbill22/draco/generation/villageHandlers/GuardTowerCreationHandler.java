package net.wildbill22.draco.generation.villageHandlers;

import java.util.List;
import java.util.Random;

import net.minecraft.util.MathHelper;
import net.minecraft.world.gen.structure.StructureVillagePieces.PieceWeight;
import net.minecraft.world.gen.structure.StructureVillagePieces.Start;
import net.wildbill22.draco.generation.villageComponents.VillageGuardTower;
import cpw.mods.fml.common.registry.VillagerRegistry;

public class GuardTowerCreationHandler implements VillagerRegistry.IVillageCreationHandler {
    
	@Override
    public Class<?> getComponentClass() {
        return VillageGuardTower.class;
    }

	@Override
	public PieceWeight getVillagePieceWeight(Random random, int i) {
//        return new PieceWeight(VillageTavern.class, 4, MathHelper.getRandomIntegerInRange(random, 0, 1));
		// First number is likelihood of spawning, second is maximum to spawn
        return new PieceWeight(VillageGuardTower.class, 15, MathHelper.getRandomIntegerInRange(random, 1 + i, 1 + i));
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Object buildComponent(PieceWeight villagePiece, Start startPiece, List pieces, Random random, 
			int p1, int p2, int p3, int p4, int p5) {
		return VillageGuardTower.buildComponent(startPiece, pieces, random, p1, p2, p3, p4, p5);
	}  
}
