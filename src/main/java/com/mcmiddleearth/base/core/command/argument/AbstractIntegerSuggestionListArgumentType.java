package com.mcmiddleearth.base.core.command.argument;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

public abstract class AbstractIntegerSuggestionListArgumentType extends AbstractSuggestionListArgumentType<Integer> {

    @Override
    public Integer parse(StringReader reader) throws CommandSyntaxException {
        return Integer.parseInt(reader.readUnquotedString());
    }

}
