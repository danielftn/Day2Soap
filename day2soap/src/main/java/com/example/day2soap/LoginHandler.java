package com.example.day2soap;

import java.sql.Connection;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class LoginHandler {
    @PostMapping("/api/login")
    public Boolean loginUser(@RequestBody User user) {
        Boolean result = false;
        try {
            DBHandler dBhandler = new DBHandler();
            result = dBhandler.searchUser(user.getUsername(), user.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
