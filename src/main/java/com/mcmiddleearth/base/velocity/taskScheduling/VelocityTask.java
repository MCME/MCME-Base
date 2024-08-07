package com.mcmiddleearth.base.velocity.taskScheduling;

import com.mcmiddleearth.base.core.taskScheduling.Task;
import com.mcmiddleearth.base.velocity.AbstractVelocityPlugin;
import com.velocitypowered.api.scheduler.ScheduledTask;

import java.util.concurrent.TimeUnit;

public class VelocityTask extends Task {

    private final AbstractVelocityPlugin plugin;

    private ScheduledTask task;

    public VelocityTask(AbstractVelocityPlugin plugin, Runnable runnable) {
        super(runnable);
        this.plugin = plugin;
    }

    @Override
    public void schedule(long delay, TimeUnit timeUnit) {
        task = plugin.getProxyServer().getScheduler().buildTask(plugin, getRunnable()).delay(delay, timeUnit).schedule();
    }

    @Override
    public void scheduleRepeating(long delay, long period, TimeUnit timeUnit) {
        task = plugin.getProxyServer().getScheduler().buildTask(plugin, getRunnable())
                .delay(delay, timeUnit)
                .repeat(period, timeUnit).schedule();
    }

    @Override
    public void cancelTask() {
        task.cancel();
    }
}
