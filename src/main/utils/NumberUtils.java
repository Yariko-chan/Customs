package main.utils;

import main.utils.constants.Constants;

public class NumberUtils {

    private NumberUtils() {
    }

    public static float parseFloat(String s) {
        if (s == null || s.isEmpty()) {
            return Constants.ERROR;
        }
        try {
            return Float.parseFloat(s);
        } catch (NumberFormatException e) {
            return Constants.ERROR;
        }
    }

    public static int parseInt(String s) {
        if (s == null || s.isEmpty()) {
            return Constants.ERROR;
        }
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return Constants.ERROR;
        }
    }
}
