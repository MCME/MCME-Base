package com.mcmiddleearth.base.bungee.server;

import com.mcmiddleearth.base.bungee.AbstractBungeePlugin;
import com.mcmiddleearth.base.core.server.McmeProxy;
import com.mcmiddleearth.base.core.server.McmeServerInfo;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;

import java.util.Collection;
import java.util.stream.Collectors;

public class BungeeMcmeProxy implements McmeProxy {

    AbstractBungeePlugin plugin;

    public BungeeMcmeProxy(AbstractBungeePlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public McmeServerInfo getServerInfo(String serverName) {
        ServerInfo serverInfo = ProxyServer.getInstance().getServerInfo(serverName);
        return serverInfo==null ? null : new BungeeMcmeServerInfo(serverInfo);
    }

    @Override
    public Collection<McmeServerInfo> getAllServerInfo() {
        return ProxyServer.getInstance().getServersCopy().values()
                .stream().map(BungeeMcmeServerInfo::new).collect(Collectors.toList());
    }

    @Override
    public void stop(Component message) {
        ProxyServer.getInstance().stop(PlainTextComponentSerializer.plainText().serialize(message));
    }

    @Override
    public void broadcast(Component message) {
        plugin.getAdventure().players().sendMessage(message);
    }
}
