package com.mcmiddleearth.base.velocity.server;

import com.mcmiddleearth.base.core.server.McmeServerInfo;
import com.velocitypowered.api.proxy.server.ServerInfo;

public class VelocityMcmeServerInfo extends McmeServerInfo {

    public VelocityMcmeServerInfo(ServerInfo serverInfo) {
        super(serverInfo.getAddress(), serverInfo.getName());
    }
}
