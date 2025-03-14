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
import java.util.ArrayList;

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
        String promptString = "List me 5 movies based on my preferences. I like " + prompt.getFavouriteMovie().getTitle()
         + " and " + prompt.getFavouriteActor() + ". I prefer movies directed by " + prompt.getFavouriteDirector() + 
         " and produced in the " + prompt.getProductionDecade() + ". I prefer movies with a " + prompt.getMpaRating() + 
         " rating and a length of " + prompt.getProductionLength() + 
         ". List the movies including title between asterisks, year in parentheses, and description in quotes.";

        //  Here are the movies:
        //  1.  ***Ready Player One*** (2018) "In a near-future dystopia, a young man finds himself competing in a virtual reality treasure hunt for the ultimate prize, controlled by the game's late creator. A lot of action, mystery and adventure."
        //  2.  ***War Horse*** (2011) "Set during World War I, this film tells the story of a young man's bond with his horse and their eventual reunion amidst the horrors of war. A touching story of friendship and courage."
        //  3.  ***The Adventures of Tintin*** (2011) "Tintin, a young reporter, and his loyal dog Snowy are thrust into a world of high adventure as they pursue a lost model ship containing a secret that could lead to a great fortune. A classic adventure film."
        //  4.  ***Jurassic World*** (2015) "22 years after the events of Jurassic Park, Isla Nublar now features a fully functioning dinosaur theme park, Jurassic World, as seen by tens of thousands of tourists. However, when a new genetically modified hybrid dinosaur escapes, chaos erupts."
        //  5.  ***Real Steel*** (2011) "In the near future, where robot boxing is a popular sport, a washed-up fighter pilot and his estranged son team up to train a discarded robot for a championship bout. A futuristic movie with many action scenes."

        String response = askLLM(builder, promptString);

        List<Movie> movies = parseMovies(response);

        System.out.println(promptString);
        System.out.println(askLLM(builder, promptString));

        return ResponseEntity.ok(movies);
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
