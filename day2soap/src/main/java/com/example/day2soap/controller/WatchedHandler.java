package com.example.day2soap.controller;

import org.springframework.web.bind.annotation.*;

import com.example.day2soap.dto.MovieRequest;
import com.example.day2soap.repository.DBConnector;

import org.springframework.http.ResponseEntity;
import java.sql.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class WatchedHandler {
    
    @PutMapping("/api/watched")
    public ResponseEntity<String> setWatched(@RequestBody MovieRequest request) {
        String username = request.getUsername();
        String movieTitle = request.getMovie().getTitle();
        boolean watched = request.getMovie().isWatched();
        
        // Prevent null or empty usernames or movie titles
        if (username == null || username.trim().isEmpty() || movieTitle == null || movieTitle.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Invalid username or movie title");
        }
        
        String sql = "UPDATE recommended_movies SET watched = ? WHERE user = ? AND movie_title = ?";
        try (Connection conn = DBConnector.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setBoolean(1, watched);
            pstmt.setString(2, username);
            pstmt.setString(3, movieTitle);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Failed to update watched status");
        }
        
        return ResponseEntity.ok("Successfully updated watched status");
    }
}
