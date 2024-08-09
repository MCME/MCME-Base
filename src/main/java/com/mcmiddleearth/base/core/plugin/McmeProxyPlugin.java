package com.mcmiddleearth.base.core.plugin;

import com.mcmiddleearth.base.core.player.McmeProxyPlayer;
import com.mcmiddleearth.base.core.server.McmeProxy;
import com.mcmiddleearth.base.core.server.McmeServerInfo;

import java.util.Collection;
import java.util.UUID;

public interface McmeProxyPlugin extends McmePlugin {

    Collection<McmeProxyPlayer> getPlayers();

    Collection<McmeProxyPlayer> getPlayers(McmeServerInfo serverInfo);

    McmeProxyPlayer getPlayer(UUID uuid);

    McmeProxyPlayer getPlayer(String playerName);

    McmeProxy getMcmeProxy();
}
