package com.example.day2soap;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBHandler {
    public void addUser(String username, String password, Connection conn) {
        String SQLcommand = "INSERT INTO users (username, user_password) VALUES (?, ?)";
        try (PreparedStatement insertStatement = conn.prepareStatement(SQLcommand)) {
            insertStatement.setString(1, username);
            insertStatement.setString(2, password);
            int rowChanges = insertStatement.executeUpdate();
            if (rowChanges == 1) {
                System.out.println("User created!");
            } else {
                System.out.println("Error creating user");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
