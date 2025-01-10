package com.mcmiddleearth.base.bungee.command;

import com.google.common.base.Joiner;
import com.mcmiddleearth.base.bungee.AbstractBungeePlugin;
import com.mcmiddleearth.base.bungee.server.BungeeMcmeProxy;
import com.mcmiddleearth.base.core.command.McmeCommandSender;
import com.mcmiddleearth.base.core.command.SimpleTabCompleteRequest;
import com.mcmiddleearth.base.core.command.TabCompleteRequest;
import com.mcmiddleearth.base.core.command.handler.AbstractCommandHandler;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.TabExecutor;

/**
 * Command tab-executor for Bungee plugins:
 * Register as command in your Bungee plugin
 */
public class BungeeCommand extends Command implements TabExecutor {

    AbstractCommandHandler commandHandler;
    AbstractBungeePlugin plugin;

    public BungeeCommand(AbstractBungeePlugin plugin, String command, AbstractCommandHandler handler) {
        super(command);
        this.plugin = plugin;
        commandHandler = handler;
    }

    @Override
    public void execute(CommandSender commandSender, String[] args) {
        McmeCommandSender wrappedSender = ((BungeeMcmeProxy)plugin.getMcmeProxy())
                                                                  .getMcmeCommandSender(commandSender);
        commandHandler.handle(wrappedSender, args);
    }

    @Override
    public Iterable<String> onTabComplete(CommandSender commandSender, String[] args) {
        McmeCommandSender wrappedSender = ((BungeeMcmeProxy)plugin.getMcmeProxy())
                                                                  .getMcmeCommandSender(commandSender);
        TabCompleteRequest request = new SimpleTabCompleteRequest(wrappedSender,
                String.format("/%s %s", commandHandler.getCommand(), Joiner.on(' ').join(args)));
        commandHandler.onTabComplete(request);
        return request.getSuggestions();
    }

}
