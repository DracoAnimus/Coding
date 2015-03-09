package net.wildbill22.draco.entities.player;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;
import net.wildbill22.draco.lib.LogHelper;
import net.wildbill22.draco.proxies.CommonProxy;

public class DragonPlayer implements IExtendedEntityProperties {
	public final static String EXT_PROP_NAME = "DragonPlayer";
	private final EntityPlayer player;
	
	// The extended player properties:
	private boolean isDragon;
	public boolean wasFlyingOnExit;
	
	public DragonPlayer(EntityPlayer player) {
		this.player = player;
		this.setDragon(true); // Will add a way to change this, but for now, always a dragon
//		player.isImmuneToFire = true;  // this is protected, could use reflection
		player.fireResistance = 1200;  // Not immune, but can stand in fire 1 minute
	}

	private static final String getSaveKey(EntityPlayer player) {
		// no longer a username field, so use the command sender name instead:
		return player.getCommandSenderName() + ":" + EXT_PROP_NAME;
	}

	// Not sure if the next two methods are needed
	public static final void loadProxyData(EntityPlayer player) {
		DragonPlayer playerData = DragonPlayer.get(player);
		NBTTagCompound savedData = CommonProxy.getEntityData(getSaveKey(player));
		if (savedData != null) {
			playerData.loadNBTData(savedData);
		}
	}
	
	public static void saveProxyData(EntityPlayer player, boolean resetBlood) {
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
	public static final void register(EntityPlayer player) {
		player.registerExtendedProperties(DragonPlayer.EXT_PROP_NAME, new DragonPlayer(player));
	}

	/**
	 * 
	 * @param player
	 * @return DragonPlayer property of player
	 */
	public static final DragonPlayer get(EntityPlayer player)	{
		return (DragonPlayer) player.getExtendedProperties(EXT_PROP_NAME);
	}

	@Override
	public void saveNBTData(NBTTagCompound compound) {
		NBTTagCompound properties = new NBTTagCompound();
		properties.setBoolean("isDragon", isDragon);
        LogHelper.info("DragonPlayer save: Player is " + (isDragon ? "a" : "not a") + " dragon");
		properties.setBoolean("wasFlyingOnExit", this.getPlayer().capabilities.isFlying);
        LogHelper.info("DragonPlayer save: Player was " + (this.getPlayer().capabilities.isFlying ? "" : "not ") + "flying");
		compound.setTag(EXT_PROP_NAME, properties);
	}

	@Override
	public void loadNBTData(NBTTagCompound compound) {
		NBTTagCompound properties = (NBTTagCompound) compound.getTag(EXT_PROP_NAME);
		this.isDragon = properties.getBoolean("isDragon");
        LogHelper.info("DragonPlayer load: Player is " + (isDragon ? "a" : "not a") + " dragon");
        this.wasFlyingOnExit = properties.getBoolean("wasFlyingOnExit");
        LogHelper.info("DragonPlayer load: Player was " + (wasFlyingOnExit ? "" : "not ") + "flying");   
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

	public EntityPlayer getPlayer() {
		return player;
	}
}
