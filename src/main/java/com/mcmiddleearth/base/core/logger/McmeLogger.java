package com.mcmiddleearth.base.core.logger;

public interface McmeLogger {

    void trace(String message);
    void debug(String message);
    void info(String message);
    void warn(String message);
    void error(String message);
    void error(String message, Throwable thrown);
}
