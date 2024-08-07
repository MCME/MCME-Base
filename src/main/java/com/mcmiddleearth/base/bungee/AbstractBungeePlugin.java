package com.mcmiddleearth.base.bungee;

import com.mcmiddleearth.base.bungee.command.BungeeMcmeCommandSender;
import com.mcmiddleearth.base.bungee.player.BungeeMcmePlayer;
import com.mcmiddleearth.base.bungee.server.BungeeMcmeProxy;
import com.mcmiddleearth.base.bungee.server.BungeeMcmeServerInfo;
import com.mcmiddleearth.base.bungee.taskScheduling.BungeeTask;
import com.mcmiddleearth.base.core.command.McmeCommandSender;
import com.mcmiddleearth.base.core.player.McmeProxyPlayer;
import com.mcmiddleearth.base.core.plugin.McmeProxyPlugin;
import com.mcmiddleearth.base.core.server.McmeProxy;
import com.mcmiddleearth.base.core.server.McmeServerInfo;
import com.mcmiddleearth.base.core.taskScheduling.Task;
import net.kyori.adventure.platform.bungeecord.BungeeAudiences;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Plugin;

import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

public abstract class AbstractBungeePlugin extends Plugin implements McmeProxyPlugin {

    private BungeeAudiences adventure;

    private final BungeeMcmeProxy mcmeProxy = new BungeeMcmeProxy(this);

    @Override
    public void onEnable() {
        adventure = BungeeAudiences.create(this);
    }

    @Override
    public void onDisable() {
        adventure.close();
    }

    @Override
    public Collection<McmeProxyPlayer> getPlayers() {
        return ProxyServer.getInstance().getPlayers().stream()
                .map(player -> new BungeeMcmePlayer(this, player)).collect(Collectors.toList());
    }

    @Override
    public Collection<McmeProxyPlayer> getPlayers(McmeServerInfo serverInfo) {
        return ((BungeeMcmeServerInfo)serverInfo).toBungeeServerInfo().getPlayers().stream()
                .map(player -> new BungeeMcmePlayer(this, player)).collect(Collectors.toList());
    }

    @Override
    public McmeProxyPlayer getPlayer(UUID uuid) {
        return new BungeeMcmePlayer(this, ProxyServer.getInstance().getPlayer(uuid));
    }

    @Override
    public McmeProxyPlayer getPlayer(String playerName) {
        return new BungeeMcmePlayer(this, ProxyServer.getInstance().getPlayer(playerName));
    }

    public McmeProxyPlayer getPlayer(ProxiedPlayer player) {
        return new BungeeMcmePlayer(this, player);
    }

    @Override
    public McmeCommandSender getConsole() {
        return new BungeeMcmeCommandSender(this, ProxyServer.getInstance().getConsole());
    }

    public Task getTask(Runnable task) {
        return new BungeeTask(this, task);
    }


    public BungeeAudiences getAdventure() {
            return adventure;
    }

    @Override
    public McmeProxy getMcmeProxy() {
        return mcmeProxy;
    }


}
