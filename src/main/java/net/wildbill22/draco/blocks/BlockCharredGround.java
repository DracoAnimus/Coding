package net.wildbill22.draco.blocks;

import net.minecraft.block.material.Material;

public class BlockCharredGround extends ModBlocks {
	public static final String name = "charredGround";

	public BlockCharredGround() {
		super(name, name, Material.grass);
//		setStepSound(soundTypeStone);
		setHardness(1.0F);
		setResistance(3.0F);
//		setHarvestLevel("???", 0);
		}

}
