package net.wildbill22.draco.network;

import java.util.Iterator;
import java.util.List;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
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
public class StaffUpdateWitherTarget implements IMessage {
	double x, y, z;

	/**
	 * Don't use
	 */
	public StaffUpdateWitherTarget() {	}

	public StaffUpdateWitherTarget(double x, double y, double z) {
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
	
	public static class Handler implements IMessageHandler<StaffUpdateWitherTarget, IMessage> {
		@Override
		public IMessage onMessage(StaffUpdateWitherTarget message, MessageContext ctx) {
			EntityPlayer player = ctx.getServerHandler().playerEntity;
			if (player != null) {
				int amplifier = Math.min(1, DragonPlayer.get(player).getLevel() / 3);
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
						// Attack
                        ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(Potion.wither.id, 80 + 20 * amplifier * 6, amplifier));
				        player.worldObj.playAuxSFXAtEntity((EntityPlayer)null, 1014, (int)entity.posX, (int)entity.posY, (int)entity.posZ, 0);
//						player.worldObj.playSoundAtEntity(entity, "minecraft:mob.ghast.scream", 0.4F, 1.0F);
		            	LogHelper.info("Used wither effect on entity!");
		            }
		        }
			}
			return null;
		}
	}
}
