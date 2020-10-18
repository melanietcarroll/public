/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.classmodeling;

/**
 *
 * @author Shawn
 */
public class CarInventory {

    private int numberOfCars;
    private String makeOfCar;
    private String modelOfCar;
    private String yearMade;
    private double priceOfCar;

    public void setNumberOfCars(int numberOfCars) {
        this.numberOfCars = numberOfCars;
    }

    public void setMakeOfCar(String makeOfCar) {
        this.makeOfCar = makeOfCar;
    }

    public void setModelOfCar(String modelOfCar) {
        this.modelOfCar = modelOfCar;
    }

    public void setYearMade(String yearMade) {
        this.yearMade = yearMade;
    }

    public void setPriceOfCar(double priceOfCar) {
        this.priceOfCar = priceOfCar;
    }

    public int getNumberOfCars() {
        return numberOfCars;
    }

    public String getMakeOfCar() {
        return makeOfCar;
    }

    public String getModelOfCar() {
        return modelOfCar;
    }

    public String getYearMade() {
        return yearMade;
    }

    public double getPriceOfCar() {
        return priceOfCar;
    }

    public void addCar() {

    }

    public void removeCar() {

    }
}
