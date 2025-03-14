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
import org.springframework.stereotype.Service;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class RecommendationHandler {
    private final ChatClient.Builder builder;

    public RecommendationHandler(ChatClient.Builder builder) {
        this.builder = builder;
    }

    @PostMapping("/api/recommendation")
    public void getRecommendation(@RequestBody UserPrompt prompt) {
        String promptString = "Give me 5 movies based on my preferences. I like " + prompt.getFavouriteMovie().getTitle()
         + " and " + prompt.getFavouriteActor() + ". I prefer movies directed by " + prompt.getFavouriteDirector() + 
         " and produced in the " + prompt.getProductionDecade() + ". I prefer movies with a " + prompt.getMpaRating() + 
         " rating and a length of " + prompt.getProductionLength() + ". List the movies including title between asterisks, year in parentheses, and description in quotes.";


        System.out.println(promptString);
        System.out.println(askLLM(builder, promptString));
    }

    public String askLLM(ChatClient.Builder builder, String prompt) {
        var client = builder.build();
        return client.prompt(prompt)
                      .call()
                      .content(); // Returns the response content
    }

}
