package net.wildbill22.draco.api;

import cpw.mods.fml.common.Optional;
import cpw.mods.fml.common.Optional.Method;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
///import joshie.enchiridion.api.IBookHandler;
import net.wildbill22.draco.lib.REFERENCE;

@Optional.Interface(iface="joshie.enchiridion.api.IBookHandler", modid="Enchiridion2", striprefs=true)
public class BookHandler implements joshie.enchiridion.api.IBookHandler {

	@Method(modid=REFERENCE.EAPIMODID)
	@Override
	public String getName() {
		return "BookHandler";
	}

	@Method(modid=REFERENCE.EAPIMODID)
	@Override
	public void handle(ItemStack stack, World world, EntityPlayer player) {
        stack.getItem().onItemRightClick(stack, world, player);
	}

}
