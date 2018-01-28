package ru.fewizz.lightbukkit.impl;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.BanList;
import org.bukkit.BanList.Type;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.NamespacedKey;
import org.bukkit.OfflinePlayer;
import org.bukkit.Server;
import org.bukkit.UnsafeValues;
import org.bukkit.Warning.WarningState;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.advancement.Advancement;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.command.Command;
import org.bukkit.command.CommandException;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.generator.ChunkGenerator.ChunkData;
import org.bukkit.help.HelpMap;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemFactory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Merchant;
import org.bukkit.inventory.Recipe;
import org.bukkit.map.MapView;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginLoadOrder;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.ServicesManager;
import org.bukkit.plugin.SimplePluginManager;
import org.bukkit.plugin.SimpleServicesManager;
import org.bukkit.plugin.java.JavaPluginLoader;
import org.bukkit.plugin.messaging.Messenger;
import org.bukkit.plugin.messaging.StandardMessenger;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.util.CachedServerIcon;

import net.minecraft.command.CommandHandler;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import ru.fewizz.lightbukkit.LightBukkit;
import ru.fewizz.lightbukkit.util.MCBukkitCommand;

public class LBServer implements Server {
	final java.util.logging.Logger logger = Logger.getLogger("LBServer");
	final List<LBWorld> worlds = new ArrayList<>(3);
	final SimplePluginManager pluginManager;
	final SimpleCommandMap commandMap;
	final SimpleServicesManager servicesManager = new SimpleServicesManager();
	final LBHelpMap helpMap = new LBHelpMap();
	final StandardMessenger messenger = new StandardMessenger();

