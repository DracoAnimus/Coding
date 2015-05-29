package net.wildbill22.draco.items.weapons;

import net.wildbill22.draco.entities.dragons.EntityDracoMortem;
import net.wildbill22.draco.items.ItemSkeletonDragonEgg;

public class ItemSkeletonDragonStaff extends ItemDragonStaff {	
	public static final String name = "skeletonDragonStaff";
	
	public ItemSkeletonDragonStaff(ToolMaterial material) {
		super(material, name);
		abilities.addChangeForm(EntityDracoMortem.name, "Skeleton dragon");
		abilities.addInvisible();
		abilities.addRegeneration();
		abilities.addSoundWave();
	}
	
	// Used to find if egg is in hoard
	@Override
	public String getEggName() {
		return ItemSkeletonDragonEgg.name;
	}
}