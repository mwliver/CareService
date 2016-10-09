package com.github.util;

import java.util.concurrent.TimeUnit;

/**
 * Copyright & Author
 * michal
 */
public class Util {

    public static String format(long duration) {
        long miliseconds = TimeUnit.MILLISECONDS.toMillis(duration);
        return String.format("%dms", miliseconds);
    }
}
