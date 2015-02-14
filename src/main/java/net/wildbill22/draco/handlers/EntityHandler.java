package net.wildbill22.draco.handlers;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.wildbill22.draco.Core;
import net.wildbill22.draco.Creative_Tab;
import cpw.mods.fml.common.registry.EntityRegistry;

/**
 * @author WILLIAM
 *
 */
public class EntityHandler 
{
	public static void registerMonsters(Class<? extends Entity> entity, String name){
		EntityRegistry.registerModEntity(entity, name, 5, Core.instance, 10, 10, true);
	}	

	@SuppressWarnings("unchecked")
	public static void registerEntityEgg(Class<? extends Entity> entity, int primaryColor, int secondaryColor){
		int entityId = EntityRegistry.findGlobalUniqueEntityId();
		EntityList.IDtoClassMapping.put(entityId, entity);
		EntityList.entityEggs.put(entityId, new EntityList.EntityEggInfo(entityId, primaryColor, secondaryColor));
		// TODO: Set tab? setCreativeTab(Creative_Tab.TabDraco_Animus);
	}	
}
