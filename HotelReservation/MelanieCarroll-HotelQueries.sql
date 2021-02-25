USE HotelReservation;

-- 1. Write a query that returns a list of reservations that end in July 2023, including the name of the guest, 
-- the room number(s), and the reservation dates.

SELECT
	Guest.FirstName,
    Guest.LastName,
    Reservation.StartDate,
    Reservation.EndDate,
    RoomReservation.RoomNumber
FROM Guest
INNER JOIN Reservation ON Guest.GuestID = Reservation.GuestID
INNER JOIN RoomReservation ON Reservation.ReservationID = RoomReservation.ReservationID
WHERE Reservation.EndDate BETWEEN '2023-07-01' AND '2023-07-31';

-- FirstName LastName StartDate	EndDate	   RoomNumber
-- Melanie	Carroll	2023-06-28	2023-07-02	205
-- Walter	Holaway	2023-07-13	2023-07-14	204
-- Wilfred	Vise	2023-07-18	2023-07-21	401
-- BettyAnn	Seery	2023-07-28	2023-07-29	303


-- 2. Write a query that returns a list of all reservations for rooms with a jacuzzi, displaying the guest's name,
-- the room number, and the dates of the reservation.

SELECT
	Guest.FirstName,
    Guest.LastName,
    Reservation.StartDate,
    Reservation.EndDate,
    RoomReservation.RoomNumber
FROM Guest
INNER JOIN Reservation ON Guest.GuestID = Reservation.GuestID
INNER JOIN RoomReservation ON Reservation.ReservationID = RoomReservation.ReservationID
INNER JOIN RoomAmenity ON RoomReservation.RoomNumber = RoomAmenity.RoomNumber
WHERE AmenityID = 1;

-- FirstName LastName StartDate	EndDate	   RoomNumber
-- Karie	Yang	2023-03-06	2023-03-07	201
-- BettyAnn	Seery	2023-02-05	2023-02-10	203
-- Karie	Yang	2023-09-13	2023-09-15	203
-- Melanie	Carroll	2023-06-28	2023-07-02	205
-- Wilfred	Vise	2023-04-23	2023-04-24	207
-- Walter	Holaway	2023-04-09	2023-04-13	301
-- Mack	    Simmer	2023-11-22	2023-11-25	301
-- BettyAnn	Seery	2023-07-28	2023-07-29	303
-- Duane  Cullison	2023-02-22	2023-02-24	305
-- BettyAnn	Seery	2023-08-30	2023-09-01	305
-- Melanie	Carroll	2023-03-17	2023-03-20	307



