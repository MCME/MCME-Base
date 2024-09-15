package com.mcmiddleearth.base.bungee.scoreboard;

import com.mcmiddleearth.base.core.scoreboard.RenderType;
import com.mcmiddleearth.base.core.scoreboard.internal.PlayerObjective;
import com.mcmiddleearth.base.core.scoreboard.internal.PlayerScore;
import com.mcmiddleearth.base.core.scoreboard.internal.ProxyScoreboard;

public class BungeeScoreboard extends ProxyScoreboard {


    @Override
    protected PlayerObjective createPlayerObjective(String name, String value, RenderType type) {
        return null;
    }

    @Override
    protected PlayerScore createPlayerScore(String itemName, String scoreName, int value) {
        return null;
    }
}
