package com.module.util.logging;

import java.util.logging.Logger;

public class AppLogger {
    public static Logger getLogger(Class<?> clazz) {
        return Logger.getLogger(clazz.getName());
    }
}
