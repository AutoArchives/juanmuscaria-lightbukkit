package ru.fewizz.lightbukkit.mixin.entity.player;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.entity.player.EntityPlayerMP;
import ru.fewizz.lightbukkit.core.*;
import ru.fewizz.lightbukkit.interfaces.IPlayer;

@Mixin(EntityPlayerMP.class)
public abstract class MixinEntityPlayerMP implements IPlayer {
	LBPlayer lbPlayer;

	@Inject(at = @At("RETURN"), method = "<init>")
	void onInit(CallbackInfo ci) {
		LightBukkit.server.onEntityPlayerMPCreated((EntityPlayerMP)(Object)this);
	}
	
	@Override
	public LBPlayer getLBPlayer() {
		return lbPlayer;
	}
	
	@Override
	public void setLBPlayer(LBPlayer player) {
		this.lbPlayer = player;
	}

}
