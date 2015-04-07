package net.wildbill22.draco.generation.structures;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChunkCoordinates;

public class StructureCoordinates extends ChunkCoordinates {
	protected Structures type;
	
	public StructureCoordinates() {	}

	public StructureCoordinates(Structures type, int posX, int posY, int posZ) {	
		super(posX, posY, posZ);
		this.type = type;
	}

	public StructureCoordinates(Structures type, StructureCoordinates coords) {	
		super(coords);
		this.type = type;
	}

	public enum Structures {
		OTHER, BALLISTA_TOWER, CATAPULT_TOWER;
	}

	protected int toInteger(Structures type) {
		switch (type) {
			case OTHER:
				return 0;
			case BALLISTA_TOWER:
				return 1;
			case CATAPULT_TOWER:
				return 2;
			default:
				return 0;
		}
	}
	
	protected Structures toEnum(int type) {
		switch (type) {
		case 0:
			return Structures.OTHER;
		case 1:
			return Structures.BALLISTA_TOWER;
		case 2:
			return Structures.CATAPULT_TOWER;
		default:
			return Structures.OTHER;
		}
	}

	public void readFromNBT(NBTTagCompound nbt, int i) {
		NBTTagCompound nbtTag = nbt.getCompoundTag("coords" + i);
		type = toEnum(nbtTag.getInteger("type"));
		posX = nbtTag.getInteger("x");
		posY = nbtTag.getInteger("y");
		posZ = nbtTag.getInteger("z");
	}

	public void writeToNBT(NBTTagCompound nbt, int i) {
		NBTTagCompound nbtTag = new NBTTagCompound();
		nbtTag.setInteger("type", toInteger(type));
		nbtTag.setInteger("x", posX);
		nbtTag.setInteger("y", posY);
		nbtTag.setInteger("z", posZ);
		nbt.setTag("coords" + i, nbtTag);
	}
}
