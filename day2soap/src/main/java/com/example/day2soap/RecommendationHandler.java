package com.example.day2soap;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;



@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class RecommendationHandler {

    

    @PostMapping("/api/recommendation")
    public void getRecommendation(@RequestBody UserPrompt prompt) {
        System.out.println(prompt.getFavouriteActor());
    }

}
