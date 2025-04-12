package com.mcmiddleearth.base.bukkit.command;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

public class BukkitMcmeConsole extends BukkitMcmeCommandSender {

    @Override
    public CommandSender getBukkitCommandSender() {
        return Bukkit.getConsoleSender();
    }
}
