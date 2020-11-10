/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.vendingmachine.service;
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

    VendingMachineDao dao;

    public VendingMachineServiceImpl(VendingMachineDao dao) {
        this.dao = dao;
    }

    @Override
    public Item addItem(String itemName, Item item) throws VendingMachinePersistenceException, VendingMachineDataValidationException {
        validateItemData(item);
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
        if (currentItem.getInventoryOfItem() < 0) {
throw new NoItemInventoryException("ERROR: Zero inventory for selected item.");
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
    public BigDecimal addMoney(String amount) throws VendingMachineDataValidationException {
        validateNumericData(amount);
        BigDecimal moneyAdded = new BigDecimal(amount);
        return moneyAdded;
    }

     @Override
    public BigDecimal buyItem(String itemName, BigDecimal money) throws NoItemInventoryException, InsufficientFundsException {
        Item currentItem = dao.getItem(itemName);
        BigDecimal itemPrice = currentItem.getPrice();
        BigDecimal changeDue = new BigDecimal("0");
        BigDecimal multiplyForPennyConversion = new BigDecimal("100");

        
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
    public Change giveChange(BigDecimal pennies) {
        int change = pennies.intValue();
        Change myChange = new Change(change);
        
    int quarters = Math.round((int)change/25);
    myChange.setNumQuarters(quarters);
    change=change%25;
    int dimes = Math.round((int)change/10);
    myChange.setNumDimes(dimes);
    change=change%10;
    int nickels = Math.round((int)change/5);
    myChange.setNumNickels(nickels);
    change=change%5;
    int totalPennies = Math.round((int)change/1);
    myChange.setNumPennies(totalPennies);


//    System.out.println("Quarters: " + quarters);
//    System.out.println("Dimes: " + dimes);
//    System.out.println("Nickels: " + nickels);
//    System.out.println("Pennies: " + pennies);
//        if (changeDue >= CoinValues.QUARTER.value){
//            changeDue = changeDue / CoinValues.QUARTER.value;
//            myChange.setNumQuarters(changeDue);
//            int remainingQuarterChange = (changeDue * CoinValues.QUARTER.value) - (changeDue  * CoinValues.QUARTER.value);
//            int dimeChange = remainingQuarterChange / CoinValues.DIME.value;
//            myChange.setNumDimes(dimeChange);
//            int remainingDimeChange = (dimeChange * CoinValues.DIME.value) - (dimeChange * CoinValues.DIME.value);
//            int nickelChange = remainingDimeChange / CoinValues.NICKEL.value;
//            myChange.setNumNickels(nickelChange);
//            int remainingNickelChange = (nickelChange * CoinValues.NICKEL.value) - (nickelChange * CoinValues.NICKEL.value);
//            int pennyChange = remainingNickelChange / CoinValues.PENNY.value;
//            myChange.setNumPennies(pennyChange);
//            return myChange;
//        }
//        if (changeDue >= CoinValues.DIME.value && changeDue < CoinValues.QUARTER.value){
//            changeDue = changeDue / CoinValues.DIME.value;
//            myChange.setNumDimes(changeDue);
//            int remainingDimeChange = (changeDue * CoinValues.DIME.value) - (changeDue * CoinValues.DIME.value);
//          
//            int nickelChange = remainingDimeChange / CoinValues.NICKEL.value;
//            myChange.setNumNickels(nickelChange);
//            int remainingNickelChange = (nickelChange * CoinValues.NICKEL.value) - (nickelChange * CoinValues.NICKEL.value);
//            int pennyChange = remainingNickelChange / CoinValues.PENNY.value;
//            myChange.setNumPennies(pennyChange);
//            return myChange;
//        }
//        if (changeDue >= CoinValues.NICKEL.value && changeDue < CoinValues.DIME.value){
//            changeDue = changeDue / CoinValues.NICKEL.value;
//            myChange.setNumNickels(changeDue);
//            int remainingNickelChange = (changeDue * CoinValues.NICKEL.value) - (changeDue * CoinValues.NICKEL.value);
//            int pennyChange = remainingNickelChange / CoinValues.PENNY.value;
//            myChange.setNumPennies(pennyChange);
//            return myChange;
//        }
//        if (changeDue < CoinValues.NICKEL.value){
//            changeDue = changeDue / CoinValues.PENNY.value;
//            myChange.setNumPennies(changeDue);
//            return myChange;
//        }   
        return myChange;
    }

     @Override
    public void updateItemToBuyInventory(String itemName, BigDecimal money) throws NoItemInventoryException, InsufficientFundsException {
        //throws NoItemIventoryException
        //throws InsufficientFundsException
        Item currentItem = dao.getItem(itemName);
        
        BigDecimal price = currentItem.getPrice();
        if (price.equals(money) || price.compareTo(money) < 0) {
            currentItem.setInventoryOfItem(currentItem.getInventoryOfItem() - 1);
            }
        if (price.compareTo(money) > 0) {
            throw new InsufficientFundsException("You do not have enough money for this selection. You only have $ " + money);

        }
        
    }

    @Override
    public List<Item> getAllItems() throws VendingMachinePersistenceException, NoItemInventoryException {
        return dao.getAllItems();
    }
public void validateItemData(Item item) throws VendingMachineDataValidationException{
String inventory = Integer.toString(item.getInventoryOfItem()); 
if (item.getPrice() == null || item.getPrice().toString().trim().length() == 0 
	||item.getItemName()== null || item.getItemName().trim().length() == 0
	||item.getInventoryOfItem() == 0 || inventory.trim().length() == 0){
throw new VendingMachineDataValidationException("ERROR: All Fields are required.");
}
}
public void validateNumericData(String string) throws VendingMachineDataValidationException{

boolean isNumeric = true;
double num = Double.parseDouble(string);

try {  
    Double.parseDouble(string);  
    isNumeric = true;
  } catch(NumberFormatException e){  
    isNumeric = false;  
  }  

if (isNumeric == false || string == null){
throw new VendingMachineDataValidationException("ERROR: Field must be numeric.");
}
if (num <= 0 || num > 10){
throw new VendingMachineDataValidationException("ERROR: Amount must be between 0 and 10.");
}

}
}
