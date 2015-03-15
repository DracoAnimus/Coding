package net.wildbill22.draco.lib;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChunkCoordinates;

public class NBTCoordinates extends ChunkCoordinates {
	
	public NBTCoordinates() {	}

	public NBTCoordinates(int posX, int posY, int posZ) {	
		super(posX, posY, posZ);
	}

	public NBTCoordinates(NBTCoordinates coords) {	
		super(coords);
	}

	public void readFromNBT(NBTTagCompound nbt, int i) {
		NBTTagCompound nbtTag = nbt.getCompoundTag(getTag(i));
		posX = nbtTag.getInteger("x");
		posY = nbtTag.getInteger("y");
		posZ = nbtTag.getInteger("z");
	}

	public void writeToNBT(NBTTagCompound nbt, int i) {
		NBTTagCompound nbtTag = new NBTTagCompound();
		nbtTag.setInteger("x", posX);
		nbtTag.setInteger("y", posY);
		nbtTag.setInteger("z", posZ);
		nbt.setTag(getTag(i), nbtTag);
	}
	
	public String getTag(int i) {
		return "coords" + i;
	}
}
