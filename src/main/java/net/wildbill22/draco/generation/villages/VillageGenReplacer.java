package net.wildbill22.draco.generation.villages;

import java.lang.reflect.Field;

import net.minecraftforge.event.terraingen.InitMapGenEvent;
import net.minecraftforge.event.terraingen.InitMapGenEvent.EventType;
import net.wildbill22.draco.Configs;
import net.wildbill22.draco.lib.LogHelper;
import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.eventhandler.IEventListener;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

/**
 * VillageGenReplacer class updates fields (through Java reflection) to change
 * how villages are generated.
 * 
 * @author WILLIAM
 *
 */
public class VillageGenReplacer implements IEventListener {

	@Override
	@SubscribeEvent
	public void invoke(Event event) {
		if (event instanceof InitMapGenEvent) {
			InitMapGenEvent e = (InitMapGenEvent) event;
			if (e.type == EventType.VILLAGE) {
				if (!(e.newGen == e.originalGen)) {
					LogHelper.error("VillageGenReplacer: " +
							"The village map generator was overwritten by another mod. There might be crashes! \n The new generator class is "
									+ e.getClass().getCanonicalName());
				}

				try { // Here be reflections.
					Field type = null;
					Field density = null;
					Field minDist = null;

					Field[] fields = e.newGen.getClass().getDeclaredFields();
					for (Field f : fields) {
						String name = f.getName();
						if (name.equals("terrainType")) {
							type = f;
						} else if (name.equals("field_82665_g")) {
							density = f;
						} else if (name.equals("field_82666_h")) {
							minDist = f;
						}
					}

					if (type != null) {
						type.setAccessible(true);
						type.setInt(e.newGen, Configs.VILLAGE.village_size);
					}
					if (density != null) {
						density.setAccessible(true);
						density.setInt(e.newGen, Configs.VILLAGE.village_density);
					}
					if (minDist != null) {
						minDist.setAccessible(true);
						minDist.setInt(e.newGen, Configs.VILLAGE.village_minDist);
					}
					LogHelper.info("VillageGenReplacer: Modified MapGenVillage fields.");
				} catch (Exception exc) {
					LogHelper.error("VillageGenReplacer: Could not modify MapGenVillage, consider disabling Village Density in VillageDensity.cfg");
					exc.printStackTrace();
				}
			}
		}
	}
}