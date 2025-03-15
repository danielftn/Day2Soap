package com.example.day2soap;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBHandler {
    public Boolean addUser(String username, String password) {
        Connection conn = DBConnector.getConnection();
        String SQLcommand = "INSERT INTO users (username, user_password) VALUES (?, ?)";
        try (PreparedStatement insertStatement = conn.prepareStatement(SQLcommand)) {
            insertStatement.setString(1, username);
            insertStatement.setString(2, password);
            int rowChanges = insertStatement.executeUpdate();
            if (rowChanges == 1) {
                System.out.println("User created!");
                return true;
            } else {
                System.out.println("Error creating user");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Error creating user: duplicate user");
            return false;
        }
    }

    public Boolean searchUser(String username, String password) {
        Connection conn = DBConnector.getConnection();
        String SQLcommand = "SELECT * FROM day2soapdb.users WHERE username = ? and user_password = ?";
        try (PreparedStatement selectStatement = conn.prepareStatement(SQLcommand)) {
            selectStatement.setString(1, username);
            selectStatement.setString(2, password);
            ResultSet rows = selectStatement.executeQuery();
            if (rows.isBeforeFirst()) {
                System.out.println("User found!");
                return true;
            } else {
                System.out.println("User not found");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


}
