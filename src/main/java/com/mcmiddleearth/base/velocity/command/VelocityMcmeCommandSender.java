package com.mcmiddleearth.base.velocity.command;

import com.mcmiddleearth.base.core.command.McmeCommandSender;
import com.mcmiddleearth.base.core.command.McmePlugin;
import com.velocitypowered.api.command.CommandSource;
import net.kyori.adventure.identity.Identity;
import net.kyori.adventure.text.Component;

public class VelocityMcmeCommandSender implements McmeCommandSender {

    private final CommandSource commandSource;
    private final McmePlugin plugin;

    public VelocityMcmeCommandSender(McmePlugin plugin, CommandSource commandSource) {
        this.commandSource = commandSource;
        this.plugin = plugin;
    }

    @Override
    public boolean hasPermission(String permissionNode) {
        return commandSource.hasPermission(permissionNode);
    }

    @Override
    public String getName() {
        return commandSource.pointers().getOrDefault(Identity.NAME,"Console");
    }

    @Override
    public McmePlugin getPlugin() {
        return plugin;
    }

    @Override
    public void sendMessage(Component message) {
        commandSource.sendMessage(message);
    }

    public CommandSource getVelocityCommandSource() {
        return commandSource;
    }
}
