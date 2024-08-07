package com.mcmiddleearth.base.core.plugin;

import com.mcmiddleearth.base.core.player.McmeProxyPlayer;
import com.mcmiddleearth.base.core.server.McmeProxy;

import java.util.Collection;
import java.util.UUID;

public interface McmeProxyPlugin extends McmePlugin {

    Collection<McmeProxyPlayer> getPlayers();

    McmeProxyPlayer getPlayer(UUID uuid);

    McmeProxyPlayer getPlayer(String playerName);

    McmeProxy getMcmeProxy();
}
