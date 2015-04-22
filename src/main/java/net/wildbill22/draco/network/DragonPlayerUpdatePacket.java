package net.wildbill22.draco.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.wildbill22.draco.entities.player.DragonPlayer;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

/**
 * Used to tell the client about changes
 * @author William
 *
 */
public class DragonPlayerUpdatePacket implements IMessage {
	private int value;

	/**
	 * Don't use
	 */
	public DragonPlayerUpdatePacket() {	}

	/**
	 * 
	 * @param value new value
	 */
	public DragonPlayerUpdatePacket(int value) {
		this.value = value;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		this.value = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(this.value);
	}
	
	public static class Handler implements IMessageHandler<DragonPlayerUpdatePacket, IMessage> {
		@Override
		public IMessage onMessage(DragonPlayerUpdatePacket message, MessageContext ctx) {
			EntityClientPlayerMP player = Minecraft.getMinecraft().thePlayer;
			if (player != null)
				DragonPlayer.get(player).setLevel(message.value);
			return null;
		}
	}
}
