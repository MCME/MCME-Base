package com.mcmiddleearth.base.core.scoreboard;

import com.mcmiddleearth.base.core.message.Message;
import com.mcmiddleearth.base.core.player.McmePlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


public interface Score {

    Message customName();
    void customName(@Nullable Message customName);
    @NotNull String getEntry();
    @NotNull Objective getObjective();
    @NotNull McmePlayer getPlayer();
    int getScore();
    @Nullable Scoreboard getScoreboard();
    boolean isScoreSet();
    boolean isTriggerable();
    NumberFormat numberFormat();
    void numberFormat(NumberFormat format);
    void resetScore();
    void setScore(int score);
    void setTriggerable(boolean triggerable);
}
