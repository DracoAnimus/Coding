package net.wildbill22.draco.items.dragoneggs;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.wildbill22.draco.entities.dragons.EntityDracoAqua;
import net.wildbill22.draco.items.ModItems;
import net.wildbill22.draco.items.weapons.ModWeapons;

public class ItemWaterDragonEgg extends ItemDragonEgg {
	public static final String name = "waterDragonEgg";
	private final static String dragonName = EntityDracoAqua.name;

	public ItemWaterDragonEgg() {
		super(name, dragonName);
//		String dragonName = EntityDracoAqua.name;
//		this.addDragonFood(dragonName, ModItems.villagerHeart);
		this.addDragonFood(dragonName, ModItems.squid);
		this.addDragonFood(dragonName, Items.fish);
		this.addSwimmingUnderWater(dragonName);
		this.addCollideWithEntities(dragonName);
		this.addIsMadeOfWaterAbilities(dragonName);
		
		// Stuff from Mariculture
//		if (Core.isMCModLoaded) {
//			this.addDragonFood(dragonName, mariculture.core.items.ItemFood.getItemById(FoodMeta.CALAMARI));			
//			this.addDragonFood(dragonName, mariculture.core.items.ItemFood.getItemById(FoodMeta.KELP_WRAP));			
//		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
		list.add(StatCollector.translateToLocal("egg.wildbill22_draco_putThisEgg"));
		list.add(StatCollector.translateToLocal("egg.wildbill22_draco_youWillGetStaff")); 
		list.add(StatCollector.translateToLocal("egg.wildbill22_draco_intoWaterDragon"));
	}

	@Override
	public String getEggName() {
		return name;
	}

	@Override
	public Item getEggItem() {
		return ModItems.waterDragonEgg;
	}

	@Override
	public ItemStack getStaffItemStack() {
		return new ItemStack(ModWeapons.waterDragonStaff);
	}
}
