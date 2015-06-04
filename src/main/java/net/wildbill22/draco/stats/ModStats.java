package net.wildbill22.draco.stats;

import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;
import net.wildbill22.draco.items.ModItems;

public class ModStats {
	// Achievements
	public static Achievement firstGoldCoin;
	public static Achievement levelTenDragon;
	public static Achievement foundAllDragonEggs;
	
	// See AchievementList.java for vanilla achievements
	public static void init() {
		// localized name, nonLocalizedName, x,y (in achievement page), item or block to display on page, see AchivementList.java for rest
		firstGoldCoin = new Achievement("achievement.firstGoldCoin", "firstGoldCoin", 0, 0, 
				new ItemStack(ModItems.goldCoin), (Achievement)null).initIndependentStat();
		levelTenDragon = new Achievement("achievement.levelTenDragon", "levelTenDragon", 1, 2, 
				new ItemStack(ModItems.goldCoin), firstGoldCoin);
		foundAllDragonEggs = new Achievement("achievement.foundAllDragonEggs", "foundAllDragonEggs", 1, 4, 
				new ItemStack(ModItems.earthDragonEgg), levelTenDragon);
		
		firstGoldCoin.registerStat();
		levelTenDragon.registerStat();
		foundAllDragonEggs.registerStat();
		
		AchievementPage.registerAchievementPage(new AchievementPage("Draco Animus", 
//				new Achievement[] { firstGoldCoin, levelTenDragon }
				new Achievement[] { firstGoldCoin, levelTenDragon, foundAllDragonEggs }
		));
	}
}
