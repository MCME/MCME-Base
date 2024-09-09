package com.mcmiddleearth.base.core.scoreboard;

import com.mcmiddleearth.base.core.message.Message;
import com.mcmiddleearth.base.core.player.McmePlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Set;

public interface Scoreboard {
    void clearSlot(@NotNull DisplaySlot slot);

    @NotNull Set<String> getEntries();

    @Nullable Objective getObjective(@NotNull String name);
    @Nullable Objective getObjective(@NotNull DisplaySlot slot);
    @NotNull Set<Objective> getObjectives();
    @NotNull Set<Objective> getObjectivesByCriteria(@NotNull String criteria);

    @NotNull Set<McmePlayer> getPlayers();

    @NotNull Set<Score> getScores(@NotNull String entry);
    @NotNull Set<Score> getScores(@NotNull McmePlayer player);

    @Nullable Team getPlayerTeam(@NotNull McmePlayer player);
    @Nullable Team getEntryTeam(@NotNull String entry);
    @Nullable Team getTeam(@NotNull String teamName);
    @NotNull Team registerNewTeam(@NotNull String name);
    @NotNull Set<Team> getTeams();

    @NotNull Objective registerNewObjective(@NotNull String name, @NotNull String criteria, @Nullable Message displayName);
    void resetScores(@NotNull String entry);
    void resetScores(@NotNull McmePlayer player);
}
