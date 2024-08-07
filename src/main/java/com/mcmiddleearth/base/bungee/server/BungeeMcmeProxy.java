package com.mcmiddleearth.base.bungee.server;

import com.mcmiddleearth.base.core.server.McmeProxy;
import com.mcmiddleearth.base.core.server.McmeServerInfo;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;

import java.util.Collection;
import java.util.stream.Collectors;

public class BungeeMcmeProxy implements McmeProxy {

    @Override
    public McmeServerInfo getServerInfo(String serverName) {
        ServerInfo serverInfo = ProxyServer.getInstance().getServerInfo(serverName);
        return serverInfo==null ? null : new BungeeMcmeServerInfo(serverInfo);
    }

    @Override
    public Collection<McmeServerInfo> getAllServerInfo() {
        return ProxyServer.getInstance().getServersCopy().values()
                .stream().map(BungeeMcmeServerInfo::new).collect(Collectors.toList());
    }
}
