package net.wildbill22.draco.generation.villageHandlers;

import java.util.List;
import java.util.Random;

import net.minecraft.world.gen.structure.StructureVillagePieces.PieceWeight;
import net.minecraft.world.gen.structure.StructureVillagePieces.Start;
import net.wildbill22.draco.generation.villageComponents.ComponentBakery;
import cpw.mods.fml.common.registry.VillagerRegistry.IVillageCreationHandler;

public class BakeryHandler implements IVillageCreationHandler {

    @Override
    public PieceWeight getVillagePieceWeight(Random random, int i) {
        return new PieceWeight(ComponentBakery.class, 10, 1);
    }

    @Override
    public Class<?> getComponentClass() {
        return ComponentBakery.class;
    }

    @SuppressWarnings("rawtypes")
	@Override
    public Object buildComponent(PieceWeight villagePiece, Start startPiece, List pieces, Random random, 
    		int x, int y, int z, int coordBaseMode, int p5) {
        return ComponentBakery.buildComponent(startPiece, pieces, random, x, y, z, coordBaseMode, p5);
    }

}
