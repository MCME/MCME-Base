package com.mcmiddleearth.base.bungee.server;

import com.mcmiddleearth.base.bungee.AbstractBungeePlugin;
import com.mcmiddleearth.base.bungee.command.BungeeMcmeCommandSender;
import com.mcmiddleearth.base.bungee.player.BungeeMcmePlayer;
import com.mcmiddleearth.base.core.command.McmeCommandSender;
import com.mcmiddleearth.base.core.player.McmeProxyPlayer;
import com.mcmiddleearth.base.core.server.McmeProxy;
import com.mcmiddleearth.base.core.server.McmeServerInfo;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.Collection;
import java.util.UUID;
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

    @Override
    public boolean sendPluginMessage(McmeServerInfo server, String channel, byte[] data, boolean queue) {
        return ((BungeeMcmeServerInfo)server).toBungeeServerInfo().sendData(channel, data, queue);
    }

    @Override
    public Collection<McmeProxyPlayer> getPlayers() {
        return ProxyServer.getInstance().getPlayers().stream()
                .map(BungeeMcmePlayer::new).collect(Collectors.toList());
    }

    @Override
    public Collection<McmeProxyPlayer> getPlayers(McmeServerInfo serverInfo) {
        return ((BungeeMcmeServerInfo)serverInfo).toBungeeServerInfo().getPlayers().stream()
                .map(BungeeMcmePlayer::new).collect(Collectors.toList());
    }

    @Override
    public McmeProxyPlayer getPlayer(UUID uuid) {
        ProxiedPlayer proxiedPlayer = ProxyServer.getInstance().getPlayer(uuid);
        return proxiedPlayer!=null ? new BungeeMcmePlayer(proxiedPlayer) : null;
    }

    @Override
    public McmeProxyPlayer getPlayer(String playerName) {
        ProxiedPlayer proxiedPlayer = ProxyServer.getInstance().getPlayer(playerName);
        return proxiedPlayer!=null ? new BungeeMcmePlayer(proxiedPlayer) : null;
    }

    public McmeProxyPlayer getPlayer(ProxiedPlayer player) {
        return new BungeeMcmePlayer(player);
    }

    @Override
    public McmeCommandSender getConsole() {
        return new BungeeMcmeCommandSender(ProxyServer.getInstance().getConsole());
    }


}
