package org.pba;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";
    private final Connection connection;
    private static final String USER = "root";
    private static final String PASS = "123456";

    public DatabaseConnection(String serverURI) throws SQLException, ClassNotFoundException {
        Class.forName(DRIVER_CLASS_NAME);
        connection = DriverManager.getConnection(serverURI, USER, PASS);
    }

    public Connection getConnection() {
        return connection;
    }
}
