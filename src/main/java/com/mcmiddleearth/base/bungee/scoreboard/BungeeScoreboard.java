package com.mcmiddleearth.base.bungee.scoreboard;

import com.mcmiddleearth.base.core.player.McmeProxyPlayer;
import com.mcmiddleearth.base.core.scoreboard.RenderType;
import com.mcmiddleearth.base.core.scoreboard.internal.*;

public class BungeeScoreboard extends ProxyScoreboard {


    @Override
    protected void removeInvalidPlayerScoreboards() {

    }

    @Override
    protected ProxyObjective createProxyObjective(String name, String value, String type) {
        return null;
    }

    @Override
    protected ProxyTeam createProxyTeam(String name) {
        return null;
    }

    @Override
    protected ProxyScore createProxyScore(String itemName, String name, int value) {
        return null;
    }

    @Override
    protected PlayerObjective createPlayerObjective(String name, String value, RenderType type) {
        return null;
    }

    @Override
    protected PlayerScore createPlayerScore(String itemName, String scoreName, int value) {
        return null;
    }

    @Override
    protected PlayerScoreboard createPlayerScoreboard(McmeProxyPlayer player) {
        return null;
    }
}
