package com.mcmiddleearth.base.bukkit.scoreboard;

import com.mcmiddleearth.base.core.message.Message;
import com.mcmiddleearth.base.core.player.McmePlayer;
import com.mcmiddleearth.base.core.scoreboard.NumberFormat;
import com.mcmiddleearth.base.core.scoreboard.Objective;
import com.mcmiddleearth.base.core.scoreboard.Score;
import com.mcmiddleearth.base.core.scoreboard.Scoreboard;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BukkitScore implements Score {

    private final org.bukkit.scoreboard.Score bukkitScore;

    public BukkitScore(org.bukkit.scoreboard.Score bukkitScore) {
        this.bukkitScore = bukkitScore;
    }

    @Override
    public Message customName() {
        return null;
    }

    @Override
    public void customName(@Nullable Message customName) {

    }

    @Override
    public @NotNull String getEntry() {
        return null;
    }

    @Override
    public @NotNull Objective getObjective() {
        return null;
    }

    @Override
    public @NotNull McmePlayer getPlayer() {
        return null;
    }

    @Override
    public int getScore() {
        return 0;
    }

    @Override
    public @Nullable Scoreboard getScoreboard() {
        return null;
    }

    @Override
    public boolean isScoreSet() {
        return false;
    }

    @Override
    public boolean isTriggerable() {
        return false;
    }

    @Override
    public NumberFormat numberFormat() {
        return null;
    }

    @Override
    public void numberFormat(NumberFormat format) {

    }

    @Override
    public void resetScore() {

    }

    @Override
    public void setScore(int score) {

    }

    @Override
    public void setTriggerable(boolean triggerable) {

    }
}