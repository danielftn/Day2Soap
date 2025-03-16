package com.example.day2soap;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import java.util.ArrayList;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class HistoryHandler {

    List<Movie> movies;

    @PostMapping("/api/history")
    public void getUserHistory(@RequestBody User user) {
        try {
            DBHandler dBhandler = new DBHandler();
            movies = dBhandler.retrieveMovies(user.getUsername());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/api/history/status")
    public List<Movie> returnUserHistory () {
        return movies;
    }
    
}
