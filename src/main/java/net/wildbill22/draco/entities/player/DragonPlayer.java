package net.wildbill22.draco.entities.player;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;
import net.wildbill22.draco.Core;
import net.wildbill22.draco.blocks.TemporaryHoard;
import net.wildbill22.draco.entities.dragons.DragonRegistry.IDragonEggHandler;
import net.wildbill22.draco.entities.dragons.EntitySilverDragon;
import net.wildbill22.draco.items.ModItems;
import net.wildbill22.draco.items.dragoneggs.ItemDragonEgg;
import net.wildbill22.draco.lib.BALANCE;
import net.wildbill22.draco.lib.LogHelper;
import net.wildbill22.draco.lib.NBTCoordinates;
import net.wildbill22.draco.network.DragonPlayerUpdateDragonName;
import net.wildbill22.draco.network.DragonPlayerUpdateIsDragon;
import net.wildbill22.draco.network.DragonPlayerUpdateLevel;
import net.wildbill22.draco.proxies.CommonProxy;
import net.wildbill22.draco.stats.ModStats;
import net.wildbill22.draco.tile_entity.TileEntityTemporaryHoard;

public class DragonPlayer implements IExtendedEntityProperties {
	public static final String EXT_PROP_NAME = "DragonPlayer";
	private final EntityPlayer player;
	
	// The extended player properties:
	private boolean isDragon;
	public boolean wasFlyingOnExit;
	private int hoardSize;
	private int level;        // current level, as determined by several factors
	private ArrayList<NBTCoordinates> hoardList;
	private String dragonName;
	private Set<String> eggsInHoard; // All the dragon egg types in the hoard

	public String getDragonName() {
		return dragonName;
	}

	public void setDragonName(String dragonName) {
		if (dragonName != null && !dragonName.isEmpty()) {
			this.dragonName = dragonName;
	        LogHelper.info("DragonPlayer setDragonName: Player is a " + dragonName + ".");
		}
	}

	public DragonPlayer(EntityPlayer player, World world) {
		// Initialize some stuff
		this.player = player;
		this.level = 1;
		this.isDragon = BALANCE.PLAYER_AS_SILVER_DRAGON_INITIALLY;
		this.dragonName = EntitySilverDragon.name;		
		this.hoardList = new ArrayList<NBTCoordinates>();
		this.eggsInHoard = new HashSet<String>();
	}

	private static final String getSaveKey(EntityPlayer player) {
		// no longer a username field, so use the command sender name instead:
		return player.getCommandSenderName() + ":" + EXT_PROP_NAME;
	}

	public static void saveProxyData(EntityPlayer player) {
		DragonPlayer playerData = DragonPlayer.get(player);
		NBTTagCompound savedData = new NBTTagCompound();
		playerData.saveNBTData(savedData);
		CommonProxy.storeEntityData(getSaveKey(player), savedData);
	}

	/**
	 * Registers Dragon properties to player
	 * 
	 * @param player
	 */
	public static final void register(EntityPlayer player, World world) {
		player.registerExtendedProperties(DragonPlayer.EXT_PROP_NAME, new DragonPlayer(player, world));
	}

	/**
	 * 
	 * @param player
	 * @return DragonPlayer property of player
	 */
	public static final DragonPlayer get(EntityPlayer player) {
		return (DragonPlayer) player.getExtendedProperties(EXT_PROP_NAME);
	}

	@Override
	public void saveNBTData(NBTTagCompound compound) {
		NBTTagCompound properties = new NBTTagCompound();
		// Dragon player properties
		properties.setString("dragonName", getDragonName());
		properties.setBoolean("isDragon", isDragon());
		properties.setBoolean("wasFlyingOnExit", this.getPlayer().capabilities.isFlying);
		properties.setInteger("hoardSize", getHoardSize());
		properties.setInteger("level", getLevel());

        // Hoard Locations
		int size = hoardList.size();
		int writeIndex = 0;
        Iterator<NBTCoordinates> iterator = hoardList.iterator();
		for (int i = 0; i < size; i++) {
			NBTCoordinates coords = iterator.next();
			if (hoardExists(coords)) {
				coords.writeToNBT(properties, writeIndex);
				writeIndex++;
			}
        }
		properties.setInteger("numHoards", writeIndex);
		
		// Eggs in hoard
		size = eggsInHoard.size();
		writeIndex = 0;
        Iterator<String> it = eggsInHoard.iterator();
		for (int i = 0; i < size; i++) {
			properties.setString("egg" + i, it.next());			
			writeIndex++;
		}
		properties.setInteger("numEggs", writeIndex);
		compound.setTag(EXT_PROP_NAME, properties);

		// Log some settings
//        LogHelper.info("DragonPlayer save: Player is " + (isDragon ? "a" : "not a") + " dragon");
//        LogHelper.info("DragonPlayer save: Player is a " + dragonName + ".");
//        LogHelper.info("DragonPlayer save: Player was " + (this.getPlayer().capabilities.isFlying ? "" : "not ") + "flying");
        LogHelper.info("DragonPlayer save: Player has " + getHoardSize() + " coins.");
//        LogHelper.info("DragonPlayer save: Player has " + writeIndex + " hoards.");
        LogHelper.info("DragonPlayer save: Player is level " + level + ".");
	}

