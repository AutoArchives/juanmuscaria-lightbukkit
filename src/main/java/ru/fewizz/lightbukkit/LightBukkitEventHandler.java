package ru.fewizz.lightbukkit;

import org.bukkit.event.block.Action;

import net.minecraft.world.WorldServer;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import ru.fewizz.lightbukkit.impl.*;
import ru.fewizz.lightbukkit.interfaces.*;

public class LightBukkitEventHandler {
	
	@SubscribeEvent
	void onPlayerInteract(PlayerInteractEvent.LeftClickBlock event) {
		onBLockClickEvent(Action.LEFT_CLICK_BLOCK, event);
	}
	
	@SubscribeEvent
	void onPlayerInteract(PlayerInteractEvent.RightClickBlock event) {
		onBLockClickEvent(Action.RIGHT_CLICK_BLOCK, event);
	}
	
	void onBLockClickEvent(Action a, PlayerInteractEvent event) {
		if(event.getSide().isClient())
			return;
		
		LBWorld world = ((ILBWorldProvider)event.getWorld()).getLBWorld();
		LBPlayer player = ((IPlayer)event.getEntityPlayer()).getLBPlayer();
		
		org.bukkit.event.player.PlayerInteractEvent bevent =
				new org.bukkit.event.player.PlayerInteractEvent(
						player,
						Action.LEFT_CLICK_BLOCK,
						new LBItemStack(event.getItemStack()),
						world.getBlockAt(event.getPos()),
						LBUtils.convertToBLockFace(event.getFace()));
		
		LightBukkit.server.getPluginManager().callEvent(bevent);
		
		if(bevent.isCancelled())
			event.setCanceled(true);
	}
	
	@SubscribeEvent
	void onWorldLoad(WorldEvent.Load event) {
		if(event.getWorld() instanceof WorldServer) {
			LightBukkit.server.onWorldLoad((WorldServer) event.getWorld());
		}
	}
	
	@SubscribeEvent
	void onWorldUnload(WorldEvent.Unload event) {
		if(event.getWorld() instanceof WorldServer) {
			LightBukkit.server.onWorldUnload((WorldServer) event.getWorld());
		}
	}
}
