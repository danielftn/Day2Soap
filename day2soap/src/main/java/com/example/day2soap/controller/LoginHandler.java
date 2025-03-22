package com.example.day2soap.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.example.day2soap.model.User;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

// initialize CORS
@CrossOrigin(origins = "https://day2soap.vercel.app/")
@RestController
public class LoginHandler {

    private String loggedUsername;

    // handles post request when the frontend sends the user logins
    @PostMapping("/api/login")
    public void loginUser(@RequestBody User user) {
        Boolean loginStatus = false;
        try {
            // call to database
            DBHandler dBhandler = new DBHandler();
            // check if user password combination exists
            loginStatus = dBhandler.searchUser(user.getUsername(), user.getPassword());
            if (loginStatus) {
                loggedUsername = user.getUsername();
            }
            else {
                loggedUsername = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // handles get request when the frontend asks if the login was successful
    @GetMapping("/api/login/status")
    public User isLoggedIn() {
        return new User(loggedUsername, null);
    }
    
}
