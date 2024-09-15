package com.mcmiddleearth.base.core.scoreboard.internal;

import com.mcmiddleearth.base.core.scoreboard.DisplaySlot;
import com.mcmiddleearth.base.core.scoreboard.Objective;
import com.mcmiddleearth.base.core.scoreboard.Score;
import com.mcmiddleearth.base.core.scoreboard.Team;

import java.util.Collection;

public interface PlayerScoreboard {

    void addObjective(Objective objective);
    void addScore(Score score);
    void addTeam(Team team);
    void clear();
    boolean	equals(Object o);
    String getName();
    Objective getObjective(String name);
    Collection<Objective> getObjectives();
    DisplaySlot getPosition();
    Score getScore(String name);
    Collection<Score> getScores();
    Team getTeam(String name);
    Collection<Team> getTeams();
    int hashCode();
    void removeObjective(String objectiveName);
    void removeScore(String scoreName);
    void removeTeam(String teamName);
    void setName(String name);
    void setPosition(DisplaySlot position);

}
