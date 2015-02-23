package net.wildbill22.draco.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.common.ChestGenHooks;
import net.wildbill22.draco.Creative_Tab;
import net.wildbill22.draco.lib.REFERENCE;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModItems extends Item {
	// All items in the mod:
	public static Item dragonScales;
	public static Item spear;
	public static Item goldCoin;
	
	public static void preInit() {
		dragonScales = new ItemDragonScales("dragonScales", "dragonScales");
		spear = new ItemSpear("spear", "spear");
		goldCoin = new ItemGoldCoin("goldCoin", "goldCoin");
		
		// Is this the right location?
		GameRegistry.registerItem(dragonScales, "dragonScales");
		GameRegistry.registerItem(spear, "spear");
		GameRegistry.registerItem(goldCoin, "goldCoin");
	}

	public static void init() {
		ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH, new WeightedRandomChestContent(new ItemStack(goldCoin), 1, 3, 50));
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
