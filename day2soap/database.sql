CREATE DATABASE MusicRecommendationDB;

USE MusicRecommendationDB;

CREATE TABLE users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
	username VARCHAR(50) NOT NULL,
	user_password VARCHAR(50) NOT NULL
);

CREATE TABLE recommended_songs (
    recommendation_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    movie_title VARCHAR(255) NOT NULL,
    release_year INT,
    description TEXT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);

CREATE USER 'user'@'localhost' IDENTIFIED BY 'ucalgary';
GRANT ALL PRIVILEGES ON MusicRecommendationDB.* TO 'user'@'localhost';
