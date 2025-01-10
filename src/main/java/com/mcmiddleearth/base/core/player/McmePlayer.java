package com.mcmiddleearth.base.core.player;

import com.mcmiddleearth.base.core.command.McmeCommandSender;
import com.mcmiddleearth.base.core.message.Message;

import java.util.UUID;

/**
 * McmePlayers are not unique. Don't compare with == but use .equals()
 */
public interface McmePlayer extends McmeCommandSender {

    UUID getUniqueId();

    void disconnect(Message message);

}
