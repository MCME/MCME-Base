package com.mcmiddleearth.base.core.server;

import com.mcmiddleearth.base.core.command.McmeCommandSender;
import com.mcmiddleearth.base.core.player.McmeBackendPlayer;
import net.kyori.adventure.text.Component;

import java.util.Collection;
import java.util.UUID;

public interface McmeBackend {

    void stop(Component message);

    void broadcast(Component message);

    Collection<McmeBackendPlayer> getPlayers();

    McmeBackendPlayer getPlayer(UUID uuid);

    McmeBackendPlayer getPlayer(String playerName);

    McmeCommandSender getConsole();

}
