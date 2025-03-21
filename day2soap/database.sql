CREATE TABLE users (
    username VARCHAR(50) PRIMARY KEY,
    user_password VARCHAR(50) NOT NULL
);

CREATE TABLE recommended_movies (
    recommendation_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50),
    movie_title VARCHAR(255) NOT NULL,
    release_year INT,
    description TEXT NOT NULL,
    watched BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (username) REFERENCES users(username) ON DELETE CASCADE
);