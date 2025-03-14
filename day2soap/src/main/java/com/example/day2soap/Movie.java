package com.example.day2soap;

public class Movie {
    private String title;
    private int year;
    private String description;

    public Movie(String title, int year, String description) {
        this.title = title;
        this.year = year;
        this.description = description;
    }

    public Movie(String title){
        this.title = title;
    }
    
    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return this.year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
