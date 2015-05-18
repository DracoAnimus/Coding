package net.wildbill22.draco.items;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemGoldDragonStaff extends ItemDragonStaff {	
	public static final String name = "goldDragonStaff";
	
	public ItemGoldDragonStaff() {
		super(name);
		addMode(GOLDDRAGON_CHANGE, "Change between Gold Dragon and Human");
		addMode(EXPLOSIVE_FIREBALLS, "Spawn Explosive Fireballs");
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
		list.add("Description: Staff that allows you to change from human to Gold dragon.");
		list.add("Use shift + right click to change modes.");
		list.add("Current ability: " + getModeName(stack) + "!");
	}
}