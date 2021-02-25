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
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * created 11/07/20
 *
 * @author Melanie Carroll
 */
public class VendingMachineDaoImpl implements VendingMachineDao {

    private final String LIST_FILE;

    public VendingMachineDaoImpl() {
        LIST_FILE = "list.txt";
    }

    public VendingMachineDaoImpl(String listTextFile) {
        LIST_FILE = listTextFile;
    }

    public static final String DELIMITER = "::";

    private Map<String, Item> items = new HashMap<>();

    @Override
    public Item addItem(String itemName, Item item) throws VendingMachinePersistenceException {
        loadList();
        Item prevItem = items.put(itemName, item);
        writeList();
        return prevItem;
    }

    @Override
    public List<Item> getAllItems() throws VendingMachinePersistenceException {
        loadList();
        return new ArrayList(items.values());
    }

    @Override
    public Item getItem(String itemName) throws VendingMachinePersistenceException {
        loadList();
        return items.get(itemName.toUpperCase());
    }

    @Override
    public Item deleteItem(String itemName) throws VendingMachinePersistenceException {
        loadList();
        Item removedItem = items.remove(itemName.toUpperCase());
        writeList();
        return removedItem;
    }

    @Override
    public Item editItem(String itemName, Item item) throws VendingMachinePersistenceException {
        loadList();
        Item editedItem = items.put(itemName, item);
        writeList();
        return editedItem;
    }

    @Override
    public int giveChange(BigDecimal pennies) {
        int changeDue = pennies.intValue();
        return changeDue;
    }

    private Item unmarshallItem(String itemAsText) {

        // split  line on DELIMITER - ::
        // Leaving an array of Strings, stored in itemTokens.
        // 
        // ____________________________
        // |        |     |           |                  
        // |item    |price|inventory  |
        // | name   |     |           |                  
        // ---------------------------
        //  [0]       [1]    [2]         
        String[] itemTokens = itemAsText.split(DELIMITER);

        // Given the pattern above, the item name is in index 0 of the array.
        String itemName = itemTokens[0];

        // Which we can then use to create a new Item object 
        Item itemFromFile = new Item(itemName);

        // Index 1 - price
        BigDecimal price = new BigDecimal(itemTokens[1]);
        itemFromFile.setPrice(price);

        // Index 2 - inventory of item
        int inventory = Integer.parseInt(itemTokens[2]);
        itemFromFile.setInventoryOfItem(inventory);

        // We have now created an item
        return itemFromFile;
    }

    private void loadList() throws VendingMachinePersistenceException {
        Scanner scanner;

        try {
            // Create Scanner for reading the file
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(LIST_FILE)));
        } catch (FileNotFoundException e) {
            throw new VendingMachinePersistenceException(
                    "-_- Could not load file data into memory.", e);
        }
        // currentLine holds the most recent line read from the file
        String currentLine;
        // currentItem holds the most recent item unmarshalled
        Item currentItem;
        // Go through LIST_FILE line by line, decoding each line into an 
        // Item object by calling the unmarshallItem method.
        // Process while we have more lines in the file
        while (scanner.hasNextLine()) {
            // get the next line in the file
            currentLine = scanner.nextLine();
            // unmarshall the line into an Item
            currentItem = unmarshallItem(currentLine);

            // use the item name as the map key for our item object.
            // Put currentItem into the map using item name as the key
            items.put(currentItem.getItemName(), currentItem);
        }
        // close scanner
        scanner.close();
    }

    private String marshallItem(Item anItem) {
        // We need to turn an Item object into a line of text for our file.
        // For example, we need an in memory object to end up like this:
        // itemname::price::inventoryofitem

        // get out each property and concatenate with DELIMITER 
        // Start with the item name
        String itemAsText = anItem.getItemName() + DELIMITER;

        // add the rest of the properties in the correct order:
        // price
        itemAsText += anItem.getPrice().toString() + DELIMITER;

        // inventory skip the DELIMITER
        itemAsText += Integer.toString(anItem.getInventoryOfItem());

        // We have now turned an item to text
        return itemAsText;
    }

    /**
     * Writes all items in the list out to a LIST_FILE.
     *
     * @throws VendingMachinePersistenceException if an error occurs writing to
     * the file
     */
    private void writeList() throws VendingMachinePersistenceException {
        // not handling the IOException - 
        // translating it to an application specific exception and 
        // then simply throwing it to the code that
        // called us.  It is the responsibility of the calling code to 
        // handle any errors that occur.
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(LIST_FILE));
        } catch (IOException e) {
            throw new VendingMachinePersistenceException(
                    "Could not save item data.", e);
        }
        // Write out the item objects to the list file.
        String itemAsText;
        List<Item> itemList = this.getAllItems();

        for (Item currentItem : itemList) {
            // turn an item into a String
            itemAsText = marshallItem(currentItem);
            // write the Item object to the file
            out.println(itemAsText);
            // force PrintWriter to write line to the file
            out.flush();
        }
        // Clean up
        out.close();
    }

    @Override
    public void updateItemInventory(String itemName) throws VendingMachinePersistenceException {
        Item currentItem = items.get(itemName);
        int inventory = currentItem.getInventoryOfItem() - 1;
        currentItem.setInventoryOfItem(inventory);
        writeList();

    }
}
