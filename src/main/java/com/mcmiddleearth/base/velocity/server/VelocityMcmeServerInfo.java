package com.mcmiddleearth.base.velocity.server;

import com.mcmiddleearth.base.VelocityBasePlugin;
import com.mcmiddleearth.base.bungee.server.BungeeMcmeServerPing;
import com.mcmiddleearth.base.core.server.McmeServerInfo;
import com.mcmiddleearth.base.core.server.McmeServerPing;
import com.mcmiddleearth.base.core.taskScheduling.Callback;
import com.velocitypowered.api.proxy.ProxyServer;
import com.velocitypowered.api.proxy.server.PingOptions;
import com.velocitypowered.api.proxy.server.RegisteredServer;
import com.velocitypowered.api.proxy.server.ServerInfo;
import com.velocitypowered.api.proxy.server.ServerPing;

import java.net.SocketAddress;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class VelocityMcmeServerInfo implements McmeServerInfo {

    ServerInfo serverInfo;
    ProxyServer proxyServer;

    public VelocityMcmeServerInfo(ProxyServer proxyServer, ServerInfo serverInfo) {
        this.serverInfo = serverInfo;
        this.proxyServer = proxyServer;
    }

    @Override
    public SocketAddress getSocketAddress() {
        return serverInfo.getAddress();
    }

    @Override
    public String getName() {
        return serverInfo.getName();
    }

    public ServerInfo toVelocityServerInfo() {
        return serverInfo;
    }

    @Override
    public void ping(Callback<McmeServerPing> callback) {
        RegisteredServer server = proxyServer.registerServer(serverInfo);
        if(server!=null) {
            CompletableFuture<ServerPing> future = server.ping(PingOptions.DEFAULT);
            proxyServer.getScheduler().buildTask(VelocityBasePlugin.getInstance(), () -> {
                try {
                    ServerPing pingResult = future.get();
                    callback.done(new VelocityMcmeServerPing(pingResult), null);
                } catch (InterruptedException | ExecutionException e) {
                    callback.done(null, new Throwable("Server did not respond!"));
                }
            }).schedule();
        } else {
            callback.done(null, new Throwable("Unknown server!"));
        }
    }
}
