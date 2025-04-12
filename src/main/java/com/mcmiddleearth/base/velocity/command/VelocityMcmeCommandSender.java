package com.mcmiddleearth.base.velocity.command;

import com.mcmiddleearth.base.adventure.AdventureMessage;
import com.mcmiddleearth.base.core.command.McmeCommandSender;
import com.mcmiddleearth.base.core.message.Message;
import com.velocitypowered.api.command.CommandSource;
import net.kyori.adventure.identity.Identity;

public abstract class VelocityMcmeCommandSender implements McmeCommandSender {

    @Override
    public boolean hasPermission(String permissionNode) {
        return getVelocityCommandSource().hasPermission(permissionNode);
    }

    @Override
    public String getName() {
        return getVelocityCommandSource().pointers().getOrDefault(Identity.NAME,"Console");
    }

    @Override
    public void sendMessage(Message message) {
        getVelocityCommandSource().sendMessage(((AdventureMessage)message).getComponent());
    }

    public abstract CommandSource getVelocityCommandSource();
}
