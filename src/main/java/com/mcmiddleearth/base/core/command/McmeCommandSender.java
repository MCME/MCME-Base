package com.mcmiddleearth.base.core.command;

import com.mcmiddleearth.base.core.message.Message;

public interface McmeCommandSender {

    boolean hasPermission(String permissionNode);

    String getName();

    void sendMessage(Message message);
}
