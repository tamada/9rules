package com.github.ninerules.utils;

import java.util.logging.Logger;

public class LoggingHelper {
    private LoggingHelper(){
    }

    public static <T> void throwing(Class<T> clazz, String method, Throwable throwable){
        String name = clazz.getName();
        Logger logger = Logger.getLogger(name);
        logger.throwing(name, method, throwable);
    }
}
