package com.mcmiddleearth.base.bungee.player;

import com.mcmiddleearth.base.bungee.command.BungeeMcmeCommandSender;
import com.mcmiddleearth.base.bungee.server.BungeeMcmeServerInfo;
import com.mcmiddleearth.base.core.plugin.McmePlugin;
import com.mcmiddleearth.base.core.player.McmeProxyPlayer;
import com.mcmiddleearth.base.core.server.McmeServerInfo;
import com.mcmiddleearth.base.core.taskScheduling.Callback;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.bungeecord.BungeeComponentSerializer;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.net.SocketAddress;
import java.util.UUID;

public class BungeeMcmePlayer extends BungeeMcmeCommandSender implements McmeProxyPlayer {

    private final ProxiedPlayer player;

    public BungeeMcmePlayer(McmePlugin plugin, ProxiedPlayer player) {
        super(plugin, player);
        this.player = player;
    }

    @Override
    public UUID getUniqueId() {
        return player.getUniqueId();
    }

    @Override
    public SocketAddress getSocketAddress() {
        return player.getSocketAddress();
    }

    @Override
    public boolean sendDataToBackend(String channel, byte[] data, boolean queue) {
        return player.getServer().getInfo().sendData(channel, data,queue);
    }

    @Override
    public McmeServerInfo getServerInfo() {
        if(player.getServer()!=null) {
            return new BungeeMcmeServerInfo(player.getServer().getInfo());
        } else {
            return null;
        }
    }

    @Override
    public void disconnect(Component message) {
        player.disconnect(BungeeComponentSerializer.get().serialize(message));
    }

    public void connect(McmeServerInfo target, Callback<Boolean> callback) {
        player.connect(((BungeeMcmeServerInfo)target).toBungeeServerInfo(), callback::done);
    }

    @Override
    public boolean isConnected() {
        return player.isConnected();
    }

    public ProxiedPlayer getProxiedPlayer() {
        return player;
    }
}
