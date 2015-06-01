package net.wildbill22.draco.network;

import java.util.Iterator;
import java.util.List;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.wildbill22.draco.entities.player.DragonPlayer;
import net.wildbill22.draco.lib.LogHelper;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

/**
 * Used to tell the client about changes
 * @author William
 *
 */
public class StaffUpdateTeleportThroughWall implements IMessage {
	double x, y, z;

	/**
	 * Don't use
	 */
	public StaffUpdateTeleportThroughWall() {	}

	public StaffUpdateTeleportThroughWall(double x, double y, double z) {
		this.x = x; this.y = y; this.z = z;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		this.x = buf.readDouble();
		this.y = buf.readDouble();
		this.z = buf.readDouble();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeDouble(this.x);
		buf.writeDouble(this.y);
		buf.writeDouble(this.z);
	}
	
	public static class Handler implements IMessageHandler<StaffUpdateTeleportThroughWall, IMessage> {
		@Override
		public IMessage onMessage(StaffUpdateTeleportThroughWall message, MessageContext ctx) {
			EntityPlayer player = ctx.getServerHandler().playerEntity;
			if (player != null) {
				player.setPositionAndUpdate(message.x, message.y, message.z);
				LogHelper.info("Teleport to: " + message.x + "," + message.y + "," + message.z);
			}
			return null;
		}
	}
}
