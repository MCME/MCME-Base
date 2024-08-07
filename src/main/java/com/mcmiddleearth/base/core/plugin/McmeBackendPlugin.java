package com.mcmiddleearth.base.core.plugin;

import com.mcmiddleearth.base.core.player.McmeBackendPlayer;
import com.mcmiddleearth.base.core.server.McmeBackend;

import java.util.Collection;
import java.util.UUID;

public interface McmeBackendPlugin extends McmePlugin {

    Collection<McmeBackendPlayer> getPlayers();

    McmeBackendPlayer getPlayer(UUID uuid);

    McmeBackendPlayer getPlayer(String playerName);

    McmeBackend getMcmeBackend();

}
