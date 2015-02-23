package net.wildbill22.draco.blocks;

import net.minecraft.block.material.Material;
import net.wildbill22.draco.lib.REFERENCE;

public class BlockCharredGround extends ModBlocks {

	public BlockCharredGround(String unlocalizedname, String texturename) {
		super(unlocalizedname, texturename, Material.grass);
//		setStepSound(soundTypeStone);
		setHardness(1.0F);
		setResistance(3.0F);
//		setHarvestLevel("???", 0);
		}

}
