package ru.fewizz.lightbukkit.mixin.server;

import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.server.MinecraftServer;

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
