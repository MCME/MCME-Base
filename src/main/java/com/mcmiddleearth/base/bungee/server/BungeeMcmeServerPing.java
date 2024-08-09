package com.mcmiddleearth.base.bungee.server;

import com.mcmiddleearth.base.core.server.McmeServerPing;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.bungeecord.BungeeComponentSerializer;
import net.md_5.bungee.api.ServerPing;
import net.md_5.bungee.api.chat.BaseComponent;

public class BungeeMcmeServerPing implements McmeServerPing {

    ServerPing serverPing;

    public BungeeMcmeServerPing(ServerPing serverPing) {
        this.serverPing = serverPing;
    }

    @Override
    public Component getDescription() {
        return BungeeComponentSerializer.get().deserialize(new BaseComponent[]{serverPing.getDescriptionComponent()});
    }

    @Override
    public int getOnlinePlayers() {
        return serverPing.getPlayers().getOnline();
    }

    @Override
    public int getMaxPlayers() {
        return serverPing.getPlayers().getMax();
    }

    @Override
    public String getVersion() {
        return serverPing.getVersion().getName();
    }
}
