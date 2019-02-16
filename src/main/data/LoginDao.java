package main.data;

import main.models.DbResult;
import main.models.User;
import main.models.UserType;

import java.sql.*;

import static main.constants.DbConstants.*;

public class LoginDao {

    public DbResult<User> login(String login, String password) {
        User user = null;
        DbResult<User> result = new DbResult<>();

        int hash = password.hashCode();
        try {
            user = getUser(login, hash);
            if (user == null) {
                result.setAuthError();
            }
        } catch (ClassNotFoundException e) {
            result.setConnectionError();
        } catch (SQLException e) {
            result.setSqlException();
        }
        result.setValue(user);
        return result;
    }

    private User getUser(String login, int passwordHash) throws ClassNotFoundException, SQLException {
        String dbRequest = "select * from user where login=\'" + login + "\' and password_hash=" + passwordHash;
        Class.forName("com.mysql.jdbc.Driver");
        Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/customs?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "root");
        PreparedStatement statement = myConn.prepareStatement(dbRequest);
        ResultSet rs = statement.executeQuery();
        if (rs.next()) {
            UserType type = UserType.getFromString(rs.getString(TYPE));
            if (type != null) {
                return new User(rs.getString(LOGIN), rs.getInt(PASSWORD_HASH), type);
            } else {
                return null;
            }
        }
        return null;
    }
}
