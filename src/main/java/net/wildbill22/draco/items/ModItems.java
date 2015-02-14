package net.wildbill22.draco.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.wildbill22.draco.lib.Reference;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModItems extends Item {
	// All items in the mod:
	public static Item dragonScales;
	
	public static void preInit() {
		dragonScales = new ItemDragonScales("dragonScales", "dragonScales");
				
		GameRegistry.registerItem(dragonScales, "dragonScales");
	}

	/**
	 * Makes a item and sets most of its properties
	 * @param unlocalizedname
	 * @param texturename
	 */
	public ModItems(String unlocalizedname, String texturename){
		super();
		
		this.setCreativeTab(CreativeTabs.tabMisc);
		this.setUnlocalizedName(Reference.Unlocalized_Path + unlocalizedname);
		this.setTextureName(Reference.Texture_Path + texturename);
	}
}
