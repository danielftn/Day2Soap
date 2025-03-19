package com.example.day2soap;
import com.example.day2soap.controller.DBHandler;
import com.example.day2soap.controller.LoginHandler;
import com.example.day2soap.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

class LoginHandlerTest {

    private MockMvc mockMvc;

    @Mock
    private DBHandler dbHandler; 

    @InjectMocks
    private LoginHandler loginHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(loginHandler).build();
    }

    @Test
    void testLoginUser_Success() throws Exception {
        when(dbHandler.searchUser(anyString(), anyString())).thenReturn(true);

        User testUser = new User("testUser", "password123");
        ObjectMapper objectMapper = new ObjectMapper();
        String userJson = objectMapper.writeValueAsString(testUser);

        mockMvc.perform(post("/api/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/login/status"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"username\":\"testUser\",\"password\":null}"));
    }

    @Test
    void testLoginUser_Failure() throws Exception {
        when(dbHandler.searchUser(anyString(), anyString())).thenReturn(false);

        User testUser = new User("invalidUser", "wrongPassword");
        ObjectMapper objectMapper = new ObjectMapper();
        String userJson = objectMapper.writeValueAsString(testUser);

        mockMvc.perform(post("/api/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson))
                .andExpect(status().isOk()); 

        mockMvc.perform(get("/api/login/status"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"username\":null,\"password\":null}"));
    }

}

