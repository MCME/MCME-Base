package com.mcmiddleearth.base.core.server;

import com.mcmiddleearth.base.core.command.McmeCommandSender;
import com.mcmiddleearth.base.core.player.McmeProxyPlayer;
import net.kyori.adventure.text.Component;

import java.util.Collection;
import java.util.UUID;

public interface McmeProxy {

    McmeServerInfo getServerInfo(String serverName);

    Collection<McmeServerInfo> getAllServerInfo();

    void stop(Component message);

    void broadcast(Component message);

    boolean sendPluginMessage(McmeServerInfo server, String channel, byte[] data, boolean queue);

    Collection<McmeProxyPlayer> getPlayers();

    Collection<McmeProxyPlayer> getPlayers(McmeServerInfo serverInfo);

    McmeProxyPlayer getPlayer(UUID uuid);

    McmeProxyPlayer getPlayer(String playerName);

    McmeCommandSender getConsole();
}
