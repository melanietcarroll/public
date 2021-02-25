/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.vendingmachine.dao;

import com.mc.vendingmachine.dto.Change;
import com.mc.vendingmachine.dto.Item;
import com.mc.vendingmachine.service.InsufficientFundsException;
import com.mc.vendingmachine.service.NoItemInventoryException;
import java.math.BigDecimal;
import java.util.List;

/**
 *  created 11/7/20
 * @author Melanie Carroll
 */
public interface VendingMachineDao {
   /**
     * Adds the given item to the vending machine and associates it with the given
     * item name. 
     *
     * @param item name with which item is to be associated
     * @param item item to be added to the list
     * @return the Item object associated with the given  
     * item name if it exists, null otherwise
     */
    Item addItem(String itemName, Item item)throws VendingMachinePersistenceException;

    /**
     * Returns a List of all items in the vending machine.
     *
     * @return List containing all items in the vending machine.
     */
    List<Item> getAllItems()throws VendingMachinePersistenceException;

    /**
     * Returns the item object associated with the given item name.
     * Returns null if no such item exists
     *
     * @param item name of the item to retrieve
     * @return the Item object associated with the given item name,  
     * null if no such item exists
     */
    Item getItem(String itemName)throws VendingMachinePersistenceException;

    /**
     * Removes from the vending machine the item associated with the item name.
     * Returns the item object that is being removed or null if
     * there is no item associated with the item name
     *
     * @param item name of items to be removed
     * @return Item object that was removed or null if no item
     * was associated with the given item name
     */
    Item deleteItem(String itemName)throws VendingMachinePersistenceException;
    /**
     * Returns the item object associated with the given item name.
     * Returns null if no such item exists
     *
     * @param item name of the item to retrieve
     * @return the edited item object associated with the given item name,  
     * null if no such item exists
     */
    Item editItem(String itemName, Item item)throws VendingMachinePersistenceException;
    /**
     * @param BigDecimal pennies 
     * @return the int number to pennies 
     */
    int giveChange (BigDecimal pennies);
    /**
     * @param item name of the item to retrieve
     * @set inventory getter of the item object associated with the given item name,  
     */
    void updateItemInventory(String itemName)throws VendingMachinePersistenceException;
}

