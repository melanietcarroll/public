/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.vendingmachine.service;

/**
 *
 * @author Shawn
 */
public class VendingMachineDuplicateItemNameException extends Exception{
     public VendingMachineDuplicateItemNameException(String message) {
        super(message);
    }

    public VendingMachineDuplicateItemNameException(String message,
            Throwable cause) {
        super(message, cause);
    }
}
