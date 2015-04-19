package net.wildbill22.draco.generation.villageHandlers;

import java.util.Calendar;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.wildbill22.draco.lib.REFERENCE;
import cpw.mods.fml.common.ObfuscationReflectionHelper;
import cpw.mods.fml.common.registry.VillagerRegistry;
import cpw.mods.fml.common.registry.VillagerRegistry.IVillageTradeHandler;

public class VillagerBakeryTradeHandler implements IVillageTradeHandler {
	public static final int BAKER = 43;
	private static ResourceLocation texture;
    private float baseChance;
	private boolean isHalloween;

	@Override
	public void manipulateTradesForVillager(EntityVillager villager, MerchantRecipeList recipeList, Random rand) 
	{
        Calendar calendar = Calendar.getInstance();

        if((calendar.get(2) + 1 == 10 && calendar.get(5) >= 28 && calendar.get(5) <= 31) || (calendar.get(2) + 1 == 11 && calendar.get(5) >= 1 && calendar.get(5) <= 2))
        {  
        	isHalloween = true;
    	}
        else
        {
        	isHalloween = false;
        }
		
        baseChance = ObfuscationReflectionHelper.<Float, EntityVillager>getPrivateValue(EntityVillager.class, villager, "field_82191_bN");
        if(!isHalloween)
        {
        	addTrade(recipeList, rand, 0.7F, new Offer(Items.bread, 2), new Offer(Items.emerald, 1));
        	addTrade(recipeList, rand, 0.7F, new Offer(Items.emerald, 3), new Offer(Items.bread, 2));
        
        	addTrade(recipeList, rand, 0.7F, new Offer(Items.bread, 6), new Offer(Items.wheat, 16));
        }
        else
        {
        	addTrade(recipeList, rand, 0.7F, new Offer(Items.string, 6), new Offer(Items.emerald, 1));
        	addTrade(recipeList, rand, 0.7F, new Offer(Items.apple, 3), new Offer(Items.emerald, 1));
        	
        	addTrade(recipeList, rand, 0.7F, new Offer(Item.getItemFromBlock(Blocks.netherrack), 64), new Offer(Item.getItemFromBlock(Blocks.cobblestone), 64));
        	addTrade(recipeList, rand, 0.7F, new Offer(Item.getItemFromBlock(Blocks.cobblestone), 64), new Offer(Item.getItemFromBlock(Blocks.netherrack), 64));
        	
        	addTrade(recipeList, rand, 0.7F, new Offer(Item.getItemFromBlock(Blocks.pumpkin), 2), new Offer(Items.emerald, 1));
        	addTrade(recipeList, rand, 0.7F, new Offer(Item.getItemFromBlock(Blocks.lit_pumpkin), 2), new Offer(Items.emerald, 2));
        }
     /*   addTrade(recipeList, rand, 0.7F, new Offer(Items.fish, 3), new Offer(Items.emerald, 1));
        addTrade(recipeList, rand, 0.7F, new Offer(Items.emerald, 5), new Offer(Items.fish, 8));*/
	}

	 private class Offer {
	        public final Object obj;
	        public final int min, max;

	        public Offer(Object obj, int min, int max) {
	            this.obj = obj;
	            this.min = min;
	            this.max = max;
	        }

	        public Offer(Object obj, int amount) {
	            this(obj, amount, amount);
	        }

	        @SuppressWarnings("unused")
			public Offer(Object obj) {
	            this(obj, 1);
	        }
	    }

	    @SuppressWarnings("unchecked")
		private void addTrade(MerchantRecipeList recipeList, Random rand, float chance, Offer sale, Offer... offers) {
	        if (offers.length <= 0 || sale.obj == null)
	            return;
	        for (Offer offer : offers) {
	            if (offer.obj == null)
	                return;
	        }
	        if (rand.nextFloat() < adjustProbability(chance)) {
	            ItemStack sellStack = prepareStack(rand, sale);
	            ItemStack buyStack1 = prepareStack(rand, offers[0]);
	            ItemStack buyStack2 = null;
	            if (offers.length >= 2)
	                buyStack2 = prepareStack(rand, offers[1]);
	            recipeList.add(new MerchantRecipe(buyStack1, buyStack2, sellStack));
	        }
	    }

	    private ItemStack prepareStack(Random rand, Offer offer) throws IllegalArgumentException {
	        if (offer.obj instanceof ItemStack) {
	            ItemStack stack = (ItemStack) offer.obj;
	            stack.stackSize = stackSize(rand, offer.min, offer.max);
	            return stack;
	        }
	        if (offer.obj instanceof Item)
	            return new ItemStack((Item) offer.obj, stackSize(rand, offer.min, offer.max));
	        if (offer.obj instanceof Block)
	            return new ItemStack((Block) offer.obj, stackSize(rand, offer.min, offer.max));
	        throw new IllegalArgumentException("Unrecongnized object passed to villager trade setup");
	    }

	    private int stackSize(Random rand, int min, int max) {
	        return MathHelper.getRandomIntegerInRange(rand, min, max);
	    }

	    private float adjustProbability(float chance) {
	        float adjustedChance = chance + baseChance;
	        return adjustedChance > 0.9F ? 0.9F - (adjustedChance - 0.9F) : adjustedChance;
	    }

	    // Call from mod's init
		public static void init() {
			VillagerBakeryTradeHandler baker = new VillagerBakeryTradeHandler();
			VillagerRegistry.instance().registerVillageTradeHandler(VillagerBakeryTradeHandler.BAKER, baker);
			VillagerRegistry.instance().registerVillagerId(VillagerBakeryTradeHandler.BAKER);
			texture = new ResourceLocation(REFERENCE.MODID + ":textures/entity/baker.png");
			VillagerRegistry.instance().registerVillagerSkin(VillagerBakeryTradeHandler.BAKER, texture);		
		}
}
