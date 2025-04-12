package com.mcmiddleearth.base.velocity.server;

import com.mcmiddleearth.base.adventure.AdventureMessage;
import com.mcmiddleearth.base.bukkit.command.BukkitMcmeCommandSender;
import com.mcmiddleearth.base.bukkit.player.BukkitMcmePlayer;
import com.mcmiddleearth.base.core.command.McmeCommandSender;
import com.mcmiddleearth.base.core.message.Message;
import com.mcmiddleearth.base.core.player.McmeProxyPlayer;
import com.mcmiddleearth.base.core.scoreboard.ScoreboardManager;
import com.mcmiddleearth.base.core.server.McmeProxy;
import com.mcmiddleearth.base.core.server.McmeServerInfo;
import com.mcmiddleearth.base.velocity.command.VelocityMcmeCommandSender;
import com.mcmiddleearth.base.velocity.command.VelocityMcmeConsole;
import com.mcmiddleearth.base.velocity.player.VelocityMcmePlayer;
import com.mcmiddleearth.base.velocity.scoreboard.VelocityScoreboardManager;
import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.ProxyServer;
import com.velocitypowered.api.proxy.server.RegisteredServer;

import java.util.Collection;
import java.util.Collections;
import java.util.UUID;
import java.util.stream.Collectors;

public class VelocityMcmeProxy implements McmeProxy {

    private static ProxyServer proxyServer;
    private final VelocityScoreboardManager scoreboardManager;

    public VelocityMcmeProxy(ProxyServer proxyServer) {
        VelocityMcmeProxy.proxyServer = proxyServer;
        this.scoreboardManager = new VelocityScoreboardManager();
    }

    @Override
    public McmeServerInfo getServerInfo(String serverName) {
        RegisteredServer server = proxyServer.getServer(serverName).orElse(null);
        return server==null ? null : new VelocityMcmeServerInfo(proxyServer, server.getServerInfo());
    }

    @Override
    public Collection<McmeServerInfo> getAllServerInfo() {
        return proxyServer.getAllServers().stream()
                .map(server -> new VelocityMcmeServerInfo(proxyServer, server.getServerInfo()))
                .collect(Collectors.toList());
    }

    @Override
    public void stop(Message message) {
        proxyServer.shutdown(((AdventureMessage)message).getComponent());
    }

    @Override
    public void broadcast(Message message) {
        proxyServer.sendMessage(((AdventureMessage)message).getComponent());
    }

    @Override
    public Collection<McmeProxyPlayer> getPlayers() {
        return proxyServer.getAllPlayers().stream().map(VelocityMcmePlayer::new).collect(Collectors.toList());
    }

    @Override
    public Collection<McmeProxyPlayer> getPlayers(McmeServerInfo serverInfo) {
        RegisteredServer server =  proxyServer.getServer(serverInfo.getName()).orElse(null);
        if(server!=null) {
            return server.getPlayersConnected().stream().map(VelocityMcmePlayer::new).collect(Collectors.toList());
        } else {
            return Collections.emptyList();
        }
    }

    public static VelocityMcmePlayer getPlayer(Player player) {
        return new VelocityMcmePlayer(player);
    }

    public static McmeCommandSender getMcmeCommandSender(CommandSource commandSender) {
        if(commandSender instanceof Player player) {
            return new VelocityMcmePlayer(player);
        } else if(commandSender.equals(proxyServer.getConsoleCommandSource())) {
            return new VelocityMcmeConsole();
        } else {
            throw new UnsupportedOperationException("MCME-Base only supported players and console as command senders.");
        }
    }

    @Override
    public McmeProxyPlayer getPlayer(UUID uuid) {
        if(uuid == null) return null;
        Player player = proxyServer.getPlayer(uuid).orElse(null);
        if(player!=null) {
            return new VelocityMcmePlayer(player);
        } else {
            return null;
        }
    }

    @Override
    public McmeProxyPlayer getPlayer(String playerName) {
        Player player = proxyServer.getPlayer(playerName).orElse(null);
        if(player!=null) {
            return new VelocityMcmePlayer(player);
        } else {
            return null;
        }
    }

    @Override
    public McmeCommandSender getConsole() {
        return new VelocityMcmeConsole();
    }

    @Override
    public ScoreboardManager getScoreboardManager() {
        return scoreboardManager;
    }

    @Override
    public McmeServerInfo getAnyConnectedServerInfo() {
        return null;
    }


}
