package com.mcmiddleearth.base.core.taskScheduling;

import java.util.concurrent.TimeUnit;

public abstract class BackendTask extends Task {

    public BackendTask(Runnable task) {
        super(task);
    }

    @Override
    public Task schedule(long delay, TimeUnit timeUnit) {
        scheduleSync(toServerTicks(delay,timeUnit));
        return this;
    }

    public abstract Task scheduleSync(long delayTicks);

    public Task scheduleAsync(long delay, TimeUnit timeUnit) {
        scheduleAsync(toServerTicks(delay,timeUnit));
        return this;
    }

    public abstract Task scheduleAsync(long delayTicks);

    @Override
    public Task scheduleRepeating(long delay, long period, TimeUnit timeUnit) {
        scheduleSyncRepeating(toServerTicks(delay,timeUnit),toServerTicks(period,timeUnit));
        return this;
    }

    public abstract Task scheduleSyncRepeating(long delayTicks, long periodTicks);

    public Task scheduleAsyncRepeating(long delay, long period, TimeUnit timeUnit) {
        scheduleAsyncRepeating(toServerTicks(delay,timeUnit),toServerTicks(period,timeUnit));
        return this;
    }

    public abstract Task scheduleAsyncRepeating(long delayTicks, long periodTicks);

    private long toServerTicks(long time, TimeUnit timeUnit) {
        return timeUnit.toMillis(time)/50;
    }

}
