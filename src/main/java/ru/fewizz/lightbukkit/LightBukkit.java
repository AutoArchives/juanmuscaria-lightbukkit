package ru.fewizz.lightbukkit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginLoadOrder;

import net.minecraft.server.MinecraftServer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartedEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import ru.fewizz.lightbukkit.impl.LBServer;

@Mod(modid = LightBukkit.MODID, name = LightBukkit.NAME, version = "0")
public class LightBukkit {
	public static final String MODID = "lightbukkit";
	public static final String NAME = "LightBukkit";
	public static final String BUKKIT_VERSION = "1.12.2-R0.1-SNAPSHOT";
	public static final Logger LOGGER = LogManager.getLogger(MODID);
	
	@Instance
	static LightBukkit instance;
	static LBServer server;

	@EventHandler
	public void preinit(FMLPreInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(instance);
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
	}
	
	@EventHandler
	public void onServerStarting(FMLServerStartingEvent event) {
		server = new LBServer();
		Bukkit.setServer(server);
		server.onServerStarting();
	}
	
	@EventHandler
	public void onServerStarted(FMLServerStartedEvent event) {
		server.onServerStarted();
	}
	
	public static MinecraftServer getMCServer() {
		return FMLCommonHandler.instance().getMinecraftServerInstance();
	}
}
