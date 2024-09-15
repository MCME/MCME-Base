package com.mcmiddleearth.base.core.scoreboard.internal;

import com.mcmiddleearth.base.core.message.Message;
import com.mcmiddleearth.base.core.player.McmePlayer;
import com.mcmiddleearth.base.core.scoreboard.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ProxyScore implements Score {

    private ProxyObjective objective;

    private Message customName;
    private final String entry;
    private boolean isSet;
    private boolean triggerable;
    private NumberFormat numberFormat;
    private int score;

    public ProxyScore(String entry) {
        this.entry = entry;
        isSet = false;
        triggerable = true;
        numberFormat = NumberFormat.noStyle();
        score = 0;
    }

    @Override
    public Message customName() {
        return customName;
    }

    @Override
    public void customName(@Nullable Message customName) {
        this.customName = customName;
    }

    @Override
    public @NotNull String getEntry() {
        return entry;
    }

    @Override
    public @NotNull Objective getObjective() {
        return objective;
    }

    @Override
    public @NotNull McmePlayer getPlayer() {
        Scoreboard scoreboard = objective.getScoreboard();
        if(scoreboard!=null) {
            Team team = scoreboard.getTeam(entry);
            if(team !=null) {
                return team.getPlayers().stream().findFirst().orElseThrow(() -> new RuntimeException("Player not found!"));
            }
        }
        throw new RuntimeException("Scoreboard or Team not found!");
    }

    @Override
    public int getScore() {
        return score;
    }

    @Override
    public @Nullable Scoreboard getScoreboard() {
        return objective.getScoreboard();
    }

    @Override
    public boolean isScoreSet() {
        return isSet;
    }

    @Override
    public boolean isTriggerable() {
        return triggerable;
    }

    @Override
    public NumberFormat numberFormat() {
        return numberFormat;
    }

    @Override
    public void numberFormat(NumberFormat format) {
        this.numberFormat = format;
    }

    @Override
    public void resetScore() {
        isSet = false;
        score = 0;
    }

    @Override
    public void setScore(int score) {
        this.score = score;
        isSet = true;
    }

    @Override
    public void setTriggerable(boolean triggerable) {
        this.triggerable = triggerable;
    }
}
