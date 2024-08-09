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
    public void schedule(long delay, TimeUnit timeUnit) {
        task = ProxyServer.getInstance().getScheduler().schedule(plugin, getRunnable(), delay, timeUnit);
    }

    @Override
    public void scheduleRepeating(long delay, long period, TimeUnit timeUnit) {
        task = ProxyServer.getInstance().getScheduler().schedule(plugin, getRunnable(), delay, period, timeUnit);
    }

    @Override
    public void cancel() {
        if(task!=null) {
            task.cancel();
        }
    }

    public ScheduledTask getTask() {
        return task;
    }
}
