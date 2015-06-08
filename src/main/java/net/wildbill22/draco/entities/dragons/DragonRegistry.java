package net.wildbill22.draco.entities.dragons;

import java.util.Map;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.wildbill22.draco.models.dragons.ModelDracoAqua;
import net.wildbill22.draco.models.dragons.ModelDracoIgnis;
import net.wildbill22.draco.models.dragons.ModelDracoMortem;
import net.wildbill22.draco.models.dragons.ModelDracoTenebrosus;
import net.wildbill22.draco.models.dragons.ModelDracoTerra;
import net.wildbill22.draco.models.dragons.ModelSilverDragon;
import net.wildbill22.draco.render.dragons.RenderDracoAqua;
import net.wildbill22.draco.render.dragons.RenderDracoIgnis;
import net.wildbill22.draco.render.dragons.RenderDracoMortem;
import net.wildbill22.draco.render.dragons.RenderDracoTenebrosus;
import net.wildbill22.draco.render.dragons.RenderDracoTerra;
import net.wildbill22.draco.render.dragons.RenderSilverDragon;

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
	
	public static void init() {
		RenderSilverDragon sdHandler = new RenderSilverDragon(new ModelSilverDragon(), 0.5F, 0); // Silver
		DragonRegistry.instance().registerDragonRendererCreationHandler(sdHandler);
		RenderDracoMortem dmHandler = new RenderDracoMortem(new ModelDracoMortem(), 0.5F);       // Skeleton Dragon
		DragonRegistry.instance().registerDragonRendererCreationHandler(dmHandler);
		RenderDracoAqua adHandler = new RenderDracoAqua(new ModelDracoAqua(), 0.5F);             // Water Dragon
		DragonRegistry.instance().registerDragonRendererCreationHandler(adHandler);
		RenderSilverDragon gdHandler = new RenderSilverDragon(new ModelSilverDragon(), 0.5F, 1); // Gold
		DragonRegistry.instance().registerDragonRendererCreationHandler(gdHandler);
		RenderDracoTerra tdHandler = new RenderDracoTerra(new ModelDracoTerra(), 0.5F);          // Earth Dragon
		DragonRegistry.instance().registerDragonRendererCreationHandler(tdHandler);
		RenderDracoTenebrosus dtHandler = new RenderDracoTenebrosus(new ModelDracoTenebrosus(), 0.5F); // Night Dragon
		DragonRegistry.instance().registerDragonRendererCreationHandler(dtHandler);
		RenderDracoIgnis idHandler = new RenderDracoIgnis(new ModelDracoIgnis(), 0.5F);          // Fire Dragon
		DragonRegistry.instance().registerDragonRendererCreationHandler(idHandler);
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
