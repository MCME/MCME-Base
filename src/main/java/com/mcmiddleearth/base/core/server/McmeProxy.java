package com.mcmiddleearth.base.core.server;

import net.kyori.adventure.text.Component;

import java.util.Collection;

public interface McmeProxy {

    McmeServerInfo getServerInfo(String serverName);

    Collection<McmeServerInfo> getAllServerInfo();

    void stop(Component message);

    void broadcast(Component message);

    boolean sendPluginMessage(McmeServerInfo server, String channel, byte[] data, boolean queue);

}
