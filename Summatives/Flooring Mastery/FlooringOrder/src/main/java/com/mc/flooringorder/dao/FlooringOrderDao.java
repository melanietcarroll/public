/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.flooringorder.dao;

import com.mc.flooringorder.dto.Order;
import com.mc.flooringorder.dto.Product;
import com.mc.flooringorder.dto.StateTaxRate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * created 11/21/20
 * @author Melanie Carroll/
 */
public interface FlooringOrderDao {
     /**
     * Adds the given Order to the orders file and associates it with the given 
     * order date.
     * 
     * @param order number with which order is to be associated
     * @param order to be added to the file
     * @return the order object
     * @throws FlooringOrderPersistenceException
     */
    Order addOrder(int orderNumber, Order order)throws FlooringOrderPersistenceException ;
    // throws FlooringOrderPersistenceException;

    /**
     * Returns a List of all Orders in the file for the date selected. 
     * 
     * @return Orders List containing all orders in the file for date selected.
     * @throws FlooringOrderPersistenceException
     */
    List<Order> displayOrders(LocalDateTime date) throws FlooringOrderPersistenceException;
     //throws FlooringOrderPersistenceException;

    /**
     * Returns the edited Order object associated with the given order date and order number.
     * Returns null if no such order exists
     * 
     * @param order date of the Order to retrieve
     * @param order number of the Order to retrieve
     * @return the edited Order object associated with the given order date and number,  
     * null if no such order exists
     * @throws FlooringOrderPersistenceException
     */
    Order editOrder(LocalDateTime orderDate, int orderNumber) throws FlooringOrderPersistenceException;
     //throws FlooringOrderPersistenceException;

    /**
     * Removes from the list the order associated with the given order date and order number. 
     * Returns the order object that is being removed or null if 
     * there is no order associated with the given order date and order number
     * 
     * @param order date of the Order to retrieve
     * @param order number of the Order to retrieve
     * @return Order object that was removed or null if no order 
     * was associated with the given order date and order number
     * @throws FlooringOrderPersistenceException
     */
    Order removeOrder(LocalDateTime orderDate, int orderNumber) throws FlooringOrderPersistenceException;
     //throws FlooringOrderPersistenceException;
    
    int getOrderNumber() throws FlooringOrderPersistenceException;
    
    ArrayList<String> getAllTaxRatesStateAbbreviations() throws FlooringOrderPersistenceException;
    
    ArrayList<String> getAllProductNames() throws FlooringOrderPersistenceException;
    
    StateTaxRate getStateTaxRate(String stateAbbreviation) throws FlooringOrderPersistenceException;
    
    Product getProduct(String productName) throws FlooringOrderPersistenceException;
            
}
