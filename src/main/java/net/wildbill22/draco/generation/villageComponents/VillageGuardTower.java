package net.wildbill22.draco.generation.villageComponents;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.common.registry.VillagerRegistry;
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
import net.wildbill22.draco.entities.hostile.EntityGuard;
import net.wildbill22.draco.generation.villageHandlers.GuardTowerCreationHandler;
import net.wildbill22.draco.items.ModItems;
import net.wildbill22.draco.lib.BALANCE;
import net.wildbill22.draco.lib.LogHelper;

public class VillageGuardTower extends StructureVillagePieces.Village
{
	public static final String TOWER_CHEST = "villagegGuardTower";
	public static final WeightedRandomChestContent[] towerChestContents = new WeightedRandomChestContent[]{
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
    private static final int HEIGHT = 13;
	private final int EAST  = 1;

    public VillageGuardTower() {}
    
	public VillageGuardTower(StructureVillagePieces.Start startPiece, int type, Random random, StructureBoundingBox _boundingBox, int direction){
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

	public static StructureVillagePieces.Village buildComponent(StructureVillagePieces.Start startPiece, 
			@SuppressWarnings("rawtypes") List pieces, Random random, int x, int y, int z, int direction, int type) {
        StructureBoundingBox _boundingBox = StructureBoundingBox.getComponentToAddBoundingBox(x, y, z, 0, 0, 0, 7, 14, 6, direction);
        if (canVillageGoDeeper(_boundingBox)) { 
        	if (StructureComponent.findIntersecting(pieces, _boundingBox) == null) {
        		return new VillageGuardTower(startPiece, type, random, _boundingBox, direction);
        	}
        }
		return null;
	}
	
	// Only create the Guard Tower in biomes with Longbow or Crossbow guards
	@Override
	public boolean addComponentParts(World world, Random random, StructureBoundingBox box) {
		BiomeGenBase biome = world.getBiomeGenForCoords(box.minX, box.minZ);
		if (biome != ModBiomes.biomeCityPlains && biome != ModBiomes.biomeTownForest)
			return true;

		LogHelper.info("VillageGuardTower: Build guard tower at: " + box.minX + ", " + box.minZ);
        if (averageGroundLevel < 0){
            averageGroundLevel = getAverageGroundLevel(world, box);
            if (averageGroundLevel < 0){
                return true;
            }
            boundingBox.offset(0, this.averageGroundLevel - boundingBox.maxY + HEIGHT - 0, 0);
        }

        int westFacingStair = this.getMetadataWithOffset(Blocks.stone_brick_stairs, 1);

        // Clear out area of trees
		fillWithAir(world, box, 0,0,0, 6,14,6);

        // Clear out the area for the building and make a base
//    	clearBlocks(world, 0,0, 6,6, box);
        for (int xx = 0; xx <= 6 ; xx++){
            for (int zz = 0; zz <= 6; zz++){
                clearCurrentPositionBlocksUpwards(world, xx,0,zz, box);
                this.func_151554_b(world, Blocks.cobblestone, 0, xx, -1, zz, box);
            }
        }
		
    	// bottom floor
        int lvl = 0; // Level under construction
		fillWithBlocks(world, box, 0,lvl,0, 6,lvl,6, Blocks.stonebrick, Blocks.stonebrick, false);  // floor
        placeBlockAtCurrentPosition(world, Blocks.stone_brick_stairs, westFacingStair, 7,lvl,3, box); // front stairs

        // 1st floor
        lvl = 1;
		fillWithBlocks(world, box, 0,lvl,0, 6,3,0, Blocks.stonebrick, Blocks.stonebrick, false);  // north wall
		fillWithBlocks(world, box, 0,lvl,6, 6,3,6, Blocks.stonebrick, Blocks.stonebrick, false);  // south wall
		fillWithBlocks(world, box, 0,lvl,1, 0,3,5, Blocks.stonebrick, Blocks.stonebrick, false);  // west wall
		fillWithBlocks(world, box, 6,lvl,1, 6,3,5, Blocks.stonebrick, Blocks.stonebrick, false);  // east wall
        // Adds custom TOWER_CHEST for loot drop
        generateStructureChestContents(world, box, random, 2,lvl,3, ChestGenHooks.getItems(TOWER_CHEST, random), 
        		ChestGenHooks.getCount(TOWER_CHEST, random));
        placeDoorAtCurrentPosition(world, box, random, 6,lvl,3, getMetadataWithOffset(Blocks.wooden_door, EAST)); // front door

        // 2nd floor
        lvl = 2;
        int eastFacingTorch = this.getMetadataWithOffset(Blocks.torch, 1);
        int northFacingTorch = this.getMetadataWithOffset(Blocks.torch, 4);
        placeBlockAtCurrentPosition(world, Blocks.torch, northFacingTorch, 1,lvl,5, box);

        // 4th floor
        lvl = 4;
		fillWithBlocks(world, box, 1,lvl,1, 5,8,1, Blocks.stonebrick, Blocks.stonebrick, false);  // north wall
		fillWithBlocks(world, box, 1,lvl,5, 5,8,5, Blocks.stonebrick, Blocks.stonebrick, false);  // south wall
		fillWithBlocks(world, box, 1,lvl,2, 1,8,4, Blocks.stonebrick, Blocks.stonebrick, false);  // east wall
		fillWithBlocks(world, box, 5,lvl,2, 5,8,4, Blocks.stonebrick, Blocks.stonebrick, false);  // west wall

        // 8th floor
        lvl = 8;
		fillWithBlocks(world, box, 0,lvl,0, 6,13,0, Blocks.stonebrick, Blocks.stonebrick, false);  // north wall
		fillWithBlocks(world, box, 0,lvl,6, 6,13,6, Blocks.stonebrick, Blocks.stonebrick, false);  // south wall
		fillWithBlocks(world, box, 0,lvl,1, 0,13,5, Blocks.stonebrick, Blocks.stonebrick, false);  // west wall
		fillWithBlocks(world, box, 6,lvl,1, 6,13,5, Blocks.stonebrick, Blocks.stonebrick, false);  // east wall
		fillWithBlocks(world, box, 1,lvl,1, 5,lvl,1, Blocks.stonebrick, Blocks.stonebrick, false);  // north floor
		fillWithBlocks(world, box, 1,lvl,5, 5,lvl,5, Blocks.stonebrick, Blocks.stonebrick, false);  // south floor
		fillWithBlocks(world, box, 1,lvl,2, 1,lvl,4, Blocks.stonebrick, Blocks.stonebrick, false);  // east floor
		fillWithBlocks(world, box, 5,lvl,2, 5,lvl,4, Blocks.stonebrick, Blocks.stonebrick, false);  // west floor

        // 9th floor
        lvl = 9;
        // Adds custom TOWER_CHEST for loot drop
        generateStructureChestContents(world, box, random, 1,lvl,3, ChestGenHooks.getItems(TOWER_CHEST, random), 
        		ChestGenHooks.getCount(TOWER_CHEST, random));

        // 10th floor
        lvl = 10;
        placeBlockAtCurrentPosition(world, Blocks.torch, eastFacingTorch, 1,lvl,4, box);

        // 12th floor
        lvl = 12;
		fillWithBlocks(world, box, 1,lvl,1, 5,lvl,1, Blocks.stonebrick, Blocks.stonebrick, false);  // north floor
		fillWithBlocks(world, box, 1,lvl,5, 5,lvl,5, Blocks.stonebrick, Blocks.stonebrick, false);  // south floor
		fillWithBlocks(world, box, 1,lvl,2, 1,lvl,4, Blocks.stonebrick, Blocks.stonebrick, false);  // east floor
		fillWithBlocks(world, box, 5,lvl,2, 5,lvl,4, Blocks.stonebrick, Blocks.stonebrick, false);  // west floor

        // 14th floor
		int stoneBrickSlab = 5;
		int stoneBrickSlabTop = stoneBrickSlab + 8;
        lvl = 14;
        for (int xx = 0; xx <= 6; xx++, xx++)
        	placeBlockAtCurrentPosition(world, Blocks.stone_slab, stoneBrickSlab, xx,lvl,0, box);
        for (int xx = 0; xx <= 6; xx++, xx++)
        	placeBlockAtCurrentPosition(world, Blocks.stone_slab, stoneBrickSlab, xx,lvl,6, box);
        for (int zz = 2; zz <= 4; zz++, zz++)
        	placeBlockAtCurrentPosition(world, Blocks.stone_slab, stoneBrickSlab, 0,lvl,zz, box);
        for (int zz = 2; zz <= 4; zz++, zz++)
        	placeBlockAtCurrentPosition(world, Blocks.stone_slab, stoneBrickSlab, 6,lvl,zz, box);

        // Glass windows in entire tower
        int grayStaindGlass = 7;
        lvl = 10;
		placeBlockAtCurrentPosition(world, Blocks.glass_pane, grayStaindGlass, 3,lvl,0, box);
		placeBlockAtCurrentPosition(world, Blocks.glass_pane, grayStaindGlass, 3,lvl,6, box);
		placeBlockAtCurrentPosition(world, Blocks.glass_pane, grayStaindGlass, 0,lvl,3, box);
		placeBlockAtCurrentPosition(world, Blocks.glass_pane, grayStaindGlass, 6,lvl,3, box);

        // Spiral stairway from top room to roof
        placeBlockAtCurrentPosition(world, Blocks.stone_slab, stoneBrickSlab, 4,1,2, box);
        placeBlockAtCurrentPosition(world, Blocks.stone_slab, stoneBrickSlabTop, 3,1,2, box);
        placeBlockAtCurrentPosition(world, Blocks.stonebrick, 0, 3,1,3, box);
        placeBlockAtCurrentPosition(world, Blocks.stone_slab, stoneBrickSlab, 2,2,2, box);
        placeBlockAtCurrentPosition(world, Blocks.stone_slab, stoneBrickSlabTop, 2,2,3, box);
        placeBlockAtCurrentPosition(world, Blocks.stonebrick, 0, 3,2,3, box);
        placeBlockAtCurrentPosition(world, Blocks.stone_slab, stoneBrickSlab, 2,3,4, box);
        placeBlockAtCurrentPosition(world, Blocks.stone_slab, stoneBrickSlabTop, 3,3,4, box);
        placeBlockAtCurrentPosition(world, Blocks.stonebrick, 0, 3,3,3, box);
        placeBlockAtCurrentPosition(world, Blocks.stone_slab, stoneBrickSlab, 4,4,4, box);
        placeBlockAtCurrentPosition(world, Blocks.stone_slab, stoneBrickSlabTop, 4,4,3, box);
        placeBlockAtCurrentPosition(world, Blocks.stonebrick, 0, 3,4,3, box);
        placeBlockAtCurrentPosition(world, Blocks.stone_slab, stoneBrickSlab, 4,5,2, box);
        placeBlockAtCurrentPosition(world, Blocks.stone_slab, stoneBrickSlabTop, 3,5,2, box);
        placeBlockAtCurrentPosition(world, Blocks.stonebrick, 0, 3,5,3, box);
        placeBlockAtCurrentPosition(world, Blocks.stone_slab, stoneBrickSlab, 2,6,2, box);
        placeBlockAtCurrentPosition(world, Blocks.stone_slab, stoneBrickSlabTop, 2,6,3, box);
        placeBlockAtCurrentPosition(world, Blocks.stonebrick, 0, 3,6,3, box);
        placeBlockAtCurrentPosition(world, Blocks.stone_slab, stoneBrickSlab, 2,7,4, box);
        placeBlockAtCurrentPosition(world, Blocks.stone_slab, stoneBrickSlabTop, 3,7,4, box);
        placeBlockAtCurrentPosition(world, Blocks.stonebrick, 0, 3,7,3, box);
        placeBlockAtCurrentPosition(world, Blocks.stone_slab, stoneBrickSlab, 4,8,4, box);
        placeBlockAtCurrentPosition(world, Blocks.stone_slab, stoneBrickSlabTop, 4,8,3, box);
        placeBlockAtCurrentPosition(world, Blocks.stonebrick, 0, 3,8,3, box);
        placeBlockAtCurrentPosition(world, Blocks.stone_slab, stoneBrickSlab, 4,9,2, box);
        placeBlockAtCurrentPosition(world, Blocks.stone_slab, stoneBrickSlabTop, 3,9,2, box);
        placeBlockAtCurrentPosition(world, Blocks.stonebrick, 0, 3,9,3, box);
        placeBlockAtCurrentPosition(world, Blocks.stone_slab, stoneBrickSlab, 2,10,2, box);
        placeBlockAtCurrentPosition(world, Blocks.stone_slab, stoneBrickSlabTop, 2,10,3, box);
        placeBlockAtCurrentPosition(world, Blocks.stonebrick, 0, 3,10,3, box);
        placeBlockAtCurrentPosition(world, Blocks.stone_slab, stoneBrickSlab, 2,11,4, box);
        placeBlockAtCurrentPosition(world, Blocks.stone_slab, stoneBrickSlabTop, 3,11,4, box);
        placeBlockAtCurrentPosition(world, Blocks.stonebrick, 0, 3,11,3, box);
        placeBlockAtCurrentPosition(world, Blocks.stone_slab, stoneBrickSlab, 4,12,4, box);
        placeBlockAtCurrentPosition(world, Blocks.stone_slab, stoneBrickSlabTop, 4,12,3, box);
        placeBlockAtCurrentPosition(world, Blocks.stonebrick, 0, 3,12,3, box);
       
        // Spawn guards on top:
        // world, box, x, y, z, number of villages
        spawnGuards(world, box, 1,12,1,1);
        spawnGuards(world, box, 5,12,5,1);
        
        return true;
    }

    /**
     * Spawns a number of guards in this component.
     * 
     * @param World
     * @param StructureBoundingBox
     * @param x
     * @param y
     * @param z
     * @param numGuards
     */
    protected void spawnGuards(World world, StructureBoundingBox box, int x, int y, int z, int numGuards) {
    	if (numGuards == 0)
    		return;
        int i1, xx, yy, zz;
		for (i1 = 0; i1 < numGuards; ++i1) {
            xx = this.getXWithOffset(x + i1, z);
            yy = this.getYWithOffset(y);
            zz = this.getZWithOffset(x + i1, z);

            if (!box.isVecInside(xx, yy, zz)) {
                return;
            }

			EntityGuard guard = EntityGuard.createGuardTypePerBiome(world, xx, zz);
			guard.setLocationAndAngles((double)xx + 0.5D, (double)yy, (double)zz + 0.5D, 0.0F, 0.0F);
            world.spawnEntityInWorld(guard);
        }
    }

    public static void registerGuardTowerChest(){
		ChestGenHooks.getInfo(TOWER_CHEST);
   		for(int i = 0; i < towerChestContents.length; i++){
			ChestGenHooks.addItem(TOWER_CHEST, towerChestContents[i]);
		}
		ChestGenHooks.getInfo(TOWER_CHEST).setMin(3);
		ChestGenHooks.getInfo(TOWER_CHEST).setMax(7);
	}

	// Call from mod's init
	public static void init() {
		VillageGuardTower.addVillagePiece(VillageGuardTower.class, "ViGrdT");
		GuardTowerCreationHandler guardTowerCreator = new GuardTowerCreationHandler();
		VillagerRegistry.instance().registerVillageCreationHandler(guardTowerCreator);
		VillageGuardTower.registerGuardTowerChest();
	}
}