package com.mcmiddleearth.base;

import com.google.inject.Inject;
import com.mcmiddleearth.base.core.message.Message;
import com.mcmiddleearth.base.velocity.AbstractVelocityPlugin;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.plugin.annotation.DataDirectory;
import com.velocitypowered.api.proxy.ProxyServer;
import net.kyori.adventure.text.Component;
import org.slf4j.Logger;

import java.nio.file.Path;

@Plugin(
        id = "mcme-base",
        name = "MCME-Base",
        version = "1.0-SNAPSHOT",
        description = "Base functionality for MCME plugins",
        authors = {"Eriol_Eandur"}
)
public class VelocityBasePlugin extends AbstractVelocityPlugin {

    private static Object instance;

    @Inject
    public VelocityBasePlugin(ProxyServer proxyServer, Logger logger, @DataDirectory Path dataDirectory) {
        super(logger, proxyServer, dataDirectory);
        instance = this;
    }

    @Subscribe
    public void onProxyInitialization(ProxyInitializeEvent event) {
        getMcmeProxy().getConsole().sendMessage(createInfoMessage().add("Initialized on Velocity proxy!"));
    }

    @Override
    public Message getMessagePrefix() {
        return createMessage().add("[MCME-Base] ");
    }

    public static VelocityBasePlugin getInstance() {
        return (VelocityBasePlugin) instance;
    }
}
