package net.wildbill22.draco.items.weapons;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.wildbill22.draco.entities.dragons.EntityGoldDragon;
import net.wildbill22.draco.items.ItemGoldDragonEgg;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemGoldDragonStaff extends ItemDragonStaff {	
	public static final String name = "goldDragonStaff";
	
	public ItemGoldDragonStaff(ToolMaterial material) {
		super(material, name);
		abilities.changeForm(EntityGoldDragon.name);
		abilities.explosiveFireballs();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
		list.add("Staff that allows you to change");
		list.add("from human to Gold dragon.");
		list.add("Use shift + right click to change modes.");
		list.add(stack.getMaxDamage() - stack.getItemDamage() + " Hits Remaining");
		list.add(((ItemDragonStaff)stack.getItem()).field_150934_a + " Attack Damage");
		list.add("Current ability: " + abilities.getModeName(stack) + "!");
	}

	// Used to find if egg is in hoard
	@Override
	public String getEggName() {
		return ItemGoldDragonEgg.name;
	}
}