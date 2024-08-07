package com.mcmiddleearth.base.velocity.player;

import com.mcmiddleearth.base.core.player.McmeProxyPlayer;
import com.mcmiddleearth.base.core.plugin.McmePlugin;
import com.mcmiddleearth.base.core.server.McmeServerInfo;
import com.mcmiddleearth.base.velocity.command.VelocityMcmeCommandSender;
import com.velocitypowered.api.proxy.Player;
import net.kyori.adventure.text.Component;

import java.net.SocketAddress;
import java.util.UUID;

public class VelocityMcmePlayer extends VelocityMcmeCommandSender implements McmeProxyPlayer {

    public VelocityMcmePlayer(McmePlugin plugin, Player player) {
        super(plugin, player);
    }

    @Override
    public UUID getUniqueId() {
        return ((Player)getVelocityCommandSource()).getUniqueId();
    }

    @Override
    public SocketAddress getSocketAddress() {
        return ((Player)getVelocityCommandSource()).getRemoteAddress();
    }

    @Override
    public void sendDataToBackend(String channel, byte[] data, boolean queue) {

    }

    @Override
    public McmeServerInfo getServerInfo() {
        return null;
    }

    @Override
    public void disconnect(Component message) {
    }

}
