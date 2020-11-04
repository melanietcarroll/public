/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.carlot.controller;
import com.mc.carlot.dao.CarLotPersistenceException;
import com.mc.carlot.dao.CarLotDAO;
import com.mc.carlot.dto.Car;
import com.mc.carlot.service.CarLotService;
import com.mc.carlot.ui.CarLotView;
import static java.awt.AWTEventMulticaster.add;
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

    public void run() {
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

    private void addCar()throws CarLotPersistenceException {
        view.displayCreateCarBanner();
        Car newCar = view.getCarInfo();
        dao.addCar(newCar.getVIN(), newCar);
        view.displayCreateSuccessBanner();
    }
private void getCars() throws CarLotPersistenceException{
    List<Car> carList = service.getAllCars();
     
    view.displayCarsList(carList);
}

private void getCar()throws CarLotPersistenceException {
     String carVIN = view.getVIN();
     Car car = service.getACar(carVIN) ;
     view.displayCar(car);
}

private void editCar()throws CarLotPersistenceException {
        view.displayEditCarBanner();
//        String dvdId = view.getDVDChoice();
        Car editedCar = view.getCarInfo();
        dao.editCar(editedCar.getVIN(), editedCar);
        view.displayCreateSuccessBanner();
    }


    private void unknownCommand() {
    view.displayUnknownCommandBanner();
}

private void exitMessage() {
    view.displayExitBanner();
}


    
}
