CREATE DATABASE MusicRecommendationDB;

USE MusicRecommendationDB;

CREATE TABLE users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL
);

CREATE TABLE recommended_songs (
    recommendation_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    movie_title VARCHAR(255) NOT NULL,
    release_year INT,
    description TEXT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);


