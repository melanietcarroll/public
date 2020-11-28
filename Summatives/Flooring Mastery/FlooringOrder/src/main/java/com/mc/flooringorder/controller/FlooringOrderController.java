/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.flooringorder.controller;

import com.mc.flooringorder.dao.FlooringOrderDao;
import com.mc.flooringorder.dao.FlooringOrderDaoFileImpl;
import com.mc.flooringorder.dao.FlooringOrderPersistenceException;
import com.mc.flooringorder.dto.Order;
import com.mc.flooringorder.dto.Product;
import com.mc.flooringorder.dto.StateTaxRate;
import com.mc.flooringorder.service.FlooringOrderNotFoundException;
import com.mc.flooringorder.service.FlooringOrderServiceLayer;
import com.mc.flooringorder.service.FlooringOrderServiceLayerImpl;
import com.mc.flooringorder.ui.FlooringOrderView;
import com.mc.flooringorder.ui.UserIO;
import com.mc.flooringorder.ui.UserIOConsoleImpl;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * created 11/21/20
 *
 * @author Melanie Carroll
 */
public class FlooringOrderController {

    private FlooringOrderView view = new FlooringOrderView();
    private FlooringOrderServiceLayer service = new FlooringOrderServiceLayerImpl();
    private FlooringOrderDao dao = new FlooringOrderDaoFileImpl();
    private UserIO io = new UserIOConsoleImpl();

    public void run() throws FlooringOrderPersistenceException, FlooringOrderNotFoundException {
        boolean keepGoing = true;
        int menuSelection = 0;
     
        try {
        while (keepGoing) {

            menuSelection = getMenuSelection();

            switch (menuSelection) {
                case 1:
                    displayOrders();
                    break;
                case 2:
                    addOrder();
                    break;
                case 3:
                    io.print("Edit an Order");
                    break;
                case 4:
                    io.print("Remove an Order");
                    break;
                case 5:
                    keepGoing = false;
                    break;
                default:
                        unknownCommand();
                }
            }
            exitMessage();
        } catch (FlooringOrderPersistenceException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }

    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }

    private void addOrder() throws FlooringOrderPersistenceException, FlooringOrderNotFoundException {
        view.displayCreateOrderBanner();

        BigDecimal area = view.getOrderArea();
        String date = view.getOrderDate();

        String name = view.getOrderName();
//        String product = view.getOrderProduct();//read in product objects into a list and check for match then use costpersquarefoot and laborcostpersquarefoot fields
//        String state = view.getOrderState();//read in state objects into a list and check for match then use statename and taxrate fields

        List<String> stateAbbreviations = service.getAllTaxRatesStateAbbreviations();
        String state = view.getOrderState(stateAbbreviations).toUpperCase();//read in state objects into a list and check for match then use statename and taxrate fields

        List<String> productNames = service.getAllProductNames();
        String product = view.getOrderProduct(productNames).toUpperCase();//read in product objects into a list and check for match then use costpersquarefoot and laborcostpersquarefoot fields

        //int orderNumber = service.getOrderNumber(date);
//        if (orderNumber == null){
//            return orderNumber = 1;
        int orderNumber = service.getOrderNumber(date);
        Order newOrder = new Order(orderNumber);

//        service.getOrderNumber(date);
        newOrder.setCustomerName(name);
        newOrder.setArea(area);
        newOrder.setOrderDate(date);
        newOrder.setState(state);
        newOrder.setProductType(product);

        Product chosenProduct = service.getProduct(product);
        newOrder.setCostPerSquareFoot(chosenProduct.getProductCostPerSquareFoot());
        newOrder.setLaborCostPerSquareFoot(chosenProduct.getProductLaborCostPerSquareFoot());

        StateTaxRate taxRate = service.getStateTaxRate(state);
        newOrder.setTaxRate(taxRate.getStateTaxRate());

        service.calculateOrder(newOrder);
        String placeOrder = view.orderSummary(newOrder);
        if (placeOrder.equalsIgnoreCase("Y")) {
            service.createOrder(newOrder.getOrderNumber(), newOrder, date);
        }
        if (placeOrder.equalsIgnoreCase("N")) {
            getMenuSelection();
        }
        view.displayCreateSuccessBanner();

    }

    private void displayOrders() throws FlooringOrderPersistenceException, FlooringOrderNotFoundException {
        String date = view.getOrderDate();
        try {
           List<Order> orderList = service.displayOrders(date);
             view.displayOrderList(orderList);
        } catch (FlooringOrderPersistenceException | FlooringOrderNotFoundException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }

    private void unknownCommand() {
          view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
       view.displayExitBanner();
    }
 
}
