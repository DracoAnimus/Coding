package net.wildbill22.draco.items;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.wildbill22.draco.entities.dragons.EntityAquaDraco;
import net.wildbill22.draco.items.weapons.ModWeapons;

public class ItemWaterDragonEgg extends ItemDragonEgg {
	public static final String name = "waterDragonEgg";

	public ItemWaterDragonEgg() {
		super(name);
		this.addDragonFood(EntityAquaDraco.name, ModItems.villagerHeart);
		this.addDragonFood(EntityAquaDraco.name, ModItems.squid);
		this.addSwimmingUnderWater(EntityAquaDraco.name);
		this.addCollideWithEntities(EntityAquaDraco.name);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
		list.add("Put this egg in the hoard");
		list.add("You will get a staff that turns"); 
		list.add("you into a Water Dragon!");
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
