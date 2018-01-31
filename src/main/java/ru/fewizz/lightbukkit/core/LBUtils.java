package ru.fewizz.lightbukkit.core;

import org.bukkit.*;
import org.bukkit.block.BlockFace;
import org.bukkit.inventory.ItemStack;

import net.minecraft.item.*;
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
	
	@SuppressWarnings("deprecation")
	public static ItemStack lbStack(net.minecraft.item.ItemStack mcStack) {
		//if(mcStack.getItem() instanceof ItemBlock)
		return new ItemStack(Item.getIdFromItem(mcStack.getItem()), mcStack.getCount(), (short)mcStack.getItemDamage());
	}
	
	@SuppressWarnings("deprecation")
	public static net.minecraft.item.ItemStack mcStack(ItemStack s) {
		return new net.minecraft.item.ItemStack(Item.getItemById(s.getTypeId()), s.getAmount(), s.getDurability());
	}
}
