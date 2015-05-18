package net.wildbill22.draco.entities.dragons;

import java.util.Map;

import com.google.common.collect.Maps;

public class DragonRegistry {
	private static final DragonRegistry INSTANCE = new DragonRegistry();
	
//	private Map<Class<?>, IDragonCreationHandler> dragonCreationHandlers = Maps.newHashMap(); 
	private Map<String, IDragonRendererCreationHandler> dragonRendererCreationHandlers = Maps.newHashMap(); 
//	private Map<Class<?>, IDragonModelCreationHandler> dragonModelCreationHandlers = Maps.newHashMap(); 
	
	public static DragonRegistry instance() {
		return INSTANCE;
	}
	
//	public void registerDragonCreationHandler(IDragonCreationHandler handler) {
//		dragonCreationHandlers.put(handler.getDragonClass(), handler);
//	}
	
	public void registerDragonRendererCreationHandler(IDragonRendererCreationHandler handler) {
		dragonRendererCreationHandlers.put(handler.getKey(), handler);
	}
	
	public IDragonRendererCreationHandler getDragonRenderer(String key) {
		return  dragonRendererCreationHandlers.get(key);
	}
	
//	public void registerDragonModelCreationHandler(IDragonModelCreationHandler handler) {
//		dragonModelCreationHandlers.put(handler.getDragonModelClass(), handler);
//	}
	
//	public interface IDragonCreationHandler {
//		/**
//         * The class of the dragon
//         */
//        Class<?> getDragonClass();
//	}

	public interface IDragonRendererCreationHandler {
		/**
         * The class of the dragon renderer
         */
        String getKey();
	}
	
//	public interface IDragonModelCreationHandler {
//		/**
//         * The class of the dragon model
//         */
//        Class<?> getDragonModelClass();
//	}	
}
