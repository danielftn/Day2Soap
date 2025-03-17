package com.example.day2soap.dto;

import com.example.day2soap.model.Movie;
import com.example.day2soap.model.User;

public class UserPrompt {
    private String username;
    private User user;
    private Movie favouriteMovie;
    private String favouriteGenre;
    private String favouriteActor;
    private String favouriteDirector;
    private String mpaRating;
    private String productionDecade;
    private String productionLength;

    public UserPrompt(Movie favouriteMovie, User user, String favouriteActor, String favouriteDirector, String mpaRating, String productionDecade, String productionLength, String username, String favouriteGenre) {
        this.username = username;
        this.favouriteMovie = favouriteMovie;
        this.user = user;
        this.favouriteActor = favouriteActor;
        this.favouriteDirector = favouriteDirector;
        this.mpaRating = mpaRating;
        this.productionDecade = productionDecade;
        this.productionLength = productionLength;
        this.favouriteGenre = favouriteGenre;
    }
    public String getFavouriteGenre() {
        return favouriteGenre;
    }
    public void setFavouriteGenre(String favouriteGenre) {
        this.favouriteGenre = favouriteGenre;
    }
    public String getUsername() {
        return username;
    }

    public Movie getFavouriteMovie() {
        return favouriteMovie;
    }

    public User getUser() {
        return user;
    }

    public String getFavouriteActor() {
        return favouriteActor;
    }

    public String getFavouriteDirector() {
        return favouriteDirector;
    }

    public String getMpaRating() {
        return mpaRating;
    }

    public String getProductionDecade() {
        return productionDecade;
    }

    public String getProductionLength() {
        return productionLength;
    }

    public void setFavouriteMovie(Movie favouriteMovie) {
        this.favouriteMovie = favouriteMovie;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setFavouriteActor(String favouriteActor) {
        this.favouriteActor = favouriteActor;
    }

    public void setFavouriteDirector(String favouriteDirector) {
        this.favouriteDirector = favouriteDirector;
    }

    public void setMpaRating(String mpaRating) {
        this.mpaRating = mpaRating;
    }

    public void setProductionDecade(String productionDecade) {
        this.productionDecade = productionDecade;
    }

    public void setProductionLength(String productionLength) {
        this.productionLength = productionLength;
    }
}