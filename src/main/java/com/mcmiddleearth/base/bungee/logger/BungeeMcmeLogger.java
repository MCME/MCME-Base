package com.mcmiddleearth.base.bungee.logger;

import com.mcmiddleearth.base.core.logger.McmeLogger;

import java.util.logging.Level;
import java.util.logging.Logger;

public class BungeeMcmeLogger implements McmeLogger {

    private final Logger logger;

    public BungeeMcmeLogger(Logger logger) {
        this.logger = logger;
    }

    @Override
    public void trace(String message) {
        logger.finest(message);
    }

    @Override
    public void debug(String message) {
        logger.fine(message);
    }

    @Override
    public void info(String message) {
        logger.info(message);
    }

    @Override
    public void warn(String message) {
        logger.warning(message);
    }

    @Override
    public void error(String message) {
        logger.severe(message);
    }

    @Override
    public void error(String message, Throwable thrown) {
        logger.log(Level.SEVERE,message,thrown);
    }
}
