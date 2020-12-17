USE PersonalTrainer;

-- Use an aggregate to count the number of Clients.
-- 500 rows
SELECT COUNT(ClientId)
FROM Client;
--------------------

-- Use an aggregate to count Client.BirthDate.
-- The number is different than total Clients. Why?
-- 463 rows
SELECT COUNT(BirthDate)
FROM Client;
--------------------

-- Group Clients by City and count them.
-- Order by the number of Clients desc.
-- 20 rows
SELECT
	City,
    COUNT(*) ClientCityCount
FROM Client c

GROUP BY City
ORDER BY COUNT(*) DESC;
--------------------

-- Calculate a total per invoice using only the InvoiceLineItem table.
-- Group by InvoiceId.
-- You'll need an expression for the line item total: Price * Quantity.
-- Aggregate per group using SUM().
-- 1000 rows
SELECT
	InvoiceId,
    SUM(Price * Quantity) InvoiceTotal
    
FROM InvoiceLineItem i
GROUP BY InvoiceId;

--------------------

-- Calculate a total per invoice using only the InvoiceLineItem table.
-- (See above.)
-- Only include totals greater than $500.00.
-- Order from lowest total to highest.
-- 234 rows

SELECT
	InvoiceId,
    SUM(Price * Quantity) InvoiceTotal
    
FROM InvoiceLineItem i
GROUP BY InvoiceId
HAVING SUM(Price * Quantity) >= 500
ORDER BY SUM(Price * Quantity);
--------------------

-- Calculate the average line item total
-- grouped by InvoiceLineItem.Description.
-- 3 rows
SELECT
	InvoiceId,
    AVG(Price) AverageTotal
    
FROM InvoiceLineItem 
GROUP BY InvoiceLineItem.Description;

--------------------

-- Select ClientId, FirstName, and LastName from Client
-- for clients who have *paid* over $1000 total.
-- Paid is Invoice.InvoiceStatus = 2.
-- Order by LastName, then FirstName.
-- 146 rows

SELECT
	Client.ClientId,
    Client.FirstName,
    Client.LastName,
    SUM(InvoiceLineItem.Price * InvoiceLineItem.Quantity) Total
FROM Client
INNER JOIN Invoice ON Client.ClientId = Invoice.ClientId
INNER JOIN InvoiceLineItem ON Invoice.InvoiceId = InvoiceLineItem.InvoiceId 
WHERE Invoice.InvoiceStatus = 2
GROUP BY Client.ClientId, Client.LastName, Client.FirstName
HAVING SUM(InvoiceLineItem.Price * InvoiceLineItem.Quantity) > 1000
ORDER BY Client.LastName, Client.FirstName;
--------------------

-- Count exercises by category.
-- Group by ExerciseCategory.Name.
-- Order by exercise count descending.
-- 13 rows
SELECT
	ExerciseCategory.Name ExerciseName,
    COUNT(Exercise.ExerciseId) ExerciseCount
FROM ExerciseCategory
INNER JOIN Exercise ON ExerciseCategory.ExerciseCategoryId = Exercise.ExerciseCategoryId
GROUP BY ExerciseCategory.ExerciseCategoryId, ExerciseCategory.Name
ORDER BY COUNT(Exercise.ExerciseId) DESC;
--------------------

-- Select Exercise.Name along with the minimum, maximum,
-- and average ExerciseInstance.Sets.
-- Order by Exercise.Name.
-- 64 rows
SELECT
	Exercise.`Name` ExerciseName,
    MIN(ExerciseInstance.Sets) MinSets,
    MAX(ExerciseInstance.Sets) MaxSets,
    AVG(ExerciseInstance.Sets) AvgSets
FROM Exercise 
INNER JOIN ExerciseInstance  ON Exercise.ExerciseId = ExerciseInstance.ExerciseId
GROUP BY Exercise.ExerciseId, Exercise.`Name`
ORDER BY Exercise.`Name`;
--------------------

-- Find the minimum and maximum Client.BirthDate
-- per Workout.
-- 26 rows
-- Sample: 
-- WorkoutName, EarliestBirthDate, LatestBirthDate
-- '3, 2, 1... Yoga!', '1928-04-28', '1993-02-07'
--------------------

-- Count client goals.
-- Be careful not to exclude rows for clients without goals.
-- 500 rows total
-- 50 rows with no goals
--------------------

-- Select Exercise.Name, Unit.Name, 
-- and minimum and maximum ExerciseInstanceUnitValue.Value
-- for all exercises with a configured ExerciseInstanceUnitValue.
-- Order by Exercise.Name, then Unit.Name.
-- 82 rows
--------------------

-- Modify the query above to include ExerciseCategory.Name.
-- Order by ExerciseCategory.Name, then Exercise.Name, then Unit.Name.
-- 82 rows
--------------------

-- Select the minimum and maximum age in years for
-- each Level.
-- To calculate age in years, use the MySQL function DATEDIFF.
-- 4 rows
--------------------

-- Stretch Goal!
-- Count logins by email extension (.com, .net, .org, etc...).
-- Research SQL functions to isolate a very specific part of a string value.
-- 27 rows (27 unique email extensions)
--------------------

-- Stretch Goal!
-- Match client goals to workout goals.
-- Select Client FirstName and LastName and Workout.Name for
-- all workouts that match at least 2 of a client's goals.
-- Order by the client's last name, then first name.
-- 139 rows
--------------------