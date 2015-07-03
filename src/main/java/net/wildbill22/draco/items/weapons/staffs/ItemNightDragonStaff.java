package net.wildbill22.draco.items.weapons.staffs;

import net.wildbill22.draco.entities.dragons.EntityDracoTenebrosus;
import net.wildbill22.draco.items.dragoneggs.ItemNightDragonEgg;

public class ItemNightDragonStaff extends ItemDragonStaff {	
	public static final String name = "nightDragonStaff";
	
	public ItemNightDragonStaff(ToolMaterial material) {
		super(material, name);
//		abilities.addChangeForm(EntityDracoTenebrosus.name, "Night dragon");
		abilities.addChangeForm(EntityDracoTenebrosus.name, "Draco Tenebrosus");
		abilities.addTeleportThroughWallsAtNight();
		abilities.addWitherEffect();
	}
	
	// Used to find if egg is in hoard
	@Override
	public String getEggName() {
		return ItemNightDragonEgg.name;
	}
}