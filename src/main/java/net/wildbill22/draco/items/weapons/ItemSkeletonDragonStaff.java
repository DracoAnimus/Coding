package net.wildbill22.draco.items.weapons;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.wildbill22.draco.entities.dragons.EntityDracoMortem;
import net.wildbill22.draco.items.ItemSkeletonDragonEgg;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemSkeletonDragonStaff extends ItemDragonStaff {	
	public static final String name = "skeletonDragonStaff";
	
	public ItemSkeletonDragonStaff(ToolMaterial material) {
		super(material, name);
		abilities.changeForm(EntityDracoMortem.name);
		abilities.invisible();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
		list.add("Staff that allows you to change");
		list.add("from human to Skeleton dragon.");
		list.add("Use shift + right click to change modes.");
		list.add(stack.getMaxDamage() - stack.getItemDamage() + " Hits Remaining.");
		list.add(((ItemDragonStaff)stack.getItem()).field_150934_a + " Attack Damage.");
		list.add("Current ability: " + abilities.getModeName(stack) + "!");
	}

	// Used to find if egg is in hoard
	@Override
	public String getEggName() {
		return ItemSkeletonDragonEgg.name;
	}
}