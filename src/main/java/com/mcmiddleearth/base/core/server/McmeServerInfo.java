package com.mcmiddleearth.base.core.server;

import java.net.SocketAddress;

public interface McmeServerInfo {

    SocketAddress getSocketAddress();

    public String getName();
}
