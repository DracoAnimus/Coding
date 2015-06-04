package net.wildbill22.draco.lib;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.client.registry.ClientRegistry;
import net.minecraft.client.settings.KeyBinding;

public class KeyBindings {
	public static KeyBinding staffChange;
	
	public static void preInit() {
		staffChange = new KeyBinding("key.staffChange", Keyboard.KEY_LMENU, "key.categories.gameplay");
		
		ClientRegistry.registerKeyBinding(staffChange);
	}
}
