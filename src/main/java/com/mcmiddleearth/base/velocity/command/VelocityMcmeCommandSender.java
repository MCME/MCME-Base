package com.mcmiddleearth.base.velocity.command;

import com.mcmiddleearth.base.adventure.AdventureMessage;
import com.mcmiddleearth.base.core.command.McmeCommandSender;
import com.mcmiddleearth.base.core.message.Message;
import com.velocitypowered.api.command.CommandSource;
import net.kyori.adventure.identity.Identity;

public class VelocityMcmeCommandSender implements McmeCommandSender {

    private final CommandSource commandSource;

    public VelocityMcmeCommandSender(CommandSource commandSource) {
        this.commandSource = commandSource;
    }

    @Override
    public boolean hasPermission(String permissionNode) {
        return commandSource.hasPermission(permissionNode);
    }

    @Override
    public String getName() {
        return commandSource.pointers().getOrDefault(Identity.NAME,"Console");
    }

    @Override
    public void sendMessage(Message message) {
        commandSource.sendMessage(((AdventureMessage)message).getComponent());
    }

    public CommandSource getVelocityCommandSource() {
        return commandSource;
    }
}
