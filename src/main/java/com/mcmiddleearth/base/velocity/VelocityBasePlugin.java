package com.mcmiddleearth.base.velocity;

import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.proxy.ProxyServer;
import org.slf4j.Logger;

@Plugin(
        id = "mcme-base",
        name = "MCME-Base",
        version = "1.0-SNAPSHOT",
        description = "Base functionality for MCME plugins",
        authors = {"Eriol_Eandur"}
)
public class VelocityBasePlugin {

    private final Logger logger;

    private final ProxyServer proxyServer;

    @Inject
    public VelocityBasePlugin(Logger logger, ProxyServer proxyServer) {
        this.logger = logger;
        this.proxyServer = proxyServer;
    }

    @Subscribe
    public void onProxyInitialization(ProxyInitializeEvent event) {
    }
}
