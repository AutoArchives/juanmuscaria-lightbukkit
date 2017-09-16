package ru.fewizz.lightbukkit.mixin.server;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.server.MinecraftServer;
import ru.fewizz.lightbukkit.impl.LBServer;

@Mixin(MinecraftServer.class)
public class MixinMinecraftServer {
	/*LBServer lbServer = new LBServer();

	@Inject(at = @At("HEAD"), method = "<init>")
	void onConstruct(CallbackInfo ci) {
		
	}
	
	@Override
	public LBServer getLBServer() {
		// TODO Auto-generated method stub
		return null;
	}*/
}
