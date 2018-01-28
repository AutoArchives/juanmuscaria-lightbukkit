package ru.fewizz.lightbukkit.impl;

import org.bukkit.ChunkSnapshot;
import org.bukkit.block.*;
import org.bukkit.entity.Entity;

import net.minecraft.world.chunk.Chunk;
import ru.fewizz.lightbukkit.interfaces.IChunk;

public class LBChunk implements org.bukkit.Chunk {
	final Chunk mcChunk;
	final IChunk icmChunk;
	
	public LBChunk(Chunk chunk) {
		this.mcChunk = chunk;
		this.icmChunk = (IChunk) chunk;
	}

	@Override
	public int getX() {
		return mcChunk.x;
	}

	@Override
	public int getZ() {
		return mcChunk.z;
	}

	@Override
	public LBWorld getWorld() {
		return icmChunk.getLBWorld();
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
		return mcChunk.isLoaded();
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
