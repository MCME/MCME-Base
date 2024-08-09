package com.mcmiddleearth.base.core.server;

import net.kyori.adventure.text.Component;

public interface McmeBackend {

    void stop(Component message);

    void broadcast(Component message);

}
