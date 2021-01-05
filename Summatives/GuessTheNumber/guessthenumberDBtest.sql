DROP DATABASE IF EXISTS guessthenumberDBtest;
CREATE DATABASE guessthenumberDBtest;

USE guessthenumberDBtest;

CREATE TABLE game(
id INT PRIMARY KEY AUTO_INCREMENT,
gameAnswer VARCHAR(10),
status VARCHAR(15));

CREATE TABLE round(
id INT PRIMARY KEY AUTO_INCREMENT,
roundGuess VARCHAR(20),
timeOfGuess DATETIME,
resultOfGuess VARCHAR(20),
gameId INT NOT NULL,
FOREIGN KEY (gameId) REFERENCES game(id));