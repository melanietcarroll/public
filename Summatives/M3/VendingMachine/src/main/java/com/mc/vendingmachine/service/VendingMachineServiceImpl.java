/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.vendingmachine.service;

import com.mc.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.util.List;
import com.mc.vendingmachine.dao.VendingMachineDao;
import com.mc.vendingmachine.dao.VendingMachinePersistenceException;
import com.mc.vendingmachine.service.InsufficientFundsException;
import com.mc.vendingmachine.service.NoItemInventoryException;
import java.math.RoundingMode;
import java.util.ArrayList;

/**
 * created 11/7/20
 *
 * @author Melanie Carroll
 */
public class VendingMachineServiceImpl implements VendingMachineService {

    VendingMachineDao dao;

    public VendingMachineServiceImpl(VendingMachineDao dao) {
        this.dao = dao;
    }

    @Override
    public Item addItem(String itemName, Item item) throws VendingMachinePersistenceException {
        return dao.addItem(itemName, item);
    }

    @Override
    public List<Item> getAllItemsInInventory() throws VendingMachinePersistenceException, NoItemInventoryException {
        //only list items with > 0 inventory
        List<Item> listItems = dao.getAllItems();
        List<Item> listItemsInventory = new ArrayList<>();
        for (Item item : listItems) {
            if (item.getInventoryOfItem() > 0) {
                listItemsInventory.add(item);
            }
        }
        return listItemsInventory;
    }

    @Override
    public Item getItem(String itemName) throws NoItemInventoryException, VendingMachinePersistenceException {
        //if greater than zero inventory
        Item currentItem = dao.getItem(itemName);
        if (currentItem.getInventoryOfItem() > 0) {
            return currentItem;
        }
        return currentItem;
    }

    @Override
    public Item deleteItem(String itemName) throws VendingMachinePersistenceException {
        return dao.deleteItem(itemName);
    }

    @Override
    public Item editItem(String itemName, Item item) throws VendingMachinePersistenceException {
        return dao.editItem(itemName, item);
    }

    @Override
    public BigDecimal addMoney(String amount) throws IncorrectMoneyException {
        BigDecimal moneyAdded = new BigDecimal("0");
       
        try{
        BigDecimal money = new BigDecimal(amount);
        
            if (money.intValue() > 0 && money.intValue() <= 10) {
                return money;
            }
            if (money.intValue() < 0 || money.intValue() > 10 ) {
                throw new IncorrectMoneyException("You must add between 0 and 10 dollars.");
            }
            return money;
        }catch (IncorrectMoneyException e){
            System.out.println("Incorrect value. Enter a valid number.");
        }
        return moneyAdded;
    }

    @Override
    public int updateItemToBuyInventory(String itemName, BigDecimal money) throws NoItemInventoryException, InsufficientFundsException {
        //throws NoItemIventoryException
        //throws InsufficientFundsException
        Item currentItem = dao.getItem(itemName);
        int inventory = currentItem.getInventoryOfItem();
        BigDecimal price = currentItem.getPrice();
        if (price.equals(money) || price.compareTo(money) > 0) {
            inventory = inventory - 1;
            return inventory;
        }
        if (price.compareTo(money) < 0) {
            throw new InsufficientFundsException("You do not have enough money for this selection. You only have " + money);

        }
        return inventory;
    }

    @Override
    public BigDecimal giveChange() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BigDecimal buyItem(String itemName, BigDecimal money) throws NoItemInventoryException, InsufficientFundsException {
        Item currentItem = dao.getItem(itemName);
        BigDecimal itemPrice = currentItem.getPrice();
        BigDecimal changeDue = new BigDecimal("0");
        BigDecimal multiplyForPennyConversion = new BigDecimal("100");

        if (money.equals(itemPrice)) {
            return changeDue;
        }
        if (money.compareTo(itemPrice) > 0) {
            changeDue = money.subtract(itemPrice).setScale(2,RoundingMode.HALF_UP);
            BigDecimal changeDuePennies = new BigDecimal(changeDue.toString()).multiply(multiplyForPennyConversion);
            return changeDuePennies;
        }
        if (money.compareTo(itemPrice) < 1) {
            throw new InsufficientFundsException("You do not have enough money to purchase this item. You only have " + money);
        }
        return changeDue;
    }

    @Override
    public List<Item> getAllItems() throws VendingMachinePersistenceException, NoItemInventoryException {
        return dao.getAllItems();
    }

}
