package net.wildbill22.draco.items.weapons;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.wildbill22.draco.Creative_Tab;
import net.wildbill22.draco.lib.REFERENCE;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemMace extends ItemAxe {	
	public static final String name = "mace";

	public ItemMace(ToolMaterial material)	{
		super(material);
		this.setCreativeTab(Creative_Tab.TabDraco_Animus);
		this.setUnlocalizedName(REFERENCE.Unlocalized_Path + name);
		this.setTextureName(REFERENCE.Texture_Path + name);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4){
		list.add("Mace.");
		list.add(stack.getMaxDamage() - stack.getItemDamage() + " Hits Remaining.");
		list.add("Causes hit damage.");
	}
}
