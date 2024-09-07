package com.mcmiddleearth.base.core.command.argument;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

public abstract class AbstractStringSuggestionListArgumentType extends AbstractSuggestionListArgumentType<String> {

    @Override
    public String parse(StringReader reader) throws CommandSyntaxException {
        return reader.readUnquotedString();
    }

}
