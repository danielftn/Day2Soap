package com.example.day2soap.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.example.day2soap.model.User;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

@CrossOrigin(origins = "https://day2soap.vercel.app/")
@RestController
public class LoginHandler {

    private String loggedUsername;

    @PostMapping("/api/login")
    public void loginUser(@RequestBody User user) {
        Boolean loginStatus = false;
        try {
            DBHandler dBhandler = new DBHandler();
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

    @GetMapping("/api/login/status")
    public User isLoggedIn() {
        return new User(loggedUsername, null);
    }
    
}
