package com.mcmiddleearth.base.core.message;

public enum MessageColor {

    CUSTOM,
    WHITE,
    GRAY,
    DARK_GRAY,
    YELLOW,
    GOLD,
    RED,
    DARK_RED,
    AQUA,
    DARK_AQUA,
    GREEN,
    DARK_GREEN,
    BLUE,
    DARK_BLUE,
    LIGHT_PURPLE,
    DARK_PURPLE,
    BLACK;

    private int red,green,blue;

    public int getRed() {
        return red;
    }

    public int getGreen() {
        return green;
    }

    public int getBlue() {
        return blue;
    }

    private MessageColor setRGB(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        return this;
    }

    public static MessageColor rgb(int red, int green, int blue) {
        return MessageColor.CUSTOM.setRGB(red, green, blue);
    }
}
