package com.mcmiddleearth.base.bukkit.player;

import com.mcmiddleearth.base.bukkit.command.BukkitMcmeCommandSender;
import com.mcmiddleearth.base.core.plugin.McmePlugin;
import com.mcmiddleearth.base.core.player.McmeBackendPlayer;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.bungeecord.BungeeComponentSerializer;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerKickEvent;

import java.util.UUID;

public class BukkitMcmePlayer extends BukkitMcmeCommandSender implements McmeBackendPlayer {


    public BukkitMcmePlayer(McmePlugin plugin, Player player) {
        super(plugin, player);
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
    public void disconnect(Component message) {
        getBukkitPlayer().kick(message);
    }

}
