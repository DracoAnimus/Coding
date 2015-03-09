package net.wildbill22.draco.blocks;

import net.minecraft.block.material.Material;

public class BlockScorchedCobblestone extends ModBlocks {
	public static final String name = "scorchedCobblestone";

	public BlockScorchedCobblestone() {
		super(name, name, Material.rock);
//		setStepSound(soundTypeStone);
		setHardness(5.0F);
		setResistance(3.0F);
//		setHarvestLevel("???", 0);
	}
}
