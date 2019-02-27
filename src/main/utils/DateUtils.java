package main.utils;

import main.utils.constants.SdfConstants;

import java.sql.Date;
import java.text.ParseException;

public class DateUtils {

    private DateUtils() {
        // only static methods
    }

    public static Date parseDate(String birthdate) {
        if (birthdate == null) {
            return null;
        }
        try {
            java.util.Date date = SdfConstants.INPUT_DATE_FORMAT.parse(birthdate);
            return new Date(date.getTime());
        } catch (ParseException e) {
            return null;
        }
    }

    public static boolean isNullOrEmpty(Date date) {
        return date == null || date.getTime() <= 0;
    }
}
