/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.carlot.controller;

import com.mc.carlot.dao.CarLotPersistenceException;
import com.mc.carlot.dao.CarLotDAO;
import com.mc.carlot.dto.Car;
import com.mc.carlot.dto.CarKey;
import com.mc.carlot.service.CarLotNoSuchCarException;
import com.mc.carlot.service.CarLotOverpaidPriceException;
import com.mc.carlot.service.CarLotService;
import com.mc.carlot.service.CarLotUnderpaidPriceException;
import com.mc.carlot.ui.CarLotView;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Shawn
 */
public class CarLotController {

    private CarLotService service;  //declaration for the CarLotService layer
    private CarLotView view;
    private CarLotDAO dao;

    public CarLotController(CarLotService service, CarLotView view) {	//constructor
        this.service = service;
        this.view = view;
    }

    public void run() throws CarLotNoSuchCarException, CarLotOverpaidPriceException, CarLotUnderpaidPriceException {
        boolean keepGoing = true;
        int menuSelection = 0;
        try {
            while (keepGoing) {

                menuSelection = getMenuSelection();

                switch (menuSelection) {
                    case 1:
                        addCar();
                        break;
                    case 2:
                        getCar();
                        break;
                    case 3:
                        getCars();
                        break;
                    case 4:
                        editCar();
                        break;
                    case 5:
                        getCarByColorList();
                        break;
                    case 6:
                        getCarMaxPrice();
                        break;
                    case 7:
                        getCarMakeModel();
                        break;
                    case 8:
                        setDiscount();
                        break;
                    case 9:
                        buyCar();
                        break;
                    case 10:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }

            }
            exitMessage();
        } catch (CarLotPersistenceException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }

    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }

    private void addCar() throws CarLotPersistenceException {
        view.displayCreateCarBanner();
        Car newCar = view.getCarInfo();
        service.addCar(newCar.getVIN(), newCar);
        view.displayCreateSuccessBanner();
    }

    private void getCars() throws CarLotPersistenceException {
        List<Car> carList = service.getAllCars();

        view.displayCarsList(carList);
    }

    private void getCar() throws CarLotPersistenceException {
        String carVIN = view.getVIN();
        Car car = service.getACar(carVIN);
        view.displayCar(car);
    }

    private void editCar() throws CarLotPersistenceException {
        view.displayEditCarBanner();
//        String dvdId = view.getDVDChoice();
        Car editedCar = view.getCarInfo();
        service.editCar(editedCar.getVIN(), editedCar);
        view.displayCreateSuccessBanner();
    }

    private void getCarByColorList() {
        view.displayGetCarsByColorBanner();
        String color = view.getColor();
        List<Car> carColorList = service.getCarsByColor(color);
        view.displayCarsList(carColorList);
    }

    private void getCarMaxPrice() {
        view.displayCarsByMaxPriceBanner();
        BigDecimal maxPrice = view.getBudget();
        List<Car> carPriceList = service.getCarsInBudget(maxPrice);
        view.displayCarsList(carPriceList);

    }

    private void getCarMakeModel() {
        view.displayCarByMakeModelBanner();
        String make = view.getMake();
        String model = view.getModel();
        List<Car> listCarsByMakeModel = service.getCarByMakeAndModel(make, model);
        view.displayCarsList(listCarsByMakeModel);

    }

    private void setDiscount() throws CarLotNoSuchCarException {
        view.setDiscountBanner();
        String vinNumber = view.getVIN();
        BigDecimal discountAmount = view.getDiscount();
        BigDecimal discountedCar = service.discountCar(vinNumber, discountAmount);
        view.displayDiscount(discountedCar);
    }

    private void buyCar() throws CarLotNoSuchCarException, CarLotOverpaidPriceException, CarLotUnderpaidPriceException {
        String carVIN = view.getVIN();
        BigDecimal maxPrice = view.getBudget();
        CarKey buyCar = service.sellCar(carVIN, maxPrice);
        view.displayBoughtCarKey(buyCar);
    }

    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        view.displayExitBanner();
    }
}
