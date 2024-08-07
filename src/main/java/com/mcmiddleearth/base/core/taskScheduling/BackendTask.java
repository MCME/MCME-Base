package com.mcmiddleearth.base.core.taskScheduling;

import java.util.concurrent.TimeUnit;

public abstract class BackendTask extends Task {

    public BackendTask(Runnable task) {
        super(task);
    }

    @Override
    public void schedule(long delay, TimeUnit timeUnit) {
        scheduleSync(toServerTicks(delay,timeUnit));
    }

    public abstract void scheduleSync(long delayTicks);

    public void scheduleAsync(long delay, TimeUnit timeUnit) {
        scheduleAsync(toServerTicks(delay,timeUnit));
    }

    public abstract void scheduleAsync(long delayTicks);

    @Override
    public void scheduleRepeating(long delay, long period, TimeUnit timeUnit) {
        scheduleSyncRepeating(toServerTicks(delay,timeUnit),toServerTicks(period,timeUnit));
    }

    public abstract void scheduleSyncRepeating(long delayTicks, long periodTicks);

    public void scheduleAsyncRepeating(long delay, long period, TimeUnit timeUnit) {
        scheduleAsyncRepeating(toServerTicks(delay,timeUnit),toServerTicks(period,timeUnit));
    }

    public abstract void scheduleAsyncRepeating(long delayTicks, long periodTicks);

    private long toServerTicks(long time, TimeUnit timeUnit) {
        return timeUnit.toMillis(time)/50;
    }

}
