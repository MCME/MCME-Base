package com.mcmiddleearth.base.bungee.player;

import com.mcmiddleearth.base.bungee.command.BungeeMcmeCommandSender;
import com.mcmiddleearth.base.core.plugin.McmePlugin;
import com.mcmiddleearth.base.core.player.McmeProxyPlayer;
import com.mcmiddleearth.base.core.server.McmeServerInfo;
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
    public void sendDataToBackend(String channel, byte[] data, boolean queue) {
        player.getServer().getInfo().sendData(channel, data,queue);
    }

    @Override
    public McmeServerInfo getServerInfo() {
        return new McmeServerInfo(player.getServer().getInfo().getSocketAddress(),
                                  player.getServer().getInfo().getName());
    }

    @Override
    public void disconnect(Component message) {
        player.disconnect(BungeeComponentSerializer.get().serialize(message));
    }
}
