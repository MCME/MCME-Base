package com.mcmiddleearth.base.velocity;

import com.mcmiddleearth.base.core.command.McmeCommandSender;
import com.mcmiddleearth.base.core.player.McmeProxyPlayer;
import com.mcmiddleearth.base.core.plugin.McmeProxyPlugin;
import com.mcmiddleearth.base.core.server.McmeProxy;
import com.mcmiddleearth.base.core.taskScheduling.Task;
import com.mcmiddleearth.base.velocity.command.VelocityMcmeCommandSender;
import com.mcmiddleearth.base.velocity.server.VelocityMcmeProxy;
import com.mcmiddleearth.base.velocity.taskScheduling.VelocityTask;
import com.velocitypowered.api.proxy.ProxyServer;
import org.slf4j.Logger;

import java.util.Collection;
import java.util.UUID;

public abstract class AbstractVelocityPlugin implements McmeProxyPlugin {

    private final Logger logger;
    private final ProxyServer proxyServer;
    private final VelocityMcmeProxy mcmeProxy;

    public AbstractVelocityPlugin(Logger logger, ProxyServer proxyServer) {
        this.logger = logger;
        this.proxyServer = proxyServer;
        mcmeProxy = new VelocityMcmeProxy(proxyServer);
    }

    public Logger getLogger() {
        return logger;
    }

    public ProxyServer getProxyServer() {
        return proxyServer;
    }

    @Override
    public Collection<McmeProxyPlayer> getPlayers() {
        return null;
    }

    @Override
    public McmeProxyPlayer getPlayer(UUID uuid) {
        return null;
    }

    @Override
    public McmeProxyPlayer getPlayer(String playerName) {
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
