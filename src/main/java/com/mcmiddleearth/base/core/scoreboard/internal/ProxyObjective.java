package com.mcmiddleearth.base.core.scoreboard.internal;

import com.mcmiddleearth.base.core.message.Message;
import com.mcmiddleearth.base.core.player.McmePlayer;
import com.mcmiddleearth.base.core.scoreboard.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Set;

public class ProxyObjective implements Objective {

    private final ProxyScoreboard scoreboard;
    private final PlayerScoreboardManager scoreboardManager;

    private Message displayName;
    private final String name;
    private final String criteria;
    private RenderType renderType;
    private DisplaySlot displaySlot;
    private NumberFormat numberFormat;
    private boolean autoUpdate;

    private Set<ProxyScore> scores;

    public ProxyObjective(ProxyScoreboard scoreboard, Message displayName, String name, String criteria) {
        this.scoreboard = scoreboard;
        scoreboardManager = scoreboard.getScoreboardManager();
        this.displayName = displayName;
        this.name = name;
        this.criteria = criteria;
        renderType = RenderType.INTEGER;
        displaySlot = DisplaySlot.SIDEBAR;
        autoUpdate = true;
        numberFormat = NumberFormat.noStyle();
    }

    @Override
    public @NotNull Message displayName() {
        return displayName;
    }

    @Override
    public void displayName(@Nullable Message displayName) {
        this.displayName = displayName;
        scoreboardManager.updateCustomName(this);
    }

    @Override
    public @NotNull String getCriteria() {
        return criteria;
    }

    @Override
    public @Nullable DisplaySlot getDisplaySlot() {
        return displaySlot;
    }

    @Override
    public @NotNull String getName() {
        return name;
    }

    @Override
    public @NotNull RenderType getRenderType() {
        return renderType;
    }

    @Override
    public @NotNull Score getScore(@NotNull String entry) {
        ProxyScore score = scores.stream().filter(search -> search.getEntry().equals(entry)).findFirst().orElse(null);
        if(score == null) {
            score = scoreboard.createProxyScore(entry, entry, 0);
            scores.add(score);
            scoreboardManager.updateValue(score);
        }
        return score;
    }

    @Override
    public @NotNull Score getScore(@NotNull McmePlayer player) {
        Team team = scoreboard.getPlayerTeam(player);
        if(team==null) {
            team = scoreboard.registerNewTeam(player.getName());
            team.addPlayer(player);
        }
        return getScore(team.getName());
    }

    @Override
    public @Nullable Scoreboard getScoreboard() {
        return scoreboard;
    }

    @Override
    public boolean isModifiable() {
        return true;
    }

    @Override
    public NumberFormat numberFormat() {
        return numberFormat;
    }

    @Override
    public void numberFormat(NumberFormat format) {
        this.numberFormat = format;
        scoreboardManager.updateNumberFormat(this);
    }

    @Override
    public void setAutoUpdateDisplay(boolean autoUpdateDisplay) {
        this.autoUpdate = autoUpdateDisplay;
    }

    @Override
    public void setDisplaySlot(@Nullable DisplaySlot slot) {
        this.displaySlot = slot;
        scoreboardManager.updateDisplayName(this);
    }

    @Override
    public void setRenderType(@NotNull RenderType renderType) {
        this.renderType = renderType;
        scoreboardManager.updateRenderType(this);
    }

    @Override
    public void unregister() {
        scoreboard.unregisterObjective(this);
    }

    @Override
    public boolean willAutoUpdateDisplay() {
        return autoUpdate;
    }
}
