package main.model.data;

import main.model.entities.DbResult;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static main.utils.constants.DbConstants.*;

abstract class Dao<T> {

    abstract String getTableName();

    abstract String getIdColumnName();

    abstract String createSaveRequest(T t);

    abstract T parseResultSetToModel(ResultSet rs) throws SQLException;

    DbResult<Boolean> saveSingle(T t) {
        DbResult<Boolean> result = new DbResult<>();
        boolean value = false;
        try {
            String dbRequest = createSaveRequest(t);
            int savedCount = request(dbRequest);
            if (savedCount > 0) value = true;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            result.setConnectionError();
        } catch (SQLException e) {
            e.printStackTrace();
            result.setSqlException();
        }
        result.setValue(value);
        return result;
    }

    DbResult<List<T>> getAll() {
        DbResult<List<T>> result = new DbResult<>();
        List<T> list = new ArrayList<>();

        try {
            String dbRequest = "select * from " + getTableName();
            ResultSet rs = requestData(dbRequest);
            while (rs.next()) {
                list.add(parseResultSetToModel(rs));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            result.setConnectionError();
        } catch (SQLException e) {
            e.printStackTrace();
            result.setSqlException();
        }
        result.setValue(list);
        return result;
    }

    DbResult<T> getSingle(int id) {
        DbResult<T> result = new DbResult<>();
        T p = null;

        try {
            String dbRequest = "select * from " + getTableName() + " where " + getTableName() + "." + getIdColumnName() + "=" + id;
            ResultSet rs = requestData(dbRequest);
            if (rs.next()) {
                p = parseResultSetToModel(rs);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            result.setConnectionError();
        } catch (SQLException e) {
            e.printStackTrace();
            result.setSqlException();
        }
        result.setValue(p);
        return result;
    }

    int request(String dbRequest) throws ClassNotFoundException, SQLException {
        Class.forName(JDBC_DRIVER);
        Connection myConn = DriverManager.getConnection(DB_PATH, DB_LOGIN, DB_PASSWORD);
        PreparedStatement statement = myConn.prepareStatement(dbRequest);
        return statement.executeUpdate();
    }

    ResultSet requestData(String dbRequest) throws ClassNotFoundException, SQLException {
        Class.forName(JDBC_DRIVER);
        Connection myConn = DriverManager.getConnection(DB_PATH, DB_LOGIN, DB_PASSWORD);
        PreparedStatement statement = myConn.prepareStatement(dbRequest);
        return statement.executeQuery();
    }
}
