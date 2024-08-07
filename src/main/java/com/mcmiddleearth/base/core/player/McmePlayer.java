package com.mcmiddleearth.base.core.player;

import com.mcmiddleearth.base.core.command.McmeCommandSender;
import net.kyori.adventure.text.Component;

import java.util.UUID;

public interface McmePlayer extends McmeCommandSender {

    UUID getUniqueId();

    void disconnect(Component message);

}
