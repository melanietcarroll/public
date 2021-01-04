DROP DATABASE IF EXISTS guessthenumberDB;
CREATE DATABASE guessthenumberDB;

USE guessthenumberDB;

CREATE TABLE game(
id INT PRIMARY KEY AUTO_INCREMENT,
gameAnswer VARCHAR(10),
finished BOOLEAN DEFAULT false);

CREATE TABLE round(
id INT PRIMARY KEY AUTO_INCREMENT,
roundGuess VARCHAR(10),
timeOfGuess DATETIME,
resultOfGuess VARCHAR(10),
gameId INT NOT NULL,
FOREIGN KEY (gameId) REFERENCES game(id));

INSERT INTO game(id, gameAnswer, finished)
VALUES(1, "1234", true),
(2, "4567", true),
(3, "8541", false);

INSERT INTO round(id, roundGuess, timeOfGuess, resultOfGuess, gameId)
VALUES(1, "1234", '2018-01-01 14:00:00', "e:4:p:0",1),
(2, "4567", '2018-01-10 14:00:00', "e:4:p:0",2),
(3, "8540", '2018-01-20 14:00:00', "e:3:p:0",3);

