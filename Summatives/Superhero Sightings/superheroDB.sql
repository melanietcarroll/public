DROP DATABASE IF EXISTS superheroSighting;
CREATE DATABASE superheroSighting;

USE superheroSighting;

CREATE TABLE Superhero(
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(30) NOT NULL,
    description VARCHAR(255),
    photo VARCHAR(255)
);

CREATE TABLE Superpower(
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(30) NOT NULL
);

CREATE TABLE Superhero_Superpower(
    superheroId INT NOT NULL,
superpowerId INT NOT NULL,
PRIMARY KEY(superheroId, superpowerId),
FOREIGN KEY (superheroId) REFERENCES Superhero(id),
FOREIGN KEY (superpowerId) REFERENCES Superpower(id));

CREATE TABLE Location(
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(30) NOT NULL,
    description VARCHAR(255),
    address VARCHAR(100),
    latitude float8 NOT NULL,
    longitude float8 NOT NULL
);


CREATE TABLE Sighting(
 id INT PRIMARY KEY AUTO_INCREMENT,
 date DATE NOT NULL,
 time TIME,
    superheroId INT NOT NULL,
    locationId INT NOT NULL,
    FOREIGN KEY (superheroId) REFERENCES Superhero(id),
    FOREIGN KEY (locationId) REFERENCES Location(id)
);

CREATE TABLE Organization(
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(30) NOT NULL,
    description VARCHAR(255),
    address VARCHAR(100)
);
CREATE TABLE Superhero_Organization(
    superheroId INT NOT NULL,
organizationId INT NOT NULL,
PRIMARY KEY(superheroId, organizationId),
FOREIGN KEY (superheroId) REFERENCES Superhero(id),
FOREIGN KEY (organizationId) REFERENCES Organization(id));

INSERT INTO Superhero(id, name, description, photo)
VALUES(1, "Superman", "flies high", "img"),
(2, "Spiderman", "climbs high", "img"),
(3, "Green Arrow", "shoots well", "img");

INSERT INTO Location(id, name, description, address, latitude, longitude)
VALUES(1, "New York", "NY", "NY,NY", 40.730610, -73.935242),
(2, "Los Angeles", "LA", "LA,CA", 34.052235, -118.243683),
(3, "Seattle", "WA", "Seattle,WA", 47.608013,  -122.335167);

INSERT INTO Sighting(id, date, time, superheroId, locationId)
VALUES(1, '2018-12-14', '12:35:25', 1, 1),
(2, '2019-12-11', '12:25:50', 1, 2),
(3, '2020-12-25', '10:40', 1, 3),
(4, '2020-12-25', '09:40', 2, 2),
(5, '2020-10-23', '13:20', 3, 3),
(6, '2020-06-22', '07:30', 2, 1),
(7, '2020-12-14', '08:45', 2, 1);


INSERT INTO Superpower(id, name)
VALUES(1, "fly"),
(2, "climb anything"),
(3, "jump high"),
(4, "super strong");

INSERT INTO Superhero_Superpower(superheroId, superpowerId)
VALUES(1, 1),
(1,2),
(1,4),
(2,1),
(2,4),
(3,4),
(3,2);

INSERT INTO Organization(id, name, description, address)
VALUES(1, "ACME", "super corp", "NY,NY"),
(2, "SupsINC", "super corp", "LA,CA"),
(3, "SUPSRUS", "super corp", "MN");

INSERT INTO Superhero_Organization(superheroId, organizationId)
VALUES(1, 1),
(1,2),
(1,3),
(2,1),
(2,2),
(3,2),
(3,3);

SELECT org.* FROM Organization org 
JOIN Superhero_Organization so ON so.organizationId = org.id WHERE so.superheroId = 4;

SELECT sup.* FROM Superhero sup 
JOIN Superhero_Organization so ON so.superheroId = sup.id WHERE so.organizationId = 1;

SELECT s.* FROM Sighting s 
JOIN Superhero sup ON s.superheroId = sup.id WHERE sup.id = 1;

-- report all locations a superhero has been seen
SELECT l.* FROM Location l
JOIN Sighting s ON s.locationId = l.id 
WHERE superheroId = 1;

-- list all sightings for superhero and location by date
SELECT Superhero.Name,
Location.Name,
Sighting.Date 
FROM Sighting
INNER JOIN Superhero ON Sighting.superheroId = Superhero.id
INNER JOIN Location ON Sighting.locationId = Location.id
WHERE date = '2020-12-25';

SELECT * FROM Sighting
WHERE date = '2020-12-25';

-- report all locations a superhero has been seen
SELECT l.* FROM Location l
JOIN Sighting s ON s.locationId = l.id
JOIN Superhero ON s.superheroId = Superhero.id
WHERE Superhero.id = 1;

-- report all superheros by location
SELECT sup.* FROM Superhero sup
JOIN Sighting s ON s.superheroId = sup.id
JOIN Location ON s.locationId = Location.id
WHERE Location.id = 1;

-- select superheros for organization
SELECT sup.* FROM Superhero sup 
JOIN Superhero_Organization so ON so.superheroId = sup.id WHERE so.organizationId = 7;

-- select location for sighting
SELECT l.* FROM Location l 
		JOIN Sighting s ON s.locationId = l.id WHERE s.id = 1;
-- select superhero for sighting
SELECT sup.* FROM Superhero sup 
JOIN Sighting s ON s.superheroId = sup.id WHERE s.id = 1;

-- get superpowers for superhero
SELECT pow.* FROM Superpower pow 
JOIN Superhero_Superpower ss ON ss.superpowerId = pow.id WHERE ss.superheroId = 4;

-- get sightings for super hero
SELECT s.* FROM Sighting s 
JOIN Superhero sup ON s.superheroId = sup.id WHERE sup.id = 1;

-- get organizations for superhero
SELECT org.* FROM Organization org 
JOIN Superhero_Organization so ON so.organizationId = org.id WHERE so.superheroId = 4;

INSERT INTO Superhero(name, description) 
                VALUES("Captain America","patriotic");
SELECT * FROM Superhero;

SELECT sup.* FROM Superhero sup
JOIN Sighting s ON s.superheroId = sup.id
JOIN Location ON s.locationId = Location.id 
WHERE Location.id = 1;

UPDATE Superpower SET name = "super climb"
WHERE id =2;

SELECT * FROM Superhero;
SELECT * FROM Sighting 
WHERE date = '2020-12-25';