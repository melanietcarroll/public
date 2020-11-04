/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.carlot.ui;

import com.mc.carlot.dto.Car;
import com.mc.carlot.dto.CarKey;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Shawn
 */
public class CarLotView {

    private UserIO io;

    public CarLotView(UserIO io) {
        this.io = io;
    }

    public int printMenuAndGetSelection() {
        io.print("Main Menu");
        io.print("1. Add Car");
        io.print("2. Get A Car");
        io.print("3. List All Cars");
        io.print("4. Edit Car");
        io.print("5. Exit");

        return io.readInt("Please select from the above choices.", 1, 5);
    }

    public void displayCreateCarBanner() {
        io.print("=== Create Car ===");
    }

    public void displayCreateSuccessBanner() {
        io.readString(
                "Car successfully created.  Please hit enter to continue");
    }

    public Car getCarInfo() {
        String carVIN = io.readString("Please enter vehicle's VIN");
        String make = io.readString("Please enter vehicle's make");
        String model = io.readString("Please enter vehicle's model");
        String color = io.readString("Please enter vehicle's color");
        String price = io.readString("Please enter vehicle's price");
        long odometerMiles = io.readLong("Please enter vehicle's odometer reading in Miles");

        BigDecimal p = new BigDecimal(price);
        Car currentCar = new Car(carVIN);
        CarKey currentCarKey = new CarKey(carVIN);
        currentCarKey.setVIN(carVIN);
        currentCarKey.setLaserCut(true);
        currentCar.setVIN(carVIN);
        currentCar.setMake(make);
        currentCar.setModel(model);
        currentCar.setColor(color);
        currentCar.setPrice(p);
        currentCar.setOdometerMiles(odometerMiles);
        currentCar.setKey(currentCarKey);
        return currentCar;
    }

    public void displayCarsList(List<Car> carList) {
        for (Car currentCar : carList) {
            String carInfo = String.format("#%s : %s, %s, %s, %s, %s, %s",
                    currentCar.getVIN(),
                    currentCar.getMake(),
                    currentCar.getModel(),
                    currentCar.getColor(),
                    currentCar.getPrice(),
                    currentCar.getOdometerMiles(),
                    currentCar.getKey());
            io.print(carInfo);
        }
        io.readString("Please hit enter to continue.");
    }
    public String getVIN() {
    return io.readString("Please enter the vehicle's VIN.");
}
    public void displayCar(Car car) {
    if (car != null) {
        io.print(car.getVIN());
        io.print(car.getMake() + " " + car.getModel());
        io.print("color: " + car.getColor());
        io.print("price: " + car.getPrice());
        io.print("odometer reading: " + car.getOdometerMiles());
        io.print("car key: " + car.getKey());
        io.print("");
    } else {
        io.print("No such car.");
    }
    io.readString("Please hit enter to continue.");
}
    public void displayEditCarBanner(){
    io.print("--- Edit CAR ---");
}
    public void displayExitBanner() {
    io.print("Good Bye!!!");
}

public void displayUnknownCommandBanner() {
    io.print("Unknown Command!!!");
}
public void displayErrorMessage(String errorMsg) {
    io.print("=== ERROR ===");
    io.print(errorMsg);
}
}
