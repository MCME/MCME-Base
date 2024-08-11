package com.mcmiddleearth.base.core.command;

import com.mcmiddleearth.base.core.plugin.McmePlugin;
import com.mcmiddleearth.base.core.Style;
import com.mcmiddleearth.base.core.util.MessageUtil;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;

public interface McmeCommandSender {

    boolean hasPermission(String permissionNode);

    String getName();

    McmePlugin getPlugin();

    default void sendInfo(Component message) {
        sendPrefixedMessage(Style.INFO, message);
    }

    default void sendError(Component message) {
        sendPrefixedMessage(Style.ERROR, message);
    }

    default void sendPrefixedMessage(TextColor color, Component message) {
        sendMessage(color, getPlugin().getMessagePrefix().append(message));
    }

    default void sendMessage(TextColor baseColor, Component message) {
        sendMessage(MessageUtil.addBaseColor(baseColor, message));
    }

    void sendMessage(Component message);
}
