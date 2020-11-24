/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.flooringorder.service;

import com.mc.flooringorder.dao.FlooringOrderAuditDao;
import com.mc.flooringorder.dao.FlooringOrderDao;
import com.mc.flooringorder.dao.FlooringOrderPersistenceException;
import com.mc.flooringorder.dto.Order;
import com.mc.flooringorder.dto.Product;
import com.mc.flooringorder.dto.StateTaxRate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * created 11/22/20
 *
 * @author Melanie Carroll
 */
public class FlooringOrderServiceLayerImpl implements FlooringOrderServiceLayer {

    private FlooringOrderAuditDao auditDao;
    FlooringOrderDao dao;

//    private Map<String, StateTaxRate> taxRates = new HashMap<>();
//    private Map<String, Product> products = new HashMap<>();
    @Override
    public Order createOrder(Order order) throws FlooringOrderPersistenceException {
//        int orderNum = dao.getOrderNumber();
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

    @Override
    public int getOrderNumber() throws FlooringOrderPersistenceException {
        int orderNum = dao.getOrderNumber();
        return orderNum;
    }

}
