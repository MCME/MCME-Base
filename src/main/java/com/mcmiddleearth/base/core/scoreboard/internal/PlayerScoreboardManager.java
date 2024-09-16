package com.mcmiddleearth.base.core.scoreboard.internal;

import com.mcmiddleearth.base.core.player.McmePlayer;
import com.mcmiddleearth.base.core.player.McmeProxyPlayer;

import java.util.HashMap;
import java.util.Map;

public interface PlayerScoreboardManager {

    private final ProxyScoreboard scoreboard;

    private final Map<McmeProxyPlayer, PlayerScoreboard> playerScoreboards = new HashMap<>();

    public PlayerScoreboardManager(ProxyScoreboard scoreboard) {
        this.scoreboard = scoreboard;
    }

    void addPlayerScoreboard(McmeProxyPlayer player) {
        PlayerScoreboard playerScoreboard = scoreboard.createPlayerScoreboard((McmeProxyPlayer) player);
        initPlayerScoreboard(player, playerScoreboard);
        playerScoreboards.put((McmeProxyPlayer) player, playerScoreboard);
    }

    void initPlayerScoreboard(McmeProxyPlayer player, PlayerScoreboard playerScoreboard) {
        //set all information
        playerScoreboard.clear();
        playerScoreboard.setName(player.getName());
        playerScoreboard.setPosition(scoreboard.getObjectives().);
    }

    public abstract void updateDisplayName(ProxyTeam team);

    public abstract void updateValue(ProxyScore score);

    public abstract void updateNumberFormat(ProxyScore score);

    public abstract void updateCustomName(ProxyScore score);

    public Map<McmeProxyPlayer, PlayerScoreboard> getPlayerScoreboards() {
        return playerScoreboards;
    }

    public abstract void updateRenderType(ProxyObjective objective);

    public abstract void updateDisplayName(ProxyObjective objective);

    public abstract void updateNumberFormat(ProxyObjective objective);

    public abstract void updateCustomName(ProxyObjective objective);

    public abstract void resetScores(ProxyScoreboard proxyScoreboard, String entry);

    public abstract void removeObjective(ProxyObjective objective);

    public abstract void addObjective(ProxyObjective objective);
}
