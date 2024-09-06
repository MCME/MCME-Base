package com.mcmiddleearth.base.core.server;

import com.mcmiddleearth.base.core.taskScheduling.Callback;

import java.net.SocketAddress;

public interface McmeServerInfo {

    SocketAddress getSocketAddress();

    String getName();

    void ping(Callback<McmeServerPing> callback);

    boolean sendPluginMessage(String channel, byte[] data, boolean queue);


}
