package ru.fewizz.lightbukkit;

import org.apache.logging.log4j.*;
import org.bukkit.Bukkit;

import net.minecraft.server.MinecraftServer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.*;
import net.minecraftforge.fml.common.Mod.*;
import net.minecraftforge.fml.common.event.*;
import ru.fewizz.lightbukkit.impl.LBServer;

@Mod(modid = LightBukkit.MODID, name = LightBukkit.NAME, version = "0")
public class LightBukkit {
	public static final String
		MODID = "lightbukkit",
		VERSION = "0.0.1",
		NAME = "LightBukkit",
		BUKKIT_VERSION = "1.12.2-R0.1-SNAPSHOT";
	public static final Logger LOGGER = LogManager.getLogger(MODID);
	
	@Instance
	static LightBukkit instance;
	public static LBServer server;
	static MinecraftServer mcServer;

	@EventHandler
	public void preinit(FMLPreInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(instance);
		MinecraftForge.EVENT_BUS.register(new LightBukkitEventHandler());
		LOGGER.info("Starting LightBukkit");
		server = new LBServer();
		Bukkit.setServer(server);
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
	}
	
	@EventHandler
	public void onServerStarting(FMLServerStartingEvent event) {
		mcServer = event.getServer();
		server.onServerStarting();
	}
	
	@EventHandler
	public void onServerStarted(FMLServerStartedEvent event) {
		server.onServerStarted();
	}
	
	public static MinecraftServer getMCServer() {
		return mcServer;
	}
}
