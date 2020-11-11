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
    }
    
