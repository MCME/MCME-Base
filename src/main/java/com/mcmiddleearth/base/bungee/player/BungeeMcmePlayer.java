package com.mcmiddleearth.base.bungee.player;

import com.mcmiddleearth.base.adventure.AdventureMessage;
import com.mcmiddleearth.base.bungee.command.BungeeMcmeCommandSender;
import com.mcmiddleearth.base.bungee.server.BungeeMcmeServerInfo;
import com.mcmiddleearth.base.core.message.Message;
import com.mcmiddleearth.base.core.player.McmeProxyPlayer;
import com.mcmiddleearth.base.core.server.McmeServerInfo;
import com.mcmiddleearth.base.core.taskScheduling.Callback;
import net.kyori.adventure.text.serializer.bungeecord.BungeeComponentSerializer;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.net.SocketAddress;
import java.util.UUID;

public class BungeeMcmePlayer extends BungeeMcmeCommandSender implements McmeProxyPlayer {

    private final UUID uniqueId;

    public BungeeMcmePlayer(ProxiedPlayer player) {
        this.uniqueId = player.getUniqueId();
    }

    public BungeeMcmePlayer(UUID uniqueId) {
        this.uniqueId = uniqueId;
    }

    @Override
    public UUID getUniqueId() {
        return uniqueId;
    }

    @Override
    public SocketAddress getSocketAddress() {
        ProxiedPlayer player = getBungeePlayer();
        if(player!=null && player.isConnected()) {
            return player.getSocketAddress();
        } else {
            return null;
        }
    }

    @Override
    public boolean sendDataToBackend(String channel, byte[] data, boolean queue) {
        ProxiedPlayer player = getBungeePlayer();
        if(player != null && player.isConnected()) {
            return player.getServer().getInfo().sendData(channel, data, queue);
        } else {
            return false;
        }
    }

    @Override
    public McmeServerInfo getServerInfo() {
        ProxiedPlayer player = getBungeePlayer();
        if(player != null && player.getServer()!=null) {
            return new BungeeMcmeServerInfo(player.getServer().getInfo());
        } else {
            return null;
        }
    }

    @Override
    public void disconnect(Message message) {
        ProxiedPlayer player = getBungeePlayer();
        if(player != null) {
            player.disconnect(BungeeComponentSerializer.get()
                    .serialize(((AdventureMessage) message).getComponent()));
        }
    }

    public void connect(McmeServerInfo target, Callback<Boolean> callback) {
        ProxiedPlayer player = getBungeePlayer();
        if(player != null) {
            player.connect(((BungeeMcmeServerInfo) target).toBungeeServerInfo(), callback::done);
        }
    }

    @Override
    public boolean isConnected() {
        ProxiedPlayer bungeePlayer = getBungeePlayer();
        return bungeePlayer != null && bungeePlayer.isConnected();
    }

    @Override
    public boolean equals(Object other) {
        ProxiedPlayer thisPlayer = getBungeePlayer();
        return other instanceof BungeeMcmePlayer otherPlayer
                && thisPlayer != null
                && otherPlayer.getUniqueId().equals(uniqueId)
                && otherPlayer.getName().equals(thisPlayer.getName());
    }

    @Override
    protected CommandSender getBungeeCommandSender() {
        return ProxyServer.getInstance().getPlayer(uniqueId);
    }

    public ProxiedPlayer getBungeePlayer() {
        return ProxyServer.getInstance().getPlayer(uniqueId);
    }
}
