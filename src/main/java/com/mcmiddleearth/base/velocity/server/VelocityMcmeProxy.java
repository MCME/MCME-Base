package com.mcmiddleearth.base.velocity.server;

import com.mcmiddleearth.base.core.server.McmeProxy;
import com.mcmiddleearth.base.core.server.McmeServerInfo;
import com.velocitypowered.api.proxy.ProxyServer;
import com.velocitypowered.api.proxy.server.RegisteredServer;

import java.util.Collection;

public class VelocityMcmeProxy implements McmeProxy {

    private final ProxyServer proxyServer;

    public VelocityMcmeProxy(ProxyServer proxyServer) {
        this.proxyServer = proxyServer;
    }

    @Override
    public McmeServerInfo getServerInfo(String serverName) {
        RegisteredServer server = proxyServer.getServer(serverName).orElse(null);
        return server==null ? null : new VelocityMcmeServerInfo(server.getServerInfo());
    }

    @Override
    public Collection<McmeServerInfo> getAllServerInfo() {
        return null;
    }
}