	@Override
	public void loadNBTData(NBTTagCompound compound) {
		NBTTagCompound properties = (NBTTagCompound) compound.getTag(EXT_PROP_NAME);
		if (properties == null)
			return;
		// Dragon player properties 
        setDragonName(properties.getString("dragonName"));
		setDragon(properties.getBoolean("isDragon"), false);
        this.wasFlyingOnExit = properties.getBoolean("wasFlyingOnExit");
        setHoardSize(properties.getInteger("hoardSize"));
        LogHelper.info("DragonPlayer load: Player has " + getHoardSize() + " coins.");
        setLevel(properties.getInteger("level"), false);
        LogHelper.info("DragonPlayer load: Player is level " + level + ".");

        // Hoard Locations
        hoardList.clear();
        int size = 0;
        if (properties.hasKey("numHoards")) {
			size = properties.getInteger("numHoards");
			for (int i = 0; i < size; i++) {
				NBTCoordinates coords = new NBTCoordinates();
				coords.readFromNBT(properties, i);
				if (hoardExists(coords)) {
					hoardList.add(coords);
				}
			}
        }
		// Eggs in hoard
        eggsInHoard.clear();
        if (properties.hasKey("numEggs")) {
			size = properties.getInteger("numEggs");
			for (int i = 0; i < size; i++) {
				String egg = properties.getString("egg" + i);
				addEgg(egg);
			}
        }

        // Log some settings
//        LogHelper.info("DragonPlayer load: Player is " + (isDragon ? "a" : "not a") + " dragon");
//        LogHelper.info("DragonPlayer load: Player is a " + dragonName + ".");
//        LogHelper.info("DragonPlayer load: Player was " + (wasFlyingOnExit ? "" : "not ") + "flying");   
//        LogHelper.info("DragonPlayer load: Player has " + hoardList.size() + " hoards.");
	}

	/**
	 * Copies additional player data from the given ExtendedPlayer instance
	 * Avoids NBT disk I/O overhead when cloning a player after respawn
	 */
	public void copy(DragonPlayer props) {
		this.isDragon = props.isDragon;
		this.wasFlyingOnExit = props.wasFlyingOnExit;
		setLevel(props.level, true);
		
        // Hoard Locations
        hoardList.clear();
		int size = props.hoardList.size();
        Iterator<NBTCoordinates> iterator = props.hoardList.iterator();
		for (int i = 0; i < size; i++) {
			NBTCoordinates coords = iterator.next();
			if (hoardExists(coords)) {
				hoardList.add(coords);
			}
        }
		       
		// Eggs in hoard
        eggsInHoard.clear();
		size = props.eggsInHoard.size();
        Iterator<String> it = props.eggsInHoard.iterator();
		for (int i = 0; i < size; i++) {
			String egg = it.next();
			eggsInHoard.add(egg);
		}
	}

	@Override
	public void init(Entity entity, World world) {
	}

	public boolean isDragon() {
		return isDragon;
	}

	public void setDragon(boolean isDragon, boolean syncClient) {
		this.isDragon = isDragon;
		if (isDragon()) {
    		// TODO: Add a clear abilities per dragon type
			player.fireResistance = 1200;  // Not immune, but can stand in fire 1 minute
    		player.capabilities.allowFlying = true;
		}
    	else {
    		// TODO: Add a set abilities per dragon type
			player.fireResistance = 0;
			if (!player.capabilities.isCreativeMode)
				player.capabilities.allowFlying = false;    		
    	}
		if (syncClient) {
			syncClient(false);
		}
		
		// Also adds unique modifiers for each dragon type
		PlayerModifiers.applyModifiers(level, player, isDragon);
	}

	private EntityPlayer getPlayer() {
		return player;
	}
	
	public int getHoardSize() {
		return hoardSize;
	}
	
	public void setHoardSize(int size) {
		this.hoardSize = size;
	}
	
	public void addHoard(int x, int y, int z) {
		NBTCoordinates coords = new NBTCoordinates(x, y, z);
		hoardList.add(coords);
		LogHelper.info("DragonPlayer: Added hoard at: " + x + "," + y + "," + z);
	}

	// TODO: Verify this removes a hoard
	public void removeHoard(int x, int y, int z) {
		int size = hoardList.size();
		for (int i = 0; i < size; i++) {
			if (hoardList.get(i).posX == x && hoardList.get(i).posY == y && hoardList.get(i).posZ == z) {
				hoardList.remove(i);
				LogHelper.info("DragonPlayer: Removed hoard at: " + x + "," + y + "," + z);
				break;
			}
        }
	}

