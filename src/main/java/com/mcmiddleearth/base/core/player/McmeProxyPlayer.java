package com.mcmiddleearth.base.core.player;

import java.net.SocketAddress;

public interface McmeProxyPlayer extends McmePlayer {

    SocketAddress getSocketAddress();

    void sendDataToBackend(String channel, byte[] data, boolean queue);
}
