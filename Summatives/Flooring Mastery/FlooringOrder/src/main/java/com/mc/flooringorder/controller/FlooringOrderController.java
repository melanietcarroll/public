/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.flooringorder.controller;

import com.mc.flooringorder.ui.FlooringOrderView;
import com.mc.flooringorder.ui.UserIO;
import com.mc.flooringorder.ui.UserIOConsoleImpl;

/**
 * created 11/21/20
 * @author Melanie Carroll
 */
public class FlooringOrderController {
    private FlooringOrderView view = new FlooringOrderView();
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
                    io.print("Add an order");
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
}