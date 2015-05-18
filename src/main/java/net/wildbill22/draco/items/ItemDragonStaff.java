package net.wildbill22.draco.items;

import java.util.Map;

import com.google.common.collect.Maps;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import net.wildbill22.draco.entities.EntityMyExplosive;
import net.wildbill22.draco.entities.EntityMyFireball;
import net.wildbill22.draco.entities.dragons.EntityDracoMortem;
import net.wildbill22.draco.entities.dragons.EntitySilverDragon;
import net.wildbill22.draco.entities.player.DragonPlayer;
import net.wildbill22.draco.lib.LogHelper;

public class ItemDragonStaff extends ModItems {	
	private Map<Integer, String> staffModes = Maps.newHashMap();
//	private int NUM_MODES;
	protected int FIREBALLS = 0;  // Keep this first
	protected int SILVERDRAGON_CHANGE = 1;
	protected int SKELETONDRAGON_CHANGE = 2;
	protected int GOLDDRAGON_CHANGE = 3;
	protected int EXPLOSIVE_FIREBALLS = 4;
	protected int INVISIBLE = 5;
	protected int NIGHTVISION = 6;
	protected int WATERBREATHING = 7;
	protected int NUM_MODES = 7;

	public ItemDragonStaff(String name) {
		super(name, name);
		setMaxStackSize(1);
//		NUM_MODES = 0;
//		FIREBALLS = addMode("Spawns Fireballs");
		addMode(FIREBALLS, "Spawns Fireballs");
	}
	
	/**
	 * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
	 */
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		// Only spawn new entities on the server!
		if (!world.isRemote) {
			if (player.isSneaking()) {
				nextMode(stack);
				player.addChatMessage(new ChatComponentText("Staff in mode: " + getModeName(stack) + "!"));
				return stack;
			}

			// Change between dragon and human
			if (getModeNumber(stack) == SILVERDRAGON_CHANGE) {
				if (DragonPlayer.get(player).isDragon()) {
					DragonPlayer.get(player).setDragon(false);
					player.addChatMessage(new ChatComponentText("Changed to human!"));					
				} else {
					DragonPlayer.get(player).setDragonName(EntitySilverDragon.name);
					DragonPlayer.get(player).setDragon(true);
					player.addChatMessage(new ChatComponentText("Changed to dragon!"));
				}
			}
			else if (getModeNumber(stack) == SKELETONDRAGON_CHANGE) {
				if (DragonPlayer.get(player).isDragon()) {
					DragonPlayer.get(player).setDragon(false);
					player.addChatMessage(new ChatComponentText("Changed to human!"));					
				} else {
					DragonPlayer.get(player).setDragonName(EntityDracoMortem.name);
					DragonPlayer.get(player).setDragon(true);
					player.addChatMessage(new ChatComponentText("Changed to Skeleton dragon!"));
				}
			}
			// No powers for humans!
			else if (!DragonPlayer.get(player).isDragon()) {
				player.addChatMessage(new ChatComponentText("Silly human, only dragons have powers!"));
				return stack;
			}
			// Fireballs
			else if (getModeNumber(stack) == FIREBALLS) {
				world.playSoundAtEntity(player, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
				/* This method will spawn an entity in the World, you can use with anything that extends
				* the Entity class, in this case it's the EntitySpear class
				*/
				world.spawnEntityInWorld(new EntityMyFireball(world, player));
			}
			// Explosive Fireballs
			else if (getModeNumber(stack) == EXPLOSIVE_FIREBALLS) {
				world.playSoundAtEntity(player, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
				/* This method will spawn an entity in the World, you can use with anything that extends
				* the Entity class, in this case it's the EntitySpear class
				*/
				world.spawnEntityInWorld(new EntityMyExplosive(world, player));
			}
			else if (getModeNumber(stack) == INVISIBLE) {
				int amplifier = Math.max(1, DragonPlayer.get(player).getLevel() / 2);
				LogHelper.info("ItemDragonStaff: Amplifier = " + amplifier);
	            player.addPotionEffect(new PotionEffect(Potion.invisibility.getId(), (60 * 20 * amplifier), amplifier));
			}
			else if (getModeNumber(stack) == NIGHTVISION) {
				int amplifier = Math.max(1, DragonPlayer.get(player).getLevel() / 2);
				LogHelper.info("ItemDragonStaff: Amplifier = " + amplifier);
	            player.addPotionEffect(new PotionEffect(Potion.nightVision.getId(), (60 * 20 * amplifier), amplifier));
			}
			else if (getModeNumber(stack) == WATERBREATHING) {
				int amplifier = Math.max(1, DragonPlayer.get(player).getLevel() / 2);
				LogHelper.info("ItemDragonStaff: Amplifier = " + amplifier);
	            player.addPotionEffect(new PotionEffect(Potion.waterBreathing.getId(), (60 * 20 * amplifier), amplifier));
			}
		}
		return stack;
	}
	
//	public int addMode(String mode) {
//		int i = staffModes.size();
//		staffModes.put(i, mode);
//		NUM_MODES++;
//		return i;
//	}
	
	public void addMode(int mode, String text) {
//		int i = staffModes.size();
		staffModes.put(mode, text);
//		NUM_MODES++;
	}
	
    public String getModeName(ItemStack stack) {
    	return staffModes.get(getModeNumber(stack));
    }

    public int getModeNumber(ItemStack stack) {
		if (!stack.hasTagCompound()) { // Ensure the ItemStack has an NBTTagCompound with a "mode" tag
			NBTTagCompound tagCompound = new NBTTagCompound();
			tagCompound.setInteger("mode", 0);
			stack.setTagCompound(tagCompound);
		}
		return stack.getTagCompound().getInteger("mode") % NUM_MODES; // Ensure mode is < NUM_MODES
    }
    
    public void nextMode(ItemStack stack) {
    	int mode = getModeNumber(stack) + 1;
    	for (; mode < NUM_MODES; mode++) {
    		if (staffModes.containsKey(mode)) {
    			break;
    		}
    	}
    	if (mode >= NUM_MODES)
    		mode = FIREBALLS; // set to 0
		stack.getTagCompound().setInteger("mode", mode);
   }
    
//    public void setNumModes(int num) {
//    	NUM_MODES = num;
//    }

	// Add this if you want the "enchanted" look in the inventory
	@Override
	public boolean hasEffect(ItemStack itemStack, int pass) {
		return true;
	}
	
    @Override
    public String getItemStackDisplayName(ItemStack stack) {
		return super.getItemStackDisplayName(stack) + " mode: " + getModeName(stack) + "!";
    }
}

//protected enum Modes {
//FIREBALLS(0),
//CHANGE(1);
//
//private int mode;
//
//private Modes(int mode) {
//	this.mode = mode;
//}
//public int getValue() {
//	return this.mode;
//}
//public Modes next() {
//	if (this == FIREBALLS)
//		return CHANGE;
//	if (this == CHANGE)
//		return FIREBALLS;
//	return FIREBALLS;
//}
//public String toString() {
//	switch (mode) {
//	case 1:
//		return "Change between Dragon and Human";
//	case 0:
//	default:
//		return "Spawns Fireballs";
//	}
//}
//}
//protected Modes mode;
