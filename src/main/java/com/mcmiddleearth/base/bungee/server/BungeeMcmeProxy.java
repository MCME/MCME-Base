package com.mcmiddleearth.base.bungee.server;

import com.mcmiddleearth.base.adventure.AdventureMessage;
import com.mcmiddleearth.base.bungee.AbstractBungeePlugin;
import com.mcmiddleearth.base.bungee.command.BungeeMcmeCommandSender;
import com.mcmiddleearth.base.bungee.command.BungeeMcmeConsole;
import com.mcmiddleearth.base.bungee.player.BungeeMcmePlayer;
import com.mcmiddleearth.base.bungee.scoreboard.BungeeScoreboardManager;
import com.mcmiddleearth.base.core.command.McmeCommandSender;
import com.mcmiddleearth.base.core.message.Message;
import com.mcmiddleearth.base.core.player.McmePlayer;
import com.mcmiddleearth.base.core.player.McmeProxyPlayer;
import com.mcmiddleearth.base.core.scoreboard.ScoreboardManager;
import com.mcmiddleearth.base.core.server.McmeProxy;
import com.mcmiddleearth.base.core.server.McmeServerInfo;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

public class BungeeMcmeProxy implements McmeProxy {

    AbstractBungeePlugin plugin;
    BungeeScoreboardManager scoreboardManager;

    public BungeeMcmeProxy(AbstractBungeePlugin plugin) {
        this.plugin = plugin;
        this.scoreboardManager = new BungeeScoreboardManager();
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
    public void stop(Message message) {
        ProxyServer.getInstance().stop(PlainTextComponentSerializer.plainText()
                .serialize(((AdventureMessage)message).getComponent()));
    }

    @Override
    public void broadcast(Message message) {
        //plugin.getAdventure().players().sendMessage(((AdventureMessage)message).getComponent());
        getPlayers().forEach(player -> player.sendMessage(message));
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
        /*ProxiedPlayer proxiedPlayer = ProxyServer.getInstance().getPlayer(uuid);
        return proxiedPlayer!=null ? new BungeeMcmePlayer(proxiedPlayer) : null;*/
        return new BungeeMcmePlayer(uuid);
    }

    @Override
    public McmeProxyPlayer getPlayer(String playerName) {
        ProxiedPlayer proxiedPlayer = ProxyServer.getInstance().getPlayer(playerName);
        return proxiedPlayer!=null ? new BungeeMcmePlayer(proxiedPlayer) : null;
    }

    public BungeeMcmePlayer getPlayer(ProxiedPlayer player) {
        return new BungeeMcmePlayer(player);
    }

    @Override
    public McmeCommandSender getConsole() {
        return new BungeeMcmeConsole();
    }

    public McmeCommandSender getMcmeCommandSender(CommandSender commandSender) {
        if(commandSender instanceof ProxiedPlayer) {
            return new BungeeMcmePlayer((ProxiedPlayer)commandSender);
        } else if(commandSender.equals(ProxyServer.getInstance().getConsole())) {
            return new BungeeMcmeConsole();
        } else {
            throw new UnsupportedOperationException("MCME-Base only supported players and console as command senders.");
        }
    }

    @Override
    public ScoreboardManager getScoreboardManager() {
        return null;
    }

    @Override
    public McmeServerInfo getAnyConnectedServerInfo() {
        for(McmeProxyPlayer player: getPlayers()) {
            McmeServerInfo server = player.getServerInfo();
            if(server != null) {
                return server;
            }
        }
        return null;
    }

}
