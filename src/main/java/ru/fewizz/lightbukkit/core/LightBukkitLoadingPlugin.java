package ru.fewizz.lightbukkit.core;

import java.util.Map;

import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.Mixins;

import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;

public class LightBukkitLoadingPlugin implements IFMLLoadingPlugin {
	
	public LightBukkitLoadingPlugin() {
		MixinBootstrap.init();
		Mixins.addConfiguration("mixins.lightbukkit.json");
	}

	@Override
	public String[] getASMTransformerClass() {
		return null;
	}

	@Override
	public String getModContainerClass() {
		return null;
	}

	@Override
	public String getSetupClass() {
		return null;
	}

	@Override
	public void injectData(Map<String, Object> data) {
	}

	@Override
	public String getAccessTransformerClass() {
		return null;
	}

}
