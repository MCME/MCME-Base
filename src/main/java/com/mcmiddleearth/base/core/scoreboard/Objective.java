package com.mcmiddleearth.base.core.scoreboard;

import com.mcmiddleearth.base.core.message.Message;
import com.mcmiddleearth.base.core.player.McmePlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface Objective {
    @NotNull Message displayName();
    void displayName(@Nullable Message displayName);
    @NotNull String getCriteria();
    @Nullable DisplaySlot getDisplaySlot();
    @NotNull String getName();
    @NotNull RenderType getRenderType();
    @NotNull Score getScore(@NotNull String entry);
    @NotNull Score getScore(@NotNull McmePlayer player);
    @Nullable Scoreboard getScoreboard();
    boolean isModifiable();
    NumberFormat numberFormat();
    void numberFormat(NumberFormat format);
    void setAutoUpdateDisplay(boolean autoUpdateDisplay);
    void setDisplaySlot(@Nullable DisplaySlot slot);
    void setRenderType(@NotNull RenderType renderType);
    void unregister();
    boolean willAutoUpdateDisplay();
}
