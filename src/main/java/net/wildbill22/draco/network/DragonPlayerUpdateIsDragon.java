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
public class DragonPlayerUpdateIsDragon implements IMessage {
	private boolean value;

	/**
	 * Don't use
	 */
	public DragonPlayerUpdateIsDragon() {	}

	/**
	 * 
	 * @param value new value
	 */
	public DragonPlayerUpdateIsDragon(boolean value) {
		this.value = value;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		this.value = buf.readBoolean();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeBoolean(this.value);
	}
	
	public static class Handler implements IMessageHandler<DragonPlayerUpdateIsDragon, IMessage> {
		@Override
		public IMessage onMessage(DragonPlayerUpdateIsDragon message, MessageContext ctx) {
			EntityClientPlayerMP player = Minecraft.getMinecraft().thePlayer;
			if (player != null) {
				if (player != null) {
					DragonPlayer dragonPlayer = DragonPlayer.get(player);
					dragonPlayer.setDragon(message.value, false);
					if (!message.value) {
						// Some other human defaults
//				        player.noClip = false;
						player.stepHeight = 0.5F; // Normally 0.5F for player
					}
				}

			}
			return null;
		}
	}
}
