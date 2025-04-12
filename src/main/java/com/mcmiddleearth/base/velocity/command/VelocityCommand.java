package com.mcmiddleearth.base.velocity.command;

import com.mcmiddleearth.base.core.command.McmeCommandSender;
import com.mcmiddleearth.base.core.command.SimpleTabCompleteRequest;
import com.mcmiddleearth.base.core.command.TabCompleteRequest;
import com.mcmiddleearth.base.core.command.handler.AbstractCommandHandler;
import com.mcmiddleearth.base.velocity.server.VelocityMcmeProxy;
import com.velocitypowered.api.command.SimpleCommand;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Command tab-executor for Velocity plugins:
 * Register as command in your Velocity plugin
 */
public class VelocityCommand implements SimpleCommand {

    private final String permission;
    private final AbstractCommandHandler handler;

    public VelocityCommand(AbstractCommandHandler handler, String permission) {
        this.handler = handler;
        this.permission = permission;
    }

    @Override
    public void execute(Invocation invocation) {
        McmeCommandSender sender = VelocityMcmeProxy.getMcmeCommandSender(invocation.source());
        String[] args = invocation.arguments();
        handler.handle(sender, args);
    }

    @Override
    public CompletableFuture<List<String>> suggestAsync(Invocation invocation) {
        return CompletableFuture.supplyAsync(()->{
            TabCompleteRequest request = new SimpleTabCompleteRequest(VelocityMcmeProxy.getMcmeCommandSender(invocation.source()),
                    String.format("/%s %s",invocation.alias(),String.join(" ",invocation.arguments())));
            handler.onTabComplete(request);
            return request.getSuggestions();
        });
    }

    @Override
    public boolean hasPermission(Invocation invocation) {
        return permission==null || invocation.source().hasPermission(permission);
    }
}
