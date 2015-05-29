package net.wildbill22.draco.items.weapons;

import net.wildbill22.draco.entities.dragons.EntityGoldDragon;
import net.wildbill22.draco.items.ItemGoldDragonEgg;

public class ItemGoldDragonStaff extends ItemDragonStaff {	
	public static final String name = "goldDragonStaff";
	
	public ItemGoldDragonStaff(ToolMaterial material) {
		super(material, name);
		abilities.addChangeForm(EntityGoldDragon.name, "Gold dragon");
		abilities.addExplosiveFireballs();
		abilities.addGoldenEye();
	}
	
	// Used to find if egg is in hoard
	@Override
	public String getEggName() {
		return ItemGoldDragonEgg.name;
	}
}