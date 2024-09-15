package com.mcmiddleearth.base.bungee.scoreboard.internal;

import com.mcmiddleearth.base.bungee.player.BungeeMcmePlayer;
import com.mcmiddleearth.base.bungee.scoreboard.BungeeObjective;
import com.mcmiddleearth.base.core.scoreboard.DisplaySlot;
import com.mcmiddleearth.base.core.scoreboard.Objective;
import com.mcmiddleearth.base.core.scoreboard.Score;
import com.mcmiddleearth.base.core.scoreboard.Team;
import com.mcmiddleearth.base.core.scoreboard.internal.PlayerScoreboard;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.Collection;

public class BungeePlayerScoreboard implements PlayerScoreboard {

    ProxiedPlayer player;

    public BungeePlayerScoreboard(BungeeMcmePlayer player) {
        this.player = player.getProxiedPlayer();
    }

    @Override
    public void addObjective(Objective objective) {
        player.getScoreboard().addObjective(((BungeeObjective)objective).getObjective;
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
        return null;
    }

    @Override
    public Objective getObjective(String name) {
        return null;
    }

    @Override
    public Collection<Objective> getObjectives() {
        return null;
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
        return null;
    }

    @Override
    public Team getTeam(String name) {
        return null;
    }

    @Override
    public Collection<Team> getTeams() {
        return null;
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
