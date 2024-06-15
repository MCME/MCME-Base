package com.mcmiddleearth.base.core.player;

public interface McmeBackendPlayer extends McmePlayer {

    void sendDataToProxy(String channel, byte[] data, boolean queue);

}
