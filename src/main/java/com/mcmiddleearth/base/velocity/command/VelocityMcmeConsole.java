package com.mcmiddleearth.base.velocity.command;

import com.mcmiddleearth.base.VelocityBasePlugin;
import com.velocitypowered.api.command.CommandSource;

public class VelocityMcmeConsole extends VelocityMcmeCommandSender {

    @Override
    public CommandSource getVelocityCommandSource() {
        return VelocityBasePlugin.getInstance().getProxyServer().getConsoleCommandSource();
    }
}
