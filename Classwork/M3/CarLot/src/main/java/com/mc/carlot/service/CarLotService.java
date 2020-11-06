/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.carlot.service;
import com.mc.carlot.service.CarLotNoSuchCarException;
import com.mc.carlot.dto.Car;
import com.mc.carlot.dto.CarKey;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Shawn
 */
public interface CarLotService {
    public Car getACar(String VIN);    
    public List<Car> getAllCars();
    public List<Car> getCarsByColor(String color);
    public List<Car> getCarsInBudget(BigDecimal maxPrice);
    public List<Car> getCarByMakeAndModel(String make, String model);
    public Car addCar(String VIN, Car car); 
    public Car editCar(String VIN, Car car);   

    public BigDecimal discountCar(String VIN, BigDecimal percentDiscount)
        throws CarLotNoSuchCarException;

    public CarKey sellCar(String VIN, BigDecimal cashPaid)
        throws CarLotNoSuchCarException,
        CarLotOverpaidPriceException,
        CarLotUnderpaidPriceException;
    
}
