package ru.fewizz.lightbukkit.mixin.world.chunk;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.world.chunk.Chunk;
import ru.fewizz.lightbukkit.core.*;
import ru.fewizz.lightbukkit.interfaces.IChunk;

@Mixin(Chunk.class)
public abstract class MixinChunk implements IChunk {
	LBChunk lbChunk;
	
	@Inject(at = @At("RETURN"), method = "<init>(Lnet/minecraft/world/World;II)V")
	void onInit(CallbackInfo ci) {
		lbChunk = new LBChunk((net.minecraft.world.chunk.Chunk) (Object)this);
	}

	@Override
	public LBWorld getLBWorld() {
		return lbChunk.getWorld();
	}
	
	@Override
	public LBChunk getLBChunk() {
		return lbChunk;
	}
}
