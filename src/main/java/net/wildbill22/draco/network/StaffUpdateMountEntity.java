package net.wildbill22.draco.network;

import java.util.Iterator;
import java.util.List;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.wildbill22.draco.lib.LogHelper;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

/**
 * Used to tell the client about changes
 * @author William
 *
 */
public class StaffUpdateMountEntity implements IMessage {
	double x, y, z;

	/**
	 * Don't use
	 */
	public StaffUpdateMountEntity() {	}

	public StaffUpdateMountEntity(double x, double y, double z) {
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
	
	public static class Handler implements IMessageHandler<StaffUpdateMountEntity, IMessage> {
		@Override
		public IMessage onMessage(StaffUpdateMountEntity message, MessageContext ctx) {
			EntityPlayer player = ctx.getServerHandler().playerEntity;
			if (player != null) {
			    AxisAlignedBB targetBox = AxisAlignedBB.getBoundingBox(
			            message.x-0.5D,
			            message.y-0.0D,
			            message.z-0.5D,
			            message.x+0.5D,
			            message.y+1.5D,
			            message.z+0.5D
			            );
		    	@SuppressWarnings("rawtypes")
				List entities = player.worldObj.getEntitiesWithinAABBExcludingEntity(player, targetBox);
		        @SuppressWarnings("rawtypes")
				Iterator iterator = entities.iterator();
		        while (iterator.hasNext()) {
		            Entity entity = (Entity)iterator.next();
		            if (entity instanceof EntityLivingBase) {
						// Mount Entity and disable attack (is in LivingHurtEvent event)
		    			entity.mountEntity(player);
		    			// Change entity mounted position?
//		    			player.getMountedYOffset();
		            	LogHelper.info("On server, mounted entity!");
		            }
		        }
			}
			return null;
		}
	}
}
