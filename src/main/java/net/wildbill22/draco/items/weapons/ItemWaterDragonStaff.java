package net.wildbill22.draco.items.weapons;

import net.wildbill22.draco.entities.dragons.EntityAquaDraco;
import net.wildbill22.draco.items.ItemWaterDragonEgg;

public class ItemWaterDragonStaff extends ItemDragonStaff {	
	public static final String name = "waterDragonStaff";
	
	public ItemWaterDragonStaff(ToolMaterial material) {
		super(material, name);
		abilities.addChangeForm(EntityAquaDraco.name, "Water dragon");
//		abilities.invisible();
		abilities.addTeleporting();
	}
	
	// Used to find if egg is in hoard
	@Override
	public String getEggName() {
		return ItemWaterDragonEgg.name;
	}
}