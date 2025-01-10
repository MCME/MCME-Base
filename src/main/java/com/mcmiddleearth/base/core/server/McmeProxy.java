package com.mcmiddleearth.base.core.server;

import com.mcmiddleearth.base.core.command.McmeCommandSender;
import com.mcmiddleearth.base.core.message.Message;
import com.mcmiddleearth.base.core.player.McmeProxyPlayer;
import com.mcmiddleearth.base.core.scoreboard.ScoreboardManager;

import java.util.Collection;
import java.util.UUID;

public interface McmeProxy {

    McmeServerInfo getServerInfo(String serverName);

    Collection<McmeServerInfo> getAllServerInfo();

    void stop(Message message);

    void broadcast(Message message);

    Collection<McmeProxyPlayer> getPlayers();

    Collection<McmeProxyPlayer> getPlayers(McmeServerInfo serverInfo);

    McmeProxyPlayer getPlayer(UUID uuid);

    McmeProxyPlayer getPlayer(String playerName);

    McmeCommandSender getConsole();

    ScoreboardManager getScoreboardManager();

    McmeServerInfo getAnyConnectedServerInfo();

}
