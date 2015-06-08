package net.wildbill22.draco.items.weapons.staffs;

import net.wildbill22.draco.entities.dragons.EntityDracoAqua;
import net.wildbill22.draco.items.dragoneggs.ItemWaterDragonEgg;

public class ItemWaterDragonStaff extends ItemDragonStaff {	
	public static final String name = "waterDragonStaff";
	
	public ItemWaterDragonStaff(ToolMaterial material) {
		super(material, name);
		abilities.addChangeForm(EntityDracoAqua.name, "Water dragon");
//		abilities.invisible();
		abilities.addTeleporting();
		abilities.addSpitBoilingWater();
	}
	
	// Used to find if egg is in hoard
	@Override
	public String getEggName() {
		return ItemWaterDragonEgg.name;
	}
}