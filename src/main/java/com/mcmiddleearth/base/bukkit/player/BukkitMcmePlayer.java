package com.mcmiddleearth.base.bukkit.player;

import com.mcmiddleearth.base.adventure.AdventureMessage;
import com.mcmiddleearth.base.bukkit.command.BukkitMcmeCommandSender;
import com.mcmiddleearth.base.core.message.Message;
import com.mcmiddleearth.base.core.player.McmeBackendPlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class BukkitMcmePlayer extends BukkitMcmeCommandSender implements McmeBackendPlayer {

    private final Player player;

    public BukkitMcmePlayer(Player player) {
        this.player = player;
    }

    @Override
    public void sendDataToProxy(String channel, byte[] data, boolean queue) {

    }

    @Override
    public UUID getUniqueId() {
        return getBukkitPlayer().getUniqueId();
    }

    public Player getBukkitPlayer() {
        return (Player) getBukkitCommandSender();
    }

    @Override
    public void disconnect(Message message) {
        getBukkitPlayer().kick(((AdventureMessage)message).getComponent());
    }


    @Override
    public boolean equals(Object other) {
        return other instanceof BukkitMcmePlayer otherPlayer
                && otherPlayer.player.equals(player);
    }

    @Override
    public CommandSender getBukkitCommandSender() {
        return player;
    }
}
