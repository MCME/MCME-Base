package com.mcmiddleearth.base.bungee.player;

import com.mcmiddleearth.base.bungee.command.BungeeMcmeCommandSender;
import com.mcmiddleearth.base.core.McmePlugin;
import com.mcmiddleearth.base.core.player.McmeProxyPlayer;
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
    public void sendDataToBackend(String channel, byte[] data, boolean queue) {
        player.getServer().getInfo().sendData(channel, data,queue);
    }
}
