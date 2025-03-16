package com.example.day2soap;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class HistoryHandler {

    @GetMapping("/api/history")
    public ResponseEntity<List<Movie>> getHistory(@RequestParam String username) {
        List<Movie> movies = new ArrayList<>();

        // Prevent null or empty usernames
        if (username == null || username.trim().isEmpty()) {
            return ResponseEntity.badRequest().body(movies);
        }

        String sql = "SELECT movie_title, release_year, description FROM recommended_movies WHERE user = ?";
        try (Connection conn = DBConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY)) {

            pstmt.setString(1, username);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    movies.add(new Movie(
                        rs.getString("movie_title"),
                        rs.getInt("release_year"),
                        rs.getString("description")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }

        return ResponseEntity.ok(movies);
    }
}
