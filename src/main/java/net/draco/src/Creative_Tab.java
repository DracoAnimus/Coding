package mod.draco_animus.main;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class Creative_Tab {

	public static CreativeTabs TabDraco_Animus = new CreativeTabs("TabDraco_Animus"){@Override public Item getTabIconItem() {return Item.getItemFromBlock (Blocks.dragon_egg);}};
	public static void OtherInfo() {
		
	}

}
