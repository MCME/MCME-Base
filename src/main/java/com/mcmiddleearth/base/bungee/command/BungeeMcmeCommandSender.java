package com.mcmiddleearth.base.bungee.command;

import com.mcmiddleearth.base.core.command.McmeCommandSender;
import com.mcmiddleearth.base.core.command.McmePlugin;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.bungeecord.BungeeComponentSerializer;
import net.md_5.bungee.api.CommandSender;

public class BungeeMcmeCommandSender implements McmeCommandSender {

    private final CommandSender commandSender;
    private final McmePlugin plugin;

    public BungeeMcmeCommandSender(McmePlugin plugin, CommandSender commandSender) {
        this.commandSender = commandSender;
        this.plugin = plugin;
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
        commandSender.sendMessage(BungeeComponentSerializer.get().serialize(message));
    }
}
