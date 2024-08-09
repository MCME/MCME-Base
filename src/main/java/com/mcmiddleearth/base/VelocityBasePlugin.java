package com.mcmiddleearth.base;

import com.google.inject.Inject;
import com.mcmiddleearth.base.velocity.AbstractVelocityPlugin;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.plugin.annotation.DataDirectory;
import com.velocitypowered.api.proxy.ProxyServer;
import net.kyori.adventure.text.Component;
import org.slf4j.Logger;

import java.io.File;
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

    private final Path dataDirectory;

    @Inject
    public VelocityBasePlugin(ProxyServer proxyServer, Logger logger, @DataDirectory Path dataDirectory) {
        super(logger, proxyServer);
        this.dataDirectory = dataDirectory;
        instance = this;
    }

    @Subscribe
    public void onProxyInitialization(ProxyInitializeEvent event) {
        getConsole().sendInfo(Component.text("Initialized on Velocity proxy!"));
    }

    @Override
    public Component getMessagePrefix() {
        return Component.text("[MCME-Base]");
    }

    @Override
    public File getDataFolder() {
        return dataDirectory.toFile();
    }

    public static Object getInstance() {
        return instance;
    }
}
