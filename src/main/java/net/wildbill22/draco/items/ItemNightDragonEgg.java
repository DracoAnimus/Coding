package net.wildbill22.draco.items;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.wildbill22.draco.entities.dragons.EntityDracoTenebrosus;
import net.wildbill22.draco.items.weapons.ModWeapons;

public class ItemNightDragonEgg extends ItemDragonEgg {
	public static final String name = "nightDragonEgg";

	public ItemNightDragonEgg() {
		super(name);
		String dragonName = EntityDracoTenebrosus.name;
		this.addDragonFood(dragonName, ModItems.villagerHeart);
		this.addDragonFood(dragonName, Items.beef);
		this.addDragonFood(dragonName, Items.cooked_beef);
		this.addNightDragonAbilities(dragonName);
		this.addInvisibleInTheDark(dragonName);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
		list.add("Put this egg in the hoard");
		list.add("You will get a staff that turns"); 
		list.add("you into an Night Dragon!");
	}
	
	@Override
	public String getEggName() {
		return name;
	}

	@Override
	public Item getEggItem() {
		return ModItems.nightDragonEgg;
	}

	@Override
	public ItemStack getStaffItemStack() {
		return new ItemStack(ModWeapons.nightDragonStaff);
	}
}
