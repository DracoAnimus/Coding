package net.wildbill22.draco.items.dragoneggs;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.wildbill22.draco.entities.dragons.EntitySilverDragon;
import net.wildbill22.draco.items.ModItems;
import net.wildbill22.draco.items.weapons.ModWeapons;

public class ItemSilverDragonEgg extends ItemDragonEgg {
	public static final String name = "silverDragonEgg";
	private final static String dragonName = EntitySilverDragon.name;

	public ItemSilverDragonEgg() {
		super(name, dragonName);
//		String dragonName = EntitySilverDragon.name;
//		this.addDragonFood(dragonName, ModItems.villagerHeart);
		this.addDragonFood(dragonName, Items.rotten_flesh);
//		this.addDragonFood(dragonName, Items.iron_ingot);
		this.addImmuneToPoison(dragonName);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
		list.add(StatCollector.translateToLocal("egg.wildbill22_draco_putThisEgg"));
		list.add(StatCollector.translateToLocal("egg.wildbill22_draco_youWillGetStaff")); 
		list.add(StatCollector.translateToLocal("egg.wildbill22_draco_intoSilverDragon"));
	}

	@Override
	public String getEggName() {
		return name;
	}

	@Override
	public Item getEggItem() {
		return ModItems.silverDragonEgg;
	}

	@Override
	public ItemStack getStaffItemStack() {
		return new ItemStack(ModWeapons.silverDragonStaff);
	}
}
