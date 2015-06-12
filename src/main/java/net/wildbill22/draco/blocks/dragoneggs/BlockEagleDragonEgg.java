package net.wildbill22.draco.blocks.dragoneggs;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.wildbill22.draco.blocks.ModBlocks;
import net.wildbill22.draco.items.ModItems;

public class BlockEagleDragonEgg extends ModBlocks {
	public static final String name = "eagleDragonEggBlock";

	public BlockEagleDragonEgg() {
		super(name, name, Material.dragonEgg);
		setStepSound(soundTypePiston);
		setHardness(1.0F);
		setResistance(3.0F);
		setHarvestLevel("pickaxe", 1);
	}
	
    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)  {
        return ModItems.eagleDragonEgg;
    }
}
