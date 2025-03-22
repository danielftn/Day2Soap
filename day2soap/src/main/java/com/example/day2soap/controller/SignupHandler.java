package com.example.day2soap.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.day2soap.model.User;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


// initialize CORS
@CrossOrigin(origins = "https://day2soap.vercel.app/")
@RestController
public class SignupHandler {
    
    private Boolean signupStatus = false;

    public Boolean getSignupStatus() {
        return signupStatus;
    }

    public void setSignupStatus(Boolean signupStatus) {
        this.signupStatus = signupStatus;
    }

    // handles post request when signup sends user credentials to make new login
    @PostMapping("/api/signup")
    public void signUserUp(@RequestBody User user) {
        try {
            // call to database
            DBHandler dBhandler = new DBHandler();
            // add new user logins to the database
            signupStatus = dBhandler.addUser(user.getUsername(), user.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    // handles get request when signup asks if a user was successfully signed up (added to the database)
    @GetMapping("/api/signup/status")
    public Boolean isSignedIn() {
        return signupStatus;
    }
}
