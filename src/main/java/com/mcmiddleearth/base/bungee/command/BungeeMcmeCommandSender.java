package com.mcmiddleearth.base.bungee.command;

import com.mcmiddleearth.base.core.command.McmeCommandSender;
import com.mcmiddleearth.base.core.plugin.McmePlugin;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.bungeecord.BungeeComponentSerializer;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.BaseComponent;

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
        BaseComponent[] baseComponents = BungeeComponentSerializer.get().serialize(message);
        //Logger.getGlobal().info("Sender: "+commandSender.toString());
        //Arrays.stream(baseComponents).forEach(component -> Logger.getGlobal().info(component.toLegacyText()));
        commandSender.sendMessage(baseComponents);
    }
}
