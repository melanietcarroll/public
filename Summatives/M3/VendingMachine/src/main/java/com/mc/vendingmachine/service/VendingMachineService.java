/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.vendingmachine.service;

import com.mc.vendingmachine.dto.CoinValues;
import com.mc.vendingmachine.service.NoItemInventoryException;
import com.mc.vendingmachine.dao.VendingMachinePersistenceException;
import com.mc.vendingmachine.dto.Change;
import com.mc.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.util.List;

/**
 *  created 11/7/20
 * @author Melanie Carroll
 */
public interface VendingMachineService {
    Item addItem(String itemName, Item item) throws VendingMachinePersistenceException, VendingMachineDataValidationException ;
    List<Item> getAllItems()throws VendingMachinePersistenceException, NoItemInventoryException;
    List<Item> getAllItemsInInventory() throws VendingMachinePersistenceException, NoItemInventoryException;
    Item getItem(String itemName)throws NoItemInventoryException,VendingMachinePersistenceException;
    Item deleteItem(String itemName)throws VendingMachinePersistenceException ;
    Item editItem(String itemName, Item item)throws VendingMachinePersistenceException, VendingMachineDataValidationException ;
    
    public BigDecimal addMoney(String money) throws VendingMachineDataValidationException;
            
    public void updateItemToBuyInventory(String itemName, BigDecimal money) throws NoItemInventoryException, InsufficientFundsException, VendingMachinePersistenceException;
    
    public BigDecimal buyItem(String itemName, BigDecimal money)throws NoItemInventoryException, InsufficientFundsException, VendingMachinePersistenceException;
    
    public Change giveChange(BigDecimal pennies);
}
