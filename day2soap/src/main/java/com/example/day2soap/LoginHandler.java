package com.example.day2soap;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class LoginHandler {

    Boolean loginStatus = false;

    @PostMapping("/api/login")
    public void loginUser(@RequestBody User user) {
        loginStatus = false;
        try {
            DBHandler dBhandler = new DBHandler();
            loginStatus = dBhandler.searchUser(user.getUsername(), user.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/api/login/status")
    public Boolean isLoggedIn() {
        return loginStatus;
    }
    
}
