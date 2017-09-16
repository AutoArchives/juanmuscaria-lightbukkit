package ru.fewizz.lightbukkit.impl;

import org.bukkit.ChunkSnapshot;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Entity;

import net.minecraft.world.chunk.Chunk;

public class LBChunk implements org.bukkit.Chunk {
	final Chunk chunk;
	final org.bukkit.World world;
	
	public LBChunk(org.bukkit.World world, Chunk chunk) {
		this.chunk = chunk;
		this.world = world;
	}

	@Override
	public int getX() {
		return chunk.x;
	}

	@Override
	public int getZ() {
		return chunk.z;
	}

	@Override
	public World getWorld() {
		return world;
	}

	@Override
	public Block getBlock(int x, int y, int z) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ChunkSnapshot getChunkSnapshot() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ChunkSnapshot getChunkSnapshot(boolean includeMaxblocky, boolean includeBiome,
			boolean includeBiomeTempRain) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Entity[] getEntities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BlockState[] getTileEntities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isLoaded() {
		return chunk.isLoaded();
	}

	@Override
	public boolean load(boolean generate) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean load() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean unload(boolean save, boolean safe) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean unload(boolean save) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean unload() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isSlimeChunk() {
		// TODO Auto-generated method stub
		return false;
	}

}
