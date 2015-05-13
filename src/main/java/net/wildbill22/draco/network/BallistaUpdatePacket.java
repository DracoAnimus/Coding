package net.wildbill22.draco.network;

import io.netty.buffer.ByteBuf;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

/**
 * Used to tell the client about changes
 * @author William
 *
 */
public class BallistaUpdatePacket implements IMessage {
	private int value;

	/**
	 * Don't use
	 */
	public BallistaUpdatePacket() {	}

	/**
	 * 
	 * @param value new value
	 */
	public BallistaUpdatePacket(int value) {
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
	
	public static class Handler implements IMessageHandler<BallistaUpdatePacket, IMessage> {
		@Override
		public IMessage onMessage(BallistaUpdatePacket message, MessageContext ctx) {
			return null;
		}
	}
}
