package com.mcmiddleearth.base.velocity;

import com.mcmiddleearth.base.core.command.McmeCommandSender;
import com.mcmiddleearth.base.core.logger.McmeLogger;
import com.mcmiddleearth.base.core.player.McmeProxyPlayer;
import com.mcmiddleearth.base.core.plugin.McmeProxyPlugin;
import com.mcmiddleearth.base.core.server.McmeProxy;
import com.mcmiddleearth.base.core.server.McmeServerInfo;
import com.mcmiddleearth.base.core.taskScheduling.Task;
import com.mcmiddleearth.base.velocity.command.VelocityMcmeCommandSender;
import com.mcmiddleearth.base.velocity.logger.VelocityMcmeLogger;
import com.mcmiddleearth.base.velocity.server.VelocityMcmeProxy;
import com.mcmiddleearth.base.velocity.taskScheduling.VelocityTask;
import com.velocitypowered.api.proxy.ProxyServer;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.slf4j.Logger;

import java.io.File;
import java.nio.file.Path;
import java.util.Collection;
import java.util.UUID;

public abstract class AbstractVelocityPlugin implements McmeProxyPlugin {

    private final McmeLogger mcmeLogger;
    private final ProxyServer proxyServer;
    private final VelocityMcmeProxy mcmeProxy;
    private final File dataFolder;

    public AbstractVelocityPlugin(Logger logger, ProxyServer proxyServer, Path dataDirectory) {
        this.proxyServer = proxyServer;
        mcmeProxy = new VelocityMcmeProxy(proxyServer);
        mcmeLogger = new VelocityMcmeLogger(logger, PlainTextComponentSerializer.plainText().serialize(getMessagePrefix()));
        this.dataFolder = dataDirectory.toFile();
    }

    @Override
    public McmeLogger getMcmeLogger() {
       return mcmeLogger;
    }

    public ProxyServer getProxyServer() {
        return proxyServer;
    }

    @Override
    public File getDataFolder() {
        return dataFolder;
    }

    @Override
    public Collection<McmeProxyPlayer> getPlayers() {
        return null;
    }

    @Override
    public Collection<McmeProxyPlayer> getPlayers(McmeServerInfo serverInfo) {
        return null;
    }

    @Override
    public McmeProxyPlayer getPlayer(UUID uuid) {
        //todo: return null if not exist
        return null;
    }

    @Override
    public McmeProxyPlayer getPlayer(String playerName) {

        //todo: return null if not exist
        return null;
    }

    @Override
    public McmeCommandSender getConsole() {
        return new VelocityMcmeCommandSender(this, proxyServer.getConsoleCommandSource());
    }

    @Override
    public Task getTask(Runnable runnable) {
        return new VelocityTask(this, runnable);
    }

    @Override
    public McmeProxy getMcmeProxy() {
        return mcmeProxy;
    }
}
