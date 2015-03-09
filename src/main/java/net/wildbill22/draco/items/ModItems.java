package net.wildbill22.draco.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.common.ChestGenHooks;
import net.minecraftforge.common.util.EnumHelper;
import net.wildbill22.draco.Creative_Tab;
import net.wildbill22.draco.lib.REFERENCE;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModItems extends Item {
	//Tool Materials
	static ToolMaterial SPEAR = EnumHelper.addToolMaterial("SPEAR", 3, 500, 9.5F, 3.0F, 10);

	// All items in the mod:
	public static Item dragonScales;
	public static Item spear;
	public static Item goldCoin;
	public static Item fireball;
	public static Item explosiveFireball;
	
	public static void preInit() {
		dragonScales = new ItemDragonScales();
		spear = new ItemSpear();
		goldCoin = new ItemGoldCoin();
		fireball = new ItemMyFireball();
		explosiveFireball = new ItemMyExplosive();
		
		// Is this the right location? (Yes)
		GameRegistry.registerItem(dragonScales, ItemDragonScales.name);
		GameRegistry.registerItem(goldCoin, ItemGoldCoin.name);
		GameRegistry.registerItem(spear, ItemSpear.name);
		GameRegistry.registerItem(fireball, ItemMyFireball.name);
		GameRegistry.registerItem(explosiveFireball, ItemMyExplosive.name);
	}

	public static void init() {
		ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH, new WeightedRandomChestContent(new ItemStack(goldCoin), 1, 5, 50));
	}
	
	/**
	 * Makes a item and sets most of its properties
	 * @param unlocalizedname
	 * @param texturename
	 */
	public ModItems(String unlocalizedname, String texturename){
		super();
		
		this.setCreativeTab(Creative_Tab.TabDraco_Animus);
		this.setUnlocalizedName(REFERENCE.Unlocalized_Path + unlocalizedname);
		this.setTextureName(REFERENCE.Texture_Path + texturename);
	}
	
	/**
	 * Makes a item and sets most of its properties
	 * Use this one when you want to use registerIcons
	 * @param unlocalizedname
	 */
	public ModItems(String unlocalizedname) {
		super();		

		this.setCreativeTab(Creative_Tab.TabDraco_Animus);
		this.setUnlocalizedName(REFERENCE.Unlocalized_Path + unlocalizedname);
	}
	
	// IconRegister renamed to IIconRegister
//	@Override
//	@SideOnly(Side.CLIENT)
//	public void registerIcons(IIconRegister iconRegister) {
//		int beginIndex = getUnlocalizedName().indexOf('_');
//		itemIcon = iconRegister.registerIcon(Reference.Texture_Path + getUnlocalizedName().substring(beginIndex + 1).toLowerCase());
//	}
}
