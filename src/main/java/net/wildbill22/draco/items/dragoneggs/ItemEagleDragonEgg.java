package net.wildbill22.draco.items.dragoneggs;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.wildbill22.draco.entities.dragons.EntityDracoAquila;
import net.wildbill22.draco.items.ModItems;
import net.wildbill22.draco.items.weapons.ModWeapons;

public class ItemEagleDragonEgg extends ItemDragonEgg {
	public static final String name = "eagleDragonEgg";
	private final static String dragonName = EntityDracoAquila.name;

	public ItemEagleDragonEgg() {
		super(name, dragonName);
//		String dragonName = EntityDracoAquila.name;
		Abilities.addAbility(dragonName, Abilities.EAGLEDRAGON);
//		this.addDragonFood(dragonName, ModItems.villagerHeart);
		this.addDragonFood(dragonName, Items.beef);
		this.addDragonFood(dragonName, Items.cooked_beef);
		this.addDragonFood(dragonName, Items.porkchop);
		this.addDragonFood(dragonName, Items.cooked_porkchop);
		this.addDragonFood(dragonName, Items.spider_eye);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
		list.add(StatCollector.translateToLocal("egg.wildbill22_draco_putThisEgg"));
		list.add(StatCollector.translateToLocal("egg.wildbill22_draco_youWillGetStaff")); 
		list.add(StatCollector.translateToLocal("egg.wildbill22_draco_intoEagleDragon"));
	}
	
	@Override
	public String getEggName() {
		return name;
	}

	@Override
	public Item getEggItem() {
		return ModItems.eagleDragonEgg;
	}

	@Override
	public ItemStack getStaffItemStack() {
		return new ItemStack(ModWeapons.eagleDragonStaff);
	}
}
