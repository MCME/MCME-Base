package com.mcmiddleearth.base.velocity.player;

import com.mcmiddleearth.base.VelocityBasePlugin;
import com.mcmiddleearth.base.adventure.AdventureMessage;
import com.mcmiddleearth.base.core.message.Message;
import com.mcmiddleearth.base.core.player.McmeProxyPlayer;
import com.mcmiddleearth.base.core.server.McmeServerInfo;
import com.mcmiddleearth.base.core.taskScheduling.Callback;
import com.mcmiddleearth.base.velocity.command.VelocityMcmeCommandSender;
import com.mcmiddleearth.base.velocity.server.VelocityMcmeServerInfo;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.ProxyServer;
import com.velocitypowered.api.proxy.ServerConnection;
import com.velocitypowered.api.proxy.messages.MinecraftChannelIdentifier;

import java.net.SocketAddress;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class VelocityMcmePlayer extends VelocityMcmeCommandSender implements McmeProxyPlayer {

    private final Player player;

    public VelocityMcmePlayer(Player player) {
        super(player);
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
        ServerConnection server = player.getCurrentServer().orElse(null);
        if(server != null) {
            return new VelocityMcmeServerInfo(VelocityBasePlugin.getInstance().getProxyServer(), server.getServerInfo());
        } else {
            return null;
        }

    }

    @Override
    public void disconnect(Message message) {
        player.disconnect(((AdventureMessage)message).getComponent());
    }

    @Override
    public void connect(McmeServerInfo target, Callback<Boolean> callback) {
//VelocityBasePlugin.getInstance().getMcmeLogger().info("VelocityPlayer.connect");
        ProxyServer proxy = VelocityBasePlugin.getInstance().getProxyServer();
        proxy.getServer(target.getName()).ifPresent(server -> {
            CompletableFuture<Boolean> result = player.createConnectionRequest(server).connectWithIndication();
            proxy.getScheduler().buildTask(VelocityBasePlugin.getInstance(), () -> {
                try {
                    boolean connected = result.get();
//VelocityBasePlugin.getInstance().getMcmeLogger().info("callback: "+connected);
                    callback.done(connected, null);
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }).schedule();
        });
    }

    @Override
    public boolean isConnected() {
        return player.getCurrentServer().isPresent();
    }
}
