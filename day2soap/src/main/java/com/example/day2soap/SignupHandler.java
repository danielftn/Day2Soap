package com.example.day2soap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class SignupHandler {
    
    @PostMapping("/api/signup")
    public void signUserUp(@RequestBody User user) {
        System.out.println(user.getUsername());
    }
}
