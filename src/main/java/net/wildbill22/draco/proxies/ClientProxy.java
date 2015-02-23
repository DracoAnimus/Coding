package net.wildbill22.draco.proxies;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;
import net.wildbill22.draco.blocks.ModBlocks;
import net.wildbill22.draco.entities.EntityCreeperDragon;
import net.wildbill22.draco.entities.EntitySpear;
import net.wildbill22.draco.entities.hostile.EntityGuard;
import net.wildbill22.draco.items.ModItems;
import net.wildbill22.draco.models.ModelCreeperDragon;
import net.wildbill22.draco.render.RenderCreeperDragon;
import net.wildbill22.draco.render.RenderGuard;
import net.wildbill22.draco.render.RenderSpear;
import net.wildbill22.draco.render.TemporaryHoardRenderer;
import net.wildbill22.draco.render.item.ItemRendererTemporaryHoard;
import net.wildbill22.draco.tile_entity.TileEntityTemporaryHoard;
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
		RenderingRegistry.registerEntityRenderingHandler(EntityGuard.class,	new RenderGuard(new ModelBiped(), 0.5F));
		
		// Throwable entities
		RenderingRegistry.registerEntityRenderingHandler(EntitySpear.class, new RenderSpear(ModItems.spear));

		// Special one for hoard
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTemporaryHoard.class, new TemporaryHoardRenderer());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.temporaryHoard), 
				new ItemRendererTemporaryHoard());
	}
	
	@Override
	public void registerTileEntitySpecialRenderer(){		
	}
	
	@Override
	public void registerSounds() {}
}
