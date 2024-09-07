package com.mcmiddleearth.base.bukkit.command.handler;

import com.google.common.base.Joiner;
import com.mcmiddleearth.base.bukkit.AbstractPaperPlugin;
import com.mcmiddleearth.base.bukkit.command.BukkitMcmeCommandSender;
import com.mcmiddleearth.base.bukkit.player.BukkitMcmePlayer;
import com.mcmiddleearth.base.core.command.McmeCommandSender;
import com.mcmiddleearth.base.core.command.SimpleTabCompleteRequest;
import com.mcmiddleearth.base.core.command.TabCompleteRequest;
import com.mcmiddleearth.base.core.command.handler.AbstractCommandHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;

/**
 * Command handler for Bukkit plugins:
 * 1. Create a subclass which creates a command tree
 * 2. Set as CommandExecutor and TabCompleter for a command of your plugin
 */
public abstract class BukkitCommandHandler extends AbstractCommandHandler implements TabExecutor {

    public BukkitCommandHandler(String command, AbstractPaperPlugin plugin) {
        super(command, plugin);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        McmeCommandSender wrappedSender;
        if(commandSender instanceof Player) {
            wrappedSender = new BukkitMcmePlayer((Player)commandSender);
        } else if(commandSender instanceof ConsoleCommandSender) {
            wrappedSender = new BukkitMcmeCommandSender(commandSender);
        } else {
            return false;
        }
        execute(wrappedSender, args);
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        McmeCommandSender wrappedSender;
        if(commandSender instanceof Player) {
            wrappedSender = new BukkitMcmePlayer((Player)commandSender);
        } else if(commandSender instanceof ConsoleCommandSender) {
            wrappedSender = new BukkitMcmeCommandSender(commandSender);
        } else {
            return Collections.emptyList();
        }
        TabCompleteRequest request = new SimpleTabCompleteRequest(wrappedSender,
                String.format("/%s %s", alias, Joiner.on(' ').join(args)));
        onTabComplete(request);
        return request.getSuggestions();
    }
}
