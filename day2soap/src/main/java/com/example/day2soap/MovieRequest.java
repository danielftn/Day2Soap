package com.example.day2soap;

public class MovieRequest {
    private String username;
    private Movie movie;

    public MovieRequest(String username, Movie movie) {
        this.username = username;
        this.movie = movie;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}
