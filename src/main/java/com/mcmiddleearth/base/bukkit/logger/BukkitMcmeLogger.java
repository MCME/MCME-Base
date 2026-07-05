package com.mcmiddleearth.base.bukkit.logger;

import com.mcmiddleearth.base.core.logger.McmeLogger;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * java.util.logging-backed {@link McmeLogger} for Paper/Bukkit plugins (Paper's plugin
 * logger is a {@link Logger}). Previously extended the Bungee logger; made self-contained
 * when the Bungee layer was removed (Velocity-only network, 26.2 migration).
 */
public class BukkitMcmeLogger implements McmeLogger {

    private final Logger logger;

    public BukkitMcmeLogger(Logger logger) {
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
        logger.log(Level.SEVERE, message, thrown);
    }
}
