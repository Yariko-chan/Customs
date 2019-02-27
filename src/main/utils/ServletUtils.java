package main.utils;

import javax.servlet.ServletConfig;

import static main.utils.constants.Constants.COUNTRIES_MAP;

import java.util.HashMap;

public class ServletUtils {

    private ServletUtils() {
//        only static methods
    }

    public static String getCountry(ServletConfig config, String code) {
        HashMap<String, String> countriesMap = (HashMap<String, String>)
                config.getServletContext().getAttribute(COUNTRIES_MAP);
        String country = countriesMap.get(code);
        if (country == null) {
            country = code;
        }
        return country;
    }
}
