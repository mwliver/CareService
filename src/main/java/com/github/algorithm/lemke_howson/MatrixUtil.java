package com.github.algorithm.lemke_howson;

/**
 * Copyright & Author
 * michal
 */
public class MatrixUtil {

    public static Double valueOf(String val) {
        return val.substring(0, 2).equals("--") ? new Double(val.substring(2, val.length() - 1)) : new Double(val);
    }
}
