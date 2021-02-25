DROP DATABASE IF EXISTS HotelReservation;

CREATE DATABASE HotelReservation;

USE HotelReservation;

CREATE TABLE Invoice (
    InvoiceID INT PRIMARY KEY AUTO_INCREMENT,
    TotalCost DECIMAL(12, 2) NOT NULL
);

CREATE TABLE RoomType (
    RoomTypeID INT PRIMARY KEY AUTO_INCREMENT,
    RoomSize VARCHAR(50) NOT NULL,
    RoomBasePrice DECIMAL(8, 2) NOT NULL,
    ExtraAdultPrice DECIMAL(5, 2) NOT NULL,
    StandardOccupancy INT NOT NULL,
    MaximumOccupancy INT NOT NULL
);

CREATE TABLE Room (
    RoomNumber CHAR(8) PRIMARY KEY,
    ADAAccessible BOOL NOT NULL DEFAULT 0,
    RoomTypeID INT NOT NULL,
    FOREIGN KEY fk_Room_RoomType (RoomTypeID)
        REFERENCES RoomType (RoomTypeID)
);

CREATE TABLE Amenity (
    AmenityID INT PRIMARY KEY AUTO_INCREMENT,
    AmenityName VARCHAR(50) NOT NULL,
    AmenityCost DECIMAL(6, 2) NOT NULL
);

CREATE TABLE RoomAmenity (
    RoomNumber CHAR(8) NOT NULL,
    AmenityID INT NOT NULL,
    PRIMARY KEY pk_RoomAmenity (RoomNumber, AmenityID),
    FOREIGN KEY fk_RoomAmenity_Room (RoomNumber)
        REFERENCES Room(RoomNumber),
    FOREIGN KEY fk_RoomAmenity_Amenity (AmenityID)
        REFERENCES Amenity (AmenityID)
);

CREATE TABLE Guest (
    GuestID INT PRIMARY KEY AUTO_INCREMENT,
    FirstName VARCHAR(50) NOT NULL,
    LastName VARCHAR(50) NOT NULL,
    Address VARCHAR(250) NOT NULL,
    City VARCHAR(100) NOT NULL,
    StateAbbr CHAR(2) NOT NULL,
    PostalCode VARCHAR(10) NOT NULL,
    Phone VARCHAR(30) NOT NULL
);

CREATE TABLE Reservation (
    ReservationID INT PRIMARY KEY AUTO_INCREMENT,
    StartDate DATE NOT NULL,
    EndDate DATE NOT NULL,
    GuestID INT NOT NULL,
    InvoiceID INT NOT NULL,
    FOREIGN KEY fk_Reservation_Guest (GuestID)
        REFERENCES Guest (GuestID),
    FOREIGN KEY fk_Reservation_Invoice (InvoiceID)
        REFERENCES Invoice (InvoiceID)
);

CREATE TABLE RoomReservation (
    RoomNumber CHAR(8) NOT NULL,
    ReservationID INT NOT NULL,
    AdultOccupancy INT NOT NULL,
	ChildOccupancy INT NOT NULL,
    TotalRoomCost DECIMAL (10,2) NOT NULL,
    PRIMARY KEY pk_RoomReservation (RoomNumber, ReservationID),
    FOREIGN KEY fk_RoomReservation_Room (RoomNumber)
        REFERENCES Room(RoomNumber),
    FOREIGN KEY fk_RoomReservation_Reservation (ReservationID)
        REFERENCES Reservation (ReservationID)
);
