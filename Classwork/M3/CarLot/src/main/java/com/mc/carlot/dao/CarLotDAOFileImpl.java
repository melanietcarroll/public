/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.carlot.dao;

import com.mc.carlot.dto.Car;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Shawn
 */
public class CarLotDAOFileImpl implements CarLotDAO{
    
    
    private Map<String, Car> cars = new HashMap<>();

@Override
public Car addCar(String VIN, Car car){
Car currentCar = cars.put(VIN, car);
return currentCar;
}

@Override
public Car getCar(String VIN){
return cars.get(VIN);
}

@Override
public List<Car> getCars(){
return new ArrayList<>(cars.values());
}

@Override
public Car removeCar(String VIN){
Car removedCar = cars.remove(VIN);
return removedCar;
}

@Override
public Car editCar(String VIN, Car car){

Car editedCar = cars.put(VIN, car);
return editedCar;

}
}