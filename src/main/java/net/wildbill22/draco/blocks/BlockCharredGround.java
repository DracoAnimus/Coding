package net.wildbill22.draco.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.wildbill22.draco.Creative_Tab;
import net.wildbill22.draco.lib.Reference;

public class BlockCharredGround extends Block {

	public BlockCharredGround() {
		super(Material.grass);
		setBlockName("charredGround");
		setBlockTextureName(Reference.MODID + ":" + getUnlocalizedName().substring(5));
		setCreativeTab(Creative_Tab.TabDraco_Animus);
//		setStepSound(soundTypeStone);
		setHardness(1.0F);
		setResistance(3.0F);
//		setHarvestLevel("???", 0);
		}

}
