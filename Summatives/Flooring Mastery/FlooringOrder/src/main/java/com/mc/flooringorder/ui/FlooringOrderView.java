/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.flooringorder.ui;

/**
 * created 11/21/20
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
}
