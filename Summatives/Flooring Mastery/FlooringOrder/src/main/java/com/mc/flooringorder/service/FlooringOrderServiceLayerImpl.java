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
public class FlooringOrderServiceLayerImpl implements FlooringOrderServiceLayer {

    @Override
    public void createOrder(Order order) throws FlooringOrderPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Order> displayOrders(LocalDateTime date) throws FlooringOrderPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Order editOrder(LocalDateTime orderDate, int orderNumber) throws FlooringOrderPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Order removeOrder(LocalDateTime orderDate, int orderNumber) throws FlooringOrderPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
