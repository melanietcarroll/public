/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.vendingmachine.service;

import com.mc.vendingmachine.dao.VendingMachineAuditDao;
import com.mc.vendingmachine.dto.CoinValues;
import com.mc.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.util.List;
import com.mc.vendingmachine.dao.VendingMachineDao;
import com.mc.vendingmachine.dao.VendingMachinePersistenceException;
import com.mc.vendingmachine.dto.Change;
import static com.mc.vendingmachine.dto.CoinValues.QUARTER;
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

    private VendingMachineAuditDao auditDao;

    public VendingMachineServiceImpl(VendingMachineDao dao, VendingMachineAuditDao auditDao) {
        this.dao = dao;
        this.auditDao = auditDao;
    }

    VendingMachineDao dao;

    public VendingMachineServiceImpl(VendingMachineDao dao) {
        this.dao = dao;
    }

    @Override
    public Item addItem(String itemName, Item item) throws VendingMachinePersistenceException, VendingMachineDataValidationException, VendingMachineDuplicateItemNameException {
        if (dao.getItem(item.getItemName()) != null) {
        throw new VendingMachineDuplicateItemNameException(
                "ERROR: Could not create item.  Item "
                + item.getItemName()
                + " already exists");
    }
        
        validateItemData(item);
        auditDao.writeAuditEntry(
                "Item " + item.getItemName() + " CREATED.");
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
    public Item getItem(String itemName) throws NoItemInventoryException, VendingMachinePersistenceException, VendingMachineDataValidationException {
        //if greater than zero inventory
        Item currentItem = dao.getItem(itemName);
//        if (itemName == null){
//            throw new VendingMachineDataValidationException("ERROR: no such item.");
//            
//        }
        if (currentItem.getInventoryOfItem() < 0) {
            throw new NoItemInventoryException("ERROR: Zero inventory for selected item.");
        }
        return currentItem;
    }

    @Override
    public Item deleteItem(String itemName) throws VendingMachinePersistenceException {
 Item removedItem = dao.deleteItem(itemName.toUpperCase());        
// Write to audit log
        auditDao.writeAuditEntry("Item " + itemName + " REMOVED.");
        return removedItem;
    }

    @Override
    public Item editItem(String itemName, Item item) throws VendingMachinePersistenceException {
        // Write to audit log
        auditDao.writeAuditEntry("Item " + itemName + " EDITED.");
        return dao.editItem(itemName, item);
    }

    @Override
    public BigDecimal addMoney(String amount) throws VendingMachineDataValidationException {
        validateNumericData(amount);
        BigDecimal moneyAdded = new BigDecimal(amount);
        isMoneyInRange(moneyAdded);
        return moneyAdded;
    }

    @Override
    public BigDecimal buyItem(String itemName, BigDecimal money) throws NoItemInventoryException, InsufficientFundsException, VendingMachinePersistenceException {
        Item currentItem = dao.getItem(itemName);
        BigDecimal itemPrice = currentItem.getPrice();
        BigDecimal changeDue = new BigDecimal("0");
        BigDecimal multiplyForPennyConversion = new BigDecimal("100");

        if (money.compareTo(itemPrice) > 0) {
            changeDue = money.subtract(itemPrice).setScale(2, RoundingMode.HALF_UP);
            BigDecimal changeDuePennies = new BigDecimal(changeDue.toString()).multiply(multiplyForPennyConversion);
            auditDao.writeAuditEntry("Item " + itemName + " bought.");
            
            return changeDuePennies;
        }
        if (money.compareTo(itemPrice) < 1) {
            throw new InsufficientFundsException("You do not have enough money to purchase this item. You only have " + money);
        }
        // Write to audit log
        
        return changeDue;
    }

    @Override
    public Change giveChange(BigDecimal pennies) {
        int change = pennies.intValue();
        Change myChange = new Change(change);

        int quarters = Math.round((int) change / 25);
        myChange.setNumQuarters(quarters);
        change = change % 25;
        int dimes = Math.round((int) change / 10);
        myChange.setNumDimes(dimes);
        change = change % 10;
        int nickels = Math.round((int) change / 5);
        myChange.setNumNickels(nickels);
        change = change % 5;
        int totalPennies = Math.round((int) change / 1);
        myChange.setNumPennies(totalPennies);

        return myChange;
    }

    @Override
    public void updateItemToBuyInventory(String itemName, BigDecimal money) throws NoItemInventoryException, InsufficientFundsException, VendingMachinePersistenceException {

        Item currentItem = dao.getItem(itemName.toUpperCase());

        BigDecimal price = currentItem.getPrice();
        if (currentItem.getInventoryOfItem() < 1) {
            throw new NoItemInventoryException("No items left!");
        }
        if (price.equals(money) || price.compareTo(money) < 0) {
            dao.updateItemInventory(itemName.toUpperCase());
        }
        if (price.compareTo(money) > 0) {
            throw new InsufficientFundsException("You do not have enough money for this selection. You only have $" + money);

        }

    }

    @Override
    public List<Item> getAllItems() throws VendingMachinePersistenceException, NoItemInventoryException {
        return dao.getAllItems();
    }
     @Override
    public void isFieldEmpty(String string) throws VendingMachineDataValidationException{
        
         if (string == null || string.trim().length() == 0){
            throw new VendingMachineDataValidationException("ERROR: All Fields are required.");
    }
    }

    public void validateItemData(Item item) throws VendingMachineDataValidationException {

        String inventory = Integer.toString(item.getInventoryOfItem());
        if (item.getPrice() == null
                || item.getItemName() == null || item.getItemName().trim().length() == 0
                || item.getInventoryOfItem() < 0 || inventory.trim().length() == 0) {
            throw new VendingMachineDataValidationException("ERROR: All Fields are required.");
        }

        if (item.getPrice().compareTo(BigDecimal.valueOf(0)) < 0
                || item.getPrice().compareTo(BigDecimal.valueOf(10)) > 0) {
            throw new VendingMachineDataValidationException("ERROR: Dollar range must be greater than 0 and less than 10.");
        }
        if (item.getInventoryOfItem() < 0) {
            throw new VendingMachineDataValidationException("ERROR: Inventory cannot be less than zero.");
        }
    }

    public void validateNumericData(String string) throws VendingMachineDataValidationException {

        boolean isNumeric = true;

        try {
            Double.parseDouble(string);
            isNumeric = true;
        } catch (NumberFormatException e) {
            isNumeric = false;
            throw new VendingMachineDataValidationException("ERROR: You must enter a number!");
        }

        if (string == null) {
            throw new VendingMachineDataValidationException("ERROR: Field must be numeric.");
        }

    }

    public void isMoneyInRange(BigDecimal amount) throws VendingMachineDataValidationException {
        if (amount.compareTo(BigDecimal.valueOf(0)) <= 0 || amount.compareTo(BigDecimal.valueOf(10)) > 0) {
            throw new VendingMachineDataValidationException("ERROR: Dollar range must be greater than 0 and less than 10.");

        }

    }
}
