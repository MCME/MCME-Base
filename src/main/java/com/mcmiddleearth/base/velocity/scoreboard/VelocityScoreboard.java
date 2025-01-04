package com.mcmiddleearth.base.velocity.scoreboard;

import com.mcmiddleearth.base.core.scoreboard.DisplaySlot;
import com.mcmiddleearth.base.core.scoreboard.Objective;
import com.mcmiddleearth.base.core.scoreboard.Score;
import com.mcmiddleearth.base.core.scoreboard.Team;
import com.mcmiddleearth.base.core.scoreboard.internal.PlayerScoreboard;

import java.util.Collection;
import java.util.List;

public class VelocityScoreboard implements PlayerScoreboard {
    @Override
    public void addObjective(Objective objective) {

    }

    @Override
    public void addScore(Score score) {

    }

    @Override
    public void addTeam(Team team) {

    }

    @Override
    public void clear() {

    }

    @Override
    public String getName() {
        return "";
    }

    @Override
    public Objective getObjective(String name) {
        return null;
    }

    @Override
    public Collection<Objective> getObjectives() {
        return List.of();
    }

    @Override
    public DisplaySlot getPosition() {
        return null;
    }

    @Override
    public Score getScore(String name) {
        return null;
    }

    @Override
    public Collection<Score> getScores() {
        return List.of();
    }

    @Override
    public Team getTeam(String name) {
        return null;
    }

    @Override
    public Collection<Team> getTeams() {
        return List.of();
    }

    @Override
    public void removeObjective(String objectiveName) {

    }

    @Override
    public void removeScore(String scoreName) {

    }

    @Override
    public void removeTeam(String teamName) {

    }

    @Override
    public void setName(String name) {

    }

    @Override
    public void setPosition(DisplaySlot position) {

    }
}
