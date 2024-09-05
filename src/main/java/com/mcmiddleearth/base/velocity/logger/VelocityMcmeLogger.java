package com.mcmiddleearth.base.velocity.logger;

import com.mcmiddleearth.base.core.logger.McmeLogger;
import org.slf4j.Logger;

public record VelocityMcmeLogger(Logger logger) implements McmeLogger {

    @Override
    public void trace(String message) {
        logger.trace(message);
    }

    @Override
    public void debug(String message) {
        logger.debug(message);
    }

    @Override
    public void info(String message) {
        logger.info(message);
    }

    @Override
    public void warn(String message) {
        logger.warn(message);
    }

    @Override
    public void error(String message) {
        logger.error(message);
    }

    @Override
    public void error(String message, Throwable thrown) {
        logger.error(message, thrown);
    }

}
