package net.wildbill22.draco.proxies;

import net.wildbill22.draco.entities.EntityCreeperDragon;
import net.wildbill22.draco.models.ModelCreeperDragon;
import net.wildbill22.draco.render.RenderCreeperDragon;
import cpw.mods.fml.client.registry.RenderingRegistry;

/** 
 * @author WILLIAM
 *
 */
public class ClientProxy extends CommonProxy
{
	public void registerRenderer(){
		//Entities
		RenderingRegistry.registerEntityRenderingHandler(EntityCreeperDragon.class, new RenderCreeperDragon(new ModelCreeperDragon(), 0.5F));
		// the 0.5F is the shadow size
	}
	
	public void registerTileEntitySpecialRenderer(){		
	}
}
