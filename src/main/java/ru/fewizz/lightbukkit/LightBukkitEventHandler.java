package ru.fewizz.lightbukkit;

import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class LightBukkitEventHandler {
	
	@SubscribeEvent
	void onPlayerInteract(PlayerInteractEvent event) {
		//org.bukkit.event.player.PlayerInteractEvent bevent = new org.bukkit.event.player.PlayerInteractEvent(who, action, item, clickedBlock, clickedFace)
	}
}
