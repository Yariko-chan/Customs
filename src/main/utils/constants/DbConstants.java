package main.utils.constants;

public class DbConstants {

    private DbConstants() {
        // only constants
    }

    public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    public static final String DB_PATH = "jdbc:mysql://localhost:3306/customs?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    public static final String DB_LOGIN = "root";
    public static final String DB_PASSWORD = "root";
    public static final int ERROR_RESULT = -1;

    public static final String LOGIN = "login";
    public static final String PASSWORD_HASH = "password_hash";
    public static final String TYPE = "type";
    public static final String ADMIN = "admin";
    public static final String USER = "user";

    public static final String PERSON_ID = "person_id";
    public static final String FIRST_NAME = "first_name";
    public static final String LAST_NAME = "last_name";
    public static final String PATRONYMIC = "patronymic";
    public static final String BIRTH_DATE = "birth_date";
    public static final String PASSPORT = "passport";
    public static final String COUNTRY = "country";
}
