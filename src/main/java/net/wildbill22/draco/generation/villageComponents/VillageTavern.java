package net.wildbill22.draco.generation.villageComponents;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.common.registry.VillagerRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureVillagePieces;
import net.minecraft.world.gen.structure.StructureVillagePieces.Start;
import net.minecraftforge.common.ChestGenHooks;
import net.wildbill22.draco.generation.villageHandlers.BarWenchCreationHandler;
import net.wildbill22.draco.generation.villageHandlers.TavernCreationHandler;
import net.wildbill22.draco.items.ModItems;
import net.wildbill22.draco.lib.BALANCE;
import net.wildbill22.draco.lib.LogHelper;

public class VillageTavern extends StructureVillagePieces.Village
{
	public static final String TAVERN_CHEST = "villageTavern";
	public static final WeightedRandomChestContent[] tavernChestContents = new WeightedRandomChestContent[]{
    	new WeightedRandomChestContent(
    			new ItemStack(ModItems.fireball), 1, 10, BALANCE.CHEST_ITEMS.VILLAGE_BLACKSMITH_FIREBALLS), 
    	new WeightedRandomChestContent(
    			new ItemStack(ModItems.explosiveFireball), 1, 10, BALANCE.CHEST_ITEMS.VILLAGE_BLACKSMITH_FIREBALLS), 
    	new WeightedRandomChestContent(
    			new ItemStack(ModItems.goldCoin), 1, 10, BALANCE.CHEST_ITEMS.VILLAGE_BLACKSMITH_GOLD_COINS),
		new WeightedRandomChestContent(
				new ItemStack(Items.diamond_sword), 1, 1, BALANCE.CHEST_ITEMS.VILLAGE_BLACKSMITH_DIAMOND_ARMOR),
		new WeightedRandomChestContent(
				new ItemStack(Items.diamond_boots), 1, 1, BALANCE.CHEST_ITEMS.VILLAGE_BLACKSMITH_DIAMOND_ARMOR),
		new WeightedRandomChestContent(
				new ItemStack(Items.diamond_chestplate), 1, 1, BALANCE.CHEST_ITEMS.VILLAGE_BLACKSMITH_DIAMOND_ARMOR),
		new WeightedRandomChestContent(
				new ItemStack(Items.diamond_helmet), 1, 1, BALANCE.CHEST_ITEMS.VILLAGE_BLACKSMITH_DIAMOND_ARMOR),
		new WeightedRandomChestContent(
				new ItemStack(Items.diamond_leggings), 1, 1, BALANCE.CHEST_ITEMS.VILLAGE_BLACKSMITH_DIAMOND_ARMOR)
    };
	private int averageGroundLevel = -1;
    private static final int HEIGHT = 10;
	private final int SOUTH = 3;
	private final int WEST  = 0;
	private final int NORTH = 2;
	private final int EAST  = 1;

    public VillageTavern() {}
    
	public VillageTavern(Start startPiece, int type, Random random, StructureBoundingBox _boundingBox, int direction){
		super(startPiece, type);
        coordBaseMode = direction;
    	boundingBox = _boundingBox;
	}
	
	public static void addVillagePiece(@SuppressWarnings("rawtypes") Class c, String s) { 
		try { 
			MapGenStructureIO.func_143031_a(c, s); 
		} 
		catch (Exception localException) {} 
	} 

	public static StructureVillagePieces.Village buildComponent(Start startPiece, @SuppressWarnings("rawtypes") List pieces, Random random, int x, int y, int z, int direction, int type) {
        StructureBoundingBox _boundingBox = StructureBoundingBox.getComponentToAddBoundingBox(x, y, z, 0, 0, 0, 14, 10, 8, direction);
        if(canVillageGoDeeper(_boundingBox)){ 
        	if(StructureComponent.findIntersecting(pieces, _boundingBox) == null){
        		return new VillageTavern(startPiece, type, random, _boundingBox, direction);
        	}
        }
		return null;
	}
	
