package com.example.day2soap.controller;

import org.springframework.web.bind.annotation.*;

import com.example.day2soap.model.Movie;
import com.example.day2soap.repository.DBConnector;

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

        try {
            DBHandler dBhandler = new DBHandler();
            movies = dBhandler.retrieveMovies(username);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok(movies);
    }
}
