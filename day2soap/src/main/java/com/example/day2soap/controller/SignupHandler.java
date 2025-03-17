package com.example.day2soap.controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.day2soap.model.User;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class SignupHandler {
    
    private Boolean signupStatus = false;

    public Boolean getSignupStatus() {
        return signupStatus;
    }

    public void setSignupStatus(Boolean signupStatus) {
        this.signupStatus = signupStatus;
    }

    @PostMapping("/api/signup")
    public void signUserUp(@RequestBody User user) {
        try {
            DBHandler dBhandler = new DBHandler();
            signupStatus = dBhandler.addUser(user.getUsername(), user.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @GetMapping("/api/signup/status")
    public Boolean isSignedIn() {
        return signupStatus;
    }
}
