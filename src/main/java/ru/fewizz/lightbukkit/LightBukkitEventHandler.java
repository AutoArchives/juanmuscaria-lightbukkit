package ru.fewizz.lightbukkit;

import net.minecraft.world.WorldServer;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import ru.fewizz.lightbukkit.impl.LBPlayer;
import ru.fewizz.lightbukkit.interfaces.IPlayer;

public class LightBukkitEventHandler {
	
	@SubscribeEvent
	void onPlayerInteract(PlayerInteractEvent.LeftClickBlock event) {
		LBPlayer player = ((IPlayer)event.getEntityPlayer()).getLBPlayer();
		
		//org.bukkit.event.player.PlayerInteractEvent bevent = new org.bukkit.event.player.PlayerInteractEvent(player, Action.LEFT_CLICK_BLOCK, new , clickedBlock, clickedFace)
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
