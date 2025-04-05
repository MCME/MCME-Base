package com.mcmiddleearth.base.velocity;

import com.mcmiddleearth.base.core.logger.McmeLogger;
import com.mcmiddleearth.base.core.plugin.McmeProxyPlugin;
import com.mcmiddleearth.base.core.server.McmeProxy;
import com.mcmiddleearth.base.core.taskScheduling.Task;
import com.mcmiddleearth.base.velocity.logger.VelocityMcmeLogger;
import com.mcmiddleearth.base.velocity.server.VelocityMcmeProxy;
import com.mcmiddleearth.base.velocity.taskScheduling.VelocityTask;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.event.proxy.ProxyReloadEvent;
import com.velocitypowered.api.event.proxy.ProxyShutdownEvent;
import com.velocitypowered.api.proxy.ProxyServer;
import org.slf4j.Logger;

import java.io.File;
import java.nio.file.Path;

public abstract class AbstractVelocityPlugin implements McmeProxyPlugin {

    private final McmeLogger mcmeLogger;
    private final ProxyServer proxyServer;
    private final VelocityMcmeProxy mcmeProxy;
    private final File dataFolder;

    public AbstractVelocityPlugin(Logger logger, ProxyServer proxyServer, Path dataDirectory) {
        this.proxyServer = proxyServer;
        mcmeProxy = new VelocityMcmeProxy(proxyServer);
        mcmeLogger = new VelocityMcmeLogger(logger);
        this.dataFolder = dataDirectory.toFile();
    }

    @Subscribe
    public final void onProxyInitialization(ProxyInitializeEvent event) {
        mcmeLogger.info("Enabling plugin on Velocity proxy!");
        enable();
        mcmeLogger.info("Plugin enabled!");
    }

    @Subscribe
    public final void onProxyReload(ProxyReloadEvent event) {
        mcmeLogger.warn("Reloading Velocity proxy might cause problems with this plugin!");
    }

    @Subscribe
    public final void onProxyShutdown(ProxyShutdownEvent event) {
        mcmeLogger.info("Disabling plugin!");
        disable();
        mcmeLogger.info("Plugin disabled!");
    }

    public abstract void enable();
    public abstract void disable();

    @Override
    public final McmeLogger getMcmeLogger() {
       return mcmeLogger;
    }

    public final ProxyServer getProxyServer() {
        return proxyServer;
    }

    @Override
    public final File getDataFolder() {
        return dataFolder;
    }

    @Override
    public final Task getTask(Runnable runnable) {
        return new VelocityTask(this, runnable);
    }

    @Override
    public final McmeProxy getMcmeProxy() {
        return mcmeProxy;
    }
}
