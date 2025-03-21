package com.example.day2soap.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
    private static final String URL = System.getenv("JDBC_DB_URL");
    private static final String USERNAME = System.getenv("JDBC_DB_USERNAME");
    private static final String PASSWORD = System.getenv("JDBC_DB_PASSWORD");
    public static Connection instance;
    
    // Return a new connection (singleton pattern)
    public static Connection getConnection() throws SQLException {
        if (instance == null || instance.isClosed()) {
            try {
                instance = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException("Database connection failed!");
            }
        }
        return instance;
    }
}
