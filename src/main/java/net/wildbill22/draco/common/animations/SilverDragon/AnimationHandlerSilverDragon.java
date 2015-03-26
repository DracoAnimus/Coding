package net.wildbill22.draco.common.animations.SilverDragon;

import java.util.HashMap;

import net.wildbill22.draco.common.MCACommonLibrary.IMCAnimatedEntity;
import net.wildbill22.draco.common.MCACommonLibrary.animation.AnimationHandler;
import net.wildbill22.draco.common.MCACommonLibrary.animation.Channel;

public class AnimationHandlerSilverDragon extends AnimationHandler {
	/** Map with all the animations. */
	public static HashMap<String, Channel> animChannels = new HashMap<String, Channel>();
	static
	{
		animChannels.put("Fly", new ChannelFly("Fly", 2.0F, 2, Channel.LOOP));
		animChannels.put("Walk", new ChannelWalk("Walk", 3.0F, 3, Channel.LOOP));
		animChannels.put("Idle", new ChannelIdle("Idle", 2.0F, 5, Channel.LINEAR));
	}
	
	public AnimationHandlerSilverDragon(IMCAnimatedEntity entity) {
		super(entity);
	}

	@Override
	public void activateAnimation(String name, float startingFrame) {
		super.activateAnimation(animChannels, name, startingFrame);
	}

	@Override
	public void stopAnimation(String name) {
		super.stopAnimation(animChannels, name);
	}

	@Override
	public void fireAnimationEventClientSide(Channel anim, float prevFrame, float frame) {
	}

	@Override
	public void fireAnimationEventServerSide(Channel anim, float prevFrame, float frame) {
	}
}