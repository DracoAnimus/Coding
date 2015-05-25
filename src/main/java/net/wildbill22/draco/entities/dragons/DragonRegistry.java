package net.wildbill22.draco.entities.dragons;

import java.util.Map;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.google.common.collect.Maps;

public class DragonRegistry {
	private static final DragonRegistry INSTANCE = new DragonRegistry();
	
//	private Map<Class<?>, IDragonCreationHandler> dragonCreationHandlers = Maps.newHashMap(); 
	private Map<String, IDragonRendererCreationHandler> dragonRendererCreationHandlers = Maps.newHashMap(); 
	public Map<String, IDragonEggHandler> dragonEggs = Maps.newHashMap(); 
	private Map<Integer, IDragonEggHandler> dragonEggHandlers = Maps.newHashMap(); 
//	private Map<String, IDragonStaffHandler> dragonStaffs = Maps.newHashMap(); 
	
	public static DragonRegistry instance() {
		return INSTANCE;
	}
	
	public void registerDragonRendererCreationHandler(IDragonRendererCreationHandler handler) {
		dragonRendererCreationHandlers.put(handler.getKey(), handler);
	}
	
	public IDragonRendererCreationHandler getDragonRenderer(String key) {
		return  dragonRendererCreationHandlers.get(key);
	}
	
	public interface IDragonRendererCreationHandler {
		/**
         * The dragon name
         */
        String getKey();
	}

	public void registerDragonEgg(IDragonEggHandler handler) {
		dragonEggs.put(handler.getEggName(), handler);
		dragonEggHandlers.put(dragonEggHandlers.size(), handler);
	}
	
	public IDragonEggHandler getDragonEgg(String key) {
		return  dragonEggs.get(key);
	}
	
	public IDragonEggHandler getDragonEggHandler(int i) {
		return dragonEggHandlers.get(i);
	}
	
	public int getNumEggs() {
		return dragonEggs.size();
	}
	
	public interface IDragonEggHandler {
		/**
         * The dragon egg name
         */
        String getEggName();

		/**
         * The dragon staff for this dragon egg
         */
        ItemStack getStaffItemStack();
                
		/**
         * The dragon egg item
         */
		Item getEggItem();
	}
	
	public interface IDragonStaffHandler {
		/**
         * The dragon egg name
         */
        String getEggName();
	}	
}
