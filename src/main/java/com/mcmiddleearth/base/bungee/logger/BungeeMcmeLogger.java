package com.mcmiddleearth.base.bungee.logger;

import com.mcmiddleearth.base.core.logger.McmeLogger;

import java.util.logging.Level;
import java.util.logging.Logger;

public class BungeeMcmeLogger implements McmeLogger {

    private final Logger logger;
    private final String prefix;

    public BungeeMcmeLogger(Logger logger, String prefix) {
        this.logger = logger;
        this.prefix = prefix;
    }

    @Override
    public void trace(String message) {
        logger.finest(prefix+message);
    }

    @Override
    public void debug(String message) {
        logger.fine(prefix+message);
    }

    @Override
    public void info(String message) {
        logger.info(prefix+message);
    }

    @Override
    public void warn(String message) {
        logger.warning(prefix+message);
    }

    @Override
    public void error(String message) {
        logger.severe(prefix+message);
    }

    @Override
    public void error(String message, Throwable thrown) {
        logger.log(Level.SEVERE,prefix+message,thrown);
    }
}
