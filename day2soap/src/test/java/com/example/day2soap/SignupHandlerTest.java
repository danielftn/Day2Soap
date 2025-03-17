package com.example.day2soap;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.junit.jupiter.api.Assertions;


import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import com.example.day2soap.controller.DBHandler;
import com.example.day2soap.controller.SignupHandler;
import com.example.day2soap.model.User;

class SignupHandlerTest {

    private MockMvc mockMvc;

    @Mock
    private DBHandler dbHandler;

    @InjectMocks
    private SignupHandler signupHandler; 

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(signupHandler).build();

        signupHandler.setSignupStatus(false);
    }

    @Test
    void testSignUserUp_Success() throws Exception {
        when(dbHandler.addUser(anyString(), anyString())).thenReturn(true);

        User testUser = new User("testUser", "password123");
        ObjectMapper objectMapper = new ObjectMapper();
        String userJson = objectMapper.writeValueAsString(testUser);

        mockMvc.perform(post("/api/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson))
                .andExpect(status().isOk());

        assertTrue(signupHandler.isSignedIn());
    }

    @Test
void testSignUserUp_Failure() {
    DBHandler dbHandler = new DBHandler();
    
    dbHandler.addUser("testuser", "password123");

    boolean result = dbHandler.addUser("testuser", "password123");

    Assertions.assertFalse(result, "User should not be able to sign up twice!");
}


    @Test
    void testIsSignedIn() throws Exception {
        signupHandler.setSignupStatus(true); 

        mockMvc.perform(get("/api/signup/status"))
                .andExpect(status().isOk())
                .andExpect(content().string("true")); 
    }
}
