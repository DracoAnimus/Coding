package net.wildbill22.draco.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.wildbill22.draco.entities.player.DragonPlayer;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.common.network.ByteBufUtils;

/**
 * Used to tell the client about changes
 * @author William
 *
 */
public class DragonPlayerUpdateDragonName implements IMessage {
	private String text;

	/**
	 * Don't use
	 */
	public DragonPlayerUpdateDragonName() {	}

	/**
	 * 
	 * @param text new value
	 */
	public DragonPlayerUpdateDragonName(String text) {
		this.text = text;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		text = ByteBufUtils.readUTF8String(buf);
	}

	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeUTF8String(buf, text);
	}
	
	public static class Handler implements IMessageHandler<DragonPlayerUpdateDragonName, IMessage> {
		@Override
		public IMessage onMessage(DragonPlayerUpdateDragonName message, MessageContext ctx) {
			EntityClientPlayerMP player = Minecraft.getMinecraft().thePlayer;
			if (player != null) {
				DragonPlayer dragonPlayer = DragonPlayer.get(player);
				if (dragonPlayer != null)
					dragonPlayer.setDragonName(message.text);
			}
			return null;
		}
	}
}
