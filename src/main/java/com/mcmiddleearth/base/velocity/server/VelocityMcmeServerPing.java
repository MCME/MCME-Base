package com.mcmiddleearth.base.velocity.server;

import com.mcmiddleearth.base.core.server.McmeServerPing;
import com.velocitypowered.api.proxy.server.ServerPing;
import net.kyori.adventure.text.Component;

public class VelocityMcmeServerPing implements McmeServerPing {

    ServerPing serverPing;

    public VelocityMcmeServerPing(ServerPing serverPing) {
        this.serverPing = serverPing;
    }

    @Override
    public Component getDescription() {
        return serverPing.getDescriptionComponent();
    }

    @Override
    public int getOnlinePlayers() {
        ServerPing.Players players = serverPing.getPlayers().orElse(null);
        if(players!=null) {
            return getOnlinePlayers();
        } else {
            return -1;
        }
    }

    @Override
    public int getMaxPlayers() {
        ServerPing.Players players = serverPing.getPlayers().orElse(null);
        if(players!=null) {
            return getMaxPlayers();
        } else {
            return -1;
        }
    }

    @Override
    public String getVersion() {
        return serverPing.getVersion().getName();
    }
}
