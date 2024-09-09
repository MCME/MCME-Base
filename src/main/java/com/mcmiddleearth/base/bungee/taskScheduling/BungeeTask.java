package com.mcmiddleearth.base.bungee.taskScheduling;

import com.mcmiddleearth.base.core.taskScheduling.Task;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.scheduler.ScheduledTask;

import java.util.concurrent.TimeUnit;

public class BungeeTask extends Task {

    private final Plugin plugin;

    private ScheduledTask task;

    public BungeeTask(Plugin plugin, Runnable task) {
        super(task);
        this.plugin = plugin;
    }

    @Override
    public Task schedule(long delay, TimeUnit timeUnit) {
        task = ProxyServer.getInstance().getScheduler().schedule(plugin, getRunnable(), delay, timeUnit);
        return this;
    }

    @Override
    public Task scheduleRepeating(long delay, long period, TimeUnit timeUnit) {
        task = ProxyServer.getInstance().getScheduler().schedule(plugin, getRunnable(), delay, period, timeUnit);
        return this;
    }

    @Override
    public Task cancel() {
        if(task!=null) {
            task.cancel();
        }
        return this;
    }

    public ScheduledTask getBungeeTask() {
        return task;
    }
}
