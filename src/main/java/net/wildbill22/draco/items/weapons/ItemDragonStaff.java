package net.wildbill22.draco.items.weapons;

import java.util.Map;

import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.item.EntityEnderPearl;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import net.wildbill22.draco.Creative_Tab;
import net.wildbill22.draco.entities.EntityMyExplosive;
import net.wildbill22.draco.entities.EntityMyFireball;
import net.wildbill22.draco.entities.dragons.DragonRegistry.IDragonStaffHandler;
import net.wildbill22.draco.entities.player.DragonPlayer;
import net.wildbill22.draco.items.ItemDragonEgg;
import net.wildbill22.draco.lib.LogHelper;
import net.wildbill22.draco.lib.REFERENCE;

public abstract class ItemDragonStaff extends ItemSword implements IDragonStaffHandler {
    protected float damageAmplifier = 0;  	// FIXME: This is not multi-player safe! (short window to collide)
    protected float field_150934_a;
    protected Abilities abilities;
    
	public ItemDragonStaff(ToolMaterial material, String name) {
		super(material);
        this.field_150934_a = 4.0F + material.getDamageVsEntity();

        this.setCreativeTab(Creative_Tab.TabDraco_Animus);
		this.setUnlocalizedName(REFERENCE.Unlocalized_Path + name);
		this.setTextureName(REFERENCE.Texture_Path + name);

		setMaxStackSize(1);
		abilities = new Abilities();
		abilities.fireballs();
	}
	
