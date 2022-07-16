CREATE TABLE IF NOT EXISTS USERS (
    user_id int AUTO_INCREMENT PRIMARY KEY,
    email varchar(50),
    login varchar(100),
    name varchar(100),
    birthday date
);

CREATE TABLE IF NOT EXISTS USER_FRIENDS (
    user_id int REFERENCES USERS (user_id),
    friend_id int REFERENCES USERS (user_id),
    friends_status boolean DEFAULT false
);

CREATE TABLE IF NOT EXISTS MPA (
    mpa_id int AUTO_INCREMENT PRIMARY KEY,
    name varchar(20),
    description varchar(80)
);

CREATE TABLE IF NOT EXISTS FILMS (
    film_id int AUTO_INCREMENT PRIMARY KEY,
    name varchar(20),
    description varchar(200),
    release_date date,
    duration int,
    mpa_id int REFERENCES MPA (mpa_id)
);

CREATE TABLE IF NOT EXISTS GENRES  (
    genre_id int AUTO_INCREMENT PRIMARY KEY,
    name varchar(20)
);

CREATE TABLE IF NOT EXISTS FILM_GENRES (
    film_id int REFERENCES FILMS (film_id),
    genre_id int REFERENCES GENRES (genre_id),
    PRIMARY KEY (film_id, genre_id)
);

CREATE TABLE IF NOT EXISTS FILM_LIKES (
    film_id int REFERENCES FILMS (film_id),
    user_id int REFERENCES USERS (user_id),
    PRIMARY KEY (film_id, user_id)
);