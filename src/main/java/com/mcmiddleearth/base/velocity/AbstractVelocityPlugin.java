package com.mcmiddleearth.base.velocity;

import com.mcmiddleearth.base.core.logger.McmeLogger;
import com.mcmiddleearth.base.core.plugin.McmeProxyPlugin;
import com.mcmiddleearth.base.core.server.McmeProxy;
import com.mcmiddleearth.base.core.taskScheduling.Task;
import com.mcmiddleearth.base.velocity.logger.VelocityMcmeLogger;
import com.mcmiddleearth.base.velocity.server.VelocityMcmeProxy;
import com.mcmiddleearth.base.velocity.taskScheduling.VelocityTask;
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
    public Task getTask(Runnable runnable) {
        return new VelocityTask(this, runnable);
    }

    @Override
    public McmeProxy getMcmeProxy() {
        return mcmeProxy;
    }
}
