package com.mcmiddleearth.base.bukkit;

import com.mcmiddleearth.base.adventure.AdventureMessage;
import com.mcmiddleearth.base.bukkit.command.BukkitMcmeCommandSender;
import com.mcmiddleearth.base.bukkit.logger.BukkitMcmeLogger;
import com.mcmiddleearth.base.bukkit.player.BukkitMcmePlayer;
import com.mcmiddleearth.base.bukkit.server.PaperMcmeBackend;
import com.mcmiddleearth.base.bukkit.taskScheduling.BukkitTask;
import com.mcmiddleearth.base.core.command.McmeCommandSender;
import com.mcmiddleearth.base.core.logger.McmeLogger;
import com.mcmiddleearth.base.core.player.McmeBackendPlayer;
import com.mcmiddleearth.base.core.plugin.McmeBackendPlugin;
import com.mcmiddleearth.base.core.server.McmeBackend;
import com.mcmiddleearth.base.core.taskScheduling.Task;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

public abstract class AbstractPaperPlugin extends JavaPlugin implements McmeBackendPlugin {

    private final McmeBackend mcmeBackend = new PaperMcmeBackend(this);
    private McmeLogger mcmeLogger;

    @Override
    public void onEnable() {
        // Plugin startup logic
        this.mcmeLogger = new BukkitMcmeLogger(getLogger());//, PlainTextComponentSerializer.plainText()
                //.serialize(((AdventureMessage)getMessagePrefix()).getComponent()));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @Override
    public Task getTask(Runnable runnable) {
        return new BukkitTask(this, runnable);
    }

    @Override
    public McmeBackend getMcmeBackend() {
        return mcmeBackend;
    }

    @Override
    public McmeLogger getMcmeLogger() {
        return mcmeLogger;
    }
}
