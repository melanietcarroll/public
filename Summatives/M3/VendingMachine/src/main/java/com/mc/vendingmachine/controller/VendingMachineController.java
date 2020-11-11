/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.vendingmachine.controller;

import com.mc.vendingmachine.dao.VendingMachinePersistenceException;
import com.mc.vendingmachine.dto.Change;
import com.mc.vendingmachine.dto.Item;
import com.mc.vendingmachine.service.VendingMachineDataValidationException;
import com.mc.vendingmachine.service.InsufficientFundsException;
import com.mc.vendingmachine.service.NoItemInventoryException;
import com.mc.vendingmachine.service.VendingMachineDuplicateItemNameException;
import com.mc.vendingmachine.service.VendingMachineService;
import com.mc.vendingmachine.ui.VendingMachineView;
import java.math.BigDecimal;
import java.util.List;

/**
 * created 11/7/20
 *
 * @author Melanie Carroll
 */
public class VendingMachineController {

    private VendingMachineService service;  //declaration for the Service layer
    private VendingMachineView view;

    public VendingMachineController(VendingMachineService service, VendingMachineView view) {   //constructor
        this.service = service;
        this.view = view;
    }

    public void run() throws VendingMachineDataValidationException, NoItemInventoryException, InsufficientFundsException, VendingMachinePersistenceException, VendingMachineDuplicateItemNameException {
        boolean keepGoing = true;
        int menuSelection = 0;
        try {
            while (keepGoing) {

                view.displayInventoryItemList(service.getAllItemsInInventory());
                menuSelection = getMenuSelection();

                switch (menuSelection) {
                    case 1:
                        addItemsToBuy();
                        break;
                    case 2:
                        editItems();
                        break;
                    case 3:
                        deleteItems();
                        break;
                    case 4:
                        getAllItems();
                        break;
                    case 5:
                        selectItemToBuy();
                        break;
                    case 6:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }

            }
            exitMessage();
        } catch (VendingMachinePersistenceException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }

    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }

    private void addItemsToBuy() throws VendingMachinePersistenceException, VendingMachineDuplicateItemNameException {
        boolean hasErrors = false;
        do {
            Item currentItem = view.getItemInfo();
            try {

                service.addItem(currentItem.getItemName(), currentItem);
                hasErrors = false;
            } catch (VendingMachineDataValidationException | VendingMachineDuplicateItemNameException e) {
                hasErrors = true;
                view.displayErrorMessage(e.getMessage());
            }
        } while (hasErrors);
    }

    private void editItems() throws VendingMachinePersistenceException, VendingMachineDataValidationException {
        
        boolean hasErrors = false;
        do {
            Item editedItem = view.getItemInfo();
            try {
                service.editItem(editedItem.getItemName(), editedItem);
                hasErrors = false;
            } catch (VendingMachineDataValidationException e) {
                hasErrors = true;
                view.displayErrorMessage(e.getMessage());
            }
        } while (hasErrors);
    }

    private void deleteItems() throws VendingMachinePersistenceException {
        String item = view.getItemChoice(); 
        try {
                 service.isFieldEmpty(item);
            } catch (VendingMachineDataValidationException e) {
                view.displayErrorMessage(e.getMessage());
                return;
            }      
            
            try {
                service.getItem(item.toUpperCase());
            } catch (VendingMachineDataValidationException |NoItemInventoryException e) {              
                view.displayErrorMessage(e.getMessage());
                return;
            }
        service.deleteItem(item);
    }

    private void getAllItems() throws VendingMachinePersistenceException, NoItemInventoryException {
        List<Item> itemList = service.getAllItems();

        view.displayItemList(itemList);
    }

    public void selectItemToBuy() throws VendingMachineDataValidationException, NoItemInventoryException, VendingMachinePersistenceException, InsufficientFundsException {

        String money = view.getMoneyAmount();

        try {
            service.addMoney(money);

        } catch (VendingMachineDataValidationException e) {
            view.displayErrorMessage(e.getMessage());
            return;
        }

        BigDecimal moneyAdded = new BigDecimal(money.replaceAll(",", ""));

            String choice = view.getItemChoice();
            try {
                 service.isFieldEmpty(choice);
            } catch (VendingMachineDataValidationException e) {
                view.displayErrorMessage(e.getMessage());
                return;
            }      
            
            try {
                service.getItem(choice.toUpperCase());
            } catch (VendingMachineDataValidationException e) {              
                view.displayErrorMessage(e.getMessage());
                return;
            }
        
        try {
            service.updateItemToBuyInventory(choice, moneyAdded);
        } catch (InsufficientFundsException | NoItemInventoryException e) {
            view.displayErrorMessage(e.getMessage());
            return;
        }
        BigDecimal change = service.buyItem(choice, moneyAdded);
        Change myChange = service.giveChange(change);
        view.displayChange(myChange);
    }

    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        view.displayExitBanner();
    }
}
