DROP DATABASE IF EXISTS superheroSightingTest;
CREATE DATABASE superheroSightingTest;

USE superheroSightingTest;

CREATE TABLE Superhero(
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(30) NOT NULL,
    description VARCHAR(255)
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
    address VARCHAR(50),
    latitude VARCHAR(15),
    longitude VARCHAR(15)
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
    address VARCHAR(50)
);
CREATE TABLE Superhero_Organization(
    superheroId INT NOT NULL,
organizationId INT NOT NULL,
PRIMARY KEY(superheroId, organizationId),
FOREIGN KEY (superheroId) REFERENCES Superhero(id),
FOREIGN KEY (organizationId) REFERENCES Organization(id));

