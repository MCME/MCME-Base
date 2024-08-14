package com.mcmiddleearth.base.bungee;

import com.mcmiddleearth.base.bungee.logger.BungeeMcmeLogger;
import com.mcmiddleearth.base.bungee.server.BungeeMcmeProxy;
import com.mcmiddleearth.base.bungee.taskScheduling.BungeeTask;
import com.mcmiddleearth.base.core.logger.McmeLogger;
import com.mcmiddleearth.base.core.plugin.McmeProxyPlugin;
import com.mcmiddleearth.base.core.server.McmeProxy;
import com.mcmiddleearth.base.core.taskScheduling.Task;
import net.kyori.adventure.platform.bungeecord.BungeeAudiences;
import net.md_5.bungee.api.plugin.Plugin;

public abstract class AbstractBungeePlugin extends Plugin implements McmeProxyPlugin {

    private BungeeAudiences adventure;
    private McmeLogger mcmeLogger;

    private final BungeeMcmeProxy mcmeProxy = new BungeeMcmeProxy(this);

    @Override
    public void onEnable() {
        adventure = BungeeAudiences.create(this);
        mcmeLogger = new BungeeMcmeLogger(getLogger());//, PlainTextComponentSerializer.plainText()
                                                    //.serialize(((AdventureMessage)getMessagePrefix()).getComponent()));
    }

    @Override
    public void onDisable() {
        adventure.close();
    }

    public Task getTask(Runnable task) {
        return new BungeeTask(this, task);
    }


    public BungeeAudiences getAdventure() {
            return adventure;
    }

    @Override
    public McmeProxy getMcmeProxy() {
        return mcmeProxy;
    }

    @Override
    public McmeLogger getMcmeLogger() {
        return mcmeLogger;
    }
}
