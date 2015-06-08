package net.wildbill22.draco.items.weapons.staffs;

import net.wildbill22.draco.entities.dragons.EntityDracoTerra;
import net.wildbill22.draco.items.dragoneggs.ItemEarthDragonEgg;

public class ItemEarthDragonStaff extends ItemDragonStaff {	
	public static final String name = "earthDragonStaff";
	
	public ItemEarthDragonStaff(ToolMaterial material) {
		super(material, name);
		abilities.addChangeForm(EntityDracoTerra.name, "Earth dragon");
		abilities.addSoundWave();
	}
	
	// Used to find if egg is in hoard
	@Override
	public String getEggName() {
		return ItemEarthDragonEgg.name;
	}
}