	// Returns true if anything in chest other than gold coins
	public boolean calculateHoardSize(World world) {
		// Only look for hoards in OverWorld!
		if (world.provider.dimensionId != 0) {
			this.player.addChatMessage(new ChatComponentText("Hoards only work in the OverWorld!"));
			return false;
		}
		hoardSize = 0;
		boolean notJustCoins = false;
		this.eggsInHoard.clear();
		int size = hoardList.size();
		for (int i = 0; i < size; i++) {
			TileEntityChest chestEntity = (TileEntityChest) world.getTileEntity(hoardList.get(i).posX, hoardList.get(i).posY, hoardList.get(i).posZ);
			if (chestEntity instanceof TileEntityTemporaryHoard) {
				for (int j = 0; j < chestEntity.getSizeInventory(); j++) {
					ItemStack itemStack = chestEntity.getStackInSlot(j);
					if (itemStack != null) {
						if (itemStack.getItem() != ModItems.goldCoin) {
							notJustCoins = true;
							if (itemStack.getItem() instanceof ItemDragonEgg) {
								this.addEgg(((IDragonEggHandler)itemStack.getItem()).getEggName());
							}
						}
						else {
							hoardSize += itemStack.stackSize;
						}
					}
				}
			}
		}
        LogHelper.info("DragonPlayer load: Player has " + getHoardSize() + " coins.");
        LogHelper.info("DragonPlayer load: Player has " + this.eggsInHoard.size() + " different eggs.");
		if (hoardSize > 0) {
			this.player.addStat(ModStats.firstGoldCoin, 1);
		}
		calculateLevel();
		return notJustCoins;
	}

	private void calculateLevel() {
		int coinsToMaxLevel = BALANCE.LEVELING.HOARD_COINS_TO_MAX_LEVEL; 
		coinsToMaxLevel += coinsToMaxLevel / 2 * Math.max(0, (this.eggsInHoard.size() - 1));
		int coinEffect = hoardSize > coinsToMaxLevel ? coinsToMaxLevel : hoardSize; 
		level = (int) Math.floor((Math.pow(coinEffect, BALANCE.LEVELING.LEVEL_MOD_TYPE) / 
				Math.pow(coinsToMaxLevel, BALANCE.LEVELING.LEVEL_MOD_TYPE)) * BALANCE.LEVELING.MAXIMUM_LEVEL);
		level = level == 0 ? 1 : level;
		if (level >= 10) {
			this.player.addStat(ModStats.levelTenDragon, 1);
			level = 10;
		}
		if (this.eggsInHoard.size() > 5) {
			this.player.addStat(ModStats.foundAllDragonEggs, 1);			
		}
		setLevel(level, true);
        LogHelper.info("DragonPlayer calculateLevel: Player is level " + level + ".");
	}

	public boolean hoardExists(NBTCoordinates coords) {
		// Don't check for block when not in Over-world
		if (player.worldObj.provider.dimensionId != 0)
			return true;
		Block block = player.worldObj.getBlock(coords.posX, coords.posY, coords.posZ);
		if (block != null && block instanceof TemporaryHoard)
			return true;
		return false;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level, boolean syncClient) {
		this.level = level;
		if (syncClient)
			syncClient(false);
		PlayerModifiers.applyModifiers(level, player, isDragon);
	}
	
	/**
	 * Handles player loading and syncing on world join. Only called server side
	 * @param player
	 */
	public static void onPlayerJoinWorld(EntityPlayer player) {
		DragonPlayer p = DragonPlayer.get(player);
    	if (p.isDragon()) {
    		player.capabilities.allowFlying = true;
    		if (p.wasFlyingOnExit)
    			player.capabilities.isFlying = true;
    	}
    	else {
    		player.capabilities.allowFlying = false;    		
    	}
		p.syncClient(false);
	}
	
//	public void syncServer() {		
//		// Only send message on client to server
//		if(player.worldObj.isRemote){
//	        LogHelper.info("DragonPlayer syncServer: About to send player isDragon " + isDragon() + " to server.");
//	        if (player != null) {
//        		Core.modChannel.sendToServer(new DragonPlayerUpdatePacket2(isDragon()));
//    	        LogHelper.info("DragonPlayer syncServer: Sent player isDragon " + isDragon() + " to server.");
//	        }
//		}
//	}
//
	public void syncClient(boolean all) {
		// Only send message on server to client
		if(!player.worldObj.isRemote){
	        LogHelper.info("DragonPlayer syncClient: About to send player level of " + level + " to client.");
	        if (player != null) {
        		Core.modChannel.sendTo(new DragonPlayerUpdateLevel(level), (EntityPlayerMP)player);
        		Core.modChannel.sendTo(new DragonPlayerUpdateIsDragon(isDragon), (EntityPlayerMP)player);
        		Core.modChannel.sendTo(new DragonPlayerUpdateDragonName(dragonName), (EntityPlayerMP)player);
		        LogHelper.info("DragonPlayer sync: Sent player level of " + level + " to client.");
	        }
		}
	}

	public boolean isEggInHoard(String name) {
		return this.eggsInHoard.contains(name);
	}
	
	public void addEgg(String name) {
		this.eggsInHoard.add(name);
	}
}
