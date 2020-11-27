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
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.OptionalInt;

/**
 * created 11/22/20
 *
 * @author Melanie Carroll
 */
public class FlooringOrderServiceLayerImpl implements FlooringOrderServiceLayer {

    private FlooringOrderAuditDao auditDao;
    FlooringOrderDao dao;
    
    @Override
     public Order createOrder(int orderNumber, Order order, String date) throws FlooringOrderPersistenceException {
    return dao.addOrder(orderNumber, order, date);
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
    public int getOrderNumber(String date) throws FlooringOrderPersistenceException {
        List<Order> orderDate = dao.displayOrders(date);
        OptionalInt orderNumber = orderDate.stream().mapToInt((p) -> p.getOrderNumber()).max();
        int orderNum = orderNumber.getAsInt() + 1;
        return orderNum;
    }

    @Override
    public ArrayList<String> getAllTaxRatesStateAbbreviations() throws FlooringOrderPersistenceException {
        return dao.getAllTaxRatesStateAbbreviations();
    }

    @Override
    public ArrayList<String> getAllProductNames() throws FlooringOrderPersistenceException {
        return dao.getAllProductNames();
    }

    @Override
    public StateTaxRate getStateTaxRate(String stateAbbreviation) throws FlooringOrderPersistenceException {
        return dao.getStateTaxRate(stateAbbreviation);
    }

    @Override
    public Product getProduct(String productType) throws FlooringOrderPersistenceException {
        return dao.getProduct(productType);
    }
    @Override
    public void calculateOrder(Order order){

        BigDecimal costPerSquareFoot = new BigDecimal(order.getCostPerSquareFoot().toString());
        BigDecimal projectArea = new BigDecimal(order.getArea().toString());
//         newOrder.setOrderNumber(orderNumber);
        //then do calculations
        BigDecimal materialCost = projectArea.multiply(costPerSquareFoot).setScale(2, RoundingMode.HALF_UP);
        order.setMaterialCost(materialCost);

        BigDecimal laborCostPerSquareFoot = new BigDecimal(order.getLaborCostPerSquareFoot().toString());
        BigDecimal laborCost = projectArea.multiply(laborCostPerSquareFoot).setScale(2, RoundingMode.HALF_UP);
        order.setLaborCost(laborCost);

        BigDecimal taxRateValue = new BigDecimal(order.getTaxRate().toString());
        BigDecimal percentage = new BigDecimal("100");
        BigDecimal taxRateCalc = taxRateValue.divide(percentage, 0, RoundingMode.HALF_UP);//tax rates are stored as whole numbers
        BigDecimal tax = materialCost.add(laborCost).multiply(taxRateCalc).setScale(2, RoundingMode.HALF_UP); 
        order.setTax(tax);

        BigDecimal total = materialCost.add(laborCost).add(tax).setScale(2, RoundingMode.HALF_UP);
        order.setTotal(total);

    }

}