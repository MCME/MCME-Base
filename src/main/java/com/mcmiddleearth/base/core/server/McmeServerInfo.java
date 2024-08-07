package com.mcmiddleearth.base.core.server;

import java.net.SocketAddress;

public class McmeServerInfo {

    private final SocketAddress socketAddress;
    private final String name;

    public McmeServerInfo(SocketAddress socketAddress, String name) {
        this.socketAddress = socketAddress;
        this.name = name;
    }

    public SocketAddress getSocketAddress() {
        return socketAddress;
    }

    public String getName() {
        return name;
    }
}
