package com.mcmiddleearth.base.bukkit.taskScheduling;

import com.mcmiddleearth.base.core.taskScheduling.BackendTask;
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
    public void scheduleSync(long delayTicks) {
        task = Bukkit.getScheduler().runTaskLater(plugin, getRunnable(), delayTicks);
    }

    @Override
    public void scheduleAsync(long delayTicks) {
        task = Bukkit.getScheduler().runTaskLater(plugin, getRunnable(), delayTicks);
    }

    @Override
    public void scheduleSyncRepeating(long delayTicks, long periodTicks) {
        task = Bukkit.getScheduler().runTaskTimer(plugin, getRunnable(), delayTicks, periodTicks);
    }

    @Override
    public void scheduleAsyncRepeating(long delayTicks, long periodTicks) {
        task = Bukkit.getScheduler().runTaskTimerAsynchronously(plugin, getRunnable(), delayTicks, periodTicks);
    }

    @Override
    public void cancel() {
        if(task!=null) {
            task.cancel();
        }
    }

    public org.bukkit.scheduler.BukkitTask getTask() {
        return task;
    }
}
