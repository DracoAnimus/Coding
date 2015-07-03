package net.wildbill22.draco.items.weapons.staffs;

import net.wildbill22.draco.entities.dragons.EntitySilverDragon;
import net.wildbill22.draco.items.dragoneggs.ItemSilverDragonEgg;

public class ItemSilverDragonStaff extends ItemDragonStaff {	
	public static final String name = "silverDragonStaff";
	
	public ItemSilverDragonStaff(ToolMaterial material) {
		super(material, name);
//		abilities.addChangeForm(EntitySilverDragon.name, "Silver dragon");
		abilities.addChangeForm(EntitySilverDragon.name, "Draco Argentum");
		abilities.addTeleporting();
		abilities.addPoison();
	}
	
	// Used to find if egg is in hoard
	@Override
	public String getEggName() {
		return ItemSilverDragonEgg.name;
	}
}