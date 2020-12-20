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
WHERE Reservation.EndDate BETWEEN '2023-07-01' AND '2023-07-31';



-- 3. Write a query that returns all the rooms reserved for a specific guest, including the guest's name, the room(s) 
-- reserved, the starting date of the reservation, and how many people were included in the reservation. 
-- (Choose a guest's name from the existing data.)


-- 4. Write a query that returns a list of rooms, reservation ID, and per-room cost for each reservation. The results 
-- should include all rooms, whether or not there is a reservation associated with the room.



-- 5. Write a query that returns all the rooms accommodating at least three guests and that are reserved on any date 
-- in April 2023.


-- 6. Write a query that returns a list of all guest names and the number of reservations per guest, sorted starting
-- with the guest with the most reservations and then by the guest's last name.


-- 7. Write a query that displays the name, address, and phone number of a guest based on their phone number. 
-- (Choose a phone number from the existing data.)