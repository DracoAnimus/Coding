package net.wildbill22.draco.items.weapons.staffs;

import net.wildbill22.draco.entities.dragons.EntityDracoMortem;
import net.wildbill22.draco.items.dragoneggs.ItemSkeletonDragonEgg;

public class ItemSkeletonDragonStaff extends ItemDragonStaff {	
	public static final String name = "skeletonDragonStaff";
	
	public ItemSkeletonDragonStaff(ToolMaterial material) {
		super(material, name);
//		abilities.addChangeForm(EntityDracoMortem.name, "Skeleton dragon");
		abilities.addChangeForm(EntityDracoMortem.name, "Draco Mortem");
		abilities.addInvisible();
		abilities.addRegeneration();
		abilities.addLocateStrongholds();
	}
	
	// Used to find if egg is in hoard
	@Override
	public String getEggName() {
		return ItemSkeletonDragonEgg.name;
	}
}