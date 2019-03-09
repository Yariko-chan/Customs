package main.utils.constants;

public class DbConstants {

    private DbConstants() {
        // only constants
    }

    public static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String DB_PATH = "jdbc:mysql://localhost:3306/customs?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    public static final String DB_LOGIN = "root";
    public static final String DB_PASSWORD = "root";

    public static final String LOGIN = "login";
    public static final String PASSWORD_HASH = "password_hash";
    public static final String TYPE = "type";
    public static final String ADMIN = "admin";
    public static final String USER = "user";

    public static final String PERSON = "person";
    public static final String PERSON_ID = "person_id";
    public static final String FIRST_NAME = "first_name";
    public static final String LAST_NAME = "last_name";
    public static final String PATRONYMIC = "patronymic";
    public static final String BIRTH_DATE = "birth_date";
    public static final String PASSPORT = "passport";
    public static final String COUNTRY = "country";

    public static final String INDIVIDUAL_SHIPMENTS = "individual_shipments";
    public static final String DATE = "date";
    public static final String PRODUCT = "product";
    public static final String PRICE = "price";
    public static final String QUANTITY = "quantity";
    public static final String RECEIVER = "receiver";

    public static final String COMPANY = "company";
    public static final String COMPANY_ID = "company_id";
    public static final String NAME = "name";
    public static final String UNP = "UNP";
    public static final String LICENSE_NUM = "license_number";
    public static final String LICENSE_VALID_DATE = "license_valid_date";

    public static final String FOREIGN_COMPANY = "foreign_company";
    public static final String FOREIGN_COMPANY_ID = "foreign_company_id";

    public static final String CONTRACT_ID = "contract_id";
    public static final String CONTRACT_NUMBER = "contract_number";
    public static final String NATIONAL_COMPANY = "national_company";
}
