/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.flooringorder.service;

import com.mc.flooringorder.dao.FlooringOrderPersistenceException;
import com.mc.flooringorder.dto.Order;
import com.mc.flooringorder.dto.Product;
import com.mc.flooringorder.dto.StateTaxRate;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;

/**
 * created 11/22/20
 * @author Melanie Carroll
 */
public interface FlooringOrderServiceLayer {
    
    Order createOrder(int orderNumber, Order order, String date) throws FlooringOrderPersistenceException;
    
    List<Order> displayOrders(String date) throws FlooringOrderPersistenceException, FlooringOrderNotFoundException;
    
    Order editOrder(String orderDate, int orderNumber) throws FlooringOrderPersistenceException;
    
    Order removeOrder(String orderDate, int orderNumber) throws FlooringOrderPersistenceException;
    
    int getOrderNumber(String date) throws FlooringOrderPersistenceException, FlooringOrderNotFoundException ;
    
   ArrayList<String> getAllTaxRatesStateAbbreviations() throws FlooringOrderPersistenceException;
    
    ArrayList<String> getAllProductNames() throws FlooringOrderPersistenceException;
    
    StateTaxRate getStateTaxRate(String stateAbbreviation) throws FlooringOrderPersistenceException;
    
    Product getProduct(String productType) throws FlooringOrderPersistenceException;
    
    void calculateOrder(Order order);
    
    Boolean checkIfFileExists(String date);
    
    
    
    
}
