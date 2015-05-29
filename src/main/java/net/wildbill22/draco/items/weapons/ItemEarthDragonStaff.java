package net.wildbill22.draco.items.weapons;

import net.wildbill22.draco.entities.dragons.EntityTerraDraco;
import net.wildbill22.draco.items.ItemEarthDragonEgg;

public class ItemEarthDragonStaff extends ItemDragonStaff {	
	public static final String name = "earthDragonStaff";
	
	public ItemEarthDragonStaff(ToolMaterial material) {
		super(material, name);
		abilities.addChangeForm(EntityTerraDraco.name, "Earth dragon");
		abilities.addSoundWave();
	}
	
	// Used to find if egg is in hoard
	@Override
	public String getEggName() {
		return ItemEarthDragonEgg.name;
	}
}