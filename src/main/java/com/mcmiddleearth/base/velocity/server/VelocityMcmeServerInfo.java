package com.mcmiddleearth.base.velocity.server;

import com.mcmiddleearth.base.core.server.McmeServerInfo;
import com.velocitypowered.api.proxy.server.ServerInfo;

import java.net.SocketAddress;

public class VelocityMcmeServerInfo implements McmeServerInfo {

    ServerInfo serverInfo;

    public VelocityMcmeServerInfo(ServerInfo serverInfo) {
        this.serverInfo = serverInfo;
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
}
