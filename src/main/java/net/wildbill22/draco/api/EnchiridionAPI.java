package net.wildbill22.draco.api;

import java.io.File;
import java.util.Collection;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Level;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.ModContainer;
import cpw.mods.fml.common.Optional.Method;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.wildbill22.draco.lib.LogHelper;
import net.wildbill22.draco.lib.REFERENCE;
import joshie.enchiridion.ELogger;
import joshie.enchiridion.api.IBookHandler;
import joshie.enchiridion.api.IEnchiridionAPI;
import joshie.enchiridion.api.IRecipeHandler;
import joshie.enchiridion.designer.BookRegistry;
import joshie.enchiridion.designer.features.FeatureRecipe;
import joshie.enchiridion.helpers.GsonClientHelper;
import joshie.enchiridion.library.BookHandlerRegistry;
import cpw.mods.fml.common.Optional;

@Optional.Interface(iface="joshie.enchiridion.api.IEnchiridionAPI", modid=REFERENCE.EAPIMODID, striprefs=true)
public class EnchiridionAPI implements IEnchiridionAPI {
	public static EnchiridionAPI instance;

	public static void init() {
		instance = new EnchiridionAPI();
		instance.registerModBooks(REFERENCE.MODID + ":" + "wildbill22_draco");
		instance.registerBookHandler(new net.wildbill22.draco.api.BookHandler());
		MinecraftForge.EVENT_BUS.register(new EnchiridionAPIEventHandler());    // For the book to spawn into player inventory
		// TODO: Register a recipe handler
	}

	@Method(modid=REFERENCE.EAPIMODID)
	@Override
	public void registerBookData(ItemStack stack, String identifier) {
		BookRegistry.registerItemStack(identifier, stack);
	}

	@Method(modid=REFERENCE.EAPIMODID)
	@Override
	public void registerBookHandler(IBookHandler handler) {
		BookHandlerRegistry.registerHandler(handler);
	}

	@Method(modid=REFERENCE.EAPIMODID)
	@Override
	public void registerModBooks(String id) {
        /** Grab the modid and the assets path **/
        String modid = id;
        String assetspath = id.toLowerCase();
        if (id.contains(":")) {
            String[] split = id.split(":");
            modid = split[0];
            assetspath = split[1].toLowerCase();
        }
        
        /** Find this mods container **/
        ModContainer mod = null;
        for (ModContainer container: Loader.instance().getActiveModList()) {
            if (container.getModId().equals(modid)) {
                mod = container;
                break;
            }
        }
        
        /** Attempt to register in dev or in jar **/
        if (mod == null) {
            LogHelper.error("When attempting to register books with Enchiridion 2 a mod with the modid " + modid + " could not be found");
        } else {
            String jar = mod.getSource().toString();
            if (jar.contains(".jar") || jar.contains(".zip")) {
                BookRegistry.registerModInJar(assetspath, new File(jar));
            } else {
            	try {
            		registerModInDev(assetspath, mod.getSource());
            	} catch (IllegalArgumentException e) {
            		LogHelper.error(e.toString());
            		LogHelper.error("assetPath: " + assetspath);
            		LogHelper.error("mod.getSource: " + mod.getSource().toString());
            	}
            }
        }
	}

	@Method(modid=REFERENCE.EAPIMODID)
	@Override
	public void registerRecipeHandler(IRecipeHandler handler) {
        FeatureRecipe.handlers.add(handler);
        LogHelper.info("Registered a new recipe handler: " + handler.getRecipeName());
	}

	@Method(modid=REFERENCE.EAPIMODID)
    public static void registerModInDev(String modid, File source) {
//        File path = FileHelper.getDevAssetsForModPath(source.getParentFile(), modid, "books");
        File path = new File(new File(new File(source, "assets"), modid), "books");

        if (!path.exists()) {
            path.mkdir();
        }
        
        Collection<File> files = FileUtils.listFiles(path, new String[] { "json" }, true);
        for (File file : files) {
            try {
                String json = FileUtils.readFileToString(file);
                BookRegistry.BookData data = BookRegistry.register(GsonClientHelper.getGson().fromJson(json, BookRegistry.BookData.class));
                ELogger.log(Level.INFO, "Successfully loaded in the book with the unique identifier: " + data.uniqueName + " for the language: " + data.language);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
