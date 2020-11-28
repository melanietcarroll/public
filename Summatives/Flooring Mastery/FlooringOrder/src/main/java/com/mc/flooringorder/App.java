/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.flooringorder;

import com.mc.flooringorder.controller.FlooringOrderController;
import com.mc.flooringorder.dao.FlooringOrderPersistenceException;
import com.mc.flooringorder.service.FlooringOrderNotFoundException;

/**
 * created 11/21/20
 * @author Melanie Carroll
 */
public class App {
    public static void main(String[] args) throws FlooringOrderPersistenceException, FlooringOrderNotFoundException {
        FlooringOrderController controller = new FlooringOrderController();
        controller.run();
    }
}
