package com.mcmiddleearth.base.bungee.server;

import com.mcmiddleearth.base.core.server.McmeServerInfo;
import com.mcmiddleearth.base.core.server.McmeServerPing;
import com.mcmiddleearth.base.core.taskScheduling.Callback;
import net.md_5.bungee.api.config.ServerInfo;

import java.net.SocketAddress;

public class BungeeMcmeServerInfo implements McmeServerInfo {

    private final ServerInfo serverInfo;

    public BungeeMcmeServerInfo(ServerInfo serverInfo) {
        this.serverInfo = serverInfo;
    }

    @Override
    public SocketAddress getSocketAddress() {
        return serverInfo.getSocketAddress();
    }

    @Override
    public String getName() {
        return serverInfo.getName();
    }

    public ServerInfo toBungeeServerInfo() {
        return serverInfo;
    }

    @Override
    public void ping(Callback<McmeServerPing> callback) {
        serverInfo.ping((pingResult, error) -> {
            callback.done(new BungeeMcmeServerPing(pingResult), error);
        });
    }
}
