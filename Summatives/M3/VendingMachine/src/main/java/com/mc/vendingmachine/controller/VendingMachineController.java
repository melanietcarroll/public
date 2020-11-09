/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.vendingmachine.controller;

import com.mc.vendingmachine.dao.VendingMachinePersistenceException;
import com.mc.vendingmachine.dto.Change;
import com.mc.vendingmachine.dto.Item;
import com.mc.vendingmachine.service.IncorrectMoneyException;
import com.mc.vendingmachine.service.InsufficientFundsException;
import com.mc.vendingmachine.service.NoItemInventoryException;
import com.mc.vendingmachine.service.VendingMachineService;
import com.mc.vendingmachine.ui.VendingMachineView;
import java.math.BigDecimal;
import java.util.List;

/**
 *  created 11/7/20
 * @author Melanie Carroll
 */
public class VendingMachineController {
    private VendingMachineService service;  //declaration for the Service layer
    private VendingMachineView view;
    
public VendingMachineController(VendingMachineService service, VendingMachineView view) {   //constructor
    this.service = service;
    this.view = view;
}    
    
    public void run() throws IncorrectMoneyException, NoItemInventoryException, InsufficientFundsException {
    boolean keepGoing = true;
    int menuSelection = 0;
    try {
        while (keepGoing) {

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
   private void addItemsToBuy() throws VendingMachinePersistenceException {
    view.displayCreateItemBanner();
    Item newItem = view.getItemInfo();
    service.addItem(newItem.getItemName(), newItem);
    view.displayCreateSuccessBanner();
} 
   
   private void editItems() throws VendingMachinePersistenceException {
    Item editedItem = view.getItemInfo();
        service.editItem(editedItem.getItemName(), editedItem);
}
   private void deleteItems() throws VendingMachinePersistenceException{
       String item = view.getItemChoice();
    service.deleteItem(item);
   }
   private void getAllItems() throws  VendingMachinePersistenceException, NoItemInventoryException{
        List<Item> itemList = service.getAllItems();

        view.displayItemList(itemList);
   }
   
   
   
   
   public void selectItemToBuy()throws IncorrectMoneyException, NoItemInventoryException, VendingMachinePersistenceException, InsufficientFundsException{
       String money = view.getMoneyAmount();
       try{
       BigDecimal moneyAdded = new BigDecimal(money);
       int amntMoney = Integer.parseInt(money);
       
       if (amntMoney < 0 || amntMoney > 10 ){
          money = view.getMoneyAmount();
       }
       service.addMoney(money);
   }catch (IncorrectMoneyException e){
       view.displayErrorMessage(e.getMessage());
       
       
       String choice = view.getItemChoice().toUpperCase();
       Item item = service.getItem(choice);
       BigDecimal itemPrice = item.getPrice();
       BigDecimal balance = service.addMoney(view.getMoneyAmount());
       int inventory = service.updateItemToBuyInventory(choice, balance);
       BigDecimal change = service.buyItem(choice, balance);
       Change myChange = service.giveChange(change);
       view.displayChange(myChange);
   } 
   }
   
   private void unknownCommand() {
    view.displayUnknownCommandBanner();
}

private void exitMessage() {
    view.displayExitBanner();
}
}
