/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.carlot.service;

import com.mc.carlot.dao.CarLotDAO;
import com.mc.carlot.dto.Car;
import com.mc.carlot.dto.CarKey;
import static java.awt.AWTEventMulticaster.add;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Shawn
 */
public class CarLotServiceLayerImpl implements CarLotService {

    CarLotDAO dao;

    public CarLotServiceLayerImpl(CarLotDAO dao) {
        this.dao = dao;
    }

    @Override
    public Car getACar(String VIN) {
        return dao.getCar(VIN);
    }

    @Override
    public List<Car> getAllCars() {
        return dao.getCars();
    }

    @Override
    public List<Car> getCarsByColor(String color) {
        List<Car> listCarsColors = dao.getCars().stream().filter((c) -> c.getColor() == color).collect(Collectors.toList());
        return listCarsColors;
    }

    @Override
    public List<Car> getCarsInBudget(BigDecimal maxPrice) {
        List<Car> listCars = dao.getCars();
        List<Car> listCarsInBudget = new ArrayList<>(listCars);
        for (Car car : listCars) {
            if (car.getPrice().compareTo(maxPrice) <= 0)   {
                listCarsInBudget.add(car);
            }
        }
        return listCarsInBudget;
    }
    @Override
    public List<Car> getCarByMakeAndModel(String make, String model){
                 
        List<Car> listCarsMakeModel = dao.getCars().stream().filter((x) -> x.getMake() == make)
                    .filter((y) -> y.getModel() == model).collect(Collectors.toList());
            return listCarsMakeModel;

        }
    

    @Override
    public BigDecimal discountCar(String VIN, BigDecimal percentDiscount) throws CarLotNoSuchCarException {
        Car carSelected = dao.getCar(VIN);
        if (carSelected == null) {
            throw new CarLotNoSuchCarException(
                    "No such car found.");
        }
        BigDecimal carPrice = carSelected.getPrice();
        BigDecimal discount = carPrice.multiply(percentDiscount);
        BigDecimal newPrice = carPrice.subtract(discount);
        
        carSelected.setPrice(newPrice);
        return newPrice;
    }

    @Override
    public CarKey sellCar(String VIN, BigDecimal cashPaid) throws CarLotNoSuchCarException,
            CarLotOverpaidPriceException, CarLotUnderpaidPriceException {
         Car carToBuy = dao.getCar(VIN);
         CarKey keyToCar = carToBuy.getKey();

        if (carToBuy == null){
	throw new CarLotNoSuchCarException("No such car found.");
}
	if (carToBuy.getPrice() == cashPaid){
//remove the car 
        dao.removeCar(VIN);
        }
	if (carToBuy.getPrice().compareTo(cashPaid)> 0){
            throw new CarLotUnderpaidPriceException(
            "You do not have enough money to buy the vehicle.");
        }
        if (carToBuy.getPrice().compareTo(cashPaid) < 0){
            throw new CarLotOverpaidPriceException("You overpaid for the vehicle.");
        }
        return keyToCar;
        }
    }
