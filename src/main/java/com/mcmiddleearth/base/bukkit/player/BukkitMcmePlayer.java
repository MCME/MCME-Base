package com.mcmiddleearth.base.bukkit.player;

import com.mcmiddleearth.base.adventure.AdventureMessage;
import com.mcmiddleearth.base.bukkit.command.BukkitMcmeCommandSender;
import com.mcmiddleearth.base.core.message.Message;
import com.mcmiddleearth.base.core.player.McmeBackendPlayer;
import org.bukkit.entity.Player;

import java.util.UUID;

public class BukkitMcmePlayer extends BukkitMcmeCommandSender implements McmeBackendPlayer {


    public BukkitMcmePlayer(Player player) {
        super(player);
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

}
