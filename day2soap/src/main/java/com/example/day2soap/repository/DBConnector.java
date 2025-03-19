package com.example.day2soap.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
    private static final String URL = "jdbc:mysql://localhost:3306/Day2Soapdb";
    private static final String USERNAME = "user";
    private static final String PASSWORD = "ucalgary";
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