-- 3. Write a query that returns all the rooms reserved for a specific guest, including the guest's name, the room(s) 
-- reserved, the starting date of the reservation, and how many people were included in the reservation. 
-- (Choose a guest's name from the existing data.)

SELECT
	Guest.FirstName,
    Guest.LastName,
    Reservation.StartDate,
    RoomReservation.AdultOccupancy,
    RoomReservation.ChildOccupancy,
    RoomReservation.RoomNumber
FROM RoomReservation
LEFT OUTER JOIN Reservation ON RoomReservation.ReservationID = Reservation.ReservationID
LEFT OUTER JOIN Guest ON Reservation.GuestID = Guest.GuestID
WHERE Guest.LastName = 'Carroll';

-- FirstName	LastName	StartDate	AdultOccupancy	ChildOccupancy	RoomNumber	
-- Melanie	    Carroll	        2023-03-17	       1	         1	         307	      
-- Melanie	    Carroll	        2023-06-28	       2	         0	         205	      




-- 4. Write a query that returns a list of rooms, reservation ID, and per-room cost for each reservation. The results 
-- should include all rooms, whether or not there is a reservation associated with the room.

SELECT
    Room.RoomNumber,
    RoomReservation.ReservationID,
    RoomReservation.TotalRoomCost
FROM Room
LEFT OUTER JOIN RoomReservation ON Room.RoomNumber = RoomReservation.RoomNumber
ORDER BY Room.RoomNumber;

-- RoomNumber	ReservationID	TotalRoomCost
-- 201	               4	     199.99
-- 202	               7	     349.98
-- 203	               2	     999.95
-- 203	               20	     399.98
-- 204	               15	     184.99
-- 205	               14	     699.96
-- 206	               12	     599.96
-- 206	               22	     449.97
-- 207	               10	     174.99
-- 208	               12	     599.96
-- 208	               19	     149.99
-- 301	               9	     799.96
-- 301	               22	     599.97
-- 302	                6	     924.95
-- 302	               23	     699.96
-- 303	               17	     199.99
-- 304	               13	     184.99
-- 305	                3	     349.98
-- 305	                18	     349.98
-- 306	               NULL	     NULL
-- 307	                5	     524.97
-- 308	                1	     299.98
-- 401	                11	     1199.97
-- 401	                16	     1259.97
-- 401	                21	     1199.97
-- 402	                NULL	NULL


-- 5. Write a query that returns all the rooms accommodating at least three guests and that are reserved on any date 
-- in April 2023.

SELECT
	RoomReservation.RoomNumber,
    RoomReservation.AdultOccupancy,
    RoomReservation.ChildOccupancy,
    Reservation.StartDate,
    Reservation.EndDate
FROM RoomReservation
CROSS JOIN Reservation
WHERE (RoomReservation.AdultOccupancy + RoomReservation.ChildOccupancy >= 3) 
AND (Reservation.StartDate BETWEEN '2023-04-01' AND '2023-04-30') ;

-- RoomNumber	AdultOccupancy	ChildOccupancy	StartDate	EndDate
-- 201	              2           	2	        4/9/2023	4/13/2023
-- 201	              2	            2	        4/23/2023	4/24/2023
-- 202	              2	            2	        4/9/2023	4/13/2023
-- 202	              2	            2	        4/23/2023	4/24/2023
-- 203	              2	            1	        4/9/2023	4/13/2023
-- 203	              2	            1	        4/23/2023	4/24/2023
-- 203	              2	            2	        4/9/2023	4/13/2023
-- 203	              2	            2	        4/23/2023	4/24/2023
-- 204	              3	            1	        4/9/2023	4/13/2023
-- 204	              3             1        	4/23/2023	4/24/2023
-- 301	              2	            2	        4/9/2023	4/13/2023
-- 301	              2	            2	        4/23/2023	4/24/2023
-- 302	              3	            0	        4/9/2023	4/13/2023
-- 302	              3	            0	        4/23/2023	4/24/2023
-- 303	              2	            1	        4/9/2023	4/13/2023
-- 303	              2	            1	        4/23/2023	4/24/2023
-- 304	              3	            0	        4/9/2023	4/13/2023
-- 304	              3	            0	        4/23/2023	4/24/2023
-- 401	              2	            4	        4/9/2023	4/13/2023
-- 401	              2          	4	        4/23/2023	4/24/2023
-- 401	              4	            2	        4/9/2023	4/13/2023
-- 401	              4	            2	        4/23/2023	4/24/2023
-- 401	              2          	2	        4/9/2023	4/13/2023
-- 401	              2          	2	        4/23/2023	4/24/2023


-- 6. Write a query that returns a list of all guest names and the number of reservations per guest, sorted starting
-- with the guest with the most reservations and then by the guest's last name.


SELECT
    Guest.FirstName,
    Guest.LastName,
    COUNT(Reservation.ReservationID) ReservationCount
FROM Guest
LEFT OUTER JOIN Reservation ON Guest.GuestID = Reservation.GuestID
GROUP BY Guest.GuestID
ORDER BY COUNT(Reservation.ReservationID) DESC, Guest.LastName;

--  FirstName	LastName	ReservationCount
-- BettyAnn	    Seery	     3
-- Mack	        Simmer	     3
-- Melanie	    Carroll	     2
-- Duane	    Cullison	 2
-- Walter	    Holaway	     2
-- Aurore	    Lipton	     2
-- Maritza	    Tilton	     2
-- Wilfred	    Vise	     2
-- Karie	    Yang	     2
-- Zachary	    Luechtefeld	 1
-- Joleen	    Tison	     1


-- 7. Write a query that displays the name, address, and phone number of a guest based on their phone number. 
-- (Choose a phone number from the existing data.)

SELECT 
    LastName,
    FirstName,
    Address,
    City,
    StateAbbr,
    PostalCode,
    Phone
FROM Guest
WHERE Phone = '291-553-0508';

-- LastName	FirstName	Address	   	         City        StateAbbr   PostalCode	        Phone
-- Simmer	Mack	379 Old Shore Street	Council Bluffs	IA	      51501	       291-553-0508
