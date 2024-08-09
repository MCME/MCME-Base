package com.mcmiddleearth.base.core.taskScheduling;

public interface Callback<T> {

    void done(T result, Throwable error);
}
