package com.mcmiddleearth.base.bukkit.command;

import com.mcmiddleearth.base.adventure.AdventureMessage;
import com.mcmiddleearth.base.core.command.McmeCommandSender;
import com.mcmiddleearth.base.core.message.Message;
import net.kyori.adventure.text.Component;
import org.bukkit.command.CommandSender;

public class BukkitMcmeCommandSender implements McmeCommandSender {

    private final CommandSender commandSender;

    public BukkitMcmeCommandSender(CommandSender commandSender) {
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
    public void sendMessage(Message message) {
        commandSender.sendMessage(((AdventureMessage)message).getComponent());
    }

    public CommandSender getBukkitCommandSender() {
        return commandSender;
    }
}
