package com.mcmiddleearth.base.core.server;

import java.util.Collection;

public interface McmeProxy {

    McmeServerInfo getServerInfo(String serverName);

    Collection<McmeServerInfo> getAllServerInfo();


}
