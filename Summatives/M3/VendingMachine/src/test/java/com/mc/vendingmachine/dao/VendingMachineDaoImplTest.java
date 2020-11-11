/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.vendingmachine.dao;

import com.mc.vendingmachine.dto.Item;
import java.io.FileWriter;
import java.io.IOException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Shawn
 */
public class VendingMachineDaoImplTest {

    VendingMachineDao testDao;

    public VendingMachineDaoImplTest() {
    }

    @BeforeEach
    public void setUp() throws Exception {
        String testFile = "testlist.txt";
        // Use the FileWriter to quickly blank the file
        new FileWriter(testFile);
        testDao = new VendingMachineDaoImpl(testFile);
    }

    @Test
    public void testAddGetItem() throws Exception {
        // Create our method test inputs
        String itemName = "COKE";
        Item item = new Item(itemName);
        BigDecimal price = new BigDecimal("1.25");
        item.setPrice(price);
        item.setInventoryOfItem(5);

        //  Add the item to the DAO
        testDao.addItem(itemName, item);
        // Get the item from the DAO
        Item retrievedItem = testDao.getItem(itemName);

        // Check the data is equal
        assertEquals(item.getItemName(),
                retrievedItem.getItemName(),
                "Checking item name.");
        assertEquals(item.getPrice(),
                retrievedItem.getPrice(),
                "Checking item price.");
        assertEquals(item.getInventoryOfItem(),
                retrievedItem.getInventoryOfItem(),
                "Checking inventory of item.");

    }

    @Test
    public void testAddGetAllItems() throws Exception {
        // Create first item
        String itemName = "SNICKERS";
        Item firstItem = new Item(itemName);
        BigDecimal price = new BigDecimal("1.25");
        firstItem.setPrice(price);
        firstItem.setInventoryOfItem(6);

        // Create second item
        String secondItemName = "PEPSI";
        Item secondItem = new Item(secondItemName);
        BigDecimal secondPrice = new BigDecimal("2.00");
        secondItem.setPrice(secondPrice);
        secondItem.setInventoryOfItem(3);

        // Add both our items to the DAO
        testDao.addItem(itemName, firstItem);
        testDao.addItem(secondItemName, secondItem);

        // Retrieve the list of all items within the DAO
        List<Item> allItems = testDao.getAllItems();

        // First check the general contents of the list
        assertNotNull(allItems, "The list of items must not null");
        assertEquals(2, allItems.size(), "List of items should have 2 items.");

        // Then the specifics
        assertTrue(testDao.getAllItems().contains(firstItem),
                "The list of items should include SNICKERS.");
        assertTrue(testDao.getAllItems().contains(secondItem),
                "The list of items should include PEPSI.");

    }

    @Test
    public void testDeleteItem() throws Exception {
        // Create two new items
        String itemName = "SNICKERS";
        Item firstItem = new Item(itemName);
        BigDecimal price = new BigDecimal("1.25");
        firstItem.setPrice(price);
        firstItem.setInventoryOfItem(6);

        // Create second item
        String secondItemName = "PEPSI";
        Item secondItem = new Item(secondItemName);
        BigDecimal secondPrice = new BigDecimal("2.00");
        secondItem.setPrice(secondPrice);
        secondItem.setInventoryOfItem(3);

        // Add both to the DAO
        testDao.addItem(itemName, firstItem);
        testDao.addItem(secondItemName, secondItem);

        // remove the first item - SNICKERS
        Item removedItem = testDao.deleteItem(firstItem.getItemName());

        // Check that the correct object was removed.
        assertEquals(removedItem, firstItem, "The removed item should be SNICKERS.");

        // Get all the items
        List<Item> allItems = testDao.getAllItems();

        // First check the general contents of the list
        assertNotNull(allItems, "All items list should be not null.");
        assertEquals(1, allItems.size(), "All items should only have 1 item.");

        // Then the specifics
        assertFalse(allItems.contains(firstItem), "All items should NOT include SNICKERS.");
        assertTrue(allItems.contains(secondItem), "All items should include PEPSI.");

        // Remove the second item
        removedItem = testDao.deleteItem(secondItem.getItemName());
        // Check that the correct object was removed.
        assertEquals(removedItem, secondItem, "The removed item should be PEPSI.");

        // retrieve all of the items again, and check the list.
        allItems = testDao.getAllItems();

        // Check the contents of the list - it should be empty
        assertTrue(allItems.isEmpty(), "The retrieved list of items should be empty.");

        // Try to 'get' both items by their old item name - they should be null!
        Item retrievedItem = testDao.getItem(firstItem.getItemName());
        assertNull(retrievedItem, "SNICKERS was removed, should be null.");

        retrievedItem = testDao.getItem(secondItem.getItemName());
        assertNull(retrievedItem, "PEPSI was removed, should be null.");

    }

    @Test
    public void testEditItem() throws Exception {
        // Create our method test inputs
        String itemName = "COKE";
        Item firstItem = new Item(itemName);
        BigDecimal price = new BigDecimal("1.25");
        firstItem.setPrice(price);
        firstItem.setInventoryOfItem(6);

        //  Add the item to the DAO
        testDao.addItem(itemName, firstItem);
        // Get the item from the DAO
        Item retrievedItem = testDao.getItem(itemName);

        //Edit the item
        String editItemName = "COKE";
        retrievedItem.setItemName(editItemName);
        BigDecimal editPrice = new BigDecimal("1.50");
        retrievedItem.setPrice(editPrice);
        retrievedItem.setInventoryOfItem(10);

        //  Add the item to the DAO
        testDao.addItem(editItemName, retrievedItem);
        // Get the item from the DAO
        Item retrievedEditedItem = testDao.getItem(editItemName);

        // Check the data is equal
        assertEquals(retrievedItem.getItemName(),
                retrievedEditedItem.getItemName(),
                "Checking item name.");
        assertEquals(retrievedItem.getPrice(),
                retrievedEditedItem.getPrice(),
                "Checking item price.");
        assertEquals(retrievedItem.getInventoryOfItem(),
                retrievedEditedItem.getInventoryOfItem(),
                "Checking inventory of item.");

    }
}
