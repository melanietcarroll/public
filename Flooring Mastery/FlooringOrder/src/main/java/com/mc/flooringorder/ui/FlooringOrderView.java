/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.flooringorder.ui;

import com.mc.flooringorder.dto.Order;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.regex.Pattern;

/**
 * created 11/21/20
 *
 * @author Melanie Carroll
 */
public class FlooringOrderView {

    private UserIO io;
    
    public FlooringOrderView(UserIO io){
        this.io = io;
    }

    public int printMenuAndGetSelection() {
        io.print("Flooring Program Main Menu");
        io.print("1. Display Orders");
        io.print("2. Add an Order");
        io.print("3. Edit an Order");
        io.print("4. Remove an Order");
        io.print("5. Quit");

        return io.readInt("Please select from the above choices.", 1, 5);
    }

    public String getOrderDate() {
        LocalDate min = LocalDate.now().plusDays(1);
        LocalDate max = LocalDate.now().plusMonths(6);
        LocalDate date = io.readDate("Please enter order date in the following format: MM/dd/yyyy", min, max);
        String formatted = date.format(DateTimeFormatter.ofPattern("MMddyyyy"));
        return formatted;
    }

    public String getOrderName() {
        String name = io.readStringInput("Enter customer name (only alpha numeric characters, commas and periods are allowed.");
        return name;
    }

    public String getOrderState(List<String> stateAbbreviations) {
        String state = io.readString("Enter one of the following sate abbrevations: " + stateAbbreviations, stateAbbreviations);
        return state;
    }

    public String getOrderProduct(List<String> productNames) {
        String product = io.readString("Enter one of the following product types: " + productNames, productNames);
        return product;
    }

    public BigDecimal getOrderArea() {
        BigDecimal area = io.readArea("Enter project area (minimum order size is 100 sq ft).");
        return area;
    }

    public void displayCreateOrderBanner() {
        io.print("=== Create Order ===");
    }

    public void displayCreateSuccessBanner() {
        io.readString(
                "Order successfully created.  Please hit enter to continue");
    }

    public String orderSummary(Order order) {
        String orderInfo = String.format("Order #: %s Name: %s State: %s Tax Rate: %s Product: %s Area: %s Cost Per Square Foot: %s"
                + " Labor Cost Per Square Foot: %s Material Cost: %s Labor Cost: %s Tax: %s Total: %s",
                order.getOrderNumber(),
                order.getCustomerName(),
                order.getState(),
                order.getTaxRate(),
                order.getProductType(),
                order.getArea(),
                order.getCostPerSquareFoot(),
                order.getLaborCostPerSquareFoot(),
                order.getMaterialCost(),
                order.getLaborCost(),
                order.getTax(),
                order.getTotal());
        io.print(orderInfo);

        String answer = io.readString("Save order Y/N");
        return answer;
    }

    public void displayOrderList(List<Order> orderList) {
        for (Order currentOrder : orderList) {
            String orderInfo = String.format("Order #: %s Name: %s State: %s Tax Rate: %s Product: %s Area: %s Cost Per Square Foot: %s"
                    + " Labor Cost Per Square Foot: %s Material Cost: %s Labor Cost: %s Tax: %s Total: %s",
                    currentOrder.getOrderNumber(),
                    currentOrder.getCustomerName(),
                    currentOrder.getState(),
                    currentOrder.getTaxRate(),
                    currentOrder.getProductType(),
                    currentOrder.getArea(),
                    currentOrder.getCostPerSquareFoot(),
                    currentOrder.getLaborCostPerSquareFoot(),
                    currentOrder.getMaterialCost(),
                    currentOrder.getLaborCost(),
                    currentOrder.getTax(),
                    currentOrder.getTotal());

            io.print(orderInfo);
        }
        io.readString("Please hit enter to continue.");
    }

    public void displayErrorMessage(String message) {
        io.print("=== ERROR ===");
        io.print(message);
    }

    public void displayUnknownCommandBanner() {
         io.print("Unknown Command!!!");
    }

    public void displayExitBanner() {
        io.print("Good Bye!!!");
    }

    public int getOrderNumber() {
        return io.readInt("Please enter order number.");
    }

    public String displayOrder(Order order) {
        String orderInfo = String.format("Order #: %s Name: %s State: %s Tax Rate: %s Product: %s Area: %s Cost Per Square Foot: %s"
                + " Labor Cost Per Square Foot: %s Material Cost: %s Labor Cost: %s Tax: %s Total: %s",
                order.getOrderNumber(),
                order.getCustomerName(),
                order.getState(),
                order.getTaxRate(),
                order.getProductType(),
                order.getArea(),
                order.getCostPerSquareFoot(),
                order.getLaborCostPerSquareFoot(),
                order.getMaterialCost(),
                order.getLaborCost(),
                order.getTax(),
                order.getTotal());
        io.print(orderInfo);

        String answer = io.readString("Are you sure you want to delete the order? Y/N");
        return answer;
    }

    public String getEditedName(String currentName) {
       String answer = io.readStringEditedOrder("Enter customer name (" + currentName +"):", currentName);
       return answer;
    }

    public String getEditedState(String currentState, List<String> stateAbbreviations) {
        String answer = io.readStringEditedStateOrProduct("Enter one of the following sate abbrevations: " + stateAbbreviations + ". Current state is (" + currentState + ")", stateAbbreviations, currentState);
        return answer;
    }

    public String getEditedProduct(String currentProductType, List<String> productNames) {
        String answer = io.readStringEditedStateOrProduct("Enter one of the following products: " + productNames + ". Current product is (" + currentProductType + ")", productNames, currentProductType);
        return answer;
    }

    public BigDecimal getEditedOrderArea(BigDecimal currentArea) {
         BigDecimal area = io.readAreaEditedOrder("Enter project area (minimum order size is 100 sq ft). Current area is "+ currentArea, currentArea);
        return area;
    }
}
