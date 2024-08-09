package com.mcmiddleearth.base.core.player;

import com.mcmiddleearth.base.core.server.McmeServerInfo;
import com.mcmiddleearth.base.core.taskScheduling.Callback;

import java.net.SocketAddress;

public interface McmeProxyPlayer extends McmePlayer {

    SocketAddress getSocketAddress();

    void sendDataToBackend(String channel, byte[] data, boolean queue);

    void connect(McmeServerInfo target, Callback<Boolean> callback);

    McmeServerInfo getServerInfo();
}
