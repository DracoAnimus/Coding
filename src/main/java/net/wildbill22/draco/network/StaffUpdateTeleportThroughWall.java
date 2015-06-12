package net.wildbill22.draco.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.util.Vec3;
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
	double maxD;

	/**
	 * Don't use
	 */
	public StaffUpdateTeleportThroughWall() {	}

	public StaffUpdateTeleportThroughWall(double x, double y, double z, double maxD) {
		this.x = x; this.y = y; this.z = z;
		this.maxD = maxD;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		this.x = buf.readDouble();
		this.y = buf.readDouble();
		this.z = buf.readDouble();
		this.maxD = buf.readDouble();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeDouble(this.x);
		buf.writeDouble(this.y);
		buf.writeDouble(this.z);
		buf.writeDouble(this.maxD);
	}
	
	// Runs on server, teleports player
	public static class Handler implements IMessageHandler<StaffUpdateTeleportThroughWall, IMessage> {
		@Override
		public IMessage onMessage(StaffUpdateTeleportThroughWall message, MessageContext ctx) {
			EntityPlayer player = ctx.getServerHandler().playerEntity;
			if (player != null) {
				double minD = 3.0D;
			    Vec3 currentPos = player.getPosition(0);
//			    currentPos.yCoord += player.eyeHeight;
			    Vec3 lookVec = Vec3.createVectorHelper(message.x, message.y, message.z);
				for (double d0 = message.maxD; d0 >= minD; d0--) {
				    Vec3 lookPos = currentPos.addVector(lookVec.xCoord * d0, lookVec.yCoord * d0, lookVec.zCoord * d0);
				    if (lookPos.yCoord >= 10 && lookPos.yCoord <= 250) {
				    	if (!isVecInsideOpaqueBlock(player, lookPos)) {
				    		player.capabilities.isFlying = true;
				    		player.setPositionAndUpdate(lookPos.xCoord, lookPos.yCoord - 0.0, lookPos.zCoord);
				    		if (player.getBrightness(0) < 8) {
				    			int amplifier = Math.max(1, DragonPlayer.get(player).getLevel() / 2);
				    			LogHelper.info("ItemDragonStaff: Amplifier = " + amplifier);
				    			player.addPotionEffect(new PotionEffect(Potion.nightVision.getId(), (60 * 20 * amplifier), amplifier));
				    		}
				    		LogHelper.info("Teleported to: " + lookPos.xCoord + "," + lookPos.yCoord + "," + lookPos.zCoord);
				    		return null;
					    }
				    }
				}
				player.addChatMessage(new ChatComponentText(
						StatCollector.translateToLocalFormatted("chat.wildbill22_draco.StaffUpdateTeleportThroughWall", message.maxD)));				
//				player.addChatMessage(new ChatComponentText("Only solid blocks in that direction for the next " + message.maxD + " meters!"));
			}
			return null;
		}
	}
	
    /**
     * Checks if this entity will be inside of an opaque block at the new location
     * FIXME: Fix this so it checks better!
     * Currently this allows player to teleport up into a block!
     */
    public static boolean isVecInsideOpaqueBlock(EntityPlayer player, Vec3 newLocation) {
		LogHelper.info("Trying to teleport to: " + newLocation.xCoord + "," + newLocation.yCoord + "," + newLocation.zCoord);
    	float width = Math.abs((float) (player.boundingBox.maxX - player.boundingBox.minX));
    	float heigth = Math.abs((float) (player.boundingBox.maxY - player.boundingBox.minY));
    	float minOffset = -0.1F;
    	float maxOffset = 1.1F;
    	float step = (maxOffset - minOffset) / 3;
        for (float i = minOffset; i <= maxOffset + 0.01F; i += step) {
            float yOffset = i * heigth;
            if (isNormalCubeAtLocation(player, newLocation, minOffset * width, yOffset, minOffset * width)
            		 || isNormalCubeAtLocation(player, newLocation, maxOffset * width, yOffset, minOffset * width)
            		 || isNormalCubeAtLocation(player, newLocation, minOffset * width, yOffset, maxOffset * width)
            		 || isNormalCubeAtLocation(player, newLocation, maxOffset * width, yOffset, maxOffset * width))
            	return true;
        }
        return false;
    }
    
    private static boolean isNormalCubeAtLocation(EntityPlayer player, Vec3 newLocation, float xOffset, float yOffset, float zOffset) {
	    int x = MathHelper.floor_double(newLocation.xCoord + (double)xOffset);
	    int y = MathHelper.floor_double(newLocation.yCoord + (double)yOffset);
	    int z = MathHelper.floor_double(newLocation.zCoord + (double)zOffset);
		LogHelper.info("Checking location: " + x + "," + y + "," + z);
	    return player.worldObj.getBlock(x, y, z).isNormalCube();
    }
}