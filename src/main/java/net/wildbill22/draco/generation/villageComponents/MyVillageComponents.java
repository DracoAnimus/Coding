package net.wildbill22.draco.generation.villageComponents;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureVillagePieces;
import net.wildbill22.draco.entities.hostile.EntityGuard;
import net.wildbill22.draco.items.ModItems;
import net.wildbill22.draco.lib.BALANCE;

public abstract class MyVillageComponents extends StructureVillagePieces.Village {
	protected int averageGroundLevel = -1;
	protected final int SOUTH = 3;
	protected final int WEST  = 0;
	protected final int NORTH = 2;
	protected final int EAST  = 1;
	protected final int bedHeadFacingSouth = 0;
	protected final int bedHeadFacingWest = 1;
	protected final int bedHeadFacingNorth = 2;
	protected final int bedHeadFacingEast = 3;
    protected final int facingUpTorch = 5;
  
    protected int eastFacingStair;
    protected int westFacingStair;
    protected int southFacingStair;
    protected int northFacingStair;
    protected int northFacingOakStair;;
    protected int eastFacingTorch;
    protected int westFacingTorch;
    protected int southFacingTorch;
    protected int northFacingTorch;
    protected void setMetaData() {
        eastFacingStair = this.getMetadataWithOffset(Blocks.stone_brick_stairs, 0);
        westFacingStair = this.getMetadataWithOffset(Blocks.stone_brick_stairs, 1);
        southFacingStair = this.getMetadataWithOffset(Blocks.stone_brick_stairs, 3);
        northFacingStair = this.getMetadataWithOffset(Blocks.stone_brick_stairs, 2);
        northFacingOakStair = this.getMetadataWithOffset(Blocks.oak_stairs, 2);

        eastFacingTorch = this.getMetadataWithOffset(Blocks.torch, 1);
        westFacingTorch = this.getMetadataWithOffset(Blocks.torch, 2);
        southFacingTorch = this.getMetadataWithOffset(Blocks.torch, 3);
        northFacingTorch = this.getMetadataWithOffset(Blocks.torch, 4);
	}

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
	public static final String BARON_CASTLE_CHEST = "villageBaronCastle";
	public static final WeightedRandomChestContent[] commonChestContents = new WeightedRandomChestContent[]{
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

	public static final String BARON_CASTLE_MESS_HALL_CHEST = "villageBaronCastleMessHall";
	public static final WeightedRandomChestContent[] messHallChestContents = new WeightedRandomChestContent[]{
		new WeightedRandomChestContent(new ItemStack(Items.apple), 1, 5, 20),
		new WeightedRandomChestContent(new ItemStack(Items.cake), 1, 1, 30),
		new WeightedRandomChestContent(new ItemStack(Items.baked_potato), 1, 3, 30),
		new WeightedRandomChestContent(new ItemStack(Items.bread), 1, 5, 30),
		new WeightedRandomChestContent(new ItemStack(Items.cooked_chicken), 1, 5, 30)
    };
	public static final String BARON_CASTLE_STUDY_CHEST = "villageBaronCastleStudy";
	public static final WeightedRandomChestContent[] studyChestContents = new WeightedRandomChestContent[]{
		new WeightedRandomChestContent(new ItemStack(Items.book), 1, 5, 20),
		new WeightedRandomChestContent(new ItemStack(Items.enchanted_book), 1, 1, 30),
		new WeightedRandomChestContent(new ItemStack(Items.enchanted_book), 1, 1, 30),
		new WeightedRandomChestContent(new ItemStack(Items.enchanted_book), 1, 1, 30),
		new WeightedRandomChestContent(new ItemStack(Items.enchanted_book), 1, 1, 30),
		new WeightedRandomChestContent(new ItemStack(Items.enchanted_book), 1, 1, 30)
    };

    public MyVillageComponents() {}

	public MyVillageComponents(StructureVillagePieces.Start startPiece, int type, StructureBoundingBox _boundingBox, int direction){
		super(startPiece, type);
        coordBaseMode = direction;
    	this.boundingBox = _boundingBox;
	}
	
	public static void addVillagePiece(@SuppressWarnings("rawtypes") Class c, String s) { 
		try { 
			MapGenStructureIO.func_143031_a(c, s); 
		} 
		catch (Exception localException) {} 
	} 

	// Helper functions
    
    /**
     * Clears out an area for building
     * @param World
     * @param xMin
     * @param zMin
     * @param xMax
     * @param zMax
     * @param StructureBoundingBox
     */
    protected void clearBlocks(World world, int xMin, int zMin, int xMax, int zMax, StructureBoundingBox box) {
        for (int xx = xMin; xx <= xMax ; xx++){
            for (int zz = zMin; zz <= zMax; zz++){
            	clearBlock(world, xx,0,zz, box);
            }
        }
    }
    
    /**
     * Clears out an block at x,y,z for building
     * @param World
     * @param x
     * @param y Clears from y up, puts down base from y-1 down
     * @param z
     * @param StructureBoundingBox
     */
    protected void clearBlock(World world, int x, int y, int z, StructureBoundingBox box) {
        clearCurrentPositionBlocksUpwards(world, x,y,z, box);
        this.func_151554_b(world, Blocks.stonebrick, 0, x,y-1,z, box);	
    }
    
    protected void placeBedAtCurrentPosition(World world, StructureBoundingBox sbb, Random random, int x, int y, int z, int dir, boolean head) {
        int xPos = this.getXWithOffset(x, z);
        int yPos = this.getYWithOffset(y);
        int zPos = this.getZWithOffset(x, z);

        int metadata = dir;
        switch (this.coordBaseMode) {
        case 3:
        	metadata++;
        case 2:
        	metadata++;
        case 1:
        	metadata++;
        	break;
        default:
        }
        if (metadata > 3)
        	metadata -= 4;
        if (head)
        	metadata += 8;

        if (sbb.isVecInside(xPos, yPos, zPos)) {
        	world.setBlock(xPos, yPos, zPos, Blocks.bed, metadata, 3);
        }
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
}