package com.mcmiddleearth.base.bungee.command.argument;

import com.mcmiddleearth.base.core.command.argument.AbstractPlayerArgumentType;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;

import java.util.Collection;
import java.util.stream.Collectors;

public class BungeeProxiedPlayerArgumentType extends AbstractPlayerArgumentType {

    @Override
    public String parse(StringReader reader) throws CommandSyntaxException {
        return reader.readUnquotedString();
    }

    @Override
    protected Collection<String> getPlayerSuggestions() {
        return ProxyServer.getInstance().getPlayers().stream().map(CommandSender::getName).collect(Collectors.toList());
    }
}
