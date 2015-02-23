package net.wildbill22.draco.items;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemDragonScales extends ModItems {

	public ItemDragonScales(String unlocalizedname, String texturename) {
		super(unlocalizedname, texturename);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
		list.add("Description: TBD");
		list.add("Ability: ???");
	}
}
