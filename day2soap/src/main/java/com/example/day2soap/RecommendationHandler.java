package com.example.day2soap;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.*;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class RecommendationHandler {
    private final ChatClient.Builder builder;

    public RecommendationHandler(ChatClient.Builder builder) {
        this.builder = builder;
    }

    // Get the recommendation data from frontend and convert it into a String to ask Gemini. Returns a list of Movie objects as a JSON to frontend
    @PostMapping("/api/recommendation")
    public ResponseEntity<List<Movie>> getRecommendation(@RequestBody UserPrompt prompt) {
        // Create a prompt string based on the user's preferences for the LLM 
        System.out.println("Getting recommendation...\n");

        StringBuilder builderString = new StringBuilder("List me 5 movies based on my preferences. I like " + prompt.getFavouriteMovie().getTitle() + " and " + prompt.getFavouriteGenre() + " movies. ");
        if(prompt.getFavouriteActor().isEmpty() == false){
            builderString.append("My favourite actor is " + prompt.getFavouriteActor() + ". ");
        }
        if(prompt.getFavouriteDirector().isEmpty() == false){
            builderString.append("I prefer movies directed by " + prompt.getFavouriteDirector() + ". ");
        }
        builderString.append("I prefer movies produced in the " + prompt.getProductionDecade() + " with a " + prompt.getMpaRating() + " rating and a length of " + prompt.getProductionLength() +
         ". List the movies including title between asterisks, year in parentheses, and description in quotes.");

        String promptString = builderString.toString();
        
        String response = askLLM(builder, promptString);
        List<Movie> movies = parseMovies(response);    

        for(Movie movie : movies){
            System.out.println(movie.getTitle());
        }

    // **Save movies to database only if user is logged in**
    if (prompt.getUsername() != null && !prompt.getUsername().isEmpty()) {
        saveMoviesToDatabase(prompt.getUsername(), movies);
    }

    return ResponseEntity.ok(movies);
}

    // **Save Generated Movies to Database**
    private void saveMoviesToDatabase(String username, List<Movie> movies) {
        Connection conn = DBConnector.getConnection();
        String sql = "INSERT INTO recommended_movies (user, movie_title, release_year, description) VALUES (?, ?, ?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            for (Movie movie : movies) {
                pstmt.setString(1, username);
                pstmt.setString(2, movie.getTitle());
                pstmt.setInt(3, movie.getYear());
                pstmt.setString(4, movie.getDescription());
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Movie> parseMovies(String response){
        // Create a pattern to match the movie information
        Pattern pattern = Pattern.compile("\\*{3}(.*?)\\*{3} \\((\\d{4})\\) \"(.*?)\"");
        Matcher matcher = pattern.matcher(response);

        // Create a list to store the movies
        List<Movie> movies = new ArrayList<Movie>();

        // Iterate through the matches and create a Movie object for each match
        while (matcher.find()) {
            String title = matcher.group(1);
            int year = Integer.parseInt(matcher.group(2));
            String description = matcher.group(3);
            Movie movie = new Movie(title, year, description);
            movies.add(movie);
        }

        return movies;
    }

    public String askLLM(ChatClient.Builder builder, String prompt) {
        var client = builder.build();
        return client.prompt(prompt)
                      .call()
                      .content(); // Returns the response content
    }

}