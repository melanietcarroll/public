/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.flooringorder.service;

import com.mc.flooringorder.dao.FlooringOrderPersistenceException;
import com.mc.flooringorder.dto.Order;
import java.time.LocalDateTime;
import java.util.List;

/**
 * created 11/22/20
 * @author Melanie Carroll
 */
public interface FlooringOrderServiceLayer {
    
    void createOrder(Order order) throws FlooringOrderPersistenceException;
    
    List<Order> displayOrders(LocalDateTime date) throws FlooringOrderPersistenceException;
    
    Order editOrder(LocalDateTime orderDate, int orderNumber) throws FlooringOrderPersistenceException;
    
    Order removeOrder(LocalDateTime orderDate, int orderNumber) throws FlooringOrderPersistenceException;
    
    int getOrderNumber() throws FlooringOrderPersistenceException;
}
