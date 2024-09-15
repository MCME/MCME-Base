package com.mcmiddleearth.base.core.scoreboard.internal;

import com.mcmiddleearth.base.core.message.Message;
import com.mcmiddleearth.base.core.player.McmePlayer;
import com.mcmiddleearth.base.core.player.McmeProxyPlayer;
import com.mcmiddleearth.base.core.scoreboard.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class ProxyScoreboard implements Scoreboard {

    Set<ProxyObjective> objectives;
    Set<ProxyTeam> teams;

    protected abstract PlayerObjective createPlayerObjective(String name, String value, RenderType type);
    protected abstract PlayerScore createPlayerScore(String itemName, String scoreName, int value);
    protected abstract PlayerScoreboard createPlayerScoreboard(McmeProxyPlayer player);


    protected abstract ProxyObjective createProxyObjective(String name, String value, String type);
    protected abstract ProxyTeam createProxyTeam(String name);
    protected abstract ProxyScore createProxyScore(String itemName, String name, int value);


    @Override
    public void clearSlot(@NotNull DisplaySlot slot) {

    }

    @Override
    public @NotNull Set<String> getEntries() {
        return teams.stream().map(Team::getName).collect(Collectors.toSet());
    }

    @Override
    public @Nullable Objective getObjective(@NotNull String name) {
        return objectives.stream().filter(objective -> objective.getName().equals(name)).findFirst().orElse(null);
    }

    @Override
    public @Nullable Objective getObjective(@NotNull DisplaySlot slot) {
        return null;
    }

    @Override
    public @NotNull Set<? extends Objective> getObjectives() {
        return objectives;
    }

    @Override
    public @NotNull Set<? extends Objective> getObjectivesByCriteria(@NotNull String criteria) {
        return objectives.stream().filter(objective -> objective.getCriteria().equals(criteria)).collect(Collectors.toSet());
    }

    @Override
    public @NotNull Set<? extends McmePlayer> getPlayers() {
        Set<McmePlayer> players = new HashSet<>();
        teams.forEach(team -> players.addAll(team.getPlayers()));
        return players;
    }

    @Override
    public @NotNull Set<? extends Score> getScores(@NotNull String entry) {
        Set<Score> scores = new HashSet<>();
        objectives.forEach(objective -> scores.add(objective.getScore(entry)));
        return scores;
    }

    @Override
    public @NotNull Set<? extends Score> getScores(@NotNull McmePlayer player) {
        Team team = getPlayerTeam(player);
        if(team!=null) {
            return getScores(team.getName());
        }
        return Collections.emptySet();
    }

    @Override
    public @Nullable Team getPlayerTeam(@NotNull McmePlayer player) {
        return teams.stream().filter(team -> team.hasPlayer(player)).findFirst().orElse(null);
    }

    @Override
    public @Nullable Team getEntryTeam(@NotNull String entry) {
        return teams.stream().filter(team -> team.getEntries().stream().anyMatch(search -> search.equals(entry))).findFirst().orElse(null);
    }

    @Override
    public @Nullable Team getTeam(@NotNull String teamName) {
        return teams.stream().filter(team->team.getName().equals(teamName)).findFirst().orElse(null);
    }

    @Override
    public @NotNull Team registerNewTeam(@NotNull String name) {
        ProxyTeam team = (ProxyTeam) getTeam(name);
        if(team!=null) {
            throw new IllegalArgumentException("Team already exists.");
        } else {
            team = createProxyTeam(name);
        }
        teams.add(team);
        return team;
    }

    @Override
    public @NotNull Set<? extends Team> getTeams() {
        return teams;
    }

    @Override
    public @NotNull Objective registerNewObjective(@NotNull String name, @NotNull String criteria, @Nullable Message displayName) {
        ProxyObjective objective = (ProxyObjective) getObjective(name);
        if(objective!=null) {
            throw new IllegalArgumentException("Objective already exists!");
        } else {
            objective = createProxyObjective(name, criteria, "interger");
        }
        objectives.add(objective);
        return objective;
    }

    @Override
    public void resetScores(@NotNull String entry) {
        objectives.forEach(objective -> objective.getScore(entry).resetScore());
    }

    @Override
    public void resetScores(@NotNull McmePlayer player) {
        Team team = getPlayerTeam(player);
        if(team != null) {
            resetScores(team.getName());
        }
    }

    void unregisterObjective(ProxyObjective objective){
        objectives.remove(objective);
    }

    public void unregisterTeam(ProxyTeam team) {
        teams.remove(team);
    }
}
