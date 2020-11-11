/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.vendingmachine;

import com.mc.vendingmachine.controller.VendingMachineController;
import com.mc.vendingmachine.dao.VendingMachineAuditDao;
import com.mc.vendingmachine.dao.VendingMachineAuditDaoFileImpl;
import com.mc.vendingmachine.dao.VendingMachineDao;
import com.mc.vendingmachine.dao.VendingMachineDaoImpl;
import com.mc.vendingmachine.dao.VendingMachinePersistenceException;
import com.mc.vendingmachine.service.VendingMachineDataValidationException;
import com.mc.vendingmachine.service.InsufficientFundsException;
import com.mc.vendingmachine.service.NoItemInventoryException;
import com.mc.vendingmachine.service.VendingMachineDuplicateItemNameException;
import com.mc.vendingmachine.service.VendingMachineService;
import com.mc.vendingmachine.service.VendingMachineServiceImpl;
import com.mc.vendingmachine.ui.UserIO;
import com.mc.vendingmachine.ui.UserIOFileImpl;
import com.mc.vendingmachine.ui.VendingMachineView;

/**
 * created 11/7/20
 *
 * @author Melanie Carroll
 */
public class App {

    public static void main(String[] args) throws VendingMachineDataValidationException, NoItemInventoryException, InsufficientFundsException, VendingMachinePersistenceException, VendingMachineDuplicateItemNameException {
        // Instantiate the UserIO implementation
        UserIO myIo = new UserIOFileImpl();
        // Instantiate the View and wire the UserIO implementation into it
        VendingMachineView myView = new VendingMachineView(myIo);
        // Instantiate the DAO
        VendingMachineDao myDao = new VendingMachineDaoImpl();
        // Instantiate the Audit DAO
        VendingMachineAuditDao myAuditDao = new VendingMachineAuditDaoFileImpl();
        // Instantiate the Service Layer and wire the DAO and Audit DAO into it
        VendingMachineService myService = new VendingMachineServiceImpl(myDao, myAuditDao);
        // Instantiate the Controller and wire the Service Layer into it
        VendingMachineController controller = new VendingMachineController(myService, myView);
        // Kick off the Controller
        controller.run();

    }
}
