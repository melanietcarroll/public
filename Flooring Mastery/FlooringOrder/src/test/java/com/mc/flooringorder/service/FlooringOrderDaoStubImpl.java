/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.flooringorder.service;

import com.mc.flooringorder.dao.FlooringOrderDao;
import com.mc.flooringorder.dao.FlooringOrderPersistenceException;
import com.mc.flooringorder.dto.Order;
import com.mc.flooringorder.dto.Product;
import com.mc.flooringorder.dto.StateTaxRate;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Melanie Carroll
 */
public class FlooringOrderDaoStubImpl implements FlooringOrderDao {

    public Order onlyOrder;

    public FlooringOrderDaoStubImpl() {

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
        onlyOrder = new Order(orderNumber);
        onlyOrder.setCustomerName("Acme");
        onlyOrder.setState("TX");
        onlyOrder.setTaxRate(taxRate);
        onlyOrder.setProductType("Wood");
        onlyOrder.setArea(area);
        onlyOrder.setCostPerSquareFoot(costPerSquareFoot);
        onlyOrder.setLaborCostPerSquareFoot(laborCostPerSquareFoot);
        onlyOrder.setMaterialCost(materialCost);
        onlyOrder.setLaborCost(laborCost);
        onlyOrder.setTax(tax);
        onlyOrder.setTotal(total);
        onlyOrder.setOrderDate(orderDate);
    }

    public FlooringOrderDaoStubImpl(Order testOrder) {
        this.onlyOrder = testOrder;
    }

    private Map<String, StateTaxRate> taxRates = new HashMap<>();
    private Map<String, Product> products = new HashMap<>();
   

    @Override

    public Order addOrder(int orderNumber, Order order, String date) throws FlooringOrderPersistenceException {
        if (orderNumber == onlyOrder.getOrderNumber()) {
            return onlyOrder;
        } else {
            return null;
        }
    }

    @Override
    public List<Order> displayOrders(String date) throws FlooringOrderPersistenceException, FlooringOrderNotFoundException {
        List<Order> orderList = new ArrayList<>();
        orderList.add(onlyOrder);
        return orderList;
    }

    @Override
    public Order editOrder(String orderDate, int orderNumber, Order order) throws FlooringOrderPersistenceException {
        if (orderNumber == onlyOrder.getOrderNumber() && orderDate.equals(onlyOrder.getOrderDate())) {
            return onlyOrder;
        } else {
            return null;
        }
    }

    @Override
    public Order removeOrder(String orderDate, int orderNumber) throws FlooringOrderPersistenceException {
        if (orderNumber == onlyOrder.getOrderNumber()) {
            return onlyOrder;
        } else {
            return null;
        }
    }

    @Override
    public ArrayList<String> getAllTaxRatesStateAbbreviations() throws FlooringOrderPersistenceException {
        StateTaxRate tax = new StateTaxRate("TX");
        tax.setStateName("Texas");
        BigDecimal taxRate = new BigDecimal("4.45");
        tax.setStateTaxRate(taxRate);
        taxRates.put(tax.getStateAbbreviation(), tax);
        return new ArrayList<>(taxRates.keySet());
    }

    @Override
    public ArrayList<String> getAllProductNames() throws FlooringOrderPersistenceException {
        Product product = new Product("Wood");
        BigDecimal productCostPerSquareFoot = new BigDecimal("5.15");
        BigDecimal laborCostPerSquareFoot = new BigDecimal("4.75");
        product.setProductCostPerSquareFoot(productCostPerSquareFoot);
        product.setProductLaborCostPerSquareFoot(laborCostPerSquareFoot);
        products.put(product.getProductType(), product);
        return new ArrayList<>(products.keySet());
    }

    @Override
    public StateTaxRate getStateTaxRate(String stateAbbreviation) throws FlooringOrderPersistenceException {
        StateTaxRate tax = new StateTaxRate("TX");
        tax.setStateName("Texas");
        BigDecimal taxRate = new BigDecimal("4.45");
        tax.setStateTaxRate(taxRate);
        taxRates.put(tax.getStateAbbreviation(), tax);
        return taxRates.get(stateAbbreviation);
    }

    @Override
    public Product getProduct(String productType) throws FlooringOrderPersistenceException {
        Product product = new Product("Wood");
        BigDecimal productCostPerSquareFoot = new BigDecimal("5.15");
        BigDecimal laborCostPerSquareFoot = new BigDecimal("4.75");
        product.setProductCostPerSquareFoot(productCostPerSquareFoot);
        product.setProductLaborCostPerSquareFoot(laborCostPerSquareFoot);
        products.put(product.getProductType(), product);
        return products.get(productType);
    }

    @Override
    public Boolean checkIfFileExists(String date) {
        boolean exists = false;
        if (date.equals(onlyOrder.getOrderDate())) {
            exists = true;
            return exists;
        }
        return exists;
    }

    @Override
    public int getOrderNumber(String date) throws FlooringOrderPersistenceException, FlooringOrderNotFoundException {
        return onlyOrder.getOrderNumber();
    }

    @Override
    public Order getOrder(String orderDate, int orderNumber) throws FlooringOrderPersistenceException, FlooringOrderNotFoundException {
        if (orderNumber == onlyOrder.getOrderNumber() && orderDate.equals(onlyOrder.getOrderDate())) {
            return onlyOrder;
        } else {
            return null;
        }
    }

}
