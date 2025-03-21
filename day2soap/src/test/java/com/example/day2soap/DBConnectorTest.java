package com.example.day2soap;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.junit.jupiter.api.Test;

public class DBConnectorTest {

    private static final String JDBC_URL = System.getenv("JDBC_DB_URL");
    private static final String USERNAME = System.getenv("JDBC_DB_USERNAME");
    private static final String PASSWORD = System.getenv("JDBC_DB_PASSWORD");

    @Test
    public void testDatabaseConnection() {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            assertNotNull(connection, "Database connection should not be null");
            System.out.println("Successfully connected to the database!");
        } catch (SQLException e) {
            throw new RuntimeException("Failed to connect to the database!", e);
        }
    }
}
