package ru.fewizz.lightbukkit.util;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.CommandExecuteAt;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import org.bukkit.command.Command;

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
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		bCommand.execute(null, getName(), args);
	}

}
