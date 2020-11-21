/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.flooringorder.dao;

import com.mc.flooringorder.dto.Order;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * created 11/21/20
 * @author Melanie Carroll
 */
public class FlooringOrderDaoFileImpl implements FlooringOrderDao {

    private Map<Integer, Order> orders = new HashMap<>();

    @Override
    public List<Order> displayOrders(LocalDateTime date) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Order editOrder(LocalDateTime orderDate, int orderNumber) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Order removeOrder(LocalDateTime orderDate, int orderNumber) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    @Override
    public Order addOrder(int orderNumber, Order order) {
        Order prevOrder = orders.put(orderNumber, order);
        return prevOrder;
    }
    
}
