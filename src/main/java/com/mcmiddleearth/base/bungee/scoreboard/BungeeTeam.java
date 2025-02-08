package com.mcmiddleearth.base.bungee.scoreboard;

import com.mcmiddleearth.base.core.player.McmeProxyPlayer;
import com.mcmiddleearth.base.core.scoreboard.internal.PlayerScoreboard;
import com.mcmiddleearth.base.core.scoreboard.internal.ProxyScoreboard;
import com.mcmiddleearth.base.core.scoreboard.internal.ProxyTeam;

public class BungeeTeam extends ProxyTeam {

    public BungeeTeam(ProxyScoreboard scoreboard, String name) {
        super(scoreboard, name);
    }

    // todo @Override
    protected PlayerScoreboard createPlayerScoreboard(McmeProxyPlayer player) {
        return null;
    }
}
