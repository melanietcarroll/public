CREATE DATABASE IF NOT EXISTS MovieCatalogue;

USE MovieCatalogue;

CREATE TABLE Genre (
    GenreID INT PRIMARY KEY AUTO_INCREMENT,
    GenreName VARCHAR(30) NOT NULL
);

CREATE TABLE Director (
    DirectorID INT PRIMARY KEY AUTO_INCREMENT,
    FirstName VARCHAR(30) NOT NULL,
    LastName VARCHAR(30) NOT NULL,
    BirthDate DATE
);

CREATE TABLE Rating (
    RatingID INT PRIMARY KEY AUTO_INCREMENT,
    RatingName CHAR(5) NOT NULL
);

CREATE TABLE Actor (
    ActorID INT PRIMARY KEY AUTO_INCREMENT,
    FirstName VARCHAR(30) NOT NULL,
    LastName VARCHAR(30) NOT NULL,
    BirthDate DATE
);

CREATE TABLE Movie (
    MovieID INT PRIMARY KEY AUTO_INCREMENT,
    RatingID INT NOT NULL,
    DirectorID INT NOT NULL,
    GenreID INT NOT NULL,
    Title VARCHAR(128) NOT NULL,
    ReleaseDate DATE,
    FOREIGN KEY fk_Movie_Rating (RatingID)
        REFERENCES Rating (RatingID),
    FOREIGN KEY fk_Movie_Director (DirectorID)
        REFERENCES Director (DirectorID),
    FOREIGN KEY fk_Movie_Genre (GenreID)
        REFERENCES Genre (GenreID)
);

CREATE TABLE CastMembers (
    CastMemberID INT PRIMARY KEY AUTO_INCREMENT,
    Role VARCHAR(50) NOT NULL,
    ActorID INT NOT NULL,
    MovieID INT NOT NULL,
    FOREIGN KEY fk_CastMembers_Actor (ActorID)
        REFERENCES Actor (ActorID),
    FOREIGN KEY fk_CastMembers_Movie (MovieID)
        REFERENCES Movie (MovieID)
);