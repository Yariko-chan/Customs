package main.utils;

import main.utils.constants.Constants;

import javax.servlet.ServletContext;
import java.sql.Date;
import java.util.HashMap;

public class StringUtils {

    private StringUtils() {
        // only static methods
    }

    public static boolean isNullOrEmpty(String name) {
        return name == null || name.isEmpty();
    }

    public static boolean isNullOrEmpty(Date date) {
        return date == null || date.getTime() <= 0;
    }

    public static boolean isCorrectCountry(ServletContext ctx, String country) {
        HashMap countriesMap = (HashMap) ctx.getAttribute(Constants.COUNTRIES_MAP);
        return country != null && countriesMap != null && countriesMap.containsKey(country);
    }

    public static boolean isValidUnp(String unp) {
        return unp.length() == 9 && unp.matches("\\d{9}");
    }
}
