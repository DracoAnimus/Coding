package mod.draco_animus.main;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;

public class Draco_Animus_ModBlocks {
	
	public static Block charredGround;
 	public static Block scorchedCobblestone;	
	
	public static void init() {
			
		charredGround = new BlockCharredGround(Material.grass).setHardness(1.0F).setResistance(3.0F).setUnlocalizedName("charredGround").setCreativeTab(OthersDraco_Animus.TabDraco_Animus);
		scorchedCobblestone = new BlockScorchedCobblestone(Material.rock).setHardness(5.0F).setResistance(3.0F).setUnlocalizedName("scorchedCobblestone").setCreativeTab(OthersDraco_Animus.TabDraco_Animus);
				
		GameRegistry.registerBlock(charredGround, "charredGround");
		GameRegistry.registerBlock(scorchedCobblestone, "scorchedCobblestone");
	}
}
