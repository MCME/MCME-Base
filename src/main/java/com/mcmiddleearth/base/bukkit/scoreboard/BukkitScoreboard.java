package com.mcmiddleearth.base.bukkit.scoreboard;

import com.mcmiddleearth.base.core.message.Message;
import com.mcmiddleearth.base.core.player.McmePlayer;
import com.mcmiddleearth.base.core.scoreboard.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Set;

public class BukkitScoreboard implements Scoreboard {

    private final org.bukkit.scoreboard.Scoreboard bukkitScoreboard;

    public BukkitScoreboard(org.bukkit.scoreboard.Scoreboard bukkitScoreboard) {
        this.bukkitScoreboard = bukkitScoreboard;
    }

    @Override
    public void clearSlot(@NotNull DisplaySlot slot) {
        bukkitScoreboard.clearSlot(org.bukkit.scoreboard.DisplaySlot.valueOf(slot.name()));
    }

    @Override
    public @NotNull Set<String> getEntries() {
        return bukkitScoreboard.getEntries();
    }

    @Override
    public @Nullable Objective getObjective(@NotNull String name) {
        org.bukkit.scoreboard.Objective objective = bukkitScoreboard.getObjective(name);
        return objective==null ? null : new BukkitObjective(objective);
    }

    @Override
    public @Nullable Objective getObjective(@NotNull DisplaySlot slot) {
        return null;
    }

    @Override
    public @NotNull Set<Objective> getObjectives() {
        return null;
    }

    @Override
    public @NotNull Set<Objective> getObjectivesByCriteria(@NotNull String criteria) {
        return null;
    }

    @Override
    public @NotNull Set<McmePlayer> getPlayers() {
        return null;
    }

    @Override
    public @NotNull Set<Score> getScores(@NotNull String entry) {
        return null;
    }

    @Override
    public @NotNull Set<Score> getScores(@NotNull McmePlayer player) {
        return null;
    }

    @Override
    public @Nullable Team getPlayerTeam(@NotNull McmePlayer player) {
        return null;
    }

    @Override
    public @Nullable Team getEntryTeam(@NotNull String entry) {
        return null;
    }

    @Override
    public @Nullable Team getTeam(@NotNull String teamName) {
        return null;
    }

    @Override
    public @NotNull Team registerNewTeam(@NotNull String name) {
        return null;
    }

    @Override
    public @NotNull Set<Team> getTeams() {
        return null;
    }

    @Override
    public @NotNull Objective registerNewObjective(@NotNull String name, @NotNull String criteria, @Nullable Message displayName) {
        return null;
    }

    @Override
    public void resetScores(@NotNull String entry) {

    }

    @Override
    public void resetScores(@NotNull McmePlayer player) {

    }
}
