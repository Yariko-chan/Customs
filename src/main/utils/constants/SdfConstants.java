package main.utils.constants;

import java.text.SimpleDateFormat;

public class SdfConstants {

    private SdfConstants() {
        // only static methods
    }

    public static SimpleDateFormat INPUT_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    public static SimpleDateFormat DB_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    public static SimpleDateFormat BIRTH_DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");
}
