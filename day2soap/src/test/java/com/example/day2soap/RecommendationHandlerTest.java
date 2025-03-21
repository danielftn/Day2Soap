package com.example.day2soap;
import com.example.day2soap.model.User;
import com.example.day2soap.controller.RecommendationHandler;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.example.day2soap.dto.UserPrompt;
import com.example.day2soap.model.Movie;
import org.springframework.ai.chat.client.ChatClient;

import java.sql.SQLException;
import java.util.List;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class RecommendationHandlerTest {

    @Mock
    private ChatClient.Builder chatClientBuilder;

    @Mock
    private ChatClient chatClient;

    @InjectMocks
    private RecommendationHandler recommendationHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    
        when(chatClientBuilder.build()).thenReturn(chatClient);
    
        // Correctly mock the response chain
        ChatClient.ChatClientRequestSpec requestSpec = mock(ChatClient.ChatClientRequestSpec.class);
        ChatClient.CallResponseSpec responseSpec = mock(ChatClient.CallResponseSpec.class);
    
        when(chatClient.prompt(anyString())).thenReturn(requestSpec);
        when(requestSpec.call()).thenReturn(responseSpec);
        when(responseSpec.content()).thenReturn("***Interstellar*** (2014) \"Space exploration adventure\"");
    }
    
    

    /** 
     * Helper method to create a test UserPrompt instance 
     */
    private UserPrompt createTestUserPrompt() {
        UserPrompt prompt = new UserPrompt();
        prompt.setUser(new User("testUser", "password123"));
        prompt.setFavouriteMovie(new Movie("Inception", 2010, "A mind-bending thriller", false));
        prompt.setFavouriteGenre("Sci-Fi");
        prompt.setFavouriteActor("Leonardo DiCaprio");
        prompt.setFavouriteDirector("Christopher Nolan");
        prompt.setProductionDecade("2010s");
        prompt.setMpaRating("PG-13");
        prompt.setProductionLength("2h 30m");
        return prompt;
    }

    @Test
    void testParseMovies() {
        String response = "***Movie One*** (2020) \"Description One\" ***Movie Two*** (2022) \"Description Two\"";
        List<Movie> movies = recommendationHandler.parseMovies(response);

        assertEquals(2, movies.size());
        assertEquals("Movie One", movies.get(0).getTitle());
        assertEquals(2020, movies.get(0).getYear());
        assertEquals("Description One", movies.get(0).getDescription());

        assertEquals("Movie Two", movies.get(1).getTitle());
        assertEquals(2022, movies.get(1).getYear());
        assertEquals("Description Two", movies.get(1).getDescription());
    }

    @Test
    void testAskLLM() {
        ChatClient.ChatClientRequestSpec requestSpec = mock(ChatClient.ChatClientRequestSpec.class);
        ChatClient.CallResponseSpec responseSpec = mock(ChatClient.CallResponseSpec.class);
    
        when(chatClient.prompt(anyString())).thenReturn(requestSpec);
        when(requestSpec.call()).thenReturn(responseSpec);
        when(responseSpec.content()).thenReturn("Mocked LLM Response");
    
        String result = recommendationHandler.askLLM(chatClientBuilder, "Test Prompt");
        assertEquals("Mocked LLM Response", result);
    }
    
    

    @Test
    void testGetRecommendation_Success() throws SQLException {
        // Create a UserPrompt instance using setters
        UserPrompt prompt = new UserPrompt();
        prompt.setFavouriteMovie(new Movie("Inception", 2010, "Sci-Fi Thriller", false));
        prompt.setFavouriteGenre("Sci-Fi");
        prompt.setFavouriteActor("Leonardo DiCaprio");
        prompt.setFavouriteDirector("Christopher Nolan");
        prompt.setProductionDecade("2010s");
        prompt.setMpaRating("PG-13");
        prompt.setProductionLength("120-150 mins");
        prompt.setUser(new User("testUser", "password"));
    
        // Properly mock the LLM response
        ChatClient.ChatClientRequestSpec requestSpec = mock(ChatClient.ChatClientRequestSpec.class);
        ChatClient.CallResponseSpec responseSpec = mock(ChatClient.CallResponseSpec.class);
    
        when(chatClient.prompt(anyString())).thenReturn(requestSpec);
        when(requestSpec.call()).thenReturn(responseSpec);
        when(responseSpec.content()).thenReturn("***Interstellar*** (2014) \"Space exploration adventure\"");
    
        // Call the method under test
        ResponseEntity<List<Movie>> response = recommendationHandler.getRecommendation(prompt);
    
        // Verify that the response contains the expected movie
        assertEquals(1, response.getBody().size());
        assertEquals("Interstellar", response.getBody().get(0).getTitle());
    }
    
    
}
