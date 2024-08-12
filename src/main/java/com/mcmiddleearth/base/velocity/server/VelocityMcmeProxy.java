package com.mcmiddleearth.base.velocity.server;

import com.mcmiddleearth.base.core.command.McmeCommandSender;
import com.mcmiddleearth.base.core.player.McmeProxyPlayer;
import com.mcmiddleearth.base.core.server.McmeProxy;
import com.mcmiddleearth.base.core.server.McmeServerInfo;
import com.mcmiddleearth.base.velocity.command.VelocityMcmeCommandSender;
import com.velocitypowered.api.proxy.ProxyServer;
import com.velocitypowered.api.proxy.messages.MinecraftChannelIdentifier;
import com.velocitypowered.api.proxy.server.RegisteredServer;
import net.kyori.adventure.text.Component;

import java.util.Collection;
import java.util.UUID;

public class VelocityMcmeProxy implements McmeProxy {

    private final ProxyServer proxyServer;

    public VelocityMcmeProxy(ProxyServer proxyServer) {
        this.proxyServer = proxyServer;
    }

    @Override
    public McmeServerInfo getServerInfo(String serverName) {
        RegisteredServer server = proxyServer.getServer(serverName).orElse(null);
        return server==null ? null : new VelocityMcmeServerInfo(proxyServer, server.getServerInfo());
    }

    @Override
    public Collection<McmeServerInfo> getAllServerInfo() {
        return null;
    }

    @Override
    public void stop(Component message) {
        proxyServer.shutdown(message);
    }

    @Override
    public void broadcast(Component message) {
        proxyServer.sendMessage(message);
    }

    @Override
    public boolean sendPluginMessage(McmeServerInfo server, String channel, byte[] data, boolean queue) {
        RegisteredServer registeredServer =  proxyServer.getServer(server.getName()).orElse(null);
        return registeredServer!=null && registeredServer.sendPluginMessage(MinecraftChannelIdentifier.from(channel), data);
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
        return new VelocityMcmeCommandSender(proxyServer.getConsoleCommandSource());
    }


}
