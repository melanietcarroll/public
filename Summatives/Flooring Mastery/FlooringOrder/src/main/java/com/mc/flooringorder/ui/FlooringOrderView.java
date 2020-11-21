/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.flooringorder.ui;

import com.mc.flooringorder.dto.Order;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.regex.Pattern;

/**
 * created 11/21/20
 *
 * @author Melanie Carroll
 */
public class FlooringOrderView {

    private UserIO io = new UserIOConsoleImpl();

    public int printMenuAndGetSelection() {
        io.print("Main Menu");
        io.print("1. Display Orders");
        io.print("2. Add an Order");
        io.print("3. Edit an Order");
        io.print("4. Remove an Order");
        io.print("5. Quit");

        return io.readInt("Please select from the above choices.", 1, 5);
    }

    public LocalDate getOrderDate() {
        LocalDate date = io.readDate("Please enter order date in the following format: MM/dd/yyyy");
        return date;
    }

    public String getOrderName() {
        String name = io.readStringInput("Enter customer name (only alpha numeric characters, commas and periods are allowed.");
        return name;
    }

    public String getOrderState() {
        String state = io.readStringInput("Enter sate abbrevation.");
        return state;
    }

    public String getOrderProduct() {
        String product = io.readStringInput("Enter the product type.");
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
}
