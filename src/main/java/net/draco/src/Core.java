package mod.draco_animus.main;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
	
@Mod(modid = "draco_animus", name = "Draco Animus", version = "v0.03")
public class Core {
		@EventHandler
	public static void PreInit(FMLPreInitializationEvent event) {
			Blocks.BlockInformation();
	Creative_Tab.OtherInfo();
				
	}
	@EventHandler
	public static void Init(FMLInitializationEvent event) {
		
	}
}
