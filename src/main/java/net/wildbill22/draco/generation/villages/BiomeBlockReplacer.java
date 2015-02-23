package net.wildbill22.draco.generation.villages;

import java.util.List;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.event.terraingen.BiomeEvent;
import net.wildbill22.draco.lib.LogHelper;
import net.wildbill22.draco.lib.Pair;
import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.eventhandler.IEventListener;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

/**
 * Replaces blocks used to build village with blocks defined in the
 * VillageBiomes.cfg
 * 
 * @author WILLIAM
 *
 */
public class BiomeBlockReplacer implements IEventListener {

	private static boolean checkCondition(BiomeGenBase biome, String condition) {
		if (biome == null || condition == null)
			return false;
		try {
			if (condition.startsWith("b:")) {
				String identifier = condition.substring("b:".length());
				return identifier.equalsIgnoreCase(biome.biomeName) || identifier.equals(String.valueOf(biome.biomeID));
			}
			if (condition.startsWith("t:")) {
				String identifier = condition.substring("t:".length());
				Type type = Type.valueOf(identifier);
				return type != null && BiomeDictionary.isBiomeOfType(biome, type);
			}
			return false;
		} catch (NullPointerException e) {
			LogHelper.warn("BiomeBlockReplacer: NullPointerException when replacing blocks:");
			e.printStackTrace();
			// VillageBiomes.log.warning("Something was NULL when replacing blocks.");
			LogHelper.warn("BiomeBlockReplacer: Biome class: " + biome.getClass() + "; Condition: " + condition);
			return false;
		}
	}
	private Map<Block, List<Pair<String, Block>>> replacements;

	private Map<Block, List<Pair<String, Integer>>> metadata;

	public BiomeBlockReplacer() {
		replacements = ConfigHandler.getReplacements();
		metadata = ConfigHandler.getMetadata();
	}

	@Override
	@SubscribeEvent
	public void invoke(Event event) {

		if (event instanceof BiomeEvent.GetVillageBlockID) {
			BiomeEvent.GetVillageBlockID ev = (BiomeEvent.GetVillageBlockID) event;
			if (replacements.containsKey(ev.original)) {
				for (Pair<String, Block> pair : replacements.get(ev.original)) {
					if (checkCondition(ev.biome, pair.left)) {
						ev.replacement = pair.right;
						ev.setResult(Event.Result.DENY);
					}
				}
			}

		}
		if (event instanceof BiomeEvent.GetVillageBlockMeta) {
			BiomeEvent.GetVillageBlockMeta ev = (BiomeEvent.GetVillageBlockMeta) event;
			boolean replaced = false;
			if (metadata.containsKey(ev.original)) {
				for (Pair<String, Integer> pair : metadata.get(ev.original)) {
					if (checkCondition(ev.biome, pair.left)) {
						ev.replacement = pair.right;
						ev.setResult(Event.Result.DENY);
						replaced = true;
					}
				}
			}
			if (!replaced && replacements.containsKey(ev.original)) {
				for (Pair<String, Block> pair : replacements.get(ev.original)) {
					if (checkCondition(ev.biome, pair.left)) {
						ev.setResult(Event.Result.ALLOW);
					}
				}
			}
		}
	}
}
