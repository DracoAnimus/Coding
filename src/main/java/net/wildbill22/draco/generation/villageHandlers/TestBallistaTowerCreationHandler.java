package net.wildbill22.draco.generation.villageHandlers;

import java.util.List;
import java.util.Random;

import net.minecraft.util.MathHelper;
import net.minecraft.world.gen.structure.StructureVillagePieces.PieceWeight;
import net.minecraft.world.gen.structure.StructureVillagePieces.Start;
import net.wildbill22.draco.generation.villageComponents.TestBallistaTower;
import cpw.mods.fml.common.registry.VillagerRegistry;

public class TestBallistaTowerCreationHandler implements VillagerRegistry.IVillageCreationHandler {
    
	@Override
    public Class<?> getComponentClass() {
        return TestBallistaTower.class;
    }

	@Override
	public PieceWeight getVillagePieceWeight(Random random, int i) {
		// First number is likelihood of spawning, second is maximum to spawn
        return new PieceWeight(TestBallistaTower.class, 25, MathHelper.getRandomIntegerInRange(random, 0 + i, 1 + i));
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Object buildComponent(PieceWeight villagePiece, Start startPiece, List pieces, Random random, 
			int p1, int p2, int p3, int p4, int p5) {
		return TestBallistaTower.buildComponent(startPiece, pieces, random, p1, p2, p3, p4, p5);
	}  
}
