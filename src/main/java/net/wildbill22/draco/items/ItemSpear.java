package net.wildbill22.draco.items;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.wildbill22.draco.entities.EntitySpear;
import net.wildbill22.draco.lib.REFERENCE;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemSpear extends ModItems 
{	
	public static final int spearDamage = 4;
	
	public ItemSpear(String unlocalizedname, String texturename) {
		super(unlocalizedname, texturename);
		setMaxStackSize(64);
	}
	
	/**
	 * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
	 */
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		//Decrease an item from the stack because you just used it!
		if (!player.capabilities.isCreativeMode)
			--stack.stackSize;

		world.playSoundAtEntity(player, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
		
		// Only spawn new entities on the server!
		if (!world.isRemote) {
			/* This method in World spawn in an entity, you can use with anything that extends
			* the Entity class, in this case it's the EntitySpear class
			*/
			world.spawnEntityInWorld(new EntitySpear(world, player));

		}
		return stack;
	}
	
	
	// Add this if you want the "enchanted" look in the inventory
//	@Override
//	public boolean hasEffect(ItemStack itemStack, int pass) {
//		return true;
//	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
		list.add("Description: TBD");
		list.add("Ability: ???");
	}
}