	@Override
	public boolean addComponentParts(World world, Random random, StructureBoundingBox box) 
	{
		LogHelper.info("VillageTavern: Build tavern at: " + box.minX + ", " + box.minZ);
        if (averageGroundLevel < 0){
            averageGroundLevel = getAverageGroundLevel(world, box);
            if (averageGroundLevel < 0){
                return true;
            }
            boundingBox.offset(0, this.averageGroundLevel - boundingBox.maxY + HEIGHT - 2, 0);
        }

        // Clear out for building and make foundation
        for (int xx = 0; xx < 14; xx++){
            for (int zz = 0; zz < 8; zz++){
                clearCurrentPositionBlocksUpwards(world, xx,0,zz, box);
                this.func_151554_b(world, Blocks.cobblestone, 0, xx, -1, zz, box);
            }
        }
		
		// floors
		fillWithBlocks(world, box, 0,0,1, 14,0,7, Blocks.dirt, Blocks.dirt, false);
		fillWithBlocks(world, box, 0,0,0, 14,0,0, Blocks.gravel, Blocks.gravel, false);
		fillWithBlocks(world, box, 1,0,1, 4,0,6, Blocks.cobblestone, Blocks.cobblestone, false);
		fillWithBlocks(world, box, 5,0,1, 12,0,6, Blocks.planks, Blocks.planks, false);
		fillWithBlocks(world, box, 1,4,1, 12,4,6, Blocks.planks, Blocks.planks, false);	
		fillWithBlocks(world, box, 7,8,1, 12,8,6, Blocks.planks, Blocks.planks, false);

		// walls
		fillWithBlocks(world, box, 1,1,1, 1,4,1, Blocks.log, Blocks.log, false);
		fillWithBlocks(world, box, 1,1,2, 1,3,5, Blocks.cobblestone, Blocks.cobblestone, false);
		fillWithBlocks(world, box, 1,1,6, 1,4,6, Blocks.log, Blocks.log, false);
		
		fillWithBlocks(world, box, 2,3,1, 3,3,1, Blocks.planks, Blocks.planks, false);
		fillWithBlocks(world, box, 2,1,1, 3,1,1, Blocks.cobblestone, Blocks.cobblestone, false);
		fillWithBlocks(world, box, 2,2,1, 3,2,1, Blocks.glass_pane, Blocks.glass_pane, false);
		fillWithBlocks(world, box, 4,1,1, 4,3,1, Blocks.log, Blocks.log, false);
		fillWithBlocks(world, box, 5,3,1, 6,3,1, Blocks.log, Blocks.log, false);
		fillWithBlocks(world, box, 7,1,1, 7,7,1, Blocks.log, Blocks.log, false);
        placeBlockAtCurrentPosition(world, Blocks.log, getMetadataWithOffset(Blocks.log, 4), 4, 3, 1, box);
        placeBlockAtCurrentPosition(world, Blocks.log, getMetadataWithOffset(Blocks.log, 4), 7, 3, 1, box);
        fillWithBlocks(world, box, 8,1,1, 11,1,1, Blocks.cobblestone, Blocks.cobblestone, false);
		fillWithBlocks(world, box, 8,2,1, 11,7,1, Blocks.planks, Blocks.planks, false);
		fillWithBlocks(world, box, 8,2,1, 11,2,1, Blocks.glass_pane, Blocks.glass_pane, false);
		fillWithBlocks(world, box, 12,1,1, 12,7,1, Blocks.log, Blocks.log, false);

		fillWithBlocks(world, box, 12,1,2, 12,1,5, Blocks.cobblestone, Blocks.cobblestone, false);
		fillWithBlocks(world, box, 12,2,2, 12,7,5, Blocks.planks, Blocks.planks, false);
		fillWithBlocks(world, box, 12,2,3, 12,2,4, Blocks.glass_pane, Blocks.glass_pane, false);
		fillWithBlocks(world, box, 12,6,3, 12,6,4, Blocks.glass_pane, Blocks.glass_pane, false);
		fillWithBlocks(world, box, 12,1,6, 12,7,6, Blocks.log, Blocks.log, false);
		
		fillWithBlocks(world, box, 7,1,6, 11,1,6, Blocks.cobblestone, Blocks.cobblestone, false);
		fillWithBlocks(world, box, 7,2,6, 11,7,6, Blocks.planks, Blocks.planks, false);
		placeBlockAtCurrentPosition(world, Blocks.glass_pane, 0, 10,6,6, box);
		fillWithBlocks(world, box, 6,1,6, 6,8,6, Blocks.log, Blocks.log, false);
		placeBlockAtCurrentPosition(world, Blocks.log, 0, 5,3,6, box);
		fillWithBlocks(world, box, 4,1,6, 4,3,6, Blocks.log, Blocks.log, false);
		fillWithBlocks(world, box, 2,1,6, 3,3,6, Blocks.cobblestone, Blocks.cobblestone, false);

		fillWithBlocks(world, box, 7,5,2, 7,7,5, Blocks.planks, Blocks.planks, false);
		fillWithBlocks(world, box, 6,5,5, 6,8,5, Blocks.planks, Blocks.planks, false);
		
		// roofing
		for(int xx = 0; xx <= 13; xx++){
	        placeBlockAtCurrentPosition(world, Blocks.oak_stairs, getMetadataWithOffset(Blocks.oak_stairs, SOUTH), xx, 4, 0, box);
	        placeBlockAtCurrentPosition(world, Blocks.oak_stairs, getMetadataWithOffset(Blocks.oak_stairs, NORTH), xx, 4, 7, box);
		}
		for(int zz = 0; zz <= 7; zz++){
	        placeBlockAtCurrentPosition(world, Blocks.oak_stairs, getMetadataWithOffset(Blocks.oak_stairs, WEST), 0, 4, zz, box);
            placeBlockAtCurrentPosition(world, Blocks.oak_stairs, getMetadataWithOffset(Blocks.oak_stairs, EAST), 13, 4, zz, box);
		}
		
		for(int xx = 6; xx <= 13; xx++){
	        placeBlockAtCurrentPosition(world, Blocks.oak_stairs, getMetadataWithOffset(Blocks.oak_stairs, SOUTH), xx, 7, 0, box);
	        placeBlockAtCurrentPosition(world, Blocks.oak_stairs, getMetadataWithOffset(Blocks.oak_stairs, NORTH), xx, 7, 7, box);
	        placeBlockAtCurrentPosition(world, Blocks.oak_stairs, getMetadataWithOffset(Blocks.oak_stairs, SOUTH), xx, 8, 1, box);
	        placeBlockAtCurrentPosition(world, Blocks.oak_stairs, getMetadataWithOffset(Blocks.oak_stairs, NORTH), xx, 8, 6, box);
		}
		for(int xx = 7; xx <= 13; xx++){
	        placeBlockAtCurrentPosition(world, Blocks.oak_stairs, getMetadataWithOffset(Blocks.oak_stairs, SOUTH), xx, 9, 2, box);
	        placeBlockAtCurrentPosition(world, Blocks.oak_stairs, getMetadataWithOffset(Blocks.oak_stairs, NORTH), xx, 9, 5, box);
		}
		for(int zz = 2; zz <= 5; zz++){
			placeBlockAtCurrentPosition(world, Blocks.oak_stairs, getMetadataWithOffset(Blocks.oak_stairs, WEST), 6,8,zz, box);
		}
		for(int zz = 3; zz <= 4; zz++){
	        placeBlockAtCurrentPosition(world, Blocks.oak_stairs, getMetadataWithOffset(Blocks.oak_stairs, EAST), 13,9,zz, box);
	        placeBlockAtCurrentPosition(world, Blocks.oak_stairs, getMetadataWithOffset(Blocks.oak_stairs, WEST), 12,9,zz, box);	        
	        placeBlockAtCurrentPosition(world, Blocks.oak_stairs, getMetadataWithOffset(Blocks.oak_stairs, WEST), 7,9,zz, box);
	        placeBlockAtCurrentPosition(world, Blocks.oak_stairs, getMetadataWithOffset(Blocks.oak_stairs, EAST), 8,9,zz, box);
		}
		for(int zz = 5; zz <= 7; zz++){
			placeBlockAtCurrentPosition(world, Blocks.oak_stairs, getMetadataWithOffset(Blocks.oak_stairs, WEST), 5,7,zz, box);
		}
		fillWithAir(world, box, 6,8,4, 6,8,4);
				
		// Interior
		fillWithBlocks(world, box, 1,5,6, 5,5,6, Blocks.fence, Blocks.fence, false);
		fillWithBlocks(world, box, 1,5,1, 1,5,5, Blocks.fence, Blocks.fence, false);
		fillWithBlocks(world, box, 2,5,1, 6,5,1, Blocks.fence, Blocks.fence, false);
		
        placeBlockAtCurrentPosition(world, Blocks.torch, 0, 1,6,6, box);
        placeBlockAtCurrentPosition(world, Blocks.torch, 0, 1,6,1, box);
        placeBlockAtCurrentPosition(world, Blocks.torch, 0, 9,9,4, box);
        placeBlockAtCurrentPosition(world, Blocks.torch, 0, 4,3,0, box);
        placeBlockAtCurrentPosition(world, Blocks.torch, 0, 7,3,0, box);
        placeBlockAtCurrentPosition(world, Blocks.torch, 0, 5,3,7, box);
        placeBlockAtCurrentPosition(world, Blocks.torch, 0, 2,3,5, box);
        placeBlockAtCurrentPosition(world, Blocks.torch, 0, 4,3,2, box);
        placeBlockAtCurrentPosition(world, Blocks.torch, 0, 9,3,2, box);
        
        for(int yy = 5; yy <= 8; yy++){
        	placeBlockAtCurrentPosition(world, Blocks.ladder, getMetadataWithOffset(Blocks.ladder, 4), 6,yy,4, box);
        }
        
        placeBlockAtCurrentPosition(world, Blocks.bookshelf, 0, 11,5,2, box);
        placeBlockAtCurrentPosition(world, Blocks.torch, 0, 11,6,2, box);
        // place a bed
        placeBlockAtCurrentPosition(world, Blocks.bed, getMetadataWithOffset(Blocks.bed, 2), 10,5,2, box);
        placeBlockAtCurrentPosition(world, Blocks.bed, getMetadataWithOffset(Blocks.bed, 0), 10,5,3, box);
        

        // Adds custom TAVERN_CHEST for loot drop
        generateStructureChestContents(world, box, random, 9,5,2, ChestGenHooks.getItems(TAVERN_CHEST, random), ChestGenHooks.getCount(TAVERN_CHEST, random));
        
        placeDoorAtCurrentPosition(world, box, random, 7,5,2, getMetadataWithOffset(Blocks.wooden_door, EAST));
        placeDoorAtCurrentPosition(world, box, random, 5,1,1, getMetadataWithOffset(Blocks.wooden_door, SOUTH));
        placeDoorAtCurrentPosition(world, box, random, 6,1,1, getMetadataWithOffset(Blocks.wooden_door, SOUTH));
        placeDoorAtCurrentPosition(world, box, random, 5,1,6, getMetadataWithOffset(Blocks.wooden_door, NORTH));
		
        fillWithBlocks(world, box, 4,1,5, 4,3,5, Blocks.log, Blocks.log, false);
		fillWithBlocks(world, box, 7,1,2, 7,3,2, Blocks.log, Blocks.log, false);
		
		placeBlockAtCurrentPosition(world, Blocks.oak_stairs, getMetadataWithOffset(Blocks.oak_stairs, WEST), 7,1,5, box);
		placeBlockAtCurrentPosition(world, Blocks.oak_stairs, getMetadataWithOffset(Blocks.oak_stairs, WEST), 8,2,5, box);
		placeBlockAtCurrentPosition(world, Blocks.oak_stairs, getMetadataWithOffset(Blocks.oak_stairs, WEST), 9,3,5, box);
		placeBlockAtCurrentPosition(world, Blocks.oak_stairs, getMetadataWithOffset(Blocks.oak_stairs, WEST), 10,4,5, box);
		fillWithAir(world, box, 7,4,5, 9,4,5);
		
		placeBlockAtCurrentPosition(world, Blocks.planks, 0, 8, 1, 5, box);
		placeBlockAtCurrentPosition(world, Blocks.spruce_stairs, getMetadataWithOffset(Blocks.spruce_stairs, WEST), 9,1,5, box);
		placeBlockAtCurrentPosition(world, Blocks.spruce_stairs, getMetadataWithOffset(Blocks.spruce_stairs, WEST), 10,1,5, box);
		placeBlockAtCurrentPosition(world, Blocks.spruce_stairs, getMetadataWithOffset(Blocks.spruce_stairs, WEST), 11,1,5, box);
		placeBlockAtCurrentPosition(world, Blocks.spruce_stairs, getMetadataWithOffset(Blocks.spruce_stairs, NORTH), 11,1,4, box);
		placeBlockAtCurrentPosition(world, Blocks.spruce_stairs, getMetadataWithOffset(Blocks.spruce_stairs, NORTH), 11,1,3, box);
		placeBlockAtCurrentPosition(world, Blocks.planks, 0, 11,1,2, box);
		placeBlockAtCurrentPosition(world, Blocks.oak_stairs, getMetadataWithOffset(Blocks.oak_stairs, WEST), 10,1,2, box);
		placeBlockAtCurrentPosition(world, Blocks.fence, 0, 9,1,2, box);
		placeBlockAtCurrentPosition(world, Blocks.wooden_pressure_plate, 0, 9,2,2, box);
		placeBlockAtCurrentPosition(world, Blocks.oak_stairs, getMetadataWithOffset(Blocks.oak_stairs, EAST), 8,1,2, box);
		
		fillWithBlocks(world, box, 4,1,2, 4,1,3, Blocks.planks, Blocks.planks, false);
		fillWithBlocks(world, box, 2,1,2, 2,1,3, Blocks.planks, Blocks.planks, false);
		// TODO: trapdoor name?
		placeBlockAtCurrentPosition(world, Blocks.brewing_stand, 0, 2,2,3, box);
		placeBlockAtCurrentPosition(world, Blocks.furnace, getMetadataWithOffset(Blocks.furnace, SOUTH), 2,1,5, box);
		placeBlockAtCurrentPosition(world, Blocks.planks, 0, 3,1,5, box);
		
        spawnVillagers(world, box, 2,1,2,1);
        return true;
    }

	// TODO: make a bar wench
	@Override
    protected int getVillagerType(int par1){
        return BarWenchCreationHandler.BAR_WENCH;
    }

	public static void registerTavernChest(){
		ChestGenHooks.getInfo(TAVERN_CHEST);
   		for(int i = 0; i < tavernChestContents.length; i++){
			ChestGenHooks.addItem(TAVERN_CHEST, tavernChestContents[i]);
		}
		ChestGenHooks.getInfo(TAVERN_CHEST).setMax(3);
		ChestGenHooks.getInfo(TAVERN_CHEST).setMax(7);
	}

	// Call from mod's init
	public static void init() {
		VillageTavern.addVillagePiece(VillageTavern.class, "ViTav");
 		TavernCreationHandler tavernCreator = new TavernCreationHandler();
		VillagerRegistry.instance().registerVillageCreationHandler(tavernCreator);
		VillageTavern.registerTavernChest();
	}
}