package ru.fewizz.lightbukkit.core;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.bukkit.*;
import org.bukkit.BanList.Type;
import org.bukkit.Warning.WarningState;
import org.bukkit.World;
import org.bukkit.advancement.Advancement;
import org.bukkit.boss.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.world.WorldInitEvent;
import org.bukkit.generator.ChunkGenerator.ChunkData;
import org.bukkit.help.HelpMap;
import org.bukkit.inventory.*;
import org.bukkit.map.MapView;
import org.bukkit.metadata.MetadataStoreBase;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.*;
import org.bukkit.plugin.java.JavaPluginLoader;
import org.bukkit.plugin.messaging.*;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.util.CachedServerIcon;

import net.minecraft.command.CommandHandler;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.*;
import net.minecraftforge.fml.common.FMLCommonHandler;
import ru.fewizz.lightbukkit.core.util.MCBukkitCommand;
import ru.fewizz.lightbukkit.interfaces.*;

public class LBServer implements Server {
	final java.util.logging.Logger JLOGGER = Logger.getLogger("LightBukkitServer");
	final List<LBWorld> worlds = new ArrayList<>(3);
	final Map<UUID, LBPlayer> players = new HashMap<>();
	final SimplePluginManager pluginManager;
	final SimpleCommandMap commandMap;
	final SimpleServicesManager servicesManager = new SimpleServicesManager();
	final LBHelpMap helpMap = new LBHelpMap();
	final StandardMessenger messenger = new StandardMessenger();
	public final MetadataStoreBase<Entity> entityMetaStore;

	LBServer() {
		commandMap = new SimpleCommandMap(this);
		pluginManager = new SimplePluginManager(this, commandMap);
		pluginManager.registerInterface(JavaPluginLoader.class);
		entityMetaStore = new MetadataStoreBase<Entity>() {
			@Override
			protected String disambiguate(Entity subject, String metadataKey) {
				return subject.getUniqueId().toString() + ":" + metadataKey;
			}
		};
		LightBukkit.LOGGER.info("Loading plugins");
		loadPlugins();
	}
	
	void onServerStarting() {
		enablePlugins(PluginLoadOrder.STARTUP);
	}
	
	void onServerStarted() {
		enablePlugins(PluginLoadOrder.POSTWORLD);
		commandMap.getCommands().forEach(com -> {
			((CommandHandler)LightBukkit.getMCServer().commandManager).registerCommand(new MCBukkitCommand(com));
		});
	}
	
	public void onEntityPlayerMPCreated(EntityPlayerMP player) {
		LBPlayer lbPlayer = players.computeIfAbsent(player.getPersistentID(), uuid -> new LBPlayer(player));
		((IPlayer)player).setLBPlayer(lbPlayer);
	}
	
	void onWorldLoad(WorldServer w) {
		LBWorld lbw = new LBWorld(w);
		((ILBWorldProvider)w).setLBWorld(lbw);
		worlds.add(lbw);
		pluginManager.callEvent(new WorldInitEvent(lbw));
	}
	
	void onWorldUnload(WorldServer w) {
		((ILBWorldProvider)w).setLBWorld(null);
		worlds.removeIf(bworld -> bworld.mcWorld == w);
	}

	private void loadPlugins() {
		// From CB
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
				LightBukkit.LOGGER.warn(ex.getMessage() + " initializing " + plugin.getDescription().getFullName() + " (Is it up to date?)", ex);
			}
		}
	}

	void enablePlugins(PluginLoadOrder type) {
		// From CB
		for (Plugin plugin : pluginManager.getPlugins()) {
			if ((!plugin.isEnabled()) && (plugin.getDescription().getLoad() == type)) {
				List<Permission> perms = plugin.getDescription().getPermissions();
				
				for (Permission perm : perms) {
	                try {
	                    pluginManager.addPermission(perm, false);
	                } catch (IllegalArgumentException ex) {
	                	LightBukkit.LOGGER.warn("Plugin " + plugin.getDescription().getFullName() + " tried to register permission '" + perm.getName() + "' but it's already registered", ex);
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
		return LightBukkit.NAME;
	}

	@Override
	public String getVersion() {
		return LightBukkit.VERSION;
	}

	@Override
	public String getBukkitVersion() {
		return LightBukkit.BUKKIT_VERSION;
	}

	@Override
	public Collection<LBPlayer> getOnlinePlayers() {
		return Collections.unmodifiableCollection(players.values());
	}

	@Override
	public int getMaxPlayers() {
		return LightBukkit.getMCServer().getMaxPlayers();
	}

	@Override
	public int getPort() {
		return LightBukkit.getMCServer().getServerPort();
	}

	@Override
	public int getViewDistance() {
		return 0;
	}

	@Override
	public String getIp() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getServerName() {
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
		LightBukkit.mcServer.sendMessage(new TextComponentString(message));
		return 1;
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
		return ((IPlayer)LightBukkit.getMCServer().getPlayerList().getPlayerByUsername(name)).getLBPlayer();
	}

	@Override
	public Player getPlayerExact(String name) {
		return getPlayer(name);
	}

	@Override
	public List<Player> matchPlayer(String name) {
		return players.values().stream().filter(p -> p.getName().equals(name)).collect(Collectors.toList());
	}

	@Override
	public Player getPlayer(UUID id) {
		return players.get(id);
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
		return Collections.unmodifiableList(worlds);
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
		return JLOGGER;
	}

	@Override
	public PluginCommand getPluginCommand(String name) {
		Command c = commandMap.getCommand(name);
		if(c instanceof PluginCommand)
			return (PluginCommand) c;
		return null;
	}

	@Override
	public void savePlayers() {
		LightBukkit.getMCServer().getPlayerList().saveAllPlayerData();
	}

	@Override
	public boolean dispatchCommand(CommandSender sender, String commandLine) throws CommandException {
		return commandMap.dispatch(sender, commandLine);
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
		return LightBukkit.mcServer.getSpawnProtectionSize();
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
		FMLCommonHandler.instance().handleExit(1);
	}

	@Override
	public int broadcast(String message, String permission) {
		AtomicInteger c = new AtomicInteger();
		
		players.forEach((uuid, pl) -> {
			if(pl.hasPermission(permission)) {
				pl.sendMessage(message);
				c.incrementAndGet();
			}
		});
		
		return c.get();
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

	@SuppressWarnings("deprecation")
	@Override
	public GameMode getDefaultGameMode() {
		return GameMode.getByValue(LightBukkit.getMCServer().getGameType().getID());
	}

	@Override
	public void setDefaultGameMode(GameMode mode) {
		LightBukkit.getMCServer().setGameType(GameType.getByID(mode.getValue()));
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
		return LightBukkit.mcServer.getMOTD();
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
		return ((IEntity)LightBukkit.mcServer.getEntityFromUuid(uuid)).getLBEntity();
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
