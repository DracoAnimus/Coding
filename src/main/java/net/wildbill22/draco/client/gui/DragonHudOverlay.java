package net.wildbill22.draco.client.gui;

import java.awt.Color;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.wildbill22.draco.entities.player.DragonPlayer;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class DragonHudOverlay extends Gui {
	private Minecraft mc;
	
	public DragonHudOverlay(Minecraft mc) {
		this.mc = mc;
	}

	@SubscribeEvent
	public void onRenderExperienceBar(RenderGameOverlayEvent.Post event) {
		if (event.type != ElementType.EXPERIENCE) {
			return;
		}
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glDisable(GL11.GL_LIGHTING);

		int level = DragonPlayer.get(mc.thePlayer).getLevel();

		if (mc.playerController.gameIsSurvivalOrAdventure() && level > 0) {
			mc.mcProfiler.startSection("dragonLevel");
			int color = Color.MAGENTA.getRGB();
			String text = "" + level;
			int x = (event.resolution.getScaledWidth() - mc.fontRenderer.getStringWidth(text)) / 2;
			int y = event.resolution.getScaledHeight() - 31 - 4 - 12;
			mc.fontRenderer.drawString(text, x, y, color);
			mc.mcProfiler.endSection();
		}
	}
}
