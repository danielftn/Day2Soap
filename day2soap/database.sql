CREATE DATABASE day2soapdb;

USE day2soapdb;

CREATE TABLE users (
	username VARCHAR(50) PRIMARY KEY,
	user_password VARCHAR(50) NOT NULL
);

CREATE TABLE recommended_movies (
    recommendation_id INT AUTO_INCREMENT PRIMARY KEY,
    user VARCHAR(50),
    movie_title VARCHAR(255) NOT NULL,
    release_year INT,
    description TEXT NOT NULL,
    watched boolean default false,
    FOREIGN KEY (user) REFERENCES users(username) ON DELETE CASCADE
);

CREATE USER 'user'@'localhost' IDENTIFIED BY 'ucalgary';
GRANT ALL PRIVILEGES ON day2soapdb.* TO 'user'@'localhost';

INSERT INTO recommended_movies (user, movie_title, release_year, description, watched) VALUES 
("test", "the matrix", 2025, "test desc", true);

INSERT INTO recommended_movies (user, movie_title, release_year, description) VALUES 
("test", "cars", 2025, "test desc");

INSERT INTO recommended_movies (user, movie_title, release_year, description) VALUES 
("test", "GOAT MOVIE", 1900, "GOAT desc");

INSERT INTO recommended_movies (user, movie_title, release_year, description) VALUES 
("daniel", "spiderverse", 2019, "test desc");

Select * from users;
Select * from recommended_movies;