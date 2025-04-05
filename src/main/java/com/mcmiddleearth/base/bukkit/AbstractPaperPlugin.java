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
import com.mcmiddleearth.base.core.server.McmeServer;
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
    public final void onEnable() {
        // Plugin startup logic
        this.mcmeLogger = new BukkitMcmeLogger(getLogger());
        mcmeLogger.info("Enabling plugin on Paper server!");
        enable();
        mcmeLogger.info("Plugin enabled!");
    }

    @Override
    public final void onDisable() {
        mcmeLogger.info("Disabling plugin on Paper proxy!");
        disable();
        mcmeLogger.info("Plugin enabled!");
    }

    public abstract void enable();
    public abstract void disable();

    @Override
    public final Task getTask(Runnable runnable) {
        return new BukkitTask(this, runnable);
    }

    @Override
    public final McmeBackend getMcmeBackend() {
        return mcmeBackend;
    }

    @Override
    public final McmeLogger getMcmeLogger() {
        return mcmeLogger;
    }

    @Override
    public McmeServer getMcmeServer() {
        return mcmeBackend;
    }
}
