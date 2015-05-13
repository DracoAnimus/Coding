package net.wildbill22.draco.generation.villageComponents;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.common.registry.VillagerRegistry;
import net.minecraft.entity.EntityList;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureVillagePieces;
import net.minecraftforge.common.ChestGenHooks;
import net.wildbill22.draco.biome.ModBiomes;
import net.wildbill22.draco.entities.hostile.EntityBaron;
import net.wildbill22.draco.entities.hostile.EntityGuard;
import net.wildbill22.draco.generation.villageHandlers.BaronCastleCreationHandler;
import net.wildbill22.draco.lib.LogHelper;

public class VillageBaronCastle extends MyVillageComponents {
    private static final int HEIGHT = 12;
   
    public VillageBaronCastle() {}

	public VillageBaronCastle(StructureVillagePieces.Start startPiece, int type, Random random, StructureBoundingBox _boundingBox, int direction){
		super(startPiece, type, _boundingBox, direction);
	}
	
	public static void addVillagePiece(@SuppressWarnings("rawtypes") Class c, String s) { 
		try { 
			MapGenStructureIO.func_143031_a(c, s); 
		} 
		catch (Exception localException) {} 
	} 

	public static StructureVillagePieces.Village buildComponent(StructureVillagePieces.Start startPiece, 
			@SuppressWarnings("rawtypes") List pieces, Random random, int x, int y, int z, int direction, int type) {
        StructureBoundingBox _boundingBox = StructureBoundingBox.getComponentToAddBoundingBox(x, y, z, 0, 0, 0, 14, 14, 12, direction);
        if (canVillageGoDeeper(_boundingBox)) { 
        	if (StructureComponent.findIntersecting(pieces, _boundingBox) == null) {
        		return new VillageBaronCastle(startPiece, type, random, _boundingBox, direction);
        	}
        }
		return null;
	}
	
