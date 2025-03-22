package com.example.day2soap.controller;

import org.springframework.web.bind.annotation.*;

import com.example.day2soap.model.Movie;
import com.example.day2soap.repository.DBConnector;

import org.springframework.http.ResponseEntity;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// initialize CORS
@CrossOrigin(origins = "https://day2soap.vercel.app/")
@RestController
public class HistoryHandler {

    // handles get request when the frontend asks for the history
    @GetMapping("/api/history")
    public ResponseEntity<List<Movie>> getHistory(@RequestParam String username) {
        List<Movie> movies = new ArrayList<>();

        // Prevent null or empty usernames
        if (username == null || username.trim().isEmpty()) {
            return ResponseEntity.badRequest().body(movies);
        }

        try {
            // call to database 
            DBHandler dBhandler = new DBHandler();
            // store movies from database into arraylist
            movies = dBhandler.retrieveMovies(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // send OK response
        return ResponseEntity.ok(movies);
    }
}
