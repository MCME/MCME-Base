package com.mcmiddleearth.base.core.scoreboard;

import com.mcmiddleearth.base.core.message.Message;
import com.mcmiddleearth.base.core.message.MessageStyle;
import org.jetbrains.annotations.NotNull;

public class NumberFormat{

    public static @NotNull NumberFormat blank() {
        return new NumberFormat();
    }
    public static @NotNull NumberFormat fixed(@NotNull Message component) {
        return new NumberFormat();
    }
    public static @NotNull NumberFormat noStyle() {
        return new NumberFormat();
    }

    public static @NotNull NumberFormat styled(@NotNull MessageStyle style) {
        return new NumberFormat();
    }

    public static @NotNull NumberFormat styled(@NotNull MessageStyle @NotNull ... styleBuilderApplicables) {
        return new NumberFormat();
    }

    public NumberFormat() {
    }
}
