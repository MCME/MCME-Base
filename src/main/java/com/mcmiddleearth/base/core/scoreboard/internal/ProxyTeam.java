package com.mcmiddleearth.base.core.scoreboard.internal;

import com.mcmiddleearth.base.core.message.Message;
import com.mcmiddleearth.base.core.message.MessageColor;
import com.mcmiddleearth.base.core.player.McmePlayer;
import com.mcmiddleearth.base.core.player.McmeProxyPlayer;
import com.mcmiddleearth.base.core.scoreboard.Scoreboard;
import com.mcmiddleearth.base.core.scoreboard.Team;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public abstract class ProxyTeam implements Team {

    private final ProxyScoreboard scoreboard;

    private final Map<McmeProxyPlayer, PlayerScoreboard> playerScoreboards = new HashMap<>();

    private final String name;
    private boolean allowFriendlyFire;
    private boolean canSeeFriendlyInvisible;
    private MessageColor color;
    private Message displayName;
    private Message prefix;
    private Message suffix;

    private final Map<Team.Option, Team.OptionStatus> options = new HashMap<>();

    public ProxyTeam(ProxyScoreboard scoreboard, String name) {
        this.scoreboard = scoreboard;
        this.name = name;
        options.put(Option.COLLISION_RULE, OptionStatus.NEVER);
        options.put(Option.DEATH_MESSAGE_VISIBILITY, OptionStatus.NEVER);
        options.put(Option.NAME_TAG_VISIBILITY, OptionStatus.ALWAYS);
    }

    @Override
    public void addPlayers(Collection<McmePlayer> entities) {

    }

    @Override
    public void addEntries(Collection<String> entries) {
        throw new UnsupportedOperationException("Only players are supported in ProxyTeams!");
    }

    @Override
    public void addEntry(@NotNull String entry) {
        throw new UnsupportedOperationException("Only players are supported in ProxyTeams!");
    }

    @Override
    public void addPlayer(@NotNull McmePlayer player) {

    }

    @Override
    public boolean allowFriendlyFire() {
        return allowFriendlyFire;
    }

    @Override
    public boolean canSeeFriendlyInvisibles() {
        return canSeeFriendlyInvisible;
    }

    @Override
    public @NotNull MessageColor color() {
        return color;
    }

    @Override
    public void color(@Nullable MessageColor color) {
        this.color = color;
    }

    @Override
    public @NotNull Message displayName() {
        return displayName;
    }

    @Override
    public void displayName(@Nullable Message displayName) {
        this.displayName = displayName;
    }

    @Override
    public @NotNull Set<String> getEntries() {
        return null;
    }

    @Override
    public @NotNull String getName() {
        return name;
    }

    @Override
    public @NotNull Team.OptionStatus getOption(@NotNull Option option) {
        return options.get(option);
    }

    @Override
    public @NotNull Set<? extends McmePlayer> getPlayers() {
        return playerScoreboards.keySet();
    }

    @Override
    public @Nullable Scoreboard getScoreboard() {
        return scoreboard;
    }

    @Override
    public int getSize() {
        return playerScoreboards.size();
    }

    @Override
    public boolean hasColor() {
        return color!=null;
    }

    @Override
    public boolean hasPlayer(@NotNull McmePlayer player) {
        return false;
    }

    @Override
    public @NotNull Message prefix() {
        return prefix;
    }

    @Override
    public void prefix(@Nullable Message prefix) {
        this.prefix = prefix;
    }

    @Override
    public boolean removePlayers(Collection<McmePlayer> entities) {
        return false;
    }

    @Override
    public boolean removeEntries(Collection<String> entries) {
        return false;
    }

    @Override
    public boolean removeEntry(@NotNull String entry) {
        return false;
    }

    @Override
    public boolean removePlayer(@NotNull McmePlayer player) {
        return false;
    }

    @Override
    public void setAllowFriendlyFire(boolean enabled) {
        this.allowFriendlyFire = enabled;
    }

    @Override
    public void setCanSeeFriendlyInvisibles(boolean enabled) {
        this.canSeeFriendlyInvisible = enabled;
    }

    @Override
    public void setOption(@NotNull Option option, @NotNull OptionStatus status) {
        options.put(option,status);
    }

    @Override
    public @NotNull Message suffix() {
        return suffix;
    }

    @Override
    public void suffix(@Nullable Message suffix) {
        this.suffix = suffix;
    }

    @Override
    public void unregister() {
        scoreboard.unregisterTeam(this);
    }
}