	public LBServer() {
		commandMap = new SimpleCommandMap(this) {
			@Override
			public boolean register(String label, String fallbackPrefix, Command command) {
				boolean registered = super.register(label, fallbackPrefix, command);
				if(registered)((CommandHandler)LightBukkit.getMCServer().commandManager).registerCommand(new MCBukkitCommand(command));
				return registered;
			}
		};
		pluginManager = new SimplePluginManager(this, commandMap);
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	public void onServerStarting() {
		LightBukkit.LOGGER.info("Loading plugins");
		loadPlugins();
		enablePlugins(PluginLoadOrder.STARTUP);
	}
	
	public void onServerStarted() {
		enablePlugins(PluginLoadOrder.POSTWORLD);
	}
	
	@SubscribeEvent
	void onWorldLoad(WorldEvent.Load event) {
		if(event.getWorld() instanceof WorldServer) {
			worlds.add(new LBWorld((WorldServer) event.getWorld()));
		}
	}
	
	@SubscribeEvent
	void onWorldUnload(WorldEvent.Unload event) {
		worlds.removeIf(bworld -> bworld.world == event.getWorld());
	}

	private void loadPlugins() {
		// From CB
		pluginManager.registerInterface(JavaPluginLoader.class);

		File pluginFolder = new File("plugins");
		if(!pluginFolder.exists()) {
			pluginFolder.mkdir();
			return;
		}


		for (Plugin plugin : pluginManager.loadPlugins(pluginFolder)) {
			try {
				String message = String.format("Loading %s", plugin.getDescription().getFullName());
				plugin.getLogger().info(message);
				plugin.onLoad();
			} catch (Throwable ex) {
				Logger.getLogger(LBServer.class.getName()).log(Level.SEVERE, ex.getMessage() + " initializing " + plugin.getDescription().getFullName() + " (Is it up to date?)", ex);
			}
		}
	}

	private void enablePlugins(PluginLoadOrder type) {
		// From CB
		for (Plugin plugin : pluginManager.getPlugins()) {
			if ((!plugin.isEnabled()) && (plugin.getDescription().getLoad() == type)) {
				List<Permission> perms = plugin.getDescription().getPermissions();
				
				for (Permission perm : perms) {
	                try {
	                    pluginManager.addPermission(perm, false);
	                } catch (IllegalArgumentException ex) {
	                    getLogger().log(Level.WARNING, "Plugin " + plugin.getDescription().getFullName() + " tried to register permission '" + perm.getName() + "' but it's already registered", ex);
	                }
	            }
	            pluginManager.dirtyPermissibles();
	            
				pluginManager.enablePlugin(plugin);
			}
		}
	}

	@Override
	public void sendPluginMessage(Plugin source, String channel, byte[] message) {

	}

	@Override
	public Set<String> getListeningPluginChannels() {
		return null;
	}

	@Override
	public String getName() {
		return "LightBukkit";
	}

	@Override
	public String getVersion() {
		return "0";
	}

	@Override
	public String getBukkitVersion() {
		return LightBukkit.BUKKIT_VERSION;
	}

	@Override
	public Collection<? extends Player> getOnlinePlayers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getMaxPlayers() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getPort() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getViewDistance() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getIp() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getServerName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getServerId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getWorldType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean getGenerateStructures() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getAllowEnd() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getAllowNether() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasWhitelist() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setWhitelist(boolean value) {
		// TODO Auto-generated method stub

	}

	@Override
	public Set<OfflinePlayer> getWhitelistedPlayers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void reloadWhitelist() {
		// TODO Auto-generated method stub

	}

	@Override
	public int broadcastMessage(String message) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getUpdateFolder() {
		return "";
	}

	@Override
	public File getUpdateFolderFile() {
		return new File(getUpdateFolder());
	}

	@Override
	public long getConnectionThrottle() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getTicksPerAnimalSpawns() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getTicksPerMonsterSpawns() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Player getPlayer(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Player getPlayerExact(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Player> matchPlayer(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Player getPlayer(UUID id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PluginManager getPluginManager() {
		return pluginManager;
	}

	@Override
	public BukkitScheduler getScheduler() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServicesManager getServicesManager() {
		return servicesManager;
	}

	@Override
	public List<World> getWorlds() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public World createWorld(WorldCreator creator) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean unloadWorld(String name, boolean save) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean unloadWorld(World world, boolean save) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public World getWorld(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public World getWorld(UUID uid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MapView getMap(short id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MapView createMap(World world) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void reload() {
		// TODO Auto-generated method stub

	}

	@Override
	public void reloadData() {
		// TODO Auto-generated method stub

	}

	@Override
	public Logger getLogger() {
		return logger;
	}

	@Override
	public PluginCommand getPluginCommand(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void savePlayers() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean dispatchCommand(CommandSender sender, String commandLine) throws CommandException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addRecipe(Recipe recipe) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Recipe> getRecipesFor(ItemStack result) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<Recipe> recipeIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void clearRecipes() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resetRecipes() {
		// TODO Auto-generated method stub

	}

	@Override
	public Map<String, String[]> getCommandAliases() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getSpawnRadius() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setSpawnRadius(int value) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean getOnlineMode() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getAllowFlight() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isHardcore() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void shutdown() {
		// TODO Auto-generated method stub
	}

	@Override
	public int broadcast(String message, String permission) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public OfflinePlayer getOfflinePlayer(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OfflinePlayer getOfflinePlayer(UUID id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<String> getIPBans() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void banIP(String address) {
		// TODO Auto-generated method stub

	}

	@Override
	public void unbanIP(String address) {
		// TODO Auto-generated method stub

	}

	@Override
	public Set<OfflinePlayer> getBannedPlayers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BanList getBanList(Type type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<OfflinePlayer> getOperators() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GameMode getDefaultGameMode() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setDefaultGameMode(GameMode mode) {
		// TODO Auto-generated method stub

	}

	@Override
	public ConsoleCommandSender getConsoleSender() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public File getWorldContainer() {
		return FMLCommonHandler.instance().getSavesDirectory();
	}

	@Override
	public OfflinePlayer[] getOfflinePlayers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Messenger getMessenger() {
		return messenger;
	}

	@Override
	public HelpMap getHelpMap() {
		return helpMap;
	}

	@Override
	public Inventory createInventory(InventoryHolder owner, InventoryType type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Inventory createInventory(InventoryHolder owner, InventoryType type, String title) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Inventory createInventory(InventoryHolder owner, int size) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Inventory createInventory(InventoryHolder owner, int size, String title) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Merchant createMerchant(String title) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getMonsterSpawnLimit() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getAnimalSpawnLimit() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getWaterAnimalSpawnLimit() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getAmbientSpawnLimit() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isPrimaryThread() {
		return FMLCommonHandler.instance().getEffectiveSide().isServer();
	}

	@Override
	public String getMotd() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getShutdownMessage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WarningState getWarningState() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ItemFactory getItemFactory() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ScoreboardManager getScoreboardManager() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CachedServerIcon getServerIcon() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CachedServerIcon loadServerIcon(File file) throws IllegalArgumentException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CachedServerIcon loadServerIcon(BufferedImage image) throws IllegalArgumentException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setIdleTimeout(int threshold) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getIdleTimeout() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ChunkData createChunkData(World world) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BossBar createBossBar(String title, BarColor color, BarStyle style, BarFlag... flags) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Entity getEntity(UUID uuid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Advancement getAdvancement(NamespacedKey key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<Advancement> advancementIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UnsafeValues getUnsafe() {
		// TODO Auto-generated method stub
		return null;
	}

}
