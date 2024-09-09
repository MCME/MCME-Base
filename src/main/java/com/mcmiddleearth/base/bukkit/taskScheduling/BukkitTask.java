package com.mcmiddleearth.base.bukkit.taskScheduling;

import com.mcmiddleearth.base.core.taskScheduling.BackendTask;
import com.mcmiddleearth.base.core.taskScheduling.Task;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class BukkitTask extends BackendTask {

    private final Plugin plugin;

    private org.bukkit.scheduler.BukkitTask task;

    public BukkitTask(Plugin plugin, Runnable task) {
        super(task);
        this.plugin = plugin;
    }

    @Override
    public Task scheduleSync(long delayTicks) {
        task = Bukkit.getScheduler().runTaskLater(plugin, getRunnable(), delayTicks);
        return this;
    }

    @Override
    public Task scheduleAsync(long delayTicks) {
        task = Bukkit.getScheduler().runTaskLater(plugin, getRunnable(), delayTicks);
        return this;
    }

    @Override
    public Task scheduleSyncRepeating(long delayTicks, long periodTicks) {
        task = Bukkit.getScheduler().runTaskTimer(plugin, getRunnable(), delayTicks, periodTicks);
        return this;
    }

    @Override
    public Task scheduleAsyncRepeating(long delayTicks, long periodTicks) {
        task = Bukkit.getScheduler().runTaskTimerAsynchronously(plugin, getRunnable(), delayTicks, periodTicks);
        return this;
    }

    @Override
    public Task cancel() {
        if(task!=null) {
            task.cancel();
        }
        return this;
    }

    public org.bukkit.scheduler.BukkitTask getBukkitTask() {
        return task;
    }
}
