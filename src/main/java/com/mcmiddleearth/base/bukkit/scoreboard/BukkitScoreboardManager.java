package com.mcmiddleearth.base.bukkit.scoreboard;

import com.mcmiddleearth.base.core.scoreboard.Scoreboard;
import com.mcmiddleearth.base.core.scoreboard.ScoreboardManager;
import org.bukkit.Bukkit;

public class BukkitScoreboardManager implements ScoreboardManager {

    @Override
    public Scoreboard getMainScoreboard() {
        return new BukkitScoreboard(Bukkit.getScoreboardManager().getMainScoreboard());
    }

    @Override
    public Scoreboard getNewScoreboard() {
        return new BukkitScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
    }
}
