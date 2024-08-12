package com.mcmiddleearth.base.bukkit.server;

import com.mcmiddleearth.base.bukkit.command.BukkitMcmeCommandSender;
import com.mcmiddleearth.base.bukkit.player.BukkitMcmePlayer;
import com.mcmiddleearth.base.core.command.McmeCommandSender;
import com.mcmiddleearth.base.core.player.McmeBackendPlayer;
import com.mcmiddleearth.base.core.server.McmeBackend;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

public class PaperMcmeBackend implements McmeBackend {

    @Override
    public void stop(Component message) {

    }

    @Override
    public void broadcast(Component message) {

    }

    @Override
    public Collection<McmeBackendPlayer> getPlayers() {
        return Bukkit.getOnlinePlayers().stream()
                .map(BukkitMcmePlayer::new).collect(Collectors.toList());
    }

    @Override
    public McmeBackendPlayer getPlayer(UUID uuid) {
        return new BukkitMcmePlayer(Bukkit.getPlayer(uuid));
    }

    @Override
    public McmeBackendPlayer getPlayer(String playerName) {
        return new BukkitMcmePlayer(Bukkit.getPlayer(playerName));
    }

    public McmeBackendPlayer getPlayer(Player player) {
        return new BukkitMcmePlayer(player);
    }

    @Override
    public McmeCommandSender getConsole() {
        return new BukkitMcmeCommandSender(Bukkit.getConsoleSender());
    }

}
