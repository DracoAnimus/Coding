package net.wildbill22.draco.items.weapons.staffs;

import net.wildbill22.draco.entities.dragons.EntityDracoIgnis;
import net.wildbill22.draco.items.dragoneggs.ItemFireDragonEgg;

public class ItemFireDragonStaff extends ItemDragonStaff {	
	public static final String name = "fireDragonStaff";
	
	public ItemFireDragonStaff(ToolMaterial material) {
		super(material, name);
//		abilities.addChangeForm(EntityDracoIgnis.name, "Fire dragon");
		abilities.addChangeForm(EntityDracoIgnis.name, "Draco Ignis");
		abilities.addFireBreathing();
	}
	
	// Used to find if egg is in hoard
	@Override
	public String getEggName() {
		return ItemFireDragonEgg.name;
	}
}