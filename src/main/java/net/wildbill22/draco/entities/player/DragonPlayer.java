package net.wildbill22.draco.entities.player;

import java.util.ArrayList;
import java.util.Iterator;

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
import net.wildbill22.draco.entities.dragons.EntitySilverDragon;
import net.wildbill22.draco.items.ModItems;
import net.wildbill22.draco.lib.BALANCE;
import net.wildbill22.draco.lib.LogHelper;
import net.wildbill22.draco.lib.NBTCoordinates;
import net.wildbill22.draco.network.DragonPlayerUpdatePacket;
import net.wildbill22.draco.proxies.CommonProxy;
import net.wildbill22.draco.stats.ModStats;
import net.wildbill22.draco.tile_entity.TileEntityTemporaryHoard;

public class DragonPlayer implements IExtendedEntityProperties {
	public static final String EXT_PROP_NAME = "DragonPlayer";
	private final EntityPlayer player;
//	private EntityMCDragon dragon =  null;

//	public EntityMCDragon getDragon() {
//		if (dragon == null) {			
//			dragon = EntityMCDragon.EntityMCDragonFactory(dragonName, world);
//			dragon.setPlayer(player);
//		}
//		return dragon;
//	}
	
	// The extended player properties:
	private boolean isDragon;
	public boolean wasFlyingOnExit;
	private int hoardSize;
	private int level;        // current level, as determined by several factors
	private ArrayList<NBTCoordinates> hoardList;

	private String dragonName;
	public String getDragonName() {
		return dragonName;
	}

	public void setDragonName(String dragonName) {
		if (dragonName != null && !dragonName.isEmpty())
			this.dragonName = dragonName;
	}

	public DragonPlayer(EntityPlayer player, World world) {
		// Initialize some stuff
		this.player = player;
		this.isDragon = true;
		this.level = 1;
//		this.player.getDataWatcher().addObject(BALANCE.LEVEL_WATCHER, this.level);
		this.hoardList = new ArrayList<NBTCoordinates>();
		this.dragonName = EntitySilverDragon.name;
		
		// Configure some stuff that needs defaults (don't set anything that will be loaded)
		this.setDragon(true); // Will add a way to change this, but for now, always a dragon
		player.fireResistance = 1200;  // Not immune, but can stand in fire 1 minute
	}

	private static final String getSaveKey(EntityPlayer player) {
		// no longer a username field, so use the command sender name instead:
		return player.getCommandSenderName() + ":" + EXT_PROP_NAME;
	}

	// Not sure if the next two methods are needed
	// Are these supposed to be in CommonProxy?
//	public static final void loadProxyData(EntityPlayer player) {
//		DragonPlayer playerData = DragonPlayer.get(player);
//		NBTTagCompound savedData = CommonProxy.getEntityData(getSaveKey(player));
//		if (savedData != null) {
//			playerData.loadNBTData(savedData);
//		}
//	}
	
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
		properties.setBoolean("isDragon", isDragon());
		properties.setBoolean("wasFlyingOnExit", this.getPlayer().capabilities.isFlying);
		properties.setInteger("hoardSize", getHoardSize());
		properties.setInteger("level", getLevel());
		properties.setString("dragonName", getDragonName());

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
		setDragon(properties.getBoolean("isDragon"));
        this.wasFlyingOnExit = properties.getBoolean("wasFlyingOnExit");
        setHoardSize(properties.getInteger("hoardSize"));
        LogHelper.info("DragonPlayer load: Player has " + getHoardSize() + " coins.");
        setLevel(properties.getInteger("level"));
        LogHelper.info("DragonPlayer load: Player is level " + level + ".");
        setDragonName(properties.getString("dragonName"));

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
		setLevel(props.level);
		int size = props.hoardList.size();
        Iterator<NBTCoordinates> iterator = props.hoardList.iterator();
		for (int i = 0; i < size; i++) {
			NBTCoordinates coords = new NBTCoordinates();
			coords.copy(iterator.next());
			hoardList.add(coords);
        }
	}

	@Override
	public void init(Entity entity, World world) {
	}

	public boolean isDragon() {
		return isDragon;
	}

	public void setDragon(boolean isDragon) {
		this.isDragon = isDragon;
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
		int size = hoardList.size();
		for (int i = 0; i < size; i++) {
			TileEntityChest chestEntity = (TileEntityChest) world.getTileEntity(hoardList.get(i).posX, hoardList.get(i).posY, hoardList.get(i).posZ);
			if (chestEntity instanceof TileEntityTemporaryHoard) {
				for (int j = 0; j < chestEntity.getSizeInventory(); j++) {
					ItemStack coins = chestEntity.getStackInSlot(j);
					if (coins != null) {
						if (coins.getItem() != ModItems.goldCoin)
							notJustCoins = true;
						else {
							hoardSize += coins.stackSize;
							if (hoardSize > 0) {
								this.player.addStat(ModStats.firstGoldCoin, 1);
							}
						}
					}
				}
			}
		}
        LogHelper.info("DragonPlayer load: Player has " + getHoardSize() + " coins.");
		calculateLevel();
		return notJustCoins;
	}

	private void calculateLevel() {
		int coinEffect = hoardSize > BALANCE.LEVELING.HOARD_COINS_TO_MAX_LEVEL ? BALANCE.LEVELING.HOARD_COINS_TO_MAX_LEVEL : hoardSize; 
		level = (int) Math.floor((Math.pow(coinEffect, BALANCE.LEVELING.LEVEL_MOD_TYPE) / 
				Math.pow(BALANCE.LEVELING.HOARD_COINS_TO_MAX_LEVEL, BALANCE.LEVELING.LEVEL_MOD_TYPE)) * BALANCE.LEVELING.MAXIMUM_LEVEL);
		level = level == 0 ? 1 : level;
		setLevel(level);
		if (level >= 10) {
			this.player.addStat(ModStats.levelTenDragon, 1);
		}
        LogHelper.info("DragonPlayer calculateLevel: Player is level " + level + ".");
	}

	private boolean hoardExists(NBTCoordinates coords) {
		Block block = player.worldObj.getBlock(coords.posX, coords.posY, coords.posZ);
		if (block != null && block instanceof TemporaryHoard)
			return true;
		return false;
	}

	public int getLevel() {
//		level = this.player.getDataWatcher().getWatchableObjectInt(BALANCE.LEVEL_WATCHER);
		return level;
	}

	public void setLevel(int level) {
//		this.player.getDataWatcher().updateObject(BALANCE.LEVEL_WATCHER, level);
		this.level = level;
		syncClient(false);
		PlayerModifiers.applyModifiers(level, player);
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
		p.syncClient(false);
	}

	public void syncClient(boolean all) {
		// Only send message on server to client
		if(!player.worldObj.isRemote){
	        LogHelper.info("DragonPlayer sync: About to send player level of " + level + " to client.");
	        if (player != null) {
        		Core.modChannel.sendTo(new DragonPlayerUpdatePacket(level), (EntityPlayerMP)player);
		        LogHelper.info("DragonPlayer sync: Sent player level of " + level + " to client.");
	        }
		}
	}
}
