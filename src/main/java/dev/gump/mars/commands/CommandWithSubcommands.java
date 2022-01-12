package dev.gump.mars.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public abstract class CommandWithSubcommands implements CommandExecutor{
	private final Map<String, CommandExecutor> subCommandsMap = new HashMap<>();

	@Override
	public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {

		// Tab Completion
		String[] subCommandNamesArray = new String[subCommandsMap.size()];
		subCommandsMap.keySet().toArray(subCommandNamesArray);

		command.tabComplete(sender, label, subCommandNamesArray);

		// Default Command
		if (args.length == 0) return this.onDefaultCommand(sender, command, args);

		// Sub Commands
		final String subCommandName = args[0].toLowerCase();
		final CommandExecutor subCommand = subCommandsMap.get(subCommandName);

		if (subCommand == null) return false;

		final String[] newArgs = Arrays.copyOfRange(args, 1, args.length);
		return subCommand.onCommand(sender, command, label, newArgs);
	}

	public void addSubCommand(final String name, final CommandExecutor executor) {
		subCommandsMap.put(name, executor);
	}

	public void removeSubCommand(final String name) {
		subCommandsMap.remove(name);
	}

	public abstract boolean onDefaultCommand(final CommandSender sender, final Command command, final String[] args);
}
