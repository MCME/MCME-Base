package com.mcmiddleearth.base.core.server;

import com.mcmiddleearth.base.core.message.Message;

public interface McmeServer {

    void stop(Message message);

    void broadcast(Message message);


}
