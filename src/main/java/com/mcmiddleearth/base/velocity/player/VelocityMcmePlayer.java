package com.mcmiddleearth.base.velocity.player;

import com.mcmiddleearth.base.core.player.McmeProxyPlayer;
import com.mcmiddleearth.base.core.plugin.McmePlugin;
import com.mcmiddleearth.base.core.server.McmeServerInfo;
import com.mcmiddleearth.base.core.taskScheduling.Callback;
import com.mcmiddleearth.base.velocity.AbstractVelocityPlugin;
import com.mcmiddleearth.base.velocity.command.VelocityMcmeCommandSender;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.ProxyServer;
import com.velocitypowered.api.proxy.ServerConnection;
import com.velocitypowered.api.proxy.messages.MinecraftChannelIdentifier;
import net.kyori.adventure.text.Component;

import java.net.SocketAddress;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class VelocityMcmePlayer extends VelocityMcmeCommandSender implements McmeProxyPlayer {

    private final Player player;

    public VelocityMcmePlayer(McmePlugin plugin, Player player) {
        super(plugin, player);
        this.player = player;
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
    public boolean sendDataToBackend(String channel, byte[] data, boolean queue) {
        ServerConnection server = player.getCurrentServer().orElse(null);
        return server != null && server.sendPluginMessage(MinecraftChannelIdentifier.from(channel), data);
    }

    @Override
    public McmeServerInfo getServerInfo() {
        return null;
    }

    @Override
    public void disconnect(Component message) {
    }

    @Override
    public void connect(McmeServerInfo target, Callback<Boolean> callback) {
        ProxyServer proxy = ((AbstractVelocityPlugin)getPlugin()).getProxyServer();
        proxy.getServer(target.getName()).ifPresent(server -> {
            CompletableFuture<Boolean> result = player.createConnectionRequest(server).connectWithIndication();
            proxy.getScheduler().buildTask(getPlugin(), () -> {
                try {
                    callback.done(result.get(), null);
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            });
        });
    }

    @Override
    public boolean isConnected() {
        return player.getCurrentServer() != null;
    }
}
