package net.wildbill22.draco.proxies;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderArrow;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;
import net.wildbill22.draco.blocks.ModBlocks;
import net.wildbill22.draco.client.gui.DragonHudOverlay;
import net.wildbill22.draco.client.renders.RenderMCSilverDragon;
import net.wildbill22.draco.common.entities.dragons.EntityMCSilverDragon;
import net.wildbill22.draco.entities.EntityMyExplosive;
import net.wildbill22.draco.entities.EntityMyFireball;
import net.wildbill22.draco.entities.EntitySpear;
import net.wildbill22.draco.entities.dragons.*;
import net.wildbill22.draco.entities.hostile.*;
import net.wildbill22.draco.items.*;
import net.wildbill22.draco.items.weapons.ModWeapons;
import net.wildbill22.draco.lib.LogHelper;
import net.wildbill22.draco.models.*;
import net.wildbill22.draco.render.*;
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
		RenderingRegistry.registerEntityRenderingHandler(EntityCreeperDragon.class, 
				new RenderCreeperDragon(new ModelCreeperDragon(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntitySilverDragon.class, 
				new RenderSilverDragon(new ModelSilverDragon(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityGuard.class,	new RenderGuard(new ModelBiped(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityMCSilverDragon.class, new RenderMCSilverDragon());
		
		// Throwable entities
		RenderingRegistry.registerEntityRenderingHandler(EntitySpear.class, new RenderSpear(ModItems.spear));
		RenderingRegistry.registerEntityRenderingHandler(EntityArrow.class, new RenderArrow());
		RenderingRegistry.registerEntityRenderingHandler(EntityMyFireball.class, new RenderMyFireball(ModItems.fireball));
		RenderingRegistry.registerEntityRenderingHandler(EntityMyExplosive.class, new RenderMyExplosive(ModItems.explosiveFireball));

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
