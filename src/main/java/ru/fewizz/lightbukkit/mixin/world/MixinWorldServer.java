package ru.fewizz.lightbukkit.mixin.world;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.world.*;
import ru.fewizz.lightbukkit.impl.LBWorld;
import ru.fewizz.lightbukkit.interfaces.ILBWorldProvider;

@Mixin(WorldServer.class)
public class MixinWorldServer implements ILBWorldProvider {
	LBWorld lbWorld;
	
	@Override
	public LBWorld getLBWorld() {
		return lbWorld;
	}
	
	@Override
	public void setLBWorld(LBWorld w) {
		lbWorld = w;
	}
	
	@Inject(at = @At("RETURN"), method = "<init>")
	public void onInit(CallbackInfo ci) {
		lbWorld = new LBWorld((WorldServer)(Object)this);
	}
}
