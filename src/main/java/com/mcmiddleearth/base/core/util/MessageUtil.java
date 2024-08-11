package com.mcmiddleearth.base.core.util;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;

import java.util.LinkedList;
import java.util.List;

public class MessageUtil {

    public static Component addBaseColor(TextColor baseColor, Component message) {
        message = message.colorIfAbsent(baseColor);
        List<Component> processedChildren = new LinkedList<>();
        for(Component child: message.children()) {
            processedChildren.add(addBaseColor(baseColor, child));
        }
        return message.children(processedChildren);
    }
}
