/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.flooringorder.dao;

import com.mc.flooringorder.dto.Order;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Melanie Carroll
 */
public class FlooringOrderDaoFileImplTest {

    FlooringOrderDao testDao;

    public FlooringOrderDaoFileImplTest() throws IOException {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() throws Exception {
       
        testDao = new FlooringOrderDaoFileImpl();
//        new FileWriter("test12202020.txt");
//        PrintWriter out = new PrintWriter(new FileWriter("test12202020.txt"));
//        out.println("OrderNumber,CustomerName,State,TaxRate,ProductType,Area,CostPerSquareFoot,LaborCostPerSquareFoot,MaterialCost,LaborCost,Tax,Total");
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testAddGetOrder() throws Exception {

        BigDecimal taxRate = new BigDecimal("4.45");
        BigDecimal area = new BigDecimal("800");
        BigDecimal costPerSquareFoot = new BigDecimal("5.15");
        BigDecimal laborCostPerSquareFoot = new BigDecimal("4.75");
        BigDecimal materialCost = new BigDecimal("4120");
        BigDecimal laborCost = new BigDecimal("3800");
        BigDecimal tax = new BigDecimal("352.44");
        BigDecimal total = new BigDecimal("8272.44");
        String orderDate = "12012020";
        
        // Create our method test inputs
        int orderNumber = 1;
        Order newOrder = new Order(orderNumber);
        newOrder.setCustomerName("Acme");
        newOrder.setState("TX");
        newOrder.setTaxRate(taxRate);
        newOrder.setProductType("Wood");
        newOrder.setArea(area);
        newOrder.setCostPerSquareFoot(costPerSquareFoot);
        newOrder.setLaborCostPerSquareFoot(laborCostPerSquareFoot);
        newOrder.setMaterialCost(materialCost);
        newOrder.setLaborCost(laborCost);
        newOrder.setTax(tax);
        newOrder.setTotal(total);
        newOrder.setOrderDate(orderDate);

        //  Add the order to the DAO
        testDao.addOrder(orderNumber, newOrder, orderDate);
        // Get the order from the DAO
        Order retrievedOrder = testDao.getOrder(orderDate, orderNumber);

        // Check the data is equal
        assertEquals(newOrder.getOrderNumber(),
                retrievedOrder.getOrderNumber(),
                "Checking order number.");
        assertEquals(newOrder.getCustomerName(),
                retrievedOrder.getCustomerName(),
                "Checking customer name.");
        assertEquals(newOrder.getState(),
                retrievedOrder.getState(),
                "Checking state.");
        assertEquals(newOrder.getTaxRate(),
                retrievedOrder.getTaxRate(),
                "Checking TaxRate.");
        assertEquals(newOrder.getProductType(),
                retrievedOrder.getProductType(),
                "Checking ProductType.");
        assertEquals(newOrder.getArea(),
                retrievedOrder.getArea(),
                "Checking Area.");
        assertEquals(newOrder.getCostPerSquareFoot(),
                retrievedOrder.getCostPerSquareFoot(),
                "Checking CostPerSquareFoot.");
        assertEquals(newOrder.getLaborCostPerSquareFoot(),
                retrievedOrder.getLaborCostPerSquareFoot(),
                "Checking LaborCostPerSquareFoot.");
        assertEquals(newOrder.getMaterialCost(),
                retrievedOrder.getMaterialCost(),
                "Checking MaterialCost.");
        assertEquals(newOrder.getLaborCost(),
                retrievedOrder.getLaborCost(),
                "Checking labor cost.");
        assertEquals(newOrder.getTax(),
                retrievedOrder.getTax(),
                "Checking tax.");
        assertEquals(newOrder.getTotal(),
                retrievedOrder.getTotal(),
                "Checking Total.");
         
    }
@Test
public void testAddGetAllOrders() throws Exception {
    // Create our first order
   BigDecimal taxRate = new BigDecimal("4.45");
        BigDecimal area = new BigDecimal("800");
        BigDecimal costPerSquareFoot = new BigDecimal("5.15");
        BigDecimal laborCostPerSquareFoot = new BigDecimal("4.75");
        BigDecimal materialCost = new BigDecimal("4120");
        BigDecimal laborCost = new BigDecimal("3800");
        BigDecimal tax = new BigDecimal("352.44");
        BigDecimal total = new BigDecimal("8272.44");
        String orderDate = "12022020";
        
        // Create our method test inputs
        int orderNumber = 1;
        Order newOrder = new Order(orderNumber);
        newOrder.setCustomerName("Acme");
        newOrder.setState("TX");
        newOrder.setTaxRate(taxRate);
        newOrder.setProductType("Wood");
        newOrder.setArea(area);
        newOrder.setCostPerSquareFoot(costPerSquareFoot);
        newOrder.setLaborCostPerSquareFoot(laborCostPerSquareFoot);
        newOrder.setMaterialCost(materialCost);
        newOrder.setLaborCost(laborCost);
        newOrder.setTax(tax);
        newOrder.setTotal(total);
//        newOrder.setOrderDate(orderDate);


    // Create our second order
    BigDecimal taxRate2 = new BigDecimal("25");
        BigDecimal area2 = new BigDecimal("650");
        BigDecimal costPerSquareFoot2 = new BigDecimal("5.15");
        BigDecimal laborCostPerSquareFoot2 = new BigDecimal("4.75");
        BigDecimal materialCost2 = new BigDecimal("3347.5");
        BigDecimal laborCost2 = new BigDecimal("3087.5");
        BigDecimal tax2 = new BigDecimal("1608.75");
        BigDecimal total2 = new BigDecimal("8043.75");
        String orderDate2 = "12022020";
        
        // Create our method test inputs
        int orderNumber2 = 2;
        Order newOrder2 = new Order(orderNumber2);
        newOrder2.setCustomerName("TurboTax");
        newOrder2.setState("CA");
        newOrder2.setTaxRate(taxRate2);
        newOrder2.setProductType("Wood");
        newOrder2.setArea(area2);
        newOrder2.setCostPerSquareFoot(costPerSquareFoot2);
        newOrder2.setLaborCostPerSquareFoot(laborCostPerSquareFoot2);
        newOrder2.setMaterialCost(materialCost2);
        newOrder2.setLaborCost(laborCost2);
        newOrder2.setTax(tax2);
        newOrder2.setTotal(total2);
//        newOrder2.setOrderDate(orderDate2);

    // Add both our students to the DAO
    testDao.addOrder(orderNumber, newOrder, orderDate);
    testDao.addOrder(orderNumber2, newOrder2, orderDate2);

    // Retrieve the list of all orders within the DAO
    List<Order> allOrders = testDao.displayOrders(orderDate);

    // First check the general contents of the list
    assertNotNull(allOrders, "The list of orders must not be null");
    assertEquals(2, allOrders.size(),"List of orders should have 2 orders.");

    // Then the specifics
    assertTrue(testDao.displayOrders(orderDate).contains(newOrder),
                "The list of orders should include Acme.");
    assertTrue(testDao.displayOrders(orderDate).contains(newOrder2),
            "The list of orders should include TurboTax.");

}
@Test
public void testRemoveOrder() throws Exception {
    // Create two new orders
    // Create our first order
   BigDecimal taxRate = new BigDecimal("4.45");
        BigDecimal area = new BigDecimal("800");
        BigDecimal costPerSquareFoot = new BigDecimal("5.15");
        BigDecimal laborCostPerSquareFoot = new BigDecimal("4.75");
        BigDecimal materialCost = new BigDecimal("4120");
        BigDecimal laborCost = new BigDecimal("3800");
        BigDecimal tax = new BigDecimal("352.44");
        BigDecimal total = new BigDecimal("8272.44");
        String orderDate = "12032020";
        
        // Create our method test inputs
        int orderNumber = 1;
        Order newOrder = new Order(orderNumber);
        newOrder.setCustomerName("Acme");
        newOrder.setState("TX");
        newOrder.setTaxRate(taxRate);
        newOrder.setProductType("Wood");
        newOrder.setArea(area);
        newOrder.setCostPerSquareFoot(costPerSquareFoot);
        newOrder.setLaborCostPerSquareFoot(laborCostPerSquareFoot);
        newOrder.setMaterialCost(materialCost);
        newOrder.setLaborCost(laborCost);
        newOrder.setTax(tax);
        newOrder.setTotal(total);
//        newOrder.setOrderDate(orderDate);


    // Create our second order
    BigDecimal taxRate2 = new BigDecimal("25");
        BigDecimal area2 = new BigDecimal("650");
        BigDecimal costPerSquareFoot2 = new BigDecimal("5.15");
        BigDecimal laborCostPerSquareFoot2 = new BigDecimal("4.75");
        BigDecimal materialCost2 = new BigDecimal("3347.5");
        BigDecimal laborCost2 = new BigDecimal("3087.5");
        BigDecimal tax2 = new BigDecimal("1608.75");
        BigDecimal total2 = new BigDecimal("8043.75");
        String orderDate2 = "12032020";
        
        // Create our method test inputs
        int orderNumber2 = 2;
        Order newOrder2 = new Order(orderNumber2);
        newOrder2.setCustomerName("TurboTax");
        newOrder2.setState("CA");
        newOrder2.setTaxRate(taxRate2);
        newOrder2.setProductType("Wood");
        newOrder2.setArea(area2);
        newOrder2.setCostPerSquareFoot(costPerSquareFoot2);
        newOrder2.setLaborCostPerSquareFoot(laborCostPerSquareFoot2);
        newOrder2.setMaterialCost(materialCost2);
        newOrder2.setLaborCost(laborCost2);
        newOrder2.setTax(tax2);
        newOrder2.setTotal(total2);
//        newOrder2.setOrderDate(orderDate2);

    // Add both to the DAO
    testDao.addOrder(orderNumber, newOrder, orderDate);
    testDao.addOrder(orderNumber2, newOrder2, orderDate2);

    // remove the first order - acme
   Order removedOrder = testDao.removeOrder(orderDate, orderNumber);

    // Check that the correct object was removed.
    assertEquals(removedOrder, newOrder, "The removed order should be Acme.");

    // Get all the orders
    List<Order> allOrders = testDao.displayOrders(orderDate);

    // First check the general contents of the list
    assertNotNull(allOrders, "All orderss list should be not null.");
    assertEquals(1, allOrders.size(), "All orders should only have 1 order.");

    // Then the specifics
    assertFalse(allOrders.contains(newOrder), "All orders should NOT include Acme.");
    assertTrue(allOrders.contains(newOrder2), "All orders should include TurboTax.");    

    // Remove the second order
    removedOrder = testDao.removeOrder(orderDate, orderNumber2);
    // Check that the correct object was removed.
    assertEquals( removedOrder, newOrder2, "The removed order should be TurboTax.");

    // retrieve all of the orders again, and check the list.
    allOrders = testDao.displayOrders(orderDate);

    // Check the contents of the list - it should be empty
    assertTrue(allOrders.isEmpty(), "The retrieved list of orders should be empty.");

    // Try to 'get' both orders by their old order# - they should be null!
    Order retrievedOrder = testDao.getOrder(orderDate, orderNumber);
    assertNull(retrievedOrder, "Acme was removed, should be null.");

    retrievedOrder = testDao.getOrder(orderDate, orderNumber2);
    assertNull(retrievedOrder, "TurboTax was removed, should be null.");

}
 @Test
    public void testEditOrder() throws Exception {
        // Create our method test inputs
        BigDecimal taxRate = new BigDecimal("4.45");
        BigDecimal area = new BigDecimal("800");
        BigDecimal costPerSquareFoot = new BigDecimal("5.15");
        BigDecimal laborCostPerSquareFoot = new BigDecimal("4.75");
        BigDecimal materialCost = new BigDecimal("4120");
        BigDecimal laborCost = new BigDecimal("3800");
        BigDecimal tax = new BigDecimal("352.44");
        BigDecimal total = new BigDecimal("8272.44");
        String orderDate = "12042020";
        
        // Create our method test inputs
        int orderNumber = 1;
        Order newOrder = new Order(orderNumber);
        newOrder.setCustomerName("Acme");
        newOrder.setState("TX");
        newOrder.setTaxRate(taxRate);
        newOrder.setProductType("Wood");
        newOrder.setArea(area);
        newOrder.setCostPerSquareFoot(costPerSquareFoot);
        newOrder.setLaborCostPerSquareFoot(laborCostPerSquareFoot);
        newOrder.setMaterialCost(materialCost);
        newOrder.setLaborCost(laborCost);
        newOrder.setTax(tax);
        newOrder.setTotal(total);

        //  Add the order to the DAO
        testDao.addOrder(orderNumber, newOrder, orderDate);
        // Get the item from the DAO
        Order retrievedOrder = testDao.getOrder(orderDate, orderNumber);

        //Edit the item
        BigDecimal taxRate2 = new BigDecimal("25");
        BigDecimal area2 = new BigDecimal("650");
        BigDecimal costPerSquareFoot2 = new BigDecimal("5.15");
        BigDecimal laborCostPerSquareFoot2 = new BigDecimal("4.75");
        BigDecimal materialCost2 = new BigDecimal("3347.5");
        BigDecimal laborCost2 = new BigDecimal("3087.5");
        BigDecimal tax2 = new BigDecimal("1608.75");
        BigDecimal total2 = new BigDecimal("8043.75");
            
        
        retrievedOrder.setCustomerName("TurboTax");
        retrievedOrder.setState("CA");
        retrievedOrder.setTaxRate(taxRate2);
        retrievedOrder.setProductType("Wood");
        retrievedOrder.setArea(area2);
        retrievedOrder.setCostPerSquareFoot(costPerSquareFoot2);
        retrievedOrder.setLaborCostPerSquareFoot(laborCostPerSquareFoot2);
        retrievedOrder.setMaterialCost(materialCost2);
        retrievedOrder.setLaborCost(laborCost2);
        retrievedOrder.setTax(tax2);
        retrievedOrder.setTotal(total2);

        //  Add the order to the DAO
        testDao.addOrder(orderNumber, retrievedOrder, orderDate);
        // Get the order from the DAO
        Order retrievedEditedOrder = testDao.getOrder(orderDate, orderNumber);

        // Check the data is equal
        // Check the data is equal
        assertEquals(retrievedOrder.getOrderNumber(),
                retrievedEditedOrder.getOrderNumber(),
                "Checking order number.");
        assertEquals(retrievedOrder.getCustomerName(),
                retrievedEditedOrder.getCustomerName(),
                "Checking customer name.");
        assertEquals(retrievedOrder.getState(),
                retrievedEditedOrder.getState(),
                "Checking state.");
        assertEquals(retrievedOrder.getTaxRate(),
                retrievedEditedOrder.getTaxRate(),
                "Checking TaxRate.");
        assertEquals(retrievedOrder.getProductType(),
                retrievedEditedOrder.getProductType(),
                "Checking ProductType.");
        assertEquals(retrievedOrder.getArea(),
                retrievedEditedOrder.getArea(),
                "Checking Area.");
        assertEquals(retrievedOrder.getCostPerSquareFoot(),
                retrievedEditedOrder.getCostPerSquareFoot(),
                "Checking CostPerSquareFoot.");
        assertEquals(retrievedOrder.getLaborCostPerSquareFoot(),
                retrievedEditedOrder.getLaborCostPerSquareFoot(),
                "Checking LaborCostPerSquareFoot.");
        assertEquals(retrievedOrder.getMaterialCost(),
                retrievedEditedOrder.getMaterialCost(),
                "Checking MaterialCost.");
        assertEquals(retrievedOrder.getLaborCost(),
                retrievedEditedOrder.getLaborCost(),
                "Checking labor cost.");
        assertEquals(retrievedOrder.getTax(),
                retrievedEditedOrder.getTax(),
                "Checking tax.");
        assertEquals(retrievedOrder.getTotal(),
                retrievedEditedOrder.getTotal(),
                "Checking Total.");

    }
}
