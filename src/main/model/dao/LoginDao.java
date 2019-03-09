package main.model.dao;

import main.model.entities.DbResult;
import main.model.entities.User;
import main.model.entities.UserType;

import java.sql.*;

import static main.utils.constants.DbConstants.*;

public class LoginDao extends Dao<User> {


    DbResult<User> login(String login, String password) {
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

    User getUser(String login, int passwordHash) throws ClassNotFoundException, SQLException {
        String dbRequest = "select * from user where login=\'" + login + "\' and password_hash=" + passwordHash;
        ResultSet rs = requestData(dbRequest);
        if (rs.next()) {
            return parseResultSetToModel(rs);
        }
        return null;
    }

    @Override
    String getTableName() {
        return "user";
    }


    @Override
    DbResult<User> getSingle(int id) {
        throw new UnsupportedOperationException("Operation not supported yet, no id column for table provided");
    }

    @Override
    String getIdColumnName() {
        throw new UnsupportedOperationException("Operation not supported yet, no id column for table provided");
    }

    @Override
    String createSaveRequest(User user) {
        throw new UnsupportedOperationException();
    }

    @Override
    User parseResultSetToModel(ResultSet rs) throws SQLException {
        UserType type = UserType.getFromString(rs.getString(TYPE));
        if (type != null) {
            return new User(rs.getString(LOGIN), rs.getInt(PASSWORD_HASH), type);
        } else {
            return null;
        }
    }
}
