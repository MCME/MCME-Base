package com.mcmiddleearth.base.adventure;

import com.mcmiddleearth.base.core.message.MessageColor;
import com.mcmiddleearth.base.core.message.MessageDecoration;
import com.mcmiddleearth.base.core.message.MessageStyle;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class AdventureUtils {

    public static Style buildStyle(MessageStyle style) {
        return Style.style(buildColor(style.getColor()), buildDecorations(style.getDecorations()));
    }

    public static TextColor buildColor(MessageColor color) {
        return switch (color) {
            case WHITE -> NamedTextColor.WHITE;
            case GRAY -> NamedTextColor.GRAY;
            case DARK_GRAY -> NamedTextColor.DARK_GRAY;
            case BLACK -> NamedTextColor.BLACK;
            case BLUE -> NamedTextColor.BLUE;
            case DARK_BLUE -> NamedTextColor.DARK_BLUE;
            case AQUA -> NamedTextColor.AQUA;
            case DARK_AQUA -> NamedTextColor.DARK_AQUA;
            case GREEN -> NamedTextColor.GREEN;
            case DARK_GREEN -> NamedTextColor.DARK_GREEN;
            case LIGHT_PURPLE -> NamedTextColor.LIGHT_PURPLE;
            case DARK_PURPLE -> NamedTextColor.DARK_PURPLE;
            case RED -> NamedTextColor.RED;
            case DARK_RED -> NamedTextColor.DARK_RED;
            case YELLOW -> NamedTextColor.YELLOW;
            case GOLD -> NamedTextColor.GOLD;
            case CUSTOM -> TextColor.color(color.getRed(),color.getGreen(),color.getBlue());
        };
    }

    public static TextDecoration[] buildDecorations(MessageDecoration[] decorations) {
        return Arrays.stream(decorations).map(AdventureUtils::buildDecoration).toArray(TextDecoration[]::new);
    }

    public static TextDecoration buildDecoration(MessageDecoration decoration) {
        return switch(decoration) {
            case BOLD -> TextDecoration.BOLD;
            case ITALIC -> TextDecoration.ITALIC;
            case STRIKETHROUGH -> TextDecoration.STRIKETHROUGH;
            case UNDERLINED -> TextDecoration.UNDERLINED;
            case OBFUSCATED -> TextDecoration.OBFUSCATED;
        };
    }

    public static Component addBaseColor(TextColor baseColor, Component message) {
        message = message.colorIfAbsent(baseColor);
        List<Component> processedChildren = new LinkedList<>();
        for(Component child: message.children()) {
            processedChildren.add(addBaseColor(baseColor, child));
        }
        return message.children(processedChildren);
    }

}
