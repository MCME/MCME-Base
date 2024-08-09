package com.mcmiddleearth.base.core.server;

import net.kyori.adventure.text.Component;

public interface McmeServerPing {

    Component getDescription();

    int getOnlinePlayers();

    int getMaxPlayers();

    String getVersion();
}
