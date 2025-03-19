package com.example.day2soap.controller;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.day2soap.model.Movie;
import com.example.day2soap.repository.DBConnector;

import java.util.ArrayList;

public class DBHandler {

    public Boolean addUser(String username, String password) throws SQLException {
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

    public Boolean searchUser(String username, String password) throws SQLException {
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

    public List<Movie> retrieveMovies(String username) throws SQLException {
        List<Movie> movies = new ArrayList<Movie>();
        String sql = "SELECT movie_title, release_year, description, watched FROM recommended_movies WHERE user = ?";
        try (Connection conn = DBConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY)) {

            pstmt.setString(1, username);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    movies.add(new Movie(
                        rs.getString("movie_title"),
                        rs.getInt("release_year"),
                        rs.getString("description"),
                        rs.getBoolean("watched")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movies;
    }

    public void updateMovies(String username, String movieTitle, boolean watched) throws SQLException{

        String sql = "UPDATE recommended_movies SET watched = ? WHERE user = ? AND movie_title = ?";
        try (Connection conn = DBConnector.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setBoolean(1, watched);
            pstmt.setString(2, username);
            pstmt.setString(3, movieTitle);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
