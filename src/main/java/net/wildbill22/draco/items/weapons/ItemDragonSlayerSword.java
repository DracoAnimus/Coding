package net.wildbill22.draco.items.weapons;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.StatCollector;
import net.wildbill22.draco.Creative_Tab;
import net.wildbill22.draco.lib.REFERENCE;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemDragonSlayerSword extends ItemSword {
	public static final String name = "dragonSlayerSword";

	public ItemDragonSlayerSword(ToolMaterial material)
	{
		super(material);
		this.setCreativeTab(Creative_Tab.TabDraco_Animus);
		this.setUnlocalizedName(REFERENCE.Unlocalized_Path + name);
		this.setTextureName(REFERENCE.Texture_Path + name);
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
//		if (par2EntityLivingBase instanceof ModelCreeperDragon)
//			par3EntityLivingBase.attackEntityAsMob(par2EntityLivingBase);
//        int i = this.isTamed() ? 6 : 2;
//		//	entity.setFire(3);
//        return entity.attackEntityFrom(DamageSource.causeMobDamage(this), (float)i);
//		
//		return true;
//	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4)
	{
		list.add(StatCollector.translateToLocal("weapon.wildbill22_draco.dragonSlayerSword"));
		list.add(StatCollector.translateToLocalFormatted("weapon.wildbill22_draco.hitsRemaining", stack.getMaxDamage() - stack.getItemDamage()));
		list.add(StatCollector.translateToLocal("weapon.wildbill22_draco.moreDamageToDragons"));
	}
}
