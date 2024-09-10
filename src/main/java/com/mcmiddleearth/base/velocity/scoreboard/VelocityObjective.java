package com.mcmiddleearth.base.velocity.scoreboard;

import com.mcmiddleearth.base.core.message.Message;
import com.mcmiddleearth.base.core.player.McmePlayer;
import com.mcmiddleearth.base.core.scoreboard.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class VelocityObjective implements Objective {


    @Override
    public @NotNull Message displayName() {
        return null;
    }

    @Override
    public void displayName(@Nullable Message displayName) {

    }

    @Override
    public @NotNull String getCriteria() {
        return null;
    }

    @Override
    public @Nullable DisplaySlot getDisplaySlot() {
        return null;
    }

    @Override
    public @NotNull String getName() {
        return null;
    }

    @Override
    public @NotNull RenderType getRenderType() {
        return null;
    }

    @Override
    public @NotNull Score getScore(@NotNull String entry) {
        return null;
    }

    @Override
    public @NotNull Score getScore(@NotNull McmePlayer player) {
        return null;
    }

    @Override
    public @Nullable Scoreboard getScoreboard() {
        return null;
    }

    @Override
    public boolean isModifiable() {
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
    public void setAutoUpdateDisplay(boolean autoUpdateDisplay) {

    }

    @Override
    public void setDisplaySlot(@Nullable DisplaySlot slot) {

    }

    @Override
    public void setRenderType(@NotNull RenderType renderType) {

    }

    @Override
    public void unregister() {

    }

    @Override
    public boolean willAutoUpdateDisplay() {
        return false;
    }
}
