package ru.fewizz.lightbukkit.mixin.server;

import org.bukkit.World;
import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.world.chunk.Chunk;
import ru.fewizz.lightbukkit.interfaces.IBLWorldProvider;

@Mixin(Chunk.class)
public class MixinChunk {
	
}
