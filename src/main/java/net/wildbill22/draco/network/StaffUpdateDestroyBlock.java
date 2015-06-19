package net.wildbill22.draco.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.StatCollector;
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
public class StaffUpdateDestroyBlock implements IMessage {
	int x, y, z;

	/**
	 * Don't use
	 */
	public StaffUpdateDestroyBlock() {	}

	public StaffUpdateDestroyBlock(int x, int y, int z) {
		this.x = x; this.y = y; this.z = z;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		this.x = buf.readInt();
		this.y = buf.readInt();
		this.z = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(this.x);
		buf.writeInt(this.y);
		buf.writeInt(this.z);
	}
	
	public static class Handler implements IMessageHandler<StaffUpdateDestroyBlock, IMessage> {
		@Override
		public IMessage onMessage(StaffUpdateDestroyBlock message, MessageContext ctx) {
			EntityPlayer player = ctx.getServerHandler().playerEntity;
			if (player != null) {
				Block block = player.worldObj.getBlock(message.x, message.y, message.z);
				if (block != null) {
					float amplifier = DragonPlayer.get(player).getLevel() / 2.0F;
					float minHardness = Math.max(1.0F, 1.0F + amplifier);
					float hardness = block.getBlockHardness(player.worldObj, message.x, message.y, message.z);
	            	LogHelper.info("Block hardness is " + hardness);
					if (hardness >= 0 && hardness < minHardness) {
						player.worldObj.setBlock(message.x, message.y, message.z, Blocks.air, 0, 3);
		            	LogHelper.info("Destroyed a block!");
					}
					else {
						player.addChatMessage(new ChatComponentText(
								StatCollector.translateToLocal("chat.wildbill22_draco.blockIsTooHard")));						
					}
				}
			}
			return null;
		}
	}
}
