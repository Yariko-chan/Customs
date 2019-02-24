package main.model.data;

import java.sql.*;

import static main.utils.constants.DbConstants.*;
import static main.utils.constants.DbConstants.DB_PASSWORD;

public abstract class Dao {

    protected int request(String dbRequest) throws ClassNotFoundException, SQLException {
        Class.forName(JDBC_DRIVER);
        Connection myConn = DriverManager.getConnection(DB_PATH, DB_LOGIN, DB_PASSWORD);
        PreparedStatement statement = myConn.prepareStatement(dbRequest);
        return statement.executeUpdate();
    }

    protected ResultSet requestData(String dbRequest) throws ClassNotFoundException, SQLException {
        Class.forName(JDBC_DRIVER);
        Connection myConn = DriverManager.getConnection(DB_PATH, DB_LOGIN, DB_PASSWORD);
        PreparedStatement statement = myConn.prepareStatement(dbRequest);
        return statement.executeQuery();
    }
}
