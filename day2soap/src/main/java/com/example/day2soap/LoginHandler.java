package com.example.day2soap;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class LoginHandler {

    String loggedUsername;

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
