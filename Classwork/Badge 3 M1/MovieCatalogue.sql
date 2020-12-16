DROP DATABASE IF EXISTS MovieCatalogue;
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
    DirectorID INT,
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

INSERT INTO Actor (ActorID, FirstName, LastName, BirthDate) VALUES
    (1, 'Bill','Murray', '1950/09/21'),
    (2, 'Dan','Aykroyd', '1952/07/01'),
    (3, 'John','Candy', '1950/10/31'),
    (4, 'Steve','Martin', NULL),
    (5, 'Sylvester','Stallone', NULL);
    
SELECT * FROM Actor;

INSERT INTO Director (DirectorID, FirstName, LastName, BirthDate) VALUES
    (1, 'Ivan','Reitman', '1946/10/27'),
    (2, 'Ted','Kotcheff', NULL);
    
SELECT * FROM Director;

INSERT INTO Rating (RatingID, RatingName) VALUES
    (1,'G'),
    (2,'PG'),
    (3,'PG-13'),
    (4, 'R');
    
SELECT * FROM Rating;

INSERT INTO Genre (GenreID, GenreName) VALUES
    (1,'Action'),
    (2,'Comedy'),
    (3,'Drama'),
    (4, 'Horror');
    
SELECT * FROM Genre;

INSERT INTO Movie (RatingID, DirectorID, GenreID, Title, ReleaseDate) VALUES
    (4, 2, 1,  'Rambo: First Blood','1982/10/22'),
    (4, NULL, 2, 'Planes, Trains & Automobiles', '1987/11/25'),
    (2, 1, 2, 'Ghostbusters',NULL),
    (2, NULL, 2, 'The Great Outdoors', '1988/06/17');
 
 SELECT * FROM Movie;
 
 INSERT INTO CastMembers (Role, ActorID, MovieID) VALUES
    ('John Rambo', 5, 1),
    ('Neil Page', 4, 2),
    ('Del Griffith', 3, 2),
    ('Dr. Peter Venkman', 1, 3),
    ('Dr. Raymond Stanz', 2, 3),
    ('Roman Craig', 2, 4),
    ('Chet Ripley', 3, 4);
    
SELECT * FROM CastMembers;

SET SQL_SAFE_UPDATES = 0;
UPDATE Movie SET
    Title = 'Ghostbusters (1984)',
    ReleaseDate = '1984/06/08'
WHERE MovieID = 3;
SET SQL_SAFE_UPDATES = 1;

SELECT * FROM Movie;

SET SQL_SAFE_UPDATES = 0;
UPDATE Genre SET
    GenreName  = 'Action/Adventure'
WHERE GenreID = 1;
SET SQL_SAFE_UPDATES = 1;

SELECT * FROM Genre;

DELETE FROM CastMembers
WHERE MovieID = 1;

DELETE FROM Movie
WHERE MovieID = 1;

SELECT * FROM Movie;

ALTER TABLE Actor
    ADD COLUMN (
        DateOfDeath DATE
    );
 
SELECT * FROM Actor;
 
SET SQL_SAFE_UPDATES = 0;
    
UPDATE Actor SET
    DateOfDeath = '1994/03/04'
WHERE ActorID = 3;

SET SQL_SAFE_UPDATES = 1;

SELECT * FROM Actor;