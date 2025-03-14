package com.example.day2soap;

public class UserPrompt {
    private Movie favouriteMovie;
    private User user;
    private String favouriteActor;
    private String favouriteDirector;
    private String mpaRating;
    private String productionDecade;
    private int length;

    public UserPrompt(Movie favouriteMovie, User user, String favouriteActor, String favouriteDirector, String mpaRating, String productionDecade, int length) {
        this.favouriteMovie = favouriteMovie;
        this.user = user;
        this.favouriteActor = favouriteActor;
        this.favouriteDirector = favouriteDirector;
        this.mpaRating = mpaRating;
        this.productionDecade = productionDecade;
        this.length = length;
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

    public int getLength() {
        return length;
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

    public void setLength(int length) {
        this.length = length;
    }
}