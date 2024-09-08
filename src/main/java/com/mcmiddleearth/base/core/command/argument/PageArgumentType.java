/*
 * Copyright (C) 2020 MCME
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.mcmiddleearth.base.core.command.argument;

import com.mcmiddleearth.base.adventure.AdventureMessage;
import com.mcmiddleearth.base.core.message.Message;
import com.mojang.brigadier.LiteralMessage;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;

import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

/**
 * @author Eriol_Eandur
 */

public class PageArgumentType implements ArgumentType<Integer>, HelpfulArgumentType {

    private Message tooltip = new AdventureMessage().add("Number of page you want to see.");

    private final Function<CommandContext, Collection<String>> listProvider;
    private final int pageSize;

    public PageArgumentType(Function<CommandContext, Collection<String>> listProvider, int pageSize) {
        this.listProvider = listProvider;
        this.pageSize = pageSize;
    }

    @Override
    public Integer parse(StringReader reader) throws CommandSyntaxException {
        String o = reader.readUnquotedString();
        try {
            int page = Integer.parseInt(o);
            if(page>0) {
                return page;
            }
        } catch(NumberFormatException ignore){}
        throw new CommandSyntaxException(new SimpleCommandExceptionType(new LiteralMessage("Failed parsing of PageArgument")),
                new LiteralMessage("Page must be an integer > 0"));
    }

    @Override
    public Collection<String> getExamples() {
        return Arrays.asList(new String[]{"1","2","3"}.clone());
    }

    @Override
    public <S> CompletableFuture<Suggestions> listSuggestions(final CommandContext<S> context, final SuggestionsBuilder builder) {
        int maxPage = Math.max((listProvider.apply(context).size()-1) / pageSize + 1,1);
        for (int i = 1; i <= maxPage; i++) {
            if ((""+i).toLowerCase().startsWith(builder.getRemaining().toLowerCase())) {
                if(tooltip == null) {
                    builder.suggest(""+i);
                } else {
                    builder.suggest(""+i, new LiteralMessage(tooltip.toString()));
                }
            }
        }
        return builder.buildFuture();
    }

    @Override
    public void setTooltip(Message tooltip) {
        this.tooltip = tooltip;
    }

    @Override
    public Message getTooltip() {
        return tooltip;
    }
}
