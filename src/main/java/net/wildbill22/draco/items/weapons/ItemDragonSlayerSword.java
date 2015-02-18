package net.wildbill22.draco.items.weapons;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.wildbill22.draco.Creative_Tab;
import net.wildbill22.draco.lib.Reference;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemDragonSlayerSword extends ItemSword
{	
	public ItemDragonSlayerSword(ToolMaterial material, String name)
	{
		super(material);
		this.setCreativeTab(Creative_Tab.TabDraco_Animus);
		this.setUnlocalizedName(Reference.Unlocalized_Path + name);
		this.setTextureName(Reference.Texture_Path + name);
	}
	
//	@Override
//	@SideOnly(Side.CLIENT)
//	public void registerIcons(IIconRegister par1IconRegister)
//	{
//		this.itemIcon = par1IconRegister.registerIcon(Reference.MODID + ":" + getUnlocalizedName().substring(5));
//	}
	
//	public boolean hitEntity(ItemStack par1ItemStack, EntityLivingBase par2EntityLivingBase, EntityLivingBase par3EntityLivingBase)
//	{
//		par1ItemStack.damageItem(1, par3EntityLivingBase);
//		
//		par2EntityLivingBase.setFire(3);
//		
//		return true;
//	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4)
	{
		list.add("Description, TBD");
		list.add(stack.getMaxDamage() - stack.getItemDamage() + " Hits Remaining");
		list.add("Ability: ???");
	}
}
