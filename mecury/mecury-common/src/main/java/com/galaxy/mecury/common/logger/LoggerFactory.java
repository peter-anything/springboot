package com.galaxy.mecury.common.logger;

import com.galaxy.mecury.common.logger.support.FailsafeLogger;

import java.io.File;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LoggerFactory {
    private static final ConcurrentHashMap<String, FailsafeLogger> LOGGERS = new ConcurrentHashMap<>();
    private static volatile LoggerAdapter LOGGER_ADAPTER;

    static {
        String logger = System.getProperty("dubbo.application.logger", "");
        switch (logger) {

        }
    }

    private LoggerFactory() {
    }

    public static void setLoggerAdapter(String loggerAdapter) {
//        if (loggerAdapter != null && loggerAdapter.length() > 0) {
//            setLoggerAdapter(ExtensionLoader);
//        }
    }

    public static void setLoggerAdapter(LoggerAdapter loggerAdapter) {
        if (loggerAdapter != null) {
            Logger logger = loggerAdapter.getLogger(LoggerFactory.class.getName());
            logger.info("using logger: " + loggerAdapter.getClass().getName());
            LoggerFactory.LOGGER_ADAPTER = loggerAdapter;
            for (Map.Entry<String, FailsafeLogger> entry : LOGGERS.entrySet()) {
                entry.getValue().setLogger(LOGGER_ADAPTER.getLogger(entry.getKey()));
            }
        }
    }

    /**
     * Get logger provider
     *
     * @param key the returned logger will be named after clazz
     * @return logger
     */
    public static Logger getLogger(Class<?> key) {
        return LOGGERS.computeIfAbsent(key.getName(), name -> new FailsafeLogger(LOGGER_ADAPTER.getLogger(name)));
    }

    /**
     * Get logger provider
     *
     * @param key the returned logger will be named after key
     * @return logger provider
     */
    public static Logger getLogger(String key) {
        return LOGGERS.computeIfAbsent(key, k -> new FailsafeLogger(LOGGER_ADAPTER.getLogger(k)));
    }

    /**
     * Get logging level
     *
     * @return logging level
     */
    public static Level getLevel() {
        return LOGGER_ADAPTER.getLevel();
    }

    /**
     * Set the current logging level
     *
     * @param level logging level
     */
    public static void setLevel(Level level) {
        LOGGER_ADAPTER.setLevel(level);
    }

    /**
     * Get the current logging file
     *
     * @return current logging file
     */
    public static File getFile() {
        return LOGGER_ADAPTER.getFile();
    }
}
