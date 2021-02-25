USE HotelReservation;

INSERT INTO RoomType (RoomTypeID, RoomSize, RoomBasePrice, ExtraAdultPrice, StandardOccupancy, MaximumOccupancy)
VALUES (1,'Double', 174.99, 10.00, 2, 4),
(2,'Single', 149.99, 0, 2, 2),
(3,'Suite', 399.99, 20.00, 3, 8);

SELECT * FROM RoomType;

INSERT INTO Room (RoomNumber, ADAAccessible, RoomTypeID)
VALUES (201, 0, 1),
(202, 1, 1),
(203, 0, 1),
(204, 1, 1),
(205, 0, 2),
(206, 1, 2),
(207, 0, 2),
(208, 1, 2),
(301, 0, 1),
(302, 1, 1),
(303, 0, 1),
(304, 1, 1),
(305, 0, 2),
(306, 1, 2),
(307, 0, 2),
(308, 1, 2),
(401, 1, 3),
(402, 1, 3);

INSERT INTO Amenity (AmenityID, AmenityName, AmenityCost)
VALUES (1, 'Jacuzzi', 25.00),
(2, 'Microwave', 0.00),
(3, 'Refrigerator', 0.00),
(4, 'Oven', 0.00),
(5, 'Stove', 0.00);

INSERT INTO RoomAmenity (RoomNumber, AmenityID)
VALUES (201, 1),
(201, 2),
(202, 3),
(203, 1),
(203, 2),
(204, 3),
(205, 2),
(205, 3),
(205, 1),
(206, 2),
(206, 3),
(207, 1),
(207, 2),
(207, 3),
(208, 2),
(208, 3),
(301, 1),
(301, 2),
(302, 3),
(303, 1),
(303, 2),
(304, 3),
(305, 1),
(305, 2),
(305, 3),
(306, 2),
(306, 3),
(307, 1),
(307, 2),
(307, 3),
(308, 2),
(308, 3),
(401, 2),
(401, 3),
(401, 4),
(401, 5),
(402, 2),
(402, 3),
(402, 4),
(402, 5);

INSERT INTO Invoice (InvoiceID, TotalCost)
VALUES (1, 299.98),
(2, 999.95),
(3, 349.98),
(4, 199.99),
(5, 524.97),
(6, 924.95),
(7, 349.98),
(8, 874.95),
(9, 799.96),
(10, 174.99),
(11, 1199.97),
(12, 1199.92),
(13, 184.99),
(14, 699.96),
(15, 184.99),
(16, 1259.97),
(17, 199.99),
(18, 349.98),
(19, 149.99),
(20, 399.98),
(21, 1199.97),
(22, 1049.94),
(23, 699.96);
  
INSERT INTO Guest (GuestID, FirstName, LastName, Address, City, StateAbbr, PostalCode, Phone)
VALUES (1, 'Melanie', 'Carroll', '8321 Jensen Ave S', 'Cottage Grove', 'MN', '55016', '651-304-0968'),
(2, 'Mack', 'Simmer', '379 Old Shore Street', 'Council Bluffs', 'IA', '51501', '291-553-0508'),
(3, 'BettyAnn', 'Seery', '750 Wintergreen Dr.', 'Wasilla', 'AK', '99654', '478-277-9632'),
(4, 'Duane', 'Cullison', '9662 Foxrun Lane', 'Harlingen', 'TX', '78552', '308-494-0198'),
(5, 'Karie', 'Yang', '9378 W. Augusta Ave.', 'West Deptford', 'NJ', '08096', '214-730-0298'),
(6, 'Aurore', 'Lipton', '762 Wild Rose Street', 'Saginaw', 'MI', '48601', '377-507-0974'),
(7, 'Zachary', 'Luechtefeld', '7 Poplar Dr.', 'Arvada', 'CO', '80003', '814-485-2615'),
(8, 'Jeremiah', 'Pendergrass', '70 Oakwood St.', 'Zion', 'IL', '60099', '279-491-0960'),
(9, 'Walter', 'Holaway', '7556 Arrowhead St.', 'Cumberland', 'RI', '02864', '446-396-6785'),
(10, 'Wilfred', 'Vise', '77 West Surrey Street', 'Oswego', 'NY', '13126', '834-727-1001'),
(11, 'Maritza', 'Tilton', '939 Linda Rd.', 'Burke', 'VA', '22015', '446-351-6860'),
(12, 'Joleen', 'Tison', '87 Queen St.', 'Drexel Hill', 'PA', '19026', '231-893-2755');

