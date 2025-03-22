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

    // add user to database from the signup
    public Boolean addUser(String username, String password) throws SQLException {
        // establish connection
        Connection conn = DBConnector.getConnection();
        String SQLcommand = "INSERT INTO users (username, user_password) VALUES (?, ?)";
        try (PreparedStatement insertStatement = conn.prepareStatement(SQLcommand)) {
            insertStatement.setString(1, username);
            insertStatement.setString(2, password);
            int rowChanges = insertStatement.executeUpdate();
            // check if the single user was added to the database
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

    // search a user on the database and check if their username and password match for the login
    public Boolean searchUser(String username, String password) throws SQLException {
         // establish connection
        Connection conn = DBConnector.getConnection();
        String SQLcommand = "SELECT * FROM users WHERE username = ? and user_password = ?";
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

    // retrieve movies belonging to a specific user for the history tab
    public List<Movie> retrieveMovies(String username) throws SQLException {
        List<Movie> movies = new ArrayList<Movie>();
        String sql = "SELECT movie_title, release_year, description, watched FROM recommended_movies WHERE user = ?";
        try (
            // establish connection
            Connection conn = DBConnector.getConnection();
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

    // update the watched status of a movie for a specific user for the movie list
    public void updateMovies(String username, String movieTitle, boolean watched) throws SQLException{

        String sql = "UPDATE recommended_movies SET watched = ? WHERE user = ? AND movie_title = ?";
        // establish connection
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

    // add movies to the database after gemini gives the recommendations
    public void addMovies(String username, List<Movie> movies) {
        // SQL to check if the movie already exists in the database
        String checkSql = "SELECT COUNT(*) FROM recommended_movies WHERE user = ? AND movie_title = ?";
        String insertSql = "INSERT INTO recommended_movies (user, movie_title, release_year, description) VALUES (?, ?, ?, ?)";

        // establish connection
        try (Connection conn = DBConnector.getConnection();
            PreparedStatement checkPstmt = conn.prepareStatement(checkSql);
            PreparedStatement insertPstmt = conn.prepareStatement(insertSql)) {

            for (Movie movie : movies) {
                // Check if the movie already exists
                checkPstmt.setString(1, username);
                checkPstmt.setString(2, movie.getTitle());
                
                ResultSet rs = checkPstmt.executeQuery();
                if (rs.next() && rs.getInt(1) == 0) { // If the movie does not exist (count = 0)
                    // Movie doesn't exist, so insert it
                    insertPstmt.setString(1, username);
                    insertPstmt.setString(2, movie.getTitle());
                    insertPstmt.setInt(3, movie.getYear());
                    insertPstmt.setString(4, movie.getDescription());
                    insertPstmt.executeUpdate();
                } else {
                    System.out.println("Movie already exists: " + movie.getTitle());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
