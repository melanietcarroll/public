/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.carlot;

import com.mc.carlot.controller.CarLotController;
import com.mc.carlot.dao.CarLotDAO;
import com.mc.carlot.dao.CarLotDAOFileImpl;
import com.mc.carlot.service.CarLotNoSuchCarException;
import com.mc.carlot.service.CarLotOverpaidPriceException;
import com.mc.carlot.service.CarLotService;
import com.mc.carlot.service.CarLotServiceLayerImpl;
import com.mc.carlot.service.CarLotUnderpaidPriceException;
import com.mc.carlot.ui.CarLotView;
import com.mc.carlot.ui.UserIO;
import com.mc.carlot.ui.UserIOConsoleImpl;

/**
 *
 * @author Shawn
 */
public class App {
    public static void main(String[] args) throws CarLotNoSuchCarException, CarLotOverpaidPriceException, CarLotUnderpaidPriceException{
UserIO myIo = new UserIOConsoleImpl();
CarLotView myView = new CarLotView(myIo);
CarLotDAO myDao = new CarLotDAOFileImpl();
CarLotService myService = new CarLotServiceLayerImpl(myDao);
CarLotController controller = new CarLotController(myService, myView);
controller.run();
}
}
