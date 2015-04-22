package net.wildbill22.draco.stats;

import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;
import net.wildbill22.draco.items.ModItems;

public class ModStats {
	// Achievements
	public static Achievement firstGoldCoin;
	public static Achievement levelTenDragon;
	
	// See AchievementList.java for vanilla achievements
	public static void init() {
		// localized name, nonLocalizedName, x,y (in achievement page), item or block to display on page, see AchivementList.java for rest
		firstGoldCoin = new Achievement("achievement.firstGoldCoin", "firstGoldCoin", 0, 0, 
				new ItemStack(ModItems.goldCoin), (Achievement)null).initIndependentStat().registerStat();
		levelTenDragon = new Achievement("achievement.levelTenDragon", "levelTenDragon", 1, 2, 
				new ItemStack(ModItems.goldCoin), firstGoldCoin).registerStat();

		AchievementPage.registerAchievementPage(new AchievementPage("Draco Animus", 
				new Achievement[] { firstGoldCoin, levelTenDragon }
		));
	}
}
