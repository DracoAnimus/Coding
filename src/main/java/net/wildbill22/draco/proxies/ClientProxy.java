package net.wildbill22.draco.proxies;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;
import net.wildbill22.draco.blocks.ModBlocks;
import net.wildbill22.draco.client.gui.DragonHudOverlay;
import net.wildbill22.draco.entities.EntityMyExplosive;
import net.wildbill22.draco.entities.EntityMyFireball;
import net.wildbill22.draco.entities.EntityMyRock;
import net.wildbill22.draco.entities.EntitySpear;
import net.wildbill22.draco.entities.dragons.*;
import net.wildbill22.draco.entities.hostile.*;
import net.wildbill22.draco.items.*;
import net.wildbill22.draco.items.weapons.ModWeapons;
import net.wildbill22.draco.lib.LogHelper;
import net.wildbill22.draco.models.*;
import net.wildbill22.draco.models.dragons.ModelDracoAqua;
import net.wildbill22.draco.models.dragons.ModelCreeperDragon;
import net.wildbill22.draco.models.dragons.ModelDracoAquila;
import net.wildbill22.draco.models.dragons.ModelDracoMortem;
import net.wildbill22.draco.models.dragons.ModelDracoTenebrosus;
import net.wildbill22.draco.models.dragons.ModelDracoIgnis;
import net.wildbill22.draco.models.dragons.ModelSilverDragon;
import net.wildbill22.draco.models.dragons.ModelWildFireDragon;
import net.wildbill22.draco.render.*;
import net.wildbill22.draco.render.dragons.RenderCreeperDragon;
import net.wildbill22.draco.render.dragons.RenderDracoAquila;
import net.wildbill22.draco.render.dragons.RenderDracoMortem;
import net.wildbill22.draco.render.dragons.RenderDracoTenebrosus;
import net.wildbill22.draco.render.dragons.RenderDracoIgnis;
import net.wildbill22.draco.render.dragons.RenderSilverDragon;
import net.wildbill22.draco.render.dragons.RenderWildFireDragon;
import net.wildbill22.draco.render.item.ItemRendererTemporaryHoard;
import net.wildbill22.draco.render.item.ItemRendererSpear;
import net.wildbill22.draco.tile_entity.*;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

/** 
 * @author WILLIAM
 *
 */
public class ClientProxy extends CommonProxy
{
	@Override
	public void registerRenderer(){
		//Entities, the 0.5F is the shadow size
		
		// Dragons
		RenderingRegistry.registerEntityRenderingHandler(EntityCreeperDragon.class, 
				new RenderCreeperDragon(new ModelCreeperDragon(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityWildFireDragon.class, 
				new RenderWildFireDragon(new ModelWildFireDragon(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntitySilverDragon.class, 
				new RenderSilverDragon(new ModelSilverDragon(), 0.5F, 0)); // Silver
		RenderingRegistry.registerEntityRenderingHandler(EntityDracoMortem.class, 
				new RenderDracoMortem(new ModelDracoMortem(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityDracoAqua.class, 
				new RenderDracoMortem(new ModelDracoAqua(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntitySilverDragon.class, 
				new RenderSilverDragon(new ModelSilverDragon(), 0.5F, 1)); // Gold
		RenderingRegistry.registerEntityRenderingHandler(EntityDracoTenebrosus.class, 
				new RenderDracoTenebrosus(new ModelDracoTenebrosus(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityDracoIgnis.class, 
				new RenderDracoIgnis(new ModelDracoIgnis(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityDracoAquila.class, 
				new RenderDracoAquila(new ModelDracoAquila(), 0.5F));
		
		// Other entities
		RenderingRegistry.registerEntityRenderingHandler(EntityGuard.class,	new RenderGuard(new ModelBiped(), 0.5F));
//		RenderingRegistry.registerEntityRenderingHandler(EntityMCSilverDragon.class, new RenderMCSilverDragon());
		RenderingRegistry.registerEntityRenderingHandler(EntityBallista.class, 
				new RenderBallista(new ModelBallista(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityCatapult.class, 
				new RenderCatapult(new ModelCatapult(), 0.5F));
		
		// Throwable entities
		RenderingRegistry.registerEntityRenderingHandler(EntitySpear.class, new RenderSpear());
		RenderingRegistry.registerEntityRenderingHandler(EntityMyFireball.class, new RenderSnowball(ModItems.fireball));
		RenderingRegistry.registerEntityRenderingHandler(EntityMyExplosive.class, new RenderSnowball(ModItems.explosiveFireball));
		RenderingRegistry.registerEntityRenderingHandler(EntityMyRock.class, new RenderSnowball(ModItems.rock));

		// Special one for hoard
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTemporaryHoard.class, new TemporaryHoardRenderer());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.temporaryHoard), 
				new ItemRendererTemporaryHoard());

		//Items
		MinecraftForgeClient.registerItemRenderer(ModWeapons.longBow, new BowRender());
		MinecraftForgeClient.registerItemRenderer(ModWeapons.crossbow, new CrossbowRender());
		MinecraftForgeClient.registerItemRenderer(ModItems.spear, new ItemRendererSpear()); // For when it is held
	}
	
	@Override
	public void registerTileEntitySpecialRenderer(){		
	}

	@Override
	public void registerSounds() {}
	
	@Override
	public void registerSubscriptions() {
		super.registerSubscriptions();
		LogHelper.info("ClientProxy: Registering subscriptions");
		MinecraftForge.EVENT_BUS.register(new DragonHudOverlay(Minecraft.getMinecraft()));
	}
}
