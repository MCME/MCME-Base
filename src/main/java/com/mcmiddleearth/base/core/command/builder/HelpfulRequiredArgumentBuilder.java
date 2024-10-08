package com.mcmiddleearth.base.core.command.builder;

import com.mcmiddleearth.base.core.command.McmeCommandSender;
import com.mcmiddleearth.base.core.command.argument.HelpfulArgumentType;
import com.mcmiddleearth.base.core.command.node.HelpfulArgumentNode;
import com.mcmiddleearth.base.core.message.Message;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import com.mojang.brigadier.tree.ArgumentCommandNode;
import com.mojang.brigadier.tree.CommandNode;

public class HelpfulRequiredArgumentBuilder<T> extends ArgumentBuilder<McmeCommandSender, HelpfulRequiredArgumentBuilder<T>> {
    private String helpText;
    private Message tooltip;
    private final String name;
    private final ArgumentType<T> type;
    private SuggestionProvider<McmeCommandSender> suggestionsProvider = null;

    private HelpfulRequiredArgumentBuilder(final String name, final ArgumentType<T> type) {
        this.name = name;
        this.type = type;
        this.helpText = "";
        this.tooltip = null;
        if(type instanceof HelpfulArgumentType && ((HelpfulArgumentType)type).getTooltip()!=null) {
            tooltip = ((HelpfulArgumentType)type).getTooltip();
        }
    }

    /**
     * Create a new argument command node with help text.
     * @param name name of the node
     * @return a HelpfulLiteralBuilder object to build the node.
     */
    public static <T> HelpfulRequiredArgumentBuilder<T> argument(final String name, final ArgumentType<T> type) {
        return new HelpfulRequiredArgumentBuilder<>(name, type);
    }

    public HelpfulRequiredArgumentBuilder<T> suggests(final SuggestionProvider<McmeCommandSender> provider) {
        this.suggestionsProvider = provider;
        return getThis();
    }

    /**
     * Add a help text to a command node. It will be displayed in command help messages. Help texts are inherited
     * to child nodes.
     * Example: /report <player> <reason> : >Here the help text is displayed<
     * @param helpText text to display in help messages.
     * @return this HelpfulRequiredArgumentBuilder
     */
    public HelpfulRequiredArgumentBuilder<T> withHelpText(String helpText) {
        this.helpText = helpText;
        return getThis();
    }

    /**
     * Add a tooltip to a command node. It will be displayed when a user hovers over a command help message.
     * @param tooltip tooltip to display
     * @return  this HelpfulRequiredArgumentBuilder
     */
    public HelpfulRequiredArgumentBuilder<T> withTooltip(Message tooltip) {
        this.tooltip = tooltip;
        if(type instanceof HelpfulArgumentType && ((HelpfulArgumentType)type).getTooltip() != null) {
            ((HelpfulArgumentType)type).setTooltip(tooltip);
        }
        return getThis();
    }

    public SuggestionProvider<McmeCommandSender> getSuggestionsProvider() {
        return suggestionsProvider;
    }

    @Override
    protected HelpfulRequiredArgumentBuilder<T> getThis() {
        return this;
    }

    public ArgumentType<T> getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public ArgumentCommandNode<McmeCommandSender, T> build() {
        final ArgumentCommandNode<McmeCommandSender, T> result = new HelpfulArgumentNode<>(getName(), getType(), getCommand(),
                                                                    getRequirement(), getRedirect(), getRedirectModifier(),
                                                                    isFork(), getSuggestionsProvider(), helpText, tooltip);

        for (final CommandNode<McmeCommandSender> argument : getArguments()) {
            result.addChild(argument);
        }

        return result;
    }
}
