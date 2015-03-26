package net.wildbill22.draco.common.animations.SilverDragon;

import net.wildbill22.draco.common.MCACommonLibrary.animation.*;
import net.wildbill22.draco.common.MCACommonLibrary.math.*;

public class ChannelIdle extends Channel {
	public ChannelIdle(String _name, float _fps, int _totalFrames, byte _mode) {
		super(_name, _fps, _totalFrames, _mode);
	}

	@Override
	protected void initializeAllFrames() {
		KeyFrame frame0 = new KeyFrame();
		frame0.modelRenderersRotations.put("neck", new Quaternion(-0.29232717F, 0.016689818F, 0.0051025893F, 0.9561592F));
		frame0.modelRenderersRotations.put("rWing1", new Quaternion(0.010408054F, -0.010408053F, -0.2163488F, 0.9762051F));
		frame0.modelRenderersRotations.put("tail1", new Quaternion(0.0F, -0.06104854F, 0.0F, 0.9981348F));
		frame0.modelRenderersRotations.put("lWing1", new Quaternion(-0.013440423F, 0.013440422F, 0.2081463F, 0.977913F));
		frame0.modelRenderersTranslations.put("neck", new Vector3f(-3.0F, -0.5F, 14.0F));
		frame0.modelRenderersTranslations.put("rWing1", new Vector3f(-9.0F, 2.0F, 13.0F));
		frame0.modelRenderersTranslations.put("tail1", new Vector3f(-3.0F, 0.0F, -28.0F));
		frame0.modelRenderersTranslations.put("lWing1", new Vector3f(3.0F, 2.0F, 13.0F));
		keyFrames.put(0, frame0);

		KeyFrame frame1 = new KeyFrame();
		frame1.modelRenderersRotations.put("neck", new Quaternion(-0.33388686F, -0.01936327F, 0.0023992308F, 0.94241124F));
		frame1.modelRenderersRotations.put("rWing1", new Quaternion(-0.006448006F, -0.0064480053F, -0.23350163F, 0.97231364F));
		frame1.modelRenderersRotations.put("lWing1", new Quaternion(-0.010465519F, -0.0104655195F, 0.2248597F, 0.9742787F));
		frame1.modelRenderersTranslations.put("neck", new Vector3f(-3.0F, -0.5F, 14.0F));
		frame1.modelRenderersTranslations.put("rWing1", new Vector3f(-9.0F, 2.0F, 13.0F));
		frame1.modelRenderersTranslations.put("lWing1", new Vector3f(3.0F, 2.0F, 13.0F));
		keyFrames.put(1, frame1);

		KeyFrame frame2 = new KeyFrame();
		frame2.modelRenderersRotations.put("neck", new Quaternion(-0.35015404F, 0.0061119613F, -0.016347183F, 0.9365296F));
		frame2.modelRenderersRotations.put("rWing1", new Quaternion(-0.010290746F, 0.0102907475F, -0.1992781F, 0.97983485F));
		frame2.modelRenderersRotations.put("tail1", new Quaternion(0.0F, -0.02617695F, 0.0F, 0.99965733F));
		frame2.modelRenderersRotations.put("lWing1", new Quaternion(0.0047411365F, -0.015149584F, 0.2165471F, 0.9761431F));
		frame2.modelRenderersTranslations.put("neck", new Vector3f(-3.0F, -0.5F, 14.0F));
		frame2.modelRenderersTranslations.put("rWing1", new Vector3f(-9.0F, 2.0F, 13.0F));
		frame2.modelRenderersTranslations.put("tail1", new Vector3f(-3.0F, 0.0F, -28.0F));
		frame2.modelRenderersTranslations.put("lWing1", new Vector3f(3.0F, 2.0F, 13.0F));
		keyFrames.put(2, frame2);
		
		KeyFrame frame3 = new KeyFrame();
		frame3.modelRenderersRotations.put("neck", new Quaternion(-0.32583895F, -0.030427022F, 0.007974836F, 0.9449019F));
		frame3.modelRenderersRotations.put("rWing1", new Quaternion(0.012162961F, -0.018884452F, -0.20772316F, 0.9779298F));
		frame3.modelRenderersRotations.put("lWing1", new Quaternion(0.015361864F, -0.0050707273F, 0.19947919F, 0.97976846F));
		frame3.modelRenderersTranslations.put("neck", new Vector3f(-3.0F, -0.5F, 14.0F));
		frame3.modelRenderersTranslations.put("rWing1", new Vector3f(-9.0F, 2.0F, 13.0F));
		frame3.modelRenderersTranslations.put("lWing1", new Vector3f(3.0F, 2.0F, 13.0F));
		keyFrames.put(3, frame3);
		
		KeyFrame frame4 = new KeyFrame();
		frame4.modelRenderersRotations.put("neck", new Quaternion(-0.31735268F, -0.01104413F, 0.0055064F, 0.9482273F));
		frame4.modelRenderersRotations.put("rWing1", new Quaternion(0.0F, 0.0F, -0.21643962F, 0.976296F));
		frame4.modelRenderersRotations.put("tail1", new Quaternion(0.0F, 0.043619387F, 0.0F, 0.99904823F));
		frame4.modelRenderersRotations.put("lWing1", new Quaternion(0.0F, 0.0F, 0.21643962F, 0.976296F));
		frame4.modelRenderersTranslations.put("neck", new Vector3f(-3.0F, -0.5F, 14.0F));
		frame4.modelRenderersTranslations.put("rWing1", new Vector3f(-9.0F, 2.0F, 13.0F));
		frame4.modelRenderersTranslations.put("tail1", new Vector3f(-3.0F, 0.0F, -28.0F));
		frame4.modelRenderersTranslations.put("lWing1", new Vector3f(3.0F, 2.0F, 13.0F));
		keyFrames.put(4, frame4);
		
		KeyFrame frame5 = new KeyFrame();
		frame5.modelRenderersRotations.put("neck", new Quaternion(-0.34202012F, 0.0F, 0.0F, 0.9396926F));
		frame5.modelRenderersTranslations.put("neck", new Vector3f(-3.0F, -0.5F, 14.0F));
		keyFrames.put(5, frame5);
	}
}