	// Only create the Baron Castle in city biomes
	@Override
	public boolean addComponentParts(World world, Random random, StructureBoundingBox box) {		
//		LogHelper.info("VillageBaronCastle: Was going to build Baron's Castle at: " + box.minX + ", " + box.minZ);
		// First part built, this is always < 0
        if (averageGroundLevel < 0){
    		BiomeGenBase biome = world.getBiomeGenForCoords(box.minX, box.minZ);
    		if (biome != ModBiomes.biomeCityPlains)
    			return false;

    		LogHelper.info("VillageBaronCastle: Build Baron's Castle at: " + box.minX + ", " + box.minZ);
            averageGroundLevel = getAverageGroundLevel(world, box);
            if (averageGroundLevel < 0){
                return true;
            }
            boundingBox.offset(0, this.averageGroundLevel - boundingBox.maxY + HEIGHT - 1, 0);
        }
        
        setMetaData();
		
        // Clear out area of trees
		fillWithAir(world, box, 0,0,0, 12,14,12);

        // Clear out the area for the building and make a base
    	clearBlocks(world, 0,0, 12,12, box);    // Front pillar
		
    	// bottom floor
        int lvl = 0; // Level under construction
		fillWithBlocks(world, box, 0,lvl,0, 12,lvl,12, Blocks.stonebrick, Blocks.stonebrick, false);
        placeBlockAtCurrentPosition(world, Blocks.stone_brick_stairs, westFacingStair, 13,lvl,7, box); // front stairs

        // 1st floor
        lvl = 1;
		fillWithBlocks(world, box, 0,lvl,0, 12,9,0, Blocks.stonebrick, Blocks.stonebrick, false);   // top wall
		fillWithBlocks(world, box, 12,lvl,1, 12,7,11, Blocks.stonebrick, Blocks.stonebrick, false); // right wall
		fillWithBlocks(world, box, 0,lvl,12, 12,9,12, Blocks.stonebrick, Blocks.stonebrick, false); // bottom wall
		fillWithBlocks(world, box, 0,lvl,1, 0,9,11, Blocks.stonebrick, Blocks.stonebrick, false);   // left wall
		fillWithBlocks(world, box, 1,lvl,2, 4,3,2, Blocks.stonebrick, Blocks.stonebrick, false);   // inner top wall
		fillWithBlocks(world, box, 5,lvl,2, 5,7,11, Blocks.stonebrick, Blocks.stonebrick, false);  // inner right wall
		// Table
		for (int zz = 4; zz <= 9; zz++) {
			placeBlockAtCurrentPosition(world, Blocks.dark_oak_stairs, eastFacingStair, 2,lvl,zz, box);
			placeBlockAtCurrentPosition(world, Blocks.dark_oak_stairs, westFacingStair, 3,lvl,zz, box);
		}
        // Adds custom BARON_CASTLE_CHEST for loot drop
        generateStructureChestContents(world, box, random, 4,lvl,11, ChestGenHooks.getItems(BARON_CASTLE_MESS_HALL_CHEST, random), 
        		ChestGenHooks.getCount(BARON_CASTLE_MESS_HALL_CHEST, random));
        placeDoorAtCurrentPosition(world, box, random, 12,lvl,7, getMetadataWithOffset(Blocks.wooden_door, EAST)); // front door
        placeDoorAtCurrentPosition(world, box, random, 5,lvl,3, getMetadataWithOffset(Blocks.wooden_door, EAST));  // inside door
        int redCarpet = 14;
        for (int xx = 6; xx <= 11; xx++) {
			for (int zz = 4; zz <= 9; zz++) {
				placeBlockAtCurrentPosition(world, Blocks.carpet, redCarpet, xx,lvl,zz, box);
			}
        }

        // 3rd floor
        lvl = 3;
        placeBlockAtCurrentPosition(world, Blocks.torch, southFacingTorch, 2,lvl,3, box);
        placeBlockAtCurrentPosition(world, Blocks.torch, northFacingTorch, 2,lvl,11, box);
        placeBlockAtCurrentPosition(world, Blocks.torch, northFacingTorch, 6,lvl,11, box);

        // 4th floor
        lvl = 4;
		fillWithBlocks(world, box, 1,lvl,2, 4,lvl,11, Blocks.stonebrick, Blocks.stonebrick, false);   // floor

        // 5th floor
        lvl = 5;
        placeBlockAtCurrentPosition(world, Blocks.torch, southFacingTorch, 11,lvl,1, box);
		fillWithBlocks(world, box, 1,lvl,6, 4,7,6, Blocks.stonebrick, Blocks.stonebrick, false);  // wall
        placeDoorAtCurrentPosition(world, box, random, 3,lvl,6, getMetadataWithOffset(Blocks.wooden_door, NORTH)); // door
        placeBlockAtCurrentPosition(world, Blocks.bookshelf, 0, 1,lvl,7, box);
        generateStructureChestContents(world, box, random, 1,lvl,8, ChestGenHooks.getItems(BARON_CASTLE_STUDY_CHEST, random), 
        		ChestGenHooks.getCount(BARON_CASTLE_STUDY_CHEST, random));
        generateStructureChestContents(world, box, random, 1,lvl,10, ChestGenHooks.getItems(BARON_CASTLE_STUDY_CHEST, random), 
        		ChestGenHooks.getCount(BARON_CASTLE_STUDY_CHEST, random));
        placeBlockAtCurrentPosition(world, Blocks.bookshelf, 0, 1,lvl,11, box);
        // chair and table, the table isn't always aligned right
		placeBlockAtCurrentPosition(world, Blocks.dark_oak_stairs, northFacingOakStair, 3,lvl,10, box);        
		placeBlockAtCurrentPosition(world, Blocks.dark_oak_stairs, southFacingStair+4, 2,lvl,11, box);
		placeBlockAtCurrentPosition(world, Blocks.dark_oak_stairs, southFacingStair+4, 3,lvl,11, box);
        placeBlockAtCurrentPosition(world, Blocks.bookshelf, 0, 4,lvl,11, box);

        // 6th floor
        lvl = 6;
        placeBlockAtCurrentPosition(world, Blocks.torch, northFacingTorch, 4,lvl,5, box);
        placeBlockAtCurrentPosition(world, Blocks.bookshelf, 0, 1,lvl,7, box);
        placeBlockAtCurrentPosition(world, Blocks.bookshelf, 0, 1,lvl,11, box);
        placeBlockAtCurrentPosition(world, Blocks.bookshelf, 0, 4,lvl,11, box);
        
        // 7th floor
        lvl = 7;
        placeBlockAtCurrentPosition(world, Blocks.bookshelf, 0, 1,lvl,7, box);
        placeBlockAtCurrentPosition(world, Blocks.bookshelf, 0, 1,lvl,8, box);
        placeBlockAtCurrentPosition(world, Blocks.bookshelf, 0, 1,lvl,9, box);
        placeBlockAtCurrentPosition(world, Blocks.bookshelf, 0, 1,lvl,10, box);
        placeBlockAtCurrentPosition(world, Blocks.bookshelf, 0, 1,lvl,11, box);
        placeBlockAtCurrentPosition(world, Blocks.torch, northFacingTorch, 3,lvl,11, box);
        placeBlockAtCurrentPosition(world, Blocks.bookshelf, 0, 4,lvl,11, box);

        // 8th floor
        lvl = 8;
		fillWithBlocks(world, box, 1,lvl,1, 12,lvl,11, Blocks.stonebrick, Blocks.stonebrick, false);   // floor
		fillWithAir(world, box, 1,lvl,2, 1,lvl,5); // stair well
		int stoneBrickSlab = 5;
		int stoneBrickSlabTop = stoneBrickSlab + 8;
        placeBlockAtCurrentPosition(world, Blocks.stone_slab, stoneBrickSlabTop, 13,lvl,3, box);
        placeBlockAtCurrentPosition(world, Blocks.stone_slab, stoneBrickSlabTop, 13,lvl,5, box);
        placeBlockAtCurrentPosition(world, Blocks.stone_slab, stoneBrickSlabTop, 13,lvl,7, box);
        placeBlockAtCurrentPosition(world, Blocks.stone_slab, stoneBrickSlabTop, 13,lvl,9, box);
		for (int zz = 3; zz <= 9; zz++)
	        placeBlockAtCurrentPosition(world, Blocks.stone_slab, stoneBrickSlabTop, 14,lvl,zz, box);			
        placeBlockAtCurrentPosition(world, Blocks.stone_brick_stairs, westFacingStair+4, 13,lvl,4, box); // inverted stairs
        placeBlockAtCurrentPosition(world, Blocks.stone_brick_stairs, westFacingStair+4, 13,lvl,6, box); // inverted stairs
        placeBlockAtCurrentPosition(world, Blocks.stone_brick_stairs, westFacingStair+4, 13,lvl,8, box); // inverted stairs
		
        // 9th floor
        lvl = 9;
        // Outer walls
		fillWithBlocks(world, box, 12,lvl,1, 12,lvl,3, Blocks.stonebrick, Blocks.stonebrick, false);   // wall
        placeBlockAtCurrentPosition(world, Blocks.stonebrick, 0, 13,lvl,3, box);
		fillWithBlocks(world, box, 14,lvl,3, 14,lvl,9, Blocks.stonebrick, Blocks.stonebrick, false);   // wall
        placeBlockAtCurrentPosition(world, Blocks.stonebrick, 0, 13,lvl,9, box);
		fillWithBlocks(world, box, 12,lvl,9, 12,lvl,11, Blocks.stonebrick, Blocks.stonebrick, false);   // wall
		// Inner walls
		fillWithBlocks(world, box, 2,lvl,2, 10,13,2, Blocks.stonebrick, Blocks.stonebrick, false);    // wall
		fillWithBlocks(world, box, 2,lvl,10, 10,13,10, Blocks.stonebrick, Blocks.stonebrick, false);  // wall
		fillWithBlocks(world, box, 2,lvl,3, 2,13,9, Blocks.stonebrick, Blocks.stonebrick, false);     // wall
		fillWithBlocks(world, box, 10,lvl,3, 10,13,9, Blocks.stonebrick, Blocks.stonebrick, false);   // wall
        for (int xx = 5; xx <= 9; xx++)
        	placeBlockAtCurrentPosition(world, Blocks.bookshelf, 0, xx,lvl,3, box);
        for (int xx = 6; xx <= 9; xx++)
        	placeBlockAtCurrentPosition(world, Blocks.bookshelf, 0, xx,lvl,9, box);
        generateStructureChestContents(world, box, random, 9,lvl,7, ChestGenHooks.getItems(BARON_CASTLE_CHEST, random), 
        		ChestGenHooks.getCount(BARON_CASTLE_CHEST, random));
        // place a bed
        placeBedAtCurrentPosition(world, box, random, 9,lvl,6, bedHeadFacingEast, true); // head
        placeBedAtCurrentPosition(world, box, random, 8,lvl,6, bedHeadFacingEast, false); // foot
        placeDoorAtCurrentPosition(world, box, random, 2,lvl,6, getMetadataWithOffset(Blocks.wooden_door, WEST)); // door

        // 10th floor
        lvl = 10;
        for (int xx = 0; xx <= 12; xx++, xx++)
        	placeBlockAtCurrentPosition(world, Blocks.stone_slab, stoneBrickSlab, xx,lvl,0, box);
        for (int xx = 0; xx <= 12; xx++, xx++)
        	placeBlockAtCurrentPosition(world, Blocks.stone_slab, stoneBrickSlab, xx,lvl,12, box);
        for (int zz = 2; zz <= 10; zz++, zz++)
        	placeBlockAtCurrentPosition(world, Blocks.stone_slab, stoneBrickSlab, 0,lvl,zz, box);
        for (int zz = 3; zz <= 9; zz++, zz++)
        	placeBlockAtCurrentPosition(world, Blocks.stone_slab, stoneBrickSlab, 14,lvl,zz, box);
    	placeBlockAtCurrentPosition(world, Blocks.stone_slab, stoneBrickSlab, 12,lvl,2, box);
    	placeBlockAtCurrentPosition(world, Blocks.stone_slab, stoneBrickSlab, 12,lvl,10, box);

        // 11th floor
        lvl = 11;
        for (int xx = 5; xx <= 9; xx++)
        	placeBlockAtCurrentPosition(world, Blocks.bookshelf, 0, xx,lvl,3, box);
        placeBlockAtCurrentPosition(world, Blocks.torch, northFacingTorch, 9,lvl,5, box);
        placeBlockAtCurrentPosition(world, Blocks.torch, northFacingTorch, 7,lvl,9, box);

        // 12th floor
        lvl = 12;
		fillWithBlocks(world, box, 3,lvl,3, 10,lvl,9, Blocks.stonebrick, Blocks.stonebrick, false);   // floor
		fillWithAir(world, box, 3,lvl,7, 3,lvl,9); // stair well
		fillWithAir(world, box, 4,lvl,9, 5,lvl,9); // stair well

        // 14th floor
        lvl = 14;
        for (int xx = 2; xx <= 10; xx++, xx++)
        	placeBlockAtCurrentPosition(world, Blocks.stone_slab, stoneBrickSlab, xx,lvl,2, box);
        for (int xx = 2; xx <= 10; xx++, xx++)
        	placeBlockAtCurrentPosition(world, Blocks.stone_slab, stoneBrickSlab, xx,lvl,10, box);
        for (int zz = 4; zz <= 8; zz++, zz++)
        	placeBlockAtCurrentPosition(world, Blocks.stone_slab, stoneBrickSlab, 2,lvl,zz, box);
        for (int zz = 4; zz <= 8; zz++, zz++)
        	placeBlockAtCurrentPosition(world, Blocks.stone_slab, stoneBrickSlab, 10,lvl,zz, box);
        
        // Spiral stairway from top room to roof
        lvl = 9;
        placeBlockAtCurrentPosition(world, Blocks.stonebrick, 0, 3,lvl,7, box);
        placeBlockAtCurrentPosition(world, Blocks.stone_slab, stoneBrickSlab, 4,lvl,7, box);
        placeBlockAtCurrentPosition(world, Blocks.stonebrick, 0, 4,lvl,8, box);
        placeBlockAtCurrentPosition(world, Blocks.stonebrick, 0, 4,lvl,9, box);
        placeBlockAtCurrentPosition(world, Blocks.stonebrick, 0, 5,lvl,9, box);
        placeBlockAtCurrentPosition(world, Blocks.stone_slab, stoneBrickSlab, 3,++lvl,8, box);
        placeBlockAtCurrentPosition(world, Blocks.stonebrick, 0, 4,lvl,8, box);
        placeBlockAtCurrentPosition(world, Blocks.stonebrick, 0, 3,lvl,9, box);
        placeBlockAtCurrentPosition(world, Blocks.stonebrick, 0, 4,lvl,9, box);
        placeBlockAtCurrentPosition(world, Blocks.stonebrick, 0, 4,++lvl,8, box);
        placeBlockAtCurrentPosition(world, Blocks.stone_slab, stoneBrickSlab, 4,lvl,9, box);
        placeBlockAtCurrentPosition(world, Blocks.stonebrick, 0, 5,lvl,9, box);
        placeBlockAtCurrentPosition(world, Blocks.stone_slab, stoneBrickSlab, 5,++lvl,8, box);
		
        // Stairway to top
        placeBlockAtCurrentPosition(world, Blocks.stone_brick_stairs, westFacingStair, 5,1,1, box);
        placeBlockAtCurrentPosition(world, Blocks.stone_brick_stairs, westFacingStair, 4,2,1, box);
        placeBlockAtCurrentPosition(world, Blocks.stone_brick_stairs, westFacingStair, 3,3,1, box);
        placeBlockAtCurrentPosition(world, Blocks.stone_brick_stairs, westFacingStair, 2,4,1, box);
        placeBlockAtCurrentPosition(world, Blocks.stonebrick, 0, 1,4,1, box);
        placeBlockAtCurrentPosition(world, Blocks.stone_brick_stairs, southFacingStair, 1,5,2, box);
        placeBlockAtCurrentPosition(world, Blocks.stone_brick_stairs, southFacingStair, 1,6,3, box);
        placeBlockAtCurrentPosition(world, Blocks.stone_brick_stairs, southFacingStair, 1,7,4, box);
        placeBlockAtCurrentPosition(world, Blocks.stone_brick_stairs, southFacingStair, 1,8,5, box);

        // Glass windows in entire castle
        int grayStaindGlass = 7;
        for (int yy = 2; yy <= 3; yy++) {
			placeBlockAtCurrentPosition(world, Blocks.glass_pane, grayStaindGlass, 8,yy,0, box);
			placeBlockAtCurrentPosition(world, Blocks.glass_pane, grayStaindGlass, 9,yy,0, box);
			placeBlockAtCurrentPosition(world, Blocks.glass_pane, grayStaindGlass, 12,yy,2, box);
			placeBlockAtCurrentPosition(world, Blocks.glass_pane, grayStaindGlass, 12,yy,3, box);
			placeBlockAtCurrentPosition(world, Blocks.glass_pane, grayStaindGlass, 0,yy,4, box);
			placeBlockAtCurrentPosition(world, Blocks.glass_pane, grayStaindGlass, 0,yy,5, box);
			placeBlockAtCurrentPosition(world, Blocks.glass_pane, grayStaindGlass, 0,yy,7, box);
			placeBlockAtCurrentPosition(world, Blocks.glass_pane, grayStaindGlass, 0,yy,8, box);
			placeBlockAtCurrentPosition(world, Blocks.glass_pane, grayStaindGlass, 12,yy,9, box);
			placeBlockAtCurrentPosition(world, Blocks.glass_pane, grayStaindGlass, 12,yy,10, box);
			placeBlockAtCurrentPosition(world, Blocks.glass_pane, grayStaindGlass, 8,yy,12, box);
			placeBlockAtCurrentPosition(world, Blocks.glass_pane, grayStaindGlass, 9,yy,12, box);
        }
        for (int yy = 4; yy <= 5; yy++) {
			placeBlockAtCurrentPosition(world, Blocks.glass_pane, grayStaindGlass, 12,yy,2, box);
			placeBlockAtCurrentPosition(world, Blocks.glass_pane, grayStaindGlass, 12,yy,3, box);
			placeBlockAtCurrentPosition(world, Blocks.glass_pane, grayStaindGlass, 12,yy,9, box);
			placeBlockAtCurrentPosition(world, Blocks.glass_pane, grayStaindGlass, 12,yy,10, box);
        }
		placeBlockAtCurrentPosition(world, Blocks.glass_pane, grayStaindGlass, 10,10,6, box);
		placeBlockAtCurrentPosition(world, Blocks.glass_pane, grayStaindGlass, 10,10,7, box);
		placeBlockAtCurrentPosition(world, Blocks.glass_pane, grayStaindGlass, 10,11,6, box);
		placeBlockAtCurrentPosition(world, Blocks.glass_pane, grayStaindGlass, 10,11,7, box);
	        
        // Spawn guards on top:
        spawnGuards(world, box, 3,13,3, 2);

        // Spawn guards in Mess Hall:
        spawnGuards(world, box, 4,2,6, 1);
        spawnGuards(world, box, 4,2,7, 1);

        // Spawn baron in study:
        spawnBaron(world, box, 3,6,9);
        
        return true;
    }

