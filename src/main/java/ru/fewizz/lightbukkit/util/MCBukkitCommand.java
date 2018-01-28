package ru.fewizz.lightbukkit.util;

import org.bukkit.command.*;

import net.minecraft.command.*;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import ru.fewizz.lightbukkit.interfaces.IPlayer;

public class MCBukkitCommand extends CommandBase {
	Command bCommand;
	
	public MCBukkitCommand(Command com) {
		this.bCommand = com;
	}
	
	@Override
	public String getName() {
		return bCommand.getName();
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return bCommand.getUsage();
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) {
		CommandSender cs = null;
		if(sender instanceof EntityPlayerMP)
			cs = ((IPlayer)sender).getLBPlayer();
		
		bCommand.execute(cs, getName(), args);
	}

}
