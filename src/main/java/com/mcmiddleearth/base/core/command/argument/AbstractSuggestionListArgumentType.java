package com.mcmiddleearth.base.core.command.argument;

import com.mcmiddleearth.base.adventure.AdventureMessage;
import com.mcmiddleearth.base.core.message.Message;
import com.mojang.brigadier.LiteralMessage;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;

import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.CompletableFuture;

public abstract class AbstractSuggestionListArgumentType <T> implements ArgumentType<T>, HelpfulArgumentType {

    private Message tooltip = new AdventureMessage().add("Choose an item from list.");

    @Override
    public Collection<String> getExamples() {
        return Collections.singletonList("anything");
    }

    @Override
    public <S> CompletableFuture<Suggestions> listSuggestions(final CommandContext<S> context, final SuggestionsBuilder builder) {
        for (String option : getSuggestions()) {
            if (option.toLowerCase().startsWith(builder.getRemaining().toLowerCase())) {
                if(tooltip == null) {
                    builder.suggest(option);
                } else {
                    builder.suggest(option, new LiteralMessage(tooltip.toString()));
                }
            }
        }
        return builder.buildFuture();
    }

    protected abstract Collection<String> getSuggestions();

    @Override
    public void setTooltip(Message tooltip) {
        this.tooltip = tooltip;
    }

    @Override
    public Message getTooltip() {
        return tooltip;
    }
}
