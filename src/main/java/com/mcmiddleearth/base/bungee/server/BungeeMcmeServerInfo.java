package com.mcmiddleearth.base.bungee.server;

import com.mcmiddleearth.base.core.server.McmeServerInfo;
import net.md_5.bungee.api.config.ServerInfo;

public class BungeeMcmeServerInfo extends McmeServerInfo {

    public BungeeMcmeServerInfo(ServerInfo serverInfo) {
        super(serverInfo.getSocketAddress(), serverInfo.getName());
    }
}
