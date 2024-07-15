package com.mcmiddleearth.base.bukkit.command;

import com.mcmiddleearth.base.core.command.McmeCommandSender;
import com.mcmiddleearth.base.core.McmePlugin;
import net.kyori.adventure.text.Component;
import org.bukkit.command.CommandSender;

public class BukkitMcmeCommandSender implements McmeCommandSender {

    private final McmePlugin plugin;
    private final CommandSender commandSender;

    public BukkitMcmeCommandSender(McmePlugin plugin, CommandSender commandSender) {
        this.plugin = plugin;
        this.commandSender = commandSender;
    }

    @Override
    public boolean hasPermission(String permissionNode) {
        return commandSender.hasPermission(permissionNode);
    }

    @Override
    public String getName() {
        return commandSender.getName();
    }

    @Override
    public McmePlugin getPlugin() {
        return plugin;
    }

    @Override
    public void sendMessage(Component message) {
        commandSender.sendMessage(message);
    }

    public CommandSender getBukkitCommandSender() {
        return commandSender;
    }
}
