package com.example.day2soap.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.example.day2soap.dto.UserPrompt;
import com.example.day2soap.model.Movie;
import com.example.day2soap.repository.DBConnector;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.http.ResponseEntity;
import java.util.regex.*;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Connection;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class RecommendationHandler {
    private final ChatClient.Builder builder;

    public RecommendationHandler(ChatClient.Builder builder) {
        this.builder = builder;
    }

    // Get the recommendation data from frontend and convert it into a String to ask Gemini. Returns a list of Movie objects as a JSON to frontend
    @PostMapping("/api/recommendation")
    public ResponseEntity<List<Movie>> getRecommendation(@RequestBody UserPrompt prompt) throws SQLException {
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
         ". List the movies including title between 3 asterisks, year in parentheses, and description in quotes.");

        String promptString = builderString.toString();
        
        String response = askLLM(builder, promptString);
        List<Movie> movies = parseMovies(response);    

        System.out.println("Recommended movies: " + response + "\n\n");

        for(Movie movie : movies){
            System.out.println(movie.getTitle());
        }

    // **Save movies to database only if user is logged in**
    if (prompt.getUser().getUsername() != null && !prompt.getUser().getUsername().isEmpty()) {
        saveMoviesToDatabase(prompt.getUser().getUsername(), movies);
    }

    return ResponseEntity.ok(movies);
}

    // **Save Generated Movies to Database**
    private void saveMoviesToDatabase(String username, List<Movie> movies) throws SQLException {
        try {
            DBHandler dBhandler = new DBHandler();
            dBhandler.addMovies(username, movies);
        } catch (Exception e) {
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
            Movie movie = new Movie(title, year, description, false);
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