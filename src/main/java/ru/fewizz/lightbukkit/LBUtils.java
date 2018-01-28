package ru.fewizz.lightbukkit;

import org.bukkit.*;
import org.bukkit.block.BlockFace;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

public class LBUtils {
	public static Location getLocationFromBlockPos(World w, BlockPos bp) {
		return new Location(w, bp.getX(), bp.getY(), bp.getZ());
	}
	
	public static BlockFace convertToBLockFace(EnumFacing facing) {
		switch(facing) {
			case DOWN: return BlockFace.DOWN;
			case UP: return BlockFace.UP;
			case NORTH: return BlockFace.NORTH;
			case SOUTH: return BlockFace.SOUTH;
			case WEST: return BlockFace.WEST;
			case EAST: return BlockFace.EAST;
		}
		
		throw new Error("Undefined facing?!");
	}
}
