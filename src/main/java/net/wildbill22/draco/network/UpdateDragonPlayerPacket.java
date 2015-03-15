package net.wildbill22.draco.network;

import io.netty.buffer.ByteBuf;

import net.minecraft.client.Minecraft;
import net.wildbill22.draco.entities.player.DragonPlayer;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

/**
 * Used to tell the server about changes
 * @author William
 *
 */
public class UpdateDragonPlayerPacket implements IMessage {

	public static class Handler implements IMessageHandler<UpdateDragonPlayerPacket, IMessage> {
		@Override
		public IMessage onMessage(UpdateDragonPlayerPacket message, MessageContext ctx) {
			DragonPlayer.get(Minecraft.getMinecraft().thePlayer).setHoardSize(message.value);
			return null;
		}
	}

	private int value;
	/**
	 * Don't use
	 */
	public UpdateDragonPlayerPacket() {	}

	/**
	 * 
	 * @param value new value
	 */
	public UpdateDragonPlayerPacket(int value) {
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
}
