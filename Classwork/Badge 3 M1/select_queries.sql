show databases;
USE PersonalTrainer;

SELECT *
FROM Exercise;

USE PersonalTrainer;

SELECT *
FROM Client;

USE PersonalTrainer;

SELECT *
FROM Client
WHERE City = 'Metairie';

USE PersonalTrainer;

SELECT *
FROM Client
WHERE ClientId = '818u7faf-7b4b-48a2-bf12-7a26c92de20c';

USE PersonalTrainer;
SELECT *
FROM Goal;

USE PersonalTrainer;
SELECT Name, LevelId
FROM Workout;

USE PersonalTrainer;
SELECT Name, LevelId, Notes
FROM Workout
WHERE LevelId = 2;

USE PersonalTrainer;
SELECT FirstName, LastName, City
FROM Client
WHERE City IN ('Metairie', 'Kenner', 'Gretna');

USE PersonalTrainer;
SELECT FirstName, LastName, BirthDate
FROM Client
WHERE BirthDate >= '1980-01-01'
AND BirthDate <= '1989-12-31';

USE PersonalTrainer;
SELECT FirstName, LastName, BirthDate
FROM Client
WHERE BirthDate BETWEEN '1980-01-01' AND '1989-12-31';

USE PersonalTrainer;
SELECT *
FROM Login
WHERE EmailAddress LIKE '%.gov';

USE PersonalTrainer;
SELECT *
FROM Login
WHERE EmailAddress NOT LIKE '%.com';

USE PersonalTrainer;
SELECT FirstName, LastName
FROM Client
WHERE BirthDate IS NULL;

USE PersonalTrainer;
SELECT Name
FROM ExerciseCategory
WHERE ParentCategoryId IS NOT NULL;

USE PersonalTrainer;
SELECT Name, Notes
FROM Workout 
WHERE Notes LIKE '%you%' AND LevelId = 3;

USE PersonalTrainer;
SELECT FirstName, LastName, City
FROM Client
WHERE  City = 'LaPlace'
AND (LastName LIKE 'L%' OR LastName LIKE 'N%' OR LastName LIKE 'M');

USE PersonalTrainer;
SELECT *
FROM Client
WHERE  FirstName = 'Estrella' AND LastName = 'Bazely';
USE PersonalTrainer;
SELECT *
FROM Login
WHERE ClientId = '87976c42-9226-4bc6-8b32-23a8cd7869a5';

USE PersonalTrainer;
SELECT WorkoutId
FROM Workout
WHERE Name = 'This Is Parkour';
USE PersonalTrainer;
SELECT GoalId
FROM WorkoutGoal 
WHERE WorkoutId = 12;
USE PersonalTrainer;
SELECT Name
FROM Goal 
WHERE GoalId = 8 OR GoalId = 3 OR GoalId = 15;
