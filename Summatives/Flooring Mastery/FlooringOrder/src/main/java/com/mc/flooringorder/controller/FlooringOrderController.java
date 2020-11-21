/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.flooringorder.controller;

import com.mc.flooringorder.dao.FlooringOrderDao;
import com.mc.flooringorder.dao.FlooringOrderDaoFileImpl;
import com.mc.flooringorder.dto.Order;
import com.mc.flooringorder.ui.FlooringOrderView;
import com.mc.flooringorder.ui.UserIO;
import com.mc.flooringorder.ui.UserIOConsoleImpl;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * created 11/21/20
 * @author Melanie Carroll
 */
public class FlooringOrderController {
    private FlooringOrderView view = new FlooringOrderView();
    private FlooringOrderDao dao = new FlooringOrderDaoFileImpl();
    private UserIO io = new UserIOConsoleImpl();
    
    
     public void run() {
    boolean keepGoing = true;
    int menuSelection = 0;
   
        while (keepGoing) {              
            
            menuSelection = getMenuSelection();

            switch (menuSelection) {
                case 1:
                    io.print("Display Orders");
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
                    io.print("UNKNOWN COMMAND");
            }

        }
        io.print("Good Bye");
}
     private int getMenuSelection() {
    return view.printMenuAndGetSelection();
     }
     
     private void addOrder(){
         view.displayCreateOrderBanner();
         int getOrderNumberFromFile = 1;//read in order number from last order OR read in order number from a saved file
         Order newOrder = new Order(getOrderNumberFromFile);
         BigDecimal area = view.getOrderArea();
         LocalDate date = view.getOrderDate();//use date to create order file or append to existing order file
         String name = view.getOrderName();
         String product = view.getOrderProduct();//read in product objects into a list and check for match then use costpersquarefoot and laborcostpersquarefoot fields
         String state = view.getOrderState();//read in state objects into a list and check for match then use statename and taxrate fields
         newOrder.setCustomerName(name);
         //then do calculations
         //BigDecimal materialCost = new BigDecimal(2,RoundingMode.HALF_UP(area * costPerSquareFoot));
         //BigDecimal laborCost = new BigDecimal(2,RoundingMode.HALF_UP(area * laborCostPerSquareFoot));
         //BigDecimal tax = new BigDecimal(2,RoundingMode.HALF_UP((materialCost + laborCost) * (taxRate/100)); //tax rates are stored as whole numbers
         //BigDecimal total = new BigDecimal(2,RoundingMode.HALF_UP(materialCost + laborCost + tax));
         //set the rest of the Order fields
         dao.addOrder(getOrderNumberFromFile, newOrder);
         view.displayCreateSuccessBanner();
        
     }
     
     
}