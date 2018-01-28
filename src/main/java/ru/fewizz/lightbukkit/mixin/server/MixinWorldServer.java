package ru.fewizz.lightbukkit.mixin.server;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;

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
	
	@Inject(at = @At("TAIL"), method = "<init>")
	public void onInit() {
		lbWorld = new LBWorld((WorldServer)(Object)this);
	}
}
