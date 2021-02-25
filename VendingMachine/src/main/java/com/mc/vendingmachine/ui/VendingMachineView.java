/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.vendingmachine.ui;

import com.mc.vendingmachine.dto.Change;
import com.mc.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.util.List;

/**
 * created 11/7/20
 *
 * @author Melanie Carroll
 */
public class VendingMachineView {

    private UserIO io;

    public VendingMachineView(UserIO io) {
        this.io = io;
    }

    public int printMenuAndGetSelection() {
        io.print("Vending Machine Menu:");
        io.print("1. Add items to the vending machine");
        io.print("2. Edit items in the vending machine");
        io.print("3. Delete items in the vending machine");
        io.print("4. Get all items in the vending machine");
        io.print("5. Select an item to buy");
        io.print("6. Exit");

        return io.readInt("Please select from the above choices.", 1, 6);
    }

    public Item getItemInfo() {
        
        String itemName = io.readString("Please enter the item name");
        BigDecimal price = io.readBig("Please enter the item's price");
        int numItems = io.readInt("Please enter how many units of the item you currently have");

        if (price.toString().isEmpty() != true || String.valueOf(numItems).isEmpty() != true || itemName.isEmpty() != true) {

            boolean isACharacter = itemName.chars().allMatch(x -> Character.isLetter(x));
            if (isACharacter != false) {
               itemName = itemName.toUpperCase();
            }
            
            Item currentItem = new Item(itemName);

            currentItem.setInventoryOfItem(numItems);      
                
            currentItem.setPrice(price);
            
            return currentItem;
        }
        return null;
    }
    

    public void displayCreateItemBanner() {
        io.print("=== Create Item ===");
    }

    public void displayCreateSuccessBanner() {
        io.readString(
                "Item successfully created.  Please hit enter to continue");
    }

    public void displayItemList(List<Item> itemList) {
        for (Item currentItem : itemList) {
            String itemInfo = String.format("Item: %s Price: %s Inventory: %s",
                    currentItem.getItemName(),
                    currentItem.getPrice(),
                    currentItem.getInventoryOfItem());
            io.print(itemInfo);
        }
        io.readString("Please hit enter to continue.");
    }

    public String getItemChoice() {
        return io.readString("Please enter the item name.");
    }

    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }

    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }

    public void displayExitBanner() {
        io.print("Good Bye!!!");
    }

    public String getMoneyAmount() {
        String money = io.readString("Please add money. This machine does not accept more than $10 per transaction!");
        return money;
    }

    public void displayChange(Change myChange) {
        io.print("Here is your change, thank you!");
        String itemInfo = String.format("Quarters: %s, Dimes: %s, Nickels: %s, Pennies %s.",
                myChange.getNumQuarters(),
                myChange.getNumDimes(),
                myChange.getNumNickels(),
                myChange.getNumPennies());
        io.print(itemInfo);
        io.readString("Please hit enter to continue.");
    }

    public void displayInventoryItemList(List<Item> allItemsInInventory) {
        io.print("=== Current Items Available ===");
        for (Item currentItem : allItemsInInventory) {
            String itemInfo = String.format("Item: %s Price: %s Inventory: %s",
                    currentItem.getItemName(),
                    currentItem.getPrice(),
                    currentItem.getInventoryOfItem());
            io.print(itemInfo);
        }
    }

}
