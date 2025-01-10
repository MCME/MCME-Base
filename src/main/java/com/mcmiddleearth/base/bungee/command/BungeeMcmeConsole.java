package com.mcmiddleearth.base.bungee.command;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;

public class BungeeMcmeConsole extends BungeeMcmeCommandSender {

    @Override
    protected CommandSender getBungeeCommandSender() {
        return ProxyServer.getInstance().getConsole();
    }
}
