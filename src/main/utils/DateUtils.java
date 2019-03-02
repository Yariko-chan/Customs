package main.utils;

import javafx.util.Pair;
import main.utils.constants.SdfConstants;

import java.sql.Date;
import java.text.ParseException;
import java.util.Calendar;

public class DateUtils {

    private DateUtils() {
        // only static methods
    }

    public static Date parseSqlDate(String birthdate) {
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

    public static java.util.Date parseJavaUtilDate(String birthdate) {
        if (birthdate == null) {
            return null;
        }
        try {
            return SdfConstants.INPUT_DATE_FORMAT.parse(birthdate);
        } catch (ParseException e) {
            return null;
        }
    }

    public static boolean isNullOrEmpty(Date date) {
        return date == null || date.getTime() <= 0;
    }

    public static Pair<Date, Date> getLastMonthPeriod() {
        java.util.Date current = new java.util.Date();
        Date to = new Date(current.getTime());
        Calendar c = Calendar.getInstance();
        c.setTime(current);
        c.add(Calendar.MONTH, -1);
        Date from = new Date(c.getTime().getTime());
        return new Pair<>(from, to);
    }
}
