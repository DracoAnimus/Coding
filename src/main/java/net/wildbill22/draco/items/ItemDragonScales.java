package net.wildbill22.draco.items;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemDragonScales extends ModItems {
	public static final String name = "dragonScales";

	public ItemDragonScales() {
		super(name, name);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
		list.add(StatCollector.translateToLocal("item.wildbill22_draco_dragonScales.info1"));
		list.add(StatCollector.translateToLocal("item.wildbill22_draco_dragonScales.info2"));
	}
}
