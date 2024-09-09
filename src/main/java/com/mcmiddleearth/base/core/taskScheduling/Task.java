package com.mcmiddleearth.base.core.taskScheduling;

import java.util.concurrent.TimeUnit;

public abstract class Task {

    private final Runnable runnable;

    public Task(Runnable runnable) {
        this.runnable = runnable;
    }

    public Runnable getRunnable() {
        return runnable;
    }

    public abstract Task schedule(long delay, TimeUnit timeUnit);

    public abstract Task scheduleRepeating(long delay, long period, TimeUnit timeUnit);

    public abstract Task cancel();
}
