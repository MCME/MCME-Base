package com.mcmiddleearth.base.bukkit.command;

import com.mcmiddleearth.base.adventure.AdventureMessage;
import com.mcmiddleearth.base.core.command.McmeCommandSender;
import com.mcmiddleearth.base.core.message.Message;
import org.bukkit.command.CommandSender;

public abstract class BukkitMcmeCommandSender implements McmeCommandSender {

    @Override
    public boolean hasPermission(String permissionNode) {
        return getBukkitCommandSender().hasPermission(permissionNode);
    }

    @Override
    public String getName() {
        return getBukkitCommandSender().getName();
    }

    @Override
    public void sendMessage(Message message) {
        getBukkitCommandSender().sendMessage(((AdventureMessage)message).getComponent());
    }

    public abstract CommandSender getBukkitCommandSender();
}