	/**
	 * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
	 */
	@Override
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {
		// Only spawn new entities on the server!
		if (!world.isRemote) {
			
			// Staff doesn't do anything if no egg for it in hoard
			if (!DragonPlayer.get(player).isEggInHoard(this.getEggName())) {
				setNoEggInHoard(itemStack);
				player.addChatMessage(new ChatComponentText("No egg in hoard for this staff!"));
				return itemStack;
			}
			// egg was put back in, so activate the staff
			else {
				setEggInHoard(itemStack);
			}
			
			// Change mode if shift key pressed
			if (player.isSneaking()) {
				abilities.nextMode(itemStack);
				player.addChatMessage(new ChatComponentText("Staff in mode: " + abilities.getModeName(itemStack) + "!"));
				return itemStack;
			}

			// Change between dragon and human
			if (abilities.getModeNumber(itemStack) == abilities.CHANGE_FORM) {
				if (DragonPlayer.get(player).isDragon()) {
					DragonPlayer.get(player).setDragon(false);
					ItemDragonEgg.setHumanAbilities(player);  // Set some things that got changed!
					player.addChatMessage(new ChatComponentText("Changed to human!"));					
				} else {
					DragonPlayer.get(player).setDragonName(abilities.dragonName);
					DragonPlayer.get(player).setDragon(true);
					player.addChatMessage(new ChatComponentText("Changed to dragon!"));
				}
			}
			else if (getCooldown(itemStack) > 0) {
				return itemStack;
			}
			// No powers for humans!
			else if (!DragonPlayer.get(player).isDragon()) {
				player.addChatMessage(new ChatComponentText("Silly human, only dragons have powers!"));
				return itemStack;
			}
			// Fireballs
			else if (abilities.getModeNumber(itemStack) == abilities.FIREBALLS) {
				world.playSoundAtEntity(player, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
				/* This method will spawn an entity in the World, you can use with anything that extends
				* the Entity class, in this case it's the EntitySpear class
				*/
				world.spawnEntityInWorld(new EntityMyFireball(world, player));
				setCooldown(itemStack, 100);
			}
			// Explosive Fireballs
			else if (abilities.getModeNumber(itemStack) == abilities.EXPLOSIVE_FIREBALLS) {
				world.playSoundAtEntity(player, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
				/* This method will spawn an entity in the World, you can use with anything that extends
				* the Entity class, in this case it's the EntitySpear class
				*/
				world.spawnEntityInWorld(new EntityMyExplosive(world, player));
				setCooldown(itemStack, 100);
			}
			else if (abilities.getModeNumber(itemStack) == abilities.INVISIBLE) {
				int amplifier = Math.max(1, DragonPlayer.get(player).getLevel() / 2);
				LogHelper.info("ItemDragonStaff: Amplifier = " + amplifier);
	            player.addPotionEffect(new PotionEffect(Potion.invisibility.getId(), (60 * 20 * amplifier), amplifier));
			}
			else if (abilities.getModeNumber(itemStack) == abilities.NIGHTVISION) {
				int amplifier = Math.max(1, DragonPlayer.get(player).getLevel() / 2);
				LogHelper.info("ItemDragonStaff: Amplifier = " + amplifier);
	            player.addPotionEffect(new PotionEffect(Potion.nightVision.getId(), (60 * 20 * amplifier), amplifier));
			}
			else if (abilities.getModeNumber(itemStack) == abilities.WATERBREATHING) {
				int amplifier = Math.max(1, DragonPlayer.get(player).getLevel() / 2);
				LogHelper.info("ItemDragonStaff: Amplifier = " + amplifier);
	            player.addPotionEffect(new PotionEffect(Potion.waterBreathing.getId(), (60 * 20 * amplifier), amplifier));
			}
			else if (abilities.getModeNumber(itemStack) == abilities.TELEPORTING) {
//				int amplifier = Math.max(1, DragonPlayer.get(player).getLevel() / 2);
				world.playSoundAtEntity(player, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
				world.spawnEntityInWorld(new EntityEnderPearl(world, player));
				setCooldown(itemStack, 100);
			}
		}
		return itemStack;
	}
	
    /**
     * Current implementations of this method in child classes do not use the entry argument beside ev. They just raise
     * the damage on the stack.
     */
	@Override
    public boolean hitEntity(ItemStack itemStack, EntityLivingBase hitEntity, EntityLivingBase playerEntity)  {
		if (playerEntity instanceof EntityPlayer) {
			damageAmplifier = DragonPlayer.get((EntityPlayer) playerEntity).getLevel() / 2;
		}
        itemStack.damageItem(1, playerEntity);
        return true;
    }

    /**
     * Gets a map of item attribute modifiers, used by ItemSword to increase hit damage.
     */
	@Override
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public Multimap getItemAttributeModifiers()
    {
        Multimap multimap = super.getItemAttributeModifiers();
        multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(field_111210_e, 
        		"Weapon modifier", (double)this.field_150934_a + damageAmplifier, 0));
        return multimap;
    }
    
    private void setNoEggInHoard(ItemStack itemStack) {
    	NBTTagCompound tagCompound = itemStack.getTagCompound();
		tagCompound.setBoolean("eggInHoard", false);    	
    }
    
    private void setEggInHoard(ItemStack itemStack) {
    	NBTTagCompound tagCompound = itemStack.getTagCompound();
		tagCompound.setBoolean("eggInHoard", true);    	
    }

    private boolean isEggInHoard(ItemStack itemStack) {    	
    	NBTTagCompound tagCompound = getTagCompound(itemStack);
		return tagCompound.getBoolean("eggInHoard");
    }
    
    private int getCooldown(ItemStack itemStack) {
    	NBTTagCompound tagCompound = getTagCompound(itemStack);
    	return tagCompound.getInteger("cooldown");
    }

    private void setCooldown(ItemStack itemStack, int cooldown) {
    	NBTTagCompound tagCompound = getTagCompound(itemStack);
    	tagCompound.setInteger("cooldown", cooldown);
    }

    /**
     * Called each tick as long the item is on a player inventory. Uses by maps to check if is on a player hand and
     * update it's contents.
     */
    public void onUpdate(ItemStack itemStack, World p_77663_2_, Entity p_77663_3_, int p_77663_4_, boolean p_77663_5_) {
    	super.onUpdate(itemStack, p_77663_2_, p_77663_3_, p_77663_4_, p_77663_5_);
    	
    	NBTTagCompound tagCompound = getTagCompound(itemStack);
		int cooldown = tagCompound.getInteger("cooldown");
			
		if (cooldown > 0) {
			cooldown--;
			boolean effect = (cooldown % 6) < 3;  // Flash
			tagCompound.setBoolean("effect", effect);
			tagCompound.setInteger("cooldown", cooldown);
		}		
    }
    
    private NBTTagCompound getTagCompound(ItemStack itemStack) {    	
		if (!itemStack.hasTagCompound()) { // Ensure the ItemStack has an NBTTagCompound
			NBTTagCompound tagCompound = new NBTTagCompound();
			tagCompound.setInteger("mode", 0);
			tagCompound.setBoolean("eggInHoard", true);
			tagCompound.setBoolean("effect", true);
			tagCompound.setInteger("cooldown", 0);
			itemStack.setTagCompound(tagCompound);
		}
		return itemStack.getTagCompound();
    }

    // Add this if you want the "enchanted" look in the inventory
	@Override
	public boolean hasEffect(ItemStack itemStack, int pass) {
    	NBTTagCompound tagCompound = getTagCompound(itemStack);
		return isEggInHoard(itemStack) && tagCompound.getBoolean("effect");
	}
	
    @Override
    public String getItemStackDisplayName(ItemStack stack) {
		return super.getItemStackDisplayName(stack) + " mode: " + abilities.getModeName(stack) + "!";
    }

    // Stuff below for modes
    
    public static class Abilities {
	    private Map<Integer, String> staffModes = Maps.newHashMap();
	    private String dragonName;
		private int FIREBALLS = 0;  // Keep this first
		private int CHANGE_FORM = 1;
		private int EXPLOSIVE_FIREBALLS = 2;
		private int INVISIBLE = 3;
		private int NIGHTVISION = 4;
		private int WATERBREATHING = 5;
		private int TELEPORTING = 6;
		private int NUM_MODES = 7;

		public void changeForm(String dragonName) {
			this.dragonName = dragonName;
			addMode(CHANGE_FORM, "Change form");
		}
		public void fireballs() {
			addMode(FIREBALLS, "Spawns Fireballs");
		}
		public void explosiveFireballs() {
			addMode(EXPLOSIVE_FIREBALLS, "Spawn Explosive Fireballs");
		}
		public void teleporting() {
			addMode(TELEPORTING, "Teleport");
		}
		public void invisible() {
			addMode(INVISIBLE, "Invisible");
		}

		private void addMode(int mode, String text) {
			staffModes.put(mode, text);
		}
		
	    public String getModeName(ItemStack stack) {
	    	return staffModes.get(getModeNumber(stack));
	    }
    
	    private int getModeNumber(ItemStack stack) {
			if (!stack.hasTagCompound()) { // Ensure the ItemStack has an NBTTagCompound with a "mode" tag
				NBTTagCompound tagCompound = new NBTTagCompound();
				tagCompound.setInteger("mode", 0);
				tagCompound.setBoolean("eggInHoard", true);
				stack.setTagCompound(tagCompound);
			}
			return stack.getTagCompound().getInteger("mode") % NUM_MODES; // Ensure mode is < NUM_MODES
	    }
	    
	    // TODO: Use an iterator
	    private void nextMode(ItemStack stack) {
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
    }    
}
