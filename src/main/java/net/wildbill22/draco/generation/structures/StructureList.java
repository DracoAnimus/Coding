package net.wildbill22.draco.generation.structures;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;

import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.WorldEvent;
import net.wildbill22.draco.generation.structures.StructureCoordinates.Structures;
import net.wildbill22.draco.lib.LogHelper;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class StructureList {
	// Code to find nearest already generated structure (in a thread, so just the largest ones)
	protected static ArrayList<StructureCoordinates> structureList = new ArrayList<StructureCoordinates>();
	private static File saveDir = null;
	private static String datFilename = "dracoStructureList.dat";
    
    public static void preInit(FMLPreInitializationEvent event) {    	
    	setPendingRead();
    }
    
	// Stuff to save the structureList
	public static void writeToNBT(NBTTagCompound nbt) {
		int size = structureList.size();
		nbt.setInteger("size", size);
        Iterator<StructureCoordinates> iterator = structureList.iterator();
		for (int i = 0; i < size; i++) {
        	iterator.next().writeToNBT(nbt, i);
        }
	}
	
	public static void readFromNBT(NBTTagCompound nbt) {
		int size = nbt.getInteger("size");
		for (int i = 0; i < size; i++) {
			StructureCoordinates coords = new StructureCoordinates();
			coords.readFromNBT(nbt, i);
			structureList.add(coords);
		}
	}
	
	public static void writeToFile(WorldEvent.Unload event) {
		if (hasPendingWrite()) {
			clearPendingWrite();
			try {
				if (saveDir != null) {
					File file = new File(saveDir, datFilename);
					if (!file.exists()) {
						file.createNewFile();
					}
					FileOutputStream fos = new FileOutputStream(file);
					NBTTagCompound nbt = new NBTTagCompound();
		   			writeToNBT(nbt);
		   			CompressedStreamTools.writeCompressed(nbt, fos);
					fos.close();
					LogHelper.info("Saved the structure list.");
				}
			} catch(Exception exception) {
				exception.printStackTrace();
			}
		}
	}
		
	public static void readFromFile(WorldEvent.Load event) {
		if (hasPendingRead()) {
			clearPendingRead();
			clearStructureList();
			try {
				if (saveDir == null)
					saveDir = event.world.getSaveHandler().getWorldDirectory();
				File file = new File(saveDir, datFilename);
				if (!file.exists()) {
					return;
				}
				FileInputStream fis = new FileInputStream(file);
				NBTTagCompound nbt = CompressedStreamTools.readCompressed(fis);
				readFromNBT(nbt);
				fis.close();
				LogHelper.info("Loaded the structure list.");
			} catch(Exception exception) {
				exception.printStackTrace();
			}
		}	
	}
	
	private static short dirtyFlag = 0;
	
	private static void setPendingRead() {
		dirtyFlag |= 1;
	}
	
	private static void setPendingWrite() {
		dirtyFlag |= 2;
	}
	
	public static void clearPendingRead() {
		dirtyFlag &= ~1;
	}
	
	public static void clearPendingWrite() {
		dirtyFlag &= ~2;
	}
	
	public static boolean hasPendingRead() {
		return (dirtyFlag & 1) == 1;
	}
	
	public static boolean hasPendingWrite() {
		return (dirtyFlag & 2) == 2;
	}
	
	public static void clearStructureList() {
		structureList.clear();
	}
	
	public static void generatedCenterAt(Structures type, int posX, int posY, int posZ) {
		StructureCoordinates center = new StructureCoordinates(type, posX, posY, posZ);
		structureList.add(center);
		setPendingWrite();
	}
	
	public static void generatedAt(Structures type, StructureCoordinates coords) {
		StructureCoordinates corner = new StructureCoordinates(type, coords);
		structureList.add(corner);
		setPendingWrite();
	}
	
	public static double findNearestStructure(int posX, int posY, int posZ) {
		double nearest = 2000;
		double distance = 2000;
        Iterator<StructureCoordinates> iterator = structureList.iterator();
        
        while (iterator.hasNext()) {
        	StructureCoordinates campCenter = iterator.next();
        	distance = Math.sqrt(campCenter.getDistanceSquared(posX, posY, posZ));
        	if (distance < nearest) {
        		nearest = distance;
        	}
        }

        return nearest;
	}

	public static double findNearestStructure(StructureCoordinates coords) {
		double nearest = 2000;
		double distance = 2000;
        Iterator<StructureCoordinates> iterator = structureList.iterator();
        
        while (iterator.hasNext()) {
        	StructureCoordinates campCenter = iterator.next();
        	distance = Math.sqrt(campCenter.getDistanceSquaredToChunkCoordinates(coords));
        	if (distance < nearest) {
        		nearest = distance;
        	}
        }
        return nearest;
	}
}
