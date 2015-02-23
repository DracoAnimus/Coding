package net.wildbill22.draco.blocks;

import net.minecraft.block.material.Material;
import net.wildbill22.draco.lib.REFERENCE;

public class BlockScorchedCobblestone extends ModBlocks {

	public BlockScorchedCobblestone(String unlocalizedname, String texturename) {
		super(unlocalizedname, texturename, Material.rock);
//		setStepSound(soundTypeStone);
		setHardness(5.0F);
		setResistance(3.0F);
//		setHarvestLevel("???", 0);
	}
}
