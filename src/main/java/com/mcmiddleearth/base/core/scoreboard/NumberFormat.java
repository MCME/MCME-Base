package com.mcmiddleearth.base.core.scoreboard;

import org.jetbrains.annotations.NotNull;

public class NumberFormat{

    static @NotNull NumberFormat blank();
    static @NotNull FixedFormat fixed(@NotNull ComponentLike component);
    static @NotNull StyledFormat noStyle();
    static @NotNull StyledFormat styled(@NotNull Style style);
    static @NotNull StyledFormat styled(@NotNull StyleBuilderApplicable @NotNull ... styleBuilderApplicables);
}