INSERT INTO Reservation (ReservationID, StartDate, EndDate, GuestID, InvoiceID)
VALUES (1, '2023-02-02', '2023-02-04', 2, 1),
(2, '2023-02-05', '2023-02-10', 3, 2),
(3, '2023-02-22', '2023-02-24', 4, 3),
(4, '2023-03-06', '2023-03-07', 5, 4),
(5,'2023-03-17', '2023-03-20', 1, 5),
(6, '2023-03-18', '2023-03-23', 6, 6),
(7, '2023-03-29', '2023-03-31', 7, 7),
(8, '2023-03-31', '2023-04-05', 8, 8),
(9, '2023-04-09', '2023-04-13', 9, 9),
(10, '2023-04-23', '2023-04-24', 10, 10),
(11, '2023-05-30', '2023-06-02', 11, 11),
(12, '2023-06-10', '2023-06-14', 12, 12),
(13,  '2023-06-17', '2023-06-18', 6, 13),
(14,  '2023-06-28', '2023-07-02', 1, 14),
(15, '2023-07-13', '2023-07-14', 9, 15),
(16, '2023-07-18', '2023-07-21', 10, 16),
(17, '2023-07-28', '2023-07-29', 3, 17),
(18, '2023-08-30', '2023-09-01', 3, 18),
(19, '2023-09-16', '2023-09-17', 2, 19),
(20, '2023-09-13', '2023-09-15', 5, 20),
(21, '2023-11-22', '2023-11-25', 4, 21),
(22, '2023-11-22', '2023-11-25', 2, 22),
(23, '2023-12-24', '2023-12-28', 11, 23); 

INSERT INTO RoomReservation (RoomNumber, ReservationID, AdultOccupancy, ChildOccupancy, TotalRoomCost)
VALUES (308, 1, 1, 0, 299.98),
(203, 2, 2, 1, 999.95),
(305, 3, 2, 0, 349.98),
(201, 4, 2, 2, 199.99),
(307, 5, 1, 1, 524.97),
(302, 6, 3, 0, 924.95),
(202, 7, 2, 2, 349.98),
(304, 8, 2, 0, 874.95),
(301, 9, 1, 0, 799.96),
(207, 10, 1, 1, 174.99),
(401, 11, 2, 4, 1199.97),
(206, 12, 2, 0, 599.96),
(208, 12, 1, 0, 599.96),
(304, 13, 3, 0, 184.99),
(205, 14, 2, 0, 699.96),
(204, 15, 3, 1, 184.99),
(401, 16, 4, 2, 1259.97),
(303, 17, 2, 1, 199.99),
(305, 18, 1, 0, 349.98),
(208, 19, 2, 0, 149.99),
(203, 20, 2, 2, 399.98),
(401, 21, 2, 2, 1199.97),
(206, 22, 2, 0, 449.97),
(301, 22, 2, 2, 599.97),
(302, 23, 2, 0, 699.96);

SET SQL_SAFE_UPDATES = 0;

DELETE FROM RoomReservation
WHERE ReservationID = 8 and RoomNumber = 304;

DELETE FROM Reservation
WHERE ReservationID = 8;

DELETE FROM Guest
WHERE GuestID = 8;

SET SQL_SAFE_UPDATES = 1;

SELECT *
FROM Guest;

SELECT *
FROM Room;