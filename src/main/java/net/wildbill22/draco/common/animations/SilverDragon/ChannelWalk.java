package net.wildbill22.draco.common.animations.SilverDragon;

import net.wildbill22.draco.common.MCACommonLibrary.animation.*;
import net.wildbill22.draco.common.MCACommonLibrary.math.*;

public class ChannelWalk extends Channel {
	public ChannelWalk(String _name, float _fps, int _totalFrames, byte _mode) {
		super(_name, _fps, _totalFrames, _mode);
	}

	@Override
	protected void initializeAllFrames() {
KeyFrame frame0 = new KeyFrame();
frame0.modelRenderersRotations.put("neck", new Quaternion(-0.3416946F, 0.040988814F, 0.014918708F, 0.93879825F));
frame0.modelRenderersRotations.put("rWing1", new Quaternion(-0.10145815F, -0.15023354F, -0.14436235F, 0.9727773F));
frame0.modelRenderersRotations.put("leftLeg1", new Quaternion(-0.5F, 0.0F, 0.0F, 0.8660254F));
frame0.modelRenderersRotations.put("tail2", new Quaternion(0.01745174F, 0.008725206F, -1.5229904E-4F, 0.9998096F));
frame0.modelRenderersRotations.put("rightLeg1", new Quaternion(-0.1305262F, 0.0F, 0.0F, 0.9914449F));
frame0.modelRenderersRotations.put("backLeftLeg1", new Quaternion(-0.1305262F, 0.0F, 0.0F, 0.9914449F));
frame0.modelRenderersRotations.put("backRightLeg1", new Quaternion(-0.5F, 0.0F, 0.0F, 0.8660254F));
frame0.modelRenderersRotations.put("tail4", new Quaternion(0.008014653F, 0.07837721F, -0.009383952F, 0.99684733F));
frame0.modelRenderersRotations.put("tail1", new Quaternion(0.0F, -0.06104854F, 0.0F, 0.9981348F));
frame0.modelRenderersRotations.put("tail3", new Quaternion(0.008649721F, 0.008649721F, -0.00880202F, 0.9998864F));
frame0.modelRenderersRotations.put("lWing1", new Quaternion(-0.10145815F, 0.15023354F, 0.14436235F, 0.9727773F));
frame0.modelRenderersTranslations.put("neck", new Vector3f(-3.0F, -0.5F, 14.0F));
frame0.modelRenderersTranslations.put("rWing1", new Vector3f(-9.0F, 2.0F, 13.0F));
frame0.modelRenderersTranslations.put("leftLeg1", new Vector3f(1.5F, -3.5F, 6.8F));
frame0.modelRenderersTranslations.put("tail2", new Vector3f(0.0F, 0.0F, -10.0F));
frame0.modelRenderersTranslations.put("rightLeg1", new Vector3f(-7.5F, -3.5F, 6.8F));
frame0.modelRenderersTranslations.put("backLeftLeg1", new Vector3f(1.5F, -1.0999999F, -26.8F));
frame0.modelRenderersTranslations.put("backRightLeg1", new Vector3f(-7.5F, -1.0999999F, -26.8F));
frame0.modelRenderersTranslations.put("tail4", new Vector3f(0.0F, 0.0F, -10.0F));
frame0.modelRenderersTranslations.put("tail1", new Vector3f(-3.0F, 0.0F, -28.0F));
frame0.modelRenderersTranslations.put("tail3", new Vector3f(0.0F, 0.0F, -10.0F));
frame0.modelRenderersTranslations.put("lWing1", new Vector3f(3.0F, 2.0F, 13.0F));
keyFrames.put(0, frame0);

KeyFrame frame1 = new KeyFrame();
frame1.modelRenderersRotations.put("neck", new Quaternion(-0.3416946F, -0.040988814F, -0.014918708F, 0.93879825F));
frame1.modelRenderersRotations.put("rWing1", new Quaternion(-0.1252769F, -0.14492337F, -0.15664662F, 0.96889865F));
frame1.modelRenderersRotations.put("leftLeg1", new Quaternion(-0.1305262F, 0.0F, 0.0F, 0.9914449F));
frame1.modelRenderersRotations.put("tail2", new Quaternion(-0.008014653F, -0.07837721F, -0.009383952F, 0.99684733F));
frame1.modelRenderersRotations.put("rightLeg1", new Quaternion(-0.5F, 0.0F, 0.0F, 0.8660254F));
frame1.modelRenderersRotations.put("backLeftLeg1", new Quaternion(-0.5F, 0.0F, 0.0F, 0.8660254F));
frame1.modelRenderersRotations.put("backRightLeg1", new Quaternion(-0.1305262F, 0.0F, 0.0F, 0.9914449F));
frame1.modelRenderersRotations.put("tail4", new Quaternion(0.0088771675F, -0.017527217F, -0.00857258F, 0.9997702F));
frame1.modelRenderersRotations.put("tail1", new Quaternion(0.0F, 0.043619387F, 0.0F, 0.99904823F));
frame1.modelRenderersRotations.put("tail3", new Quaternion(0.008419302F, 0.017296808F, -0.017601358F, 0.99966F));
frame1.modelRenderersRotations.put("lWing1", new Quaternion(-0.12608352F, 0.14566462F, 0.15244755F, 0.9693526F));
frame1.modelRenderersTranslations.put("neck", new Vector3f(-3.0F, -0.5F, 14.0F));
frame1.modelRenderersTranslations.put("rWing1", new Vector3f(-9.0F, 2.0F, 13.0F));
frame1.modelRenderersTranslations.put("leftLeg1", new Vector3f(1.5F, -3.5F, 6.8F));
frame1.modelRenderersTranslations.put("tail2", new Vector3f(0.0F, 0.0F, -10.0F));
frame1.modelRenderersTranslations.put("rightLeg1", new Vector3f(-7.5F, -3.5F, 6.8F));
frame1.modelRenderersTranslations.put("backLeftLeg1", new Vector3f(1.5F, -1.0999999F, -26.8F));
frame1.modelRenderersTranslations.put("backRightLeg1", new Vector3f(-7.5F, -1.0999999F, -26.8F));
frame1.modelRenderersTranslations.put("tail4", new Vector3f(0.0F, 0.0F, -10.0F));
frame1.modelRenderersTranslations.put("tail1", new Vector3f(-3.0F, 0.0F, -28.0F));
frame1.modelRenderersTranslations.put("tail3", new Vector3f(0.0F, 0.0F, -10.0F));
frame1.modelRenderersTranslations.put("lWing1", new Vector3f(3.0F, 2.0F, 13.0F));
keyFrames.put(1, frame1);

KeyFrame frame2 = new KeyFrame();
frame2.modelRenderersRotations.put("neck", new Quaternion(-0.3416946F, 0.040988814F, 0.014918708F, 0.93879825F));
frame2.modelRenderersRotations.put("rWing1", new Quaternion(-0.10145815F, -0.15023354F, -0.14436235F, 0.9727773F));
frame2.modelRenderersRotations.put("leftLeg1", new Quaternion(-0.5F, 0.0F, 0.0F, 0.8660254F));
frame2.modelRenderersRotations.put("tail2", new Quaternion(0.01745174F, 0.008725206F, -1.5229904E-4F, 0.9998096F));
frame2.modelRenderersRotations.put("rightLeg1", new Quaternion(-0.1305262F, 0.0F, 0.0F, 0.9914449F));
frame2.modelRenderersRotations.put("backLeftLeg1", new Quaternion(-0.1305262F, 0.0F, 0.0F, 0.9914449F));
frame2.modelRenderersRotations.put("backRightLeg1", new Quaternion(-0.5F, 0.0F, 0.0F, 0.8660254F));
frame2.modelRenderersRotations.put("tail4", new Quaternion(0.008096238F, 0.06967519F, -0.009313655F, 0.9974934F));
frame2.modelRenderersRotations.put("tail1", new Quaternion(0.0F, -0.06104854F, 0.0F, 0.9981348F));
frame2.modelRenderersRotations.put("tail3", new Quaternion(0.008649721F, 0.008649721F, -0.00880202F, 0.9998864F));
frame2.modelRenderersRotations.put("lWing1", new Quaternion(-0.10145815F, 0.15023354F, 0.14436235F, 0.9727773F));
frame2.modelRenderersTranslations.put("neck", new Vector3f(-3.0F, -0.5F, 14.0F));
frame2.modelRenderersTranslations.put("rWing1", new Vector3f(-9.0F, 2.0F, 13.0F));
frame2.modelRenderersTranslations.put("leftLeg1", new Vector3f(1.5F, -3.5F, 6.8F));
frame2.modelRenderersTranslations.put("tail2", new Vector3f(0.0F, 0.0F, -10.0F));
frame2.modelRenderersTranslations.put("rightLeg1", new Vector3f(-7.5F, -3.5F, 6.8F));
frame2.modelRenderersTranslations.put("backLeftLeg1", new Vector3f(1.5F, -1.0999999F, -26.8F));
frame2.modelRenderersTranslations.put("backRightLeg1", new Vector3f(-7.5F, -1.0999999F, -26.8F));
frame2.modelRenderersTranslations.put("tail4", new Vector3f(0.0F, 0.0F, -10.0F));
frame2.modelRenderersTranslations.put("tail1", new Vector3f(-3.0F, 0.0F, -28.0F));
frame2.modelRenderersTranslations.put("tail3", new Vector3f(0.0F, 0.0F, -10.0F));
frame2.modelRenderersTranslations.put("lWing1", new Vector3f(3.0F, 2.0F, 13.0F));
keyFrames.put(2, frame2);

}
}