package net.wildbill22.draco.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

/**
 * Used to tell the client about changes
 * @author William
 *
 */
public class StaffUpdateDropEntity implements IMessage {
	boolean doIt;
	
	/**
	 * Don't use
	 */
	public StaffUpdateDropEntity() {	}

	public StaffUpdateDropEntity(boolean doIt) {
		this.doIt = doIt;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		this.doIt = buf.readBoolean();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeBoolean(this.doIt);
	}
	
	public static class Handler implements IMessageHandler<StaffUpdateDropEntity, IMessage> {
		@Override
		public IMessage onMessage(StaffUpdateDropEntity message, MessageContext ctx) {
			EntityPlayer player = ctx.getServerHandler().playerEntity;
			if (player != null) {
	        	EntityLivingBase rider = (EntityLivingBase) player.riddenByEntity;
	        	if (rider != null) {
					dismountEntity(rider, player, 2);
					rider.ridingEntity = null;
					player.riddenByEntity = null;
	        	}
			}
			return null;
		}
	}
	
    /**
     * Moves the entity to a position out of the way of its mount.
     */
    private static void dismountEntity(Entity ridingEntity, Entity riddenEntity, int dismountDistance) {
        double x = ridingEntity.posX;
        double y = ridingEntity.boundingBox.minY + (double)ridingEntity.height;
        double z = ridingEntity.posZ;
        for (int dx = -dismountDistance; dx <= dismountDistance; ++dx) {
            for (int dz = -dismountDistance; dz < dismountDistance; ++dz) {
                if (dx != 0 || dz != 0)  {
                    AxisAlignedBB axisalignedbb = riddenEntity.boundingBox.getOffsetBoundingBox((double)dx, 1.0D, (double)dz);
                    if (riddenEntity.worldObj.func_147461_a(axisalignedbb).isEmpty()) { 
                        int xPos = (int)(riddenEntity.posX + (double)dx);
                        int zPos = (int)(riddenEntity.posZ + (double)dz);
                        if (riddenEntity.worldObj.isAirBlock(xPos, (int)riddenEntity.posY, zPos)) {
                        	((EntityLivingBase) riddenEntity).setPositionAndUpdate(
                        			riddenEntity.posX + (double)dx, riddenEntity.posY + 1.0D, riddenEntity.posZ + (double)dz);
                        	return;
                        }
                    }
                }
            }
        }
        ((EntityLivingBase) riddenEntity).setPositionAndUpdate(x, y, z);
    }
}
