package net.wildbill22.draco.items.weapons;

import net.wildbill22.draco.entities.dragons.EntitySilverDragon;
import net.wildbill22.draco.items.ItemSilverDragonEgg;

public class ItemSilverDragonStaff extends ItemDragonStaff {	
	public static final String name = "silverDragonStaff";
	
	public ItemSilverDragonStaff(ToolMaterial material) {
		super(material, name);
		abilities.addChangeForm(EntitySilverDragon.name, "Silver dragon");
//		abilities.explosiveFireballs();
		abilities.addTeleporting();
//		abilities.addHaste();
		abilities.addFireBreathing();
//		abilities.addTeleportThroughWalls();
	}
	
	// Used to find if egg is in hoard
	@Override
	public String getEggName() {
		return ItemSilverDragonEgg.name;
	}
}