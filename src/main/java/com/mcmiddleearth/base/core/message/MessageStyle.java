package com.mcmiddleearth.base.core.message;

public class MessageStyle {

    public static final MessageDecoration[] DEFAULT_DECORATION = new MessageDecoration[]{};
    public static final MessageStyle DEFAULT = new MessageStyle(McmeColors.DEFAULT, DEFAULT_DECORATION);

    private MessageColor color;
    private MessageDecoration[] decorations;

    public MessageStyle(MessageDecoration... decorations) {
        this.decorations = decorations;
    }

    public MessageStyle(MessageColor color) {
        this.color = color;
        decorations = new MessageDecoration[0];
    }

    public MessageStyle(MessageColor color, MessageDecoration... decorations) {
        this.color = color;
        this.decorations = decorations;
    }

    public MessageColor getColor() {
        return color;
    }

    public void setColor(MessageColor color) {
        this.color = color;
    }

    public MessageDecoration[] getDecorations() {
        return decorations;
    }

    public void setDecorations(MessageDecoration... decorations) {
        this.decorations = decorations;
    }
}