	/**
     * Spawns a number of guards in this component. Parameters: world, component bounding box, x offset, y
     * offset, z offset, number of guards
     */
    protected void spawnBaron(World world, StructureBoundingBox box, int x, int y, int z) {
        int xx, yy, zz;
        xx = this.getXWithOffset(x, z);
        yy = this.getYWithOffset(y);
        zz = this.getZWithOffset(x, z);
        if (!box.isVecInside(xx, yy, zz)) {
            return;
        }
        if (EntityGuard.isBaronBiome(world, xx, zz)) {
        	EntityBaron baron = (EntityBaron) EntityList.createEntityByName(EntityBaron.getFullName(), world);
			baron.setLocationAndAngles((double)xx + 0.5D, (double)yy, (double)zz + 0.5D, 0.0F, 0.0F);
            world.spawnEntityInWorld(baron);
			LogHelper.info("VillageBaronCastle: Spawned Baron at: " + xx + " " + yy + " " + zz);
        }
    }

	public static void registerBaronCastleChest(){
		// Study chest
		ChestGenHooks.getInfo(BARON_CASTLE_STUDY_CHEST);
   		for(int i = 0; i < commonChestContents.length; i++){
			ChestGenHooks.addItem(BARON_CASTLE_STUDY_CHEST, commonChestContents[i]);
		}
   		ChestGenHooks.addItem(BARON_CASTLE_STUDY_CHEST, new WeightedRandomChestContent(new ItemStack(Items.book), 1, 5, 20));
   		ItemStack magicBook = new ItemStack(Items.enchanted_book);
//   		magicBook.addEnchantment(Enchantment.protection, random.nextInt(3)+1);
		ChestGenHooks.addItem(BARON_CASTLE_STUDY_CHEST, new WeightedRandomChestContent(magicBook, 1, 5, 20));
   		magicBook = new ItemStack(Items.enchanted_book);
//   		magicBook.addEnchantment(Enchantment.infinity, random.nextInt(3)+1);
		ChestGenHooks.addItem(BARON_CASTLE_STUDY_CHEST, new WeightedRandomChestContent(magicBook, 1, 5, 20));
   		magicBook = new ItemStack(Items.enchanted_book);
//   		magicBook.addEnchantment(Enchantment.power, random.nextInt(3));
		ChestGenHooks.addItem(BARON_CASTLE_STUDY_CHEST, new WeightedRandomChestContent(magicBook, 1, 5, 20));
   		magicBook = new ItemStack(Items.enchanted_book);
//   		magicBook.addEnchantment(Enchantment.punch, random.nextInt(3));
		ChestGenHooks.addItem(BARON_CASTLE_STUDY_CHEST, new WeightedRandomChestContent(magicBook, 1, 5, 20));
   		magicBook = new ItemStack(Items.enchanted_book);
//   		magicBook.addEnchantment(Enchantment.unbreaking, random.nextInt(3)+1);
		ChestGenHooks.addItem(BARON_CASTLE_STUDY_CHEST, new WeightedRandomChestContent(magicBook, 1, 5, 20));
		ChestGenHooks.getInfo(BARON_CASTLE_STUDY_CHEST).setMin(3);
		ChestGenHooks.getInfo(BARON_CASTLE_STUDY_CHEST).setMax(7);
		
		// Mess hall chest
		ChestGenHooks.getInfo(BARON_CASTLE_MESS_HALL_CHEST);
   		for(int i = 0; i < commonChestContents.length; i++){
			ChestGenHooks.addItem(BARON_CASTLE_MESS_HALL_CHEST, commonChestContents[i]);
		}
   		for(int i = 0; i < messHallChestContents.length; i++){
			ChestGenHooks.addItem(BARON_CASTLE_MESS_HALL_CHEST, messHallChestContents[i]);
		}
		ChestGenHooks.getInfo(BARON_CASTLE_MESS_HALL_CHEST).setMin(3);
		ChestGenHooks.getInfo(BARON_CASTLE_MESS_HALL_CHEST).setMax(7);

		// All other chests
		ChestGenHooks.getInfo(BARON_CASTLE_CHEST);
   		for(int i = 0; i < commonChestContents.length; i++){
			ChestGenHooks.addItem(BARON_CASTLE_CHEST, commonChestContents[i]);
		}
		ChestGenHooks.getInfo(BARON_CASTLE_CHEST).setMin(3);
		ChestGenHooks.getInfo(BARON_CASTLE_CHEST).setMax(7);
	}
	
	// Call from mod's init
	public static void init() {
		VillageBaronCastle.addVillagePiece(VillageBaronCastle.class, "ViBrnCastle");
		BaronCastleCreationHandler baronCastleCreator = new BaronCastleCreationHandler();
		VillagerRegistry.instance().registerVillageCreationHandler(baronCastleCreator);
		VillageBaronCastle.registerBaronCastleChest();
	}
}