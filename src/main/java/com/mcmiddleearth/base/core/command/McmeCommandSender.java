package com.mcmiddleearth.base.core.command;

import com.mcmiddleearth.base.core.plugin.McmePlugin;
import com.mcmiddleearth.base.core.Style;
import net.kyori.adventure.text.Component;

public interface McmeCommandSender {

    boolean hasPermission(String permissionNode);

    String getName();

    McmePlugin getPlugin();

    default void sendInfo(Component message) {
        Component result = getPlugin().getMessagePrefix().append(Component.text(" ").color(Style.INFO));
        result = result.append(message.color(Style.INFO));
        sendMessage(result);
    }

    default void sendError(Component message) {
        Component result = getPlugin().getMessagePrefix().append(Component.text(" ").color(Style.ERROR));
        result = result.append(message.color(Style.ERROR));
        sendMessage(result);
    }

    void sendMessage(Component message);

}
