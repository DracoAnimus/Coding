package net.wildbill22.draco.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

/**
 * Used to tell the client about changes
 * @author William
 *
 */
public class DragonAbilityLavaToObsidian implements IMessage {
	double x, y, z;

	/**
	 * Don't use
	 */
	public DragonAbilityLavaToObsidian() {	}

	public DragonAbilityLavaToObsidian(double x, double y, double z) {
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
	
	public static class Handler implements IMessageHandler<DragonAbilityLavaToObsidian, IMessage> {
		@Override
		public IMessage onMessage(DragonAbilityLavaToObsidian message, MessageContext ctx) {
			EntityClientPlayerMP player = Minecraft.getMinecraft().thePlayer;
	    	for (int i = 0; i < 10; i++) {
	    		float d0 = player.worldObj.rand.nextFloat() - 0.5F;
    			player.worldObj.spawnParticle("splash", message.x + d0, message.y + d0 + 1.0F, message.z + d0, 0, 0, 0);
	    	}
			return null;
		}
	}
}
