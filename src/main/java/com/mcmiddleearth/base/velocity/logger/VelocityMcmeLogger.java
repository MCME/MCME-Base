package com.mcmiddleearth.base.velocity.logger;

import com.mcmiddleearth.base.core.logger.McmeLogger;
import org.slf4j.Logger;

public class VelocityMcmeLogger implements McmeLogger {

    private final Logger logger;
    private final String prefix;

    public VelocityMcmeLogger(Logger logger, String prefix) {
        this.logger = logger;
        this.prefix = prefix;
    }

    @Override
    public void trace(String message) {
        logger.trace(prefix+message);
    }

    @Override
    public void debug(String message) {
        logger.debug(prefix+message);
    }

    @Override
    public void info(String message) {
        logger.info(prefix+message);
    }

    @Override
    public void warn(String message) {
        logger.warn(prefix+message);
    }

    @Override
    public void error(String message) {
        logger.error(prefix+message);
    }

    @Override
    public void error(String message, Throwable thrown) {
        logger.error(prefix+message,thrown);
    }

}