package ru.fewizz.lightbukkit.impl;

import java.util.Collection;
import java.util.List;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.block.PistonMoveReaction;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;

public class LBBLock implements Block {
	final int x;
	final int y;
	final int z;
	final int dim;

	public LBBLock(int x, int y, int z, LBWorld w) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.dim = w.mcWorld.provider.getDimension();
	}
	
	public LBBLock(int x, int y, int z, int dim) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.dim = dim;
	}

	@Override
	public void setMetadata(String metadataKey, MetadataValue newMetadataValue) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<MetadataValue> getMetadata(String metadataKey) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasMetadata(String metadataKey) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void removeMetadata(String metadataKey, Plugin owningPlugin) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public byte getData() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Block getRelative(int modX, int modY, int modZ) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Block getRelative(BlockFace face) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Block getRelative(BlockFace face, int distance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Material getType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getTypeId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public byte getLightLevel() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public byte getLightFromSky() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public byte getLightFromBlocks() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public World getWorld() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getX() {
		return x;
	}

	@Override
	public int getY() {
		return y;
	}

	@Override
	public int getZ() {
		return z;
	}

	@Override
	public Location getLocation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Location getLocation(Location loc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Chunk getChunk() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setData(byte data) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setData(byte data, boolean applyPhysics) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setType(Material type) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setType(Material type, boolean applyPhysics) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean setTypeId(int type) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean setTypeId(int type, boolean applyPhysics) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean setTypeIdAndData(int type, byte data, boolean applyPhysics) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public BlockFace getFace(Block block) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BlockState getState() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Biome getBiome() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setBiome(Biome bio) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isBlockPowered() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isBlockIndirectlyPowered() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isBlockFacePowered(BlockFace face) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isBlockFaceIndirectlyPowered(BlockFace face) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getBlockPower(BlockFace face) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getBlockPower() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isLiquid() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double getTemperature() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getHumidity() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public PistonMoveReaction getPistonMoveReaction() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean breakNaturally() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean breakNaturally(ItemStack tool) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Collection<ItemStack> getDrops() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<ItemStack> getDrops(ItemStack tool) {
		// TODO Auto-generated method stub
		return null;
	}

}
