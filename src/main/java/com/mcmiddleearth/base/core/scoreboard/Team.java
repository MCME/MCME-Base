package com.mcmiddleearth.base.core.scoreboard;

import com.mcmiddleearth.base.core.message.Message;
import com.mcmiddleearth.base.core.message.MessageColor;
import com.mcmiddleearth.base.core.player.McmePlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.Set;

public interface Team {

    void addPlayers(Collection<McmePlayer> entities);
    void addEntries(Collection<String> entries);
    void addEntry(@NotNull String entry);
    void addPlayer(@NotNull McmePlayer player);
    boolean allowFriendlyFire();
    boolean canSeeFriendlyInvisibles();
    @NotNull MessageColor color();
    void color(@Nullable MessageColor color);
    @NotNull Message displayName();
    void displayName(@Nullable Message displayName);
    @NotNull Set<String> getEntries();
    @NotNull String getName();
    @NotNull Team.OptionStatus getOption(@NotNull Team.Option option);
    @NotNull Set<? extends McmePlayer> getPlayers();
    @Nullable Scoreboard getScoreboard();
    int getSize();
    boolean hasColor();
    boolean hasPlayer(@NotNull McmePlayer player);
    @NotNull Message prefix();
    void prefix(@Nullable Message prefix);
    boolean removePlayers(Collection<McmePlayer> entities);
    boolean removeEntries(Collection<String> entries);
    boolean removeEntry(@NotNull String entry);
    boolean removePlayer(@NotNull McmePlayer player);
    void setAllowFriendlyFire(boolean enabled);
    void setCanSeeFriendlyInvisibles(boolean enabled);
    void setOption(@NotNull Team.Option option, @NotNull Team.OptionStatus status);
    @NotNull Message suffix();
    void suffix(@Nullable Message suffix);
    void unregister();

    public enum Option {
        COLLISION_RULE, DEATH_MESSAGE_VISIBILITY, NAME_TAG_VISIBILITY
    }

    public enum OptionStatus {
        ALWAYS, FOR_OTHER_TEAMS, FOR_OWN_TEAM, NEVER
    }
}
