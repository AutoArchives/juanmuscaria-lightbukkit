package ru.fewizz.lightbukkit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bukkit.plugin.PluginLoadOrder;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import ru.fewizz.lightbukkit.impl.LBServer;

@Mod(modid = LightBukkit.MODID, version = "0")
public class LightBukkit {
	public static final String MODID = "lightbukkit";
	public static final Logger LOGGER = LogManager.getLogger(MODID);
	
	@Instance
	static LightBukkit instance;
	static LBServer server;

	@EventHandler
	public void preinit(FMLPreInitializationEvent event) {
		server = new LBServer();//((IMinecraftServer)FMLCommonHandler.instance().getMinecraftServerInstance()).getLBServer();
		MinecraftForge.EVENT_BUS.register(instance);
		LOGGER.info("Loading plugins");
		server.loadPlugins();
		server.enablePlugins(PluginLoadOrder.STARTUP);
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
	}
	
	@EventHandler
	public void serverStart(FMLServerStartingEvent event) {
		server.enablePlugins(PluginLoadOrder.POSTWORLD);
	}
}
